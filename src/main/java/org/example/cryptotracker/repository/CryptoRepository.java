package org.example.cryptotracker.repository;

import org.example.cryptotracker.model.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoEntity, Integer> {

    Optional<CryptoEntity> findCryptoEntitiesById(Integer id);
}
