package com.bank.credit_card.generic.exception;

public class UnprocessableEntityException extends ServiceException {

    public UnprocessableEntityException() {
        super();
    }

    public UnprocessableEntityException(String message) {
        super(message);
    }

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnprocessableEntityException(Throwable cause) {
        super(cause);
    }
}

