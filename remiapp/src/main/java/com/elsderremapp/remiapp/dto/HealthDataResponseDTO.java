package com.elsderremapp.remiapp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HealthDataResponseDTO {
    private Long id;
    private Double bloodPressureSystolic;
    private Double bloodPressureDiastolic;
    private Double heartRate;
    private Double weight;
    private Double temperature;
    private String notes;
    private LocalDateTime recordedAt;
}
