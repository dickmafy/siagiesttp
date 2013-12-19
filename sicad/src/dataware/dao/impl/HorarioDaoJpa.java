package dataware.dao.impl;
import java.util.ArrayList;
import java.util.List;

import modules.administracion.domain.Ambiente;
import modules.administracion.domain.AmbienteUnidad;
import modules.administracion.domain.MetaDetalle;
import modules.administracion.domain.MetaInstitucional;
import modules.admision.domain.Matricula;
import modules.horario.domain.HorarioDistribucion;
import modules.horario.domain.Seccion;

import org.hibernate.Query;

import com.belogick.factory.util.support.DaoException;

import dataware.dao.HorarioDao;

public class HorarioDaoJpa extends MarcoDaoJpa implements HorarioDao 
{
	public List<MetaInstitucional> listarMetaInstitucional(Long institucion, Long annio, Long proceso) throws Exception 
	{
		List<MetaInstitucional> lista=new ArrayList<MetaInstitucional>();
		Query consulta=createQuery("SELECT * FROM administracion.lst_meta_institucional(:institucion, :annio, :proceso)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			MetaInstitucional field=new MetaInstitucional();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setInstitucion(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setProfesion(Long.parseLong(objetos[2].toString()));}
			
			if(objetos[3]!=null){field.setAnnio(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setProceso(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setTurno(Long.parseLong(objetos[5].toString()));}
			if(objetos[6]!=null){field.setMeta_matricula(Long.parseLong(objetos[6].toString()));}
			
			if(objetos[7]!=null){field.setMeta_egresados(Long.parseLong(objetos[7].toString()));}
			if(objetos[8]!=null){field.setMeta_titulados(Long.parseLong(objetos[8].toString()));}
			
			if(objetos[9]!=null){field.setResolucion(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setSolicitud(Long.parseLong(objetos[10].toString()));}
			if(objetos[11]!=null){field.setNombreProfesion(objetos[11].toString());}
			if(objetos[12]!=null){field.setEstado(Long.parseLong(objetos[12].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public List<MetaDetalle> listarMetaDetalle(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception 
	{
		List<MetaDetalle> lista=new ArrayList<MetaDetalle>();
		Query consulta=createQuery("SELECT * FROM administracion.lst_meta_detalle(:institucion, :annio, :proceso, :profesion, :turno)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		consulta.setParameter("turno", Integer.parseInt(turno.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			MetaDetalle field=new MetaDetalle();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setMeta(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setTipo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setUnidad(Long.parseLong(objetos[3].toString()));}
			
			if(objetos[4]!=null){field.setIngresantes(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setRegular(Long.parseLong(objetos[5].toString()));}
			if(objetos[6]!=null){field.setRezagados(Long.parseLong(objetos[6].toString()));}
			if(objetos[7]!=null){field.setRetiros(Long.parseLong(objetos[7].toString()));}
			if(objetos[8]!=null){field.setTraslados(Long.parseLong(objetos[8].toString()));}
			if(objetos[9]!=null){field.setMetaTotal(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setMetaAvance(Long.parseLong(objetos[10].toString()));}
			
			if(objetos[11]!=null){field.setResolucion(Long.parseLong(objetos[11].toString()));}
			if(objetos[12]!=null){field.setSolicitud(Long.parseLong(objetos[12].toString()));}
			
			if(objetos[13]!=null){field.setPermiso(Long.parseLong(objetos[13].toString()));}
			if(objetos[14]!=null){field.setCantidad(Long.parseLong(objetos[14].toString()));}
			if(objetos[15]!=null){field.setEstado(Long.parseLong(objetos[15].toString()));}
			
			if(objetos[16]!=null){field.setNombreUnidad(objetos[16].toString());}
			if(objetos[17]!=null){field.setNombreModulo(objetos[17].toString());}
			if(objetos[18]!=null){field.setValorModulo(Long.parseLong(objetos[18].toString()));}
			if(objetos[19]!=null){field.setValorHoras(Long.parseLong(objetos[19].toString()));}
			if(objetos[20]!=null){field.setNombreProfesion(objetos[20].toString());}
			
			lista.add(field);
		}
		return lista;
	}
	
	public List<Seccion> listarSeccion(Long meta) throws Exception 
	{
		List<Seccion> lista=new ArrayList<Seccion>();
		Query consulta=createQuery("SELECT * FROM horario.lst_seccion(:meta)");
		consulta.setParameter("meta", Integer.parseInt(meta.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Seccion field=new Seccion();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setDetalle(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setNombre(objetos[2].toString());}
			
			if(objetos[3]!=null){field.setDocente(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setMetodologia(objetos[4].toString());}
			if(objetos[5]!=null){field.setEvaluacion(objetos[5].toString());}
			if(objetos[6]!=null){field.setRecursos(objetos[6].toString());}
			if(objetos[7]!=null){field.setEstado(Long.parseLong(objetos[7].toString()));}
			
			if(objetos[8]!=null){field.setNombreUnidad(objetos[8].toString());}
			if(objetos[9]!=null){field.setNombreModulo(objetos[9].toString());}
			lista.add(field);
		}
		return lista;
	}
	
	public void insertarDistribucion(List<HorarioDistribucion> lista, Long seccion, Long ambienteOld) throws DaoException 
	{
		StringBuilder query=new StringBuilder();
		if(ambienteOld!=null)	
		{query.append("DELETE FROM horario.m_distribucion WHERE pk_seccion='"+seccion+"' AND pk_ambiente='"+ambienteOld+"'; ");}
		query.append("INSERT INTO horario.m_distribucion(\"pk_seccion\",\"pk_ambiente\",\"dia\",\"hora_inicio\",\"hora_fin\") VALUES ");
		for(int i=0; i<lista.size(); i++)
		{query.append("('"+seccion+"','"+lista.get(i).getAmbiente()+"','"+lista.get(i).getDia()+"','"+lista.get(i).getInicio()+"','"+lista.get(i).getFin()+"'),");}
		executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
		query=null;
	}
	
	public List<HorarioDistribucion> listarDistribucion(Long tipo, Long seccion, Long ambiente) throws Exception 
	{
		List<HorarioDistribucion> lista=new ArrayList<HorarioDistribucion>();
		Query consulta=createQuery("SELECT * FROM horario.lst_distribucion(:tipo,:seccion,:ambiente)");
		consulta.setParameter("tipo",  Integer.parseInt(tipo.toString()));
		consulta.setParameter("seccion", seccion);
		consulta.setParameter("ambiente", Integer.parseInt(ambiente.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			HorarioDistribucion field=new HorarioDistribucion();
			if(objetos[0]!=null){field.setSeccion(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setAmbiente(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setDia(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setInicio(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setFin(Long.parseLong(objetos[4].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public List<Ambiente> listarAmbientes(Long institucion, Long tipo, Long capacidad) throws Exception
	{
		List<Ambiente> lista=new ArrayList<Ambiente>();
		Query consulta=createQuery("SELECT * FROM administracion.lst_ambiente(:institucion,:tipo,:capacidad)");
		consulta.setParameter("institucion",  Integer.parseInt(institucion.toString()));
		consulta.setParameter("tipo",  Integer.parseInt(tipo.toString()));
		consulta.setParameter("capacidad",  Integer.parseInt(capacidad.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Ambiente field=new Ambiente();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setLocal(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setTipo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setCodigo(objetos[3].toString());}
			if(objetos[4]!=null){field.setNombre(objetos[4].toString());}
			if(objetos[5]!=null){field.setArea(Long.parseLong(objetos[5].toString()));}
			if(objetos[6]!=null){field.setObservacion(objetos[6].toString());}
			if(objetos[7]!=null){field.setCapacidad(Long.parseLong(objetos[7].toString()));}
			if(objetos[8]!=null){field.setMinimo(Long.parseLong(objetos[8].toString()));}
			if(objetos[9]!=null){field.setResolucion(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setSolicitud(Long.parseLong(objetos[10].toString()));}
			if(objetos[11]!=null){field.setEstado(Long.parseLong(objetos[11].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public List<AmbienteUnidad> listarAmbienteUnidad(Long institucion, Long seccion, Long unidad) throws Exception
	{
		List<AmbienteUnidad> lista=new ArrayList<AmbienteUnidad>();
		Query consulta=createQuery("SELECT * FROM administracion.lst_ambiente_unidads(:institucion,:seccion, :unidad)");
		consulta.setParameter("institucion",  Integer.parseInt(institucion.toString()));
		consulta.setParameter("seccion",  seccion);
		consulta.setParameter("unidad",  Integer.parseInt(unidad.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			AmbienteUnidad field=new AmbienteUnidad();
			if(objetos[0]!=null){field.setNombreTipo(objetos[0].toString());}
			if(objetos[1]!=null){field.setNombreAmbiente(objetos[1].toString());}
			if(objetos[2]!=null){field.setValorCapacidad(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setHoras(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setValorAmbiente(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setTipo(Long.parseLong(objetos[5].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public void actualizarAvance(Long metaDetalle) throws Exception
	{
		Query consulta=createQuery("SELECT * FROM horario.total_avance(:meta)");
		consulta.setParameter("meta",  Integer.parseInt(metaDetalle.toString()));
		List rst=consulta.list();
		Long cont=0L;
		for(int i=0; i<rst.size(); i++)
		{
			if(rst.get(i)!=null)
			{cont=cont+Long.parseLong(rst.get(i).toString());}
		}
		executeQueryUpdate(" UPDATE administracion.m_meta_detalle SET meta_avance='"+cont+"' WHERE pk_detalle='"+metaDetalle+"';");
		rst=null;
	}
	
	public List<Seccion> listarSecciones(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception 
	{
		List<Seccion> lista=new ArrayList<Seccion>();
		Query consulta=createQuery("SELECT * FROM horario.lst_secciones(:institucion, :annio, :proceso, :profesion, :turno)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		consulta.setParameter("turno", Integer.parseInt(turno.toString()));
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
	
	public boolean validarDisponibilidad(Long seccion, Long docente) throws Exception 
	{
		Query consulta=createQuery("SELECT * FROM horario.validarDisponibilidad(:seccion, :docente)");
		consulta.setParameter("seccion", seccion);
		consulta.setParameter("docente", Integer.parseInt(docente.toString()));
		if(consulta.list().size()==0) 	{return true;}
		else							{return false;}
	}
	
	public boolean validarHoras(Long docente, Long horas) throws Exception 
	{
		Query consulta=createQuery("SELECT * FROM horario.validarHoras(:docente)");
		consulta.setParameter("docente", Integer.parseInt(docente.toString()));
		List rst=consulta.list();
		Long totalHoras=horas;
		for(int i=0; i<rst.size(); i++)
		{
			if(rst.get(i)!=null)
			{totalHoras=totalHoras+Long.parseLong(rst.get(i).toString());}
		}
		if(totalHoras<21)		{return true;}
		return false;
	}
	
	public void actualizarDocente(Long seccion, Long docente) throws Exception
	{executeQueryUpdate("UPDATE horario.m_seccion SET docente='"+docente+"' WHERE pk_seccion='"+seccion+"';");}
	
	public List<Seccion> listarSeccionesDocente(Long institucion, Long annio, Long proceso, Long docente) throws Exception 
	{
		List<Seccion> lista=new ArrayList<Seccion>();
		Query consulta=createQuery("SELECT * FROM horario.lst_seccionesdocente(:institucion, :annio, :proceso, :docente)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("docente", Integer.parseInt(docente.toString()));
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
			if(objetos[8]!=null){field.setMeta(Long.parseLong(objetos[8].toString()));}
			if(objetos[9]!=null){field.setValorUnidad(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setEstadoSilabo(Long.parseLong(objetos[10].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public void insertSilaboAlumno(Long meta, Long unidad, Long seccion, Long docente) throws Exception 
	{
		// J : dice , sino es un select ,entonces que es!
		executeQueryUpdate("SELECT horario.ins_silabo_alumno("+meta+","+unidad+","+seccion+","+docente+");");
	}
	
	public List<Matricula> listarAlumnosSeccion(Long meta, Long unidad, Long seccion, Long docente) throws Exception 
	{
		List<Matricula> lista=new ArrayList<Matricula>();
		Query consulta=createQuery("SELECT * FROM horario.lst_silaboalumno(:meta, :unidad, :seccion, :docente)");
		consulta.setParameter("meta", Integer.parseInt(meta.toString()));
		consulta.setParameter("unidad", Integer.parseInt(unidad.toString()));
		consulta.setParameter("seccion", Integer.parseInt(seccion.toString()));
		consulta.setParameter("docente", Integer.parseInt(docente.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Matricula field=new Matricula();
			if(objetos[0]!=null){field.setPersona(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setPersonaNombre(objetos[1].toString());}
			if(objetos[2]!=null){field.setPersonaPaterno(objetos[2].toString());}
			if(objetos[3]!=null){field.setPersonaMaterno(objetos[3].toString());}
			if(objetos[4]!=null){field.setSilabo(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setSilaboAlumno(Long.parseLong(objetos[5].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public List<Seccion> listarUnidadesAlumno(Long institucion, Long annio, Long proceso, Long alumno) throws Exception 
	{
		List<Seccion> lista=new ArrayList<Seccion>();
		Query consulta=createQuery("SELECT * FROM horario.lst_unidadesalumno(:institucion, :annio, :proceso, :alumno)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("alumno", Integer.parseInt(alumno.toString()));
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
			if(objetos[8]!=null){field.setMeta(Long.parseLong(objetos[8].toString()));}
			if(objetos[9]!=null){field.setValorUnidad(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setEstadoSilabo(Long.parseLong(objetos[10].toString()));}
			if(objetos[11]!=null){field.setAlumno(Long.parseLong(objetos[11].toString()));}
			lista.add(field);
		}
		return lista;
	}
}
