package dataware.dao.impl;
import java.text.SimpleDateFormat;
import dataware.dao.IntranetDao;

public class IntranetDaoJpa extends HorarioDaoJpa implements IntranetDao 
{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	
}
