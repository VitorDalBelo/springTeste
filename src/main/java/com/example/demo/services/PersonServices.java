package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.demo.model.Person;

@Service
public class PersonServices {
   private final AtomicLong counter = new AtomicLong();
   private Logger logger = Logger.getLogger(PersonServices.class.getName());

   public Person findById(String id){
      logger.info("Finding for one person");
      Person person = new Person();
      person.setId(counter.incrementAndGet());
      person.setFirstName("João");
      person.setLastName("Silva");
      person.setAddress("Rua ABC, 123");
      person.setGender("Masculino");

      return person;
   }

   public List<Person> findAll(){
      logger.info("Finding all people");
      List<Person> people = new ArrayList<>();
      for(int i = 0; i < 8 ; i++){
         Person person = this.mockPerson(i);
         people.add(person);
      }
      return people;
   }

   public Person create(Person person){
      logger.info("Creating a people");
      
      return person;
   }

   public Person update(Person person){
      logger.info("Updating a people");

      return person;
   }

   public void delete(String id){
      logger.info("Deleting a people");
   }

   private Person mockPerson (int i) {
   Person person = new Person ();
   person. setId(counter.incrementAndGet());
   person.setFirstName("Person name " + i);
   person.setLastName("Last name " + i);
   person.setAddress("Some address in Brasil" + i);
   person. setGender (i % 2 == 0 ? "Masculino" : "Feminino");
   return person;
   }
}
