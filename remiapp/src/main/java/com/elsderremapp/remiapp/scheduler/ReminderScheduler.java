package com.elsderremapp.remiapp.scheduler;

import com.elsderremapp.remiapp.model.Medicine;
import com.elsderremapp.remiapp.model.ReminderLog;
import com.elsderremapp.remiapp.model.ReminderStatus;
import com.elsderremapp.remiapp.repository.MedicineRepository;
import com.elsderremapp.remiapp.repository.ReminderLogRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReminderScheduler {

    private final MedicineRepository medicineRepository;
    private final ReminderLogRepository reminderLogRepository;

    /*
     *  Create reminders at exact medicine time
     */
    @Scheduled(cron = "0 * * * * *")// every minute
    @Transactional
    public void generateReminders() {

        LocalDateTime now = LocalDateTime.now()
                .withSecond(0)
                .withNano(0);

        List<Medicine> medicines = medicineRepository.findAll();

        for (Medicine medicine : medicines) {

            LocalDateTime scheduledDateTime =
                    LocalDateTime.of(now.toLocalDate(), medicine.getTime());

            boolean alreadyExists =
                    reminderLogRepository
                            .existsByMedicineIdAndScheduledTimeBetween(
                                    medicine.getId(),
                                    scheduledDateTime.minusMinutes(1),
                                    scheduledDateTime.plusMinutes(1)
                            );

            if (!alreadyExists &&
                    scheduledDateTime.equals(now)) {

                ReminderLog log = ReminderLog.builder()
                        .elder(medicine.getUser())  //  FIXED
                        .medicine(medicine)
                        .scheduledTime(scheduledDateTime)
                        .status(ReminderStatus.PENDING)
                        .build();

                reminderLogRepository.save(log);
                // 🔜 Future:
                // firebaseService.notifyElder(logEntry);
            }
        }
    }

    /*
     *  Mark reminders MISSED after 15 minutes
     */
    @Scheduled(cron = "0 * * * * *")
    // every minute
    @Transactional
    public void markMissedReminders() {

        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

        List<ReminderLog> pendingReminders =
                reminderLogRepository.findByStatusAndScheduledTimeBefore(ReminderStatus.PENDING,now);

        for (ReminderLog log : pendingReminders) {

            if (log.getScheduledTime().isBefore(now.minusMinutes(30))) {

                log.setStatus(ReminderStatus.MISSED);
                reminderLogRepository.save(log);
            }
        }

            //  Future:
            // firebaseService.notifyCaregiver(reminder);
        }

    private boolean isWithinDateRange(Medicine medicine, LocalDate today) {

        if (medicine.getStartDate() != null &&
                today.isBefore(medicine.getStartDate())) {
            return false;
        }

        if (medicine.getEndDate() != null &&
                today.isAfter(medicine.getEndDate())) {
            return false;
        }

        return true;
    }

    private boolean shouldTriggerToday(Medicine medicine, LocalDate today) {

        switch (medicine.getFrequency()) {

            case DAILY:
                return true;

            case WEEKLY:
                return medicine.getStartDate().getDayOfWeek()
                        == today.getDayOfWeek();

            case MONTHLY:
                return medicine.getStartDate().getDayOfMonth()
                        == today.getDayOfMonth();

            default:
                return false;
        }
    }

    private boolean alreadyLogged(Long medicineId, LocalDateTime scheduledTime) {

        LocalDateTime startOfMinute = scheduledTime;
        LocalDateTime endOfMinute = scheduledTime
                .withSecond(59)
                .withNano(999999999);


        return reminderLogRepository
                .existsByMedicineIdAndScheduledTimeBetween(
                        medicineId,
                        scheduledTime,
                        scheduledTime.plusMinutes(1)
                );

    }
}