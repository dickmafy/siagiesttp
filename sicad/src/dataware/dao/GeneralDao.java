package dataware.dao;
import java.util.List;

import modules.mantenimiento.domain.Supervision;

public interface GeneralDao extends SeguridadDao
{
	public List<Supervision> listarSupervision(Long dre, boolean tipo) throws Exception ;
}
