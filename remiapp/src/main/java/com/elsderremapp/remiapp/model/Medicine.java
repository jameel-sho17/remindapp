package com.elsderremapp.remiapp.model;

import jakarta.persistence.*;
import lombok.*;
import com.elsderremapp.remiapp.model.User;

import java.time.LocalTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicineName;

    private String dosage;

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private Frequency frequency; // DAILY / WEEKLY etc

    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
