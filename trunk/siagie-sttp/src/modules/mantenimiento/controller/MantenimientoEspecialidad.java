package modules.mantenimiento.controller;

import com.belogick.factory.util.controller.GenericController;
import modules.mantenimiento.domain.Especialidad;
import modules.seguridad.domain.Usuario;

public class MantenimientoEspecialidad extends GenericController   
{	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Mantenimiento";
		moduleName="Especialidad";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="espec_new";
		page_main="espec_list";
		page_update="espec_update";
		forward(page_main);
	}
		
	public void afterNew() throws Exception
	{
		setEnabled(new Especialidad());
	
	}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(getService().listByObjectEnabledDisabled(new Especialidad()));}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Especialidad object = (Especialidad)getBean();
		
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre de la Especialidad.");			
			success = false;
		}
	
		
		object=null;
		return success;
	}
	

} 
