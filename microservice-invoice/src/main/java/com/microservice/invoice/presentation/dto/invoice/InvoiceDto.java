package com.microservice.invoice.presentation.dto.invoice;

import com.microservice.invoice.presentation.dto.activity.ActivityResponseDto;
import com.microservice.invoice.presentation.dto.client.ClientResponseDto;
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
    private LocalDateTime createdTime;
    private ActivityResponseDto activityResponseDto;
    private ClientResponseDto clientResponseDto;
    private Integer total;
}
