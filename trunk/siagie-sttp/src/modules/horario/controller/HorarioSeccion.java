package modules.horario.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.HorarioService;
import modules.administracion.domain.AmbienteUnidad;
import modules.administracion.domain.MetaDetalle;
import modules.seguridad.domain.Usuario;

public class HorarioSeccion extends GenericController   
{
	private List<SelectItem> 		seccionList;	
	private List<AmbienteUnidad> 	ambienteTipoList;
	
	private	Long 	institucion,seccion,unidadDidactica;
	private	Long	turno,totalMeta,capacidad,estado, horas, pkMetaDet;
	
	private HorarioService	myService;
	
	public void init(Long pk, Long und, Long trn, Long hrs, Long total, Long estado) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Secciones";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		
		unidadDidactica=und;
		turno=trn;
		horas = hrs;
		pkMetaDet=pk;
		
		capacidad=0L;
		this.estado=estado;
		
		seccionList=getListSelectItem(myService.listarSeccion(pk),"id","nombre",true);
		if(seccionList.size()>2)	{totalMeta=total/(seccionList.size()-1);}
		else						{totalMeta=total;}
		seccion=-1L;
		selectSeccion();
		
		page_new="hor_meta_sec";
		page_main="hor_meta_sec";
		page_update="hor_meta_sec";
		forward(page_main);
		
	}
	
	public void selectSeccion() throws Exception 
	{
		if(seccion.longValue()>0L)	{ambienteTipoList=myService.listarAmbienteUnidad(institucion, seccion, unidadDidactica);}
		else						{ambienteTipoList=new ArrayList<AmbienteUnidad>();}
	}
	
	public void optionHorario() throws Exception
	{
		if(seccion>0L)
		{
			HorarioSeccionDistribucion go = (HorarioSeccionDistribucion)getSpringBean("horarioSeccionDistribucion");
			go.init(seccion, ((AmbienteUnidad)getBeanSelected()).getTipo(), turno, ((AmbienteUnidad)getBeanSelected()).getHoras(), totalMeta, ((AmbienteUnidad)getBeanSelected()).getNombreTipo(), ((AmbienteUnidad)getBeanSelected()).getValorAmbiente(), estado);
		}
	}
	
	//fer
	public void prePublicar() throws Exception
	{		
		int hrs=0;
		
		for (AmbienteUnidad item : ambienteTipoList) 
		{
			hrs = hrs + item.getHoras().intValue();		
		}
				
		if(hrs >= horas.intValue())
		{
			MetaDetalle metaDet = (MetaDetalle) myService.findById(MetaDetalle.class, pkMetaDet);
			metaDet.setEstado(2L);
			myService.updateStatus(metaDet, 2L);
			setMessageSuccess("El registro fue pre-publicado satifactoriamente.");
		}
		
	}
	
	
	public HorarioService getMyService() 										{return myService;}
	public void setMyService(HorarioService myService) 							{this.myService = myService;}
	
	public Long getCapacidad() 													{return capacidad;}
	public void setCapacidad(Long capacidad) 									{this.capacidad = capacidad;}
	
	public Long getSeccion() 													{return seccion;}
	public void setSeccion(Long seccion) 										{this.seccion = seccion;}
	
	public List<SelectItem> getSeccionList() 									{return seccionList;}
	public void setSeccionList(List<SelectItem> seccionList) 					{this.seccionList = seccionList;}

	public List<AmbienteUnidad> getAmbienteTipoList() 							{return ambienteTipoList;}
	public void setAmbienteTipoList(List<AmbienteUnidad> ambienteTipoList) 		{this.ambienteTipoList = ambienteTipoList;}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	
	
} 
