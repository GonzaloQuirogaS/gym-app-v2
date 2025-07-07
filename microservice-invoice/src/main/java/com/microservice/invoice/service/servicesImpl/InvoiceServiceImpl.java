package com.microservice.invoice.service.servicesImpl;

import com.microservice.invoice.persistence.entity.Invoice;
import com.microservice.invoice.persistence.repository.InvoiceRepository;
import com.microservice.invoice.presentation.dto.activity.ActivityResponseDto;
import com.microservice.invoice.presentation.dto.client.ClientResponseDto;
import com.microservice.invoice.presentation.dto.invoice.InvoiceDto;
import com.microservice.invoice.presentation.exception.IdNotFoundException;
import com.microservice.invoice.service.client.FeignClientServiceActivity;
import com.microservice.invoice.service.client.FeignClientServiceClient;
import com.microservice.invoice.service.interfaces.IInvoiceService;
import com.microservice.invoice.util.Utils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.microservice.invoice.util.constant.ErrorConstants.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final FeignClientServiceClient feignClient;
    private final FeignClientServiceActivity feignActivity;
    private final Utils utils;

    @Override
    public List<InvoiceDto> findAll() {
        return invoiceRepository.findAll().stream()
                .map(invoice -> {
                    ClientResponseDto client = feignClient.findClientById(invoice.getIdClient());
                    ActivityResponseDto activity = feignActivity.findActivityById(invoice.getIdActivity());
                    return utils.mapToDto(invoice, client, activity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDto save(Long idClient) {
        ClientResponseDto client;
        try {
            client = feignClient.findClientById(idClient);
        } catch (FeignException.NotFound e) {
            throw new IdNotFoundException(CLIENT_NOT_REGISTERED);
        }

        ActivityResponseDto activity = feignActivity.findActivityById(client.getIdActivity());
        UUID randomUUID = UUID.randomUUID();

        Invoice invoice = new Invoice();
        invoice.setNumber(randomUUID.toString());
        invoice.setIdClient(idClient);
        invoice.setIdActivity(activity.getId());
        invoice.setTotal(activity.getPrice());
        invoice.setCreatedTime(LocalDateTime.now());

        invoiceRepository.save(invoice);
        return utils.mapToDto(invoice, client, activity);
    }

    @Override
    public InvoiceDto deleteById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new IdNotFoundException(INVOICE_NOT_FOUND));
        ActivityResponseDto activity = feignActivity.findActivityById(invoice.getIdActivity());
        ClientResponseDto client = feignClient.findClientById(invoice.getIdClient());
        invoiceRepository.deleteById(id);
        return utils.mapToDto(invoice, client, activity);
    }

    @Override
    public InvoiceDto findById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(INVOICE_NOT_FOUND));
        ClientResponseDto client = feignClient.findClientById(invoice.getIdClient());
        ActivityResponseDto activity = feignActivity.findActivityById(invoice.getIdActivity());
        return utils.mapToDto(invoice, client, activity);
    }
}
