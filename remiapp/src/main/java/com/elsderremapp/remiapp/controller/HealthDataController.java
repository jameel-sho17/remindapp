package com.elsderremapp.remiapp.controller;

import com.elsderremapp.remiapp.dto.HealthDataRequestDTO;
import com.elsderremapp.remiapp.dto.HealthDataResponseDTO;
import com.elsderremapp.remiapp.model.HealthData;
import com.elsderremapp.remiapp.service.HealthDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health-data")
@RequiredArgsConstructor
public class HealthDataController {
    private final HealthDataService healthDataService;

    @PostMapping("/{elderId}")
    public HealthDataResponseDTO createHealthData(
            @PathVariable Long elderId,
            @RequestBody HealthDataRequestDTO request) {

        return healthDataService.createHealthData(elderId, request);
    }

    @GetMapping("/{elderId}")
    public List<HealthDataResponseDTO> getHealthHistory(
            @PathVariable Long elderId) {

        return healthDataService.getHealthHistory(elderId);
    }
}
