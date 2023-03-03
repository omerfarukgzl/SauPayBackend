package com.omer.Payment.repository;

import com.omer.Payment.model.CommerceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommerceCompanyRepository extends JpaRepository<CommerceCompany, String> {
}
