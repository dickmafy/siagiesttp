package modules.admision.controller; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.AdmisionService;
import modules.admision.domain.Postulante;
import modules.admision.domain.Proceso;
import modules.seguridad.domain.Usuario;

@ManagedBean
@SessionScoped
public class AdmisionIngresante extends GenericController   
{
	private AdmisionService	myService;
	private List<SelectItem>    procesoList;
	
	private boolean enabled;
	private Long 	proceso;
	private Long 	annio;
	private Long institucion;
	
	public void init(Long id) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Ingresantes";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		proceso=id;
		annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");
		
		page_new="adm_ing_lst";
		page_main="adm_ing_lst";
		page_update="adm_ing_lst";	
		
		procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
		optionSelectProceso();
		forward(page_main);
	}
	public void init() throws Exception
	{init(-1L);}
	
	public void selectAnnio() throws Exception
	{
		proceso=-1L;
		procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
	}
	
	public void defaultList() throws Exception
	{setBeanList(myService.listarPostulante(proceso, 2L));}
	
	public void optionSelectProceso() throws Exception
	{
		if(proceso.longValue()>0L)	{defaultList();}
		else						{setBeanList(new ArrayList());}
		enabledOptions();
	}
	public void enabledOptions() throws Exception
	{
		if(proceso.longValue()>0L)
		{
			Proceso bean=(Proceso)myService.findById(Proceso.class, proceso);
			if(bean.getEstado().longValue()==4L)	{enabled=true;}
			bean=null;
		}
		else
		{enabled=false;}
	}
	
	public void optionUpdate() throws Exception
	{
		if(enabled)
		{
			List<Postulante> postulantes=(List<Postulante>)getBeanList();
			for(int i=0; i<postulantes.size(); i++)
			{getService().save(postulantes.get(i));}
			defaultList();
			setMessageSuccess("El registro de ingresantes fue actualizado con éxito.");
		}
		else
		{setMessageError("El registro de ingresantes a cadudado ó no se encuentra habilitado.");}
	}
	
	public List<SelectItem> getProcesoList() 									{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 					{this.procesoList = procesoList;}
	
	public Long getProceso() 													{return proceso;}
	public void setProceso(Long proceso) 										{this.proceso = proceso;}
	
	public boolean isEnabled() 													{return enabled;}
	public void setEnabled(boolean enabled) 									{this.enabled = enabled;}
	
	public AdmisionService getMyService() 										{return myService;}	
	public void setMyService(AdmisionService myService)							{this.myService = myService;}
	
	public Long getAnnio() 														{return annio;}
	public void setAnnio(Long annio) 											{this.annio = annio;}
} 
