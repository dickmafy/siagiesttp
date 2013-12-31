package modules.planificacion.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.PlanificacionService;
import modules.mantenimiento.domain.Especialidad;
import modules.planificacion.domain.Objetivo;
import modules.planificacion.domain.Politicas;
import modules.seguridad.domain.Usuario;

public class PlanificacionObjetivo extends GenericController   
{	
	private List<SelectItem> 	lineamientoList;
	private List<SelectItem>    variableList;
	
	private PlanificacionService myService;
	private Long institucion;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Objetivo";
		moduleName="Planificacion";
		userName=usr.getUsuario();
		
		institucion = usr.getInstitucion();
		
		defaultList();
		page_new="planificacion_objetivo_new";
		page_main="planificacion_objetivo_list";
		page_update="planificacion_objetivo_update";
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Objetivo bean = new Objetivo();
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		getBeanList();
		bean=null;
	}
	
	@Override
	public void afterNew() throws Exception 
	{
		setEnabled(new Objetivo());
		Objetivo bean = (Objetivo) getBean();
		bean.setInstitucion(institucion);
		variableList=getListSelectItem(new ArrayList(), "id", "nombre",true);
	}
	
	@Override
	public void afterLoad() throws Exception 
	{
		Politicas obj=new Politicas();
		Objetivo bean = (Objetivo) getBean();
		variableList=getListSelectItem(obj.obtenerVariables(bean.getLineamiento()), "id", "nombre",true);
	}
	
	public void selectLineamiento() throws Exception
	{
		Politicas obj=new Politicas();
		Objetivo bean = (Objetivo) getBean();
		if(bean.getLineamiento()>0L)
		{variableList=getListSelectItem(obj.obtenerVariables(bean.getLineamiento()), "id", "nombre",true);}
		else
		{variableList=getListSelectItem(new ArrayList(), "id", "nombre",true);}
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Objetivo object = (Objetivo)getBean();
		if(!validateSelect(object.getLineamiento()))
		{
			setMessageError("Debe ingresar el tipo de Objetivo.");			
			success = false;
		}
		else if(!validateSelect(object.getVariable()))
		{
			setMessageError("Debe ingresar la fecha correcta.");			
			success = false;
		}
		object=null;
		return success;
	} 
	
	public void goIndicador() throws Exception
	{
		PlanificacionIndicador go = (PlanificacionIndicador)getSpringBean("planificacionIndicador");
		go.init(((Objetivo)getBeanSelected()).getId().longValue());
	}
	public void goResultado() throws Exception
	{
		PlanificacionResultado go = (PlanificacionResultado)getSpringBean("planificacionResultado");
		go.init(((Objetivo)getBeanSelected()).getId().longValue());
	}
	
	
	public PlanificacionService getMyService() {		return myService;	}
	public void setMyService(PlanificacionService myService) {		this.myService = myService;	}

	public List<SelectItem> getLineamientoList() {	
		return lineamientoList;
	}

	public void setLineamientoList(List<SelectItem> lineamientoList) {
		this.lineamientoList = lineamientoList;
	}

	public List<SelectItem> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<SelectItem> variableList) {
		this.variableList = variableList;
	}
} 
