package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.NgoDto;
import com.app.service.NgoService;

@RestController
@RequestMapping("/Ngos")
public class NgoController {
    @Autowired
    private NgoService NgoService;

    @GetMapping("/{id}")
    public ResponseEntity<NgoDto> getNgoById(@PathVariable Long id) {
        NgoDto NgoDto = NgoService.getNgoById(id);
        return ResponseEntity.ok(NgoDto);
    }

    @GetMapping
    public ResponseEntity<List<NgoDto>> getAllNgos() {
        List<NgoDto> Ngos = NgoService.getAllNgos();
        return ResponseEntity.ok(Ngos);
    }

    @PostMapping("/add")
    public ResponseEntity<NgoDto> createNgo(@RequestBody NgoDto NgoDto) {
        NgoDto createdNgo = NgoService.createNgo(NgoDto);
        return ResponseEntity.ok(createdNgo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NgoDto> updateNgo(@PathVariable Long id, @RequestBody NgoDto NgoDetails) {
        NgoDto updatedNgo = NgoService.updateNgo(id, NgoDetails);
        return ResponseEntity.ok(updatedNgo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNgo(@PathVariable Long id) {
        NgoService.deleteNgo(id);
        return ResponseEntity.noContent().build();
    }
}
