package modules.institucion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import modules.administracion.domain.MetaOcupacional;
import modules.seguridad.domain.Usuario;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.SeguridadService;

public class InstitucionMetaCetpro extends GenericController   
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
		page_new="itc_meta_cetpro";
		page_main="itc_meta_cetpro";
		page_update="itc_meta_cetpro";
		setBeanList(myService.listarMetaOcupacional(usr.getInstitucion()));
		forward(page_main);
	}
	
	public StreamedContent getFile() 							
	{
		MetaOcupacional object = (MetaOcupacional)getBeanSelected();
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
