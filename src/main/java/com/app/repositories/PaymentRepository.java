package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long>{

}
