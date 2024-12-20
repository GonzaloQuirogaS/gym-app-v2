package com.microservice.invoice.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDto {
    private Long id;
    private String number;
    private Long idActivity;
    private LocalDateTime createdTime;
    private Long idClient;
    private Integer total;
}
