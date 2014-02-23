package modules.institucion.controller; 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.SeguridadService;
import modules.administracion.domain.Personal;
import modules.mantenimiento.domain.Puesto;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

public class InstitucionPersonal extends GenericController   
{
	private List<SelectItem> puestoList;
	
	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia,distrito;
	private SeguridadService	myService;
	private Long institucion;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucional";
		moduleName="Personal";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		defaultList();
		page_new="itc_prs_new";
		page_main="itc_prs_lst";
		page_update="itc_prs_upd";
		forward(page_main);
		
		puestoList=getListSelectItem(new Puesto(), "id", "nombre",true);
	}
	
	
	
	@Override
	public void defaultList() throws Exception
	{
		Personal bean=new Personal();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBeanList(getService().listByObject(bean));
		bean=null;
	}
		
	@Override
	public void afterNew() throws Exception
	{
		Personal bean=new Personal();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		fillDep();
	}
	
	
	/*
	@Override
	public void beforeNew() throws Exception {
		Personal bean=new Personal();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		fillDep();
	}
	*/
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Personal object = (Personal)getBean();
		if(!validateEmpty(object.getCodigo()))
		{
			setMessageError("Debe ingresar el C�digo Modular.");			
			success = false;
		}
		else if(!validateEmpty(object.getDni()))
		{
			setMessageError("Debe ingresar el n�mero del DNI.");			
			success = false;
		}
		else if(!validateSelect(object.getPuesto()))
		{
			setMessageError("Debe seleccionar el Cargo.");			
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
			setMessageError("Debe ingresar la direcci�n.");			
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
		getListUbigeoGuardado();
	}

	private void getListUbigeoGuardado() throws Exception {
		//Trae el ubigeo Guardado del Personal
		try 
		{
			Personal bean=(Personal)getBeanSelected();
			bean = (Personal) myService.findById(bean);
			Ubigeo ubigeo  = (Ubigeo) getService().findById(Ubigeo.class,bean.getUbigeo());

			departamento = ubigeo.getDepartamento();
			depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
			provincia = ubigeo.getProvincia();
			proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
			disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
			distrito = ubigeo.getId();
			
			Personal beanTemporal = (Personal)getBean();
			beanTemporal.setUbigeo(distrito);
			setBean(beanTemporal);
			
		}
		catch (Exception e) 
		{
			fillDep();
			fillPro();
			fillDis();
		}
	}
	
	public void optionGoAcademico() throws Exception
	{
		InstitucionHistorialAcademico go = (InstitucionHistorialAcademico)getSpringBean("institucionHistorialAcademico");
		go.init(((Personal)getBeanSelected()).getId().longValue(), ((Personal)getBeanSelected()).getNombreCompleto());
	}
	
	public void optionGoLaboral() throws Exception
	{
		InstitucionHistorialLaboral go = (InstitucionHistorialLaboral)getSpringBean("institucionHistorialLaboral");
		go.init(((Personal)getBeanSelected()).getId().longValue(), ((Personal)getBeanSelected()).getNombreCompleto());
	}
	
	
	public List<SelectItem> getDepList() 						{return depList;}
	public void setDepList(List<SelectItem> depList) 			{this.depList = depList;}
	public List<SelectItem> getProList() 						{return proList;}
	public void setProList(List<SelectItem> proList) 			{this.proList = proList;}
	public List<SelectItem> getDisList() 						{return disList;}
	public void setDisList(List<SelectItem> disList) 			{this.disList = disList;}
	public Long getDepartamento() 								{return departamento;}
	public void setDepartamento(Long departamento) 				{this.departamento = departamento;}
	public Long getProvincia() 									{return provincia;}
	public void setProvincia(Long provincia) 					{this.provincia = provincia;}
	
	public SeguridadService getMyService() 						{return myService;}	
	public void setMyService(SeguridadService myService)		{this.myService = myService;}

	public List<SelectItem> getPuestoList() 					{return puestoList;}
	public void setPuestoList(List<SelectItem> puestoList) 		{this.puestoList = puestoList;}

	public Long getDistrito() 									{return distrito;	}
	public void setDistrito(Long distrito) 						{this.distrito = distrito;	}
} 
