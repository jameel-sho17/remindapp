package com.elsderremapp.remiapp.dto;

import lombok.Data;

@Data
public class MedicineRequestDTO {
    private String medicineName;
    private String dosage;
    private String time;
    private String frequency;
    private Long userId;
}
