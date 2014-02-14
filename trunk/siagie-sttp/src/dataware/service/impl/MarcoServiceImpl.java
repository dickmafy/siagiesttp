package dataware.service.impl;
import java.util.List;

import modules.administracion.domain.AmbienteUnidad;
import modules.marco.domain.Itinerario;
import modules.marco.domain.ItinerarioTransversal;
import modules.marco.domain.Profesion;
import modules.marco.domain.ReferenteEducativo;
import modules.marco.domain.ReferenteProductivo;
import modules.marco.domain.Transversal;
import dataware.dao.MarcoDao;
import dataware.service.MarcoService;

public class MarcoServiceImpl extends InstitucionServiceImpl implements MarcoService
{
	private MarcoDao marcoDao;
	
	public MarcoDao getMarcoDao() 									{return marcoDao;}
	public void setMarcoDao(MarcoDao marcoDao) 						{this.marcoDao = marcoDao;}	
	
	public List<Profesion> listarProfesiones() throws Exception 
	{return getMarcoDao().listarProfesiones();}
	
	public List<Transversal> listarTransversal(boolean tipo, Long profesion) throws Exception 
	{return getMarcoDao().listarTransversal(tipo,profesion);}
	
	public void eliminarTransversal(Long profesion, Long modulo) throws Exception
	{getMarcoDao().eliminarTransversal(profesion,modulo);}
	
	public List<ReferenteProductivo> listarReferenteProductivo(Long profesion, int opcion, Long tipo) throws Exception
	{return getMarcoDao().listarReferenteProductivo(profesion,opcion,tipo);}
	
	public List<ReferenteEducativo> listarReferenteEducativo(Long profesion, int opcion, Long tipo) throws Exception
	{return getMarcoDao().listarReferenteEducativo(profesion,opcion,tipo);}
	
	public List<Itinerario> listarItinerario(Long profesion, Long modulo, Long tipo) throws Exception 
	{return getMarcoDao().listarItinerario(profesion,modulo, tipo);}
	
	public List<Transversal> listarReferenteTransversal(int opcion, Long modulo) throws Exception
	{return getMarcoDao().listarReferenteTransversal(opcion,modulo);}
	
	public List<ItinerarioTransversal> listarItinerarioTransversal(Long tipo) throws Exception
	{return getMarcoDao().listarItinerarioTransversal(tipo);}
	
	public List<Itinerario> listarItinerarioTotal(Long profesion) throws Exception
	{return getMarcoDao().listarItinerarioTotal(profesion);}
	
	public List<AmbienteUnidad> listarAmbienteTipo(Long institucion, Long unidad) throws Exception 
	{return getMarcoDao().listarAmbienteTipo(institucion, unidad);}
	
	public void eliminarAmbienteTipo(Long id) throws Exception
	{getMarcoDao().eliminarAmbienteTipo(id);}
	
	public List<Itinerario> listarModulos(Long profesion) throws Exception
	{return getMarcoDao().listarModulos(profesion);}
}

