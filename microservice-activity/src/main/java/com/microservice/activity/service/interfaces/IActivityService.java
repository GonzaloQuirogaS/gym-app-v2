package com.microservice.activity.service.interfaces;

import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
import com.microservice.activity.presentation.http.response.ActivityByClientResponse;

import java.util.List;

public interface IActivityService {
    List<ActivityDto> findAll();
    ActivityDto save(ActivityRequestDto activityRequestDto);
    ActivityDto findById(Long id);
    void deleteById(Long id);
    ActivityDto update(Long id, ActivityRequestDto activityRequestDto);
    ActivityByClientResponse findClientsByIdActivity(Long id);
}
