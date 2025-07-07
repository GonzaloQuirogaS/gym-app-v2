package com.microservice.client.service.serviceImpl;

import com.microservice.client.service.client.FeignClientService;
import com.microservice.client.persistence.entity.Client;
import com.microservice.client.persistence.repository.ClientRepository;
import com.microservice.client.presentation.dto.client.ClientDto;
import com.microservice.client.presentation.dto.client.ClientRequestDto;
import com.microservice.client.presentation.dto.activity.ActivityResponseDto;
import com.microservice.client.presentation.exception.ActivityNotFoundException;
import com.microservice.client.presentation.exception.IdNotFoundException;
import com.microservice.client.service.interfaces.IClientService;
import com.microservice.client.util.Utils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.microservice.client.util.constant.ErrorConstants.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {
    private final ClientRepository clientRepository;
    private final FeignClientService feignClient;
    private final Utils utils;

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(utils::mapToDto)
                .toList();
    }

    @Override
    public ClientDto save(ClientRequestDto clientRequestDto) {
        Client client = new Client();
        client.setName(clientRequestDto.getName());
        client.setSurname(clientRequestDto.getSurname());
        client.setAge(clientRequestDto.getAge());
        client.setPhone(clientRequestDto.getPhone());
        client.setEmail(clientRequestDto.getEmail());
        clientRepository.save(client);
        return utils.mapToDto(client);
    }

    @Override
    public ClientDto updateById(Long id, ClientRequestDto clientRequestDto) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException(CLIENT_NOT_FOUND));
        client.setName(clientRequestDto.getName());
        client.setSurname(clientRequestDto.getSurname());
        client.setAge(clientRequestDto.getAge());
        client.setPhone(clientRequestDto.getPhone());
        client.setEmail(clientRequestDto.getEmail());
        clientRepository.save(client);
        return utils.mapToDto(client);
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException(CLIENT_NOT_FOUND));
        return utils.mapToDto(client);
    }

    @Override
    public void deleteById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(CLIENT_NOT_FOUND));
        clientRepository.delete(client);
    }

    @Override
    public List<ClientDto> findByIdActivity(Long id) {
        List<Client> clients = clientRepository.findAllByActivityId(id);
        List<ClientDto> clientDtos = clients.stream()
                .map(utils::mapToDto)
                .toList();
        if (clientDtos.isEmpty()) {
            throw new ActivityNotFoundException(ACTIVITY_NOT_FOUND);
        }
        return clientDtos;
    }

    @Override
    public ClientDto setActivity(Long idClient, Long idActivity) {
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new IdNotFoundException(CLIENT_NOT_FOUND));
        try {
            ActivityResponseDto  activityResponseDto = feignClient.findActivityById(idActivity);
        } catch (FeignException.NotFound e) {
            throw new ActivityNotFoundException(ACTIVITY_NOT_FOUND);
        }
        client.setActivityRegisterDate(LocalDateTime.now());
        client.setActivityExpireDate(LocalDateTime.now().plusMonths(1));
        client.setActivityId(idActivity);
        clientRepository.save(client);
        return utils.mapToDto(client);
    }

    @Override
    public ClientDto deleteActivity(Long idClient, Long idActivity) {
        try {
            ActivityResponseDto  activityResponseDto = feignClient.findActivityById(idActivity);
        } catch (FeignException.NotFound e) {
            throw new ActivityNotFoundException(ACTIVITY_NOT_FOUND);
        }
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new IdNotFoundException(CLIENT_NOT_FOUND));
        if (client.getActivityId() == null) {
            throw new ActivityNotFoundException(CLIENT_ACTIVITY_NOT_FOUND);
        }
        client.setActivityExpireDate(null);
        client.setActivityRegisterDate(null);
        client.setActivityId(null);
        clientRepository.save(client);
        return utils.mapToDto(client);
    }
}
