package com.microservice.invoice.service.servicesImpl;

import com.microservice.invoice.persistence.entity.Invoice;
import com.microservice.invoice.persistence.repository.InvoiceRepository;
import com.microservice.invoice.presentation.dto.InvoiceDto;
import com.microservice.invoice.presentation.dto.InvoiceRequestDto;
import com.microservice.invoice.service.interfaces.IInvoiceService;
import com.microservice.invoice.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final Mapper mapper;

    @Override
    public List<InvoiceDto> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream().map(mapper::mapToInvoiceDto).collect(Collectors.toList());
    }

    @Override
    public InvoiceDto save(InvoiceRequestDto invoiceRequestDto) {

        UUID randomUUID = UUID.randomUUID();

        Invoice invoice = new Invoice();
        invoice.setNumber(randomUUID.toString());
        invoice.setIdClient(invoiceRequestDto.getIdClient());
        invoice.setIdActivity(invoiceRequestDto.getIdActivity());
        invoice.setTotal(100);
        invoice.setCreatedTime(LocalDateTime.now());

        invoiceRepository.save(invoice);
        return mapper.mapToInvoiceDto(invoice);
    }

    @Override
    public InvoiceDto deleteById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        invoiceRepository.deleteById(id);
        return mapper.mapToInvoiceDto(invoice);
    }

    @Override
    public InvoiceDto findById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        return mapper.mapToInvoiceDto(invoice);
    }
}
