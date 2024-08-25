package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Enfermedad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7931950808673881944L;
	/**
	 * 
	 */
	private String id_enfermedad;
	private String nombre;
	private String descripcion;
	private boolean permanente;
	
	public Enfermedad(String id_enfermedad, String nombre, String descripcion, boolean permanente) {
		super();
		this.id_enfermedad = id_enfermedad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.permanente = permanente;
	}
	public String getId() {
		return id_enfermedad;
	}
	public void setId(String id) {
		this.id_enfermedad = id_enfermedad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isPermanente() {
		return permanente;
	}
	public void setPermanente(boolean permanente) {
		this.permanente = permanente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}