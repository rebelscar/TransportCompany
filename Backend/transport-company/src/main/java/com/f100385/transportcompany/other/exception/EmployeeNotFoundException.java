package com.f100385.transportcompany.other.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(int id) {
        super("Could not find Employee!\nDEBUG MODE: id = " + id);
    }

}
