package dev.rubeen.java.learn.mockito.chapter02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    private final List<Person> persons = List.of(
            new Person(1, "John", "Doe", LocalDate.of(1980, Month.JANUARY, 1)),
            new Person(2, "Hans", "Müller", LocalDate.of(1985, Month.FEBRUARY, 2)),
            new Person(10, "Jane", "Doe", LocalDate.of(1990, Month.MARCH, 3)),
            new Person(20, "Max", "Mustermann", LocalDate.of(1995, Month.APRIL, 4)),
            new Person(30, "Erika", "Mustermann", LocalDate.of(2000, Month.MAY, 5)),
            new Person(40, "Peter", "Pan", LocalDate.of(2005, Month.JUNE, 6))
    );

    @Test
    void getLastNames_usingMockMethod () {
        final PersonRepository personRepository = mock(PersonRepository.class);
        when(personRepository.findAll()).thenReturn(persons);
        final PersonService personService = new PersonService(personRepository);

        final List<String> lastNames = personService.getLastNames();

        assertThat(lastNames)
                .contains("Doe", "Müller", "Mustermann", "Pan");
        verify(personRepository).findAll();
    }

    @Test
    void getLastNames_usingAnnotations () {
        when(repository.findAll()).thenReturn(persons);

        final List<String> lastNames = service.getLastNames();

        assertThat(lastNames)
                .contains("Doe", "Müller", "Mustermann", "Pan");
        verify(repository).findAll();
    }

    @Test
    void findByIds_explicitWhens () {
        when(repository.findById(1)).thenReturn(Optional.of(persons.get(0)));
        when(repository.findById(2)).thenReturn(Optional.of(persons.get(1)));
        when(repository.findById(3)).thenReturn(Optional.empty());

        final List<Person> actual = service.findByIds(1, 2, 3);

        assertThat(actual).containsExactly(persons.get(0), persons.get(1));
    }

    @Test
    void findByIds_thenReturnWithMultipleArgs () {
        when(repository.findById(anyInt())).thenReturn(
                Optional.of(persons.get(0)),
                Optional.of(persons.get(1)),
                Optional.empty()
        );

        final List<Person> actual = service.findByIds(1, 2, 3);

        assertThat(actual).containsExactly(persons.get(0), persons.get(1));
    }

    @Test
    void deleteAllWithNulls () {
        when(repository.findAll()).thenReturn(Collections.singletonList(null));
        doThrow(RuntimeException.class).when(repository).delete(null);

        assertThatThrownBy(() -> service.deleteAll())
                .isInstanceOf(RuntimeException.class);
        verify(repository).delete(null);
    }
}
