package modules.administracion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.faces.model.SelectItem;

import modules.administracion.domain.Cronograma;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Solicitud;
import modules.horario.controller.HorarioMetaDetalle;
import modules.mantenimiento.domain.Resolucion;
import modules.seguridad.domain.Usuario;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.SeguridadService;

public class AdminMetaInstitucional extends GenericController   
{
	private StreamedContent file;
	private Long institucion, formacion;
	private String nombreInstitucion;
	
	private List<SelectItem>    profesionList;
	private List<SelectItem>    institucionList;
	private List<SelectItem>    resolucionList;
	private List<SelectItem>    solicitudList;
	
	private SeguridadService	myService;	
	private List<Institucion> instituciones=null;
	private List<SelectItem> procesoList;
	private List<Cronograma> listProceso;
	
	
	@SuppressWarnings("unchecked")
	public void initBase(Long codigo, String nombre, Long forma) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Instituciones - Meta Institucional ";
		userName=usr.getUsuario();
		institucion=codigo;
		nombreInstitucion=nombre;
		formacion=forma;
		defaultList();
		
		Institucion obj=new Institucion();
		obj.setFormacion(1L);
		instituciones=myService.listByObjectEnabledDisabled(obj);
		institucionList=getListSelectItem(instituciones, "id", "nombre", true);
		obj=null;
		
		page_new="meta_institucional_new";
		page_main="meta_institucional_list";
		page_update="meta_institucional_update";
		forward(page_main);
	}
	
	@Override
	public void init() throws Exception 
	{initBase(-1L,"", -1L);}
	
	public void init(Long codigo, String nombre, Long forma) throws Exception
	{initBase(codigo,nombre,forma);}
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterNew() throws Exception
	{
		MetaInstitucional bean=new MetaInstitucional();
		bean.setInstitucion(institucion);
		bean.setEstado(1L);
		setBean(bean);
		bean=null;
		profesionList=getListSelectItem(myService.listarOferta(institucion, DateHelper.getDate(),2L),"profesion","nombreProfesion",false);
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		Cronograma cronograma = new Cronograma();
		cronograma.setInstitucion(institucion);
		cronograma.setTipo(1L);
		listProceso = myService.listByObject(cronograma);
		
	
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(4L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterLoad() throws Exception
	{
		profesionList=getListSelectItem(myService.listarOferta(institucion, DateHelper.getDate(),1L),"profesion","nombreProfesion",false);
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		Cronograma cronograma = new Cronograma();
		cronograma.setInstitucion(institucion);
		cronograma.setTipo(1L);
		listProceso = myService.listByObject(cronograma);
		
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(4L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
	}
	
	
	@Override
	public void defaultList() throws Exception
	{
		setBeanList(null);
		if(institucion.longValue()>0L)
		{
			setBeanList(myService.listarMetaInstitucional(institucion,false));
			setNombreInstitucion();
		}
	}

	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		MetaInstitucional object = (MetaInstitucional)getBean();
		if(!validateSelect(object.getProfesion()))
		{
			setMessageError("Debe seleccionar una Profesión");			
			success = false;
		}
		else if(!validateSelect(object.getAnnio()))
		{
			setMessageError("Debe seleccionar el Año");			
			success = false;
		}
		else if(!validateSelect(object.getProceso()))
		{
			setMessageError("Debe seleccionar el Proceso");			
			success = false;
		}
		else if(!validateSelect(object.getTurno()))
		{
			setMessageError("Debe seleccionar el Turno");			
			success = false;
		}
		else if(object.getMeta_matricula()<=0)
		{
			setMessageError("La Meta de Matricula no puede ser menor a 1 ");			
			success = false;
		}
		else if(!validateSelect(object.getResolucion()))
		{
			setMessageError("Debe seleccionar la Resolución de ejecución");			
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
				{
					nombreInstitucion=instituciones.get(i).getNombreCompleto();
					formacion=instituciones.get(i).getFormacion();
				}
			}
		}
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
	
	
	public void optionGoDetalleMetaInstitucionDeAdministrador() throws Exception
	{
		AdminMetaInstitucionalDetalle go = (AdminMetaInstitucionalDetalle)getSpringBean("adminMetaInstitucionalDetalle");
		Long proceso = ((MetaInstitucional) getBeanSelected()).getProceso();
		Long annio = ((MetaInstitucional) getBeanSelected()).getAnnio();
		go.init(institucion,annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
	}
	
	public SeguridadService getMyService() 								{return myService;}	
	public void setMyService(SeguridadService myService)				{this.myService = myService;}
	
	public String getNombreInstitucion() 								{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion) 			{this.nombreInstitucion = nombreInstitucion;}

	public List<SelectItem> getProfesionList() 							{return profesionList;}
	public void setProfesionList(List<SelectItem> profesionList) 		{this.profesionList = profesionList;}

	public List<SelectItem> getInstitucionList() 						{return institucionList;	}
	public void setInstitucionList(List<SelectItem> institucionList)	{this.institucionList = institucionList;	}
	
	public Long getInstitucion() 										{return institucion;}
	public void setInstitucion(Long institucion) 						{this.institucion = institucion;}

	public List<SelectItem> getResolucionList() 						{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 		{this.resolucionList = resolucionList;}

	public List<SelectItem> getSolicitudList() 							{return solicitudList;}
	public void setSolicitudList(List<SelectItem> solicitudList) 		{this.solicitudList = solicitudList;}

	public List<SelectItem> getProcesoList() 							{return procesoList;	}
	public void setProcesoList(List<SelectItem> procesoList) 			{this.procesoList = procesoList;	}

	public List<Cronograma> getListProceso() 							{return listProceso;	}
	public void setListProceso(List<Cronograma> listProceso) 			{this.listProceso = listProceso;	}

	
	
} 
