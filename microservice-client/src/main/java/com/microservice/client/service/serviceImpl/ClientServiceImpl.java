package com.microservice.client.service.serviceImpl;

import com.microservice.client.persistence.entity.Client;
import com.microservice.client.persistence.repository.ClientRepository;
import com.microservice.client.presentation.dto.ClientDto;
import com.microservice.client.presentation.dto.ClientRequestDto;
import com.microservice.client.service.interfaces.IClientService;
import com.microservice.client.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    private final Mapper mapper;

    @Override
    public List<ClientDto> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(mapper::mapToClientDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto save(ClientRequestDto clientRequestDto) {
        Client client = new Client();
        client.setName(clientRequestDto.getName());
        client.setSurname(clientRequestDto.getSurname());
        client.setPhone(clientRequestDto.getPhone());
        client.setEmail(clientRequestDto.getEmail());

        clientRepository.save(client);
        return mapper.mapToClientDto(client);
    }

    @Override
    public ClientDto updateById(Long id, ClientRequestDto clientRequestDto) {

        Client client = clientRepository.findById(id).orElseThrow();
        client.setName(clientRequestDto.getName());
        client.setSurname(clientRequestDto.getSurname());
        client.setAge(clientRequestDto.getAge());
        client.setPhone(clientRequestDto.getPhone());
        client.setEmail(clientRequestDto.getEmail());
        client.setActivityId(null);
        clientRepository.save(client);

        return mapper.mapToClientDto(client);
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        return mapper.mapToClientDto(client);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDto> findByIdActivity(Long id) {
        List<Client> clients = clientRepository.findAllByActivityId(id);
        return clients.stream().map(mapper::mapToClientDto).collect(Collectors.toList());
    }
}
