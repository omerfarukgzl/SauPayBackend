package com.omer.Payment.repository;

import com.omer.Payment.model.BankCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCompanyRepository extends JpaRepository<BankCompany, String> {
}
