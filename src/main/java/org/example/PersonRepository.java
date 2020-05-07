package org.example;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created on 07.05.2020.
 * <p>
 * In-memory data-store of persons
 *
 * @author Sergey Radchenko
 */
@Component
public class PersonRepository {

    private HashMap<Integer, Person> persons;

    public PersonRepository() {
        this.persons = new HashMap<>();
    }

    public PersonRepository(HashMap<Integer, Person> persons) {
        this.persons = persons;
    }

    /**
     * Creates new person with provided parameters
     *
     * @param id      person's id
     * @param name    person's name
     * @param surname person's surname
     * @return new person
     */
    public Person create(int id, String name, String surname) {
        if (!(persons.containsKey(id) || existsWithNameAndSurname(name, surname))) {
            Person newPerson = new Person(id, name, surname);
            persons.put(id, newPerson);
            return newPerson;
        } else throw new RuntimeException("Entity with selected id or name and surname pair already exists.");
    }

    /**
     * Person by id
     *
     * @param id person's id
     * @return person with existing id
     */
    public Person getById(int id) {
        return persons.get(id);
    }

    /**
     * Update name and surname of existing person
     *
     * @param id      existing person's id
     * @param name    person's name
     * @param surname person's surname
     * @return updated person
     */
    public Person update(int id, String name, String surname) {
        Person person = persons.get(id);

        if (person == null) throw new RuntimeException("Entity with selected id not exists.");
        if (existsWithNameAndSurname(name, surname))
            throw new RuntimeException("Entity with same name and surname already exists.");

        person.setName(name);
        person.setSurname(surname);

        persons.put(id, person);
        return person;
    }

    private boolean existsWithNameAndSurname(String name, String surname) {
        return persons.values()
                      .stream()
                      .anyMatch(p -> p.getName().equals(name) &&
                                     p.getSurname().equals(surname));
    }
}
