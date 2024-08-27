package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.NgoDto;
import com.app.entity.NgoEntity;
import com.app.repositories.NgoRepository;

@Service
public class NgoService {
	@Autowired
    private NgoRepository NgoRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Transactional(readOnly = true)
    public NgoDto getNgoById(Long id) {
        NgoEntity Ngo = NgoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ngo not found"));
        return modelMapper.map(Ngo,NgoDto.class);
    }
    
    @Transactional(readOnly = true)
    public List<NgoDto> getAllNgos() {
        return NgoRepository.findAll().stream()
                .map(Ngo -> modelMapper.map(Ngo, NgoDto.class))
                .collect(Collectors.toList());
    }
    
    @Transactional
    public NgoDto createNgo(NgoDto NgoDto) {
        NgoEntity Ngo = modelMapper.map(NgoDto, NgoEntity.class);
        Ngo = NgoRepository.save(Ngo);
        return modelMapper.map(Ngo, NgoDto.class);
    }
    
    @Transactional
    public NgoDto updateNgo(Long id, NgoDto NgoDetails) {
        NgoEntity Ngo = NgoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ngo not found"));
        modelMapper.map(NgoDetails, Ngo);
        Ngo = NgoRepository.save(Ngo);
        return modelMapper.map(Ngo, NgoDto.class);
    }
    @Transactional
    public void deleteNgo(Long id) {
        NgoEntity Ngo = NgoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ngo not found"));
        NgoRepository.delete(Ngo);
    }
}

