package com.elsderremapp.remiapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Firebase device token
    private String deviceToken;

    // Elder -> Medicines
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Medicine> medicines;

    // Elder -> Health Data
    @OneToMany(mappedBy = "elder", cascade = CascadeType.ALL)
    private List<HealthData> healthDataList;



}
