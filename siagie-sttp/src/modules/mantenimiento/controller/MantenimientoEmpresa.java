package modules.mantenimiento.controller;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.SeguridadService;
import modules.mantenimiento.domain.Empresa;
import modules.mantenimiento.domain.GiroNegocio;
import modules.seguridad.domain.Usuario;

public class MantenimientoEmpresa extends GenericController   
{	 
	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia;
	private SeguridadService	myService;
	private List<SelectItem> listaGiro;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Mantenimiento";
		moduleName="Empresa";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="empresa_new";
		page_main="empresa_list";
		page_update="empresa_update";
		forward(page_main);
		listaGiro = getListSelectItem(new GiroNegocio(), "id", "nombre", true);
	}
	
	@Override
	public void afterNew() throws Exception 
	{
		setEnabled(new Empresa());
		fillDep();
	}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Empresa object = (Empresa)getBean();
		
		if(!validateEmpty(object.getRuc()))
		{
			setMessageError("Debe ingresar el númrto de RUC.");			
			success = false;
		}
		else if(!validateNumeric(object.getRuc()))
		{
			setMessageError("EL RUC solo debe estar conformado por números.");
			success = false;
		}
		else if(!validateLength(object.getRuc(), 11))
		{
			setMessageError("Debe ingresar los 11 digitos del RUC.");	
			success = false;
		}
		else if(!validateEmpty(object.getRazon_social()))
		{
			setMessageError("Debe ingresar la razon social");			
			success = false;
		}
		else if(!validateSelect(object.getNegocio()))
		{
			setMessageError("Debe seleccionar el giro de negocio.");			
			success = false;
		}
		else if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el tipo de sociedad.");			
			success = false;
		}
		else if(!validateSelect(provincia))
		{
			setMessageError("Debe seleccionar la Provincia.");			
			success = false;
		}
		else if(!validateSelect(departamento))
		{
			setMessageError("Debe seleccionar el Departamento.");			
			success = false;
		}
		else if(!validateSelect(object.getUbigeo()))
		{
			setMessageError("Debe seleccionar el Distrito.");			
			success = false;
		}
		else if(!validateEmpty(object.getDireccion()))
		{
			setMessageError("Debe ingresar la Dirección.");			
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
		Empresa bean=(Empresa)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		departamento=-1L;
		provincia=-1L;
	}
	
	public void fillPro() throws Exception
	{
		proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
		disList=new ArrayList<SelectItem>();
		
		Empresa bean=(Empresa)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		provincia=-1L;
	}
	
	public void fillDis() throws Exception
	{
		disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		Empresa bean=(Empresa)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
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
		
	public List<SelectItem> getListaGiro() 						{return listaGiro;}
	public void setListaGiro(List<SelectItem> listaGiro)		{this.listaGiro = listaGiro;}
} 
