package com.microservice.invoice.service.interfaces;

import com.microservice.invoice.presentation.dto.InvoiceDto;
import com.microservice.invoice.presentation.dto.InvoiceRequestDto;

import java.util.List;

public interface IInvoiceService {

    List<InvoiceDto> findAll();

    InvoiceDto save(InvoiceRequestDto invoiceRequestDto);

    InvoiceDto deleteById(Long id);

    InvoiceDto findById(Long id);

}
