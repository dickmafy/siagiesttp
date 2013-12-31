package modules.cetpro.controller;

import com.belogick.factory.util.controller.GenericController;

import modules.cetpro.domain.ItinerarioCetpro;
import modules.mantenimiento.domain.Especialidad;
import modules.mantenimiento.domain.Profession;
import modules.seguridad.domain.Usuario;

public class CetproItinerario extends GenericController   
{	
	Long oferta;
	
	public void initBase(Long pk_oferta) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Cetpro";
		moduleName="Itinerario";
		userName=usr.getUsuario();
		oferta=pk_oferta;
		
		defaultList();
		page_new="cet_iti_new";
		page_main="cet_iti_list";
		page_update="cet_iti_upd";
		forward(page_main);
	}
		
	public void afterNew() throws Exception
	{setEnabled(new ItinerarioCetpro());}
	
	@Override
	public void init() throws Exception 
	{initBase(-1L);}
	
	@Override
	public void defaultList() throws Exception
	{
		ItinerarioCetpro bean = new ItinerarioCetpro();
		bean.setPk_oferta(oferta);
		
		getService().listByObjectEnabled(bean);
		setBeanList(getService().listByObjectEnabled(bean));
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		ItinerarioCetpro object = (ItinerarioCetpro)getBean();
		
		if(!validateEmpty(object.getTitulo()))
		{
			setMessageError("Debe ingresar el título.");			
			success = false;
		}
		else if(!validateZero(object.getHoras()))
		{
			setMessageError("Debe ingresar el número de horas.");			
			success = false;
		}
		else if(!validateZero(object.getSesiones()))
		{
			setMessageError("Debe ingresar el número de sesiones.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción.");			
			success = false;
		}
		
		object=null;
		return success;
	}
	

} 
