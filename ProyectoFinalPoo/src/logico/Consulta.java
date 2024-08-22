package logico;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4893965540895230589L;
	/**
	 * 
	 */
	private String id_consulta; // id_consulta
	private Paciente paciente;
	private Medico medico;
	private Secretario secretario;
	private String descripcion;
	private Date fechaConsulta;
	private Enfermedad diagnostico;
	private String tratamiento;
	private Vacuna_Dosis vacuna_dosis;
	private boolean asistencia;
	
	
	public Consulta(String codigo, Paciente paciente, Medico medico, Secretario secretario, String descripcion, Date fechaConsulta,
			Enfermedad diagnostico, String tratamiento, Vacuna_Dosis vacuna_dosis, boolean asistencia) {
		super();
		this.id_consulta = codigo;
		this.paciente = paciente;
		this.medico = medico;
		this.secretario = secretario;
		this.descripcion = descripcion;
		this.fechaConsulta = fechaConsulta;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
		this.vacuna_dosis = vacuna_dosis;
		this.asistencia = asistencia;
	}
	public String getId_consulta() {
		return id_consulta;
	}
	public void setId_consulta(String codigo) {
		this.id_consulta = codigo;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaConsulta() {
		return fechaConsulta;
	}
	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	public Enfermedad getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(Enfermedad diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	public Vacuna_Dosis getVacuna_Dosis() {
		return vacuna_dosis;
	}
	public void setVacuna_Dosis(Vacuna_Dosis vacuna_dosis) {
		this.vacuna_dosis = vacuna_dosis;
	}
	public boolean isAsistencia() {
		return asistencia;
	}
	public void setAsistencia(boolean asistencia) {
		this.asistencia = asistencia;
	}
	public Secretario getSecretario() {
		return secretario;
	}
	public void setSecretario(Secretario secretario) {
		this.secretario = secretario;
	}

}
