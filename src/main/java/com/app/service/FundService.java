package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.FundDto;
import com.app.entity.CampaignEntity;
import com.app.entity.FundEntity;
import com.app.entity.NgoEntity;
import com.app.repositories.CampaignRepository;
import com.app.repositories.FundRepository;
import com.app.repositories.NgoRepository;

@Service
public class FundService {
	@Autowired
    private FundRepository fundRepository;
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private NgoRepository ngoRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Transactional(readOnly = true)
    public FundDto getfundById(Long id,Long campaignId,Long NgoId) {
    	CampaignEntity campaignEntity = campaignRepository.findById(campaignId).orElseThrow(()-> new RuntimeException("campaign not found"));
    	NgoEntity ngoEntity = ngoRepository.findById(NgoId).orElseThrow(()-> new RuntimeException("ngo not found"));
    	
        FundEntity fund = fundRepository.findById(id).orElseThrow(() -> new RuntimeException("fund not found"));
        
        FundDto fundDto =  modelMapper.map(fund,FundDto.class);
        fundDto.setCampaignId(campaignEntity.getId());
        return fundDto;
    }
    
    @Transactional(readOnly = true)
    public List<FundDto> getAllFunds(Long campaignId, Long ngoId) {
        // Fetch the CampaignEntity by its ID
        CampaignEntity campaignEntity = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        // Fetch the NgoEntity by its ID
        NgoEntity ngoEntity = ngoRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("NGO not found"));

        // Fetch the funds by campaignId and ngoId (assuming such a method exists in the repository)
        return fundRepository.findAll().stream()
                .map(fund -> {
                    FundDto fundDto = modelMapper.map(fund, FundDto.class);
                    fundDto.setCampaignId(campaignEntity.getId());
                    fundDto.setNgoId(ngoEntity.getId());
                    return fundDto;
                })
                .collect(Collectors.toList());
    }

    
    @Transactional
    public FundDto createfund(FundDto fundDto,Long campaignId, Long ngoId) {
    	 CampaignEntity campaignEntity = campaignRepository.findById(campaignId)
                 .orElseThrow(() -> new RuntimeException("Campaign not found"));

         // Fetch the NgoEntity by its ID
         NgoEntity ngoEntity = ngoRepository.findById(ngoId)
                 .orElseThrow(() -> new RuntimeException("NGO not found"));
         
        FundEntity fundEntity = modelMapper.map(fundDto, FundEntity.class);
        fundEntity.setCampaign(campaignEntity);
        fundEntity.setNgo(ngoEntity);
        
        fundEntity = fundRepository.save(fundEntity);
        FundDto fundDto1 =  modelMapper.map(fundEntity, FundDto.class);
        fundDto1.setCampaignId(campaignEntity.getId());
        fundDto1.setNgoId(ngoEntity.getId());
        return fundDto1; 
    }
    
    @Transactional
    public FundDto updatefund(Long id, FundDto fundDetails, Long campaignId, Long ngoId) {
    	 CampaignEntity campaignEntity = campaignRepository.findById(campaignId)
                 .orElseThrow(() -> new RuntimeException("Campaign not found"));

         // Fetch the NgoEntity by its ID
         NgoEntity ngoEntity = ngoRepository.findById(ngoId)
                 .orElseThrow(() -> new RuntimeException("NGO not found"));
         
        FundEntity fund = fundRepository.findById(id).orElseThrow(() -> new RuntimeException("fund not found"));
        modelMapper.map(fundDetails, fund);
        
        fund.setCampaign(campaignEntity);
        fund.setNgo(ngoEntity);
        
        fund = fundRepository .save(fund);
        FundDto fundDto =  modelMapper.map(fund, FundDto.class);
        fundDto.setCampaignId(campaignEntity.getId());
        fundDto.setCampaignId(ngoEntity.getId());
        return fundDto;
    }
    @Transactional
    public void deletefund(Long id) {
    	
        FundEntity fund = fundRepository.findById(id).orElseThrow(() -> new RuntimeException("fund not found"));
        fundRepository.delete(fund);
    }
}

