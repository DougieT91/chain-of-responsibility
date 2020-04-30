package com.tawandr.chain.of.responsibility.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created By Dougie T Muringani : 25/4/2020
 *
 * @apiNote Abstract class that manages the chaining of services to each other.
 * @implNote Any service that needs to be chained to others needs to do 2 things:
 * firstly, extend the Chainable<T> class, specifying the type (T), which is the data type passed for processing
 * secondly, Override the "processData()" method, which is responsible for all the processing of/on the data object
 */

public abstract class Chainable<T> {

    /**
     * The Chainable object that will be processed after the current one.
     * <p>
     * Must be of the same type as the current one. i.e they both should be able to process the same data
     */
    protected Chainable<T> next;

    /**
     * Latest data state
     */
    private T data;

    /**
     * Narrative to explain reason of termination of chain. Also used to explain result in execution stage.
     */
    protected String message;

    private static final Logger log = LoggerFactory.getLogger(Chainable.class);

    protected Chainable() {
    }

    /**
     * This method is used to set the next element in the chain.
     *
     * @param next the next element in the chain element
     * @implNote replaces first element if it is the temporary Builder class with the "next" class
     */
    public Chainable<T> withNext(Chainable<T> next) {

        boolean thisIsBuilderPlaceholder = this.getClass() == Builder.class;
        if (thisIsBuilderPlaceholder) {
            return next;
        }

        if (this.next == null) {
            this.next = next;
            return this;
        }

        this.next.withNext(next);

        return this;
    }

    /**
     * This method is the one that invokes the user-defined method (processData())
     *
     * @param data: data object to be processed.
     *              <p>
     *              sets the latest data state before chain is broken or execution is completed
     *              returns the "data" object after it has been processed by the next element in the chain
     */
    public T process(T data) {
        try {
            data = processData(data);
        } catch (ChainBreakerException e) {
            message = e.getMessage();
            log.info("Chain Execution exited/broken prematurely with message:\n{}", message);
            return this.data;
        }
        return processNext(data);
    }

    /**
     * Helper methods to break chain execution by throwing a ChainBreakerException Exception
     */
    protected T chainBreaker(T data,
                             String message) {
        return chainBreaker(data, message, null);
    }

    protected T chainBreaker(T data,
                             String message,
                             Throwable cause) {
        this.data = data;
        if (cause == null) {
            throw new ChainBreakerException(message);
        } else {
            throw new ChainBreakerException(message, cause);
        }
    }


    /**
     * This is the main method in terms of the service's functionality. It is user-defined.
     * The method does some operations based on the "data" object.
     * The user is required to implement this method
     *
     * @param data: data object to be processed.
     *              <p>
     *              returns an object of the same type as "data", or the same object after it has been used for processing.
     *              User is recommended NOT to reassign "data" but rather modify it or return a new object all together hence "final"
     */
    protected abstract T processData(final T data);

    /**
     * method used to pass processing to next element in the chain
     *
     * @param data : data being processed.
     *             if there are no other elements in the chain (next == null), methods returns "data" the final result
     */
    private T processNext(T data) {
        if (next == null) {
            return data;
        }
        return next.process(data);
    }

    /**
     * Prints the chain in-order of execution. Direction denoted by arrow symbol ( -> )
     */
    public String printChain() {
        StringBuilder builder = new StringBuilder();
        builder.append("ChainOfResponsibility{ ");

        final String chainElements = printNext();
        builder.append(chainElements);

        builder.append(" }");
        return builder.toString();
    }

    /**
     * utility method used by the "printChain()" method
     */
    private String printNext() {
        final String className = this.getClass().getSimpleName();
        if (next == null) {
            return className;
        }
        return className + " -> " + next.printNext();
    }

    /**
     * method used to start the chain creation. starting with a temporary (Builder) class
     * Builder element is replaced as soon as the first element is added using "builder.withNext(first)"
     */
    public static <T> Chainable<T> createChain(Chainable<T> first) {
        final Builder<T> builder = new Builder<>();
        return builder.withNext(first);
    }

    /**
     * Builder Utility class. Temporary/Dummy Chainable concrete class
     * used to create a temporary initial Chainable Object as Chainable is abstract hence cannot be instantiated
     */
    private static class Builder<T> extends Chainable<T> {
        @Override
        protected T processData(T data) {
            return data;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "\n\tChainable{" +
                "\n\t\tnext=" + next +
                "\n\t}";
    }
}
