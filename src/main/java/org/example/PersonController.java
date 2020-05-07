package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 07.05.2020.
 * <p>
 * Controller for persons entities
 *
 * @author Sergey Radchenko
 */
@RestController
public class PersonController {

    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates new person with params of body
     *
     * @param person new person params
     * @return created person
     */
    @PostMapping("api/post")
    public Person createPerson(@RequestBody Person person) {
        return repository.create(person.getId(),
                                 person.getName(),
                                 person.getSurname());
    }

    /**
     * Get person by id
     *
     * @param id person's id
     * @return person
     */
    @GetMapping("api/get/{id}")
    public Person getPerson(@PathVariable int id) {
        return repository.getById(id);
    }

    /**
     * @param id        existing person's id
     * @param updateDto dto of new person's parameters
     * @return updated person entity
     */
    @PutMapping("api/put{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody PersonUpdateDto updateDto) {
        return repository.update(id, updateDto.getName(), updateDto.getSurname());
    }
}
