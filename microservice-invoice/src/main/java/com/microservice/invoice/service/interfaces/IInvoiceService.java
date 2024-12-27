package com.microservice.invoice.service.interfaces;

import com.microservice.invoice.presentation.dto.invoice.InvoiceDto;

import java.util.List;

public interface IInvoiceService {

    List<InvoiceDto> findAll();

    InvoiceDto save(Long idClient);

    InvoiceDto deleteById(Long id);

    InvoiceDto findById(Long id);

}
