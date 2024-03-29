package dataware.service.impl;
import java.util.Date;
import java.util.List;

import com.belogick.factory.util.support.DaoException;

import modules.administracion.domain.Ambiente;
import modules.administracion.domain.AmbienteUnidad;
import modules.administracion.domain.MetaDetalle;
import modules.administracion.domain.MetaInstitucional;
import modules.admision.domain.Matricula;
import modules.horario.domain.AsistenciaDocente;
import modules.horario.domain.HorarioDistribucion;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCalendario;
import dataware.dao.HorarioDao;
import dataware.service.HorarioService;

public class HorarioServiceImpl extends MarcoServiceImpl implements HorarioService
{
	private HorarioDao horarioDao;
	
	public HorarioDao getHorarioDao() 									{return horarioDao;}
	public void setHorarioDao(HorarioDao horarioDao) 					{this.horarioDao = horarioDao;}	
	
	public List<MetaInstitucional> listarMetaInstitucional(Long institucion, Long annio, Long proceso) throws Exception
	{return getHorarioDao().listarMetaInstitucional(institucion, annio, proceso);}
	
	public List<MetaDetalle> listarMetaDetalle(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception
	{return getHorarioDao().listarMetaDetalle(institucion, annio, proceso, profesion, turno);}
	
	public List<Seccion> listarSeccion(Long meta) throws Exception
	{return getHorarioDao().listarSeccion(meta);}
	
	public void insertarDistribucion(List<HorarioDistribucion> lista, Long seccion, Long ambienteOld) throws DaoException 
	{getHorarioDao().insertarDistribucion(lista, seccion, ambienteOld);}
	
	public List<HorarioDistribucion> listarDistribucion(Long tipo, Long seccion, Long ambiente) throws Exception 
	{return getHorarioDao().listarDistribucion(tipo, seccion, ambiente);}
	
	public List<Ambiente> listarAmbientes(Long institucion, Long tipo, Long capacidad) throws Exception
	{return getHorarioDao().listarAmbientes(institucion, tipo, capacidad);}
	
	public List<AmbienteUnidad> listarAmbienteUnidad(Long institucion, Long seccion, Long unidad) throws Exception
	{return getHorarioDao().listarAmbienteUnidad(institucion, seccion, unidad);}
	
	public void actualizarAvance(Long metaDetalle) throws Exception
	{getHorarioDao().actualizarAvance(metaDetalle);}
	
	public List<Seccion> listarSecciones(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception
	{return getHorarioDao().listarSecciones(institucion, annio, proceso, profesion, turno);}
	
	public List<Seccion> listarSilabos(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception
	{return getHorarioDao().listarSilabos(institucion, annio, proceso, profesion, turno);}
	
	public boolean validarDisponibilidad(Long seccion, Long docente) throws Exception
	{return getHorarioDao().validarDisponibilidad(seccion, docente);}
	
	public boolean validarHoras(Long docente, Long horas) throws Exception 
	{return getHorarioDao().validarHoras(docente, horas);}
	
	public void actualizarDocente(Long seccion, Long docente) throws Exception
	{getHorarioDao().actualizarDocente(seccion, docente);}
	
	public Long validarEtapaAsignacion(Long meta) throws Exception
	{return getHorarioDao().validarEtapaAsignacion(meta);}
	
	public Long validarEtapaSilabo(Long meta) throws Exception
	{return getHorarioDao().validarEtapaSilabo(meta);}
	
	public Long validarEtapaAdmision(Long annio, Long proceso) throws Exception
	{return getHorarioDao().validarEtapaAdmision(annio, proceso);}
	
	public void etapaAdmision(Long annio, Long proceso) throws Exception
	{getHorarioDao().etapaAdmision(annio, proceso);}
	
	public List<Seccion> listarAsistenciaDocente(Long institucion, Long annio, Long proceso, Long docente, Date fecha) throws Exception
	{return getHorarioDao().listarAsistenciaDocente(institucion, annio, proceso, docente, fecha);}

	public void actualizarEstadoSesion(Long seccion, Date fecha, Long ambiente) throws Exception
	{getHorarioDao().actualizarEstadoSesion(seccion, fecha, ambiente);}
	
	public AsistenciaDocente obtenerHorarioDocente(Long seccion, Date fecha, Long ambiente) throws Exception
	{return getHorarioDao().obtenerHorarioDocente(seccion, fecha, ambiente);}

	public void actualizarSesion(AsistenciaDocente bean) throws Exception
	{getHorarioDao().actualizarSesion(bean);}
	
	public List<SilaboCalendario> listarAsistenciaAlumno(Long silabo, Long alumno) throws Exception 
	{return getHorarioDao().listarAsistenciaAlumno(silabo, alumno);}
	
	public List<Seccion> listarUnidadesAlumno(Long institucion, Long annio, Long proceso, Long alumno) throws Exception 
	{return getHorarioDao().listarUnidadesAlumno(institucion, annio, proceso, alumno);}
		
	public List<Matricula> listarAlumnosSeccion(Long meta, Long unidad, Long seccion, Long docente) throws Exception 
	{return getHorarioDao().listarAlumnosSeccion(meta, unidad, seccion, docente);}
	
	public void insertSilaboAlumno (Long meta, Long unidad, Long seccion, Long docente) throws Exception
	{getHorarioDao().insertSilaboAlumno(meta, unidad, seccion, docente);}

	public List<Seccion> listarSeccionesDocente(Long institucion, Long annio, Long proceso, Long docente) throws Exception
	{return getHorarioDao().listarSeccionesDocente(institucion, annio, proceso, docente);}
	
	
}

