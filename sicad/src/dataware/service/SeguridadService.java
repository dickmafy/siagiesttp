package dataware.service;
import java.util.Date;
import java.util.List;
import modules.administracion.domain.Convenio;
import modules.administracion.domain.Cronograma;
import modules.administracion.domain.HistorialLaboral;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.MetaOcupacional;
import modules.administracion.domain.Oferta;
import modules.administracion.domain.Solicitud;
import modules.admision.domain.Postulante;
import modules.admision.domain.Proceso;
import modules.admision.domain.Retiro;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Menu;
import modules.seguridad.domain.MenuAcceso;
import modules.seguridad.domain.Usuario;
import modules.seguridad.domain.VariableAcceso;
import com.belogick.factory.util.service.GenericService;
import com.belogick.factory.util.support.DaoException;

public interface SeguridadService extends GenericService
{
	public Usuario obtenerUsuario(String usuario) throws Exception	;
	public MenuAcceso listarAccesos(Long perfil, Long principal) throws Exception;
	public VariableAcceso listarVariables() throws Exception;
	public void insertarAcceso(List<Menu> lista, Long perfil) throws DaoException;
	
	public List<Ubigeo> listarUbigeos(Long dep, Long pro) throws Exception ;
	public List<Oferta> listarOferta(Long institucion, Date fecha, Long tipo) throws Exception;
	public List<Cronograma> listarPeriodo(Long institucion, Date fecha, Long tipo) throws Exception;
	
	public Proceso existeProceso(Long institucion, Long annio) throws Exception ;
	public boolean existeFechaProceso(Long proceso, Long actividad, Date fecha) throws Exception ;
	public Long obtenerUbigeo(Long ubigeo, boolean tipo) throws Exception;
	public Postulante obtenerIngresante(Long proceso, String dni) throws Exception ;
	public Retiro   retirar_matriculado(Long proceso, String dni) throws Exception ;
	public List<Solicitud> listarSolicitudes() throws Exception;
	public List<Solicitud> listaSolicitudInstitucion(Long codigo) throws Exception;
	
	public List<Convenio> listarConvenios(Long pk_institucion) throws Exception;
	public List<MetaInstitucional> listarMetaInstitucional(Long institucion, boolean tipo) throws Exception;
	public List<MetaOcupacional> listarMetaOcupacional(Long institucion) throws Exception;
	public List<HistorialLaboral> listarHistorialLaboral(Long personal) throws Exception;
	
}
