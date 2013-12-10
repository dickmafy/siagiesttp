package modules.planificacion.controller; 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.PlanificacionService;
import modules.planificacion.domain.Documento;
import modules.planificacion.domain.RecursoPropio;
import modules.planificacion.domain.RecursoPropio;
import modules.seguridad.domain.Usuario;

public class PlanificacionRecurso extends GenericController   
{	 
	private UploadedFile docpdf;
	private StreamedContent file;
	private PlanificacionService myService;
	private Long objetivo,institucion;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Recurso";
		moduleName="Planificacion";
		userName=usr.getUsuario();
		institucion = usr.getInstitucion();
		
		defaultList();
		page_new="planificacion_recurso_new";
		page_main="planificacion_recurso_list";
		page_update="planificacion_recurso_update";
		forward(page_main);
	}
	
	public void init(Long pcodigo_Recurso) throws Exception 
	{
		init();
		objetivo = pcodigo_Recurso;
		
		
	}
	
	@Override
	public void defaultList() throws Exception
	{
		RecursoPropio bean = new RecursoPropio();
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		getBeanList();
		bean=null;
	}
	
	@Override
	public void afterNew() throws Exception 
	{
		setEnabled(new RecursoPropio());
		RecursoPropio bean = (RecursoPropio) getBean();
		bean.setPk_institucion(institucion);
		
	}
	
	//Inicio - manejo PDF
	@Override
	public void afterLoad() throws Exception 
		{
	    	RecursoPropio bean=(RecursoPropio)getBeanSelected();
	    	setBean(bean);
			try {new FileInputStream(getServletContext().getRealPath("/recursos/documentos/recursoPropios/"+bean.getId()+".pdf"));} 
			catch (FileNotFoundException e) {System.out.println(e);}
		}
	 
	public StreamedContent getFile() 							
	{
		RecursoPropio object = (RecursoPropio)getBeanSelected();
		try 
		{
			System.out.println(getServletContext().getRealPath("/recursos/documentos/recursoPropios/"+object.getId()+".pdf"));
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/recursoPropios/"+object.getId()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public void doUpload(Long name) throws IOException
	{
		if(docpdf != null) 
		{
			
			File docFile = new File(getServletContext().getRealPath("/recursos/documentos/recursoPropios/"+name+".pdf"));
			OutputStream out = new FileOutputStream(docFile, true);
			out.write(docpdf.getContents());
			out.close();
        }
	}

	@Override
	public void afterSave() throws Exception 
	{
		RecursoPropio res = (RecursoPropio) getBeanSave();
		doUpload(res.getId());
		res = null;

	}

	@Override
	public void afterUpdate() throws Exception 
	{
		RecursoPropio res = (RecursoPropio) getBean();
		doUpload(res.getId());
	}

	//Fin - manejo PDF
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		RecursoPropio object = (RecursoPropio)getBean();
		if(!validateSelect(object.getPeriodicidad()))
		{
			setMessageError("Debe ingresar el tipo de Recurso.");			
			success = false;
		}
		object=null;
		return success;
	} 
	
	public UploadedFile getDocpdf() 									{return docpdf;}
	public void setDocpdf(UploadedFile docpdf) 							{this.docpdf = docpdf;}

	public PlanificacionService getMyService() {		return myService;	}
	public void setMyService(PlanificacionService myService) {		this.myService = myService;	}
	
} 
