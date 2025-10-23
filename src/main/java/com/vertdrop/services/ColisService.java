package com.vertdrop.services;

import com.vertdrop.entities.Colis;
import com.vertdrop.repositories.ColisRepository;

import java.util.List;

public class ColisService {

    private final ColisRepository colisRepository;

    public ColisService(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    public Colis save(Colis colis) {
        return colisRepository.save(colis);
    }

    public List<Colis> findAll() {
        return colisRepository.findAll();
    }

    public Colis findById(Long id) {
        return colisRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        colisRepository.deleteById(id);
    }

    public List<Colis> findByLivreurId(Long livreurId) {
        return colisRepository.findByLivreurId(livreurId);
    }

    public Colis saveUnique(Colis colis) {
        var existing = colisRepository.findByDestinataireAndAdresse(colis.getDestinataire(), colis.getAdresse());
        if (existing.isPresent()) {
            System.out.println("Colis already exists: " + existing.get());
            return existing.get();
        }
        return colisRepository.save(colis);
    }
}
