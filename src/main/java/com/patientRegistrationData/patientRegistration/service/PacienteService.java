package com.patientRegistrationData.patientRegistration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientRegistrationData.patientRegistration.model.Paciente;
import com.patientRegistrationData.patientRegistration.repository.PacienteRepository;



@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public Paciente registrarPaciente(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}
	
	public List<Paciente> obtenerTodos(){
		return pacienteRepository.findAll();
	}
	
	public Optional<Paciente> obtenerPorId(Long id){
		return pacienteRepository.findById(id);
	}
	
	public Paciente actualizarPaciente(Long id, Paciente pacienteDetails) {
		Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
		
		if (optionalPaciente.isPresent()) {
			Paciente pacienteExistente = optionalPaciente.get();
			pacienteExistente.setNombre(pacienteDetails.getNombre());
			pacienteExistente.setEdad(pacienteDetails.getEdad());
			pacienteExistente.setTelefono(pacienteDetails.getTelefono());
			pacienteExistente.setDireccion(pacienteDetails.getDireccion());
			pacienteExistente.setMotivoConsulta(pacienteDetails.getMotivoConsulta());
			return pacienteRepository.save(pacienteExistente);			
		}else {
			throw new RuntimeException("El paciente no existe. " + id);
		}
	}
	
	public void eliminarPaciente(Long id) {
		if (pacienteRepository.existsById(id)) {
			pacienteRepository.deleteById(id);			
		}else {
			throw new RuntimeException("El paciente no existe. " + id);
		}
	}
	
}
