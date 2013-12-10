package modules.mantenimiento.controller; 
import com.belogick.factory.util.controller.GenericController;
import modules.mantenimiento.domain.Puesto;
import modules.mantenimiento.domain.Requisito;
import modules.seguridad.domain.Usuario;

public class MantenimientoRequisito extends GenericController   
{	 
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Generales";
		moduleName="Cargos";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="requisito_new";
		page_main="requisito_list";
		page_update="requisito_update";
		forward(page_main);
	}
	
	@Override
	public void afterNew() throws Exception 
	{setEnabled(new Requisito());}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Requisito object = (Requisito)getBean();
		if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción del Cargo.");			
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
