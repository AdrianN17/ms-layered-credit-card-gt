package com.bank.credit_card.generic.exception;

public class BadGatewayException extends ServiceException {

    public BadGatewayException() {
        super();
    }

    public BadGatewayException(String message) {
        super(message);
    }

    public BadGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadGatewayException(Throwable cause) {
        super(cause);
    }
}

