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

import com.app.dto.PaymentDto;
import com.app.service.CampaignService;
import com.app.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private CampaignService campaignService;

    @GetMapping("/{id}/{camapignId}")
    public ResponseEntity<PaymentDto> getpaymentById(@PathVariable Long id,@PathVariable("camapignId") Long camapignId) {
        PaymentDto PaymentDto = paymentService.getPaymentById(id,camapignId);
        return ResponseEntity.ok(PaymentDto);
    }

    @GetMapping("/{camapignId}")
    public ResponseEntity<List<PaymentDto>> getAllpayments(@PathVariable("camapignId") Long camapignId) {
        List<PaymentDto> payments = paymentService.getAllPayments(camapignId);
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/add/{camapignId}")
    public ResponseEntity<PaymentDto> createpayment(@RequestBody PaymentDto PaymentDto,@PathVariable("camapignId") Long camapignId) {
        PaymentDto createdpayment = paymentService.createPayment(PaymentDto,camapignId);
        return ResponseEntity.ok(createdpayment);
    }

    @PutMapping("/{id}/{camapignId}")
    public ResponseEntity<PaymentDto> updatepayment(@PathVariable Long id,@PathVariable("camapignId") Long camapignId, @RequestBody PaymentDto paymentDetails) {
        PaymentDto updatedpayment = paymentService.updatePayment(id, paymentDetails,camapignId);
        return ResponseEntity.ok(updatedpayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletepayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
