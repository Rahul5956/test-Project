package com.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class CampaignDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate; // Consider using a proper date type for better handling
    private LocalDate endDate; // Consider using a proper date type for better handling
    private Double goalAmount;
    private Double currentAmount;
 // This represents the ID of the associated NGO

    // Getters and Setters
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
