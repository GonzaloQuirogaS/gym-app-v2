package com.microservice.client.presentation.controller;

import com.microservice.client.presentation.dto.ClientDto;
import com.microservice.client.presentation.dto.ClientRequestDto;
import com.microservice.client.service.interfaces.IClientService;
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

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

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

    @GetMapping("/search-by-activity/{id}")
    public ResponseEntity<?> findByIdActivity(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findByIdActivity(id));
    }

    @GetMapping("/set-activity/client/{idClient}/activity/{idActivity}")
    public ResponseEntity<ClientDto> setActivity(@PathVariable Long idClient, @PathVariable Long idActivity) {
        return ResponseEntity.ok(clientService.setActivity(idClient, idActivity));
    }
}


