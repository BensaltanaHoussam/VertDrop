package com.vertdrop.repositories;


import com.vertdrop.entities.Colis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {

    List<Colis> findByLivreurId(Long livreurId);

    Optional<Colis> findByDestinataireAndAdresse(String destinataire, String adresse);

}
