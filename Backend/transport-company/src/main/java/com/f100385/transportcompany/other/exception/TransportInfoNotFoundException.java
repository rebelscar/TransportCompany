package com.f100385.transportcompany.other.exception;

public class TransportInfoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TransportInfoNotFoundException(int id) {
        super("Could not find transport information!\nDEBUG MODE: id = " + id);
    }

}
