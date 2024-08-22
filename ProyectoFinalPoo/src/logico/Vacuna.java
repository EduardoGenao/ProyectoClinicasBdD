package logico;

import java.io.Serializable;
import java.util.Date;

public class Vacuna implements Serializable{
	private static final long serialVersionUID = 4340160188790802370L;
	/**
	 * 
	 */
	private String id_vacuna;
	private String nombre;
	private String  descrip;
	private int antMiligramos;
	private Date fechaRecibida;
	
	public Vacuna(String id_vacuna, String nombre, String descrip, int antMiligramos) {
		super();
		this.id_vacuna = id_vacuna;
		this.nombre = nombre;
		this.descrip = descrip;
		this.antMiligramos = antMiligramos;
		fechaRecibida =  new Date();
	}
	
	public String getId_vacuna() {
		return id_vacuna;
	}
	public void setId_vacuna(String id_vacuna) {
		this.id_vacuna = id_vacuna;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public int getAntMiligramos() {
		return antMiligramos;
	}
	public void setAntMiligramos(int antMiligramos) {
		this.antMiligramos = antMiligramos;
	}
	public Date getFechaRecibida() {
		return fechaRecibida;
	}
	public void setFechaRecibida(Date fechaRecibida) {
		this.fechaRecibida = fechaRecibida;
	}
	
	
	

}