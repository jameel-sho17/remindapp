package com.elsderremapp.remiapp.repository;

import com.elsderremapp.remiapp.model.CaregiverLink;
import com.elsderremapp.remiapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CaregiverLinkRepository extends JpaRepository<CaregiverLink , Long> {

    Optional<CaregiverLink> findByElderAndCaregiver(User elder, User caregiver);

    List<CaregiverLink> findByCaregiver(User caregiver);

    List<CaregiverLink> findByElder(User elder);


}
