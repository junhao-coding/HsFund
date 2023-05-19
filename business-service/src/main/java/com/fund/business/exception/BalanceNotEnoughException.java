package com.fund.business.exception;

public class BalanceNotEnoughException extends RuntimeException{

    public BalanceNotEnoughException(String message) {
        super(message);
    }

    public BalanceNotEnoughException() {
    }
}
