package com.microservice.client.presentation.controller;

import com.microservice.client.presentation.dto.client.ClientDto;
import com.microservice.client.presentation.dto.client.ClientRequestDto;
import com.microservice.client.service.interfaces.IClientService;
import com.microservice.client.util.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.microservice.client.util.constant.PathConstants.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V2_CLIENTS)
@CrossOrigin("*")
public class ClientController {

    private final Utils utils;

    private final IClientService clientService;

    @Tag(name = "GET", description = "Get methods")
    @Operation(summary = "Get all clients",
            description = "Get all clients")
    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @Tag(name = "POST", description = "Post Methods")
    @Operation(summary = "Save client",
            description = "Save client")
    @PostMapping(SAVE)
    public ResponseEntity<?> save(@Valid @RequestBody ClientRequestDto clientRequestDto, BindingResult result) {
        if (result.hasFieldErrors()) {
            return utils.validation(result);
        }
        ClientDto clientDto = clientService.save(clientRequestDto);
        return ResponseEntity.ok(clientDto);
    }

    @Tag(name = "GET")
    @Operation(summary = "Get client by ID",
            description = "Get client by ID")
    @GetMapping(GET_BY_ID)
    public ResponseEntity<ClientDto> findClientById(@PathVariable Long id) {
        ClientDto clientDto = clientService.findById(id);
        return ResponseEntity.ok(clientDto);
    }

    @Tag(name = "PUT")
    @Operation(summary = "Update client", description = "Update client")
    @PutMapping(UPDATE)
    private ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientRequestDto clientRequestDto) {
        ClientDto clientDto = clientService.updateById(id, clientRequestDto);
        return ResponseEntity.ok(clientDto);
    }

    @Tag(name = "DELETE")
    @Operation(summary = "Delete client by ID",
            description = "Delete client by ID")
    @DeleteMapping(DELETE)
    private ResponseEntity<?> delete(@PathVariable Long id) {
        ClientDto clientDto = clientService.findById(id);
        clientService.deleteById(id);
        return ResponseEntity.ok("Eliminado con exito!");
    }

    @Tag(name = "GET")
    @Operation(summary = "Get clients by activity ID",
            description = "Get clients by activity ID")
    @GetMapping(SEARCH_BY_ACTIVITY_ID)
    public ResponseEntity<List<ClientDto>> findByIdActivity(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findByIdActivity(id));
    }

    @Tag(name = "POST")
    @Operation(summary = "Set activity to clients by ID",
            description = "Set activity to clients by ID")
    @PostMapping(SET_ACTIVITY_TO_CLIENT_BY_ID)
    public ResponseEntity<ClientDto> setActivity(@PathVariable Long idClient, @PathVariable Long idActivity) {
        return ResponseEntity.ok(clientService.setActivity(idClient, idActivity));
    }

    @Tag(name = "DELETE")
    @Operation(summary = "Delete clients activity",
            description = "Delete clients activity")
    @DeleteMapping(DELETE_CLIENT_ACTIVITY)
    public ResponseEntity<ClientDto> deleteActivity(@PathVariable Long idClient, @PathVariable Long idActivity) {
        return ResponseEntity.ok(clientService.deleteActivity(idClient, idActivity));
    }
}


