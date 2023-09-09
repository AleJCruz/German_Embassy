package com.upc.german_embassy.controller;

import com.upc.german_embassy.dto.ApplicantDTO;
import com.upc.german_embassy.entities.Applicant;
import com.upc.german_embassy.interfaceservice.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @PostMapping("/applicant")
    public ResponseEntity<ApplicantDTO> register(@RequestBody ApplicantDTO applicantDTO){
        Applicant applicant;
        ApplicantDTO dto;
        try{
            applicant = convertToEntity(applicantDTO);
            applicant = applicantService.registerApplicant(applicant);
            dto = convertToDTO(applicant);

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido registrar");
        }
        return new ResponseEntity<ApplicantDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/applicants")
    public ResponseEntity<List<ApplicantDTO>> listApplicants(){
        List<Applicant> list;
        List<ApplicantDTO> listDTO=null;
        try {
            list = applicantService.listApplicants();
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    @PutMapping("/applicant")
    public ResponseEntity<ApplicantDTO> updateApplicant(@RequestBody ApplicantDTO applicantDTO){
        Applicant applicant;
        ApplicantDTO dto;
        try{
            applicant = convertToEntity(applicantDTO);
            applicant = applicantService.updateApplicant(applicant);
            dto = convertToDTO(applicant);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede actualizar");
        }
        return new ResponseEntity<ApplicantDTO>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/applicant/{id}")
    public ResponseEntity<ApplicantDTO> delete(@PathVariable(value="id") Long id){
        Applicant applicant;
        ApplicantDTO dto;
        try {
            applicant = applicantService.deleteApplicant(id);
            dto = convertToDTO(applicant);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el curso");
        }
        return new ResponseEntity<ApplicantDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/applicant/singles")
    public ResponseEntity<List<ApplicantDTO>> listSinglesApplicants(){
        List<Applicant> list;
        List<ApplicantDTO> listDTO=null;
        try {
            list = applicantService.listSingleApplicants();
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    @PutMapping("/applicant/update/{id}")
    public ResponseEntity<ApplicantDTO> updateApplicantByid(@PathVariable(value="id") Long id){
        Applicant applicant;
        ApplicantDTO dto;
        try {
            applicant = applicantService.updateById(id);
            dto = convertToDTO(applicant);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el curso");
        }
        return new ResponseEntity<ApplicantDTO>(dto, HttpStatus.OK);
    }


    @GetMapping("/applicant/qualified/{dni}")
    public String applicantIsEligible(@PathVariable(value="dni")String dni){
        String mensaje;
        try {
            mensaje = applicantService.applicantIsEligible(dni);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el aplicante");
        }
        return mensaje;
    }

    @GetMapping("/applicants/orderbyname")
    public ResponseEntity<List<ApplicantDTO>> listApplicantsWithQualificationOrderByName(){
        List<Applicant> list;
        List<ApplicantDTO> listDTO=null;
        try {
            list = applicantService.listApplicantsOrderByName();
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    @GetMapping("/applicants/average")
    public String getAverage_EligibleApplicants(){
        String mensaje;
        try {
            mensaje = applicantService.getAverage_EligibleApplicants();
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "No se encontró la lista");
        }
        return mensaje;
    }

    private Applicant convertToEntity(ApplicantDTO applicantDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(applicantDTO, Applicant.class);
    }

    private ApplicantDTO convertToDTO(Applicant applicant){
        ModelMapper modelMapper =new ModelMapper();
        return modelMapper.map(applicant, ApplicantDTO.class);
    }

    private List<ApplicantDTO> convertToListDTO(List<Applicant> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}

