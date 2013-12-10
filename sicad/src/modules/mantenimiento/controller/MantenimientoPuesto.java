package modules.mantenimiento.controller; 
import com.belogick.factory.util.controller.GenericController;
import modules.mantenimiento.domain.Puesto;
import modules.seguridad.domain.Usuario;

public class MantenimientoPuesto extends GenericController   
{	 
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Generales";
		moduleName="Cargos";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="puesto_new";
		page_main="puesto_list";
		page_update="puesto_update";
		forward(page_main);
	}
	
	@Override
	public void afterNew() throws Exception 
	{setEnabled(new Puesto());}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Puesto object = (Puesto)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del Cargo.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
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
