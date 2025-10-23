package com.example.demo;

import com.example.demo.controllers.StudentController;
import com.example.demo.entities.Student;
import com.example.demo.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setId(1);
        student.setName("Alice");

        when(studentService.save(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.save(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(student, response.getBody());
        verify(studentService, times(1)).save(student);
    }

    @Test
    void testSaveAllStudents() {
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("Alice");

        Student s2 = new Student();
        s2.setId(2);
        s2.setName("Bob");

        List<Student> students = Arrays.asList(s1, s2);

        when(studentService.saveAll(students)).thenReturn(students);

        ResponseEntity<List<Student>> response = studentController.saveAll(students);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(studentService, times(1)).saveAll(students);
    }

    @Test
    void testDeleteStudent() {
        int studentId = 1;
        when(studentService.delete(studentId)).thenReturn(true);

        ResponseEntity<Void> response = studentController.delete(studentId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).delete(studentId);
    }

    @Test
    void testFindAllStudents() {
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("Alice");

        List<Student> students = List.of(s1);

        when(studentService.findAll()).thenReturn(students);

        ResponseEntity<List<Student>> response = studentController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(studentService, times(1)).findAll();
    }

    @Test
    void testCountStudents() {
        long count = 5L;
        when(studentService.countStudents()).thenReturn(count);

        ResponseEntity<Long> response = studentController.countStudent();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(count, response.getBody());
        verify(studentService, times(1)).countStudents();
    }

    @Test
    void testFindByYear() {
        // Mocker avec Collection<Object[]>
        Collection<Object[]> byYear = Arrays.asList(
                new Object[]{"2023", 10L},
                new Object[]{"2024", 12L}
        );

        when(studentService.findNbrStudentByYear()).thenReturn(byYear);

        ResponseEntity<Collection<?>> response = studentController.findByYear();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(studentService, times(1)).findNbrStudentByYear();
    }
}
