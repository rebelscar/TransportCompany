package com.f100385.transportcompany.other.exception;

public class TransactionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TransactionNotFoundException(int id) {
        super("Could not find Transaction!\nDEBUG MODE: id =" + id);
    }


}
