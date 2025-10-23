package com.vertdrop.controllers;

import com.vertdrop.entities.Livreur;
import com.vertdrop.services.LivreurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/livreurs")
public class LivreurController {

    private final LivreurService livreurService;

    // constructor injection (Spring will autowire)
    public LivreurController(LivreurService livreurService) {
        this.livreurService = livreurService;
    }

    @GetMapping
    public ResponseEntity<List<Livreur>> listAll() {
        return ResponseEntity.ok(livreurService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livreur> getById(@PathVariable Long id) {
        return livreurService.findByIdOpt(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Livreur> create(@RequestBody Livreur payload) {
        Livreur saved = livreurService.save(payload);
        return ResponseEntity.created(URI.create("/api/livreurs/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livreur> update(@PathVariable Long id, @RequestBody Livreur payload) {
        payload.setId(id);
        Livreur updated = livreurService.save(payload);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livreurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
