package com.elsderremapp.remiapp.service;

import com.elsderremapp.remiapp.dto.CaregiverLinkRequestDTO;
import com.elsderremapp.remiapp.dto.CaregiverLinkResponseDTO;
import com.elsderremapp.remiapp.exception.BadRequestException;
import com.elsderremapp.remiapp.exception.ResourceNotFoundException;
import com.elsderremapp.remiapp.model.CaregiverLink;
import com.elsderremapp.remiapp.model.Role;
import com.elsderremapp.remiapp.model.User;
import com.elsderremapp.remiapp.repository.CaregiverLinkRepository;
import com.elsderremapp.remiapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaregiverLinkService {
    private final CaregiverLinkRepository caregiverLinkRepository;
    private final UserRepository userRepository;

    public CaregiverLinkResponseDTO createLink(
            CaregiverLinkRequestDTO dto) {

        User elder = userRepository.findById(dto.getElderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Elder not found"));

        User caregiver = userRepository.findById(dto.getCaregiverId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Caregiver not found"));

        //  Role validation
        if (elder.getRole() != Role.ELDER) {
            throw new BadRequestException("User is not an elder");
        }

        if (caregiver.getRole() != Role.CAREGIVER) {
            throw new BadRequestException("User is not a caregiver");
        }

        //  Prevent duplicate link
        caregiverLinkRepository
                .findByElderAndCaregiver(elder, caregiver)
                .ifPresent(link -> {
                    throw new BadRequestException("Link already exists");
                });

        CaregiverLink link = CaregiverLink.builder()
                .elder(elder)
                .caregiver(caregiver)
                .linkedAt(LocalDateTime.now())
                .build();

        caregiverLinkRepository.save(link);

        return mapToResponse(link);
    }

    public List<CaregiverLinkResponseDTO> getCaregiversForElder(Long elderId) {

        User elder = userRepository.findById(elderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Elder not found"));

        return caregiverLinkRepository.findByElder(elder)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CaregiverLinkResponseDTO> getEldersForCaregiver(Long caregiverId) {

        User caregiver = userRepository.findById(caregiverId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Caregiver not found"));

        return caregiverLinkRepository.findByCaregiver(caregiver)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CaregiverLinkResponseDTO mapToResponse(CaregiverLink link) {
        return CaregiverLinkResponseDTO.builder()
                .id(link.getId())
                .elderId(link.getElder().getId())
                .elderName(link.getElder().getName())
                .caregiverId(link.getCaregiver().getId())
                .caregiverName(link.getCaregiver().getName())
                .linkedAt(link.getLinkedAt().toString())
                .build();
    }
    public void disconnectCaregiver(Long elderId, Long caregiverId) {

        User elder = userRepository.findById(elderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Elder not found"));

        User caregiver = userRepository.findById(caregiverId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Caregiver not found"));

        CaregiverLink link = caregiverLinkRepository
                .findByElderAndCaregiver(elder, caregiver)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Link not found"));

        caregiverLinkRepository.delete(link);
    }

}
