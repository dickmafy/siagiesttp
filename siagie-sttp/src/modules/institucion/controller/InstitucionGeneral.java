package modules.institucion.controller; 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.belogick.factory.util.controller.GenericController;

import modules.administracion.domain.Institucion;
import modules.administracion.domain.Local;
import modules.mantenimiento.domain.Resolucion;
import modules.seguridad.domain.Usuario; 

public class InstitucionGeneral extends GenericController   
{
	private List<Local> locales;
	private UploadedFile logo;
	private Long institucion;
	private String nomResCre;
	private String nomResRen;
	private String nomResRev;
	private Resolucion beanRes;
	
	private StreamedContent fileCre,fileRen,fileRev;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucional";
		moduleName="General ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		page_new="itc_general";
		page_main="itc_general";
		page_update="itc_general";
		setBean(getService().findById(Institucion.class, usr.getInstitucion()));
		
		Local bean=new Local();
		bean.setInstitucion(usr.getInstitucion());
		locales=getService().listByObjectEnabled(bean);
		bean=null;
		
		obtenerResoluciones();
				
		forward(page_main);	
	}
	
	public void obtenerResoluciones() throws Exception
	{
		Institucion object = (Institucion)getBean();		
		Resolucion beanRes = new Resolucion();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
		
		if(object.getResolcre()!=null){
			beanRes = (Resolucion) getService().findById(Resolucion.class, object.getResolcre());
			nomResCre = beanRes.getNombre() + " (" + sd.format(beanRes.getFecha()) +")";
		}
		if(object.getResolren()!=null){
			beanRes = (Resolucion) getService().findById(Resolucion.class, object.getResolren());
			nomResRen = beanRes.getNombre() + " (" + sd.format(beanRes.getFecha()) +")";
		}
		if(object.getResolrev()!=null){
			beanRes = (Resolucion) getService().findById(Resolucion.class, object.getResolrev());
			nomResRev = beanRes.getNombre() + " (" + sd.format(beanRes.getFecha()) +")";
		}
		
		beanRes = null;
	}
	
	public void optionUpdate() throws Exception 
	{
		getService().save(getBean());
		setMessageSuccess("Los datos fueron actualizados exitosamente.");
		uploadPic();
	}
	
	public String getPic()
	{
		try
		{			
			File fotoFile = new File(getServletContext().getRealPath("/recursos/logos/"+institucion+".png"));
			if(fotoFile.isFile()) return "/recursos/logos/"+institucion+".png";
			else return "/recursos/logos/default.png";
		}
		catch(Exception e)
		{return "/recursos/logos/default.png";}
		
	}
	
	public void uploadPic() throws IOException
	{
		if(logo != null) 
		{
			File docFile = new File(getServletContext().getRealPath("/recursos/logos/"+institucion+".png"));
			OutputStream out = new FileOutputStream(docFile, true);
			out.write(logo.getContents());
			out.close();
        }
	}
	
	public StreamedContent getFileCre() 							
	{
		Institucion object = (Institucion)getBean();
		try 
		{
			if(object.getResolcre()!=null)
			{
				InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getResolcre()+".pdf")));
				return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
			}
			return null;
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public StreamedContent getFileRen() 							
	{
		Institucion object = (Institucion)getBean();
		try 
		{
			if(object.getResolren()!=null)
			{
				InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getResolren()+".pdf")));
				return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
			}
			return null;
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public StreamedContent getFileRev() 							
	{
		Institucion object = (Institucion)getBean();
		try 
		{
			if(object.getResolrev()!=null)
			{
				InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getResolrev()+".pdf")));
				return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
			}
			return null;
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	
	public List<Local> getLocales() 				{return locales;}
	public void setLocales(List<Local> locales) 	{this.locales = locales;}
	public UploadedFile getLogo() 					{return logo;}	
	public void setLogo(UploadedFile logo)			{this.logo = logo;}

	public String getNomResCre() {
		return nomResCre;
	}

	public void setNomResCre(String nomResCre) {
		this.nomResCre = nomResCre;
	}

	public String getNomResRen() {
		return nomResRen;
	}

	public void setNomResRen(String nomResRen) {
		this.nomResRen = nomResRen;
	}

	public String getNomResRev() {
		return nomResRev;
	}

	public void setNomResRev(String nomResRev) {
		this.nomResRev = nomResRev;
	}
	
	
} 
