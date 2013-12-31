package modules.administracion.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.SeguridadService;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Personal;
import modules.mantenimiento.domain.Puesto;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

public class AdminPersonal extends GenericController   
{
	private List<SelectItem> puestoList;
	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia,distrito;
	private SeguridadService	myService;
	private Long institucion;
	private String nombreInstitucion;

	private List<SelectItem> institucionList;
	private List<Institucion> instituciones=null;
	
	public void initBase(Long codigo, String nombre) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Instituciones - Personal ";
		userName=usr.getUsuario();
		institucion=codigo;
		nombreInstitucion=nombre;
		defaultList();
		
		instituciones=myService.listByObjectEnabledDisabled(new Institucion());
		puestoList=getListSelectItem(new Puesto(), "id", "nombre",true);
		institucionList=getListSelectItem(instituciones, "id", "nombre", true);
		
		page_new="adm_prs_new";
		page_main="adm_prs_lst";
		page_update="adm_prs_upd";
		forward(page_main);
	}
	
	@Override
	public void init() throws Exception 
	{initBase(-1L,"");}
	
	public void init(Long codigo, String nombre) throws Exception
	{initBase(codigo,nombre);}
	
	@Override
	public void defaultList() throws Exception
	{
		Personal bean=new Personal();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		bean=null;
		setNombreInstitucion();
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
	public void afterNew() throws Exception
	{
		Personal bean=new Personal();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		bean=null;
		fillDep();
	}
	
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Personal object = (Personal)getBean();
		if(!validateEmpty(object.getCodigo()))
		{
			setMessageError("Debe ingresar el Código Modular.");			
			success = false;
		}
		else if(!validateEmpty(object.getDni()))
		{
			setMessageError("Debe ingresar el número del DNI.");			
			success = false;
		}
		else if(!validateSelect(object.getPuesto()))
		{
			setMessageError("Debe seleccionar el Puesto Laboral.");			
			success = false;
		}
		else if(!validateEmpty(object.getNombres()))
		{
			setMessageError("Debe ingresar sus Nombres.");			
			success = false;
		}
		else if(!validateEmpty(object.getApepat()))
		{
			setMessageError("Debe ingresar su Apellido Paterno.");			
			success = false;
		}
		else if(!validateEmpty(object.getApemat()))
		{
			setMessageError("Debe ingresar su Apellido Materno.");			
			success = false;
		}
		else if(object.getSexo()==null)
		{
			setMessageError("Debe ingresar su Sexo.");			
			success = false;
		}		
		else if(!validateEmail(object.getCorreo().toLowerCase()))
		{
			setMessageError("Debe ingresar su correo electrónico correctamente.");			
			success = false;
		}
		else if(!validateSelect(provincia))
		{
			setMessageError("Debe seleccionar la provincia.");			
			success = false;
		}
		else if(!validateSelect(departamento))
		{
			setMessageError("Debe seleccionar el departamento.");			
			success = false;
		}
		else if(!validateSelect(object.getUbigeo()))
		{
			setMessageError("Debe seleccionar el distrito.");			
			success = false;
		}
		else if(!validateEmpty(object.getDireccion()))
		{
			setMessageError("Debe ingresar la dirección.");			
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
		Personal bean=(Personal)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		departamento=-1L;
		provincia=-1L;
	}
	
	public void fillPro() throws Exception
	{
		proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
		disList=new ArrayList<SelectItem>();
		
		Personal bean=(Personal)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		provincia=-1L;
	}
	
	public void fillDis() throws Exception
	{
		disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		Personal bean=(Personal)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
	}
		
	
	@Override
	public void afterLoad() throws Exception 
	{
		Personal bean=(Personal)getBeanSelected();
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
		bean=null;
	}
	
	public void optionGoAcademico() throws Exception
	{
		AdminHistorialAcademico go = (AdminHistorialAcademico)getSpringBean("adminHistorialAcademico");
		go.init(((Personal)getBeanSelected()).getId().longValue(), ((Personal)getBeanSelected()).getNombreCompleto());
	}
	
	public void optionGoLaboral() throws Exception
	{
		AdminHistorialLaboral go = (AdminHistorialLaboral)getSpringBean("adminHistorialLaboral");
		go.init(((Personal)getBeanSelected()).getId().longValue(), ((Personal)getBeanSelected()).getNombreCompleto());
	}
	
	public List<SelectItem> getDepList() 								{return depList;}
	public void setDepList(List<SelectItem> depList) 					{this.depList = depList;}
	public List<SelectItem> getProList() 								{return proList;}
	public void setProList(List<SelectItem> proList) 					{this.proList = proList;}
	public List<SelectItem> getDisList() 								{return disList;}
	public void setDisList(List<SelectItem> disList) 					{this.disList = disList;}
	public Long getDepartamento() 										{return departamento;}
	public void setDepartamento(Long departamento) 						{this.departamento = departamento;}
	public Long getProvincia() 											{return provincia;}
	public void setProvincia(Long provincia) 							{this.provincia = provincia;}
	public Long getDistrito() 											{return distrito;	}
	public void setDistrito(Long distrito) 								{this.distrito = distrito;	}
	
	public SeguridadService getMyService() 								{return myService;}	
	public void setMyService(SeguridadService myService)				{this.myService = myService;}

	public List<SelectItem> getPuestoList() 							{return puestoList;}
	public void setPuestoList(List<SelectItem> puestoList) 				{this.puestoList = puestoList;}
	
	public List<SelectItem> getInstitucionList() 						{return institucionList;}
	public void setInstitucionList(List<SelectItem> institucionList) 	{this.institucionList = institucionList;}

	public Long getInstitucion() 										{return institucion;}
	public void setInstitucion(Long institucion) 						{this.institucion = institucion;}

	public String getNombreInstitucion() 								{return nombreInstitucion;}
	public void setNombreInstitucion(String nombreInstitucion) 			{this.nombreInstitucion = nombreInstitucion;}
} 
