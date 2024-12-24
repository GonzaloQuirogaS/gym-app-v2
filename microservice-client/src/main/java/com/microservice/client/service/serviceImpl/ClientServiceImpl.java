package com.microservice.client.service.serviceImpl;

import com.microservice.client.client.FeingClient;
import com.microservice.client.persistence.entity.Client;
import com.microservice.client.persistence.repository.ClientRepository;
import com.microservice.client.presentation.dto.client.ClientDto;
import com.microservice.client.presentation.dto.client.ClientRequestDto;
import com.microservice.client.presentation.dto.activity.ActivityResponseDto;
import com.microservice.client.presentation.exception.ActivityNotFoundException;
import com.microservice.client.presentation.exception.IdNotFoundException;
import com.microservice.client.service.interfaces.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final FeingClient feingClient;

    @Override
    public List<ClientDto> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDto> clientDtos = new ArrayList<>();

        for (Client client : clients) {
            ClientDto clientDto = ClientDto.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .surname(client.getSurname())
                    .age(client.getAge())
                    .email(client.getEmail())
                    .phone(client.getPhone())
                    .registerDate(client.getRegisterDate())
                    .activityRegisterDate(client.getActivityRegisterDate())
                    .activityExpireDate(client.getActivityExpireDate())
                    .build();
            clientDtos.add(clientDto);
        }
        return clientDtos;
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
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .age(client.getAge())
                .email(client.getEmail())
                .phone(client.getPhone())
                .registerDate(client.getRegisterDate())
                .activityRegisterDate(client.getActivityRegisterDate())
                .activityExpireDate(client.getActivityExpireDate())
                .build();
    }

    @Override
    public ClientDto updateById(Long id, ClientRequestDto clientRequestDto) {

        Client client = clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No existe cliente con ese ID"));

        if (client.getActivityId() == null){
            client.setActivityId(null);
        }

        client.setName(clientRequestDto.getName());
        client.setSurname(clientRequestDto.getSurname());
        client.setAge(clientRequestDto.getAge());
        client.setPhone(clientRequestDto.getPhone());
        client.setEmail(clientRequestDto.getEmail());
        clientRepository.save(client);

        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .age(client.getAge())
                .email(client.getEmail())
                .phone(client.getPhone())
                .registerDate(client.getRegisterDate())
                .activityRegisterDate(client.getActivityRegisterDate())
                .activityExpireDate(client.getActivityExpireDate())
                .build();
    }

    @Override
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No existe cliente con ese ID"));
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .age(client.getAge())
                .email(client.getEmail())
                .phone(client.getPhone())
                .registerDate(client.getRegisterDate())
                .activityRegisterDate(client.getActivityRegisterDate())
                .activityExpireDate(client.getActivityExpireDate())
                .build();
    }

    @Override
    public void deleteById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No existe cliente con ese ID"));
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDto> findByIdActivity(Long id) {
        List<Client> clients = clientRepository.findAllByActivityId(id);
        List<ClientDto> clientDtos = new ArrayList<>();
        for (Client client : clients) {
            ClientDto clientDto = ClientDto.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .surname(client.getSurname())
                    .age(client.getAge())
                    .email(client.getEmail())
                    .phone(client.getPhone())
                    .registerDate(client.getRegisterDate())
                    .activityRegisterDate(client.getActivityRegisterDate())
                    .activityExpireDate(client.getActivityExpireDate())
                    .build();
            clientDtos.add(clientDto);
        }

        if (clientDtos.isEmpty()) {
            throw new ActivityNotFoundException("Actividad no encontrada");
        }

        return clientDtos;
    }

    @Override
    public ClientDto setActivity(Long idClient, Long idActivity) {
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new IdNotFoundException("No existe cliente con ese ID"));
        ActivityResponseDto activityResponseDto = feingClient.findActivityById(idActivity);
        if (activityResponseDto == null) {
            throw new ActivityNotFoundException("Actividad no encontrada");
        }
        client.setActivityRegisterDate(LocalDateTime.now());
        client.setActivityExpireDate(LocalDateTime.now().plusMonths(1));
        client.setActivityId(idActivity);
        clientRepository.save(client);

        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .age(client.getAge())
                .email(client.getEmail())
                .phone(client.getPhone())
                .registerDate(client.getRegisterDate())
                .activityRegisterDate(client.getActivityRegisterDate())
                .activityExpireDate(client.getActivityExpireDate())
                .build();
    }

    @Override
    public ClientDto deleteActivity(Long idClient, Long idActivity) {
        ActivityResponseDto activityResponseDto = feingClient.findActivityById(idActivity);
        if (activityResponseDto == null) {
            throw new ActivityNotFoundException("Actividad no encontrada");
        }
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new IdNotFoundException("No existe cliente con ese ID"));
        if (client.getActivityId() == null) {
            throw new ActivityNotFoundException("El cliente no esta anotado a la actividad");
        }
        client.setActivityExpireDate(null);
        client.setActivityRegisterDate(null);
        client.setActivityId(null);
        clientRepository.save(client);

        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .surname(client.getSurname())
                .age(client.getAge())
                .email(client.getEmail())
                .phone(client.getPhone())
                .registerDate(client.getRegisterDate())
                .activityRegisterDate(client.getActivityRegisterDate())
                .activityExpireDate(client.getActivityExpireDate())
                .build();
    }
}
