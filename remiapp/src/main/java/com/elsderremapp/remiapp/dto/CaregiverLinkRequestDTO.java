package com.elsderremapp.remiapp.dto;

import lombok.Data;

@Data
public class CaregiverLinkRequestDTO {
    private Long elderId;
    private Long caregiverId;
}
