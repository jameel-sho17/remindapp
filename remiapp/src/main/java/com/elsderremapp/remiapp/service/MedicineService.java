package com.elsderremapp.remiapp.service;

import com.elsderremapp.remiapp.dto.MedicineRequestDTO;
import com.elsderremapp.remiapp.dto.MedicineResponseDTO;
import com.elsderremapp.remiapp.exception.BadRequestException;
import com.elsderremapp.remiapp.exception.ResourceNotFoundException;
import com.elsderremapp.remiapp.model.Frequency;
import com.elsderremapp.remiapp.model.Medicine;
import com.elsderremapp.remiapp.model.User;
import com.elsderremapp.remiapp.repository.MedicineRepository;
import com.elsderremapp.remiapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;

    // ========================= ADD MEDICINE =========================
    public MedicineResponseDTO addMedicine(MedicineRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + request.getUserId()));

        Frequency frequency = parseFrequency(request.getFrequency());

        Medicine medicine = Medicine.builder()
                .medicineName(request.getMedicineName())
                .dosage(request.getDosage())
                .time(LocalTime.parse(request.getTime()))
                .frequency(frequency)
                .confirmed(false)
                .user(user)
                .build();

        return mapToResponse(medicineRepository.save(medicine));
    }

    // ========================= GET MEDICINES =========================
    public List<MedicineResponseDTO> getMedicines(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + userId));

        return medicineRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ========================= UPDATE MEDICINE =========================
    public MedicineResponseDTO updateMedicine(Long id, MedicineRequestDTO request) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Medicine not found with id " + id));

        medicine.setMedicineName(request.getMedicineName());
        medicine.setDosage(request.getDosage());
        medicine.setTime(LocalTime.parse(request.getTime()));
        medicine.setFrequency(parseFrequency(request.getFrequency()));

        return mapToResponse(medicineRepository.save(medicine));
    }

    // ========================= DELETE =========================
    public void deleteMedicine(Long id) {

        if (!medicineRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medicine not found with id " + id);
        }

        medicineRepository.deleteById(id);
    }

    // ========================= PRIVATE HELPERS =========================

    private Frequency parseFrequency(String frequencyValue) {
        try {
            return Frequency.valueOf(frequencyValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid frequency value. Use DAILY / WEEKLY / MONTHLY");
        }
    }

    private MedicineResponseDTO mapToResponse(Medicine medicine) {

        return MedicineResponseDTO.builder()
                .id(medicine.getId())
                .medicineName(medicine.getMedicineName())
                .dosage(medicine.getDosage())
                .time(medicine.getTime().toString())
                .frequency(medicine.getFrequency().name())
                .confirmed(medicine.isConfirmed())
                .build();
    }

}
