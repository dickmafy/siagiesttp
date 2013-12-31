package dataware.dao;
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

public interface IntranetDao extends HorarioDao
{
	public List<Seccion> listarSilabos(Long institucion, Long annio, Long proceso, Long docente) throws Exception;
	public void insertarCriterios(List<ReferenteEducativo> criterio_prof, List<Transversal> criterio_trans, Long seccion, Long tipo) throws DaoException;
	public List<SilaboNota> criteriosSeleccionados(Long seccion) throws Exception;

	public List<SilaboContenido> listarContenido(Long seccion) throws Exception;
	public void eliminarContenido(Long primaria) throws Exception;
	public void actualizarSemana(Long contenido, Long semana) throws Exception;
	
	public List<AsistenciaDocente> listarSesiones(Long seccion, Long ambiente) throws Exception;
	public List<AsistenciaAlumno> listarAsistencia(Long seccion, Long ambiente, Date fecha) throws Exception;
	public List<Ambiente> listarAmbientes(Long seccion) throws Exception;
}
