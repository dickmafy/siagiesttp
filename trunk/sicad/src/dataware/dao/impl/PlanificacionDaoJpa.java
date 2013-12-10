package dataware.dao.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import modules.admision.domain.Matricula;
import modules.admision.domain.Postulante;
import modules.admision.domain.PostulanteRequisito;
import modules.admision.domain.Proceso;
import modules.admision.domain.ProcesoCronograma;
import modules.admision.domain.ProcesoOferta;
import modules.admision.domain.Requisitos;

import org.hibernate.Query;

import com.belogick.factory.util.support.DaoException;

import dataware.dao.AdmisionDao;
import dataware.dao.PlanificacionDao;

public class PlanificacionDaoJpa extends IntranetDaoJpa implements PlanificacionDao 
{
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
}
