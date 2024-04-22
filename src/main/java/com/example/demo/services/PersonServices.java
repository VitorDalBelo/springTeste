package com.example.demo.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;

@Service
public class PersonServices {
   private final AtomicLong counter = new AtomicLong();
   private Logger logger = Logger.getLogger(PersonServices.class.getName());

   @Autowired
   PersonRepository personRepository;

   public Person findById(Long id){
      logger.info("Finding for one person");

      return personRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Person not found"));
   }

   public List<Person> findAll(){
      logger.info("Finding all people");

      return personRepository.findAll();
   }

   public Person create(Person person){
      logger.info("Creating a people");
      
      return personRepository.save(person);
   }

   public Person update(Person person){
      logger.info("Updating a people");

      Person entity = this.findById(person.getId());

      entity.setFirstName(person.getFirstName());
      entity.setLastName(person.getLastName());
      entity.setAddress (person.getAddress());
      entity.setGender(person.getGender ()) ;

      return personRepository.save(entity);
   }

   public void delete(Long id){
      logger.info("Deleting a people");

      Person entity = this.findById(id);

      personRepository.delete(entity);
   }

}
