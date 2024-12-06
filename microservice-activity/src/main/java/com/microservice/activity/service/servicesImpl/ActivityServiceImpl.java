package com.microservice.activity.service.servicesImpl;

import com.microservice.activity.persistence.entity.Activity;
import com.microservice.activity.persistence.repository.ActivityRepository;
import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
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
    private final Mapper mapper;

    @Override
    public List<ActivityDto> findAll() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(mapper::mapToActivityDto).collect(Collectors.toList());
    }

    @Override
    public ActivityDto save(ActivityRequestDto activityRequestDto) {
        Activity activity = new Activity();
        activity.setName(activity.getName());
        activity.setPrice(activity.getPrice());
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
        return mapper.mapToActivityDto(activity);
    }
}
