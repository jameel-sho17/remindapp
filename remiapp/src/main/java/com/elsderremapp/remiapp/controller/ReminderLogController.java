package com.elsderremapp.remiapp.controller;

import com.elsderremapp.remiapp.dto.ReminderLogRequestDTO;
import com.elsderremapp.remiapp.dto.ReminderLogResponseDTO;
import com.elsderremapp.remiapp.model.ReminderLog;
import com.elsderremapp.remiapp.model.ReminderStatus;
import com.elsderremapp.remiapp.service.ReminderLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/reminder-logs")
@RequiredArgsConstructor
public class ReminderLogController {
    private final ReminderLogService reminderLogService;

    @PostMapping
    public ReminderLogResponseDTO createLog(
            @RequestBody ReminderLogRequestDTO request) {

        return reminderLogService.createLog(request);
    }

    @PatchMapping("/{logId}")
    public ReminderLogResponseDTO updateStatus(
            @PathVariable Long logId,
            @RequestParam String status) {

        return reminderLogService.updateStatus(logId, status);
    }
}
