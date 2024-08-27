package com.app.controller;

import com.app.dto.DonationDto;
import com.app.dto.DonerDto;
import com.app.service.DonorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {
    @Autowired
    private DonorService donorService;

    @GetMapping("/{id}")
    public ResponseEntity<DonerDto> getDonorById(@PathVariable Long id) {
        DonerDto donorDTO = donorService.getDonorById(id);
        return ResponseEntity.ok(donorDTO);
    }

    @GetMapping
    public ResponseEntity<List<DonerDto>> getAllDonors() {
        List<DonerDto> donors = donorService.getAllDonors();
        return ResponseEntity.ok(donors);
    }

    @PostMapping("/add")
    public ResponseEntity<DonerDto> createDonor(@RequestBody DonerDto donorDTO) {
        DonerDto createdDonor = donorService.createDonor(donorDTO);
        return ResponseEntity.ok(createdDonor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonerDto> updateDonor(@PathVariable Long id, @RequestBody DonerDto donorDetails) {
        DonerDto updatedDonor = donorService.updateDonor(id, donorDetails);
        return ResponseEntity.ok(updatedDonor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ResponseEntity.noContent().build();
    }
}
