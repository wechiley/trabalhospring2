package com.academify.controller;

import com.academify.model.Bar;
import com.academify.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bars")
public class BarController {

    @Autowired
    private BarService barService;

    @GetMapping
    public List<Bar> getAllBars() {
        return barService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Bar bar) {
        try {
            return ResponseEntity.ok(barService.save(bar));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBarById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(barService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody Bar barDetails) {
        try {

            return ResponseEntity.ok(barService.update(id, barDetails));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarById(@PathVariable Long id) {
        try {
            barService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countBars() {
        return ResponseEntity.ok(barService.count());
    }
}
