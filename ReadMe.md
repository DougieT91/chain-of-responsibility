# CHAIN-OF-RESPONSIBILITY  
## Background
This project is based on one of the object oriented [design patterns](https://en.wikipedia.org/wiki/Design_Patterns) introduced by Gang of Four(GoF).  The pattern is in fact called [**"chain-of-responsibility"**](https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern). A good book to learn these design patterns is [Head First Design Patterns](https://www.amazon.com/Head-First-Design-Patterns-Brain-Friendly/dp/0596007124) by Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra.
The four GoF authors were [Erich Gamma](http://en.wikipedia.org/wiki/Erich_Gamma), [Richard Helm](http://en.wikipedia.org/wiki/Richard_Helm), [Ralph Johnson](http://en.wikipedia.org/wiki/Ralph_Johnson) and [John Vlissides](http://en.wikipedia.org/wiki/John_Vlissides).

According to **refctoring.guru**:
> **Chain of Responsibility** is a behavioral design pattern that lets you pass requests along a chain of handlers. Upon receiving a request,
> each handler decides either to process the request or to pass it to
> the next handler in the chain.

A number of (simple) examples of the pattern in use can be found through a simple google search.  I liked [this one](https://refactoring.guru/design-patterns/chain-of-responsibility/java/example).

## The Project
### Adding This project to yours
This project is actually a library. built using `maven`. 
To use it in your project: 
- **first**, you need it in your ".m2" folder. Since the project is not on ***maven-central***, you will need to clone it (**`git clone project_url`**) and install it manually (**`mvn install`**).
- secondly you just need to import it into your project. At the point of creation of this document, the project was on version `1.0.0.RELEASE` but this may change in the future. I am also using maven sothe import statement looks like this:

```
<dependency>
    <groupId>zw.co.econet</groupId>
    <artifactId>chain-of-responsibility</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```

### Using the Library
Well, Using the library is quite simple. 

This Library uses a flow that is a bit different from the canonical version of the pattern given by various authors. Most of the pattern examples are built on the notion of looking for the **right handler**, launching it and exiting the chain after that. But here we execute every handler until one of three types of chain exits:
- the very last one in the chain, 
- there’s one (chain element) that **can’t handle** the request. 
- there’s one (chain element) that defines a **conditional exit** to the chain execution.

Be aware that this still is the Chain of Responsibility pattern, even though the flow is a bit different.

This Library consists 3 main components:

 1. The **`Chainable`** class: thisis an Abstract class that manages the chaining of services to each other. So any service that needs to be chained (Joined) to others needs to do 2 things:
	- firstly, extend the **`Chainable<T>`** class, specifying the type, **`T`**, which is the data type passed for processing.
	- secondly, Override the "**`processData()`**" method, which is responsible for all the processing of/on the data object. This is where the overriding class defines what happens to the data object or because of it.
	- As a point to note, the helper methods to actually build the chain and join elements to it are part of the **`Chainable`** class. To create the chain object for building you can use the helper static  method `createChain(Chainable<T> first)` method, where `first` is an already created/instatiated `Chainable` object. To add elements to that chain you use the `withNext(Chainable<T> next)` here `first` and `next` are already created/instatiated `Chainable` objects. the usual intsatiation preceded with `withNext(Chainable<T> next)` calls still works but has unpredictable behaviour and coupling issues yet to be ironed out, so it is not encouraged.

 2. The **`ChainOfResponsibility`** interface: This interface is responsible for joining the Chainable Elements and triggering the execution of the chain-processing of a provided `data` object of Type **`T`**. This is done by calling the *member* method **`process(T data)`**. You can define multiple chains using different combinations (order) of the same/different chain elements. The only catch is that you will have to give dirrent names to your **`ChainOfResponsibility`** classes/interfaces.  That being said, lets talk about how you use this interface:
	- firstly, you need to implement the **`ChainOfResponsibility<T>`** interface. If you are using and interface *(as some frameworks or design patterns encourage)*, you are encouraged to extend the **`ChainOfResponsibility<T>`** inferace from your interface, rather than implement it from the concrete class
	- secondly, you will be required to override the **`public Chainable<T> buildChain()`** method. This is the method where you create the chain in order *(as described in the `Chainable` element description)* and return it.
 3. The **`ChainBreakerException`** Exception: This (Runtime) Exception is used to break execution in a `ChainOfResponsibility` Chain. It is used to create the **Conditional Exit** we mentioned earlier. It creates the option for users that want to implement the canonical version of the pattern where one element is the **right handler**. It can also be used when the user decides a certain condition that has been met and termination needs to end at that point. The good part is you won't need to access the Exception itself. In fact You cant, as its not public. Instead you just use the `chainBreaker(...)` statement to break the chain. the `chainBreaker()` method will handle the rest for you. The method has 2 variations (through overloading) that that have the following parameters *(in order)*:
	- The latest (state) `data` object is required so
	- A message is **required** to explain reason for (premature) break of chain execution. 
	- An **optional** `cause` object *(of type `Throwable`)* can also be provided in this excetion, which means the user has an option to wrap other exceptions that may be thrown in a `ChainBreakerException` Runtime Exception.
	- This method returns an object of the same type as data, so basically you use the statement similar to:
	
	```
	return chainBreaker(data, "some_message", cause);
	```
 
---

### Summary
I have added some examples on usage of how to use the library and structure your components in the package [`com.tawandr.chain.of.responsibility.utils.example`]

If I was to sum it up in one sentence I would say this:
> "You simply need to make your services **`Chainable<T>`**, and link them to **`next`** to each other by building the **`ChainOfResponsibility<T>`** and **`process()`** your **`data`**(T), and if you hit a **`chainBreaker()`** condition, just return what you managed to **`process()`**", **Library developer**

Simple right? Enjoy!