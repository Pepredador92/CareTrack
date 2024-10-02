package com.patientRegistrationData.patientRegistration.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientRegistrationData.patientRegistration.model.Paciente;
import com.patientRegistrationData.patientRegistration.service.PacienteService;



@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;

	@PostMapping("/registrar")
	public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
		Paciente nuevoPaciente = pacienteService.registrarPaciente(paciente);
		return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Paciente>> obtenerPaciente() {
		List<Paciente> pacientes = pacienteService.obtenerTodos();
		return new ResponseEntity<>(pacientes, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
		Optional<Paciente> paciente = pacienteService.obtenerPorId(id);
		return paciente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetalles){
		try {
			Paciente pacienteActualizado = pacienteService.actualizarPaciente(id, pacienteDetalles);
			return new ResponseEntity<>(pacienteActualizado, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Paciente> eliminarPaciente(@PathVariable Long id){
		try {
			pacienteService.eliminarPaciente(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
