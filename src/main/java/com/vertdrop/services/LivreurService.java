package com.vertdrop.services;

import com.vertdrop.entities.Livreur;
import com.vertdrop.repositories.LivreurRepository;

import java.util.List;

public class LivreurService {

    private LivreurRepository livreurRepository;

    public LivreurService(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;

    }

    public Livreur save(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    public List<Livreur> findAll() {
        return livreurRepository.findAll();
    }

    public Livreur findById(Long id) {
        return livreurRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        livreurRepository.deleteById(id);
    }

}
