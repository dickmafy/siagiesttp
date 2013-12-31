package modules.administracion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.faces.model.SelectItem;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import dataware.service.SeguridadService;
import modules.administracion.domain.Cronograma;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Solicitud;
import modules.mantenimiento.domain.Resolucion;
import modules.seguridad.domain.Usuario;

public class AdminProceso extends GenericController   
{
	private Long institucion;
	String nombreInstitucion;
	private SeguridadService	myService;
	private List<SelectItem> 	resolucionList;
	private List<SelectItem> 	solicitudList;
	private List<SelectItem> 	institucionList;
	
	private StreamedContent file;
	private List<Institucion> instituciones=null;
	
	public void initBase(Long codigoInstitucion, String nombreInstituto) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Instituciones - Cronograma";
		userName=usr.getUsuario();
		
		institucion=codigoInstitucion;
		nombreInstitucion=nombreInstituto;
		
		defaultList();
		Institucion obj=new Institucion();
		obj.setFormacion(1L);
		instituciones=myService.listByObjectEnabledDisabled(obj);
		institucionList=getListSelectItem(instituciones, "id", "nombre", true);
		obj=null;
		
		page_new="adm_crn_new";
		page_main="adm_crn_lst";
		page_update="adm_crn_upd";
		forward(page_main);
	}
	
	@Override
	public void init() throws Exception 
	{initBase(-1L,"");}
	
	public void init(Long codigoInstitucion, String nombreInstitucion) throws Exception
	{initBase(codigoInstitucion,nombreInstitucion);}
	
	@Override
	public void afterNew() throws Exception
	{
		Cronograma bean=new Cronograma();
		bean.setInstitucion(institucion);
		setBean(bean);
		bean=null;
		
		Resolucion listResolucion=new Resolucion();
		resolucionList = getListSelectItem(listResolucion, "id", "nombre", true);
		listResolucion = null;
		
		Solicitud listSolicitud=new Solicitud();
		listSolicitud.setInstitucion(institucion);
		listSolicitud.setTipo(6L);
		solicitudList = getListSelectItem(listSolicitud, "id", "resolucion", true);
		listSolicitud=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		setBeanList(myService.listarPeriodo(institucion, DateHelper.getDate(),0L));
		setNombreInstitucion();
	}
	
	@Override
	public void beforeSave() throws Exception
	{
		Cronograma bean = (Cronograma) getBean();
		if(bean.getTipo().longValue()==1L)
		{bean.setEjecucion(bean.getPeriodo());}
		setBean(bean);
		bean=null;
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		Resolucion listResolucion=new Resolucion();
		resolucionList = getListSelectItem(listResolucion, "id", "nombre", true);
		listResolucion = null;
		
		Solicitud listSolicitud=new Solicitud();
		listSolicitud.setInstitucion(institucion);
		listSolicitud.setTipo(6L);
		solicitudList = getListSelectItem(listSolicitud, "id", "resolucion", true);
		listSolicitud=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Cronograma object = (Cronograma)getBean();
		
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el Tipo");			
			success = false;
		}
		else if(!validateSelect(object.getPeriodo()))
		{
			setMessageError("Debe seleccionar el Periodo");			
			success = false;
		}
		else if(object.getTipo().longValue()==2L && !validateSelect(object.getEjecucion()))
		{
			setMessageError("Debe seleccionar el Periodo de Ejecución");			
			success = false;
		}
		else if(object.getTipo().longValue()==2L && object.getPeriodo()>object.getEjecucion())
		{
			setMessageError("El Periodo de Ejecución debe ser mayor al Perido seleccionado");			
			success = false;
		}
		else if(!validateEmpty(object.getVigenciaInicio()))
		{
			setMessageError("Debe ingresar la Fecha de Inicio de la Vigencia del Periodo");			
			success = false;
		}
		else if(!validateEmpty(object.getVigenciaFin()))
		{
			setMessageError("Debe ingresar la Fecha de Fin de la Vigencia del Periodo");			
			success = false;
		}
		else if(object.getVigenciaFin().before(object.getVigenciaInicio()))
		{
			setMessageError("La fecha de vigencia final no puede ser menor a la fecha de inicio de vigencia.");			
			success = false;
		}
		else if(!validateSelect(object.getResolucion()))
		{
			setMessageError("Debe seleccionar la Resolución");			
			success = false;
		}
		object=null;
		return success;
	}
	
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
	
	public String getNombreInstitucion() 								{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion) 			{this.nombreInstitucion = nombreInstitucion;}

	public List<SelectItem> getInstitucionList() 						{return institucionList;	}
	public void setInstitucionList(List<SelectItem> institucionList) 	{this.institucionList = institucionList;}
	
	public List<SelectItem> getResolucionList() 						{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 		{this.resolucionList = resolucionList;}
	
	public List<SelectItem> getSolicitudList()	 						{return solicitudList;}
	public void setSolicitudList(List<SelectItem> solicitudList) 		{this.solicitudList = solicitudList;}
	
	public Long getInstitucion() 										{return institucion;}
	public void setInstitucion(Long institucion) 						{this.institucion = institucion;}	
} 
