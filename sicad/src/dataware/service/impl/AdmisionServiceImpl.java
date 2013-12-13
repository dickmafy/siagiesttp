package dataware.service.impl;
import java.util.List;

import com.belogick.factory.util.support.DaoException;

import modules.administracion.domain.Oferta;
import modules.admision.domain.Interes;
import modules.admision.domain.Matricula;
//import modules.admision.domain.MatriculaSeccion;
import modules.admision.domain.Persona;
import modules.admision.domain.Postulante;
import modules.admision.domain.PostulanteRequisito;
import modules.admision.domain.Proceso;
import modules.admision.domain.ProcesoCronograma;
import modules.admision.domain.ProcesoOferta;
import modules.admision.domain.Requisitos;
import modules.horario.domain.Seccion;
import dataware.dao.AdmisionDao;
import dataware.service.AdmisionService;

public class AdmisionServiceImpl extends HorarioServiceImpl implements AdmisionService
{
	
	private AdmisionDao admisionDao;

	public AdmisionDao getAdmisionDao()						{return admisionDao;}
	public void setAdmisionDao(AdmisionDao admisionDao) 	{this.admisionDao = admisionDao;}
	
	public Persona encontrarPersona(Long docTip, String docNro) throws Exception
	{return getAdmisionDao().encontrarPersona(docTip, docNro);}
	
	public void insertarIntereses(List<Oferta> lista, Long pk_interesado) throws Exception
	{getAdmisionDao().insertarIntereses(lista, pk_interesado);}
	
	public List<Interes> obtenerIntereses(Long interesado) throws Exception
	{return getAdmisionDao().obtenerIntereses(interesado);}
	
	
	
		
	public List<Proceso> listarProcesos(Long institucion) throws Exception 
	{return getAdmisionDao().listarProcesos(institucion);}
	
	public List<ProcesoOferta> listarProcesoOferta(Long proceso) throws Exception 
	{return getAdmisionDao().listarProcesoOferta(proceso);}
	
	public List<ProcesoCronograma> listarProcesoCronograma(Long proceso) throws Exception 
	{return getAdmisionDao().listarProcesoCronograma(proceso);}
	
	public void actualizarProcesoCronograma(List<ProcesoCronograma> lista, Long proceso) throws Exception
	{getAdmisionDao().actualizarProcesoCronograma(lista,proceso);}
	
	
	
	public List<Postulante> listarPostulante(Long proceso, Long tipo) throws Exception 
	{return getAdmisionDao().listarPostulante(proceso, tipo);}
	
	public List<Requisitos> listarRequisitos(Long institucion, Long tipo, Long modalidad) throws Exception 
	{return getAdmisionDao().listarRequisitos(institucion, tipo, modalidad);}	
	
	public List<Requisitos> listarRequisitosPostulante(Long postulante, Long institucion, Long modalidad) throws Exception
	{return getAdmisionDao().listarRequisitosPostulante(postulante, institucion, modalidad);}
	
	public void insertarRequisitos(boolean proceso, List<Requisitos> lista, Long primaria) throws DaoException 
	{getAdmisionDao().insertarRequisitos(proceso, lista, primaria);}
	
	public void eliminarRequisito(boolean proceso, Long primaria) throws Exception
	{getAdmisionDao().eliminarRequisito(proceso, primaria);}
	
	public List<Seccion> listarUnidades(Long institucion, Long annio, Long proceso, Long profesion, Long turno, Long modulo) throws Exception
	{return getAdmisionDao().listarUnidades(institucion, annio, proceso, profesion, turno, modulo);}
	
	public void actualizarMatriculaSeccion(List<Seccion> lista, Long matricula) throws Exception 
	{getAdmisionDao().actualizarMatriculaSeccion(lista, matricula);}
	
	
	public void procesarIngresantes(Long proceso) throws Exception
	{getAdmisionDao().procesarIngresantes(proceso);}
	
	public List<Matricula> listarMatricula(Long proceso) throws Exception
	{return getAdmisionDao().listarMatricula(proceso);}
	
	public List<Requisitos> listarRequisitosMatricula(Long matricula, Long institucion, Long modalidad) throws Exception
	{return getAdmisionDao().listarRequisitosMatricula(matricula, institucion, modalidad);}
	
	public void eliminarRequisito(Requisitos bean) throws Exception 
	{getAdmisionDao().eliminarRequisito(bean);}
	
	//public List<MatriculaSeccion> listarUnidadesDisponibles(Long persona, Long institucion, Long profesion, Long modulo, Long tipo) throws Exception 
	//{return getAdmisionDao().listarUnidadesDisponibles(persona, institucion, profesion, modulo, tipo);}
}

