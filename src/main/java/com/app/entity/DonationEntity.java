package com.app.entity;

import javax.persistence.*;

@Entity
public class DonationEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	    private String itemType;
	    private Integer quantity;
	    private String description;


	    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinColumn(name = "donor_id")
	    private DonerEntity donor;

	    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinColumn(name = "campaign_id")
	    private CampaignEntity campaign;

	    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinColumn(name = "payment_id")
	    private PaymentEntity payment;


		public DonationEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		public DonationEntity(Long id, String itemType, Integer quantity, String description, DonerEntity donor,
				CampaignEntity campaign, PaymentEntity payment) {
			super();
			this.id = id;
			this.itemType = itemType;
			this.quantity = quantity;
			this.description = description;
			this.donor = donor;
			this.campaign = campaign;
			this.payment = payment;
		}

		public DonationEntity(String itemType, Integer quantity, String description, DonerEntity donor,
				CampaignEntity campaign, PaymentEntity payment) {
			super();
			this.itemType = itemType;
			this.quantity = quantity;
			this.description = description;
			this.donor = donor;
			this.campaign = campaign;
			this.payment = payment;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getItemType() {
			return itemType;
		}

		public void setItemType(String itemType) {
			this.itemType = itemType;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public DonerEntity getDonor() {
			return donor;
		}

		public void setDonor(DonerEntity donor) {
			this.donor = donor;
		}

		public CampaignEntity getCampaign() {
			return campaign;
		}

		public void setCampaign(CampaignEntity campaign) {
			this.campaign = campaign;
		}

		public PaymentEntity getPayment() {
			return payment;
		}

		public void setPayment(PaymentEntity payment) {
			this.payment = payment;
		}
	    

}

