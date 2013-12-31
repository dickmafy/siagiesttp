package modules.evaluaciones.controller; 


import modules.evaluaciones.domain.Escala;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.controller.GenericController;

public class EvaluacionEscala extends GenericController   
{	 
		
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Escala ";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="esc_new";
		page_main="esc_list";
		page_update="esc_update";
		forward(page_main);		
	}
	@Override
	public void afterNew() throws Exception
	{
		setEnabled(new Escala());		
	}
	
	public void optionGoDetalle() throws Exception
	{
		EvaluacionEscalaDetalle go = (EvaluacionEscalaDetalle)getSpringBean("evaluacionEscalaDetalle");
		go.init(((Escala)getBeanSelected()).getId().longValue(), ((Escala)getBeanSelected()).getNombre());
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Escala object = (Escala)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre de la Escala.");			
			success = false;
		}else if(!validateEmpty(object.getDescripcion())){
			setMessageError("Debe ingresar la descripción de la Escala.");			
			success = false;
		}
		
		object=null;
		return success;
	}   
		
	
} 
