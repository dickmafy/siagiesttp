package modules.intranet.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import modules.admision.domain.Matricula;
import modules.horario.domain.AsistenciaAlumnoCalendario;
import modules.horario.domain.SilaboAlumno;


public class AsistenciaWrapper implements Serializable
{
	
	private Matricula matricula;
	private AsistenciaAlumnoCalendario asistenciaAlumnoCalendario;
	private SilaboAlumno silaboAlumno;
	private List<Matricula> listMatricula;
	
	
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	public AsistenciaAlumnoCalendario getAsistenciaAlumnoCalendario() {
		return asistenciaAlumnoCalendario;
	}
	public void setAsistenciaAlumnoCalendario(
			AsistenciaAlumnoCalendario asistenciaAlumnoCalendario) {
		this.asistenciaAlumnoCalendario = asistenciaAlumnoCalendario;
	}
	public SilaboAlumno getSilaboAlumno() {
		return silaboAlumno;
	}
	public void setSilaboAlumno(SilaboAlumno silaboAlumno) {
		this.silaboAlumno = silaboAlumno;
	}
	public List<Matricula> getListMatricula() {
		return listMatricula;
	}
	public void setListMatricula(List<Matricula> listMatricula) {
		this.listMatricula = listMatricula;
	}
	
	
	
	
}
	