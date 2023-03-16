package com.f100385.transportcompany.other.exception;

public class VehicleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VehicleNotFoundException(int id) {
        super("Could not find Vehicle!\nDEBUG MODE: id = " + id);
    }


}
