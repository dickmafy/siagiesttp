package modules.evaluaciones.controller; 
import java.util.List;

import javax.faces.model.SelectItem;

import modules.evaluaciones.domain.Instrumento;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.controller.GenericController;

public class EvaluacionInstrumento extends GenericController   
{	 
	private List<SelectItem> escalaList;
		
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Instrumento ";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="ins_new";
		page_main="ins_list";
		page_update="ins_update";
		forward(page_main);
	}
	
	public List<SelectItem> getEscalaList() 				{return escalaList;}
	public void setEscalaList(List<SelectItem> escalaList) 	{this.escalaList = escalaList;}
		
	@Override
	public void afterNew() throws Exception
	{	
		setEnabled(new Instrumento());		
		Instrumento object = (Instrumento)getBean();
		object.setAspectos(true);
		object.setAlineado(true);
		object.setObservados(true);
		object.setAutoevaluacion(true);
		
	}
	
	
	
	public void optionGoEncuesta() throws Exception
	{
		EvaluacionEncuesta go = (EvaluacionEncuesta)getSpringBean("evaluacionEncuesta");
		go.init(((Instrumento)getBeanSelected()).getId().longValue());
		
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Instrumento object = (Instrumento)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del Instrumento.");			
			success = false;
		}
		else if(!validateSelect(object.getEstado()))
		{
			setMessageError("Debe seleccionar un Estado.");			
			success = false;
		}
		else if(!validateEmpty(object.getObjetivos()))
		{
			setMessageError("Debe  ingresar los objetivos.");			
			success = false;
		}
		else if(!validateEmpty(object.getInstrucciones()))
		{
			setMessageError("Debe ingresar las instrucciones.");			
			success = false;
		}
		
		object=null;
		return success;
	}
	
} 
