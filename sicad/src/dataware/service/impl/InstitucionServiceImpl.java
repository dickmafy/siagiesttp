package dataware.service.impl;
import dataware.dao.InstitucionDao;
import dataware.service.InstitucionService;

public class InstitucionServiceImpl extends GeneralServiceImpl implements InstitucionService
{
	private InstitucionDao institucionDao;
	
	public InstitucionDao getInstitucionDao()						{return institucionDao;}
	public void setInstitucionDao(InstitucionDao institucionDao)	{this.institucionDao = institucionDao;}
}

