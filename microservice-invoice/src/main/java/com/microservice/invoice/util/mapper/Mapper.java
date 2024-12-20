package com.microservice.invoice.util.mapper;

import com.microservice.invoice.persistence.entity.Invoice;
import com.microservice.invoice.presentation.dto.InvoiceDto;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public InvoiceDto mapToInvoiceDto(Invoice invoice) {
        if (invoice == null) return null;

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoice.getId());
        invoiceDto.setNumber(invoice.getNumber());
        invoiceDto.setCreatedTime(invoice.getCreatedTime());
        invoiceDto.setIdClient(invoice.getIdClient());
        invoiceDto.setIdActivity(invoice.getIdActivity());
        invoiceDto.setTotal(invoice.getTotal());

        return invoiceDto;
    }
}