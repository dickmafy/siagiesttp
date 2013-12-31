package dataware.service.impl;
import java.util.List;

import modules.mantenimiento.domain.Supervision;
import dataware.dao.GeneralDao;
import dataware.service.GeneralService;

public class GeneralServiceImpl extends SeguridadServiceImpl implements GeneralService
{
	private GeneralDao generalDao;
	
	public GeneralDao getGeneralDao()					{return generalDao;}
	public void setGeneralDao(GeneralDao generalDao)	{this.generalDao = generalDao;}
	
	public List<Supervision> listarSupervision(Long dre, boolean tipo) throws Exception		
	{return getGeneralDao().listarSupervision(dre,tipo);}
}

