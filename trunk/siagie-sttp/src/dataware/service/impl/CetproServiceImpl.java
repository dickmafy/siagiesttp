package dataware.service.impl;
import dataware.dao.CetproDao;
import dataware.dao.InstitucionDao;
import dataware.service.InstitucionService;

public class CetproServiceImpl extends GeneralServiceImpl implements InstitucionService
{
	private CetproDao cetproDao;

	public CetproDao getCetproDao() 						{return cetproDao;}
	public void setCetproDao(CetproDao cetproDao) 			{this.cetproDao = cetproDao;}
	
	
}

