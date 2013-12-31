package modules.seguridad.controller; 
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.domain.LogBase;

import modules.seguridad.domain.Usuario;

public class LogController extends GenericController   
{	 
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Configuración";
		moduleName="Seguridad - Logs ";
		userName=usr.getUsuario();
		
		defaultList();
		page_main="log_list";
		forward(page_main);
	}
		
	@Override
	public void defaultList() throws Exception 	
	{setBeanList(getService().listByObject(new LogBase()));}
} 
