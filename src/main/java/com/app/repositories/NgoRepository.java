package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.NgoEntity;

public interface NgoRepository extends JpaRepository<NgoEntity,Long> {

}
