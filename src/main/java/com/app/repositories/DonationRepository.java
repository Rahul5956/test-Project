package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.DonationEntity;

public interface DonationRepository extends JpaRepository<DonationEntity,Long>{

}
