package logico;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6618491932439092308L;
	private int id_persona;
	private String nombre;
	private String apellido;
	private Date fechaDeNacim;
	private String direccion;
	private String sexo;
	private String telefono;
	private String cedula;
	
	public Persona(int id_persona, String nombre, String apellido, Date fechaDeNacim, String direccion, String sexo, String telefono, String cedula) {
		super();
		this.id_persona = id_persona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacim = fechaDeNacim;
		this.direccion = direccion;
		this.sexo = sexo;
		this.telefono = telefono;
		this.cedula = cedula;
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

	public Date getFechaDeNacim() {
		return fechaDeNacim;
	}

	public void setFechaDeNacim(Date fechaDeNacim) {
		this.fechaDeNacim = fechaDeNacim;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String string) {
		this.sexo = string;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId_persona() {
		return id_persona;
	}

	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	

}
