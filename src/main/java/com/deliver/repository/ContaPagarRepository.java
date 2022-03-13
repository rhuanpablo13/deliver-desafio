package com.deliver.repository;

import com.deliver.domains.ContaPagar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaPagarRepository extends JpaRepository <ContaPagar, Integer> {
    
}