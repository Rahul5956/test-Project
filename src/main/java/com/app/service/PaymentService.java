package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PaymentDto;
import com.app.entity.CampaignEntity;
import com.app.entity.PaymentEntity;
import com.app.repositories.CampaignRepository;
import com.app.repositories.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
    private PaymentRepository paymentRepository;
	
	@Autowired
	private CampaignRepository campaignRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Transactional(readOnly = true)
    public PaymentDto getPaymentById(Long id,Long campaignId) {
    	CampaignEntity campaignEntity = campaignRepository.findById(campaignId).orElseThrow(()-> new RuntimeException("campaign not found"));
    	
        PaymentEntity payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("payment not found"));
        
        PaymentDto paymentDto = modelMapper.map(payment,PaymentDto.class);
        paymentDto.setCampaignId(campaignEntity.getId());
        return paymentDto;
    }
    
    @Transactional(readOnly = true)
    public List<PaymentDto> getAllPayments(Long campaignId) {
    	CampaignEntity campaignEntity = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        return paymentRepository.findAll().stream()
                .map(payment -> {
                	
                PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);
                paymentDto.setCampaignId(campaignEntity.getId());
                return paymentDto;
                })
                .collect(Collectors.toList());
    }
    
    @Transactional
    public PaymentDto createPayment(PaymentDto PaymentDto,Long campaignId)
    {
    	CampaignEntity campaignEntity = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        PaymentEntity paymentEntity = modelMapper.map(PaymentDto, PaymentEntity.class);
        paymentEntity.setCampaign(campaignEntity);
        paymentEntity = paymentRepository.save(paymentEntity);
        PaymentDto paymentDto =  modelMapper.map(paymentEntity, PaymentDto.class);
        paymentDto.setCampaignId(campaignEntity.getId());
        return paymentDto;
    }
    
    @Transactional
    public PaymentDto updatePayment(Long id, PaymentDto paymentDetails,Long campaignId) 
    {
    	CampaignEntity campaignEntity = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    	
        PaymentEntity payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("payment not found"));
        modelMapper.map(paymentDetails, payment);
        
        payment.setCampaign(campaignEntity);
        payment = paymentRepository.save(payment);
        
        PaymentDto paymentDto =  modelMapper.map(payment, PaymentDto.class);
        paymentDto.setCampaignId(campaignEntity.getId());
        return paymentDto;
    }
    @Transactional
    public void deletePayment(Long id) {
        PaymentEntity payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("payment not found"));
        paymentRepository.delete(payment);
    }
}