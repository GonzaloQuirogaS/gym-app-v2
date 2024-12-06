package com.microservice.activity.util.mapper;

import com.microservice.activity.persistence.entity.Activity;
import com.microservice.activity.presentation.dto.activity.ActivityDto;

public class Mapper {

    public ActivityDto mapToActivityDto(Activity activity) {
        if (activity == null) return null;

        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setName(activity.getName());
        activityDto.setPrice(activity.getPrice());

        return activityDto;
    }
}
