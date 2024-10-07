package com.example.demo.repositories;
import com.example.demo.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PersonRepositoryTest{

    @Autowired
    private PersonRepository repository;
    @DisplayName("Given Person Object When Save then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnSavedPerson(){
        //Given
        Person person0 = new Person("Vagner", "Ferreira", "Parana - Brasil", "Male", "vagner@gmail.com");

        //When
        Person savedPerson = repository.save(person0);

        //Then
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }


}