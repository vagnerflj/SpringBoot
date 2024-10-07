package com.example.demo.services;


import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());
    @Autowired
    PersonRepository repository;

    public List<Person> findAll( ){

        return repository.findAll();
    }



    public Person findById(Long id){

        logger.info("Finding one person!");

        Person person = new Person();

        person.setFirstName("Vagner");
        person.setLastName("Ferreira");
        person.setAddress("Telemaco borba");
        person.setGender("Male");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records fould for this ID!"));
    }
    public Person create(Person person) {

        logger.info("Creating one person!");

        return repository.save(person);
    }
    public Person update(Person person) {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records fould for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());



        return repository.save(person);
    }
    @Transactional
    public void delete(Long id) {
        logger.info("Deleting one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

}
