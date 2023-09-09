package com.upc.german_embassy.interfaceservice;

import com.upc.german_embassy.entities.Applicant;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApplicantService {
    public void ApplicaQualification(Applicant applicant);
    public Applicant registerApplicant(Applicant applicant);
    public Applicant updateApplicant(Applicant applicant) throws Exception;
    public Applicant deleteApplicant(Long id) throws Exception;
    public List<Applicant> listApplicants();
    public List<Applicant> listSingleApplicants();
    public String applicantIsEligible(String dni);

    public Applicant updateById (Long id) throws Exception;
    public List<Applicant> listApplicantsOrderByName();
    public String getAverage_EligibleApplicants();
}
