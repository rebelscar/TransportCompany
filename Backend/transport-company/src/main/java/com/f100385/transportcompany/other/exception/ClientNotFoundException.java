package com.f100385.transportcompany.other.exception;

public class ClientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(int id) {
        super("Could not find Client!\nDEBUG MODE: id = " + id);
    }

}
