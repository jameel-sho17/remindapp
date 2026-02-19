package com.elsderremapp.remiapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name ="invite_codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InviteCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private LocalDateTime expiresAt;

    private boolean used;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elder_id", nullable = false)
    private User elder;
}
