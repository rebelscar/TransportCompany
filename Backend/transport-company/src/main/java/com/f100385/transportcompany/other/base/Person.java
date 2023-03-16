package com.f100385.transportcompany.other.base;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

/**
 * The class represents a person that has demo variables to
 * demonstrate and distinguish human beings.
 */
@MappedSuperclass
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    public Person(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    protected Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID='" + id + '\'' +
                "First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", age=" + age;
    }
}
