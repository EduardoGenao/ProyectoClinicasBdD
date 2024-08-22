package logico;

import java.io.Serializable;
import java.util.Date;

public class Secretario implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int id_secretario;
	private String telefono_trabajo;
	private Cuenta cuenta;
	private Persona persona;
	
	
	
	public Secretario(int id_secretario, String telefono_trabajo, Cuenta cuenta, Persona persona) {
		super();
		this.id_secretario = id_secretario;
		this.telefono_trabajo = telefono_trabajo;
		this.cuenta = cuenta;
		this.persona = persona;
	}
	
	
	
	public int getId_secretario() {
		return id_secretario;
	}
	public void setId_secretario(int id_secretario) {
		this.id_secretario = id_secretario;
	}
	public String getTelefono_trabajo() {
		return telefono_trabajo;
	}
	public void setTelefono_trabajo(String telefono_trabajo) {
		this.telefono_trabajo = telefono_trabajo;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	/**
	 * 
	 */
	
	
}
