package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CampaignEntity;

public interface CampaignRepository extends JpaRepository<CampaignEntity,Long> {

}
