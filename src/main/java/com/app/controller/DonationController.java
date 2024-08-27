package com.app.controller;

import com.app.dto.CampaignDto;
import com.app.dto.DonationDto;
import com.app.dto.DonerDto;
import com.app.dto.PaymentDto;
import com.app.service.CampaignService;
import com.app.service.DonationService;
import com.app.service.DonorService;
import com.app.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {
    @Autowired
    private DonationService donationService;
    
    @Autowired 
    private DonorService donerService;

    @Autowired
   private CampaignService campaignService;

    @Autowired
   private PaymentService paymentService;

    @GetMapping("/{id}/{donorId}/{campaignId}/{paymentId}")
    public ResponseEntity<DonationDto> getDonationById(
            @PathVariable Long id,
            @PathVariable("donorId") Long donorId,
            @PathVariable("campaignId") Long campaignId,
            @PathVariable("paymentId") Long paymentId) {
        try {
            DonationDto donationDTO = donationService.getById(id, donorId, campaignId, paymentId);
            return ResponseEntity.ok(donationDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("{donorId}/{campaignId}/{paymentId}")
    public ResponseEntity<List<DonationDto>> getAllDonations(
    		@PathVariable("donorId") Long donorId,
            @PathVariable("campaignId") Long campaignId,
            @PathVariable("paymentId") Long paymentId) {
    	try {
    		 List<DonationDto> donations = donationService.getAllDonations(donorId, campaignId, paymentId);
    	     return ResponseEntity.ok(donations);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(null);
		}
    }

    @PostMapping("/create/{donorId}/{campaignId}/{paymentId}")
    public ResponseEntity<DonationDto> createDonation(
            @PathVariable("donorId") Long donorId,
            @PathVariable("campaignId") Long campaignId,
            @PathVariable("paymentId") Long paymentId,
            @RequestBody DonationDto donationDto) {
        
        try {
            DonationDto createdDonation = donationService.createDonation(donationDto, donorId, campaignId, paymentId);
            return ResponseEntity.ok(createdDonation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
       
    }

    @PutMapping("/{id}/{donorId}/{campaignId}/{paymentId}")
    public ResponseEntity<DonationDto> updateDonation(
            @RequestBody DonationDto donationDetails,
            @PathVariable Long id,
            @PathVariable Long donorId,
            @PathVariable Long campaignId,
            @PathVariable Long paymentId) {
        try {
            DonationDto updatedDonation = donationService.updateDonation(donationDetails,id,donorId, campaignId, paymentId);
            return ResponseEntity.ok(updatedDonation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping("/{id}/{donorId}/{campaignId}/{paymentId}")
    public ResponseEntity<Void> deleteDonation(
            @PathVariable Long id,
            @PathVariable Long donorId,
            @PathVariable Long campaignId,
            @PathVariable Long paymentId) {
        try {
            donationService.deleteDonation(id, donorId, campaignId, paymentId);
            return ResponseEntity.noContent().build(); // No content to return, deletion successful
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if the donation or other constraints are not met
        }
    }
    
}

