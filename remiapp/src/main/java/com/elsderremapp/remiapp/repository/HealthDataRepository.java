package com.elsderremapp.remiapp.repository;

import com.elsderremapp.remiapp.model.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HealthDataRepository extends JpaRepository<HealthData,Long> {
    List<HealthData> findByElderIdOrderByRecordedAtDesc(Long elderId);

    List<HealthData> findByElderIdAndRecordedAtBetween(
            Long elderId,
            LocalDateTime start,
            LocalDateTime end);

}
