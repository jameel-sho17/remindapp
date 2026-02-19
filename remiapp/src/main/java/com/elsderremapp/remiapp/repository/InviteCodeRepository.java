package com.elsderremapp.remiapp.repository;

import com.elsderremapp.remiapp.model.InviteCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InviteCodeRepository extends CrudRepository<InviteCode, Long> {
    Optional<InviteCode> findByCode(String code);
}
