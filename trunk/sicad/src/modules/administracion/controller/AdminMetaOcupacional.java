package modules.administracion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import modules.administracion.domain.Ambiente;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.MetaOcupacional;
import modules.administracion.domain.Solicitud;
import modules.horario.domain.Seccion;
import modules.mantenimiento.domain.Resolucion;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.SeguridadService;

public class AdminMetaOcupacional extends GenericController   
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
	
	
	public void initBase(Long codigo, String nombre, Long forma) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Instituciones - Meta Ocupacional ";
		userName=usr.getUsuario();
		institucion=codigo;
		nombreInstitucion=nombre;
		formacion=forma;
		defaultList();
		
		instituciones=myService.listByObjectEnabledDisabled(new Institucion());
		filtrarInstitutos(instituciones);
		
		
		page_new="metaocup_new";
		page_main="metaocup_lst";
		page_update="metaocup_upd";
		forward(page_main);
	}
	
	@Override
	public void init() throws Exception 
	{initBase(-1L,"", -1L);}
	
	public void init(Long codigo, String nombre, Long forma) throws Exception
	{initBase(codigo,nombre,forma);}
	
	public void filtrarInstitutos(List<Institucion> instituciones) throws Exception
	{
		List<Institucion> nueva=new ArrayList<Institucion>();	
		for (Institucion item : instituciones) 
		{
			if(item.getFormacion() != 1L)
			{nueva.add(item);}						
		}
		institucionList=getListSelectItem(nueva, "id", "nombre", true);
		nueva=null;
	}
	
	
	@Override
	public void afterNew() throws Exception
	{
		MetaOcupacional bean=new MetaOcupacional();
		bean.setInstitucion(institucion);
		setBean(bean);
		bean=null;
		
		profesionList=getListSelectItem(myService.listarOferta(institucion, DateHelper.getDate(),1L),"profesion","nombreProfesion",false);
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
	
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(4L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		profesionList=getListSelectItem(myService.listarOferta(institucion, DateHelper.getDate(),1L),"profesion","nombreProfesion",false);
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
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
			setBeanList(myService.listarMetaOcupacional(institucion));
			setNombreInstitucion();
		}
	}

	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		MetaOcupacional object = (MetaOcupacional)getBean();
		if(!validateSelect(object.getProfesion()))
		{
			setMessageError("Debe seleccionar una Profesión");			
			success = false;
		}
		else if(!validateSelect(object.getResolucion()))
		{
			setMessageError("Debe seleccionar la Resolución de ejecución");			
			success = false;
		}
		else if(!validateEmpty(object.getMinimo().toString()))
		{
			setMessageError("Debe ingresar la meta mínima");			
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
} 
