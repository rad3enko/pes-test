package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Created on 07.05.2020.
 * <p>
 * Unit tests for PersonRepository class
 *
 * @author Sergey Radchenko
 */
public class PersonRepositoryTest {

    private final HashMap<Integer, Person> mockMap = mock(HashMap.class);
    private final PersonRepository repository = new PersonRepository(mockMap);
    private final Person mockPerson = mock(Person.class);

    private final int id = 1;
    private final String name = "Douglas";
    private final String surname = "Adams";

    @Test
    public void testCreate() {
        // Arrange
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);

        // Act
        Person result = repository.create(id, name, surname);

        // Assert
        verify(mockMap).put(eq(id), personCaptor.capture());

        assertEquals(id, personCaptor.getValue().getId());
        assertEquals(name, personCaptor.getValue().getName());
        assertEquals(surname, personCaptor.getValue().getSurname());

        assertEquals(id, result.getId());
        assertEquals(name, result.getName());
        assertEquals(surname, result.getSurname());
    }

    @Test
    public void testCreateWhenIdExists() {
        // Arrange
        when(mockMap.containsKey(any(Integer.class))).thenReturn(true);

        // Act && Assert
        assertThrows(RuntimeException.class, () -> repository.create(id, name, surname));
        verify(mockMap).containsKey(id);
    }

    @Test
    public void testCreateWhenNameAndSurnameExists() {
        // Arrange
        when(mockMap.values()).thenReturn(Collections.singletonList(new Person(id + 1, name, surname)));

        // Act
        assertThrows(RuntimeException.class, () -> repository.create(id, name, surname));
    }

    @Test
    public void testGetById() {
        // Arrange
        when(mockMap.get(any(Integer.class))).thenReturn(mockPerson);

        // Act
        Person result = repository.getById(id);

        // Assert
        verify(mockMap).get(id);
        assertEquals(mockPerson, result);
    }

    @Test
    public void testUpdate() {
        // Arrange
        when(mockMap.get(any(Integer.class))).thenReturn(mockPerson);

        // Act
        Person result = repository.update(id, name, surname);

        // Assert
        verify(mockMap).get(id);
        verify(mockPerson).setName(name);
        verify(mockPerson).setSurname(surname);
        verify(mockMap).put(id, mockPerson);

        assertEquals(mockPerson, result);
    }

    @Test
    public void testUpdateNotExistingPerson() {
        // Arrange
        when(mockMap.get(any(Integer.class))).thenReturn(null);

        // Act && Assert
        assertThrows(RuntimeException.class, () -> repository.update(id, name, surname));
        verify(mockMap).get(id);
    }

    @Test
    public void testUpdateForExistingNameAndSurname() {
        // Arrange
        when(mockMap.values()).thenReturn(Collections.singletonList(new Person(id + 1, name, surname)));

        // Act && Assert
        assertThrows(RuntimeException.class, () -> repository.update(id, name, surname));
        verify(mockMap).get(id);
    }
}
