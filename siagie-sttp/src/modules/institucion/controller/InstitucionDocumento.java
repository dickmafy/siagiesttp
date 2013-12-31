package modules.institucion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.PlanificacionService;
import modules.planificacion.domain.Documento;
import modules.seguridad.domain.Usuario;

public class InstitucionDocumento extends GenericController   
{	 
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
		page_new="itc_documento";
		page_main="itc_documento";
		page_update="itc_documento";
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
	

	public PlanificacionService getMyService() 					{return myService;}
	public void setMyService(PlanificacionService myService) 	{this.myService = myService;}
} 
