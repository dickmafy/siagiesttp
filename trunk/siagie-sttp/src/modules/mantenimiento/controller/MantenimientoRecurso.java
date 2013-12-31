package modules.mantenimiento.controller; 
import com.belogick.factory.util.controller.GenericController;
import modules.mantenimiento.domain.Recurso;
import modules.seguridad.domain.Usuario;

public class MantenimientoRecurso extends GenericController   
{	 
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Generales";
		moduleName="Recursos";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="recurso_new";
		page_main="recurso_list";
		page_update="recurso_update";
		forward(page_main);
		uppercase=false;
	}
	
	@Override
	public void afterNew() throws Exception 
	{setEnabled(new Recurso());}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Recurso object = (Recurso)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del Recurso.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción del Recurso.");			
			success = false;
		}
		else if(!validateSelect(object.getEstado()))
		{
			setMessageError("Debe seleccionar un estado.");			
			success = false;
		}
		object=null;
		return success;
	} 
} 
