package com.app.entity;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class CampaignEntity {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String title;
	    private String description;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private Double goalAmount;
	    private Double currentAmount;

		public CampaignEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CampaignEntity(Long id, String title, String description, LocalDate startDate, LocalDate endDate,
				Double goalAmount, Double currentAmount, NgoEntity ngo) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.startDate = startDate;
			this.endDate = endDate;
			this.goalAmount = goalAmount;
			this.currentAmount = currentAmount;
		}

		public CampaignEntity(String title, String description, LocalDate startDate, LocalDate endDate, Double goalAmount,
				Double currentAmount) {
			super();
			this.title = title;
			this.description = description;
			this.startDate = startDate;
			this.endDate = endDate;
			this.goalAmount = goalAmount;
			this.currentAmount = currentAmount;
			
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public Double getGoalAmount() {
			return goalAmount;
		}

		public void setGoalAmount(Double goalAmount) {
			this.goalAmount = goalAmount;
		}

		public Double getCurrentAmount() {
			return currentAmount;
		}

		public void setCurrentAmount(Double currentAmount) {
			this.currentAmount = currentAmount;
		}
		
	    
    
}

