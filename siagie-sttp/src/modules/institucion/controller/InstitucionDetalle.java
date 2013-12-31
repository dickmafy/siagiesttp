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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import com.belogick.factory.util.controller.GenericController;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Local;
import modules.seguridad.domain.Usuario; 

public class InstitucionDetalle extends GenericController   
{
	private List<Local> locales;
	private UploadedFile logo;
	private Long institucion;
	
	private StreamedContent fileCre,fileRen,fileRev;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucional";
		moduleName="General ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		page_new="itc_detalle";
		page_main="itc_detalle";
		page_update="itc_detalle";
		setBean(getService().findById(Institucion.class, usr.getInstitucion()));
		
		Local bean=new Local();
		bean.setInstitucion(usr.getInstitucion());
		locales=getService().listByObjectEnabled(bean);
		bean=null;
		
		forward(page_main);	
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
} 
