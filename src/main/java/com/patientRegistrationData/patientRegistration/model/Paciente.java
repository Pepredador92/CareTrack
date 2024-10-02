package com.patientRegistrationData.patientRegistration.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "paciente")
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nombre;
	
	@Column(nullable = false)
	private int edad;
	
	@Column(length = 15)
	private String telefono;
	
	@Column(length = 255)
	private String direccion;
	
	@Column(name = "motivo_consulta", length = 255)
	private String motivoConsulta;
	
	@Column(name = "fecha_registro", updatable = false)
	private LocalDate fechaRegistro;
	
	

	public Paciente() {
	}

	public Paciente(Long id, String nombre, int edad, String telefono, String direccion, String motivoConsulta,
			LocalDate fechaRegistro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.direccion = direccion;
		this.motivoConsulta = motivoConsulta;
		this.fechaRegistro = fechaRegistro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	

}
