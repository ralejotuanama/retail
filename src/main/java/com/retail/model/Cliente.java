package com.retail.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1832722960331921367L;

	@Transient
	final Integer esperanzavida = 76;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;

	@Size(min = 3, message = "Nombre debe tener minimo 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;

	@Size(min = 3, message = "Apellido debe tener minimo 3 caracteres")
	@Column(name = "apellido", nullable = false, length = 70)
	private String apellido;

	private Integer edad;

	@Column(name = "fechanacimiento", nullable = false)
	private LocalDate fechanacimiento;

	@Transient
	private LocalDate fechamuerte;

	public LocalDate getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(LocalDate fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Transient
	public LocalDate getFechamuerte() {
		return getFechanacimiento().plusYears(esperanzavida - getEdad());
	}

	public void setFechamuerte(LocalDate fechamuerte) {
		this.fechamuerte = fechamuerte;
	}

}
