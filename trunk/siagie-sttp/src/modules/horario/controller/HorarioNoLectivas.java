package modules.horario.controller; 
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.SeguridadService;
import modules.administracion.domain.Ambiente;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Local;
import modules.administracion.domain.Solicitud;
import modules.mantenimiento.domain.Resolucion;
import modules.mantenimiento.domain.TipoAmbiente;
import modules.seguridad.domain.Usuario;

public class HorarioNoLectivas extends GenericController   
{
	private List<SelectItem> 	ambienteTipoList;
	private List<SelectItem>    localList;
	private List<SelectItem>    institucionList;
	private List<SelectItem>    resolucionList;
	private List<SelectItem>    solicitudList;
	
	
	private	String	nombreLocal;
	private String 	nombreInstitucion;
	private Long institucion;
	private Long local;
	
	private	List<Local> 		locales;
	private List<Institucion> instituciones=null;
	private SeguridadService	myService;
	
	public void initBase(Long codigo, String nombre) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Instituciones - Ambientes ";
		userName=usr.getUsuario();
		institucion=codigo;
		nombreInstitucion=nombre;
		local=-1L;
		defaultList();
		
		instituciones=myService.listByObjectEnabledDisabled(new Institucion());
		ambienteTipoList=getListSelectItem(new TipoAmbiente(), "id", "nombre",true);
		institucionList=getListSelectItem(instituciones, "id", "nombre", true);
		
		page_new="adm_amb_new";
		page_main="adm_amb_lst";
		page_update="adm_amb_upd";
		forward(page_main);
	}
	
	@Override
	public void init() throws Exception 
	{initBase(-1L,"");}
	
	public void init(Long codigo, String nombre) throws Exception
	{
		initBase(codigo,nombre);
		Local beanLocal=new Local();
		beanLocal.setInstitucion(institucion);
		locales=getService().listByObjectEnabled(beanLocal);
		localList=getListSelectItem(locales, "id", "nombre",true);
		beanLocal=null;
	}
	
	public void optionNew() throws Exception
	{
		if(local.longValue()>0L)	{forward(page_new);}
		else						{setMessageError("Debe seleccionar un Local para agregar ambientes.");}
	}
	
	public void selectInstitucion() throws Exception 
	{
		Local beanLocal=new Local();
		beanLocal.setInstitucion(institucion);
		locales=getService().listByObjectEnabled(beanLocal);
		localList=getListSelectItem(locales, "id", "nombre",true);
		setBeanList(null);
		beanLocal=null;
		setNombreInstitucion();
	}
	
	public void selectLocal() throws Exception
	{
		for(int i=0; i<locales.size(); i++)
		{
			if(locales.get(i).getId().longValue()==local.longValue())
			{nombreLocal=locales.get(i).getNombre();}
		}
		defaultList();
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Ambiente bean=new Ambiente();
		bean.setLocal(local);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		bean=null;
	}
	
	@Override
	public void afterNew() throws Exception
	{
		Ambiente bean=new Ambiente();
		bean.setLocal(local);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		bean=null;
		
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(9L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(9L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
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
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Ambiente object = (Ambiente)getBean();
		
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el tipo de ambiente.");			
			success = false;
		}
		else if(!validateEmpty(object.getCodigo()))
		{
			setMessageError("Debe ingresar el codigo del ambiente.");			
			success = false;
		}
		else if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del ambiente.");			
			success = false;
		}
		else if(object.getArea()<=0L)
		{
			setMessageError("El area del ambiente debe ser mayor a 0.");			
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

	public List<SelectItem> getLocalList() 									{return localList;}
	public void setLocalList(List<SelectItem> localList) 					{this.localList = localList;}

	public String getNombreLocal() 											{return nombreLocal;}
	public void setNombreLocal(String nombreLocal) 							{this.nombreLocal = nombreLocal;}
	
	public String getNombreInstitucion() 									{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion) 				{this.nombreInstitucion = nombreInstitucion;}
	
	public Long getInstitucion() 											{return institucion;}
	public void setInstitucion(Long institucion) 							{this.institucion = institucion;}
	
	public Long getLocal() 													{return local;}
	public void setLocal(Long local) 										{this.local = local;}

	public List<SelectItem> getInstitucionList() 							{return institucionList;}
	public void setInstitucionList(List<SelectItem> institucionList) 		{this.institucionList = institucionList;}
	
	public SeguridadService getMyService() 									{return myService;}	
	public void setMyService(SeguridadService myService)					{this.myService = myService;}

	public List<SelectItem> getAmbienteTipoList() 							{return ambienteTipoList;}
	public void setAmbienteTipoList(List<SelectItem> ambienteTipoList) 		{this.ambienteTipoList = ambienteTipoList;}
	
	public List<SelectItem> getResolucionList() 						{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 		{this.resolucionList = resolucionList;}

	public List<SelectItem> getSolicitudList() 							{return solicitudList;}
	public void setSolicitudList(List<SelectItem> solicitudList) 		{this.solicitudList = solicitudList;}
	
} 
