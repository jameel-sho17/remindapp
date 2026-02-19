package com.elsderremapp.remiapp.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "health_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who owns this health record
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elder_id", nullable = false)
    private User elder;

    private Double bloodPressureSystolic;
    private Double bloodPressureDiastolic;
    private Double heartRate;
    private Double bloodSugar;
    private Double weight;
    private Double temperature;

    private String notes;

    private LocalDateTime recordedAt;

    private LocalDateTime createdAt;
}
