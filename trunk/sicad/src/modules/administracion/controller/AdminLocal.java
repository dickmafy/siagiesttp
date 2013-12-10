package modules.administracion.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.SeguridadService;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Local;
import modules.administracion.domain.Personal;
import modules.administracion.domain.Solicitud;
import modules.mantenimiento.domain.Resolucion;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

public class AdminLocal extends GenericController   
{
	private Long institucion;
	private String nombreInstitucion;
	private List<SelectItem>    personalList;
	private List<SelectItem>    institucionList;
	private List<SelectItem>    resolucionList;
	private List<SelectItem>    solicitudList;
	
	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia;
	private SeguridadService	myService;
	private List<Institucion> instituciones=null;
	
	public void initBase(Long codigo, String nombre) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Instituciones - Local ";
		userName=usr.getUsuario();
		institucion=codigo;
		nombreInstitucion=nombre;
		defaultList();
		
		instituciones=myService.listByObjectEnabledDisabled(new Institucion());
		institucionList=getListSelectItem(instituciones, "id", "nombre", true);
		
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		personalList=getListSelectItem(obj, "id", "nombres,apepat,apemat"," ", true);
		obj=null;
		
		page_new="adm_loc_new";
		page_main="adm_loc_lst";
		page_update="adm_loc_upd";
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
	public void afterNew() throws Exception
	{
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		personalList=getListSelectItem(obj, "id", "nombres,apepat,apemat"," ", true);
		obj=null;
		
		Local bean=new Local();
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		bean.setInstitucion(institucion);
		bean.setPrincipal(false);
		setBean(bean);
		bean=null;
		
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(9L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
		
		fillDep();
	}
	
	@Override
	public void afterLoad() throws Exception 
	{
		Local bean=(Local)getBeanSelected();
		depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
		if(bean.getUbigeo()!=null)
		{
			Ubigeo ubi=(Ubigeo) myService.findById(Ubigeo.class, bean.getUbigeo());
			departamento = ubi.getDepartamento();
			provincia = ubi.getProvincia();
			depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
			proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
			disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		}
		else
		{
			departamento=-1L;
			provincia=-1L;
		}
		
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		Solicitud sol=new Solicitud();
		sol.setInstitucion(institucion);
		sol.setTipo(9L);
		sol.setEstado(3L);
		solicitudList=getListSelectItem(myService.listByObject(sol),"id","id,fecha,nombreTipo"," ",true);
		sol=null;
		bean=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Local bean=new Local();
		bean.setInstitucion(institucion);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		bean=null;
		setNombreInstitucion();
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Local object = (Local)getBean();
		
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del Local.");			
			success = false;
		}
		else if(!validateSelect(provincia))
		{
			setMessageError("Debe seleccionar la Provincia del Local.");			
			success = false;
		}
		else if(!validateSelect(departamento))
		{
			setMessageError("Debe seleccionar el Departamento del Local.");			
			success = false;
		}
		else if(!validateSelect(object.getUbigeo()))
		{
			setMessageError("Debe seleccionar el Distrito del Local.");			
			success = false;
		}
		else if(!validateEmpty(object.getDireccion()))
		{
			setMessageError("Debe ingresar la dirección del Local.");			
			success = false;
		}		
		else if(!validateEmpty(object.getTelefonos()))
		{
			setMessageError("Debe ingresar los números telefonicos asociados al Local.");			
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
	
	
	public void fillDep() throws Exception
	{
		depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
		proList=new ArrayList<SelectItem>();
		disList=new ArrayList<SelectItem>();
		Local bean=(Local)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		departamento=-1L;
		provincia=-1L;
	}
	
	public void fillPro() throws Exception
	{
		proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
		disList=new ArrayList<SelectItem>();
		
		Local bean=(Local)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		provincia=-1L;
	}
	
	public void fillDis() throws Exception
	{
		disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		Local bean=(Local)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
	}
	
	public List<SelectItem> getDepList() 									{return depList;}
	public void setDepList(List<SelectItem> depList) 						{this.depList = depList;}
	public List<SelectItem> getProList() 									{return proList;}
	public void setProList(List<SelectItem> proList) 						{this.proList = proList;}
	public List<SelectItem> getDisList() 									{return disList;}
	public void setDisList(List<SelectItem> disList) 						{this.disList = disList;}
	public Long getDepartamento() 											{return departamento;}
	public void setDepartamento(Long departamento) 							{this.departamento = departamento;}
	public Long getProvincia() 												{return provincia;}
	public void setProvincia(Long provincia) 								{this.provincia = provincia;}
	
	public SeguridadService getMyService() 									{return myService;}	
	public void setMyService(SeguridadService myService)					{this.myService = myService;}
	
	public String getNombreInstitucion() 									{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion)		 		{this.nombreInstitucion = nombreInstitucion;}	
	
	public List<SelectItem> getPersonalList()                  			    {return personalList;}
	public void setPersonalList(List<SelectItem> personalList)  		    {this.personalList = personalList;}
	
	public List<SelectItem> getInstitucionList() 							{return institucionList;}
	public void setInstitucionList(List<SelectItem> institucionList) 		{this.institucionList = institucionList;}

	public Long getInstitucion() 											{return institucion;}
	public void setInstitucion(Long institucion) 							{this.institucion = institucion;}
	
	public List<SelectItem> getResolucionList() 						{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 		{this.resolucionList = resolucionList;}

	public List<SelectItem> getSolicitudList() 							{return solicitudList;}
	public void setSolicitudList(List<SelectItem> solicitudList) 		{this.solicitudList = solicitudList;}
} 
