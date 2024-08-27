package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.FundEntity;

public interface FundRepository extends JpaRepository<FundEntity,Long> {

}
