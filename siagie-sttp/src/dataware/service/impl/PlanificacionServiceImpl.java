package dataware.service.impl;
import dataware.dao.PlanificacionDao;
import dataware.service.PlanificacionService;

public class PlanificacionServiceImpl extends IntranetServiceImpl implements PlanificacionService
{
	private PlanificacionDao planificacionDao;

	public PlanificacionDao getPlanificacionDao() 						{return planificacionDao;}
	public void setPlanificacionDao(PlanificacionDao planificacionDao) 	{this.planificacionDao = planificacionDao;}
	
	
}

