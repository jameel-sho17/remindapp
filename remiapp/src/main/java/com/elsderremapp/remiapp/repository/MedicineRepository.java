package com.elsderremapp.remiapp.repository;

import com.elsderremapp.remiapp.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {
    List<Medicine> findByUserId(Long userId);
    List<Medicine> findByTimeAndConfirmedTrue(LocalTime time);
}
