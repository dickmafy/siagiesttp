package modules.seguridad.controller; 
import com.belogick.factory.util.controller.GenericController;
import modules.seguridad.domain.Variable;
import modules.seguridad.domain.Usuario;
import dataware.service.SeguridadService;

public class VariableController extends GenericController   
{	 
	private SeguridadService	myService;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Configuración";
		moduleName="Seguridad - Variables ";
		userName=usr.getUsuario();
		
		defaultList();
		page_main="var_list";
		page_update="var_update";
		forward(page_main);
	}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Variable object = (Variable)getBean();
		if(!validateEmpty(object.getValor()))
		{
			setMessageError("Debe ingresar el valor de la variable.");			
			success = false;
		}
		object=null;
		return success;
	}   
	
	public SeguridadService getMyService() 					{return myService;}	
	public void setMyService(SeguridadService myService)	{this.myService = myService;}
	
	
} 
