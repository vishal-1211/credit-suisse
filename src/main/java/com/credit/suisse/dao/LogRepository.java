package com.credit.suisse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credit.suisse.entity.LogEntity;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Integer> {

}
