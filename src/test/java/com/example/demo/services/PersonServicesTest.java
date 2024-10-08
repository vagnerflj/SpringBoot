package com.example.demo.services;


import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

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


}
