package com.elsderremapp.remiapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicineResponseDTO {

    private Long id;
    private String medicineName;
    private String dosage;
    private String time;
    private String frequency;
    private boolean confirmed;
}
