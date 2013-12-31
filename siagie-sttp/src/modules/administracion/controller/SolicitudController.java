package modules.administracion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import modules.administracion.domain.Solicitud;
import modules.administracion.domain.SolicitudMovimiento;
import modules.seguridad.domain.Usuario;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import com.belogick.factory.util.support.ServiceException;
import dataware.service.SeguridadService;

@ManagedBean
@SessionScoped
public class SolicitudController extends GenericController   
{
	private boolean evaluate=false;
	private Long institucion;
    private Long usuario;
    private SeguridadService	myService;
    List<Solicitud> listadoSolicitudes;
    private Solicitud solicitud;
    private int numeroSolicitudes;
    private StreamedContent file;
    private boolean existeDoc;

	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
	
		appName="Institucion";
		moduleName="Solicitud";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		usuario=usr.getId();
		
		defaultList();

		if(!evaluate)	
		{
			page_main="solicitud_list";
			page_new="solicitud_new";
			page_update="solicitud_update";
		}
		else
		{
			page_main="solicitud_eval";
		}
		forward(page_main);
		
		
	}		
    
    public void initEval() throws Exception
    {
    	evaluate=true;
    	init();
    }
	
	@Override
	public void defaultList() throws Exception
	{

		if(evaluate){
			listadoSolicitudes=myService.listarSolicitudes();	
			setBeanList(getService().listByObject(listadoSolicitudes)); 
			
			numeroSolicitudes = listadoSolicitudes.size();
		}
		solicitud  = null;
	}
	
	@Override
	public void beforeSave() throws Exception 
	{
		if(!evaluate)
		{
			Solicitud solicitud = (Solicitud)getBean();
			solicitud.setInstitucion(institucion);
			solicitud.setEstado(1L);
			setBean(solicitud);
			solicitud = null;
		}
	}
	
	public void aprobarSolicitud() throws Exception
	{
		Solicitud solicitud =  (Solicitud) getBeanSelected();
		status(solicitud, 3L);
		guardarSolicitudMovimiento(3L,((Solicitud)getBeanSelected()).getId());
		defaultList();
		setMessageSuccess("La solicitud ha sido Aprobada");
		forward(page_main);
	}

	public void rehazarSolicitud() throws Exception
	{
		
		Solicitud solicitud =  (Solicitud) getBeanSelected();
		status(solicitud,4L);
		guardarSolicitudMovimiento(4L,((Solicitud)getBeanSelected()).getId());
		defaultList();
		setMessageSuccess("La solicitud ha sido Rechazada");
		forward(page_main);
	}
	
	public void ejecutarSolicitud()throws Exception{
		Solicitud solicitud =  (Solicitud) getBeanSelected();
		status(solicitud, 5L);
		guardarSolicitudMovimiento(5L,((Solicitud)getBeanSelected()).getId());
		defaultList();
		setMessageSuccess("La solicitud ha sido Ejecutada");	
	}
	
	public void detalleSolicitud()throws Exception{
		
		try {			
			Solicitud item = (Solicitud)getBeanSelected();
			
			setBean(item);
			page_update="solicitud_eval_update";
			
			try {
				new FileInputStream(getServletContext().getRealPath("/recursos/documentos/solicitud/"+item.getId()+".pdf"));
	    		existeDoc = true;
	    	} catch (FileNotFoundException e) {existeDoc = false;}
			
			forward(page_update);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	

	public void enviarSolicitud() throws Exception
	{
		status(getBeanSelected(), 2L);
		guardarSolicitudMovimiento(2L,((Solicitud)getBeanSelected()).getId());	
		defaultList();
	}
	
	private void guardarSolicitudMovimiento(Long estado, Long solicitud) throws ServiceException 
	{
		SolicitudMovimiento solicitudMovimiento = new SolicitudMovimiento();
		solicitudMovimiento.setSolicitud(solicitud);
		solicitudMovimiento.setFecha(DateHelper.getDate());
		solicitudMovimiento.setObservacion("--");
		solicitudMovimiento.setOperacion(estado);
		solicitudMovimiento.setUsuario(usuario);
		getService().save(solicitudMovimiento);
	}
	

	public void rechazarSolicitud() throws Exception{
		Solicitud temporal = (Solicitud) getBean();
		Long motivoTemporal = temporal.getMotivo();
		String obs=temporal.getObservacion();
		try {
			temporal = (Solicitud) myService.findById(Solicitud.class,temporal.getId());
			temporal.setObservacion(obs);
			temporal.setMotivo(motivoTemporal);
			getService().save(temporal);
			rehazarSolicitud();
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public StreamedContent getFile() 							
	{
		Solicitud object = (Solicitud)getBeanSelected();
		
		try 
		{
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/solicitud/"+object.getId()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Solicitud object = (Solicitud)getBean();
		
		if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción.");			
			success = false;
		}
		else if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe ingresar el tipo.");			
			success = false;
		}
		
		else if(!validateEmpty(object.getFecha()))
		{
			setMessageError("Debe ingresar la fecha.");			
			success = false;
		}
		
		object=null;
		return success;
	}  
	
	

	public List<Solicitud> getListadoSolicitudes() 							{return listadoSolicitudes;}
	public void setListadoSolicitudes(List<Solicitud> listadoSolicitudes) 	{this.listadoSolicitudes = listadoSolicitudes;}

	public SeguridadService getMyService() 									{return myService;}
	public void setMyService(SeguridadService myService) 					{this.myService = myService;}
	
	public Solicitud getSolicitud() 										{return solicitud;}
	public void setSolicitud(Solicitud solicitud) 							{this.solicitud = solicitud;}

	public int getNumeroSolicitudes() 										{return numeroSolicitudes;}
	public void setNumeroSolicitudes(int numeroSolicitudes) 				{this.numeroSolicitudes = numeroSolicitudes;}
	
	public boolean isExisteDoc() 											{return existeDoc;}
} 

