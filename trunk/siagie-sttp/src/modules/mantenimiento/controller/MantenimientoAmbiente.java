package modules.mantenimiento.controller;
import com.belogick.factory.util.controller.GenericController;
import modules.mantenimiento.domain.TipoAmbiente;
import modules.seguridad.domain.Usuario;

public class MantenimientoAmbiente extends GenericController   
{	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Mantenimiento";
		moduleName="Ambiente";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="tipoamb_new";
		page_main="tipoamb_lst";
		page_update="tipoamb_upd";
		forward(page_main);
	}
		
	public void afterNew() throws Exception
	{
		setEnabled(new TipoAmbiente());
	
	}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(getService().listByObjectEnabledDisabled(new TipoAmbiente()));}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		TipoAmbiente object = (TipoAmbiente)getBean();
		
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del tipo de ambiente.");			
			success = false;
		}
	
		
		object=null;
		return success;
	}
	

} 
