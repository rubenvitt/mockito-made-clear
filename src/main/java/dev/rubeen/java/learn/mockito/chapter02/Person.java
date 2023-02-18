package dev.rubeen.java.learn.mockito.chapter02;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class Person {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;

    public Person (final int id, final String firstName, final String lastName, final LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getId () {
        return id;
    }

    public String getFirstName () {
        return firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public LocalDate getBirthDate () {
        return birthDate;
    }

    @Override
    public String toString () {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("birthDate", birthDate)
                .toString();
    }
}
