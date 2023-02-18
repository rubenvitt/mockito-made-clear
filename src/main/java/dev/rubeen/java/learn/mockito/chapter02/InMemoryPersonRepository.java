package dev.rubeen.java.learn.mockito.chapter02;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class InMemoryPersonRepository implements PersonRepository{

    private final List<Person> persons = Collections.synchronizedList(new LinkedList<>());

    @Override
    public Person save (final Person person) {
        persons.add(person);
        return person;
    }

    @Override
    public Optional<Person> findById (final int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst();
    }

    @Override
    public List<Person> findAll () {
        return persons;
    }

    @Override
    public long count () {
        return persons.size();
    }

    @Override
    public void delete (final Person person) {
        persons.remove(person);
    }
}
