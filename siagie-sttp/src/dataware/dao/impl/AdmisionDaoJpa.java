package dataware.dao.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import modules.administracion.domain.Oferta;
import modules.admision.domain.Interes;
import modules.admision.domain.Matricula;
import modules.admision.domain.MatriculaSeccion;
import modules.admision.domain.Persona;
import modules.admision.domain.Postulante;
import modules.admision.domain.Proceso;
import modules.admision.domain.ProcesoCronograma;
import modules.admision.domain.ProcesoOferta;
import modules.admision.domain.Requisitos;
import modules.cetpro.domain.CetproMatricula;
import modules.cetpro.domain.CetproMatriculaAlumno;
import modules.horario.domain.AsistenciaAlumno;
import modules.horario.domain.AsistenciaDocente;
import modules.horario.domain.HorarioDistribucion;
import modules.horario.domain.Seccion;
import modules.marco.domain.Itinerario;

import org.hibernate.Query;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.support.DaoException;

import dataware.dao.AdmisionDao;

public class AdmisionDaoJpa extends HorarioDaoJpa implements AdmisionDao 
{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	public Persona encontrarPersona(Long docTip, String docNro) throws Exception 
	{
		List<Persona> lista=new ArrayList<Persona>();
		Query consulta=createQuery("SELECT lst.* FROM admision.m_persona lst WHERE lst.documento_nro='"+docNro+"' AND lst.documento_tipo='"+docTip+"' AND lst.estado='"+Constante.ROW_STATUS_ENABLED+"'; ");
		List rst=consulta.list();
		if(rst.size()==0)	{return null;}
		else
		{
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Object[] objetos=(Object[])rst.get(0);
			Persona field=new Persona();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setDocumento_tipo(Long.parseLong(objetos[1].toString()));
			field.setDocumento_nro(objetos[2].toString());
			field.setNombres(objetos[3].toString());
			field.setApellido_paterno(objetos[4].toString());
			field.setApellido_materno(objetos[5].toString());
			field.setGenero(Long.parseLong(objetos[6].toString()));
			field.setEstado_civil(Long.parseLong(objetos[7].toString()));
			field.setNacimiento_lugar(Long.parseLong(objetos[8].toString()));
			field.setNacimiento_fecha(dateFormat.parse(objetos[9].toString()));
			field.setCorreo(objetos[10].toString());
			field.setTelefonos(objetos[11].toString());
			if(objetos[12]!=null)	{field.setCelular(Long.parseLong(objetos[12].toString()));}
			if(objetos[13]!=null)	{field.setUbigeo(Long.parseLong(objetos[13].toString()));}
			if(objetos[14]!=null)	{field.setDireccion(objetos[14].toString());}
			if(objetos[15]!=null)	{field.setReferencia(objetos[15].toString());}
			if(objetos[16]!=null)	{field.setColegio_tipo(Long.parseLong(objetos[16].toString()));}
			if(objetos[17]!=null)	{field.setColegio(objetos[17].toString());}
			if(objetos[18]!=null)	{field.setApox_vinculo(Long.parseLong(objetos[18].toString()));}
			if(objetos[19]!=null)	{field.setApox_dni(objetos[19].toString());}
			if(objetos[20]!=null)	{field.setApox_nombres(objetos[20].toString());}
			if(objetos[21]!=null)	{field.setApoy_vinculo(Long.parseLong(objetos[21].toString()));}
			if(objetos[22]!=null)	{field.setApoy_dni(objetos[22].toString());}
			if(objetos[23]!=null)	{field.setApoy_nombres(objetos[23].toString());}
			field.setEstado(Long.parseLong(objetos[24].toString()));
			return field;
		}
	}
	
	public void insertarIntereses(List<Oferta> lista, Long pk_interesado) throws Exception
	{
		StringBuilder query=new StringBuilder();
		query.append("INSERT INTO admision.m_interes(\"pk_interesado\",\"pk_profesion\") VALUES ");
		for(int i=0; i<lista.size(); i++)
		{
			if(lista.get(i).getCheck())
			{query.append("('"+pk_interesado+"','"+lista.get(i).getProfesion()+"'),");}
		}
		executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
		query=null;
	}
	
