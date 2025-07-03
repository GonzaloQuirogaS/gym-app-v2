package com.microservice.invoice.presentation.controller;

import com.microservice.invoice.presentation.dto.invoice.InvoiceDto;
import com.microservice.invoice.service.interfaces.IInvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.microservice.invoice.util.constant.PathConstants.*;

import java.util.List;

@RestController
@RequestMapping(API_V2_INVOICES)
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
    @GetMapping(GET_BY_ID)
    public ResponseEntity<InvoiceDto> findInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.findById(id));
    }

    @Tag(name = "POST", description = "Post Methods")
    @Operation(summary = "Save invoice",
            description = "Save invoice")
    @PostMapping(SAVE)
    public ResponseEntity<InvoiceDto> save(@RequestParam Long idClient) {
        return ResponseEntity.ok(invoiceService.save(idClient));
    }

    @Tag(name = "DELETE")
    @Operation(summary = "Delete invoice by ID",
            description = "Delete invoice by ID")
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        invoiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
