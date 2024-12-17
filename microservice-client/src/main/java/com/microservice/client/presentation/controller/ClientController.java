package com.microservice.client.presentation.controller;

import com.microservice.client.presentation.dto.ClientDto;
import com.microservice.client.presentation.dto.ClientRequestDto;
import com.microservice.client.service.interfaces.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/clients")
@CrossOrigin("*")
public class ClientController {

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
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ClientRequestDto clientRequestDto) {
        try {

            ClientDto clientDto = clientService.save(clientRequestDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(clientDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    @Tag(name = "GET")
    @Operation(summary = "Get client by ID",
            description = "Get client by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> findClientById(@PathVariable Long id) {
        try {
            ClientDto clientDto = clientService.findById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clientDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No existe cliente con ese id!");
        }
    }

    @Tag(name = "PUT")
    @Operation(summary = "Update client", description = "Update client")
    @PutMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClientRequestDto clientRequestDto) {
        try {
            ClientDto clientDto = clientService.updateById(id, clientRequestDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clientDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el ciente!");
        }
    }

    @Tag(name = "DELETE")
    @Operation(summary = "Delete client by ID",
            description = "Delete client by ID")
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible eliminar al cliente, asegurese de eliminar sus disciplinas y facturas y de que el cliente exista primero!");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Eliminado con exito!");
    }

    @Tag(name = "GET")
    @Operation(summary = "Get clients by activity ID",
            description = "Get clients by activity ID")
    @GetMapping("/search-by-activity/{id}")
    public ResponseEntity<?> findByIdActivity(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clientService.findByIdActivity(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El id dado no corresponde a ninguna actividad!");
        }
    }

    @Tag(name = "GET")
    @Operation(summary = "Set activity to clients by ID",
            description = "Set activity to clients by ID")
    @GetMapping("/set-activity/client/{idClient}/activity/{idActivity}")
    public ResponseEntity<?> setActivity(@PathVariable Long idClient, @PathVariable Long idActivity) {
        try {
            return ResponseEntity.ok(clientService.setActivity(idClient, idActivity));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Los ID dados no corresponde a ninguna actividad o cliente");
        }
    }

    @Tag(name = "DELETE")
    @Operation(summary = "Delete clients activity",
            description = "Delete clients activity")
    @DeleteMapping("/delete-activity/client/{idClient}/activity/{idActivity}")
    public ResponseEntity<?> deleteActivity(@PathVariable Long idClient, @PathVariable Long idActivity) {
        try {
            return ResponseEntity.ok(clientService.deleteActivity(idClient, idActivity));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Los ID dados no corresponde a ninguna actividad o cliente");
        }
    }
}


