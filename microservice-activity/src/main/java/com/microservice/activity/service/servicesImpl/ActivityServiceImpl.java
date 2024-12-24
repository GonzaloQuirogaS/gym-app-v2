package com.microservice.activity.service.servicesImpl;

import com.microservice.activity.client.Client;
import com.microservice.activity.persistence.entity.Activity;
import com.microservice.activity.persistence.repository.ActivityRepository;
import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
import com.microservice.activity.presentation.dto.client.ClientDto;
import com.microservice.activity.presentation.exception.IdNotFoundException;
import com.microservice.activity.presentation.http.response.ActivityByClientResponse;
import com.microservice.activity.service.interfaces.IActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {


    private final ActivityRepository activityRepository;
    private final Client client;

    @Override
    public List<ActivityDto> findAll() {
        List<Activity> activities = activityRepository.findAll();
        List<ActivityDto> activityDtos = new ArrayList<>();

        for (Activity activity : activities) {
            ActivityDto activityDto = ActivityDto.builder()
                    .id(activity.getId())
                    .name(activity.getName())
                    .price(activity.getPrice())
                    .build();
            activityDtos.add(activityDto);
        }
        return activityDtos;
    }

    @Override
    public ActivityDto save(ActivityRequestDto activityRequestDto) {
        Activity activity = new Activity();
        activity.setName(activityRequestDto.getName());
        activity.setPrice(activityRequestDto.getPrice());
        activityRepository.save(activity);

        return ActivityDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .price(activity.getPrice())
                .build();
    }

    @Override
    public ActivityDto findById(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No existe actividad con ese ID"));

        return ActivityDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .price(activity.getPrice()).build();
    }

    @Override
    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public ActivityDto update(Long id, ActivityRequestDto activityRequestDto) {
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No existe actividad con ese ID"));
        activity.setName(activityRequestDto.getName());
        activity.setPrice(activityRequestDto.getPrice());
        activityRepository.save(activity);
        return ActivityDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .price(activity.getPrice())
                .build();
    }

    @Override
    public ActivityByClientResponse findClientsByIdActivity(Long id) {

        Activity activity = activityRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No existe actividad con ese ID"));
        List<ClientDto> clientDtoList = client.findAllClientsByActivity(id);
        return ActivityByClientResponse.builder()
                .id(activity.getId())
                .name(activity.getName())
                .price(activity.getPrice())
                .clientDtoList(clientDtoList)
                .build();
    }
}
