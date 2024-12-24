package com.microservice.activity.presentation.controller;

import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
import com.microservice.activity.service.interfaces.IActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    ResponseEntity<ActivityDto> findDisciplineById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.findById(id));
    }

    @PutMapping("/update/{id}")
    @Tag(name = "PUT")
    @Operation(summary = "Update activity", description = "Update activity")
    private ResponseEntity<ActivityDto> update(@PathVariable Long id, @RequestBody ActivityRequestDto activityRequestDto) {
        return ResponseEntity.ok(activityService.update(id, activityRequestDto));
    }

    @PostMapping("/save")
    @Tag(name = "POST", description = "Post Methods")
    @Operation(summary = "Save activity",
            description = "Save activity")
    private ResponseEntity<?> save(@RequestBody ActivityRequestDto activityRequestDto) {
        return ResponseEntity.ok(activityService.save(activityRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    @Tag(name = "DELETE")
    @Operation(summary = "Delete activity by ID",
            description = "Delete activity by ID")
    private ResponseEntity<ActivityDto> delete(@PathVariable Long id) {
        ActivityDto activityDto = activityService.findById(id);
        activityService.deleteById(id);
        return ResponseEntity.ok(activityDto);
    }

    @GetMapping("/search-client/{id}")
    @Tag(name = "GET")
    @Operation(summary = "Search clients by activity ID",
            description = "Search clients by activity ID")
    public ResponseEntity<?> findClientsByIdActivity(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.findClientsByIdActivity(id));
    }
}
