package modules.marco.controller; 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.MarcoService;
import modules.marco.domain.Transversal;
import modules.seguridad.domain.Usuario;

public class MarcoTransversal extends GenericController   
{	 
	private MarcoService	myService;
	private List<Transversal> esquemaList;
	private List<Transversal> contenidoList;
	
	private List<SelectItem> moduloList;
	private List<SelectItem> capacidadList;
	
	private Long tipo;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Marco Educativo";
		moduleName="Modulos Transversales";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="trnv_new";
		page_main="trnv_list";
		page_update="trnv_update";
		System.out.println("toodo ok 1");
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		List<Transversal> lst=myService.listarReferenteTransversal(0, 0L);
		contenidoList=new ArrayList<Transversal>();
		esquemaList=new ArrayList<Transversal>();
		
		for(int i=0; i<lst.size(); i++)
		{
			if(lst.get(i).getTipo().longValue()==3L && lst.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())	
			{contenidoList.add(lst.get(i));}
			if(lst.get(i).getTipo().longValue()!=3L && lst.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())	
			{esquemaList.add(lst.get(i));}
		}
		fillModulo();
		
	}
	
	public void setting() throws Exception
	{
		Transversal ref=(Transversal)getBean();
		ref.setModulo(0L);
		ref.setNivel(0L);
		ref.setSubnivel(0L);		
		ref.setEstado(Constante.ROW_STATUS_ENABLED);
		if(tipo.longValue()==2L)	{ref.setTipo(1L);}
		if(tipo.longValue()==3L)	{ref.setTipo(2L);}
		if(tipo.longValue()==4L)	{ref.setTipo(2L);}
		if(tipo.longValue()==5L)	{ref.setTipo(3L);}
		setBean(ref);
	}
	
	public void fillModulo() throws Exception
	{
		List<Transversal> list=new ArrayList<Transversal>();
		for(int i=0; i<esquemaList.size(); i++)
		{
			if(esquemaList.get(i).getNivel().longValue()==0L && esquemaList.get(i).getSubnivel().longValue()==0L)
			{list.add(esquemaList.get(i));}
		}
		moduloList=getListSelectItem(list, "modulo", "titulo", true);
		list=null;
	}
	public void fillCapacidad() throws Exception
	{
		List<Transversal> list=new ArrayList<Transversal>();
		for(int i=0; i<esquemaList.size(); i++)
		{
			if(esquemaList.get(i).getModulo().longValue()==((Transversal)getBean()).getModulo() && esquemaList.get(i).getNivel().longValue()!=0L && esquemaList.get(i).getSubnivel().longValue()==0L)
			{list.add(esquemaList.get(i));}
		}
		capacidadList=getListSelectItem(list, "nivel", "descripcion", true);
		list=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Transversal object = (Transversal)getBean();
		if(tipo.longValue()>2L && !validateSelect(object.getModulo()))
		{
			setMessageError("Debe seleccionar una Modulo.");			
			success = false;
		}
		else if(tipo.longValue()==4L && !validateSelect(object.getNivel()))
		{
			setMessageError("Debe seleccionar una Capacidad Terminal.");			
			success = false;
		}
		else if(!validateEmpty(object.getTitulo()) && tipo.longValue()<3L)
		{
			setMessageError("Debe ingresar el titulo.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripcion.");			
			success = false;
		}
		object=null;
		return success;
	}
	
	public MarcoService getMyService() 										{return myService;}
	public void setMyService(MarcoService myService) 						{this.myService = myService;}

	public List<Transversal> getEsquemaList() 								{return esquemaList;}
	public void setEsquemaList(List<Transversal> esquemaList) 				{this.esquemaList = esquemaList;}

	public List<Transversal> getContenidoList() 							{return contenidoList;}
	public void setContenidoList(List<Transversal> contenidoList)		 	{this.contenidoList = contenidoList;}
	
	public List<SelectItem> getModuloList() 								{return moduloList;}
	public void setModuloList(List<SelectItem> moduloList) 					{this.moduloList = moduloList;}

	public List<SelectItem> getCapacidadList() 								{return capacidadList;}
	public void setCapacidadList(List<SelectItem> capacidadList) 			{this.capacidadList = capacidadList;}

	public Long getTipo() 													{return tipo;}
	public void setTipo(Long tipo) 											{this.tipo = tipo;}
} 
