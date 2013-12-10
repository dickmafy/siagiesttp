package dataware.service;
import java.util.List;

import modules.mantenimiento.domain.Supervision;
public interface GeneralService extends SeguridadService
{
	public List<Supervision> listarSupervision(Long dre, boolean tipo) throws Exception;
}
