package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.IntranetService;
import modules.horario.domain.SilaboContenido;
import modules.seguridad.domain.Usuario;

public class DocenteSilaboContenido extends GenericController   
{
	private IntranetService	myService;
	private List<SelectItem>   		elementoList;
	private Long seccion,modulo,profesion,tipoModulo, tipo;
	private String nombreUnidad;
	
	public void init(Long seccion, Long profesion, Long modulo, Long tipoModulo, String nombreUnidad) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Sílabo Contenido";
		userName=usr.getUsuario();
		this.seccion=seccion;
		this.modulo=modulo;
		this.tipoModulo=tipoModulo;
		this.profesion=profesion;
		this.nombreUnidad=nombreUnidad;
		
		defaultList();
		page_new="docente_silabo_cont_new";
		page_main="docente_silabo_cont";
		page_update="docente_silabo_cont_upd";
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		List<SilaboContenido> listado=myService.listarContenido(seccion);
		List<SilaboContenido> elemento=new ArrayList<SilaboContenido>();
		setBeanList(listado);
		for(int i=0; i<listado.size(); i++)
		{
			if(listado.get(i).getActividad().longValue()==0L)
			{elemento.add(listado.get(i));}
		}
		elementoList = getListSelectItem(elemento, "elemento", "descripcion",true);
	}
	
	public void optionNew() throws Exception
	{
		tipo=-1L;
		setBean(new SilaboContenido());
		forward(page_new);
	}
	
	public void afterLoad() throws Exception
	{
		SilaboContenido ref=(SilaboContenido)getBean();
		if(ref.getActividad().longValue()==0L)	{tipo=1L;}
		else									{tipo=2L;}
	}
	
	public void optionDelete() throws Exception
	{
		SilaboContenido bean=(SilaboContenido)getBeanSelected();
		myService.eliminarContenido(bean.getId());
		defaultList();
	}
	
	public void setting() throws Exception
	{
		SilaboContenido ref=(SilaboContenido)getBean();
		ref.setElemento(0L);
		ref.setActividad(0L);
		ref.setSeccion(seccion);
		ref.setTipo(-1L);
		setBean(ref);
		ref=null;
	}
	
	public void setSemana() throws Exception
	{
		SilaboContenido ref=(SilaboContenido)getBeanSelected();
		myService.actualizarSemana(ref.getId(), ref.getSemana());
		setMessageSuccess("El número de semana de la actividad se actualizo exitosamente.");
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		SilaboContenido object = (SilaboContenido)getBean();
		if(tipo.longValue()==1L && !validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el Tipo de Contenido del Elemento de Capacidad.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción.");			
			success = false;
		}
		else if(tipo.longValue()==1L && object.getTipo().longValue()==1L && !validateEmpty(object.getContenido()))
		{
			setMessageError("Debe ingresar el Contenido Básico del Elemento de Capacidad.");			
			success = false;
		}
		else if(tipo.longValue()==2L && !validateSelect(object.getElemento()))
		{
			setMessageError("Debe seleccionar el Elemento de Capacidad.");			
			success = false;
		}
		object=null;
		return success;
	}
	
	
	public List<SelectItem> getElementoList() 							{return elementoList;}
	public void setElementoList(List<SelectItem> elementoList) 			{this.elementoList = elementoList;}

	public IntranetService getMyService() 								{return myService;}
	public void setMyService(IntranetService myService) 				{this.myService = myService;}

	public String getNombreUnidad()				 						{return nombreUnidad;}
	public void setNombreUnidad(String nombreUnidad)		 			{this.nombreUnidad = nombreUnidad;}
		
	public Long getTipo() 												{return tipo;}
	public void setTipo(Long tipo) 										{this.tipo = tipo;}

} 
