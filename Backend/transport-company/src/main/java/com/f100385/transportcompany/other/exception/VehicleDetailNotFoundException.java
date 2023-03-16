package com.f100385.transportcompany.other.exception;

public class VehicleDetailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VehicleDetailNotFoundException(int id) {
        super("Could not find vehicle detail!\nDEBUG MODE: id = " + id);
    }
}
