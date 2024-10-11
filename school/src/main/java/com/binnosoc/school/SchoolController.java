package com.binnosoc.school;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @Operation(summary = "Récupérer toutes les écoles", description = "Cette méthode permet de récupérer la liste complète des écoles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Les écoles ont été récupérées avec succès", 
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SchoolEntity.class))),
            @ApiResponse(responseCode = "500", description = "Erreur serveur lors de la récupération des écoles")
    })
    @GetMapping
    public List<SchoolEntity> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @Operation(summary = "Récupérer une école par ID", description = "Cette méthode permet de récupérer une école spécifique en fonction de son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'école a été récupérée avec succès", 
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SchoolEntity.class))),
            @ApiResponse(responseCode = "404", description = "Aucune école trouvée avec cet ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SchoolEntity> getSchoolById(@PathVariable Long id) {
        Optional<SchoolEntity> school = schoolService.getSchoolById(id);
        if (school.isPresent()) {
            return ResponseEntity.ok(school.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Créer une nouvelle école", description = "Cette méthode permet de créer une nouvelle école.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "L'école a été créée avec succès", 
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SchoolEntity.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides pour la création de l'école")
    })
    @PostMapping
    public SchoolEntity createSchool(@RequestBody SchoolEntity school) {
        return schoolService.createSchool(school);
    }

    @Operation(summary = "Mettre à jour une école par ID", description = "Cette méthode permet de mettre à jour une école existante en fonction de son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'école a été mise à jour avec succès", 
                         content = @Content(mediaType = "application/json", schema = @Schema(implementation = SchoolEntity.class))),
            @ApiResponse(responseCode = "404", description = "Aucune école trouvée avec cet ID"),
            @ApiResponse(responseCode = "400", description = "Données invalides pour la mise à jour de l'école")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SchoolEntity> updateSchool(@PathVariable Long id, @RequestBody SchoolEntity updatedSchool) {
        SchoolEntity school = schoolService.updateSchool(id, updatedSchool);
        if (school != null) {
            return ResponseEntity.ok(school);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Supprimer une école par ID", description = "Cette méthode permet de supprimer une école existante en fonction de son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "L'école a été supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune école trouvée avec cet ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}
