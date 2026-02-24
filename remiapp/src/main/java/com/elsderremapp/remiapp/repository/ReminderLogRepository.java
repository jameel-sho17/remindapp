package com.elsderremapp.remiapp.repository;

import com.elsderremapp.remiapp.model.ReminderLog;
import com.elsderremapp.remiapp.model.ReminderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderLogRepository extends JpaRepository<ReminderLog, Long> {
    List<ReminderLog> findByElderIdOrderByScheduledTimeDesc(Long elderId);

    List<ReminderLog> findByElderIdAndStatus(Long elderId, ReminderStatus status);

    boolean existsByMedicineIdAndScheduledTimeBetween(
            Long medicineId,
            LocalDateTime start,
            LocalDateTime end);

    List<ReminderLog> findByStatusAndScheduledTimeBefore(
            ReminderStatus status,
            LocalDateTime time
    );
}
