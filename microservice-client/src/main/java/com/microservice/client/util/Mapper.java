package com.microservice.client.util;

import com.microservice.client.persistence.entity.Client;
import com.microservice.client.presentation.dto.ClientDto;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public ClientDto mapToClientDto(Client client) {
        if (client == null) return null;
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setSurname(client.getSurname());
        clientDto.setAge(client.getAge());
        clientDto.setPhone(client.getPhone());
        clientDto.setRegisterDate(client.getRegisterDate());
        clientDto.setEmail(client.getEmail());
        return clientDto;
    }}