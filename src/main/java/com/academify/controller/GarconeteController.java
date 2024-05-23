package com.academify.controller;

import com.academify.model.Garconete;
import com.academify.model.Bar;
import com.academify.service.GarconeteService;
import com.academify.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/garconetes")
public class GarconeteController {

    @Autowired
    private GarconeteService garconeteService;

    @Autowired
    private BarService barService; //injetar BarService

    @GetMapping
    public List<Garconete> getAllGarconetes() {
        return garconeteService.findAll();
    }


    @PostMapping
    public ResponseEntity<?> createGarconete(@Valid @RequestBody Garconete garconete) {
        try {
            // Obter o Bar pelo ID
            Bar bar = barService.findById(garconete.getBar().getId());

            // Associar o Bar ao Garçonete
            garconete.setBar(bar);

            // Salvar o Garçonete
            return ResponseEntity.ok(garconeteService.save(garconete));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGarconeteById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(garconeteService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<?> updateGarconete(@PathVariable Long id, @Valid @RequestBody Garconete garconeteDetails) {
        try {
            return ResponseEntity.ok(garconeteService.update(id, garconeteDetails));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarconete(@PathVariable Long id) {
        try {
            garconeteService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countGarconetes() {
        return ResponseEntity.ok(garconeteService.count());
    }
}
