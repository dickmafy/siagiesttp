package dataware.service;
import java.util.List;

import com.belogick.factory.util.support.DaoException;

import modules.administracion.domain.Ambiente;
import modules.administracion.domain.AmbienteUnidad;
import modules.administracion.domain.MetaDetalle;
import modules.administracion.domain.MetaInstitucional;
import modules.horario.domain.HorarioDistribucion;
import modules.horario.domain.Seccion;

public interface HorarioService extends MarcoService
{
	public List<MetaInstitucional> listarMetaInstitucional(Long institucion, Long annio, Long proceso) throws Exception;
	public List<MetaDetalle> listarMetaDetalle(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception;
	public List<Seccion> listarSeccion(Long meta) throws Exception;
	public void insertarDistribucion(List<HorarioDistribucion> lista, Long seccion, Long ambienteOld) throws DaoException;
	public List<HorarioDistribucion> listarDistribucion(Long tipo, Long seccion, Long ambiente) throws Exception;
	public List<Ambiente> listarAmbientes(Long institucion, Long tipo, Long capacidad) throws Exception;
	public List<AmbienteUnidad> listarAmbienteUnidad(Long institucion, Long seccion, Long unidad) throws Exception;
	public void actualizarAvance(Long metaDetalle) throws Exception;
	public List<Seccion> listarSecciones(Long institucion, Long annio, Long proceso, Long profesion, Long turno) throws Exception;
	public boolean validarDisponibilidad(Long seccion, Long docente) throws Exception;
	public boolean validarHoras(Long docente, Long horas) throws Exception ;
	public void actualizarDocente(Long seccion, Long docente) throws Exception;
	public List<Seccion> listarSeccionesDocente(Long institucion, Long annio, Long proceso, Long docente) throws Exception;
}
