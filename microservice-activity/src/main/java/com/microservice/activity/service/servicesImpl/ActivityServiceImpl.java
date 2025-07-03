package com.microservice.activity.service.servicesImpl;

import com.microservice.activity.persistence.entity.Activity;
import com.microservice.activity.persistence.repository.ActivityRepository;
import com.microservice.activity.presentation.dto.activity.ActivityDto;
import com.microservice.activity.presentation.dto.activity.ActivityRequestDto;
import com.microservice.activity.presentation.dto.client.ClientDto;
import com.microservice.activity.presentation.exception.IdNotFoundException;
import com.microservice.activity.presentation.http.response.ActivityByClientResponse;
import com.microservice.activity.service.client.FeignClientService;
import com.microservice.activity.service.interfaces.IActivityService;
import com.microservice.activity.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.microservice.activity.util.constant.ErrorConstants.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {
    private final ActivityRepository activityRepository;
    private final FeignClientService feignClient;
    private final Utils utils;

    @Override
    public List<ActivityDto> findAll() {
            return activityRepository.findAll().stream()
                    .map(utils::mapToDto)
                    .toList();
    }

    @Override
    public ActivityDto save(ActivityRequestDto activityRequestDto) {
        Activity activity = new Activity();
        activity.setName(activityRequestDto.getName());
        activity.setPrice(activityRequestDto.getPrice());
        activityRepository.save(activity);
        return utils.mapToDto(activity);
    }

    @Override
    public ActivityDto findById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ACTIVITY_NOT_FOUND));
        return utils.mapToDto(activity);
    }

    @Override
    public void deleteById(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ACTIVITY_NOT_FOUND));
        activityRepository.delete(activity);
    }

    @Override
    public ActivityDto update(Long id, ActivityRequestDto activityRequestDto) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ACTIVITY_NOT_FOUND));
        activity.setName(activityRequestDto.getName());
        activity.setPrice(activityRequestDto.getPrice());
        activityRepository.save(activity);
        return utils.mapToDto(activity);
    }

    @Override
    public ActivityByClientResponse findClientsByIdActivity(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ACTIVITY_NOT_FOUND));
        List<ClientDto> clientDtoList = feignClient.findAllClientsByActivity(id);
        return ActivityByClientResponse.builder()
                .id(activity.getId())
                .name(activity.getName())
                .price(activity.getPrice())
                .clients(clientDtoList)
                .build();
    }
}
