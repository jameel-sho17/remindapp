package com.elsderremapp.remiapp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CaregiverLinkResponseDTO {
    private Long id;
    private Long elderId;
    private String elderName;
    private Long caregiverId;
    private String caregiverName;
    private String linkedAt;
}
