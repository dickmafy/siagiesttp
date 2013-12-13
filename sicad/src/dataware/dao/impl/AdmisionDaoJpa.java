package dataware.dao.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import modules.administracion.domain.Oferta;
import modules.admision.domain.Interes;
import modules.admision.domain.Matricula;
//import modules.admision.domain.MatriculaSeccion;
import modules.admision.domain.Persona;
import modules.admision.domain.Postulante;
import modules.admision.domain.Proceso;
import modules.admision.domain.ProcesoCronograma;
import modules.admision.domain.ProcesoOferta;
import modules.admision.domain.Requisitos;
import modules.horario.domain.Seccion;

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
	
	public List<Proceso> listarProcesos(Long institucion) throws Exception 
	{
		List<Proceso> lista=new ArrayList<Proceso>();
		Query consulta=createQuery("SELECT * FROM admision.lst_proceso(:institucion);");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
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
			if(objetos[4]!=null){field.setSeccion(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setPersona(Long.parseLong(objetos[5].toString()));}
			if(objetos[6]!=null){field.setPago_fecha(dateFormat.parse(objetos[6].toString()));}
			if(objetos[7]!=null){field.setPago_banco(Long.parseLong(objetos[7].toString()));}			
			if(objetos[8]!=null){field.setPago_recibo(objetos[8].toString());}
			if(objetos[9]!=null){field.setModalidad(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setEstado(Long.parseLong(objetos[10].toString()));}
			
			if(objetos[11]!=null){field.setPersonaNombre(objetos[11].toString());}
			if(objetos[12]!=null){field.setPersonaPaterno(objetos[12].toString());}
			if(objetos[13]!=null){field.setPersonaMaterno(objetos[13].toString());}
			if(objetos[14]!=null){field.setPersonaCorreo(objetos[14].toString());}
			if(objetos[15]!=null){field.setPersonaTelefono(objetos[15].toString());}
			if(objetos[16]!=null){field.setPersonaCelular(objetos[16].toString());}
			if(objetos[17]!=null){field.setPersonaDireccion(objetos[17].toString());}
			if(objetos[18]!=null){field.setPersonaDni(objetos[18].toString());}
			if(objetos[19]!=null){field.setNombreEspecialidad(objetos[19].toString());}
						
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
	
	
	public List<Seccion> listarUnidades(Long institucion, Long annio, Long proceso, Long profesion, Long turno, Long modulo) throws Exception 
	{
		List<Seccion> lista=new ArrayList<Seccion>();
		Query consulta=createQuery("SELECT * FROM admision.lst_unidades(:institucion, :annio, :proceso, :profesion, :turno, :modulo);");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		consulta.setParameter("turno", Integer.parseInt(turno.toString()));
		consulta.setParameter("modulo", Integer.parseInt(turno.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Seccion field=new Seccion();
			if(objetos[0]!=null){field.setValorTipoModulo(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setNombreModulo(objetos[1].toString());}
			if(objetos[2]!=null){field.setValorModulo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setNombreUnidad(objetos[3].toString());}
			if(objetos[4]!=null){field.setValorHoras(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setDocente(Long.parseLong(objetos[5].toString()));}
			if(objetos[6]!=null){field.setNombre(objetos[6].toString());}
			if(objetos[7]!=null){field.setId(Long.parseLong(objetos[7].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public void actualizarMatriculaSeccion(List<Seccion> lista, Long matricula) throws Exception 
	{
		StringBuilder query=new StringBuilder();
		query.append("DELETE FROM admision.m_matricula_seccion WHERE pk_matricula='"+matricula+"'; ");
		query.append("INSERT INTO admision.m_matricula_seccion(\"pk_matricula\",\"pk_seccion\") VALUES ");
		for(int i=0; i<lista.size(); i++)
		{
			{query.append("('"+matricula+"','"+lista.get(i).getId()+"'),");}
		}
		executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
		query=null;
	}
	
//	
//	public List<MatriculaSeccion> listarUnidadesDisponibles(Long persona, Long institucion, Long profesion, Long modulo, Long tipo) throws Exception
//	{
//		List<MatriculaSeccion> lista=new ArrayList<MatriculaSeccion>();
//		Query consulta=createQuery("SELECT * FROM admision.lst_requisito_matricula(:persona,:institucion,:profesion,:modulo,:tipo);");
//		consulta.setParameter("persona", Integer.parseInt(persona.toString()));
//		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
//		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
//		consulta.setParameter("modulo", Integer.parseInt(modulo.toString()));
//		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
//		List rst=consulta.list();
//		for(int i=0; i<rst.size(); i++)
//		{
//			Object[] objetos=(Object[])rst.get(i);
//			MatriculaSeccion field=new MatriculaSeccion();
//			field.setModulo(Long.parseLong(objetos[0].toString()));
//			field.setTipo(Long.parseLong(objetos[1].toString()));
//			field.setUnidad(Long.parseLong(objetos[2].toString()));
//			field.setNombreUnidad(objetos[3].toString());
//			lista.add(field);
//			objetos=null;
//		}
//		return lista;
//	}
}
