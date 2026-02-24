package com.elsderremapp.remiapp.controller;

import com.elsderremapp.remiapp.dto.CaregiverLinkRequestDTO;
import com.elsderremapp.remiapp.dto.CaregiverLinkResponseDTO;
import com.elsderremapp.remiapp.service.CaregiverLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caregiver-links")
@RequiredArgsConstructor
public class CaregiverLinkController {

    private final CaregiverLinkService caregiverLinkService;

    @GetMapping("/elder/{elderId}")
    public ResponseEntity<List<CaregiverLinkResponseDTO>>
    getCaregiversForElder(@PathVariable Long elderId) {

        return ResponseEntity.ok(
                caregiverLinkService.getCaregiversForElder(elderId)
        );
    }

    @GetMapping("/caregiver/{caregiverId}")
    public ResponseEntity<List<CaregiverLinkResponseDTO>>
    getEldersForCaregiver(@PathVariable Long caregiverId) {

        return ResponseEntity.ok(
                caregiverLinkService.getEldersForCaregiver(caregiverId)
        );
    }
    @DeleteMapping("/disconnect")
    public ResponseEntity<String> disconnect(
            @RequestParam Long elderId,
            @RequestParam Long caregiverId) {

        caregiverLinkService.disconnectCaregiver(elderId, caregiverId);
        return ResponseEntity.ok("Caregiver disconnected successfully");
    }

}
