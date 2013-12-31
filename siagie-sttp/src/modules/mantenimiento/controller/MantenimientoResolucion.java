package modules.mantenimiento.controller; 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modules.mantenimiento.domain.Resolucion;
import modules.seguridad.domain.Usuario;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.belogick.factory.util.controller.GenericController;

@ManagedBean
@SessionScoped
public class MantenimientoResolucion extends GenericController   
{
    private UploadedFile docpdf;
    private StreamedContent file;
   
    @Override
    public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
	
		appName="Administración";
		moduleName="Resolución";
		userName=usr.getUsuario();
		defaultList();
		
		page_main="res_lst";
		page_new="res_new";
		page_update="res_upd";
		forward(page_main);
	}		
        	
    @Override
    public void defaultList() throws Exception
    {
    	List<Resolucion> resol=getService().listByObjectEnabledDisabled(new Resolucion());
    	List<Resolucion> nueva=new ArrayList<Resolucion>();
    	for(int i=0; i<resol.size(); i++)
    	{
    		Resolucion aux=resol.get(i);
    		File docFile = new File(getServletContext().getRealPath("/recursos/documentos/resolucion/"+aux.getId()+".pdf"));
    		if(docFile.exists())	{aux.setExistFile(true);}
    		else					{aux.setExistFile(false);}
    		nueva.add(aux);
    		aux=null;
    		docFile=null;
    	}
    	setBeanList(nueva);
    	resol=null;
    	nueva=null;
    }
    	
    @Override
	public void afterNew() throws Exception 
	{setEnabled(new Resolucion());}
    
    @Override
	public void afterLoad() throws Exception 
	{
    	Resolucion bean=(Resolucion)getBeanSelected();
    	setBean(bean);
		try {new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+bean.getId()+".pdf"));} 
		catch (FileNotFoundException e) {System.out.println(e);}
	}
    
    @Override
	public void afterSave() throws Exception
	{
    	 Resolucion res=(Resolucion)getBeanSave(); 
    	 doUpload(res.getId());
		 res = null;
		 
	}
	
	@Override
	public void afterUpdate() throws Exception 
	{
		Resolucion res=(Resolucion)getBean();
		doUpload(res.getId());
	}
    
	public StreamedContent getFile() 							
	{
		Resolucion object = (Resolucion)getBeanSelected();
		try 
		{
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getId()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public void doUpload(Long name) throws IOException
	{
		if(docpdf != null) 
		{
			File docFile = new File(getServletContext().getRealPath("/recursos/documentos/resolucion/"+name+".pdf"));
			OutputStream out = new FileOutputStream(docFile, true);
			out.write(docpdf.getContents());
			out.close();
        }
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Resolucion object = (Resolucion)getBean();
		
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el Tipo de Resolución.");			
			success = false;
		}
		else if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el Número de Resolución.");			
			success = false;
		}
		else if(!validateEmpty(object.getFecha()))
		{
			setMessageError("Debe ingresar la Fecha de Emisión.");			
			success = false;
		}
		
		object=null;
		return success;
	}  
	
	public UploadedFile getDocpdf() 							{return docpdf;}
	public void setDocpdf(UploadedFile docpdf) 					{this.docpdf = docpdf;}
} 

