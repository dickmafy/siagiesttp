package modules.horario.servicio;

import modules.admision.domain.Persona;
import modules.cetpro.domain.CetproMatriculaAlumno;
import modules.horario.domain.SilaboAlumno;

public class PersonaAlumnoCetpro {
	
	private Persona persona;
	private CetproMatriculaAlumno silaboAlumno;
	
	private double nota;
	
	public PersonaAlumnoCetpro(Persona persona, CetproMatriculaAlumno silaboAlumno) {
		this.persona = persona;
		this.silaboAlumno = silaboAlumno;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public CetproMatriculaAlumno getSilaboAlumno() {
		return silaboAlumno;
	}
	public void setSilaboAlumno(CetproMatriculaAlumno silaboAlumno) {
		this.silaboAlumno = silaboAlumno;
	}

}
