package com.binnosoc.studend;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	private StudentRepository studentRepository;

 public void saveStudent(StudentEntity student) {
	 studentRepository.save(student);
 }
 
 public StudentEntity findStudentById(Integer id) {
	 return studentRepository.findById(id).map(StudentEntity).orElseThrow(
			 ()-> new EntityNotFoundException("Aucun etudiant avec l'ID = " + id + " n' ete trouve dans la BDD"));
			 

 }
 
}
