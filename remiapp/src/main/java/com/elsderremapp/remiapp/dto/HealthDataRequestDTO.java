package com.elsderremapp.remiapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HealthDataRequestDTO {
    private Double bloodPressureSystolic;
    private Double bloodPressureDiastolic;
    private Double heartRate;
    private Double weight;
    private Double temperature;
    private String notes;
}
