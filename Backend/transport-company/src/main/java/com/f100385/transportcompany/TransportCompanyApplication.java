package com.f100385.transportcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Welcome to my humble demonstration.
 *<p>This is the backend for the Transport Company project that I've built.
 * It follows almost the standard pattern except that I do not use DTO,
 * instead, the library, Jackson, is used. With that in mind,
 * you might see Serializers and Deserializers around.
 *<p> Those act the same way.
 * <p>
 * @author  Georgi Angelov
 * @version 1.0
 * @since   2023-01-18
 */
@SpringBootApplication
public class TransportCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportCompanyApplication.class, args);
    }

}
