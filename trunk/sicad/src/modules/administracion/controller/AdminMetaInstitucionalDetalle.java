package modules.administracion.controller; 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.HorarioService;
import modules.administracion.domain.MetaDetalle;
import modules.administracion.domain.Solicitud;
import modules.mantenimiento.domain.Resolucion;
import modules.seguridad.domain.Usuario;

public class AdminMetaInstitucionalDetalle extends GenericController   
{
	private HorarioService		myService;
	private List<SelectItem> 	resolucionList;
	private List<SelectItem>	solicitudList;
	
	private Long 	annio,proceso,profesion,turno;
	private Long 	institucion;
	
	public void init(Long institucion,Long annio, Long proceso, Long profesion, Long turno) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		
		this.institucion=institucion;
		this.annio=annio;
		this.proceso=proceso;
		this.profesion=profesion;
		this.turno=turno;
		
		defaultList();
		
		Resolucion listResolucion=new Resolucion();
		resolucionList = getListSelectItem(listResolucion, "id", "nombre", true);
		listResolucion = null;
		
		Solicitud listSolicitud=new Solicitud();
		listSolicitud.setInstitucion(institucion);
		listSolicitud.setTipo(6L);
		solicitudList = getListSelectItem(listSolicitud, "id", "resolucion", true);
		listSolicitud=null;
		
		page_main="meta_institucional_list_detail";
		page_update="meta_institucional_list_detail_update";
		forward(page_main);
	}
	
	@Override
	public void beforeUpdate() throws Exception
	{
		MetaDetalle bean=(MetaDetalle)getBean();
		Long metaTotal=bean.getIngresantes().longValue()+bean.getRegular().longValue()-(bean.getRezagados().longValue()+bean.getRetiros().longValue());
		bean.setMetaTotal(metaTotal);
		setBean(bean);
		bean=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		if(institucion.longValue()>0L && annio.longValue()>0L && proceso.longValue()>0L && profesion.longValue()>0L && turno.longValue()>0L)
		{setBeanList(myService.listarMetaDetalle(institucion, annio, proceso, profesion, turno));}
		else
		{setBeanList(new ArrayList<MetaDetalle>());}
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		MetaDetalle object = (MetaDetalle)getBean();
		if(!validateSelect(object.getResolucion()) && object.getCantidad()>1)
		{
			setMessageError("Debe seleccionar la Resolución para ejecutar la actualización.");			
			success = false;
		}
		object=null;
		return success;
	}
	
	
	public HorarioService getMyService() 							{return myService;}
	public void setMyService(HorarioService myService) 				{this.myService = myService;}

	public List<SelectItem> getResolucionList() 					{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 	{this.resolucionList = resolucionList;}

	public List<SelectItem> getSolicitudList() 						{return solicitudList;}
	public void setSolicitudList(List<SelectItem> solicitudList) 	{this.solicitudList = solicitudList;}
	
	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getProceso() 										{return proceso;}
	public void setProceso(Long proceso) 							{this.proceso = proceso;}

	public Long getProfesion() 										{return profesion;}
	public void setProfesion(Long profesion) 						{this.profesion = profesion;}

	public Long getTurno() 											{return turno;}
	public void setTurno(Long turno) 								{this.turno = turno;}

} 
