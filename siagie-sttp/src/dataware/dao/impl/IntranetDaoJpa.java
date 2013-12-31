package dataware.dao.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modules.administracion.domain.Ambiente;
import modules.horario.domain.AsistenciaAlumno;
import modules.horario.domain.AsistenciaDocente;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboContenido;

import org.hibernate.Query;

import modules.horario.domain.SilaboNota;
import modules.marco.domain.ReferenteEducativo;
import modules.marco.domain.Transversal;

import com.belogick.factory.util.support.DaoException;

import dataware.dao.IntranetDao;

public class IntranetDaoJpa extends HorarioDaoJpa implements IntranetDao 
{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	public List<Seccion> listarSilabos(Long institucion, Long annio, Long proceso, Long docente) throws Exception 
	{
		List<Seccion> lista=new ArrayList<Seccion>();
		Query consulta=createQuery("SELECT * FROM horario.lst_silabos(:institucion, :annio, :proceso, :docente)");
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
			if(objetos[5]!=null){field.setNombre(objetos[5].toString());}
			if(objetos[6]!=null){field.setId(Long.parseLong(objetos[6].toString()));}
			if(objetos[7]!=null){field.setNombreProfesion(objetos[7].toString());}
			if(objetos[8]!=null){field.setValorTurno(Long.parseLong(objetos[8].toString()));}
			if(objetos[9]!=null){field.setValorProfesion(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setValorUnidad(Long.parseLong(objetos[10].toString()));}
			if(objetos[11]!=null){field.setEstado(Long.parseLong(objetos[11].toString()));}
			lista.add(field);
		}
		return lista;
	}
	public void insertarCriterios(List<ReferenteEducativo> criterio_prof, List<Transversal> criterio_trans, Long seccion, Long tipo) throws DaoException 
	{
		StringBuilder query=new StringBuilder();		
		query.append("DELETE FROM horario.m_silabo_nota WHERE pk_seccion='"+seccion+"';");
		query.append("INSERT INTO horario.m_silabo_nota(\"pk_seccion\",\"pk_criterio\") VALUES ");
		
		if(tipo==1L)
		{
			for(int i=0; i<criterio_prof.size(); i++)
			{if(criterio_prof.get(i).isCheckItem()) {query.append("('"+seccion+"','"+criterio_prof.get(i).getId()+"'),");} }
			executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
			query=null;	
		}
		else
		{
			for(int i=0; i<criterio_trans.size(); i++)
			{if(criterio_trans.get(i).isCheckItem()) {query.append("('"+seccion+"','"+criterio_trans.get(i).getId()+"'),");} }
			executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
			query=null;
		}
		
	}
	public List<SilaboNota> criteriosSeleccionados(Long seccion) throws Exception
	{
		List<SilaboNota> lista=new ArrayList<SilaboNota>();
		Query consulta=createQuery("SELECT * FROM horario.m_silabo_nota WHERE pk_seccion="+seccion);
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			SilaboNota field=new SilaboNota();
			field.setSeccion(Long.parseLong(objetos[0].toString()));
			field.setCriterio(Long.parseLong(objetos[1].toString()));
			lista.add(field);
		}
		return lista;
		
	}
	
	public List<SilaboContenido> listarContenido(Long seccion) throws Exception
	{
		List<SilaboContenido> lista=new ArrayList<SilaboContenido>();
		Query consulta=createQuery("SELECT * FROM horario.lst_silabo_contenido(:seccion)");
		consulta.setParameter("seccion", Integer.parseInt(seccion.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			SilaboContenido field=new SilaboContenido();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setSeccion(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setTipo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setElemento(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setActividad(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setDescripcion(objetos[5].toString());}
			if(objetos[6]!=null){field.setContenido(objetos[6].toString());}
			if(objetos[7]!=null){field.setTareas(objetos[7].toString());}
			if(objetos[8]!=null){field.setSemana(Long.parseLong(objetos[8].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public void eliminarContenido(Long primaria) throws Exception
	{executeQueryUpdate("DELETE FROM horario.m_silabo_contenido WHERE pk_contenido='"+primaria+"';");}
	
	public void actualizarSemana(Long contenido, Long semana) throws Exception 
	{executeQueryUpdate("UPDATE horario.m_silabo_contenido SET semana='"+semana+"' WHERE pk_contenido='"+contenido+"';");}
	
	
	
	
	
	
	
	
	public List<AsistenciaDocente> listarSesiones(Long seccion, Long ambiente) throws Exception
	{
		List<AsistenciaDocente> lista=new ArrayList<AsistenciaDocente>();
		Query consulta=createQuery("SELECT * FROM horario.lst_sesiones(:seccion,:ambiente)");
		consulta.setParameter("seccion", seccion);
		consulta.setParameter("ambiente", Integer.parseInt(ambiente.toString()));
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			AsistenciaDocente field=new AsistenciaDocente();
			if(objetos[0]!=null){field.setSeccion(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setAmbiente(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setFecha(dateFormat.parse(objetos[2].toString()));}
			
			if(objetos[7]!=null){field.setEstado(Long.parseLong(objetos[7].toString()));}
			lista.add(field);
		}
		return lista;
	}
	
	public List<AsistenciaAlumno> listarAsistencia(Long seccion, Long ambiente, Date fecha) throws Exception
	{
		List<AsistenciaAlumno> lista=new ArrayList<AsistenciaAlumno>();
		Query consulta=createQuery("SELECT * FROM horario.lst_asistencia(:seccion,:ambiente,:fecha)");
		consulta.setParameter("seccion", seccion);
		consulta.setParameter("ambiente", Integer.parseInt(ambiente.toString()));
		consulta.setParameter("fecha", fecha);
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			AsistenciaAlumno field=new AsistenciaAlumno();
			if(objetos[0]!=null){field.setPersona(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setId(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setAmbiente(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setFecha(dateFormat.parse(objetos[3].toString()));}
			if(objetos[4]!=null){field.setJustificacion(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setEstado(Long.parseLong(objetos[5].toString()));}
			if(objetos[6]!=null){field.setNombres(objetos[6].toString());}
			if(objetos[7]!=null){field.setApellidos(objetos[7].toString()+" "+objetos[8].toString());}
			lista.add(field);
		}
		return lista;
	}
	
	
	public List<Ambiente> listarAmbientes(Long seccion) throws Exception
	{
		List<Ambiente> lista=new ArrayList<Ambiente>();
		Query consulta=createQuery("SELECT * FROM horario.lst_ambientes_seccion(:seccion)");
		consulta.setParameter("seccion", seccion);
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Ambiente field=new Ambiente();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setNombre(objetos[1].toString());}
			lista.add(field);
		}
		return lista;
	}
	
}

