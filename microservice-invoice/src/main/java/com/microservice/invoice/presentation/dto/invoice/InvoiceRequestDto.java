package com.microservice.invoice.presentation.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceRequestDto {
    private Long idActivity;
    private Long idClient;
}
