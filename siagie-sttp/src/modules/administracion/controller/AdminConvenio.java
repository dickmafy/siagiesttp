package modules.administracion.controller; 
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
import modules.administracion.domain.Convenio;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Solicitud;
import modules.mantenimiento.domain.Empresa;
import modules.seguridad.domain.Usuario;
import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.SeguridadService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.DefaultStreamedContent; 

@ManagedBean
@SessionScoped
public class AdminConvenio extends GenericController   
{	 
    private List<SelectItem>   	empresaList;
	private List<SelectItem> 	institucionList;
	private List<SelectItem>    solicitudList;
	
	private Long institucion;
	private String nombreInstitucion;
	private SeguridadService myService;
	private List<Institucion> instituciones=null;
	
	private UploadedFile docpdf;
	private StreamedContent file;
	    
    public void initBase(Long codigo, String nombre) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Administracion";
		moduleName="Convenios";
		userName=usr.getUsuario();
		institucion=codigo;
		nombreInstitucion=nombre;
		defaultList();
		
		empresaList = getListSelectItem(new Empresa(), "id", "razon_social",true);
		instituciones=myService.listByObjectEnabledDisabled(new Institucion());
		institucionList=getListSelectItem(instituciones, "id", "nombre", true);
		
		page_new="cnv_new";
		page_main="cnv_lst";
		page_update="cnv_upd";
		forward(page_main);
	}
    
    @Override
	public void init() throws Exception 
	{initBase(-1L,"");}
	
	public void init(Long codigo, String nombre) throws Exception
	{initBase(codigo,nombre);}
	
	public void setNombreInstitucion()
	{
		if(instituciones!=null)
		{
			for(int i=0; i<instituciones.size(); i++)
			{
				if(instituciones.get(i).getId().longValue()==institucion.longValue())
				{nombreInstitucion=instituciones.get(i).getNombreCompleto();}
			}
		}
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
    	setNombreInstitucion();
    }
    
	@Override 
	public void afterNew() throws Exception
	{
		Convenio bean=new Convenio();
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		bean.setInstitucion(institucion);
		setBean(bean);
		bean=null;
		
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(8L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(8L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
	}
	
	@Override
	public void afterSave() throws Exception
	{
		 Convenio conv=(Convenio)getBeanSave();
		 doUpload(conv.getId());
	}
	
	@Override
	public void afterUpdate() throws Exception 
	{
		Convenio res=(Convenio)getBean();
		doUpload(res.getId());
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
	
	public void doUpload(Long name) throws IOException
	{
		if(docpdf != null) 
		{
			
			File docFile = new File(getServletContext().getRealPath("/recursos/documentos/convenios/"+name+".pdf"));
			OutputStream out = new FileOutputStream(docFile, true);
			out.write(docpdf.getContents());
			out.close();
        }
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Convenio object = (Convenio)getBean();
		
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe asignar nombre al Convenio");			
			success = false;
		}
		else if(!validateSelect(object.getEmpresa()))
		{
			setMessageError("Debe seleccionar el Aliado Estratégico.");			
			success = false;
		}
		else if(!validateSelect(object.getModalidad()))
		{
			setMessageError("Debe seleccionar la Modalidad");			
			success = false;
		}
		else if(!validateSelect(object.getAlcance()))
		{
			setMessageError("Debe seleccionar el Alcance");			
			success = false;
		}
		else if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el Tipo");			
			success = false;
		}
		else if(!validateEmpty(object.getFecha_inicio()))
		{
			setMessageError("Debe seleccionar la fecha de inicio");			
			success = false;
		}
		else if(!validateEmpty(object.getFecha_fin()))
		{
			setMessageError("Debe seleccionar la fecha final");			
			success = false;
		}
		else if(!object.getFecha_inicio().before(object.getFecha_fin()))
		{
			setMessageError("La fecha final no puede ser menor a la fecha de inicio.");			
			success = false;
		}
		object=null;
		return success;
	}

	public List<SelectItem> getEmpresaList() 							{return empresaList;}
	public void setEmpresaList(List<SelectItem> empresaList)		 	{this.empresaList = empresaList;}

	public List<SelectItem> getInstitucionList() 						{return institucionList;	}
	public void setInstitucionList(List<SelectItem> institucionList) 	{this.institucionList = institucionList;}
	
	public List<SelectItem> getSolicitudList() 							{return solicitudList;}
	public void setSolicitudList(List<SelectItem> solicitudList)	 	{this.solicitudList = solicitudList;}

	public SeguridadService getMyService() 								{return myService;}	
	public void setMyService(SeguridadService myService)				{this.myService = myService;}
	
	public UploadedFile getDocpdf() 									{return docpdf;}
	public void setDocpdf(UploadedFile docpdf) 							{this.docpdf = docpdf;}
	
	public String getNombreInstitucion() 								{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion) 			{this.nombreInstitucion = nombreInstitucion;}

	public Long getInstitucion() 										{return institucion;}
	public void setInstitucion(Long institucion) 						{this.institucion = institucion;}	
	
} 