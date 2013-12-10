package modules.institucion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import modules.administracion.domain.MetaInstitucional;
import modules.seguridad.domain.Usuario;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.SeguridadService;

public class InstitucionMetaIest extends GenericController   
{
	private StreamedContent file;
	private SeguridadService	myService;	
	
	@Override
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucion";
		moduleName="Meta Institucional ";
		userName=usr.getUsuario();
		page_new="itc_meta_iest";
		page_main="itc_meta_iest";
		page_update="itc_meta_iest";
		setBeanList(myService.listarMetaInstitucional(usr.getInstitucion(),true));
		forward(page_main);
	}
	
	public StreamedContent getFile() 							
	{
		MetaInstitucional object = (MetaInstitucional)getBeanSelected();
		try 
		{
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getResolucion()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public SeguridadService getMyService() 								{return myService;}	
	public void setMyService(SeguridadService myService)				{this.myService = myService;}
} 
