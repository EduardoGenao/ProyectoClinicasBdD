package logico;

import java.io.Serializable;

public class Vacuna_Dosis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6448717151653029575L;
	
	private int vacuna_dosis_id;
	private int cant_miligramos;
	private Vacuna vacuna;
	private Consulta consulta;
	public Vacuna_Dosis(int vacuna_dosis_id, int cant_miligramos, Vacuna vacuna, Consulta consulta) {
		super();
		this.vacuna_dosis_id = vacuna_dosis_id;
		this.cant_miligramos = cant_miligramos;
		this.vacuna = vacuna;
		this.consulta = consulta;
	}
	public int getCant_miligramos() {
		return cant_miligramos;
	}
	public void setCant_miligramos(int cant_miligramos) {
		this.cant_miligramos = cant_miligramos;
	}
	public Vacuna getVacuna() {
		return vacuna;
	}
	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public int getVacuna_dosis_id() {
		return vacuna_dosis_id;
	}
	public void setVacuna_dosis_id(int vacuna_dosis_id) {
		this.vacuna_dosis_id = vacuna_dosis_id;
	}
	
	
}
