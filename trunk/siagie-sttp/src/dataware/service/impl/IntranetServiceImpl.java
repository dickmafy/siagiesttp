package dataware.service.impl;
import java.util.Date;
import java.util.List;

import modules.administracion.domain.Ambiente;
import modules.horario.domain.AsistenciaAlumno;
import modules.horario.domain.AsistenciaDocente;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboContenido;
import modules.horario.domain.SilaboNota;
import modules.marco.domain.ReferenteEducativo;
import modules.marco.domain.Transversal;

import com.belogick.factory.util.support.DaoException;

import dataware.dao.IntranetDao;
import dataware.service.IntranetService;

public class IntranetServiceImpl extends HorarioServiceImpl implements IntranetService
{
	private IntranetDao intranetDao;

	public IntranetDao getIntranetDao() 				{return intranetDao;}
	public void setIntranetDao(IntranetDao intranetDao) {this.intranetDao = intranetDao;}
	
	public void insertarCriterios(List<ReferenteEducativo> criterio_prof, List<Transversal> criterio_trans, Long seccion, Long tipo) throws DaoException
	{intranetDao.insertarCriterios(criterio_prof, criterio_trans, seccion, tipo);}
	
	public List<Seccion> listarSilabos(Long institucion, Long annio, Long proceso, Long docente) throws Exception
	{return getIntranetDao().listarSilabos(institucion, annio, proceso, docente);}
	
	public List<SilaboNota> criteriosSeleccionados(Long seccion) throws Exception
	{return intranetDao.criteriosSeleccionados(seccion);}

	public List<SilaboContenido> listarContenido(Long seccion) throws Exception
	{return intranetDao.listarContenido(seccion);}
	
	public void eliminarContenido(Long primaria) throws Exception
	{intranetDao.eliminarContenido(primaria);}
	
	public void actualizarSemana(Long contenido, Long semana) throws Exception
	{intranetDao.actualizarSemana(contenido, semana);}
	
	public List<AsistenciaDocente> listarSesiones(Long seccion, Long ambiente) throws Exception
	{return intranetDao.listarSesiones(seccion,ambiente);}
	
	public List<AsistenciaAlumno> listarAsistencia(Long seccion, Long ambiente, Date fecha) throws Exception
	{return intranetDao.listarAsistencia(seccion, ambiente, fecha);}
	
	public List<Ambiente> listarAmbientes(Long seccion) throws Exception
	{return intranetDao.listarAmbientes(seccion);}
	
}

