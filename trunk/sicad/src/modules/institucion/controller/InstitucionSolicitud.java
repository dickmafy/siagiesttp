package modules.institucion.controller; 

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import modules.administracion.domain.Convenio;
import modules.administracion.domain.Solicitud;
import modules.administracion.domain.SolicitudMovimiento;
import modules.mantenimiento.domain.Resolucion;
import modules.seguridad.domain.Usuario;

import com.aprolab.sicad.utils.AppKeys;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import com.belogick.factory.util.support.ServiceException;

import dataware.service.SeguridadService;

@ManagedBean
@SessionScoped
public class InstitucionSolicitud extends GenericController   
{
	private Long institucion;
    private Long usuario;
    private UploadedFile docpdf;
    private StreamedContent file;
    private SeguridadService myService;
    private boolean existeDoc;
    List<Solicitud> listadoSolicitudes;
   
    @Override
    public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
	
		appName="Institucion";
		moduleName="Solicitud";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		usuario=usr.getId();
		defaultList();
		
		page_main="itc_sol_lst";
		page_new="itc_sol_new";
		page_update="itc_sol_update";
		forward(page_main);
	}		
        
	@Override
	public void defaultList() throws Exception
	{
		Solicitud solicitud = new Solicitud();
		solicitud.setInstitucion(institucion);
		solicitud.setRegistrante(usuario);
		//solicitud.setRegistrante(usuario);
		//solicitud.setNombreRegistrante(userName);
		solicitud.setEstado(1L);
		setBeanList(getService().listByObject(solicitud));		
		solicitud  = null;
	}
	
	public void initList() throws Exception
	{
		//Solicitud solicitud = new Solicitud();
		//solicitud.setInstitucion(institucion);
		//setBeanList(getService().listByObject(solicitud));		
		//solicitud  = null;
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		institucion=usr.getInstitucion();
		
		listadoSolicitudes=myService.listaSolicitudInstitucion(institucion);	
		setBeanList(myService.listaSolicitudInstitucion(institucion)); 
		forward("itc_sol_lst_query");
	}
	
	@Override
	public void afterNew() throws Exception 
	{
		Solicitud bean = (Solicitud)getBean();
		bean.setInstitucion(institucion);
		bean.setFecha(DateHelper.getDate());
		bean.setRegistrante(usuario);
		bean.setNombreRegistrante(userName);
		bean.setEstado(1L);
		setBean(bean);
		bean = null;
	}
	
	@Override
	public void afterSave() throws Exception
	{
		 Solicitud sol=(Solicitud)getBeanSave();
		 uploadDoc(sol.getId());
		 sol = null;
	}
	
	@Override
	public void afterUpdate() throws Exception {
		Solicitud bean=(Solicitud)getBean();		
		uploadDoc(bean.getId());
	}
	
	
	@Override
	public void afterLoad() throws Exception {
		Solicitud bean=(Solicitud)getBeanSelected();
    	setBean(bean);
		
		try {
			new FileInputStream(getServletContext().getRealPath("/recursos/documentos/solicitud/"+bean.getId()+".pdf"));
    		existeDoc = true;
    	} catch (FileNotFoundException e) {existeDoc = false;}
		
	}

	public void enviarSolicitud() throws Exception
	{
		status(getBeanSelected(), 2L);
		guardarSolicitudMovimiento(2L,((Solicitud)getBeanSelected()).getId());
		defaultList();
	}
	
	public void anularSolicitud() throws Exception
	{
		status(getBeanSelected(), 0L);
		guardarSolicitudMovimiento(0L,((Solicitud)getBeanSelected()).getId());		
		setMessageSuccess("La solicitud ha sido anulada");
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
	
	public void uploadDoc(Long name) throws IOException
	{
		if(docpdf != null) {
			File docFile = new File(getServletContext().getRealPath("/recursos/documentos/solicitud/"+name+".pdf"));
			OutputStream out = new FileOutputStream(docFile, true);
			out.write(docpdf.getContents());
			out.close();
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
			setMessageError("Debe ingresar la descripcion.");			
			success = false;
		}
		else if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe ingresar el tipo.");			
			success = false;
		}

		else if(!validateEmpty(object.getFecha_inicio()))
		{
			setMessageError("Debe ingresar la fecha de Inicio.");			
			success = false;
		}
		
		else if(!validateEmpty(object.getFecha_termino()))
		{
			setMessageError("Debe ingresar la fecha de Finalización.");			
			success = false;
		}
		else if(object.getFecha_termino().before(object.getFecha_inicio()))
		{
			setMessageError("La fecha final no puede ser menor a la fecha de inicio del feriado.");			
			success = false;
		}
		
		object=null;
		return success;
	}  
	
	public UploadedFile getDocpdf() 							{return docpdf;}
	public void setDocpdf(UploadedFile docpdf) 					{this.docpdf = docpdf;}
	
	public boolean isExisteDoc() 								{return existeDoc;} 
	
	public SeguridadService getMyService() 									{return myService;}
	public void setMyService(SeguridadService myService) 					{this.myService = myService;}
	
	public List<Solicitud> getListadoSolicitudes() 							{return listadoSolicitudes;}
	public void setListadoSolicitudes(List<Solicitud> listadoSolicitudes) 	{this.listadoSolicitudes = listadoSolicitudes;}

} 

