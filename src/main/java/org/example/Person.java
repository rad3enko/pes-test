package org.example;

/**
 * Created on 07.05.2020.
 * <p>
 * The main domain
 *
 * @author Sergey Radchenko
 */
public class Person {

    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /**
     * To enable jackson serialization
     */
    private Person() {}

    private int id;
    private String name;
    private String surname;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
