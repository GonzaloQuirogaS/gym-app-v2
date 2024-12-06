package com.microservice.activity.presentation.controller;

import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
import com.microservice.activity.service.interfaces.IActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final IActivityService activityService;

    @GetMapping
    private ResponseEntity<List<ActivityDto>> findAll() {
        List<ActivityDto> activities = activityService.findAll();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findDisciplineById(@PathVariable Long id) {
        try {
            ActivityDto activityDto = activityService.findById(id);
            return ResponseEntity.ok(activityDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El id dado no corresponde a ninguna actividad!");
        }
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody ActivityRequestDto activityRequestDto) {
        try {
            ActivityDto activityDto = activityService.update(id, activityRequestDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(activityDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la actividad!");
        }
    }

    @PostMapping("/save")
    private ResponseEntity<?> save(@RequestBody ActivityRequestDto activityRequestDto) {
        try {
            ActivityDto activityDto = activityService.save(activityRequestDto);
            return ResponseEntity.ok(activityDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible crear la actividad, verifique los datos!");
        }
    }

    @DeleteMapping("/delete/{id}")
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
}
