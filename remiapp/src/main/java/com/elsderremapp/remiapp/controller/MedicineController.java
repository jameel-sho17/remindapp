package com.elsderremapp.remiapp.controller;

import com.elsderremapp.remiapp.dto.MedicineRequestDTO;
import com.elsderremapp.remiapp.dto.MedicineResponseDTO;
import com.elsderremapp.remiapp.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
@CrossOrigin
public class MedicineController {
    private  final MedicineService medicineService;

    @PostMapping("/addmedicines")
    public ResponseEntity<MedicineResponseDTO> addMedicine(
            @RequestBody MedicineRequestDTO request) {

        return ResponseEntity.ok(medicineService.addMedicine(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MedicineResponseDTO>> getMedicines(
            @PathVariable Long userId) {

        return ResponseEntity.ok(medicineService.getMedicines(userId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MedicineResponseDTO> updateMedicine(
            @PathVariable Long id,
            @RequestBody MedicineRequestDTO request) {

        return ResponseEntity.ok(medicineService.updateMedicine(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok("Medicine deleted successfully");
    }
}
