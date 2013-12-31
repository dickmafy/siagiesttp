package dataware.dao;
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

public interface HorarioDao extends MarcoDao
{
	public List<MetaInstitucional> listarMetaInstitucional(Long institucion, Long annio, Long proceso) throws Exception ;
	public List<MetaDetalle> listarMetaDetalle(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception;
	public List<Seccion> listarSeccion(Long meta) throws Exception;
	public void insertarDistribucion(List<HorarioDistribucion> lista, Long seccion, Long ambienteOld) throws DaoException ;
	public List<HorarioDistribucion> listarDistribucion(Long tipo, Long seccion, Long ambiente) throws Exception ;
	public List<Ambiente> listarAmbientes(Long institucion, Long tipo, Long capacidad) throws Exception;
	public List<AmbienteUnidad> listarAmbienteUnidad(Long institucion, Long seccion, Long unidad) throws Exception;
	public void actualizarAvance(Long metaDetalle) throws Exception;
	public List<Seccion> listarSecciones(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception;
	public List<Seccion> listarSilabos(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception;
	public boolean validarDisponibilidad(Long seccion, Long docente) throws Exception;
	public boolean validarHoras(Long docente, Long horas) throws Exception ;
	public void actualizarDocente(Long seccion, Long docente) throws Exception;
	
	
	public Long validarEtapaAsignación(Long meta) throws Exception;
	public Long validarEtapaSilabo(Long meta) throws Exception;
	public Long validarEtapaAdmision(Long annio, Long proceso) throws Exception;
	public void etapaAdmision(Long annio, Long proceso) throws Exception;
	
	public List<Seccion> listarAsistenciaDocente(Long institucion, Long annio, Long proceso, Long docente, Date fecha) throws Exception;
	public void actualizarEstadoSesion(Long seccion, Date fecha, Long ambiente) throws Exception;
	public AsistenciaDocente obtenerHorarioDocente(Long seccion, Date fecha, Long ambiente) throws Exception;
	public void actualizarSesion(AsistenciaDocente bean) throws Exception;
	public List<SilaboCalendario> listarAsistenciaAlumno(Long silabo, Long alumno) throws Exception ;
	public List<Seccion> listarUnidadesAlumno(Long institucion, Long annio, Long proceso, Long alumno) throws Exception;
	public List<Matricula> listarAlumnosSeccion(Long meta, Long unidad, Long seccion, Long docente) throws Exception;
	public void insertSilaboAlumno (Long meta, Long unidad, Long seccion, Long docente) throws Exception;
	public List<Seccion> listarSeccionesDocente(Long institucion, Long annio, Long proceso, Long docente) throws Exception;
	
	
}
