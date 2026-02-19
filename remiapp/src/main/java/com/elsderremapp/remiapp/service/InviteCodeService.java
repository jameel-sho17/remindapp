package com.elsderremapp.remiapp.service;

import com.elsderremapp.remiapp.dto.ConnectUsingCodeRequestDTO;
import com.elsderremapp.remiapp.dto.GenerateInviteRequestDTO;
import com.elsderremapp.remiapp.dto.InviteCodeResponseDTO;
import com.elsderremapp.remiapp.exception.BadRequestException;
import com.elsderremapp.remiapp.exception.ResourceNotFoundException;
import com.elsderremapp.remiapp.model.CaregiverLink;
import com.elsderremapp.remiapp.model.InviteCode;
import com.elsderremapp.remiapp.model.Role;
import com.elsderremapp.remiapp.model.User;
import com.elsderremapp.remiapp.repository.CaregiverLinkRepository;
import com.elsderremapp.remiapp.repository.InviteCodeRepository;
import com.elsderremapp.remiapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class InviteCodeService {
    private final InviteCodeRepository inviteCodeRepository;
    private final UserRepository userRepository;
    private final CaregiverLinkRepository caregiverLinkRepository;
    private static final int INVITE_EXPIRY_DAYS = 7;
    //  Generate Invite Code
    public InviteCodeResponseDTO generateInviteCode(
            GenerateInviteRequestDTO dto) {

        User elder = userRepository.findById(dto.getElderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Elder not found"));

        if (elder.getRole() != Role.ELDER) {
            throw new BadRequestException("User is not an elder");
        }

        String code = generateRandomCode();

        InviteCode inviteCode = InviteCode.builder()
                .code(code)
                .elder(elder)
                .used(false)
                .expiresAt(LocalDateTime.now().plusDays(INVITE_EXPIRY_DAYS))
                .build();

        inviteCodeRepository.save(inviteCode);

        return InviteCodeResponseDTO.builder()
                .code(code)
                .expiresAt(inviteCode.getExpiresAt().toString())
                .build();
    }

    // Connect Using Code
    public String connectUsingCode(ConnectUsingCodeRequestDTO dto) {

        InviteCode inviteCode = inviteCodeRepository
                .findByCode(dto.getCode())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid code"));

        if (inviteCode.isUsed()) {
            throw new BadRequestException("Code already used");
        }

        if (inviteCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Code expired");
        }

        User caregiver = userRepository.findById(dto.getCaregiverId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Caregiver not found"));

        if (caregiver.getRole() != Role.CAREGIVER) {
            throw new BadRequestException("User is not a caregiver");
        }

        //  Prevent duplicate link
        caregiverLinkRepository
                .findByElderAndCaregiver(inviteCode.getElder(), caregiver)
                .ifPresent(link -> {
                    throw new BadRequestException("Already connected");
                });

        CaregiverLink link = CaregiverLink.builder()
                .elder(inviteCode.getElder())
                .caregiver(caregiver)
                .linkedAt(LocalDateTime.now())
                .build();

        caregiverLinkRepository.save(link);

        inviteCode.setUsed(true);
        inviteCodeRepository.save(inviteCode);

        return "Caregiver connected successfully!";
    }

    //  Random Code Generator
    private String generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }
}
