package dataware.dao.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import modules.horario.domain.Seccion;
import dataware.dao.IntranetDao;

public class IntranetDaoJpa extends HorarioDaoJpa implements IntranetDao 
{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
}
