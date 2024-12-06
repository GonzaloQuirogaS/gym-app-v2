package com.microservice.activity.service.interfaces;

import com.microservice.activity.presentation.dto.ActivityDto;
import com.microservice.activity.presentation.dto.ActivityRequestDto;

import java.util.List;

public interface IActivityService {

    List<ActivityDto> findAll();

    ActivityDto save(ActivityRequestDto activityRequestDto);

    ActivityDto findById(Long id);

    void deleteById(Long id);

    ActivityDto update(Long id, ActivityRequestDto activityRequestDto);

}
