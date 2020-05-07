package org.example;

/**
 * Created on 07.05.2020.
 * <p>
 * Data transfer object for update API
 *
 * @author Sergey Radchenko
 */
public class PersonUpdateDto {

    private String name;
    private String surname;

    public PersonUpdateDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public PersonUpdateDto() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
