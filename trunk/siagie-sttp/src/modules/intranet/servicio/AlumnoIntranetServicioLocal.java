package modules.intranet.servicio;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Expression;

import com.aprolab.sicad.persistence.JPAPersistenceUtil;

import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboAlumno;
import modules.horario.domain.SilaboCronograma;
import modules.horario.domain.SilaboNotaAlumno;

public class AlumnoIntranetServicioLocal {
	
	public static List<AlumnoIntranet> buscarAlumnoIntranet( Long alumnoId) throws Exception{
		
		List<SilaboAlumno> silaboAlumnos = JPAPersistenceUtil.getSession().createCriteria(SilaboAlumno.class)
				.add(Expression.eq("pk_alumno", alumnoId)).list();
		
		List<AlumnoIntranet> alumnoIntranets = new ArrayList<AlumnoIntranet>();
		
		for (SilaboAlumno silaboAlumno : silaboAlumnos) {
			SilaboCronograma silaboCronograma = (SilaboCronograma) JPAPersistenceUtil.getSession()
					.createCriteria(SilaboCronograma.class)
					.add(Expression.eq("id", silaboAlumno.getPk_silabo_cronograma())).uniqueResult();
			Seccion seccion = (Seccion) JPAPersistenceUtil.getSession()
					.createCriteria(Seccion.class)
					.add(Expression.eq("id", silaboCronograma.getPk_seccion())).uniqueResult();
			seccion.setNombreModulo("Modulo");
			seccion.setNombreUnidad("Computacion");
			
			alumnoIntranets.add(new AlumnoIntranet(seccion, silaboAlumno));
		}

		return alumnoIntranets;
	}
	
	
	public static List<AlumnoIntranetNota> findNotas(Long pk_silabo_alumno){
		List<AlumnoIntranetNota> alumnoIntranetNotas = new ArrayList<AlumnoIntranetNota>();
		List<SilaboNotaAlumno> silaboNotaAlumnos = JPAPersistenceUtil.getSession().createCriteria(SilaboNotaAlumno.class)
				.add(Expression.eq("pk_silabo_alumno", pk_silabo_alumno)).list();
		for (int i = 0; i < silaboNotaAlumnos.size(); i++) {
			alumnoIntranetNotas.add(new AlumnoIntranetNota("Unidad Didactica "+(i+1), silaboNotaAlumnos.get(i)));
		}
		return alumnoIntranetNotas;
	}
}
