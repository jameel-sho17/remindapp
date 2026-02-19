package com.elsderremapp.remiapp.service;

import com.elsderremapp.remiapp.dto.ReminderLogRequestDTO;
import com.elsderremapp.remiapp.dto.ReminderLogResponseDTO;
import com.elsderremapp.remiapp.exception.ResourceNotFoundException;
import com.elsderremapp.remiapp.model.Medicine;
import com.elsderremapp.remiapp.model.ReminderLog;
import com.elsderremapp.remiapp.model.ReminderStatus;
import com.elsderremapp.remiapp.model.User;
import com.elsderremapp.remiapp.repository.MedicineRepository;
import com.elsderremapp.remiapp.repository.ReminderLogRepository;
import com.elsderremapp.remiapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReminderLogService {

    private final ReminderLogRepository reminderLogRepository;
    private final UserRepository userRepository;
    private final MedicineRepository medicineRepository;

    // ========================= CREATE LOG =========================
    public ReminderLogResponseDTO createLog(ReminderLogRequestDTO request) {

        User elder = userRepository.findById(request.getElderId())
                .orElseThrow(() -> new ResourceNotFoundException("Elder not found with id " + request.getElderId()));

        Medicine medicine = medicineRepository.findById(request.getMedicineId())
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id " + request.getMedicineId()));

        ReminderLog log = ReminderLog.builder()
                .elder(elder)
                .medicine(medicine)
                .scheduledTime(LocalDateTime.parse(request.getScheduledTime()))
                .status(ReminderStatus.PENDING)
                .build();

        return mapToResponse(reminderLogRepository.save(log));
    }

    // ========================= UPDATE STATUS =========================
    public ReminderLogResponseDTO updateStatus(Long logId, String statusValue) {

        ReminderLog log = reminderLogRepository.findById(logId)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with id " + logId));

        ReminderStatus status;

        try {
            status = ReminderStatus.valueOf(statusValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value. Use PENDING / TAKEN / MISSED");
        }

        log.setStatus(status);

        if (status == ReminderStatus.TAKEN) {
            log.setTakenTime(LocalDateTime.now());
        }

        return mapToResponse(reminderLogRepository.save(log));
    }

    // ========================= MAPPING =========================
    private ReminderLogResponseDTO mapToResponse(ReminderLog log) {

        return ReminderLogResponseDTO.builder()
                .id(log.getId())
                .scheduledTime(log.getScheduledTime().toString())
                .status(log.getStatus().name())
                .takenTime(log.getTakenTime() != null ? log.getTakenTime().toString() : null)
                .build();
    }
}
