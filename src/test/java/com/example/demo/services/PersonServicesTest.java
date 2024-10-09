package com.example.demo.services;


import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository repository;
    @InjectMocks
    private PersonServices services;
    private Person person0;
    @BeforeEach
    public void setup(){
        //Given
        person0 = new Person("Vagner",
                "Ferreira",
                "Parana - Brasil",
                "Male",
                "vagner@gmail.com");
    }
    @DisplayName("JUnit test for Given Person Object When Save Person then Return Person Object")
    @Test
    void testeGivenPersonObject_WhenSavePerson_thenReturnPersonObject(){
        // Given
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person0)).willReturn(person0);
        // When
        Person savedPerson = services.create(person0);

        // Then
        assertNotNull(savedPerson);
        assertEquals("Vagner", savedPerson.getFirstName());
    }
    @DisplayName("Given Existin Email When Save Person then Throws Exception")
    @Test
    void testeGivenExistinEmail_WhenSavePerson_thenThrowsException(){
        // Given
        given(repository.findByEmail(anyString())).willReturn(Optional.of(person0));
        // When
        assertThrows(ResourceNotFoundException.class, () -> {
            services.create(person0);
        });
        // Then
        verify(repository, never()).save(any(Person.class));
    }
    @DisplayName("Given Persons List When FindAll Person then Return Persons List")
    @Test
    void testeGivenPersonsList_WhenFindAllPerson_thenReturnPersonsList(){
        // Given
        Person person1 = new Person("Leonardo", "Ferreira", "Parana - Brasil", "Male", "vagner@gmail.com");

        given(repository.findAll()).willReturn(List.of(person0, person1));

        // When
        List<Person> personList = services.findAll();
        // Then
        assertNotNull(personList);
        assertEquals(2, personList.size());
    }
    @DisplayName("Given Persons Empty List When FindAll Person then Return Empty Persons List")
    @Test
    void testeGivenEmptyPersonsList_WhenFindAllPerson_thenReturnEmptyPersonsList(){
        // Given
        given(repository.findAll()).willReturn(Collections.emptyList());

        // When
        List<Person> personList = services.findAll();
        // Then
        assertTrue(personList.isEmpty());
        assertEquals(0, personList.size());
    }
    @DisplayName("Given Persons Id When FindById Person then Return Persons Object")
    @Test
    void testeGivenPersonId_WhenFindById_thenReturnPersonObject(){
        // Given
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        // When
        Person savedPerson = services.findById(1L);

        // Then
        assertNotNull(savedPerson);
        assertEquals("Vagner", savedPerson.getFirstName());
    }
    @DisplayName("Given Persons object When update Person then Return updated Persons Object")
    @Test
    void testeGivenPersonObject_WhenUpdatePerson_thenReturnUpdatedPersonObject(){
        // Given
        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        person0.setEmail("vagner@gmail.com");
        person0.setFirstName("Vagner");

        given(repository.save(person0)).willReturn(person0);

        // When
        Person updatedPerson = services.update(person0);

        // Then
        assertNotNull(updatedPerson);
        assertEquals("Vagner", updatedPerson.getFirstName());
        assertEquals("vagner@gmail.com", updatedPerson.getEmail());
    }
    @DisplayName("Given PersonId When Delete Person then Do Nothing")
    @Test
    void testeGivenPersonId_WhenDeletePerson_thenDoNothing(){
        // Given
        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));
        willDoNothing().given(repository).delete(person0);

        // When
        services.delete(person0.getId());

        // Then
        verify(repository, times(1)).delete(person0);
    }



}
