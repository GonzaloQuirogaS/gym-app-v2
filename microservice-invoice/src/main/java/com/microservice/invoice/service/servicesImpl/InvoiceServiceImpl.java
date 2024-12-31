package com.microservice.invoice.service.servicesImpl;

import com.microservice.invoice.client.FeignClientActivity;
import com.microservice.invoice.client.FeignClientClient;
import com.microservice.invoice.persistence.entity.Invoice;
import com.microservice.invoice.persistence.repository.InvoiceRepository;
import com.microservice.invoice.presentation.dto.activity.ActivityResponseDto;
import com.microservice.invoice.presentation.dto.client.ClientResponseDto;
import com.microservice.invoice.presentation.dto.invoice.InvoiceDto;
import com.microservice.invoice.presentation.exception.IdNotFoundException;
import com.microservice.invoice.service.interfaces.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.microservice.invoice.util.constant.ErrorConstants.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final FeignClientClient feignClient;

    private final FeignClientActivity feignActivity;

    @Override
    public List<InvoiceDto> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (Invoice invoice : invoices) {
            ClientResponseDto clientResponseDto = feignClient.findClientById(invoice.getIdClient());
            ActivityResponseDto activityResponseDto = feignActivity.findActivityById(invoice.getIdActivity());

            InvoiceDto invoiceDto = InvoiceDto.builder()
                    .id(invoice.getId())
                    .number(invoice.getNumber())
                    .createdTime(invoice.getCreatedTime())
                    .clientResponseDto(clientResponseDto)
                    .activityResponseDto(activityResponseDto)
                    .total(invoice.getTotal())
                    .build();

            invoiceDtos.add(invoiceDto);
        }
        return invoiceDtos;
    }


    @Override
    public InvoiceDto save(Long idClient) {
        ClientResponseDto clientResponseDto = feignClient.findClientById(idClient);

        if (clientResponseDto.getIdActivity() == null) {
            throw new IdNotFoundException(CLIENT_NOT_REGISTERED);
        }
        ActivityResponseDto activityResponseDto = feignActivity.findActivityById(clientResponseDto.getIdActivity());

        UUID randomUUID = UUID.randomUUID();

        Invoice invoice = new Invoice();
        invoice.setNumber(randomUUID.toString());
        invoice.setIdClient(idClient);
        invoice.setIdActivity(activityResponseDto.getId());
        invoice.setTotal(activityResponseDto.getPrice());
        invoice.setCreatedTime(LocalDateTime.now());

        invoiceRepository.save(invoice);
        return InvoiceDto.builder()
                .id(invoice.getId())
                .number(invoice.getNumber())
                .createdTime(invoice.getCreatedTime())
                .clientResponseDto(clientResponseDto)
                .activityResponseDto(activityResponseDto)
                .total(activityResponseDto.getPrice())
                .build();
    }

    @Override
    public InvoiceDto deleteById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new IdNotFoundException(INVOICE_NOT_FOUND));
        ActivityResponseDto activityResponseDto = feignActivity.findActivityById(invoice.getIdActivity());
        ClientResponseDto clientResponseDto = feignClient.findClientById(invoice.getIdClient());

        invoiceRepository.deleteById(id);
        return InvoiceDto.builder()
                .id(invoice.getId())
                .number(invoice.getNumber())
                .createdTime(invoice.getCreatedTime())
                .clientResponseDto(clientResponseDto)
                .activityResponseDto(activityResponseDto)
                .total(activityResponseDto.getPrice())
                .build();
    }

    @Override
    public InvoiceDto findById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new IdNotFoundException(INVOICE_NOT_FOUND));
        ClientResponseDto clientResponseDto = feignClient.findClientById(invoice.getIdClient());
        ActivityResponseDto activityResponseDto = feignActivity.findActivityById(invoice.getIdActivity());
        return InvoiceDto.builder()
                .id(invoice.getId())
                .number(invoice.getNumber())
                .createdTime(invoice.getCreatedTime())
                .clientResponseDto(clientResponseDto)
                .activityResponseDto(activityResponseDto)
                .total(activityResponseDto.getPrice())
                .build();
    }
}
