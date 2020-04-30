package com.tawandr.chain.of.responsibility.utils;

/**
 * Created By Dougie T Muringani : 25/4/2020
 *
 * @implNote Exception Used to break execution in a ChainOfResponsibility Chain.
 * @implSpec message is required to explain reason for (premature) break of chain execution
 */
class ChainBreakerException extends RuntimeException {
    ChainBreakerException(String message) {
        super(message);
    }

    ChainBreakerException(String message,
                                  Throwable cause) {
        super(message, cause);
    }
}
