package com.microservice.activity.service.servicesImpl;

import com.microservice.activity.client.Client;
import com.microservice.activity.persistence.entity.Activity;
import com.microservice.activity.persistence.repository.ActivityRepository;
import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
import com.microservice.activity.presentation.dto.client.ClientDto;
import com.microservice.activity.presentation.http.response.ActivityByClientResponse;
import com.microservice.activity.service.interfaces.IActivityService;
import com.microservice.activity.util.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {


    private final ActivityRepository activityRepository;
    private final Client client;
    private final Mapper mapper;

    @Override
    public List<ActivityDto> findAll() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(mapper::mapToActivityDto).collect(Collectors.toList());
    }

    @Override
    public ActivityDto save(ActivityRequestDto activityRequestDto) {
        Activity activity = new Activity();
        activity.setName(activityRequestDto.getName());
        activity.setPrice(activityRequestDto.getPrice());
        activityRepository.save(activity);
        return mapper.mapToActivityDto(activity);
    }

    @Override
    public ActivityDto findById(Long id) {
        Activity activity = activityRepository.findById(id).orElseThrow();
        return mapper.mapToActivityDto(activity);
    }

    @Override
    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public ActivityDto update(Long id, ActivityRequestDto activityRequestDto) {
        Activity activity = activityRepository.findById(id).orElseThrow();
        activity.setName(activityRequestDto.getName());
        activity.setPrice(activityRequestDto.getPrice());
        activityRepository.save(activity);
        return mapper.mapToActivityDto(activity);
    }

    @Override
    public ActivityByClientResponse findClientsByIdActivity(Long id) {

        Activity activity = activityRepository.findById(id).orElseThrow();

        List<ClientDto> clientDtoList = client.findAllClientsByActivity(id);

        return ActivityByClientResponse.builder()
                .id(activity.getId())
                .name(activity.getName())
                .price(activity.getPrice())
                .clientDtoList(clientDtoList)
                .build();
    }
}
