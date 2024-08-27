package com.app.entity;

import javax.persistence.*;

@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String timestamp;
    private String status;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

	public PaymentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentEntity(Long id, Double amount, String timestamp, String status, CampaignEntity campaign) {
		super();
		this.id = id;
		this.amount = amount;
		this.timestamp = timestamp;
		this.status = status;
		this.campaign = campaign;
		
	}

	public PaymentEntity(Double amount, String timestamp, String status, CampaignEntity campaign) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
		this.status = status;
		this.campaign = campaign;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CampaignEntity getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignEntity campaign) {
		this.campaign = campaign;
	}
    
}


