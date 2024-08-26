package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable{
	
	private static final long serialVersionUID = -906314617963733177L;
	/**
	 * 
	 */
	private String id_cita;
	private String nombre;
	private Date fecha_cita;
	private boolean asistencia;
	private Medico id_medico;
	private Tipo_Cita id_tipo_cita;
	private Paciente paciente;
	private Secretario secretario;
	
	

	public Cita(String id_cita, String nombre, Date fecha_cita, boolean asistencia, Medico id_medico,
			Tipo_Cita id_tipo_cita, Paciente paciente, Secretario secretario) {
		super();
		this.id_cita = id_cita;
		this.nombre = nombre;
		this.fecha_cita = fecha_cita;
		this.asistencia = asistencia;
		this.id_medico = id_medico;
		this.id_tipo_cita = id_tipo_cita;
		this.paciente = paciente;
		this.secretario = secretario;
	}



	public String getId_cita() {
		return id_cita;
	}



	public void setId_cita(String id_cita) {
		this.id_cita = id_cita;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Date getFecha_cita() {
		return fecha_cita;
	}



	public void setFecha_cita(Date fecha_cita) {
		this.fecha_cita = fecha_cita;
	}



	public boolean isAsistencia() {
		return asistencia;
	}



	public void setAsistencia(boolean asistencia) {
		this.asistencia = asistencia;
	}



	public Medico getId_medico() {
		return id_medico;
	}



	public void setId_medico(Medico id_medico) {
		this.id_medico = id_medico;
	}



	public Tipo_Cita getId_tipo_cita() {
		return id_tipo_cita;
	}



	public void setId_tipo_cita(Tipo_Cita id_tipo_cita) {
		this.id_tipo_cita = id_tipo_cita;
	}



	public Paciente getPaciente() {
		return paciente;
	}



	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}



	public Secretario getSecretario() {
		return secretario;
	}



	public void setSecretario(Secretario secretario) {
		this.secretario = secretario;
	}

	
	
	

}