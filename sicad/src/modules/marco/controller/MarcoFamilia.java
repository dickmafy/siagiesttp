package modules.marco.controller; 
import com.belogick.factory.util.controller.GenericController;
import modules.marco.domain.Familia;
import modules.seguridad.domain.Usuario;

public class MarcoFamilia extends GenericController   
{	 

	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Marco Educativo";
		moduleName="Familia Profesional";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="fampro_new";
		page_main="fampro_list";
		page_update="fampro_update";
		forward(page_main);
	}
		
	@Override
	public void afterNew() throws Exception
	{setEnabled(new Familia());}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Familia object = (Familia)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre de la Familia Profesional.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción.");			
			success = false;
		}
		object=null;
		return success;
	}
} 
