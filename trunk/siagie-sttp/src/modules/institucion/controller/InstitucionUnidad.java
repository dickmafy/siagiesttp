package modules.institucion.controller; 
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.MarcoService;
import modules.administracion.domain.AmbienteUnidad;
import modules.mantenimiento.domain.TipoAmbiente;
import modules.marco.domain.Itinerario;
import modules.seguridad.domain.Usuario;

public class InstitucionUnidad extends GenericController   
{
	private MarcoService		myService;
	private List<Itinerario> 	itinerarioList;
	private List<SelectItem>    ambienteList;
	
	private String nombreProfesion;
	private Long institucion;
	private Itinerario itinerarioSelect;
	
	public void init(Long profesion, String nombreProfesion) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucion";
		moduleName="Itinerario ";
		userName=usr.getUsuario();		
		institucion=usr.getInstitucion();
		
		this.nombreProfesion=nombreProfesion;
		
		page_new="itc_oferta_amb";
		page_main="itc_oferta_amb";
		page_update="itc_oferta_amb";
		
		ambienteList=getListSelectItem(new TipoAmbiente(), "id", "nombre",true);
		itinerarioList=myService.listarItinerario(profesion, -1L);
		
		setBean(new AmbienteUnidad());
		forward("itc_oferta_itnr");	
	}
	
	public void optionAmbiente() throws Exception
	{
		setBean(new AmbienteUnidad());
		defaultList();
		forward("itc_oferta_amb");	
	}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(myService.listarAmbienteTipo(institucion, itinerarioSelect.getId()));}
	
	@Override
	public void beforeSave() throws Exception
	{
		AmbienteUnidad object = (AmbienteUnidad)getBean();
		object.setInstitucion(institucion);
		object.setUnidad(itinerarioSelect.getId());
		setBean(object);
	}
	
	@Override
	public void afterSave() throws Exception
	{setBean(new AmbienteUnidad());}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		AmbienteUnidad object = (AmbienteUnidad)getBean();
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el tipo de Ambiente.");			
			success = false;
		}
		else if(!validateZero(object.getHoras()))
		{
			setMessageError("Debe ingresar el número del horas.");			
			success = false;
		}
		object=null;
		return success;
	}  

	public void optionDelete() throws Exception
	{
		myService.eliminarAmbienteTipo(((AmbienteUnidad)getBeanSelected()).getId());
		defaultList();
		setBean(new AmbienteUnidad());
		setMessageSuccess("El registro se elimino satisfactoriamente.");
	}
	
	public List<Itinerario> getItinerarioList() 						{return itinerarioList;}
	public void setItinerarioList(List<Itinerario> itinerarioList) 		{this.itinerarioList = itinerarioList;}
	
	public Itinerario getItinerarioSelect() 							{return itinerarioSelect;}
	public void setItinerarioSelect(Itinerario itinerarioSelect) 		{this.itinerarioSelect = itinerarioSelect;}

	public String getNombreProfesion() 									{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 				{this.nombreProfesion = nombreProfesion;}

	public MarcoService getMyService() 									{return myService;}	
	public void setMyService(MarcoService myService)					{this.myService = myService;}

	public List<SelectItem> getAmbienteList() 							{return ambienteList;}
	public void setAmbienteList(List<SelectItem> ambienteList) 			{this.ambienteList = ambienteList;}
} 
