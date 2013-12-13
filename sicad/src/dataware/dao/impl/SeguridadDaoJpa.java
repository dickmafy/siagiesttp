package dataware.dao.impl;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import dataware.dao.SeguridadDao;
import modules.administracion.domain.Convenio;
import modules.administracion.domain.Cronograma;
import modules.administracion.domain.HistorialLaboral;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.MetaOcupacional;
import modules.administracion.domain.Oferta;
import modules.administracion.domain.Solicitud;
import modules.admision.domain.Persona;
import modules.admision.domain.Postulante;
import modules.admision.domain.Proceso;
import modules.admision.domain.Retiro;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Menu;
import modules.seguridad.domain.MenuAcceso;
import modules.seguridad.domain.Usuario;
import modules.seguridad.domain.Variable;
import modules.seguridad.domain.VariableAcceso;
import com.belogick.factory.util.dao.impl.GenericDaoJpa;
import com.belogick.factory.util.support.DaoException;

public class SeguridadDaoJpa extends GenericDaoJpa implements SeguridadDao 
{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	public Usuario obtenerUsuario(String usuario) throws Exception 
	{
		Usuario item=new Usuario();
		Query consulta=createQuery("SELECT * FROM seguridad.get_usuario(:usuario)");
		consulta.setParameter("usuario", usuario);
		List rst=consulta.list();
		if(rst.size()==0)
		{return null;}
		
		Object[] objetos=(Object[])rst.get(0);
		
		item.setId(Long.parseLong(objetos[0].toString()));
		item.setPerfil(Long.parseLong(objetos[1].toString()));
		item.setUsuario(objetos[2].toString());
		item.setContrasena(objetos[3].toString());

		System.out.println(objetos[0]+"IDUSUARIO");
		System.out.println(objetos[1]+"PERFIL");
		System.out.println(objetos[2]+"LGINUSUARIO");
		System.out.println(objetos[3]+"CONTRAsenia");
		System.out.println(objetos[4]+"pertenecnai");
		System.out.println(objetos[5]+"nombre");
		System.out.println(objetos[6]+"acceso");
		System.out.println(objetos[7]+"estado");
		System.out.println(objetos[8]+"nombreperfil");
		System.out.println(objetos[9]+"pestado perfil");
		System.out.println(objetos[4]+"institup");
		//System.exit(-1);
		if(objetos[4]!=null)	{item.setPertenencia(Long.parseLong(objetos[4].toString()));}
		if(objetos[5]!=null)    {item.setNombres(objetos[5].toString());}
		else{
			item.setNombres("DESCONOCIDO");
		}
		item.setEstado(Long.parseLong(objetos[7].toString()));
		item.setNombrePerfil(objetos[8].toString());
		item.setEstadoPerfil(Long.parseLong(objetos[9].toString()));
		if(objetos[10]!=null)	{item.setInstitucion(Long.parseLong(objetos[10].toString()));}
		return item;
	}
	
