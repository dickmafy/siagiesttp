package modules.marco.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.MarcoService;
import modules.marco.domain.ReferenteProductivo;
import modules.seguridad.domain.Usuario;

public class ProfesionProductivo extends GenericController   
{	
	private MarcoService	myService;
	private List<ReferenteProductivo> esquemaList;
	private List<ReferenteProductivo> capacidadList;
	private List<ReferenteProductivo> rubroList;
	
	private List<SelectItem> unidadList;
	private List<SelectItem> realizacionList;
	
	private Long tipo=0L,profesion;
	private String nombreProfesion, pagina, medios,procesos,resultados,informacion;
	
	public void init(Long id, String nombre, String returnPage) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Marco Educativo";
		moduleName="Profesion - Referente Productivo";
		userName=usr.getUsuario();
		profesion=id;
		nombreProfesion=nombre;
		pagina=returnPage;
		page_new="prdt_new";
		page_update="prdt_update";
		page_main="prdt_list";
		defaultList();		
		forward(page_main);
	}
	
	public void returnPage() throws Exception
	{forward(pagina);}
	
	@Override
	public void defaultList() throws Exception
	{
		List<ReferenteProductivo> productivoList=myService.listarReferenteProductivo(profesion, 0, 1L);
		capacidadList=new ArrayList<ReferenteProductivo>();
		esquemaList=new ArrayList<ReferenteProductivo>();
		rubroList=new ArrayList<ReferenteProductivo>();
		for(int i=0; i<productivoList.size(); i++)
		{
			if(productivoList.get(i).getTipo().longValue()==2L && productivoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{capacidadList.add(productivoList.get(i));}
			if(productivoList.get(i).getTipo().longValue()==4L && productivoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{esquemaList.add(productivoList.get(i));}
			if(productivoList.get(i).getTipo().longValue()==5L && productivoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{rubroList.add(productivoList.get(i));}
		}
		productivoList=null;
		fillUnidad();
	}
	
	public void setting() throws Exception
	{
		ReferenteProductivo ref=(ReferenteProductivo)getBean();
		ref.setNivelA(0L);
		ref.setNivelB(0L);
		ref.setNivelC(0L);
		ref.setProfesion(profesion);
		ref.setEstado(Constante.ROW_STATUS_ENABLED);
		if(tipo.longValue()==1L)	{ref.setTipo(2L);}
		if(tipo.longValue()==2L)	{ref.setTipo(4L);}
		if(tipo.longValue()==3L)	{ref.setTipo(4L);}
		if(tipo.longValue()==4L)	{ref.setTipo(4L);}
		setBean(ref);
		ref=null;
	}
	
	@Override
	public void afterNew() throws Exception
	{
		tipo=0L;
		setEnabled(new ReferenteProductivo());
		medios="";
		procesos="";
		resultados="";
		informacion="";
	}
	
	@Override
	public void afterSave() throws Exception
	{
		if(tipo.longValue()==2L)
		{
			ReferenteProductivo ref=new ReferenteProductivo();
			Long nivel=Long.valueOf(((ReferenteProductivo)getBeanSave()).getId().toString().substring(4,6));
			
			ref.setNivelC(0L);
			ref.setProfesion(profesion);
			ref.setEstado(Constante.ROW_STATUS_ENABLED);
			
			ref.setNivelA(nivel);
			ref.setNivelB(0L);
			ref.setDescripcion(medios);
			ref.setTipo(5L);
			getService().save(ref);
			
			ref.setNivelA(nivel);
			ref.setNivelB(0L);
			ref.setDescripcion(resultados);
			ref.setTipo(5L);
			getService().save(ref);
			
			ref.setNivelA(nivel);
			ref.setNivelB(0L);
			ref.setDescripcion(procesos);
			ref.setTipo(5L);
			getService().save(ref);
			
			ref.setNivelA(nivel);
			ref.setNivelB(0L);
			ref.setDescripcion(informacion);
			ref.setTipo(5L);
			getService().save(ref);
			
			ref=null;
			nivel=null;
		}
	}
	
	public void fillUnidad() throws Exception
	{
		List<ReferenteProductivo> list=new ArrayList<ReferenteProductivo>();
		for(int i=0; i<esquemaList.size(); i++)
		{
			if(esquemaList.get(i).getNivelB().longValue()==0L && esquemaList.get(i).getNivelC().longValue()==0L)
			{list.add(esquemaList.get(i));}
		}
		unidadList=getListSelectItem(list, "nivelA", "descripcion", true);
		list=null;
	}
	public void fillRealizacion() throws Exception
	{
		List<ReferenteProductivo> list=new ArrayList<ReferenteProductivo>();
		for(int i=0; i<esquemaList.size(); i++)
		{
			if(esquemaList.get(i).getNivelA().longValue()==((ReferenteProductivo)getBean()).getNivelA() && esquemaList.get(i).getNivelB().longValue()!=0L && esquemaList.get(i).getNivelC().longValue()==0L)
			{list.add(esquemaList.get(i));}
		}
		realizacionList=getListSelectItem(list, "nivelB", "descripcion", true);
		list=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		ReferenteProductivo object = (ReferenteProductivo)getBean();
		if(tipo.longValue()>2L && !validateSelect(object.getNivelA()))
		{
			setMessageError("Debe seleccionar una Unidad de Competencia.");			
			success = false;
		}
		else if(tipo.longValue()==4L && !validateSelect(object.getNivelB()))
		{
			setMessageError("Debe seleccionar una Realización.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la Descripción.");			
			success = false;
		}
		else if(!validateEmpty(medios) && tipo.longValue()==2L && object.getNivelA().longValue()==0L)
		{
			setMessageError("Debe ingresar el Rubro: Medios y materiales de producción para la Unidad de Competencia.");			
			success = false;
		}
		else if(!validateEmpty(resultados) && tipo.longValue()==2L && object.getNivelA().longValue()==0L)
		{
			setMessageError("Debe ingresar el Rubro: Principales resultados del trabajo para la Unidad de Competencia.");			
			success = false;
		}
		else if(!validateEmpty(procesos) && tipo.longValue()==2L && object.getNivelA().longValue()==0L)
		{
			setMessageError("Debe ingresar el Rubro: Procesos, metodos y procedimientos para la Unidad de Competencia.");			
			success = false;
		}
		else if(!validateEmpty(informacion) && tipo.longValue()==2L && object.getNivelA().longValue()==0L)
		{
			setMessageError("Debe ingresar el Rubro: Información para la Unidad de Competencia.");			
			success = false;
		}
		object=null;
		return success;
	}
	
	public MarcoService getMyService() 										{return myService;}
	public void setMyService(MarcoService myService) 						{this.myService = myService;}

	public List<ReferenteProductivo> getEsquemaList() 						{return esquemaList;}
	public void setEsquemaList(List<ReferenteProductivo> esquemaList) 		{this.esquemaList = esquemaList;}

	public List<ReferenteProductivo> getCapacidadList() 					{return capacidadList;}
	public void setCapacidadList(List<ReferenteProductivo> capacidadList) 	{this.capacidadList = capacidadList;}

	public List<ReferenteProductivo> getRubroList() 						{return rubroList;}
	public void setRubroList(List<ReferenteProductivo> rubroList) 			{this.rubroList = rubroList;}

	public String getNombreProfesion() 										{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 					{this.nombreProfesion = nombreProfesion;}

	public List<SelectItem> getUnidadList() 								{return unidadList;}
	public void setUnidadList(List<SelectItem> unidadList) 					{this.unidadList = unidadList;}
	
	public List<SelectItem> getRealizacionList() 							{return realizacionList;}
	public void setRealizacionList(List<SelectItem> realizacionList) 		{this.realizacionList = realizacionList;}
	
	public Long getTipo() 													{return tipo;}
	public void setTipo(Long tipo) 											{this.tipo = tipo;}

	public String getMedios() 												{return medios;}
	public void setMedios(String medios) 									{this.medios = medios;}
	public String getProcesos() 											{return procesos;}
	public void setProcesos(String procesos) 								{this.procesos = procesos;}
	public String getResultados() 											{return resultados;}
	public void setResultados(String resultados) 							{this.resultados = resultados;}
	public String getInformacion() 											{return informacion;}
	public void setInformacion(String informacion) 							{this.informacion = informacion;}
	
	
} 
