package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Retourne tous les étudiants
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // Compte le nombre d'étudiants
    public long countStudents() {
        return studentRepository.count();
    }

    // Retourne le nombre d'étudiants par année
    public Collection<Object[]> findNbrStudentByYear() {
        return studentRepository.findNbrStudentByYear();
    }

    // Sauvegarde un étudiant
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    // Supprime un étudiant par ID
    public boolean delete(int id) {
        if(studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Sauvegarde une liste d'étudiants
    public List<Student> saveAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }
}
