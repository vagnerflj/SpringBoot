package com.example.demo.repositories;
import com.example.demo.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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
    @DisplayName("Given Person List When FindAll then Return PersonList")
    @Test
    void testGivenPersonList_WhenFindAll_thenReturnPersonList(){
        //Given
        Person person0 = new Person("Vagner", "Ferreira", "Parana - Brasil", "Male", "vagner@gmail.com");

        Person person1 = new Person("Leonardo", "Ferreira", "Parana - Brasil", "Male", "vagner@gmail.com");

        repository.save(person0);
        repository.save(person1);

        //When
        List<Person> personList = repository.findAll();

        //Then
        assertNotNull(personList);
        assertEquals(2, personList.size());
    }
    @DisplayName("Given Person Object When FindById then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindById_thenReturnPersonObject(){
        //Given
        Person person0 = new Person("Vagner", "Ferreira", "Parana - Brasil", "Male", "vagner@gmail.com");

        repository.save(person0);
        //When
        Person savedPerson = repository.findById(person0.getId()).get();

        //Then
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());
    }
    @DisplayName("Given Person Object When FindByEmail then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindByEmail_thenReturnPersonObject(){
        //Given
        Person person0 = new Person("Vagner", "Ferreira", "Parana - Brasil", "Male", "vagner@gmail.com");

        repository.save(person0);
        //When
        Person savedPerson = repository.findByEmail(person0.getEmail()).get();

        //Then
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());
    }
    @DisplayName("Given Person Object When Update Person then Return Update Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatePersonObject(){
        //Given
        Person person0 = new Person("Vagner", "Ferreira", "Parana - Brasil", "Male", "vagner@gmail.com");

        repository.save(person0);
        //When
        Person savedPerson = repository.findById(person0.getId()).get();
        savedPerson.setFirstName("Leonardo");
        savedPerson.setEmail("Leonardo@gmail.com");

        Person updatePerson = repository.save(savedPerson);

        //Then
        assertNotNull(savedPerson);
        assertEquals("Leonardo", updatePerson.getFirstName());
        assertEquals("Leonardo@gmail.com", updatePerson.getEmail());
    }
    @DisplayName("Given Person Object When Delete then Remove Person")
    @Test
    void testGivenPersonObject_WhenDelete_thenRemovePerson(){
        //Given
        Person person0 = new Person("Vagner", "Ferreira", "Parana - Brasil", "Male", "vagner@gmail.com");

        repository.save(person0);
        //When
        repository.deleteById(person0.getId());

        Optional<Person> personOptional = repository.findById(person0.getId());

        //Then
        assertTrue(personOptional.isEmpty());
    }

}