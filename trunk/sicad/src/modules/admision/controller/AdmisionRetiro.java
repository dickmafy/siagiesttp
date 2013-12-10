package modules.admision.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.AdmisionService;
import dataware.service.SeguridadService;
import modules.admision.domain.Matricula;
import modules.admision.domain.Retiro;
import modules.admision.domain.Persona;
import modules.admision.domain.Proceso;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

@ManagedBean
@SessionScoped
public class AdmisionRetiro extends GenericController   
{
	
	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia;
	private AdmisionService	myService;
	private SeguridadService segService;
	private Retiro  beanRetiro;
	private Persona beanPersona;
	private boolean show;
	private	Long seccion;
	private Long proceso;
	private Long institucion;
	private String dni;
	private String nombreProceso;
	
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		Proceso prc = (Proceso)getSpringBean("procesoSesion");
		appName="Admision";
		moduleName="Postulante";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		proceso=prc.getId();
	
		page_new="adm_ret_new";
		page_main="adm_ret_new";
		page_update="adm_ret_new";		
		
		forward(page_main);
		usr=null;
		dni="";
		beanRetiro = new Retiro();
		//nombreProceso=prc.getAnnio().toString();
		show=false;
	}
	
	public void optionLoadPersona() throws Exception
	{
		if(dni.length()<=3)
		{setMessageError("Debe ingresar los 8 dígitos del DNI para ejecutar correctamente la búsqueda.");}
		else
		{
			System.out.println(proceso+"proceso Actual");
			Retiro bean=segService.retirar_matriculado(institucion,dni);
			fillDep();
			if(bean!=null)	
			{
				beanRetiro=bean; show=true; 
				System.out.println(beanRetiro.getMatricula()+"Dentro de la carga");
				
				Ubigeo beanUbigeo=(Ubigeo) segService.findById(Ubigeo.class, beanRetiro.getUbigeo());
				departamento=beanUbigeo.getDepartamento();
				provincia=beanUbigeo.getProvincia();
				beanUbigeo=null;
				
				depList=getListSelectItem(segService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
				proList=getListSelectItem(segService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
				disList=getListSelectItem(segService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
				
				setBean(beanRetiro);
			}
			else			
			{
				show=false;
				beanRetiro=new Retiro();
				setMessageError("El Alumno no se encuentra matriculado en el proceso.");
			}
		}
	}
	
	@Override
	public void nativeSave(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		if(validation())
		{setMessageSuccess("El retiro se ha realizado satisfactoriamente");}
		afterSave();
		
	}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success=true;
		Retiro object =(Retiro)getBean();
		object.setMatricula(beanRetiro.getMatricula());
		object.setPersona(beanRetiro.getPersona());
		object.setEstado(1L);
		
		if(!validateSelect(object.getMotivo()))
		{
			setMessageError("Debe ingresar el motivo del retiro");			
			success = false;
		}

		if(!validateSelect(object.getMatricula()))
		{
		
			setMessageError("Debe de seleccionar el Alumno a retirar");			
			success = false;
		}
		Matricula matri = new Matricula();
		matri.setId(beanRetiro.getMatricula());
		Matricula beanMat = (Matricula) segService.findById(matri);
		segService.updateStatus(beanMat, 5L);
		return success;
	}
	
			
	@Override
	public void afterSave() throws Exception
	{beanRetiro = null;		dni = null;}
	
	
	public void fillDep() throws Exception
	{
		depList=getListSelectItem(segService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
		proList=new ArrayList<SelectItem>();
		disList=new ArrayList<SelectItem>();
		Retiro bean=(Retiro)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		departamento=-1L;
		provincia=-1L;
	}
	
	public void fillPro() throws Exception
	{
		proList=getListSelectItem(segService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
		disList=new ArrayList<SelectItem>();
		
		Retiro bean=(Retiro)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
		provincia=-1L;
	}
	
	public void fillDis() throws Exception
	{
		disList=getListSelectItem(segService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		Retiro bean=(Retiro)getBean();
		bean.setUbigeo(-1L);
		setBean(bean);
	}
	
	
	public AdmisionService getMyService() 										{return myService;}	
	public void setMyService(AdmisionService myService)							{this.myService = myService;}

	public Long getProceso() 													{return proceso;}
	public void setProceso(Long proceso) 										{this.proceso = proceso;}

	public String getDni() 														{return dni;}
	public void setDni(String dni) 												{this.dni = dni;}

	public Retiro getBeanRetiro() 												{return beanRetiro;}
	public void setBeanRetiro(Retiro beanRetiro) 								{this.beanRetiro = beanRetiro;}

	public String getNombreProceso() 											{return nombreProceso;}
	public void setNombreProceso(String nombreProceso) 							{this.nombreProceso = nombreProceso;}

	public Long getSeccion() 													{return seccion;}
	public void setSeccion(Long seccion) 										{this.seccion = seccion;}

	public boolean isShow() 													{return show;}
	public void setShow(boolean show) 											{this.show = show;}

	public List<SelectItem> getDepList() 										{return depList;}
	public void setDepList(List<SelectItem> depList) 							{this.depList = depList;}

	public List<SelectItem> getProList() 										{return proList;}
	public void setProList(List<SelectItem> proList) 						    {this.proList = proList;}

	public List<SelectItem> getDisList() 										{return disList;}
	public void setDisList(List<SelectItem> disList) 							{this.disList = disList;}

	public Long getDepartamento() 												{return departamento;}
	public void setDepartamento(Long departamento) 							    {this.departamento = departamento;}

	public Long getProvincia() 													{return provincia;}
	public void setProvincia(Long provincia) 									{this.provincia = provincia;}

	public Persona getBeanPersona() 											{return beanPersona;}
	public void setBeanPersona(Persona beanPersona) 							{this.beanPersona = beanPersona;}

	public Long getInstitucion() 												{return institucion;}
	public void setInstitucion(Long institucion) 								{this.institucion = institucion;}
	
	public SeguridadService getSegService() 					{return segService;}
	public void setSegService(SeguridadService segService) 		{this.segService = segService;}
} 
