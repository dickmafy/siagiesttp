package modules.mantenimiento.controller;

import com.belogick.factory.util.controller.GenericController;

import modules.mantenimiento.domain.Especialidad;
import modules.mantenimiento.domain.Profession;
import modules.seguridad.domain.Usuario;

public class MantenimientoProfesion extends GenericController   
{	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Mantenimiento";
		moduleName="Profesion";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="profession_new";
		page_main="profession_lst";
		page_update="profession_upd";
		forward(page_main);
	}
		
	public void afterNew() throws Exception
	{
		setEnabled(new Profession());
	
	}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(getService().listByObjectEnabledDisabled(new Profession()));}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Profession object = (Profession)getBean();
		
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre de la Profesion.");			
			success = false;
		}
	
		
		object=null;
		return success;
	}
	

} 
