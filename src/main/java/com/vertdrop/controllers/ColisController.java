package com.vertdrop.controllers;

import com.vertdrop.dto.CreateColisRequest;
import com.vertdrop.entities.Colis;
import com.vertdrop.entities.Livreur;
import com.vertdrop.entities.StatusColis;
import com.vertdrop.services.ColisService;
import com.vertdrop.services.LivreurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/colis")
public class ColisController {

    private final ColisService colisService;
    private final LivreurService livreurService;

    public ColisController(ColisService colisService, LivreurService livreurService) {
        this.colisService = colisService;
        this.livreurService = livreurService;
    }

    @GetMapping
    public ResponseEntity<List<Colis>> listAll() {
        return ResponseEntity.ok(colisService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colis> getById(@PathVariable Long id) {
        Colis c = colisService.findById(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-livreur/{livreurId}")
    public ResponseEntity<List<Colis>> listByLivreur(@PathVariable Long livreurId) {
        return ResponseEntity.ok(colisService.findByLivreurId(livreurId));
    }

    @PostMapping
    public ResponseEntity<Colis> create(@RequestBody CreateColisRequest payload) {
        Livreur liv = livreurService.findById(payload.getLivreurId());
        if (liv == null) return ResponseEntity.notFound().build();

        Colis toSave = new Colis(
                payload.getDestinataire(),
                payload.getAdresse(),
                payload.getPoids(),
                StatusColis.PREPARATION,
                liv
        );
        Colis saved = colisService.saveUnique(toSave);
        return ResponseEntity.created(URI.create("/api/colis/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colis> update(@PathVariable Long id, @RequestBody CreateColisRequest payload) {
        Colis existing = colisService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        Livreur liv = livreurService.findById(payload.getLivreurId());
        if (liv == null) return ResponseEntity.status(400).build();

        existing.setDestinataire(payload.getDestinataire());
        existing.setAdresse(payload.getAdresse());
        existing.setPoids(payload.getPoids());
        existing.setLivreur(liv);

        Colis updated = colisService.save(existing);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Colis> updateStatus(@PathVariable Long id, @RequestParam("value") String value) {
        Colis existing = colisService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        try {
            StatusColis status = StatusColis.valueOf(value.toUpperCase());
            existing.setStatus(status);
            return ResponseEntity.ok(colisService.save(existing));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Colis existing = colisService.findById(id);
        if (existing == null) return ResponseEntity.notFound().build();
        colisService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
