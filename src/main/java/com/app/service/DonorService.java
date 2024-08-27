package com.app.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.DonerDto;
import com.app.entity.DonerEntity;
import com.app.repositories.DonerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonorService {
    @Autowired
    private DonerRepository donorRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Transactional(readOnly = true)
    public DonerDto getDonorById(Long id) {
        DonerEntity donor = donorRepository.findById(id).orElseThrow(() -> new RuntimeException("Donor not found"));
        return modelMapper.map(donor,DonerDto.class);
    }
    
    @Transactional(readOnly = true)
    public List<DonerDto> getAllDonors() {
        return donorRepository.findAll().stream()
                .map(donor -> modelMapper.map(donor, DonerDto.class))
                .collect(Collectors.toList());
    }
    
    @Transactional
    public DonerDto createDonor(DonerDto donorDto) {
        DonerEntity donor = modelMapper.map(donorDto, DonerEntity.class);
        donor = donorRepository.save(donor);
        return modelMapper.map(donor, DonerDto.class);
    }
    
    @Transactional
    public DonerDto updateDonor(Long id, DonerDto donorDetails) {
        DonerEntity donor = donorRepository.findById(id).orElseThrow(() -> new RuntimeException("Donor not found"));
        modelMapper.map(donorDetails, donor);
        donor = donorRepository.save(donor);
        return modelMapper.map(donor, DonerDto.class);
    }
    @Transactional
    public void deleteDonor(Long id) {
        DonerEntity donor = donorRepository.findById(id).orElseThrow(() -> new RuntimeException("Donor not found"));
        donorRepository.delete(donor);
    }
}



