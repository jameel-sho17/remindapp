package com.elsderremapp.remiapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReminderLogResponseDTO {
    private Long id;
    private String scheduledTime;
    private String status;
    private String takenTime;
}
