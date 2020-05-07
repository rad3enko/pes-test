package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created on 07.05.2020.
 * <p>
 * Unit tests for PersonController class
 *
 * @author Sergey Radchenko
 */
public class PersonControllerTest {

    private final PersonRepository repository = mock(PersonRepository.class);
    private final PersonController controller = new PersonController(repository);

    private final int id = 1;
    private final String name = "Douglas";
    private final String surname = "Adams";

    private Person mockPerson = mock(Person.class);

    @Test
    public void testCreatePerson() {
        // Arrange
        Person createPerson = new Person(id, name, surname);
        when(repository.create(any(Integer.class), any(String.class), any(String.class))).thenReturn(mockPerson);

        // Act
        Person result = controller.createPerson(createPerson);

        // Assert
        verify(repository).create(id, name, surname);
        assertEquals(mockPerson, result);
    }

    @Test
    public void testGetPerson() {
        // Arrange
        when(repository.getById(any(Integer.class))).thenReturn(mockPerson);

        // Act
        Person result = controller.getPerson(id);

        // Assert
        verify(repository).getById(id);
        assertEquals(mockPerson, result);
    }

    @Test
    public void testUpdatePerson() {
        // Arrange
        PersonUpdateDto updateDto = new PersonUpdateDto(name, surname);
        when(repository.update(any(Integer.class), any(String.class), any(String.class))).thenReturn(mockPerson);

        // Act
        Person result = controller.updatePerson(id, updateDto);

        // Assert
        verify(repository).update(id, name, surname);
        assertEquals(mockPerson, result);
    }
}
