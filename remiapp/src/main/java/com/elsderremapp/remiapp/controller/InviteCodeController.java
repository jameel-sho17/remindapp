package com.elsderremapp.remiapp.controller;

import com.elsderremapp.remiapp.dto.ConnectUsingCodeRequestDTO;
import com.elsderremapp.remiapp.dto.GenerateInviteRequestDTO;
import com.elsderremapp.remiapp.dto.InviteCodeResponseDTO;
import com.elsderremapp.remiapp.service.InviteCodeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invite-code")
@RequiredArgsConstructor
public class InviteCodeController {
    private final InviteCodeService inviteCodeService;

    @PostMapping("/generate")
    public ResponseEntity<InviteCodeResponseDTO> generateCode(
            @RequestBody GenerateInviteRequestDTO dto) {

        return ResponseEntity.ok(
                inviteCodeService.generateInviteCode(dto)
        );
    }

    @PostMapping("/connect")
    public ResponseEntity<String> connectUsingCode(
            @RequestBody ConnectUsingCodeRequestDTO dto) {

        return ResponseEntity.ok(
                inviteCodeService.connectUsingCode(dto)
        );
    }
}
