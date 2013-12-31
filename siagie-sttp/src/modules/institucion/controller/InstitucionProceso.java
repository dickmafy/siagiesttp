package modules.institucion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import dataware.service.SeguridadService;
import modules.administracion.domain.Cronograma;
import modules.seguridad.domain.Usuario;

public class InstitucionProceso extends GenericController   
{
	private SeguridadService	myService;
	private StreamedContent file;
	
	@Override
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucion";
		moduleName="Procesos de Admisión ";
		userName=usr.getUsuario();
		page_new="itc_cronograma";
		page_main="itc_cronograma";
		page_update="itc_cronograma";
		setBeanList(myService.listarPeriodo(usr.getInstitucion(), DateHelper.getDate(),1L));
		forward(page_main);
	}
	
	public StreamedContent getFile() 							
	{
		Cronograma object = (Cronograma)getBeanSelected();
		try 
		{
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getResolucion()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public SeguridadService getMyService() 								{return myService;}	
	public void setMyService(SeguridadService myService	)				{this.myService = myService;}
} 
