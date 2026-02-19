package com.elsderremapp.remiapp.service;

import com.elsderremapp.remiapp.dto.HealthDataRequestDTO;
import com.elsderremapp.remiapp.dto.HealthDataResponseDTO;
import com.elsderremapp.remiapp.exception.BadRequestException;
import com.elsderremapp.remiapp.exception.ResourceNotFoundException;
import com.elsderremapp.remiapp.model.HealthData;
import com.elsderremapp.remiapp.model.Role;
import com.elsderremapp.remiapp.model.User;
import com.elsderremapp.remiapp.repository.HealthDataRepository;
import com.elsderremapp.remiapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
    @RequiredArgsConstructor
    public class HealthDataService {
    private final HealthDataRepository healthDataRepository;
    private final UserRepository userRepository;

    public HealthDataResponseDTO createHealthData(Long elderId,
                                                  HealthDataRequestDTO dto) {

        User elder = userRepository.findById(elderId)
                .orElseThrow(() -> new ResourceNotFoundException("Elder not found"));

        if (elder.getRole() != Role.ELDER) {
            throw new BadRequestException("User is not an elder");
        }

        HealthData healthData = HealthData.builder()
                .bloodPressureSystolic(dto.getBloodPressureSystolic())
                .bloodPressureDiastolic(dto.getBloodPressureDiastolic())
                .heartRate(dto.getHeartRate())
                .weight(dto.getWeight())
                .temperature(dto.getTemperature())
                .notes(dto.getNotes())
                .elder(elder)
                .createdAt(LocalDateTime.now())
                .recordedAt(LocalDateTime.now())
                .build();

        HealthData saved = healthDataRepository.save(healthData);

        return mapToResponse(saved);
    }

    public List<HealthDataResponseDTO> getHealthHistory(Long elderId) {

        if (!userRepository.existsById(elderId)) {
            throw new ResourceNotFoundException("Elder not found");
        }

        return healthDataRepository
                .findByElderIdOrderByRecordedAtDesc(elderId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private HealthDataResponseDTO mapToResponse(HealthData data) {
        return HealthDataResponseDTO.builder()
                .id(data.getId())
                .bloodPressureSystolic(data.getBloodPressureSystolic())
                .bloodPressureDiastolic(data.getBloodPressureDiastolic())
                .heartRate(data.getHeartRate())
                .weight(data.getWeight())
                .temperature(data.getTemperature())
                .notes(data.getNotes())
                .recordedAt(data.getRecordedAt())
                .build();
    }

    }

