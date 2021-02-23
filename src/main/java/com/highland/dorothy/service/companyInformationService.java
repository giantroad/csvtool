package com.highland.dorothy.service;

import com.highland.dorothy.bean.companyInformation;
import com.highland.dorothy.repository.CompanyInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class companyInformationService {
    @Autowired
    CompanyInformationRepository companyInformation;
    public void save(List<companyInformation> companyInformationList){
        companyInformation.saveAndFlush(companyInformationList);
    }
}
