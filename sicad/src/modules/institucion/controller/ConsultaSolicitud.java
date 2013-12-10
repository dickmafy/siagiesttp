package modules.institucion.controller; 
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modules.administracion.domain.Solicitud;
import modules.seguridad.domain.Usuario;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.SeguridadService;

@ManagedBean
@SessionScoped
public class ConsultaSolicitud extends GenericController   
{
	private SeguridadService	myService;
    List<Solicitud> listadoSolicitudes;
    
    @Override
    public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
	
		appName="Institucion";
		moduleName="Solicitud";
		userName=usr.getUsuario();
		usr.getInstitucion();
		usr.getId();
		defaultList();
		
		page_main="itc_sol_lst_query";
		forward(page_main);
	}		
        
	@Override
	public void defaultList() throws Exception
	{
		
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");	
	
		listadoSolicitudes=myService.listaSolicitudInstitucion(usr.getInstitucion());	
	}

	public List<Solicitud> getListadoSolicitudes() 							{return listadoSolicitudes;}
	public void setListadoSolicitudes(List<Solicitud> listadoSolicitudes)   {this.listadoSolicitudes = listadoSolicitudes;}

	public SeguridadService getMyService() 									{return myService;}
	public void setMyService(SeguridadService myService) 					{this.myService = myService;}

	
} 

