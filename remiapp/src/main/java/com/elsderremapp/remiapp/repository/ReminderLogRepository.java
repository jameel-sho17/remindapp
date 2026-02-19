package com.elsderremapp.remiapp.repository;

import com.elsderremapp.remiapp.model.ReminderLog;
import com.elsderremapp.remiapp.model.ReminderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReminderLogRepository extends CrudRepository<ReminderLog, Long> {
    List<ReminderLog> findByElderIdOrderByScheduledTimeDesc(Long elderId);

    List<ReminderLog> findByElderIdAndStatus(Long elderId, ReminderStatus status);
}