	public MenuAcceso listarAccesos(Long perfil, Long principal) throws Exception 
	{
		MenuAcceso menuAcc=new MenuAcceso();
		Query consulta=createQuery("SELECT * FROM seguridad.lst_acceso(:perfil, :principal)");
		consulta.setParameter("perfil", Integer.parseInt(perfil.toString()));
		consulta.setParameter("principal", Integer.parseInt(principal.toString()));
		List rst=consulta.list();
		List<Menu> menus=new ArrayList<Menu>();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Menu field=new Menu();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setSistema(Long.parseLong(objetos[1].toString()));
			field.setModulo(Long.parseLong(objetos[2].toString()));
			field.setMenu(Long.parseLong(objetos[3].toString()));
			field.setTitulo(objetos[4].toString());
			field.setDescripcion(objetos[5].toString());
			if(objetos[6]!=null){field.setAccion(objetos[6].toString());}
			field.setMetodo(objetos[7].toString());
			field.setEstado(Long.parseLong(objetos[8].toString()));
			menus.add(field);
		}
		menuAcc.setItems(menus);
		return menuAcc;	
	}
				
	public VariableAcceso listarVariables() throws Exception 
	{
		VariableAcceso varAcc=new VariableAcceso();
		Query consulta=createQuery("SELECT * FROM seguridad.lst_variable()");
		List rst=consulta.list();
		List<Variable> var=new ArrayList<Variable>();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Variable field=new Variable();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setCodigo(Long.parseLong(objetos[1].toString()));
			field.setNombre(objetos[2].toString());
			field.setDescripcion(objetos[3].toString());
			field.setValor(objetos[4].toString());
			field.setEstado(Long.parseLong(objetos[5].toString()));
			var.add(field);
		}
		varAcc.setVariables(var);
		return varAcc;
	}
	
	public void insertarAcceso(List<Menu> lista, Long perfil) throws DaoException 
	{
		StringBuilder query=new StringBuilder();
		query.append("INSERT INTO seguridad.acceso(\"pk_perfil\",\"pk_menu\",\"permiso\") VALUES ");
		for(int i=0; i<lista.size(); i++)
		{
			if(lista.get(i).getSelected())
			{query.append("('"+perfil+"','"+lista.get(i).getId()+"','1'),");}
		}
		executeQueryUpdate(query.toString().substring(0,query.toString().length()-1)+";");
		query=null;
	}
	
	public List<Ubigeo> listarUbigeos(Long dep, Long pro) throws Exception 
	{
		List<Ubigeo> lista=new ArrayList<Ubigeo>();
		Query consulta=createQuery("SELECT * FROM mantenimiento.lst_ubigeo(:dep, :pro)");
		consulta.setParameter("dep", Integer.parseInt(dep.toString()));
		consulta.setParameter("pro", Integer.parseInt(pro.toString()));
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Ubigeo field=new Ubigeo();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setDepartamento(Long.parseLong(objetos[1].toString()));
			field.setProvincia(Long.parseLong(objetos[2].toString()));
			field.setDescripcion(objetos[3].toString());
			lista.add(field);
		}
		return lista;
	}
	
	
	
	public List<Oferta> listarOferta(Long institucion, Date fecha, Long tipo) throws Exception 
	{
		List<Oferta> lista=new ArrayList<Oferta>();
		Query consulta=createQuery("SELECT * FROM admision.lst_oferta(:institucion,:fecha,:tipo)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("fecha", fecha);
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		List rst=consulta.list();
		
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Oferta field=new Oferta();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setInstitucion(Long.parseLong(objetos[1].toString()));
			field.setProfesion(Long.parseLong(objetos[2].toString()));
			field.setVigenciaInicio(dateFormat.parse(objetos[3].toString()));
			field.setVigenciaFin(dateFormat.parse(objetos[4].toString()));
			if(objetos[5]!=null){field.setResolucion(Long.parseLong(objetos[5].toString()));}
			if(objetos[6]!=null){field.setSolicitud(Long.parseLong(objetos[6].toString()));}
			field.setNombreProfesion(objetos[7].toString());
			lista.add(field);
		}
		return lista;
	}
	
	public List<Cronograma> listarPeriodo(Long institucion, Date fecha, Long tipo) throws Exception 
	{
		List<Cronograma> lista=new ArrayList<Cronograma>();
		Query consulta=createQuery("SELECT * FROM admision.lst_periodo(:institucion,:fecha,:tipo)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("fecha", fecha);
		consulta.setParameter("tipo", Integer.parseInt(tipo.toString()));
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Cronograma field=new Cronograma();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setInstitucion(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setTipo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setPeriodo(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setEjecucion(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setVigenciaInicio(dateFormat.parse(objetos[5].toString()));}
			if(objetos[6]!=null){field.setVigenciaFin(dateFormat.parse(objetos[6].toString()));}
			if(objetos[7]!=null){field.setResolucion(Long.parseLong(objetos[7].toString()));}
			if(objetos[8]!=null){field.setSolicitud(Long.parseLong(objetos[8].toString()));}
			
			lista.add(field);
		}
		return lista;
	}

	
	
	
	
	
	
	
	
	
	
	

	public Retiro retirar_matriculado(Long proceso, String dni) throws Exception
	{
		List<Persona> lista=new ArrayList<Persona>();
		Query consulta=createQuery("SELECT * FROM admision.get_proceso_retiro("+proceso+",'"+dni+"');");

		List rst=consulta.list();
		if(rst.size()==0)	{return null;}
		else
		{
			Object[] objetos=(Object[])rst.get(0);
			Retiro field=new Retiro();
			field.setMatricula(Long.parseLong(objetos[0].toString()));
			field.setProceso(Long.parseLong(objetos[1].toString()));
			field.setEspecialidad(Long.parseLong(objetos[2].toString()));
			field.setTurno(Long.parseLong(objetos[3].toString()));
			field.setSeccion(Long.parseLong(objetos[4].toString()));
			field.setPersona(Long.parseLong(objetos[5].toString()));
			field.setNombres(objetos[8].toString());
			field.setApellidos(objetos[9].toString()+" "+objetos[10].toString());
			field.setEstado_civil(Long.parseLong(objetos[11].toString()));
			field.setCorreo(objetos[12].toString());
			field.setUbigeo(Long.parseLong(objetos[13].toString()));
			field.setNombreEspecialidad(objetos[14].toString());
			field.setNombreProceso(objetos[15].toString()+"-"+objetos[17].toString());
			return field;
		}
	}
	
	
	
	public Proceso existeProceso(Long institucion, Long annio) throws Exception 
	{
		List<Proceso> lista=new ArrayList<Proceso>();
		Query consulta=createQuery("SELECT * FROM admision.exist_proceso(:institucion, :annio);");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("annio", Integer.parseInt(annio.toString()));
		List rst=consulta.list();
		if(rst.size()==0)	{return null;}
		else
		{
			dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Object[] objetos=(Object[])rst.get(0);
			Proceso field=new Proceso();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setInstitucion(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setAnnio(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setProceso(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setTipo(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setCosto_carpeta(Double.parseDouble(objetos[5].toString()));}
			if(objetos[6]!=null){field.setCosto_inscripcion(Double.parseDouble(objetos[6].toString()));}
			if(objetos[7]!=null){field.setCosto_matreg(Double.parseDouble(objetos[7].toString()));}
			if(objetos[8]!=null){field.setCosto_matext(Double.parseDouble(objetos[8].toString()));}
			if(objetos[9]!=null){field.setFecha(dateFormat.parse(objetos[9].toString()));}
			if(objetos[10]!=null){field.setRegistrante(Long.parseLong(objetos[10].toString()));}
			if(objetos[11]!=null){field.setEstado(Long.parseLong(objetos[11].toString()));}
			return field;
		}
	}
	
	public boolean existeFechaProceso(Long proceso, Long actividad, Date fecha) throws Exception 
	{
		List<Proceso> lista=new ArrayList<Proceso>();
		Query consulta=createQuery("SELECT * FROM admision.exist_proceso(:proceso, :actividad, :fecha);");
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("actividad", Integer.parseInt(actividad.toString()));
		consulta.setParameter("fecha", fecha);
		List rst=consulta.list();
		if(rst.size()==0)	{return false;}
		else				{return true;}
	}
	
	
	
	
	
	
	public Long obtenerUbigeo(Long ubigeo, boolean tipo) throws Exception 
	{
		Query consulta=createQuery("SELECT * FROM seguridad.get_ubigeo(:ubigeo,:tipo);");
		consulta.setParameter("ubigeo", Integer.parseInt(ubigeo.toString()));
		consulta.setParameter("tipo", tipo);
		List rst=consulta.list();
		if(rst.size()>0)
		{
			Object[] objetos=(Object[])rst.get(0);
			return Long.parseLong(objetos[0].toString());
		}
		return null;
	}
	
	public Postulante obtenerIngresante(Long proceso, String dni) throws Exception 
	{
		Query consulta=createQuery("SELECT * FROM admision.get_proceso_ingresante(:proceso,:dni);");
		consulta.setParameter("proceso", Integer.parseInt(proceso.toString()));
		consulta.setParameter("dni", dni);
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		if(rst.size()!=0)
		{
			Object[] objetos=(Object[])rst.get(0);
			Postulante field=new Postulante();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setProceso(Long.parseLong(objetos[1].toString()));
			field.setProfesion(Long.parseLong(objetos[2].toString()));
			field.setPersona(Long.parseLong(objetos[3].toString()));
			field.setTurno(Long.parseLong(objetos[4].toString()));
			if(objetos[5]!=null){field.setPago_fecha(dateFormat.parse(objetos[5].toString()));}
			if(objetos[6]!=null){field.setPago_banco(Long.parseLong(objetos[6].toString()));}
			if(objetos[7]!=null){field.setPago_recibo(objetos[7].toString());}
			if(objetos[8]!=null){field.setPuntuacion(Double.parseDouble(objetos[8].toString()));}
			if(objetos[9]!=null){field.setModalidad(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setRegfec(dateFormat.parse(objetos[10].toString()));}
			if(objetos[11]!=null){field.setRegusr(Long.parseLong(objetos[11].toString()));}
			if(objetos[12]!=null){field.setEstado(Long.parseLong(objetos[12].toString()));}
			if(objetos[13]!=null){field.setPostulanteDni(objetos[13].toString());}
			if(objetos[14]!=null){field.setPostulanteNombres(objetos[14].toString());}
			if(objetos[15]!=null){field.setPostulanteApellidos(objetos[15].toString()+" "+objetos[16].toString());}
			if(objetos[17]!=null){field.setNombreEspecialidad(objetos[17].toString());}
			if(objetos[18]!=null){field.setPersonaId(Long.parseLong(objetos[18].toString()));}
			return field;
		}
		dateFormat=null;
		return null;
	}
	
	public List<Solicitud> listarSolicitudes() throws Exception 
	{
		Solicitud varAcc=new Solicitud();
		Query consulta=createQuery("SELECT * FROM administracion.lst_solicitud()");
		List rst=consulta.list();
		List<Solicitud> var=new ArrayList<Solicitud>();
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Solicitud field=new Solicitud();
			String fecha=dateFormat2.format(objetos[1]);			
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setFechas(fecha);
			field.setNombreInstitucion(objetos[2].toString());
			field.setNombreRegistrante(objetos[3].toString());
			field.setTipo(Long.parseLong(objetos[4].toString()));
			field.setDescripcion(objetos[5].toString());
			field.setResolucion(objetos[6].toString());
			field.setFecha_inicio(dateFormat2.parse(objetos[7].toString()));
			field.setFecha_termino(dateFormat2.parse(objetos[8].toString()));
			field.setEstado(Long.parseLong(objetos[9].toString()));
			var.add(field); 
		}
		return var;
	}
	
	public List<Solicitud> listaSolicitudInstitucion(Long codigo) throws Exception 
	{
		Query consulta=createQuery("SELECT * FROM administracion.lst_solicitudinstitucion(:institucion)");
		consulta.setParameter("institucion", Integer.parseInt(codigo.toString()));
		List rst=consulta.list();
		List<Solicitud> var=new ArrayList<Solicitud>();
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Solicitud field=new Solicitud();
			String fecha=dateFormat2.format(objetos[1]);			
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setFechas(fecha);
			field.setNombreInstitucion(objetos[2].toString());
			field.setNombreRegistrante(objetos[3].toString());
			field.setTipo(Long.parseLong(objetos[4].toString()));
			field.setDescripcion(objetos[5].toString());
			field.setResolucion(objetos[6].toString());
			field.setFecha_inicio(dateFormat2.parse(objetos[7].toString()));
			field.setFecha_termino(dateFormat2.parse(objetos[8].toString()));
			field.setEstado(Long.parseLong(objetos[9].toString()));
			var.add(field); 
		}
		return var;
	}
	
	
	
	

	


	
	


	@Override
	public List<Convenio> listarConvenios(Long pk_institucion) throws Exception {
		List<Convenio> lst=new ArrayList<Convenio>();
		Query consulta=createQuery("SELECT * FROM administracion.lst_convenio(:institucion);");
		consulta.setParameter("institucion", Integer.parseInt(pk_institucion.toString()));
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Convenio field=new Convenio();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setInstitucion(Long.parseLong(objetos[1].toString()));
			field.setEmpresa(Long.parseLong(objetos[2].toString()));
			field.setNombre(objetos[3].toString());
			if(objetos[4]!=null)	{field.setModalidad(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null)	{field.setAlcance(Long.parseLong(objetos[5].toString()));}
			if(objetos[6]!=null)	{field.setTipo(Long.parseLong(objetos[6].toString()));}
			if(objetos[7]!=null)	{field.setFecha_inicio(dateFormat.parse(objetos[7].toString()));}
			if(objetos[8]!=null)	{field.setFecha_fin(dateFormat.parse(objetos[8].toString()));}
			if(objetos[9]!=null)	{field.setBeneficio(objetos[9].toString());}
			if(objetos[10]!=null)	{field.setCompromiso(objetos[10].toString());}
			if(objetos[11]!=null)	{field.setResponsable(objetos[11].toString());}
			field.setEstado(Long.parseLong(objetos[12].toString()));
			field.setNombreInstitucion(objetos[13].toString());
			lst.add(field);
			field=null;
		}
		return lst;	
	}

	
	@Override
	public List<MetaOcupacional> listarMetaOcupacional(Long institucion) throws Exception 
	{
		List<MetaOcupacional> lista=new ArrayList<MetaOcupacional>();
		Query consulta=createQuery("SELECT * FROM administracin.lst_metaocup(:institucion)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		List rst=consulta.list();
		
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			MetaOcupacional field=new MetaOcupacional();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setInstitucion(Long.parseLong(objetos[1].toString()));
			field.setProfesion(Long.parseLong(objetos[2].toString()));
			field.setMinimo(Long.parseLong(objetos[3].toString()));
			if(objetos[4]!=null){field.setResolucion(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setSolicitud(Long.parseLong(objetos[5].toString()));}
			field.setNombreProfesion(objetos[6].toString());
			lista.add(field);
		}
		return lista;
	}
	
	@Override
	public List<MetaInstitucional> listarMetaInstitucional(Long institucion, boolean tipo) throws Exception 
	{
		List<MetaInstitucional> lista=new ArrayList<MetaInstitucional>();
		Query consulta=createQuery("SELECT * FROM administracion.lst_meta_institucional(:institucion, :tipo)");
		consulta.setParameter("institucion", Integer.parseInt(institucion.toString()));
		consulta.setParameter("tipo", tipo);
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
	 
	@Override
	public List<HistorialLaboral> listarHistorialLaboral(Long personal) throws Exception 
	{
		List<HistorialLaboral> lista=new ArrayList<HistorialLaboral>();
		Query consulta=createQuery("SELECT * FROM administracion.lst_historial_laboral(:personal)");
		consulta.setParameter("personal", Integer.parseInt(personal.toString()));
		List rst=consulta.list();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			HistorialLaboral field=new HistorialLaboral();
			if(objetos[0]!=null){field.setId(Long.parseLong(objetos[0].toString()));}
			if(objetos[1]!=null){field.setPersonal(Long.parseLong(objetos[1].toString()));}
			if(objetos[2]!=null){field.setTipo(Long.parseLong(objetos[2].toString()));}
			if(objetos[3]!=null){field.setInstitucion(Long.parseLong(objetos[3].toString()));}
			if(objetos[4]!=null){field.setPuesto(Long.parseLong(objetos[4].toString()));}
			if(objetos[5]!=null){field.setFuncion(objetos[5].toString());}
			if(objetos[6]!=null){field.setInicio(dateFormat.parse(objetos[6].toString()));}
			if(objetos[7]!=null){field.setFin(dateFormat.parse(objetos[7].toString()));}
			if(objetos[8]!=null){field.setResolucion(Long.parseLong(objetos[8].toString()));}
			if(objetos[9]!=null){field.setEstado(Long.parseLong(objetos[9].toString()));}
			if(objetos[10]!=null){field.setNombreInstitucion(objetos[10].toString());}
			if(objetos[11]!=null){field.setNombrePuesto(objetos[11].toString());}
			lista.add(field);
		}
		return lista;
	}


}



