package com.highland.dorothy.repository;

import com.highland.dorothy.bean.companyInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInformationRepository extends JpaRepository<companyInformation,Integer> {
}
