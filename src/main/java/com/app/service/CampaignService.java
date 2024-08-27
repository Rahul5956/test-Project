package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CampaignDto;
import com.app.dto.DonationDto;
import com.app.entity.CampaignEntity;
import com.app.entity.NgoEntity;
import com.app.repositories.CampaignRepository;
import com.app.repositories.NgoRepository;

@Service
public class CampaignService {

	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired NgoRepository ngoRepository;
	
	@Transactional(readOnly = true)
	public CampaignDto getById(Long id)
	{
		CampaignEntity campaign = campaignRepository.findById(id).orElseThrow(()-> new RuntimeException("campaign not found"));
		return modelMapper.map(campaign,CampaignDto.class);
	}
	
	@Transactional(readOnly = true)
	public List <CampaignDto> getAllCamapaigns()
	{
		return campaignRepository.findAll().stream()
				.map(campaign -> modelMapper.map(campaign,CampaignDto.class))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public CampaignDto createCampaign(CampaignDto campaignDto)
	{
		CampaignEntity campaign = modelMapper.map(campaignDto,CampaignEntity.class);
		campaign = campaignRepository.save(campaign);
		return modelMapper.map(campaign,CampaignDto.class);	
	}
	
	@Transactional
	public CampaignDto updateCampaign(Long id, CampaignDto campaignDetails)
	{
		CampaignEntity campaign = campaignRepository.findById(id).orElseThrow(()-> new RuntimeException("Campaign not found"));
		modelMapper.map(campaignDetails,campaign);
		return modelMapper.map(campaign,CampaignDto.class);
	}
	@Transactional
	public void deleteCampaign(Long id)
	{
		CampaignEntity campaign = campaignRepository.findById(id).orElseThrow(()-> new RuntimeException("Campaign not Found"));
		campaignRepository.delete(campaign);
	}
}
