package modelo;

import java.sql.Date;

public class Huespedes {

	private Integer id;
	private String nombre, apellido;
	private Date fechaDeNacimiento;
	private String nacionalidad, telefono;
	private Integer idReserva;
	
	public Huespedes(String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public Integer getIdReserva() {
		return idReserva;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return String.format("id: %s, nombre: %s, apellido: %s, reserva: %s}",
				this.id,
				this.nombre,
				this.apellido,
				this.idReserva);
	}
	
	
}
