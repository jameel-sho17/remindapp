package com.elsderremapp.remiapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(
        name = "caregiver_links",
        uniqueConstraints = @UniqueConstraint(
        columnNames = {"elder_id", "caregiver_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaregiverLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Elder
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elder_id", nullable = false)
    private User elder;

    // Caregiver
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caregiver_id", nullable = false)
    private User caregiver;

    private LocalDateTime linkedAt;
}
