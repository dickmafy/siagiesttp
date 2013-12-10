package dataware.service.impl;
import dataware.dao.IntranetDao;
import dataware.service.IntranetService;

public class IntranetServiceImpl extends HorarioServiceImpl implements IntranetService
{
	private IntranetDao intranetDao;

	public IntranetDao getIntranetDao() 				{return intranetDao;}
	public void setIntranetDao(IntranetDao intranetDao) {this.intranetDao = intranetDao;}
	
	
}

