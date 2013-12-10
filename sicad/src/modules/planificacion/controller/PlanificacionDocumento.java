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
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.PlanificacionService;
import modules.planificacion.domain.Documento;
import modules.seguridad.domain.Usuario;

public class PlanificacionDocumento extends GenericController   
{	 
	private UploadedFile docpdf;
	private StreamedContent file;
	private PlanificacionService myService;
	private Long institucion;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Documento";
		moduleName="Planificacion";
		userName=usr.getUsuario();
		
		institucion = usr.getInstitucion();
		
		defaultList();
		page_new="planificacion_documento_new";
		page_main="planificacion_documento_list";
		page_update="planificacion_documento_update";
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Documento bean = new Documento();
		bean.setInstitucion(institucion);
		setBeanList(myService.listByObjectEnabledDisabled(bean));
		bean=null;
	}
	
	@Override
	public void afterNew() throws Exception 
	{
		setEnabled(new Documento());
		Documento bean = (Documento) getBean();
		bean.setInstitucion(institucion);
		bean.setFecha(DateHelper.getDate());
		setBean(bean);
	}
	
    @Override
	public void afterLoad() throws Exception 
	{
    	Documento bean=(Documento)getBeanSelected();
    	setBean(bean);
	}
	
	
	public StreamedContent getFile() 							
	{
		Documento object = (Documento)getBeanSelected();
		try 
		{
			System.out.println(getServletContext().getRealPath("/recursos/documentos/institucionales/"+object.getId()+".pdf"));
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/institucionales/"+object.getId()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public void doUpload(Long name) throws IOException
	{
		if(docpdf != null) 
		{
			
			File docFile = new File(getServletContext().getRealPath("/recursos/documentos/institucionales/"+name+".pdf"));
			OutputStream out = new FileOutputStream(docFile, true);
			out.write(docpdf.getContents());
			out.close();
        }
	}
		
    @Override
	public void afterSave() throws Exception
	{
    	 Documento res=(Documento)getBeanSave(); 
    	 doUpload(res.getId());
		 res = null;
		 
	}
    @Override
	public void beforeUpdate() throws Exception 
	{
		Documento res=(Documento)getBean();
		res.setFecha(DateHelper.getDate());
		setBean(res);
	}
    
	@Override
	public void afterUpdate() throws Exception 
	{
		Documento res=(Documento)getBean();
		doUpload(res.getId());
	}
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Documento object = (Documento)getBean();
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el Tipo de Documento.");			
			success = false;
		}
		else if(docpdf==null)
		{
			setMessageError("Debe adjuntar el Documento Digital en formato PDF.");			
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
