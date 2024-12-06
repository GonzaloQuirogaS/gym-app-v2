package com.microservice.client.service.interfaces;

import com.microservice.client.presentation.dto.ClientDto;
import com.microservice.client.presentation.dto.ClientRequestDto;

import java.util.List;

public interface IClientService {

    List<ClientDto> findAll();

    ClientDto save(ClientRequestDto clientRequestDto);

    ClientDto updateById(Long id, ClientRequestDto clientRequestDto);

    ClientDto findById(Long id);

    void deleteById(Long id);

    List<ClientDto> findByIdActivity(Long id);

}
