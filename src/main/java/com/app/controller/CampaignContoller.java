package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.CampaignDto;
import com.app.service.CampaignService;

@RestController
@RequestMapping("campaigns")
public class CampaignContoller {

    @Autowired
    private CampaignService campaignService;

    // Get a campaign by its ID and NGO ID
    @GetMapping("/{id}")
    public ResponseEntity<CampaignDto> getCampaignById(@PathVariable Long id) {
        CampaignDto campaignDto = campaignService.getById(id);
        return ResponseEntity.ok(campaignDto);
    }

    // Get all campaigns
    @GetMapping
    public ResponseEntity<List<CampaignDto>> getAllCampaigns() {
        List<CampaignDto> campaigns = campaignService.getAllCamapaigns();
        return ResponseEntity.ok(campaigns);
    }

    // Create a new campaign
    @PostMapping("/add")
    public ResponseEntity<CampaignDto> createCampaign(@RequestBody CampaignDto campaignDto) 
    {
        CampaignDto createdCampaign = campaignService.createCampaign(campaignDto);
        return ResponseEntity.ok(createdCampaign);
    }

    // Update an existing campaign
    @PutMapping("/{id}")
    public ResponseEntity<CampaignDto> updateCampaign(@PathVariable Long id, 
                                                      @RequestBody CampaignDto campaignDetails) 
                                                       {
        CampaignDto updatedCampaign = campaignService.updateCampaign(id, campaignDetails);
        return ResponseEntity.ok(updatedCampaign);
    }

    // Delete a campaign by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
}
 