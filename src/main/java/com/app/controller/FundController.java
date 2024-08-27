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

import com.app.dto.FundDto;
import com.app.service.CampaignService;
import com.app.service.FundService;
import com.app.service.NgoService;

@RestController
@RequestMapping("/funds")
public class FundController {
    @Autowired
    private FundService fundService;
    
    @Autowired
    private CampaignService campaignService;
    
    @Autowired 
    private NgoService ngoService;
    
    @GetMapping("/{id}/{camapignId}/{NgoId}")
    public ResponseEntity<FundDto> getfundById(@PathVariable Long id,@PathVariable("NgoId") Long NgoId,@PathVariable("camapignId") Long camapignId) 
    {
    	
        FundDto FundDto = fundService.getfundById(id,NgoId,camapignId);
        return ResponseEntity.ok(FundDto);
    }

    @GetMapping("{camapignId}/{NgoId}")
    public ResponseEntity<List<FundDto>> getAllfunds(@PathVariable("NgoId") Long NgoId,@PathVariable("camapignId") Long camapignId) {
        List<FundDto> funds = fundService.getAllFunds(NgoId,camapignId);
        return ResponseEntity.ok(funds);
    }

    @PostMapping("/add/{camapignId}/{NgoId}")
    public ResponseEntity<FundDto> createfund(@PathVariable("NgoId") Long NgoId,@PathVariable("camapignId") Long camapignId,@RequestBody FundDto fundDto) {
        FundDto createdfund = fundService.createfund(fundDto,NgoId,camapignId);
        return ResponseEntity.ok(createdfund);
    }

    @PutMapping("/{id}/{camapignId}/{NgoId}")
    public ResponseEntity<FundDto> updatefund(@PathVariable Long id, @PathVariable("NgoId") Long NgoId,@PathVariable("camapignId") Long camapignId,@RequestBody FundDto fundDetails) {
        FundDto updatedfund = fundService.updatefund(id, fundDetails,NgoId, camapignId);
        return ResponseEntity.ok(updatedfund);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletefund(@PathVariable Long id) {
        fundService.deletefund(id);
        return ResponseEntity.noContent().build();
    }
}
