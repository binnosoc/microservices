package com.binnosoc.studend;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    // Sauvegarder un étudiant (Create)
    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    // Récupérer tous les étudiants (Read)
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    // Trouver un étudiant par son ID (Read)
    public Optional<StudentEntity> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    // Mettre à jour un étudiant (Update)
    public StudentEntity updateStudent(Integer id, StudentEntity updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setSchoolId(updatedStudent.getSchoolId());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new EntityNotFoundException("Aucun étudiant avec l'ID = " + id + " n'a été trouvé dans la BDD"));
    }

    // Supprimer un étudiant (Delete)
    public void deleteStudent(Integer id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun étudiant avec l'ID = " + id + " n'a été trouvé dans la BDD"));
        studentRepository.delete(student);
    }
}
