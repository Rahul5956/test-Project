package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.DonationDto;
import com.app.entity.CampaignEntity;
import com.app.entity.DonationEntity;
import com.app.entity.DonerEntity;
import com.app.entity.PaymentEntity;
import com.app.repositories.CampaignRepository;
import com.app.repositories.DonationRepository;
import com.app.repositories.DonerRepository;
import com.app.repositories.PaymentRepository;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DonerRepository donerRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional(readOnly = true)
    public DonationDto getById(Long id, Long donorId, Long campaignId, Long paymentId) {
        // Fetch the related entities using their respective IDs
        DonerEntity donorEntity = donerRepository.findById(donorId).orElseThrow(() -> new RuntimeException("Donor not found"));
        CampaignEntity campaignEntity = campaignRepository.findById(campaignId).orElseThrow(() -> new RuntimeException("Campaign not found"));
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
        DonationEntity donation = donationRepository.findById(id).orElseThrow(() -> new RuntimeException("Donation Not Found"));

        // Map the DonationEntity to DonationDto
        DonationDto savedDonationDto = modelMapper.map(donation, DonationDto.class);

        // Manually set the nested entity IDs
        savedDonationDto.setDonorId(donorEntity.getId());
        savedDonationDto.setCampaignId(campaignEntity.getId());
        savedDonationDto.setPaymentId(paymentEntity.getId());

        // Return the DTO
        return savedDonationDto;
    }

    @Transactional(readOnly = true)
    public List<DonationDto> getAllDonations(Long donorId, Long campaignId, Long paymentId) {
        DonerEntity donorEntity = donerRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found"));
        CampaignEntity campaignEntity = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return donationRepository.findAll().stream()
                .map(donation -> {
                    DonationDto donationDto = modelMapper.map(donation, DonationDto.class);
                    donationDto.setDonorId(donorEntity.getId());
                    donationDto.setCampaignId(campaignEntity.getId());
                    donationDto.setPaymentId(paymentEntity.getId());
                    return donationDto;
                })
                .collect(Collectors.toList());
    }

    

    	@Transactional
    	public DonationDto createDonation(DonationDto donationDto, Long donorId, Long campaignId, Long paymentId) {
    	    
    	    DonerEntity donorEntity = donerRepository.findById(donorId).orElseThrow(() -> new RuntimeException("Donor not found"));
    	    CampaignEntity campaignEntity = campaignRepository.findById(campaignId).orElseThrow(() -> new RuntimeException("Campaign not found"));
    	    PaymentEntity paymentEntity = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));

    	    // Create and populate the DonationEntity
    	    DonationEntity donationEntity = modelMapper.map(donationDto, DonationEntity.class);
    	    donationEntity.setDonor(donorEntity);
    	    donationEntity.setCampaign(campaignEntity);
    	    donationEntity.setPayment(paymentEntity);

    	    // Save the entity to the repository
    	    donationEntity = donationRepository.save(donationEntity);

    	    // Map the saved entity to DTO and return
    	   // return modelMapper.map(donationEntity, DonationDto.class);
    	    DonationDto savedDonationDto = modelMapper.map(donationEntity, DonationDto.class);
    	    
    	    // Manually set the nested entity IDs
    	    savedDonationDto.setDonorId(donorEntity.getId());
    	    savedDonationDto.setCampaignId(campaignEntity.getId());
    	    savedDonationDto.setPaymentId(paymentEntity.getId());

    	    // Return the DTO
    	    return savedDonationDto;
    	}

     

    @Transactional
    public DonationDto updateDonation(DonationDto donationDetails,Long id, Long donorId, Long campaignId, Long paymentId) {
    	DonerEntity donorEntity = donerRepository.findById(donorId).orElseThrow(() -> new RuntimeException("Donor not found"));
	    CampaignEntity campaignEntity = campaignRepository.findById(campaignId).orElseThrow(() -> new RuntimeException("Campaign not found"));
	    PaymentEntity paymentEntity = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
        DonationEntity donation = donationRepository.findById(id).orElseThrow(() -> new RuntimeException("Donation not found"));
        
        modelMapper.map(donationDetails, donation);
     // Set the nested entities to the donation entity
        donation.setDonor(donorEntity);
        donation.setCampaign(campaignEntity);
        donation.setPayment(paymentEntity);

        // Save the updated entity
        donation = donationRepository.save(donation);

        // Map the updated entity back to a DTO
        DonationDto savedDonationDto = modelMapper.map(donation, DonationDto.class);

        // Manually set the nested entity IDs in the DTO
        savedDonationDto.setDonorId(donorEntity.getId());
        savedDonationDto.setCampaignId(campaignEntity.getId());
        savedDonationDto.setPaymentId(paymentEntity.getId());

        // Return the updated DTO
        return savedDonationDto;
    }

    @Transactional
    public void deleteDonation(Long id, Long donorId, Long campaignId, Long paymentId) {
        // Fetch the donation entity to be deleted
        DonationEntity donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        // Optionally, validate the provided donor, campaign, and payment IDs
        if (!donation.getDonor().getId().equals(donorId) ||
            !donation.getCampaign().getId().equals(campaignId) ||
            !donation.getPayment().getId().equals(paymentId)) {
            throw new RuntimeException("Donation does not match provided identifiers");
        }

        // Now delete the donation
        donationRepository.delete(donation);
    }

}
