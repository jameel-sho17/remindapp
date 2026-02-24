package com.elsderremapp.remiapp.controller;

import com.elsderremapp.remiapp.dto.ReminderResponseDTO;
import com.elsderremapp.remiapp.model.ReminderLog;
import com.elsderremapp.remiapp.model.ReminderStatus;
import com.elsderremapp.remiapp.repository.ReminderLogRepository;
import com.elsderremapp.remiapp.scheduler.ReminderScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {
    private final ReminderLogRepository reminderLogRepository;
    private final ReminderScheduler reminderScheduler;

    /*
     * Get reminders by elder
     */
    @GetMapping("/elder/{elderId}")
    public List<ReminderResponseDTO> getRemindersByElder(@PathVariable Long elderId) {

        return reminderLogRepository
                .findByElderIdOrderByScheduledTimeDesc(elderId)
                .stream()
                .map(log -> new ReminderResponseDTO(
                        log.getId(),
                        log.getMedicine().getId(),
                        log.getMedicine().getMedicineName(), // ✅ FIXED
                        log.getScheduledTime(),
                        log.getStatus()
                ))
                .toList();
    }
    /*
     * Get reminders by status
     */
    @GetMapping("/elder/{elderId}/status/{status}")
    public List<ReminderResponseDTO> getRemindersByStatus(
            @PathVariable Long elderId,
            @PathVariable ReminderStatus status) {

        return reminderLogRepository
                .findByElderIdAndStatus(elderId, status)
                .stream()
                .map(log -> new ReminderResponseDTO(
                        log.getId(),
                        log.getMedicine().getId(),
                        log.getMedicine().getMedicineName(),
                        log.getScheduledTime(),
                        log.getStatus()
                ))
                .toList();
    }
    /*
     * Manually trigger reminder generation (Testing only)
     */
    @PostMapping("/trigger/generate")
    public String triggerGenerate() {
        reminderScheduler.generateReminders();
        return "Reminder generation triggered manually.";
    }

    /*
     * Manually trigger missed check (Testing only)
     */
    @PostMapping("/trigger/missed")
    public String triggerMissed() {
        reminderScheduler.markMissedReminders();
        return "Missed reminder check triggered manually.";
    }
 }

