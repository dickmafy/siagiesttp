package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.IntranetService;
import dataware.service.MarcoService;
import modules.administracion.domain.Institucion;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class IntranetAlumnoNotas extends GenericController   
{	
	private IntranetService	myService;
	private List<ReferenteEducativo> criteriosList;
	
	private List<SelectItem> moduloProfesionalList;
	private List<SelectItem> capacidadProfesionalList;
	
	private List<SelectItem> moduloTransversalList;
	private List<SelectItem> capacidadTransversalList;
	
	private Long tipo=0L,seccion,modulo,profesion;
	private String nombreUnidad;
	
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="S�labo";
		userName=usr.getUsuario();
		seccion=1L;
		modulo=1L;
		profesion=101L;
		nombreUnidad= "Prueba";
		page_new="IntranetAlumnoNotas_new";
		page_update="IntranetAlumnoNotas_update";
		page_main="IntranetAlumnoNotas_list";
		//defaultList();		
		forward(page_main);
		optionCriterios();
	}
	
	public void optionCriterios() throws Exception
	{
		List<ReferenteEducativo> educativoList=myService.listarReferenteEducativo(profesion, 0, 1L);
		criteriosList=new ArrayList<ReferenteEducativo>();
		
		for(int i=0; i<educativoList.size(); i++)
		{
			if(educativoList.get(i).getTipo().longValue()==1L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{criteriosList.add(educativoList.get(i));	}
		}
		educativoList=null;
		
		filtrarModulo(criteriosList,modulo);
		
		forward("silabo_crit");
	}
	
	public void filtrarModulo(List<ReferenteEducativo> educativoList, Long modulo) throws Exception
	{
		ArrayList<ReferenteEducativo> filtro=new ArrayList<ReferenteEducativo>();	
		for (ReferenteEducativo item : educativoList) 
		{
			if(item.getNivelA() == modulo)
			{filtro.add(item);}						
		}
		
		criteriosList=filtro;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		
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
			setMessageError("Debe ingresar el T�tulo.");			
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
	
	public IntranetService getMyService() 													{return myService;}
	public void setMyService(IntranetService myService) 									{this.myService = myService;}

	public List<ReferenteEducativo> getCriteriosList() 										{return criteriosList;}
	public void setCriteriosList(List<ReferenteEducativo> criteriosList) 					{this.criteriosList = criteriosList;}

	public String getNombreUnidad() 													{return nombreUnidad;}
	public void setNombreUnidad(String nombreUnidad) 									{this.nombreUnidad = nombreUnidad;}

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
