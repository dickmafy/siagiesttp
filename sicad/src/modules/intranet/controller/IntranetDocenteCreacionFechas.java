package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.HorarioService;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Personal;
import modules.horario.domain.Seccion;
import modules.seguridad.domain.Usuario;

public class IntranetDocenteCreacionFechas extends GenericController   
{
	private List<SelectItem>    profesionList;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    docenteList;
	
	List<MetaInstitucional> metas;
	private Long annio,proceso,profesion,turno;
	private Long institucion;
	private HorarioService	myService;
	
	public void init(Seccion pseccion) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();

		
		//defaultList();
		page_new="";
		page_main="IntranetDocenteCreacionFechas_list";
		page_update="";
		forward(page_main);
	}
	
	public void init() throws Exception
	{init(null);}
	
	
	
	
	public HorarioService getMyService() 							{return myService;}
	public void setMyService(HorarioService myService) 				{this.myService = myService;}

	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getProceso() 										{return proceso;}
	public void setProceso(Long proceso) 							{this.proceso = proceso;}

	public Long getProfesion() 										{return profesion;}
	public void setProfesion(Long profesion) 						{this.profesion = profesion;}

	public Long getTurno() 											{return turno;}
	public void setTurno(Long turno) 								{this.turno = turno;}
	
	public List<SelectItem> getProfesionList() 						{return profesionList;}
	public void setProfesionList(List<SelectItem> profesionList) 	{this.profesionList = profesionList;}
	
	public List<SelectItem> getProcesoList() 						{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 		{this.procesoList = procesoList;}

	public List<SelectItem> getDocenteList() 						{return docenteList;}
	public void setDocenteList(List<SelectItem> docenteList) 		{this.docenteList = docenteList;}

} 
