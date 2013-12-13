package dataware.dao;
import java.util.List;

import com.belogick.factory.util.support.DaoException;

import modules.administracion.domain.Oferta;
import modules.admision.domain.Interes;
import modules.admision.domain.Matricula;
//import modules.admision.domain.MatriculaSeccion;
import modules.admision.domain.Persona;
import modules.admision.domain.Postulante;
import modules.admision.domain.Proceso;
import modules.admision.domain.ProcesoCronograma;
import modules.admision.domain.ProcesoOferta;
import modules.admision.domain.Requisitos;
import modules.horario.domain.Seccion;

public interface AdmisionDao extends HorarioDao
{
	public Persona encontrarPersona(Long docTip, String docNro) throws Exception;
	public void insertarIntereses(List<Oferta> lista, Long pk_interesado) throws Exception;
	public List<Interes> obtenerIntereses(Long interesado) throws Exception;
	
	public List<Proceso> listarProcesos(Long institucion) throws Exception ;
	public List<ProcesoCronograma> listarProcesoCronograma(Long proceso) throws Exception ;
	public List<ProcesoOferta> listarProcesoOferta(Long proceso) throws Exception ;
	public void actualizarProcesoCronograma(List<ProcesoCronograma> lista, Long proceso) throws Exception;
	
	public List<Postulante> listarPostulante(Long proceso, Long tipo) throws Exception ;
	public List<Requisitos> listarRequisitos(Long institucion, Long tipo, Long modalidad) throws Exception ;
	public List<Requisitos> listarRequisitosPostulante(Long postulante, Long institucion, Long modalidad) throws Exception;
	public void insertarRequisitos(boolean proceso, List<Requisitos> lista, Long primaria) throws DaoException ;
	public void eliminarRequisito(boolean proceso, Long primaria) throws Exception;
	public List<Seccion> listarUnidades(Long institucion, Long annio, Long proceso, Long profesion, Long turno, Long modulo) throws Exception;
	public void actualizarMatriculaSeccion(List<Seccion> lista, Long matricula) throws Exception ;
	
	public void procesarIngresantes(Long proceso) throws Exception;
	public List<Matricula> listarMatricula(Long proceso) throws Exception;
	public List<Requisitos> listarRequisitosMatricula(Long matricula, Long institucion, Long modalidad) throws Exception;
	
	public void eliminarRequisito(Requisitos bean) throws Exception ;
	//public List<MatriculaSeccion> listarUnidadesDisponibles(Long persona, Long institucion, Long profesion, Long modulo, Long tipo) throws Exception;
}
