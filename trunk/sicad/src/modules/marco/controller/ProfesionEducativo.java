package modules.marco.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.MarcoService;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class ProfesionEducativo extends GenericController   
{	
	private MarcoService	myService;
	private List<ReferenteEducativo> esquemaList;
	private List<ReferenteEducativo> contenidoList;
	private List<ReferenteEducativo> transversalList;
	private List<ReferenteEducativo> contenidoTList;
	
	private List<SelectItem> moduloProfesionalList;
	private List<SelectItem> capacidadProfesionalList;
	
	private List<SelectItem> moduloTransversalList;
	private List<SelectItem> capacidadTransversalList;
	
	private Long tipo=0L,profesion;
	private String nombreProfesion, pagina;
	
	public void init(Long id, String nombre, String returnPage) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Marco Educativo";
		moduleName="Profesion - Referente Educativo";
		userName=usr.getUsuario();
		profesion=id;
		nombreProfesion=nombre;
		pagina=returnPage;
		page_new="educ_new";
		page_update="educ_update";
		page_main="educ_list";
		defaultList();		
		forward(page_main);
	}
	
	public void returnPage() throws Exception
	{forward(pagina);}
	
	@Override
	public void defaultList() throws Exception
	{
		List<ReferenteEducativo> educativoList=myService.listarReferenteEducativo(profesion, 0, 1L);
		esquemaList=new ArrayList<ReferenteEducativo>();
		contenidoList=new ArrayList<ReferenteEducativo>();
		transversalList=new ArrayList<ReferenteEducativo>();
		contenidoTList=new ArrayList<ReferenteEducativo>();
		
		for(int i=0; i<educativoList.size(); i++)
		{
			if(educativoList.get(i).getTipo().longValue()==1L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{esquemaList.add(educativoList.get(i));	}
			if(educativoList.get(i).getTipo().longValue()==2L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{contenidoList.add(educativoList.get(i));}
			if(educativoList.get(i).getTipo().longValue()==3L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{transversalList.add(educativoList.get(i));}
			if(educativoList.get(i).getTipo().longValue()==4L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{contenidoTList.add(educativoList.get(i));}
		}
		educativoList=null;
		fillModuloTransversal();
		fillModuloProfesional();
	}
	
	public void setting() throws Exception
	{
		ReferenteEducativo ref=(ReferenteEducativo)getBean();
		ref.setNivelA(0L);
		ref.setNivelB(0L);
		ref.setNivelC(0L);
		ref.setProfesion(profesion);
		ref.setEstado(Constante.ROW_STATUS_ENABLED);
		if(tipo.longValue()==2L)	{ref.setTipo(1L);}
		if(tipo.longValue()==3L)	{ref.setTipo(1L);}
		if(tipo.longValue()==4L)	{ref.setTipo(1L);}
		if(tipo.longValue()==5L)	{ref.setTipo(2L);}
		if(tipo.longValue()==6L)	{ref.setTipo(3L);}
		if(tipo.longValue()==7L)	{ref.setTipo(3L);}
		if(tipo.longValue()==8L)	{ref.setTipo(3L);}
		if(tipo.longValue()==9L)	{ref.setTipo(4L);}
		setBean(ref);
		ref=null;
	}
	
	@Override
	public void afterNew() throws Exception
	{
		tipo=-1L;
		setEnabled(new ReferenteEducativo());
	}
	
	
	public void fillModuloProfesional() throws Exception
	{
		List<ReferenteEducativo> list=new ArrayList<ReferenteEducativo>();
		for(int i=0; i<esquemaList.size(); i++)
		{
			if(esquemaList.get(i).getNivelB().longValue()==0L && esquemaList.get(i).getNivelC().longValue()==0L)
			{list.add(esquemaList.get(i));}
		}
		moduloProfesionalList=getListSelectItem(list, "nivelA", "descripcion", true);
		list=null;
	}
	public void fillCapacidadProfesional() throws Exception
	{
		List<ReferenteEducativo> list=new ArrayList<ReferenteEducativo>();
		for(int i=0; i<esquemaList.size(); i++)
		{
			if(esquemaList.get(i).getNivelA().longValue()==((ReferenteEducativo)getBean()).getNivelA() && esquemaList.get(i).getNivelB().longValue()!=0L && esquemaList.get(i).getNivelC().longValue()==0L)
			{list.add(esquemaList.get(i));}
		}
		capacidadProfesionalList=getListSelectItem(list, "nivelB", "descripcion", true);
		list=null;
	}
	
	public void fillModuloTransversal() throws Exception
	{
		List<ReferenteEducativo> list=new ArrayList<ReferenteEducativo>();
		for(int i=0; i<transversalList.size(); i++)
		{
			if(transversalList.get(i).getNivelB().longValue()==0L && transversalList.get(i).getNivelC().longValue()==0L)
			{list.add(transversalList.get(i));}
		}
		moduloTransversalList=getListSelectItem(list, "nivelA", "descripcion", true);
		list=null;
	}
	public void fillCapacidadTransversal() throws Exception
	{
		List<ReferenteEducativo> list=new ArrayList<ReferenteEducativo>();
		for(int i=0; i<transversalList.size(); i++)
		{
			if(transversalList.get(i).getNivelA().longValue()==((ReferenteEducativo)getBean()).getNivelA() && transversalList.get(i).getNivelB().longValue()!=0L && transversalList.get(i).getNivelC().longValue()==0L)
			{list.add(transversalList.get(i));}
		}
		capacidadTransversalList=getListSelectItem(list, "nivelB", "descripcion", true);
		list=null;
	}
	
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		ReferenteEducativo object = (ReferenteEducativo)getBean();
		if(tipo.longValue()>2L && !validateSelect(object.getNivelA()))
		{
			setMessageError("Debe seleccionar una Modulo.");			
			success = false;
		}
		else if(tipo.longValue()==4L && !validateSelect(object.getNivelB()))
		{
			setMessageError("Debe seleccionar una Capacidad Terminal.");			
			success = false;
		}
		else if(!validateEmpty(object.getTitulo()) && tipo.longValue()==1L)
		{
			setMessageError("Debe ingresar el Título.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la Descripcion.");			
			success = false;
		}
		object=null;
		return success;
	}
	
	public MarcoService getMyService() 													{return myService;}
	public void setMyService(MarcoService myService) 									{this.myService = myService;}

	public List<ReferenteEducativo> getEsquemaList() 									{return esquemaList;}
	public void setEsquemaList(List<ReferenteEducativo> esquemaList) 					{this.esquemaList = esquemaList;}

	public List<ReferenteEducativo> getContenidoList() 									{return contenidoList;}
	public void setContenidoList(List<ReferenteEducativo> contenidoList)		 		{this.contenidoList = contenidoList;}
	
	public List<ReferenteEducativo> getContenidoTList() 								{return contenidoTList;}
	public void setContenidoTList(List<ReferenteEducativo> contenidoTList) 				{this.contenidoTList = contenidoTList;}

	public String getNombreProfesion() 													{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 								{this.nombreProfesion = nombreProfesion;}

	public List<ReferenteEducativo> getTransversalList() 								{return transversalList;}
	public void setTransversalList(List<ReferenteEducativo> transversalList)			{this.transversalList = transversalList;}

	public List<SelectItem> getModuloProfesionalList() 									{return moduloProfesionalList;}
	public void setModuloProfesionalList(List<SelectItem> moduloProfesionalList) 		{this.moduloProfesionalList = moduloProfesionalList;}

	public List<SelectItem> getCapacidadProfesionalList() 								{return capacidadProfesionalList;}
	public void setCapacidadProfesionalList(List<SelectItem> capacidadProfesionalList) 	{this.capacidadProfesionalList = capacidadProfesionalList;}

	public List<SelectItem> getModuloTransversalList() 									{return moduloTransversalList;}
	public void setModuloTransversalList(List<SelectItem> moduloTransversalList) 		{this.moduloTransversalList = moduloTransversalList;}
	
	public List<SelectItem> getCapacidadTransversalList() 								{return capacidadTransversalList;}
	public void setCapacidadTransversalList(List<SelectItem> capacidadTransversalList) 	{this.capacidadTransversalList = capacidadTransversalList;}
	
	public Long getTipo() 																{return tipo;}
	public void setTipo(Long tipo) 														{this.tipo = tipo;}

} 
