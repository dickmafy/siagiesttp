package dataware.dao.impl;
import java.util.ArrayList;
import java.util.List;

import modules.administracion.domain.AmbienteUnidad;
import modules.marco.domain.Itinerario;
import modules.marco.domain.ItinerarioTransversal;
import modules.marco.domain.Profesion;
import modules.marco.domain.ReferenteEducativo;
import modules.marco.domain.ReferenteProductivo;
import modules.marco.domain.Transversal;

import org.hibernate.Query;

import dataware.dao.MarcoDao;

public class MarcoDaoJpa extends InstitucionDaoJpa implements MarcoDao 
{
	public List<Profesion> listarProfesiones() throws Exception 
	{
		List<Profesion> lst=new ArrayList<Profesion>();
		Query consulta=createQuery("SELECT * FROM marco.lst_profesion()");
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Profesion field=new Profesion();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setFamilia(Long.parseLong(objetos[1].toString()));
			field.setFormacion(Long.parseLong(objetos[2].toString()));
			field.setNombre(objetos[3].toString());
			if(objetos[4]!=null)	{field.setDescripcion(objetos[4].toString());}
			if(objetos[5]!=null)	{field.setMencion(objetos[5].toString());}
			if(objetos[6]!=null)	{field.setAptitudes(objetos[6].toString());}
			if(objetos[7]!=null)	{field.setActitudes(objetos[7].toString());}
			if(objetos[8]!=null)	{field.setAmbiente(objetos[8].toString());}
			if(objetos[9]!=null)	{field.setDuracion(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null)	{field.setDependencia(Long.parseLong(objetos[10].toString()));}
			if(objetos[13]!=null)	{field.setReferente(Boolean.parseBoolean(objetos[13].toString()));}
			field.setEstado(Long.parseLong(objetos[14].toString()));
			field.setNombreFamilia(objetos[15].toString());
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public List<Transversal> listarTransversal(boolean tipo, Long profesion) throws Exception 
	{
		List<Transversal> lst=new ArrayList<Transversal>();
		Query consulta=createQuery("SELECT * FROM marco.lst_transversal(:tipo,:profesion)");
		consulta.setParameter("tipo", tipo);
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Transversal field=new Transversal();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setTitulo(objetos[1].toString());
			field.setDescripcion(objetos[2].toString());
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public void eliminarTransversal(Long profesion, Long modulo) throws Exception 
	{executeQueryUpdate("DELETE FROM marco.m_referente_transversal WHERE pk_profesion='"+profesion+"' AND pk_transversal='"+modulo+"'; ");}
	
	public List<ReferenteProductivo> listarReferenteProductivo(Long profesion, int opcion, Long tipo) throws Exception 
	{
		List<ReferenteProductivo> lst=new ArrayList<ReferenteProductivo>();
		Query consulta=createQuery("SELECT * FROM marco.lst_referente_productivo(:profesion,:opcion,:tipo)");
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		consulta.setParameter("opcion", opcion);
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			ReferenteProductivo field=new ReferenteProductivo();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setProfesion(Long.parseLong(objetos[1].toString()));
			field.setTipo(Long.parseLong(objetos[2].toString()));
			field.setNivelA(Long.parseLong(objetos[3].toString()));
			field.setNivelB(Long.parseLong(objetos[4].toString()));
			field.setNivelC(Long.parseLong(objetos[5].toString()));
			if(objetos[6]!=null)	{field.setTitulo(objetos[6].toString());}
			if(objetos[7]!=null)	{field.setDescripcion(objetos[7].toString());}
			field.setEstado(Long.parseLong(objetos[8].toString()));
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public List<ReferenteEducativo> listarReferenteEducativo(Long profesion, int opcion, Long tipo) throws Exception 
	{
		List<ReferenteEducativo> lst=new ArrayList<ReferenteEducativo>();
		Query consulta=createQuery("SELECT * FROM marco.lst_referente_educativo(:profesion,:opcion,:tipo)");
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		consulta.setParameter("opcion", opcion);
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			ReferenteEducativo field=new ReferenteEducativo();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setProfesion(Long.parseLong(objetos[1].toString()));
			field.setTipo(Long.parseLong(objetos[2].toString()));
			field.setNivelA(Long.parseLong(objetos[3].toString()));
			field.setNivelB(Long.parseLong(objetos[4].toString()));
			field.setNivelC(Long.parseLong(objetos[5].toString()));
			if(objetos[6]!=null)	{field.setTitulo(objetos[6].toString());}
			if(objetos[7]!=null)	{field.setDescripcion(objetos[7].toString());}
			if(objetos[8]!=null)	{field.setReferente(Long.parseLong(objetos[8].toString()));}
			field.setEstado(Long.parseLong(objetos[9].toString()));
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public List<Itinerario> listarItinerario(Long profesion, Long modulo, Long tipo) throws Exception 
	{
		List<Itinerario> lst=new ArrayList<Itinerario>();
		Query consulta=createQuery("SELECT * FROM marco.lst_referente_itinerario(:profesion,:modulo,:tipo)");
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		consulta.setParameter("modulo", Integer.parseInt(modulo.toString()));
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Itinerario field=new Itinerario();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setProfesion(Long.parseLong(objetos[1].toString()));
			field.setModulo(Long.parseLong(objetos[2].toString()));
			if(objetos[3]!=null)	{field.setTipo(Boolean.parseBoolean(objetos[3].toString()));}
			if(objetos[4]!=null)	{field.setTitulo(objetos[4].toString());}
			if(objetos[5]!=null)	{field.setDescripcion(objetos[5].toString());}
			field.setSemestre(Long.parseLong(objetos[6].toString()));
			field.setHorasSemestre(Long.parseLong(objetos[7].toString()));
			field.setHorasTotal(Long.parseLong(objetos[8].toString()));
			field.setCreditos(Double.parseDouble(objetos[9].toString()));
			field.setEstado(Long.parseLong(objetos[10].toString()));
			if(objetos[11]!=null)	{field.setNombreModulo(objetos[11].toString());}
			if(objetos[12]!=null)	{field.setTipoModulo(Long.parseLong(objetos[12].toString()));}
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public List<Transversal> listarReferenteTransversal(int opcion, Long modulo) throws Exception 
	{
		List<Transversal> lst=new ArrayList<Transversal>();
		Query consulta=createQuery("SELECT * FROM marco.lst_referente_transversal(:opcion,:modulo)");
		consulta.setParameter("opcion", opcion);
		consulta.setParameter("modulo", Integer.parseInt(modulo.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Transversal field=new Transversal();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setModulo(Long.parseLong(objetos[1].toString()));
			field.setTipo(Long.parseLong(objetos[2].toString()));
			field.setNivel(Long.parseLong(objetos[3].toString()));
			field.setSubnivel(Long.parseLong(objetos[4].toString()));
			if(objetos[5]!=null)	{field.setTitulo(objetos[5].toString());}
			if(objetos[6]!=null)	{field.setDescripcion(objetos[6].toString());}
			if(objetos[7]!=null)	{field.setDuracion(Long.parseLong(objetos[7].toString()));}
			field.setEstado(Long.parseLong(objetos[8].toString()));
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public List<ItinerarioTransversal> listarItinerarioTransversal(Long tipo) throws Exception 
	{
		List<ItinerarioTransversal> lst=new ArrayList<ItinerarioTransversal>();
		Query consulta=createQuery("SELECT * FROM marco.lst_referente_itinerario_trans(:tipo)");
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			ItinerarioTransversal field=new ItinerarioTransversal();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setTipo(Long.parseLong(objetos[1].toString()));
			field.setModulo(Long.parseLong(objetos[2].toString()));
			if(objetos[3]!=null)	{field.setTitulo(objetos[3].toString());}
			if(objetos[4]!=null)	{field.setDescripcion(objetos[4].toString());}
			field.setSemestre(Long.parseLong(objetos[5].toString()));
			field.setHorasSemestre(Long.parseLong(objetos[6].toString()));
			field.setHorasTotal(Long.parseLong(objetos[7].toString()));
			field.setCreditos(Double.parseDouble(objetos[8].toString()));
			field.setEstado(Long.parseLong(objetos[9].toString()));
			if(objetos[10]!=null)	{field.setNombreModulo(objetos[10].toString());}
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public List<Itinerario> listarItinerarioTotal(Long profesion) throws Exception 
	{
		List<Itinerario> lst=new ArrayList<Itinerario>();
		Query consulta=createQuery("SELECT * FROM marco.lst_itinerario(:profesion)");
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Itinerario field=new Itinerario();
			field.setTipoItinerario(Long.parseLong(objetos[0].toString()));
			field.setModulo(Long.parseLong(objetos[1].toString()));
			if(objetos[2]!=null)	{field.setNombreModulo(objetos[2].toString());}
			if(objetos[3]!=null)	{field.setTitulo(objetos[3].toString());}
			if(objetos[4]!=null)	{field.setDescripcion(objetos[4].toString());}
			field.setSemestre(Long.parseLong(objetos[5].toString()));
			field.setHorasSemestre(Long.parseLong(objetos[6].toString()));
			field.setHorasTotal(Long.parseLong(objetos[7].toString()));
			field.setCreditos(Double.parseDouble(objetos[8].toString()));
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public List<AmbienteUnidad> listarAmbienteTipo(Long institucion, Long unidad) throws Exception 
	{
		List<AmbienteUnidad> lst=new ArrayList<AmbienteUnidad>();
		Query consulta=createQuery("SELECT * FROM administracion.lst_ambiente_unidad(:institucion, :unidad)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("unidad", Integer.parseInt(unidad.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			AmbienteUnidad field=new AmbienteUnidad();
			if(objetos[0]!=null)	{field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null)	{field.setInstitucion(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null)	{field.setUnidad(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null)	{field.setTipo(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null)	{field.setHoras(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null)	{field.setNombreTipo(objetos[5].toString());}
			lst.add(field);
			field=null;
		}
		return lst;	
	}
	
	public void eliminarAmbienteTipo(Long id) throws Exception
	{executeQueryUpdate("DELETE FROM administracion.m_ambiente_unidad WHERE pk_transitoria='"+id+"';");}
	
	public List<Itinerario> listarModulos(Long profesion) throws Exception
	{
		List<Itinerario> lst=new ArrayList<Itinerario>();
		Query consulta=createQuery("SELECT * FROM marco.lst_modulos(:profesion)");
		consulta.setParameter("profesion", Integer.parseInt(profesion.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Itinerario field=new Itinerario();
			if(objetos[0]!=null)	{field.setTipoItinerario(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null)	{field.setModulo(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null)	{field.setNombreModulo(objetos[2].toString());}
			lst.add(field);
			field=null;
		}
		return lst;	
	}
}
