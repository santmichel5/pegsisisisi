package com.example.h2demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            person.setName(personDetails.getName());
            person.setAge(personDetails.getAge());
            return personRepository.save(person);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}
