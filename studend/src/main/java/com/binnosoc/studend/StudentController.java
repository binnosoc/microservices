package com.binnosoc.studend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET all students
    @Operation(summary = "Récupérer tous les étudiants", description = "Cette méthode permet de récupérer la liste de tous les étudiants.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des étudiants récupérée avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))),
            @ApiResponse(responseCode = "500", description = "Erreur lors de la récupération des étudiants")
    })
    @GetMapping
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET a student by ID
    @Operation(summary = "Récupérer un étudiant par ID", description = "Cette méthode permet de récupérer un étudiant spécifique en fonction de son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'étudiant a été récupéré avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))),
            @ApiResponse(responseCode = "404", description = "Aucun étudiant trouvé avec cet ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Integer id) {
        Optional<StudentEntity> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CREATE a new student
    @Operation(summary = "Créer un nouvel étudiant", description = "Cette méthode permet d'enregistrer un nouvel étudiant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'étudiant a été créé avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))),
            @ApiResponse(responseCode = "400", description = "Les données de l'étudiant ne sont pas valides")
    })
    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentEntity student) {
        return studentService.createStudent(student);
    }

    // UPDATE an existing student
    @Operation(summary = "Mettre à jour un étudiant", description = "Cette méthode permet de mettre à jour les informations d'un étudiant existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'étudiant a été mis à jour avec succès", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StudentEntity.class))),
            @ApiResponse(responseCode = "404", description = "Aucun étudiant trouvé avec cet ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Integer id, @RequestBody StudentEntity updatedStudent) {
        Optional<StudentEntity> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            StudentEntity savedStudent = studentService.updateStudent(id, updatedStudent);
            return ResponseEntity.ok(savedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a student by ID
    @Operation(summary = "Supprimer un étudiant par ID", description = "Cette méthode permet de supprimer un étudiant spécifique en fonction de son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "L'étudiant a été supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun étudiant trouvé avec cet ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        Optional<StudentEntity> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
