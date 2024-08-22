package logico;

import java.io.Serializable;

public class Cuenta implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2273878296121320019L;
	/**
	 * 
	 */
	
	private String id_cuenta;
	private String usuario;
	private String contrasena;
	
	
	public Cuenta(String id_cuenta, String usuario, String contrasena) {
		super();
		this.id_cuenta = id_cuenta;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	
	public String getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(String id_cuenta) {
		this.id_cuenta = id_cuenta;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	
	

}
