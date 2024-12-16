package com.microservice.activity.presentation.controller;

import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
import com.microservice.activity.service.interfaces.IActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/activities")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActivityController {

    private final IActivityService activityService;


    @Tag(name = "GET", description = "Get methods")
    @Operation(summary = "Get all activities",
            description = "Get all activities")
    @GetMapping
    private ResponseEntity<List<ActivityDto>> findAll() {
        return ResponseEntity.ok(activityService.findAll());
    }

    @GetMapping("/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Get activity by ID",
            description = "Get activity by ID")
    ResponseEntity<?> findDisciplineById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(activityService.findById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El id dado no corresponde a ninguna actividad!");
        }
    }

    @PutMapping("/update/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Update activity", description = "Update activity")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody ActivityRequestDto activityRequestDto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(activityService.update(id, activityRequestDto));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    @PostMapping("/save")
    @Tag(name = "POST", description = "Post Methods")
    @Operation(summary = "Save activity",
            description = "Save activity")
    private ResponseEntity<?> save(@RequestBody ActivityRequestDto activityRequestDto) {
        try {
            return ResponseEntity.ok(activityService.save(activityRequestDto));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible crear la actividad, verifique los datos!");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Delete activity by ID",
            description = "Delete activity by ID")
    private ResponseEntity<?> delete(@RequestBody Long id) {
        try {
            ActivityDto activityDto = activityService.findById(id);
            activityService.deleteById(id);
            return ResponseEntity.ok(activityDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible eliminar la actividad, verifique los datos!");
        }
    }

    @GetMapping("/search-client/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Search clients by activity ID",
            description = "Search clients by activity ID")
    public ResponseEntity<?> findClientsByIdActivity(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(activityService.findClientsByIdActivity(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El id dado no corresponde a ninguna actividad!");
        }
    }
}
