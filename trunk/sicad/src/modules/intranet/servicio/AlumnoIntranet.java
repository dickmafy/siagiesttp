package modules.intranet.servicio;

import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboAlumno;

public class AlumnoIntranet {
	
	Seccion seccion;
	SilaboAlumno silaboAlumno;
	
	public AlumnoIntranet(Seccion seccion, SilaboAlumno silaboAlumno) {
		this.seccion = seccion;
		this.silaboAlumno = silaboAlumno;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public SilaboAlumno getSilaboAlumno() {
		return silaboAlumno;
	}

	public void setSilaboAlumno(SilaboAlumno silaboAlumno) {
		this.silaboAlumno = silaboAlumno;
	}
	
	
	
}
