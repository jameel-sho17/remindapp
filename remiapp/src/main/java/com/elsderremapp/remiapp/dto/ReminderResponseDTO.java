package com.elsderremapp.remiapp.dto;

import com.elsderremapp.remiapp.model.ReminderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReminderResponseDTO {
    private Long id;
    private Long medicineId;
    private String medicineName;
    private LocalDateTime scheduledTime;
    private ReminderStatus status;


}
