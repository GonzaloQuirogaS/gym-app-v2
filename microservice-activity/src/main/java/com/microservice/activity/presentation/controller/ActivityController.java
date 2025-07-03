package com.microservice.activity.presentation.controller;

import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
import com.microservice.activity.presentation.http.response.ActivityByClientResponse;
import com.microservice.activity.service.interfaces.IActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.microservice.activity.util.constant.PathConstants.*;

import java.util.List;

@RestController
@RequestMapping(API_V2_ACTIVITIES)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActivityController {
    private final IActivityService activityService;

    @Tag(name = "GET", description = "Get methods")
    @Operation(summary = "Get all activities",
            description = "Get all activities")
    @GetMapping
    public ResponseEntity<List<ActivityDto>> findAll() {
        return ResponseEntity.ok(activityService.findAll());
    }

    @GetMapping(GET_BY_ID)
    @Tag(name = "GET")
    @Operation(summary = "Get activity by ID",
            description = "Get activity by ID")
    public ResponseEntity<ActivityDto> findDisciplineById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.findById(id));
    }

    @PutMapping(UPDATE)
    @Tag(name = "PUT")
    @Operation(summary = "Update activity", description = "Update activity")
    public ResponseEntity<ActivityDto> update(@PathVariable Long id, @RequestBody ActivityRequestDto activityRequestDto) {
        return ResponseEntity.ok(activityService.update(id, activityRequestDto));
    }

    @PostMapping(SAVE)
    @Tag(name = "POST", description = "Post Methods")
    @Operation(summary = "Save activity",
            description = "Save activity")
    public ResponseEntity<ActivityDto> save(@Valid @RequestBody ActivityRequestDto activityRequestDto) {
        ActivityDto saved = activityService.save(activityRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping(DELETE)
    @Tag(name = "DELETE")
    @Operation(summary = "Delete activity by ID",
            description = "Delete activity by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(SEARCH_CLIENT_BY_ID)
    @Tag(name = "GET")
    @Operation(summary = "Search clients by activity ID",
            description = "Search clients by activity ID")
    public ResponseEntity<ActivityByClientResponse> findClientsByIdActivity(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.findClientsByIdActivity(id));
    }
}
