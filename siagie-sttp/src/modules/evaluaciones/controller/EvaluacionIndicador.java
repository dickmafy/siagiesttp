package modules.evaluaciones.controller; 

import com.belogick.factory.util.controller.GenericController;
import modules.evaluaciones.domain.Indicadores;
import modules.seguridad.domain.Usuario;

public class EvaluacionIndicador extends GenericController   
{	 
		
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Indicador ";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="ind_new";
		page_main="ind_list";
		page_update="ind_update";
		forward(page_main);
		
	}
		
	
	@Override
	public void afterNew() throws Exception
	{
		setEnabled(new Indicadores());
		
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Indicadores object = (Indicadores)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del indicador.");			
			success = false;
		}
		
		object=null;
		return success;
	}  
	
	public void optionGoRango() throws Exception
	{
		EvaluacionRango go = (EvaluacionRango)getSpringBean("evaluacionRango");
		go.init(((Indicadores)getBeanSelected()).getId().longValue(), ((Indicadores)getBeanSelected()).getNombre());
	}
	
} 