	public List<Interes> obtenerIntereses(Long interesado) throws Exception
	{
		List<Interes> lista=new ArrayList<Interes>();
		Query consulta=createQuery("SELECT * FROM admision.lst_interes(:interesado);");
		consulta.setParameter("interesado", Integer.parseInt(interesado.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Interes field=new Interes();
			field.setPk_interesado(Long.parseLong(objetos[0].toString()));
			field.setPk_profesion(Long.parseLong(objetos[1].toString()));
			field.setNombreProfesion(objetos[2].toString());
			lista.add(field);
		}
		dateFormat=null;
		return lista;
	}
	
	public List<Proceso> listarProcesos(Long institucion, Long annio) throws Exception 
	{
		List<Proceso> lista=new ArrayList<Proceso>();
		Query consulta=createQuery("SELECT * FROM admision.lst_proceso(:institucion,:annio);");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Proceso field=new Proceso();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setInstitucion(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setAnnio(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setProceso(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setCosto_carpeta(Double.parseDouble(objetos[4].toString()));}
			if(objetos[5]!=null){field.setCosto_inscripcion(Double.parseDouble(objetos[5].toString()));}
			if(objetos[6]!=null){field.setCosto_matreg(Double.parseDouble(objetos[6].toString()));}
			if(objetos[7]!=null){field.setCosto_matext(Double.parseDouble(objetos[7].toString()));}
			if(objetos[8]!=null){field.setFecha(dateFormat.parse(objetos[8].toString()));}
			if(objetos[9]!=null){field.setRegistrante(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setEstado(Long.parseLong(objetos[10].toString()));}
			if(objetos[11]!=null){field.setTipo(Long.parseLong(objetos[11].toString()));}
			if(objetos[12]!=null){field.setEjecucion(Long.parseLong(objetos[12].toString()));}
			lista.add(field);
		}
		dateFormat=null;
		return lista;
	}
	
	public List<ProcesoCronograma> listarProcesoCronograma(Long proceso) throws Exception 
	{
		List<ProcesoCronograma> lista=new ArrayList<ProcesoCronograma>();
		Query consulta=createQuery("SELECT lst.* FROM admision.m_proceso_cronograma lst WHERE lst.pk_proceso='"+proceso+"';");
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			ProcesoCronograma field=new ProcesoCronograma();
			field.setPk_proceso(Long.parseLong(objetos[0].toString()));
			field.setPk_actividad(Long.parseLong(objetos[1].toString()));
			if(objetos[2]!=null){field.setFecha_inicio(dateFormat.parse(objetos[2].toString()));}
			if(objetos[3]!=null){field.setFecha_fin(dateFormat.parse(objetos[3].toString()));}
			lista.add(field);
		}
		dateFormat=null;
		return lista;
	}
	
	public List<ProcesoOferta> listarProcesoOferta(Long proceso) throws Exception 
	{
		List<ProcesoOferta> lista=new ArrayList<ProcesoOferta>();
		Query consulta=createQuery("SELECT * FROM admision.lst_proceso_oferta(:proceso);");
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			ProcesoOferta field=new ProcesoOferta();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setProceso(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setProfesion(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setTurno(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setNombreProfesion(objetos[4].toString());}
			
			lista.add(field);
		}
		dateFormat=null;
		return lista;
	}
	
	public void actualizarProcesoCronograma(List<ProcesoCronograma> lista, Long proceso) throws Exception 
	{
		StringBuilder query=new StringBuilder();
		query.append("DELETE FROM admision.m_proceso_cronograma WHERE pk_proceso='"+proceso+"'; ");
		query.append("INSERT INTO admision.m_proceso_cronograma(\"pk_proceso\",\"pk_actividad\",\"fecha_inicio\",\"fecha_fin\") VALUES ");
		for(int i=0; i<lista.size(); i++)
		{
			if(lista.get(i).getFecha_inicio()==null && lista.get(i).getFecha_fin()!=null)
			{query.append("('"+proceso+"','"+lista.get(i).getPk_actividad()+"',NULL,'"+lista.get(i).getFecha_fin()+"'),");}
			if(lista.get(i).getFecha_inicio()!=null && lista.get(i).getFecha_fin()==null)
			{query.append("('"+proceso+"','"+lista.get(i).getPk_actividad()+"','"+lista.get(i).getFecha_inicio()+"',NULL),");}
			if(lista.get(i).getFecha_inicio()!=null && lista.get(i).getFecha_fin()!=null)
			{query.append("('"+proceso+"','"+lista.get(i).getPk_actividad()+"','"+lista.get(i).getFecha_inicio()+"','"+lista.get(i).getFecha_fin()+"'),");}
		}
		executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
		query=null;
	}
	
	public List<Postulante> listarPostulante(Long proceso, Long tipo) throws Exception 
	{
		List<Postulante> lista=new ArrayList<Postulante>();
		Query consulta=createQuery("SELECT * FROM admision.lst_proceso_persona(:proceso,:tipo);");
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Postulante field=new Postulante();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setProceso(Long.parseLong(objetos[1].toString()));
			field.setPersona(Long.parseLong(objetos[2].toString()));
			field.setProfesion(Long.parseLong(objetos[3].toString()));
			field.setTurno(Long.parseLong(objetos[4].toString()));
			if(objetos[5]!=null){field.setPago_fecha(dateFormat.parse(objetos[5].toString()));}
			if(objetos[6]!=null){field.setPago_banco(Long.parseLong(objetos[6].toString()));}
			if(objetos[7]!=null){field.setPago_recibo(objetos[7].toString());}
			if(objetos[8]!=null){field.setPuntuacion(Double.parseDouble(objetos[8].toString()));}
			if(objetos[9]!=null){field.setModalidad(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setRegfec(dateFormat.parse(objetos[10].toString()));}
			if(objetos[11]!=null){field.setRegusr(Long.parseLong(objetos[11].toString()));}
			if(objetos[12]!=null){field.setIngreso(Long.parseLong(objetos[12].toString()));}
			if(objetos[13]!=null){field.setEstado(Long.parseLong(objetos[13].toString()));}
			if(objetos[14]!=null){field.setPostulanteDni(objetos[14].toString());}
			if(objetos[15]!=null){field.setPostulanteNombres(objetos[15].toString());}
			if(objetos[16]!=null){field.setPostulanteApellidos(objetos[16].toString()+" "+objetos[17].toString());}
			if(objetos[18]!=null){field.setNombreEspecialidad(objetos[18].toString());}
			lista.add(field);
		}
		dateFormat=null;
		return lista;
	}
	
	public List<Requisitos> listarRequisitos(Long institucion, Long tipo, Long modalidad) throws Exception 
	{
		List<Requisitos> lista=new ArrayList<Requisitos>();
		Query consulta=createQuery("SELECT * FROM admision.lst_proceso_requisito(:institucion,:tipo,:modalidad);");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		consulta.setParameter("modalidad", Integer.parseInt(modalidad.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Requisitos field=new Requisitos();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setInstitucion(Long.parseLong(objetos[1].toString()));
			field.setTipo(Long.parseLong(objetos[2].toString()));
			field.setModalidad(Long.parseLong(objetos[3].toString()));
			field.setRequisito(Long.parseLong(objetos[4].toString()));
			if(objetos[5]!=null){field.setNombreRequisito(objetos[5].toString());}			
			lista.add(field);
		}
		return lista;
	}
	public List<Requisitos> listarRequisitosPostulante(Long postulante, Long institucion, Long modalidad) throws Exception 
	{
		List<Requisitos> lista=new ArrayList<Requisitos>();
		Query consulta=createQuery("SELECT * FROM admision.lst_requisito_postulante(:postulante,:modalidad, :institucion);");
		consulta.setParameter("postulante", Integer.parseInt(postulante.toString()));
		consulta.setParameter("modalidad", Integer.parseInt(modalidad.toString()));
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Requisitos field=new Requisitos();
			field.setRequisito(Long.parseLong(objetos[0].toString()));
			field.setNombreRequisito(objetos[1].toString());
			if(objetos[2]!=null)	{field.setCheck(true);}
			else					{field.setCheck(false);}
			lista.add(field);
		}
		return lista;
	}
	public void insertarRequisitos(boolean proceso, List<Requisitos> lista, Long primaria) throws DaoException 
	{
		boolean flag=false;
		StringBuilder query=new StringBuilder();
		if(proceso)	{query.append("INSERT INTO admision.m_postulante_requisito(\"pk_postulante\",\"pk_requisito\") VALUES ");}
		else		{query.append("INSERT INTO admision.m_matricula_requisito(\"pk_matricula\",\"pk_requisito\") VALUES ");}
		for(int i=0; i<lista.size(); i++)
		{
			if(lista.get(i).getCheck())
			{
				query.append("('"+primaria+"','"+lista.get(i).getRequisito()+"'),");
				flag=true;
			}
		}
		if(flag){executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");}
		query=null;
	}
	
	public void eliminarRequisito(boolean proceso, Long primaria) throws Exception 
	{
		if(proceso)		{executeQueryUpdate("DELETE FROM admision.m_postulante_requisito WHERE pk_postulante='"+primaria+"'; ");}
		else			{executeQueryUpdate("DELETE FROM admision.m_matricula_requisito WHERE pk_matricula='"+primaria+"'; ");}
	}
	
	
	
	
	
	
	public void procesarIngresantes(Long proceso) throws Exception
	{
		StringBuilder query=new StringBuilder();
		List<Postulante> lista=listarPostulante(proceso, 3L);
		query.append("INSERT INTO admision.m_matricula(\"pk_proceso\",\"pk_especialidad\",\"pk_turno\",\"pk_persona\",\"estado\") VALUES ");
		for(int i=0; i<lista.size(); i++)
		{query.append("('"+lista.get(i).getProceso()+"','"+lista.get(i).getProfesion()+"','"+lista.get(i).getTurno()+"','"+lista.get(i).getPersona()+"',0),");}
		executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
		query=null;
	}
	
	public List<Matricula> listarMatricula(Long proceso) throws Exception
	{
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Matricula> lista=new ArrayList<Matricula>();
		Query consulta=createQuery("SELECT * FROM admision.lst_matricula(:proceso);");
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			
			Object[] objetos=(Object[])rst.get(i);
			Matricula field=new Matricula();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setProceso(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setEspecialidad(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setTurno(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setPersona(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setPago_fecha(dateFormat.parse(objetos[5].toString()));}
			if(objetos[6]!=null){field.setPago_banco(Long.parseLong(objetos[6].toString()));}			
			if(objetos[7]!=null){field.setPago_recibo(objetos[7].toString());}
			if(objetos[8]!=null){field.setModalidad(Long.parseLong(objetos[8].toString()));}
			if(objetos[9]!=null){field.setEstado(Long.parseLong(objetos[9].toString()));}
			
			if(objetos[10]!=null){field.setPersonaNombre(objetos[10].toString());}
			if(objetos[11]!=null){field.setPersonaPaterno(objetos[11].toString());}
			if(objetos[12]!=null){field.setPersonaMaterno(objetos[12].toString());}
			if(objetos[13]!=null){field.setPersonaCorreo(objetos[13].toString());}
			if(objetos[14]!=null){field.setPersonaTelefono(objetos[14].toString());}
			if(objetos[15]!=null){field.setPersonaCelular(objetos[15].toString());}
			if(objetos[16]!=null){field.setPersonaDireccion(objetos[16].toString());}
			if(objetos[17]!=null){field.setPersonaDni(objetos[17].toString());}
			if(objetos[18]!=null){field.setNombreEspecialidad(objetos[18].toString());}
						
			lista.add(field);
		}
		dateFormat=null;
		return lista;
	}
	public List<Requisitos> listarRequisitosMatricula(Long matricula, Long institucion, Long modalidad) throws Exception 
	{
		List<Requisitos> lista=new ArrayList<Requisitos>();
		Query consulta=createQuery("SELECT * FROM admision.lst_requisito_matricula(:matricula,:modalidad, :institucion);");
		consulta.setParameter("matricula", Integer.parseInt(matricula.toString()));
		consulta.setParameter("modalidad", Integer.parseInt(modalidad.toString()));
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Requisitos field=new Requisitos();
			field.setRequisito(Long.parseLong(objetos[0].toString()));
			field.setNombreRequisito(objetos[1].toString());
			if(objetos[2]!=null)	{field.setCheck(true);}
			else					{field.setCheck(false);}
			lista.add(field);
		}
		return lista;
	}
	
	

	public void eliminarRequisito(Requisitos bean) throws Exception 
	{executeQueryUpdate("DELETE FROM administracion.m_requisito WHERE pk_requisitos='"+bean.getId()+"'; ");}
	
	
	
	public List<Itinerario> listarUnidadesDisponibles(Long persona, Long institucion, Long profesion, Long modulo, Long tipo) throws Exception
	{
		List<Itinerario> lista=new ArrayList<Itinerario>();
		Query consulta=createQuery("SELECT * FROM horario.lst_unidades(:persona,:institucion,:profesion,:modulo,:tipo);");
		consulta.setParameter("persona", Integer.parseInt(persona.toString()));
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		consulta.setParameter("modulo", Integer.parseInt(modulo.toString()));
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Itinerario field=new Itinerario();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setTitulo(objetos[1].toString());}
			if(objetos[2]!=null){field.setSemestre(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setHorasSemestre(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setHorasTotal(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setCreditos(Double.parseDouble(objetos[5].toString()));}
			lista.add(field);
			objetos=null;
		}
		return lista;
	}
	
	public List<Seccion> listarSecciones(Long institucion, Long annio, Long proceso, Long profesion, Long turno, Long modulo, Long tipoModulo, Long unidad) throws Exception 
	{
		List<Seccion> lista=new ArrayList<Seccion>();
		Query consulta=createQuery("SELECT * FROM horario.lst_secciones(:institucion, :annio, :proceso, :profesion, :turno, :modulo, :tipo, :unidad)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		consulta.setParameter("turno", Integer.parseInt(turno.toString()));
		consulta.setParameter("modulo", Integer.parseInt(modulo.toString()));
		consulta.setParameter("tipo", Integer.parseInt(tipoModulo.toString()));
		consulta.setParameter("unidad", Integer.parseInt(unidad.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Seccion field=new Seccion();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setNombre(objetos[1].toString());}
			lista.add(field);
		}
		return lista;
	}
	
	public boolean validarCruces(Long seccion, Long matricula) throws Exception 
	{
		Query consulta=createQuery("SELECT * FROM horario.validarHorario(:seccion, :matricula)");
		consulta.setParameter("seccion", seccion);
		consulta.setParameter("matricula", Integer.parseInt(matricula.toString()));
		if(consulta.list().size()==0) 	{return true;}
		else							{return false;}
	}
	
	public boolean validarVacantes(Long seccion) throws Exception 
	{
		Query consulta=createQuery("SELECT lst.vacantes, lst.matriculados FROM horario.m_seccion lst WHERE lst.pk_seccion='"+seccion+"'; ");
		List rst=consulta.list();
		if(rst.size()==0)	{return false;}
		else
		{
			Long vacantes=0L, matriculados=0L;
			Object[] objetos=(Object[])rst.get(0);
			if(objetos[0]!=null)	{vacantes=Long.parseLong(objetos[0].toString());}
			if(objetos[1]!=null)	{matriculados=Long.parseLong(objetos[1].toString());}
			if((matriculados+1)>vacantes)
			{return false;}
			return true;
		}
	}
	
	public void actualizarMatricula(boolean tipo, Long seccion, Long matricula, Long persona, Date fecha) throws Exception 
	{
		StringBuilder query=new StringBuilder();
		if(tipo)	
		{
			query.append("UPDATE horario.m_seccion SET matriculados=matriculados+1 WHERE pk_seccion='"+seccion+"'; ");
			query.append("INSERT INTO admision.m_matricula_seccion(\"pk_matricula\",\"pk_seccion\") VALUES ('"+matricula+"','"+seccion+"'); ");			
			executeQueryUpdate(query.toString());
			generarAsistencia(seccion, persona, fecha);
			generarNotas(seccion, persona);
			
		}
		else		
		{
			query.append("UPDATE horario.m_seccion SET matriculados=matriculados-1 WHERE pk_seccion='"+seccion+"'; " );
			query.append("DELETE FROM admision.m_matricula_seccion WHERE pk_seccion='"+seccion+"' AND pk_matricula='"+matricula+"'; ");			
			query.append("DELETE FROM horario.m_asistencia_alumno WHERE pk_seccion='"+seccion+"' AND pk_persona='"+persona+"'; ");
			query.append("DELETE FROM horario.m_record_academico WHERE pk_seccion='"+seccion+"' AND pk_persona='"+persona+"'; ");
			executeQueryUpdate(query.toString());
		}
		query=null;
	}
	
	public List<MatriculaSeccion> listarSeccionesMatricula(Long matricula) throws Exception 
	{
		List<MatriculaSeccion> lista=new ArrayList<MatriculaSeccion>();
		Query consulta=createQuery("SELECT * FROM horario.lst_horario_alumno(:matricula)");
		consulta.setParameter("matricula", Integer.parseInt(matricula.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			MatriculaSeccion field=new MatriculaSeccion();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setSeccion(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setTipo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setModulo(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setNombreModulo(objetos[4].toString());}
			if(objetos[5]!=null){field.setNombreUnidad(objetos[5].toString());}
			if(objetos[6]!=null){field.setNombreSeccion(objetos[6].toString());}
			lista.add(field);
		}
		return lista;
	}
	
	
	
	private void generarAsistencia(Long seccion, Long persona, Date fechaInicio) throws Exception 
	{
		List<AsistenciaAlumno> 		asistencia=new ArrayList<AsistenciaAlumno>();
		List<HorarioDistribucion> 	dias=new ArrayList<HorarioDistribucion>();
		
		Query consulta1=createQuery("SELECT * FROM horario.lst_horario_dias(:seccion)");
		consulta1.setParameter("seccion", seccion);
		List rst1=consulta1.list();
		
		for(int i=0; i<rst1.size(); i++)
		{
			Object[] objetos=(Object[])rst1.get(i);
			HorarioDistribucion field=new HorarioDistribucion();
			if(objetos[0]!=null){field.setSeccion(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setAmbiente(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setDia(Long.parseLong(objetos[2].toString()));}
			dias.add(field);
		}
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fechaInicio);

		Query consulta2=createQuery("SELECT * FROM horario.lst_horario_ambientes(:seccion)");
		consulta2.setParameter("seccion", seccion);
		List rst2=consulta2.list();
		
		for(int i=0; i<rst2.size(); i++)
		{
			if(rst2.get(i)!=null)
			{
				for(int k=0; k<126; k++)
				{
					if(k==0)	{cal.add(Calendar.DATE, 0);}
					else		{cal.add(Calendar.DATE, 1);}
					asistencia=checkDia(cal.getTime(), Long.parseLong(rst2.get(i).toString()), persona, dias, asistencia, seccion);
				}
			}
		}
		insertarAsistencia(asistencia);
	}
	
	private List<AsistenciaAlumno> checkDia(Date fecha, Long ambiente, Long persona, List<HorarioDistribucion> dias, List<AsistenciaAlumno> asistencia, Long seccion)
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		Long nroDia=Long.parseLong(cal.get(Calendar.DAY_OF_WEEK)+"")+1L;
		
		for(int i=0; i<dias.size(); i++)
		{
			HorarioDistribucion temp=dias.get(i);
			if(temp.getAmbiente().longValue()==ambiente.longValue() && temp.getDia().longValue()==nroDia.longValue())
			{
				AsistenciaAlumno asis=new AsistenciaAlumno();
				asis.setFecha(fecha);
				asis.setId(seccion);
				asis.setAmbiente(ambiente);
				asis.setPersona(persona);
				asis.setEstado(0L);
				asistencia.add(asis);
			}
			temp=null;
		}
		cal=null;
		return asistencia;
	}
	
	private void insertarAsistencia(List<AsistenciaAlumno> asistencia) throws Exception
	{
		StringBuilder query=new StringBuilder();
		query.append("INSERT INTO horario.m_asistencia_alumno(\"pk_persona\",\"pk_seccion\",\"pk_ambiente\",\"pk_fecha\",\"estado\") VALUES ");
		for(int i=0; i<asistencia.size(); i++)
		{query.append("('"+asistencia.get(i).getPersona()+"','"+asistencia.get(i).getId()+"','"+asistencia.get(i).getAmbiente()+"','"+asistencia.get(i).getFecha()+"',0),");}
		if(asistencia.size()>0)
		{executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");}
		query=null;
	}
	
	private void generarNotas(Long seccion, Long persona) throws Exception 
	{
		StringBuilder query=new StringBuilder();
		query.append("INSERT INTO horario.m_record_academico(\"pk_persona\",\"pk_seccion\",\"pk_criterio\",\"procedimental\",\"actitudinal\",\"conceptual\",\"promedio\",\"estado\") VALUES ");
		
		Query consulta=createQuery("SELECT lst.pk_criterio FROM horario.m_silabo_nota lst WHERE lst.pk_seccion='"+seccion+"'; ");
		List rst=consulta.list();
		if(rst.size()>0)
		{
			for(int i=0; i<rst.size(); i++)
			{
				if(rst.get(i)!=null)
				{query.append("('"+persona+"','"+seccion+"','"+rst.get(i)+"',0,0,0,0,0),");}
			}
			executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
		}
		query=null;
		consulta=null;
		rst=null;
	}
	  
	public void iniciarClases(Date fechaInicio, Long proceso) throws Exception 
	{
		Query consulta=createQuery("SELECT * FROM horario.lst_seccionxproceso(:proceso)");
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			if(objetos[0]!=null)
			{generarAsistenciaDocente(Long.parseLong(objetos[0].toString()), fechaInicio);}
		}
	}
	
	private void generarAsistenciaDocente(Long seccion, Date fechaInicio) throws Exception 
	{
		List<AsistenciaDocente> 	asistencia=new ArrayList<AsistenciaDocente>();
		List<HorarioDistribucion> 	dias=new ArrayList<HorarioDistribucion>();
		
		Query consulta1=createQuery("SELECT * FROM horario.lst_horario_dias(:seccion)");
		consulta1.setParameter("seccion", seccion);
		List rst1=consulta1.list();
		
		for(int i=0; i<rst1.size(); i++)
		{
			Object[] objetos=(Object[])rst1.get(i);
			HorarioDistribucion field=new HorarioDistribucion();
			if(objetos[0]!=null){field.setSeccion(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setAmbiente(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setDia(Long.parseLong(objetos[2].toString()));}
			dias.add(field);
		}
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fechaInicio);

		Query consulta2=createQuery("SELECT * FROM horario.lst_horario_ambientes(:seccion)");
		consulta2.setParameter("seccion", seccion);
		List rst2=consulta2.list();
		
		for(int i=0; i<rst2.size(); i++)
		{
			if(rst2.get(i)!=null)
			{
				for(int k=0; k<126; k++)
				{
					if(k==0)	{cal.add(Calendar.DATE, 0);}
					else		{cal.add(Calendar.DATE, 1);}
					asistencia=checkDiaDocente(cal.getTime(), Long.parseLong(rst2.get(i).toString()), dias, asistencia, seccion);
				}
			}
		}
		insertarAsistenciaDocente(asistencia);
	}
	
	private List<AsistenciaDocente> checkDiaDocente(Date fecha, Long ambiente, List<HorarioDistribucion> dias, List<AsistenciaDocente> asistencia, Long seccion)
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		Long nroDia=Long.parseLong(cal.get(Calendar.DAY_OF_WEEK)+"")+1L;
		
		for(int i=0; i<dias.size(); i++)
		{
			HorarioDistribucion temp=dias.get(i);
			if(temp.getAmbiente().longValue()==ambiente.longValue() && temp.getDia().longValue()==nroDia.longValue())
			{
				AsistenciaDocente asis=new AsistenciaDocente();
				asis.setFecha(fecha);
				asis.setSeccion(seccion);
				asis.setAmbiente(ambiente);
				asis.setEstado(0L);
				asistencia.add(asis);
			}
			temp=null;
		}
		cal=null;
		return asistencia;
	}
	
	private void insertarAsistenciaDocente(List<AsistenciaDocente> asistencia) throws Exception
	{
		StringBuilder query=new StringBuilder();
		query.append("INSERT INTO horario.m_asistencia_docente(\"pk_seccion\",\"pk_ambiente\",\"pk_fecha\",\"estado\") VALUES ");
		for(int i=0; i<asistencia.size(); i++)
		{query.append("('"+asistencia.get(i).getSeccion()+"','"+asistencia.get(i).getAmbiente()+"','"+asistencia.get(i).getFecha()+"',0),");}
		if(asistencia.size()>0)
		{executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");}
		query=null;
	}
	
	@SuppressWarnings("rawtypes")
	public List<CetproMatricula> listarUnidadesCetpro(Long anno,Long modulo) throws Exception
	{
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<CetproMatricula> lista=new ArrayList<CetproMatricula>();
		Query consulta=createQuery("SELECT * FROM cetpro.lst_unidadesoferta(:anno, :modulo);");
		consulta.setParameter("anno", Integer.parseInt(anno.toString()));
		consulta.setParameter("modulo", Integer.parseInt(modulo.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			
			Object[] objetos=(Object[])rst.get(i);
			CetproMatricula field=new CetproMatricula();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setPk_docente(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setPk_modulo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setPk_familia(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setApepat(objetos[4].toString());}
			if(objetos[5]!=null){field.setApemat(objetos[5].toString());}
			if(objetos[6]!=null){field.setNom(objetos[6].toString());}
			if(objetos[7]!=null){field.setNomModulo(objetos[7].toString());}
			if(objetos[8]!=null){field.setNomFamilia(objetos[8].toString());}
			if(objetos[9]!=null){field.setFecha_ini(dateFormat.parse(objetos[9].toString()));}
			if(objetos[10]!=null){field.setFecha_fin(dateFormat.parse(objetos[10].toString()));}	
			if(objetos[11]!=null){field.setAnno(Long.parseLong(objetos[11].toString()));}
			if(objetos[12]!=null){field.setTurno(Long.parseLong(objetos[12].toString()));}
			if(objetos[13]!=null){field.setEstado(Long.parseLong(objetos[13].toString()));}
			if(objetos[14]!=null){field.setProfesion(Long.parseLong(objetos[14].toString()));}
			if(objetos[15]!=null){field.setModulo(Long.parseLong(objetos[15].toString()));}
						
			lista.add(field);
		}
		dateFormat=null;
		return lista;
	}
	
	public List<CetproMatriculaAlumno> listarAlumnosMatricula(Long matricula) throws Exception 
	{
		List<CetproMatriculaAlumno> lista=new ArrayList<CetproMatriculaAlumno>();
		Query consulta=createQuery("SELECT * FROM cetpro.lst_alumnosmatricula(:matricula)");
		consulta.setParameter("matricula", Integer.parseInt(matricula.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			CetproMatriculaAlumno field=new CetproMatriculaAlumno();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setPk_persona(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setAlumno_dni(objetos[2].toString());}
			if(objetos[3]!=null){field.setAlumno_apepat(objetos[3].toString());}
			if(objetos[4]!=null){field.setAlumno_apemat(objetos[4].toString());}
			if(objetos[5]!=null){field.setAlumno_nom(objetos[5].toString());}
			lista.add(field);
		}
		return lista;
	}
	
	public void actualizarMatriculaCetpro(boolean tipo, Long matricula, Long alumno) throws Exception 
	{
		StringBuilder query=new StringBuilder();
		if(tipo)	
		{
			//query.append("UPDATE horario.m_seccion SET matriculados=matriculados+1 WHERE pk_seccion='"+seccion+"'; ");
			query.append("INSERT INTO cetpro.m_cetpro_matricula_alumno(\"pk_cetpro_matricula\",\"pk_persona\",\"estado\") VALUES ('"+matricula+"','"+alumno+"','"+1+"'); ");			
			executeQueryUpdate(query.toString());
			//generarAsistencia(seccion, persona, fecha);
			//generarNotas(seccion, persona);
		}
		else		
		{
			//query.append("UPDATE horario.m_seccion SET matriculados=matriculados-1 WHERE pk_seccion='"+seccion+"'; " );
			query.append("DELETE FROM cetpro.m_cetpro_matricula_alumno WHERE pk_cetpro_matricula='"+matricula+"' AND pk_persona='"+alumno+"'; ");			
			//query.append("DELETE FROM horario.m_asistencia_alumno WHERE pk_seccion='"+seccion+"' AND pk_persona='"+persona+"'; ");
			//query.append("DELETE FROM horario.m_record_academico WHERE pk_seccion='"+seccion+"' AND pk_persona='"+persona+"'; ");
			executeQueryUpdate(query.toString());
		}
		query=null;
	}
	
	public List<Persona> listarInteresados(Long institucion) throws Exception 
	{
		List<Persona> lista=new ArrayList<Persona>();
		Query consulta=createQuery("SELECT * FROM cetpro.lst_interesados(:institucion)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Persona field=new Persona();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setNombres(objetos[1].toString());}
			if(objetos[2]!=null){field.setApellido_paterno(objetos[2].toString());}
			if(objetos[3]!=null){field.setApellido_materno(objetos[3].toString());}
			lista.add(field);
		}
		return lista;
	}
	
	public List<CetproMatricula> listarUnidadesDocenteAlumno(Long anno,Long tipo,Long id) throws Exception
	{
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<CetproMatricula> lista=new ArrayList<CetproMatricula>();
		Query consulta=createQuery("SELECT * FROM cetpro.lst_unidadesdocentealumno(:anno, :tipo, :id);");
		consulta.setParameter("anno", Integer.parseInt(anno.toString()));
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		consulta.setParameter("id", Integer.parseInt(id.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			
			Object[] objetos=(Object[])rst.get(i);
			CetproMatricula field=new CetproMatricula();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setPk_docente(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setPk_modulo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setPk_familia(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setApepat(objetos[4].toString());}
			if(objetos[5]!=null){field.setApemat(objetos[5].toString());}
			if(objetos[6]!=null){field.setNom(objetos[6].toString());}
			if(objetos[7]!=null){field.setNomModulo(objetos[7].toString());}
			if(objetos[8]!=null){field.setNomFamilia(objetos[8].toString());}
			if(objetos[9]!=null){field.setFecha_ini(dateFormat.parse(objetos[9].toString()));}
			if(objetos[10]!=null){field.setFecha_fin(dateFormat.parse(objetos[10].toString()));}	
			if(objetos[11]!=null){field.setAnno(Long.parseLong(objetos[11].toString()));}
			if(objetos[12]!=null){field.setTurno(Long.parseLong(objetos[12].toString()));}
			if(objetos[13]!=null){field.setEstado(Long.parseLong(objetos[13].toString()));}
			if(objetos[14]!=null){field.setProfesion(Long.parseLong(objetos[14].toString()));}
			
			lista.add(field);
		}
		dateFormat=null;
		return lista;
	}
}

