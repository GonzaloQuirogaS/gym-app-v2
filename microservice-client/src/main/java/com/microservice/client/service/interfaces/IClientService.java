package com.microservice.client.service.interfaces;

import com.microservice.client.presentation.dto.client.ClientDto;
import com.microservice.client.presentation.dto.client.ClientRequestDto;

import java.util.List;

public interface IClientService {

    List<ClientDto> findAll();

    ClientDto save(ClientRequestDto clientRequestDto);

    ClientDto updateById(Long id, ClientRequestDto clientRequestDto);

    ClientDto findById(Long id);

    void deleteById(Long id);

    List<ClientDto> findByIdActivity(Long id);

    ClientDto setActivity(Long idClient, Long idActivity);

    ClientDto deleteActivity(Long idClient, Long idActivity);
}
