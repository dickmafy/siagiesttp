package modules.institucion.controller; 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import modules.administracion.domain.Convenio;
import modules.mantenimiento.domain.Empresa;
import modules.seguridad.domain.Usuario;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.SeguridadService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.DefaultStreamedContent; 

@ManagedBean
@SessionScoped
public class InstitucionConvenio extends GenericController   
{	 
	private SeguridadService myService;
	private Long institucion;
	private StreamedContent file;
	private String nomEmpresa;
	private String correoEmpresa;
	
    @Override
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucional";
		moduleName="Convenios";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		defaultList();
		page_main="itc_cnv_lst";
		forward(page_main);
	}
    
    @Override
    public void defaultList() throws Exception
    {
    	List<Convenio> antig=myService.listarConvenios(institucion);
    	List<Convenio> nueva=new ArrayList<Convenio>();
    	for(int i=0; i<antig.size(); i++)
    	{
    		Convenio aux=antig.get(i);
    		File docFile = new File(getServletContext().getRealPath("/recursos/documentos/convenios/"+aux.getId()+".pdf"));
    		if(docFile.exists())	{aux.setExistFile(true);}
    		else					{aux.setExistFile(false);}
    		nueva.add(aux);
    		aux=null;
    		docFile=null;
    	}
    	setBeanList(nueva);
    	antig=null;
    	nueva=null;
    }
    
    public void optionDetail() throws Exception
	{
    	Convenio bean=(Convenio)getBeanSelected();
    	setBean(bean);
    	
    	Empresa emp = (Empresa) getService().findById(Empresa.class, bean.getEmpresa());    	
    	nomEmpresa = emp.getRazon_social();
    	correoEmpresa = emp.getCorreo();
    	emp=null;
    	
    	forward("itc_cnv_det");
	}
	
    public StreamedContent getFile() 							
	{
		Convenio object = (Convenio)getBeanSelected();
		try 
		{
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/convenios/"+object.getId()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
    
    public SeguridadService getMyService() 						{return myService;}	
	public void setMyService(SeguridadService myService)		{this.myService = myService;} 

	public String getNomEmpresa() 								{return nomEmpresa;}	
	public String getCorreoEmpresa() 							{return correoEmpresa;}
} 