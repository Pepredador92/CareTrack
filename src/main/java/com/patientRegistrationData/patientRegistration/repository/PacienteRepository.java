package com.patientRegistrationData.patientRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientRegistrationData.patientRegistration.model.Paciente;



@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
