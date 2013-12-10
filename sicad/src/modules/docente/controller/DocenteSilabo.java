package modules.docente.controller; 
import com.belogick.factory.util.controller.GenericController;

import modules.evaluaciones.domain.Aspecto;
import modules.evaluaciones.domain.EscalaDetalle;
import modules.evaluaciones.domain.Pregunta;
import modules.seguridad.domain.Usuario;

public class DocenteSilabo extends GenericController   
{		
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Aspecto ";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="asp_new";
		page_main="asp_list";
		page_update="asp_update";
		forward(page_main);		
	}
	
	@Override
	public void afterNew() throws Exception
	{
		setEnabled(new Aspecto());		
	}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Aspecto object = (Aspecto)getBean();
		if(!validateEmpty(object.getSigla()))
		{
			setMessageError("Debe ingresar una sigla.");			
			success = false;
		}
		else if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar un nombre de aspecto.");			
			success = false;
		}
		
		object=null;
		return success;
	}   
	
	
} 
