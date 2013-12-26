package modules.intranet.servicio;

import modules.horario.domain.SilaboNotaAlumno;

public class AlumnoIntranetNota {
	String capacidadTerminal;
	SilaboNotaAlumno silaboNotaAlumno;
	public AlumnoIntranetNota(String capacidadTerminal,
			SilaboNotaAlumno silaboNotaAlumno) {
		this.capacidadTerminal = capacidadTerminal;
		this.silaboNotaAlumno = silaboNotaAlumno;
	}
	
	
	public SilaboNotaAlumno getSilaboNotaAlumno() {
		return silaboNotaAlumno;
	}
	public void setSilaboNotaAlumno(SilaboNotaAlumno silaboNotaAlumno) {
		this.silaboNotaAlumno = silaboNotaAlumno;
	}


	public String getCapacidadTerminal() {
		return capacidadTerminal;
	}


	public void setCapacidadTerminal(String capacidadTerminal) {
		this.capacidadTerminal = capacidadTerminal;
	}
	
	
}
