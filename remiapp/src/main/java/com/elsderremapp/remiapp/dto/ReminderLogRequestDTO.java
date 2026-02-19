package com.elsderremapp.remiapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReminderLogRequestDTO {
    private Long elderId;
    private Long medicineId;
    private String scheduledTime;
}
