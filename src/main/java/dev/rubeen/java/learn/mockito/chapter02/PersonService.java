package dev.rubeen.java.learn.mockito.chapter02;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService (final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<String> getLastNames () {
        return personRepository.findAll().stream()
                .map(Person::getLastName)
                .toList();
    }

    public List<Person> findByIds (final int... ids) {
        return Arrays.stream(ids)
                .mapToObj(personRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public void deleteAll () {
        personRepository.findAll().forEach(personRepository::delete);
    }
}
