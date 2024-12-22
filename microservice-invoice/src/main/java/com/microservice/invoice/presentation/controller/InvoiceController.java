package com.microservice.invoice.presentation.controller;

import com.microservice.invoice.presentation.dto.InvoiceDto;
import com.microservice.invoice.presentation.dto.InvoiceRequestDto;
import com.microservice.invoice.service.interfaces.IInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/invoices")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InvoiceController {

    private final IInvoiceService invoiceService;

    @Tag(name = "GET", description = "Get methods")
    @Operation(summary = "Get all invoices",
            description = "Get all invoices")
    @GetMapping
    public ResponseEntity<List<InvoiceDto>> findAll() {
        List<InvoiceDto> invoices = invoiceService.findAll();
        return ResponseEntity.ok(invoices);
    }

    @Tag(name = "GET")
    @Operation(summary = "Get invoice by ID",
            description = "Get invoice by ID")
    @GetMapping("/{id}")
    ResponseEntity<?> findInvoiceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(invoiceService.findById(id));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("El id dado no corresponde a ninguna factura!");
        }
    }

    @Tag(name = "POST", description = "Post Methods")
    @Operation(summary = "Save invoice",
            description = "Save invoice")
    @PostMapping("/save")
    private ResponseEntity<?> save(@RequestBody InvoiceRequestDto invoiceRequestDto) {
        try {
            return ResponseEntity.ok(invoiceService.save(invoiceRequestDto));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible crear la factura, verifique los datos!");
        }
    }

    @Tag(name = "DELETE")
    @Operation(summary = "Delete invoice by ID",
            description = "Delete invoice by ID")
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            InvoiceDto invoiceDto = invoiceService.findById(id);
            invoiceService.deleteById(id);
            return ResponseEntity.ok(invoiceDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No fue posible eliminar la factura, verifique los datos!");
        }
    }

}
