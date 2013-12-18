package modules.horario.servicio;

import modules.admision.domain.Persona;
import modules.horario.domain.SilaboAlumno;

public class PersonaAlumno {
	
	private Persona persona;
	private SilaboAlumno silaboAlumno;
	
	private double nota;
	
	public PersonaAlumno(Persona persona, SilaboAlumno silaboAlumno) {
		this.persona = persona;
		this.silaboAlumno = silaboAlumno;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public SilaboAlumno getSilaboAlumno() {
		return silaboAlumno;
	}
	public void setSilaboAlumno(SilaboAlumno silaboAlumno) {
		this.silaboAlumno = silaboAlumno;
	}
	
	
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}

}
