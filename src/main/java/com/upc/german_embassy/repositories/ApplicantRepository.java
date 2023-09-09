package com.upc.german_embassy.repositories;

import com.upc.german_embassy.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long> {
    public Applicant findApplicantByDni(String dni);

    public List<Applicant> findAllByOrderByNameAsc();
}
