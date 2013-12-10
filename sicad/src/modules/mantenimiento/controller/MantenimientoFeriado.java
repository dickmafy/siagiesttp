package modules.mantenimiento.controller; 
import com.belogick.factory.util.controller.GenericController;
import modules.mantenimiento.domain.FeriadoGeneral;
import modules.seguridad.domain.Usuario;

public class MantenimientoFeriado extends GenericController   
{	 
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Generales";
		moduleName="Feriado";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="feriado_new";
		page_main="feriado_list";
		page_update="feriado_update";
		forward(page_main);
		uppercase=true;
	}
	
	@Override
	public void afterNew() throws Exception 
	{setEnabled(new FeriadoGeneral());}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		FeriadoGeneral object = (FeriadoGeneral)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del Día Feriado.");			
			success = false;
		}
		if(!validateEmpty(object.getFechaInicio()))
		{
			setMessageError("Debe ingresar la fecha de inicio del feriado.");			
			success = false;
		}
		if(!validateEmpty(object.getFechaFin()))
		{
			setMessageError("Debe ingresar la fecha final del feriado.");			
			success = false;
		}
		else if(!validateSelect(object.getEstado()))
		{
			setMessageError("Debe seleccionar un estado.");			
			success = false;
		}
		else if(object.getFechaFin().before(object.getFechaInicio()))
		{
			setMessageError("La fecha final no puede ser menor a la fecha de inicio del feriado.");			
			success = false;
		}
		object=null;
		return success;
	} 
	
} 
