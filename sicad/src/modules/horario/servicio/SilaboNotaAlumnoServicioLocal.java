package modules.horario.servicio;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Expression;

import com.aprolab.sicad.persistence.JPAPersistenceUtil;
import com.belogick.factory.util.support.ServiceException;

import modules.admision.domain.Persona;
import modules.horario.domain.SilaboAlumno;
import modules.horario.domain.SilaboNotaAlumno;

public class SilaboNotaAlumnoServicioLocal {
	
	public static SilaboNotaAlumno getSilaboNotaAlumno(Long pk_alumno,Long pk_unidad_ct) throws ServiceException{
		
		SilaboNotaAlumno result = (SilaboNotaAlumno) JPAPersistenceUtil.getSession().createCriteria(SilaboNotaAlumno.class)
				.add(Expression.eq("pk_silabo_alumno",pk_alumno))
				.add(Expression.eq("pk_unidad_ct",pk_unidad_ct)).uniqueResult();
		
		if (result!=null) return result;
		
		result = new SilaboNotaAlumno();
		result.setPk_silabo_alumno(pk_alumno);
		result.setPk_unidad_ct(pk_unidad_ct);
		result.setEstado(1L);
		result.setNota(0.0);
		
		return (SilaboNotaAlumno) JPAPersistenceUtil.getSession().save(result); 
	}
	
	public static List<PersonaAlumno> findBySilaboCronograma(Long silaboCronogramaId,Long pk_unidad_ct) {
		List<PersonaAlumno> matriculados = new ArrayList<PersonaAlumno>();
		List<SilaboAlumno> silaboAlumnos = JPAPersistenceUtil.getSession()
				.createCriteria(SilaboAlumno.class)
				.add(Expression.eq("pk_silabo_cronograma",silaboCronogramaId)).list();
		Persona persona;
		for (SilaboAlumno silaboAlumno : silaboAlumnos) {
			
			persona = (Persona) JPAPersistenceUtil.getSession().createCriteria(Persona.class)
					.add(Expression.eq("id",silaboAlumno.getPk_alumno())).uniqueResult();
			PersonaAlumno personaAlumno = new PersonaAlumno(persona,silaboAlumno);
			
			personaAlumno.setNota(10.0);
			try {
				personaAlumno.setNota(getSilaboNotaAlumno(silaboAlumno.getId(), pk_unidad_ct).getNota());
			} catch (ServiceException e) {
			}
			
			matriculados.add(personaAlumno);
			
		}
		return matriculados;
	}
	
	
	
}
