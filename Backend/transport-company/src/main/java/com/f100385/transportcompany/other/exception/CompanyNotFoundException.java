package com.f100385.transportcompany.other.exception;

public class CompanyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CompanyNotFoundException(int id) {
        super("Could not find Company!\nDEBUG MODE: id = " + id);
    }

}
