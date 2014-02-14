package dataware.dao;
import java.util.List;

import modules.administracion.domain.AmbienteUnidad;
import modules.marco.domain.Itinerario;
import modules.marco.domain.ItinerarioTransversal;
import modules.marco.domain.Profesion;
import modules.marco.domain.ReferenteEducativo;
import modules.marco.domain.ReferenteProductivo;
import modules.marco.domain.Transversal;

public interface MarcoDao extends InstitucionDao
{
	public List<Profesion> listarProfesiones() throws Exception ;
	public List<Transversal> listarTransversal(boolean tipo, Long profesion) throws Exception ;
	public void eliminarTransversal(Long profesion, Long modulo) throws Exception;
	public List<ReferenteProductivo> listarReferenteProductivo(Long profesion, int opcion, Long tipo) throws Exception;
	public List<ReferenteEducativo> listarReferenteEducativo(Long profesion, int opcion, Long tipo) throws Exception;
	public List<Itinerario> listarItinerario(Long profesion, Long modulo, Long tipo) throws Exception ;
	public List<Transversal> listarReferenteTransversal(int opcion, Long modulo) throws Exception;
	public List<ItinerarioTransversal> listarItinerarioTransversal(Long tipo) throws Exception;
	public List<Itinerario> listarItinerarioTotal(Long profesion) throws Exception;
	public List<AmbienteUnidad> listarAmbienteTipo(Long institucion, Long unidad) throws Exception ;
	public void eliminarAmbienteTipo(Long id) throws Exception;
	public List<Itinerario> listarModulos(Long profesion) throws Exception;
}
