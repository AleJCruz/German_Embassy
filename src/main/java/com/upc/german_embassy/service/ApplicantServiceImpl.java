package com.upc.german_embassy.service;

import com.upc.german_embassy.entities.Applicant;
import com.upc.german_embassy.interfaceservice.ApplicantService;
import com.upc.german_embassy.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public void ApplicaQualification(Applicant applicant) {
        double statusfactor = 0;
        double bagfactor = 0;
        if (applicant.isBag()) bagfactor = 20;
        if (applicant.getStatus().equals("soltero") || applicant.getStatus().equals("divorciado") || applicant.getStatus().equals("viudo"))
            statusfactor = 0.5;
        else statusfactor = 1;
        applicant.setQualification((applicant.getAge() * statusfactor) + (applicant.getSalary() / 1000) + bagfactor);
        //guardarlo con update
        applicantRepository.save(applicant);
    }

    //calificación = (edad * factor estado civil) + (sueldo neto / 1000.00) + (factor bolsa de viaje)
    public Applicant registerApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    public Applicant updateApplicant(Applicant applicant) throws Exception {
        applicantRepository.findById(applicant.getId()).
                orElseThrow(() -> new Exception("No se encontró la entidad"));
        return applicantRepository.save(applicant);
    }

    public Applicant deleteApplicant(Long id) throws Exception {
        Applicant applicant = applicantRepository.findById(id).orElseThrow(() -> new Exception("No se encontro el aplicante"));
        applicantRepository.delete(applicant);
        return applicant;
    }

    public List<Applicant> listApplicants() {
        List<Applicant> list = applicantRepository.findAll();
        for (Applicant a : list) {
            ApplicaQualification(a);
        }
        return list;
    }

    public List<Applicant> listSingleApplicants() {
        List<Applicant> singles = new ArrayList<>();
        for (Applicant a : applicantRepository.findAll()) {
            if (a.getStatus().equals("soltero"))
                singles.add(a);
        }
        return singles;
    }

    public String applicantIsEligible(String dni) {
        Applicant a = applicantRepository.findApplicantByDni(dni);
        ApplicaQualification(a);
        if (a.getQualification() > 60) return "Es elegible";
        else return "No es elegible";
    }


    public Applicant updateById (Long id) throws Exception{ //Preguntarle al profe
        Applicant applicant = applicantRepository.findById(id).orElseThrow(()->new Exception("No se encontro el aplicante"));
        return updateApplicant(applicant);
    }



    public List<Applicant> listApplicantsOrderByName() {
        List<Applicant> list = applicantRepository.findAllByOrderByNameAsc();
        for (Applicant a : list) {
            ApplicaQualification(a);
        }
        return list;
    }

    public String getAverage_EligibleApplicants() {
        double prom = 0;
        List<Applicant> list = new ArrayList<>();
        for (Applicant a : listApplicants()) {
            if (a.getQualification() > 60) {
                list.add(a);
                prom += a.getQualification();
            }
        }
        if (list.isEmpty()){return "No hay postulantes elegibles.";}

        return "El promedio de puntaje de los calificados es: " + prom / list.stream().count() + " \nY la cantidad de postulantes elegibles es: " + list.stream().count();
    }

}