package modules.mantenimiento.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import modules.mantenimiento.domain.Supervision;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.GeneralService;

public class MantenimientoSupervision extends GenericController   
{	 
	
	private List<SelectItem> dreList;
	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia;
	private GeneralService	myService;
	private boolean tipo;
	private Long dre;
	
	@Override
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Tablas Maestras";
		userName=usr.getUsuario();
		dre=-1L;
		defaultList();
		forward(page_main);
		
	}
	
	public void initUgel() throws Exception 
	{
		moduleName="UGEL";
		tipo=false;
		page_new="ugel_new";
		page_main="ugel_list";
		page_update="ugel_update";
		init();
		dreList=getListSelectItem(myService.listarSupervision(null,true),"nivel","nombre",true);
	}
	public void initDre() throws Exception 
	{
		moduleName="DRE";
		tipo=true;
		page_new="dre_new";
		page_main="dre_list";
		page_update="dre_update";
		init();
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		Supervision bean = (Supervision) getBean();
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
	
	public void fillDep() throws Exception
	{
		depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
		proList=new ArrayList<SelectItem>();
		disList=new ArrayList<SelectItem>();
		
		Supervision bean = (Supervision) getBean();
		bean.setUbigeo(-1L);
		departamento=-1L;
		provincia=-1L;

	}
	public void fillPro() throws Exception
	{
		proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
		disList=new ArrayList<SelectItem>();

		Supervision bean = (Supervision) getBean();
		bean.setUbigeo(-1L);
		provincia=-1L;
	}
	
	public void fillDis() throws Exception
	{
		disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		
		Supervision bean = (Supervision) getBean();
		bean.setUbigeo(-1L);
		
	}
	
	@Override
	public void defaultList() throws Exception 
	{setBeanList(myService.listarSupervision(dre,tipo));}
	
	@Override
	public void afterNew() throws Exception 
	{
		setEnabled(new Supervision());
		Supervision bean = (Supervision) getBean();
		if(tipo)	{bean.setNivel(0L);}
		else		{bean.setNivel(dre);}
		bean.setSubnivel(0L);
		setBean(bean);
		fillDep();		
		bean=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Supervision object = (Supervision)getBean();

		if(!tipo && !validateSelect(object.getNivel()))
		{
			setMessageError("Debe seleccionar una DRE.");
			success = false;
		}
		else if(!validateEmpty(object.getRuc()))
		{
			setMessageError("Debe ingresar el RUC.");
			success = false;
		}
		else if(!validateNumeric(object.getRuc()))
		{
			setMessageError("EL RUC solo debe estar conformado por números.");
			success = false;
		}
		else if(!validateLength(object.getRuc(),11))
		{
			setMessageError("Debe ingresar los 11 digitos del RUC.");
			success = false;
		}
		else if(!validateEmpty(object.getNombre()))
		{
			if(tipo)	{setMessageError("Debe ingresar el nombre de la DRE.");}
			else		{setMessageError("Debe ingresar el nombre de la UGEL.");}
			success = false;
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
			setMessageError("Debe seleccionar el Distrito.");			
			success = false;
		}
		else if(!validateEmpty(object.getDireccion()))
		{
			setMessageError("Debe ingresar la Dirección");			
			success = false;
		}
		
	
		object=null;
		return success;
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
	
	public List<SelectItem> getDreList() 						{return dreList;}
	public void setDreList(List<SelectItem> dreList) 			{this.dreList = dreList;}

	public GeneralService getMyService() 						{return myService;}	
	public void setMyService(GeneralService myService)			{this.myService = myService;}

	public Long getDre() 										{return dre;}
	public void setDre(Long dre) 								{this.dre = dre;}
} 
