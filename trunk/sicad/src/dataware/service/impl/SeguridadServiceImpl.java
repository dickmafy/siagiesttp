package dataware.service.impl;
import java.util.Date;
import java.util.List;

import dataware.dao.SeguridadDao;
import modules.administracion.domain.Convenio;
import modules.administracion.domain.Cronograma;
import modules.administracion.domain.HistorialLaboral;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.MetaOcupacional;
import modules.administracion.domain.Oferta;
import modules.administracion.domain.Solicitud;
import modules.admision.domain.Matricula;
import modules.admision.domain.Postulante;
import modules.admision.domain.Proceso;
import modules.admision.domain.Retiro;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Menu;
import modules.seguridad.domain.MenuAcceso;
import modules.seguridad.domain.Usuario;
import modules.seguridad.domain.VariableAcceso;
import dataware.service.SeguridadService;

import com.belogick.factory.util.service.impl.GenericServiceImpl;
import com.belogick.factory.util.support.DaoException;

public class SeguridadServiceImpl extends GenericServiceImpl implements SeguridadService
{
	private SeguridadDao seguridadDao;
	
	public SeguridadDao getSeguridadDao()					{return seguridadDao;}
	public void setSeguridadDao(SeguridadDao seguridadDao)	{this.seguridadDao = seguridadDao;}

	public Usuario obtenerUsuario(String usuario) throws Exception		
	{return getSeguridadDao().obtenerUsuario(usuario);}
	
	public MenuAcceso listarAccesos(Long perfil, Long principal) throws Exception		
	{return getSeguridadDao().listarAccesos(perfil,principal);}
	
	public VariableAcceso listarVariables() throws Exception		
	{return getSeguridadDao().listarVariables();}

	public void insertarAcceso(List<Menu> lista, Long perfil) throws DaoException
	{getSeguridadDao().insertarAcceso(lista, perfil);}
	
	public List<Ubigeo> listarUbigeos(Long dep, Long pro) throws Exception 
	{return getSeguridadDao().listarUbigeos(dep,pro);}
	
	public List<Oferta> listarOferta(Long institucion, Date fecha, Long tipo) throws Exception 
	{return getSeguridadDao().listarOferta(institucion, fecha, tipo);}
	
	public List<Cronograma> listarPeriodo(Long institucion, Date fecha, Long tipo) throws Exception
	{return getSeguridadDao().listarPeriodo(institucion, fecha, tipo);}
	
	
	public Proceso existeProceso(Long institucion, Long annio) throws Exception 
	{return getSeguridadDao().existeProceso(institucion, annio);}
	
	public boolean existeFechaProceso(Long proceso, Long actividad, Date fecha) throws Exception 
	{return getSeguridadDao().existeFechaProceso(proceso, actividad, fecha);}
	
	
	public Long obtenerUbigeo(Long ubigeo, boolean tipo) throws Exception 
	{return getSeguridadDao().obtenerUbigeo(ubigeo, tipo);}
	
	public Postulante obtenerIngresante(Long proceso, String dni) throws Exception 
	{return getSeguridadDao().obtenerIngresante(proceso, dni);}
	
	public Retiro   retirar_matriculado(Long proceso, String dni) throws Exception
	{return getSeguridadDao().retirar_matriculado(proceso, dni);}
	
	public List<Solicitud> listarSolicitudes() throws Exception 
	{return getSeguridadDao().listarSolicitudes();}
	
	public List<Solicitud> listaSolicitudInstitucion(Long codigo) throws Exception 
	{return getSeguridadDao().listaSolicitudInstitucion(codigo);}
	
	
	public List<Convenio> listarConvenios(Long pk_institucion) throws Exception
	{return getSeguridadDao().listarConvenios(pk_institucion);}
	
	public List<MetaInstitucional> listarMetaInstitucional(Long institucion, boolean tipo) throws Exception 
	{return getSeguridadDao().listarMetaInstitucional(institucion, tipo); }
	
	public List<MetaOcupacional> listarMetaOcupacional(Long institucion) throws Exception
	{return getSeguridadDao().listarMetaOcupacional(institucion); }
	
	public List<HistorialLaboral> listarHistorialLaboral(Long personal) throws Exception
	{return getSeguridadDao().listarHistorialLaboral(personal); }
	
	public List<Matricula> listMatriculaInstitucion(Long institucion) throws Exception
	{return getSeguridadDao().listMatriculaInstitucion(institucion);}

}

