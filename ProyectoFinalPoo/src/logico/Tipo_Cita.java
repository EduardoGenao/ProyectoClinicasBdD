package logico;

import java.io.Serializable;

public class Tipo_Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4327936467872436807L;
	private String id_tipo;
	private String nombre;
	
	public Tipo_Cita(String id_tipo, String nombre) {
		super();
		this.id_tipo = id_tipo;
		this.nombre = nombre;
	}

	public String getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(String id_tipo) {
		this.id_tipo = id_tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
