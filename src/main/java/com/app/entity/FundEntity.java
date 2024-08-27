package com.app.entity;

import javax.persistence.*;

@Entity
public class FundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String timestamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ngo_id")
    private NgoEntity ngo;

	public FundEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FundEntity(Long id, Double amount, String timestamp, CampaignEntity campaign, NgoEntity ngo) {
		super();
		this.id = id;
		this.amount = amount;
		this.timestamp = timestamp;
		this.campaign = campaign;
		this.ngo = ngo;
	}

	public FundEntity(Double amount, String timestamp, CampaignEntity campaign, NgoEntity ngo) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
		this.campaign = campaign;
		this.ngo = ngo;
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

	public CampaignEntity getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignEntity campaign) {
		this.campaign = campaign;
	}

	public NgoEntity getNgo() {
		return ngo;
	}

	public void setNgo(NgoEntity ngo) {
		this.ngo = ngo;
	}

    
}


