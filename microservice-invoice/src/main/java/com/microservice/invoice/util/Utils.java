package com.microservice.invoice.util;

import com.microservice.invoice.persistence.entity.Invoice;
import com.microservice.invoice.presentation.dto.activity.ActivityResponseDto;
import com.microservice.invoice.presentation.dto.client.ClientResponseDto;
import com.microservice.invoice.presentation.dto.invoice.InvoiceDto;
import org.springframework.stereotype.Service;

@Service
public class Utils {
    public InvoiceDto mapToDto(Invoice invoice, ClientResponseDto client, ActivityResponseDto activity) {
        return InvoiceDto.builder()
                .id(invoice.getId())
                .number(invoice.getNumber())
                .createdTime(invoice.getCreatedTime())
                .client(client)
                .activity(activity)
                .total(invoice.getTotal())
                .build();
    }
}
