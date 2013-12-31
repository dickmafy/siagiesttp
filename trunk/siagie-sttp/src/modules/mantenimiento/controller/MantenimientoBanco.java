package modules.mantenimiento.controller; 
import com.belogick.factory.util.controller.GenericController;
import modules.mantenimiento.domain.Banco;
import modules.seguridad.domain.Usuario;

public class MantenimientoBanco extends GenericController   
{	 
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Generales";
		moduleName="Banco";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="banco_new";
		page_main="banco_list";
		page_update="banco_update";
		forward(page_main);
	}
	
	@Override
	public void afterNew() throws Exception 
	{setEnabled(new Banco());}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Banco object = (Banco)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del Banco.");			
			success = false;
		}
		else if(!validateEmpty(object.getSiglas()))
		{
			setMessageError("Debe ingresar las siglas del Banco.");			
			success = false;
		}
		object=null;
		return success;
	} 
	
} 
