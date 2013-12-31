package modules.mantenimiento.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.SeguridadService;
import modules.administracion.domain.Personal;
import modules.mantenimiento.domain.Colegio;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

public class MantenimientoColegio extends GenericController   
{

	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia,distrito;
	private SeguridadService	myService;
	
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Mantenimiento";
		moduleName="Colegio";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="col_new";
		page_main="col_list";
		page_update="col_update";
		forward(page_main);
	}
		
	public void afterNew() throws Exception
	{
		setEnabled(new Colegio());
		fillDep();
	}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(getService().listByObjectEnabledDisabled(new Colegio()));}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Colegio object = (Colegio)getBean();
		
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe ingresar el tipo.");			
			success = false;
		}
		else if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre.");			
			success = false;
		}
		
		else if(!validateEmpty(object.getTelefono()))
		{
			setMessageError("Debe ingresar el número telefónico.");			
			success = false;
		}
		
		else if(!validateLengthGreater(object.getTelefono(),7))
		{
			setMessageError("El teléfono debe tener 7 digitos");
			success=false;
		}
		
		else if(!validateNumeric(object.getTelefono()))
		{
			setMessageError("El teléfono debe de contener solo números");
			success=false;
		}
		
		else if(!validateSelect(departamento))
		{
			setMessageError("Debe seleccionar el Departamento.");			
			success = false;
		}
		
		else if(!validateSelect(provincia))
		{
			setMessageError("Debe seleccionar la Provincia.");			
			success = false;
		}

		else if(!validateSelect(object.getUbigeo()))
		{
			setMessageError("Debe seleccionar el distrito.");			
			success = false;
		}
		else if(!validateEmpty(object.getDireccion()))
		{
			setMessageError("Debe ingresar la Direccion.");			
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
		Colegio bean=(Colegio)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		departamento=-1L;
		provincia=-1L;
	}
	
	public void fillPro() throws Exception
	{
		proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
		disList=new ArrayList<SelectItem>();
		
		Colegio bean=(Colegio)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		provincia=-1L;
	}
	
	public void fillDis() throws Exception
	{
		disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		Colegio bean=(Colegio)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
	}

	@Override
	public void afterLoad() throws Exception 
	{
		//Trae el ubigeo Guardado del Colegio
		getListUbigeoGuardado();
	}

	private void getListUbigeoGuardado() throws Exception {
		try 
		{
			Colegio bean=(Colegio)getBeanSelected();
			bean = (Colegio) myService.findById(bean);
			Ubigeo ubigeo  = (Ubigeo) getService().findById(Ubigeo.class,bean.getUbigeo());

			departamento = ubigeo.getDepartamento();
			depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
			provincia = ubigeo.getProvincia();
			proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
			disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
			distrito = ubigeo.getId();
			
			Colegio beanTemporal = (Colegio)getBean();
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
	public List<SelectItem> getDepList() 					{return depList;}
	public void setDepList(List<SelectItem> depList) 		{this.depList = depList;}

	public List<SelectItem> getProList() 					{return proList;}
	public void setProList(List<SelectItem> proList) 		{this.proList = proList;}

	public List<SelectItem> getDisList() 					{return disList;}
	public void setDisList(List<SelectItem> disList) 		{this.disList = disList;}

	public Long getDepartamento() 							{return departamento;}
	public void setDepartamento(Long departamento) 			{this.departamento = departamento;}

	public Long getProvincia() 								{return provincia;}
	public void setProvincia(Long provincia) 				{this.provincia = provincia;}

	public SeguridadService getMyService() 					{return myService;}

	public void setMyService(SeguridadService myService) 	{this.myService = myService;}
	public Long getDistrito() 									{return distrito;	}
	public void setDistrito(Long distrito) 						{this.distrito = distrito;	}
	

} 
