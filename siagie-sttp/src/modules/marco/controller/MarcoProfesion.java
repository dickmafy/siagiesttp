package modules.marco.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.MarcoService;
import modules.marco.domain.Familia;
import modules.marco.domain.Profesion;
import modules.seguridad.domain.Usuario;

public class MarcoProfesion extends GenericController   
{	
	private MarcoService	myService;
	private List<SelectItem> familiaList;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Marco Educativo";
		moduleName="Profesiones";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="prof_new";
		page_main="prof_list";
		page_update="prof_update";
		forward(page_main);
	}
		
	@Override
	public void afterNew() throws Exception
	{
		Profesion bean=new Profesion();
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		bean.setFormacion(1L);
		setBean(bean);
		familiaList=getListSelectItem(myService.listByObjectEnabled(new Familia()), "id", "nombre", true);
		bean=null;
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		familiaList=getListSelectItem(myService.listByObjectEnabled(new Familia()), "id", "nombre", true);		
	}
	
	public void optionGoGeneral() throws Exception
	{forward("prof_general");}
	
	public void optionGoProductivo() throws Exception
	{
		ProfesionProductivo go = (ProfesionProductivo)getSpringBean("profesionProductivo");
		go.init(((Profesion)getBeanSelected()).getId().longValue(), ((Profesion)getBeanSelected()).getNombre(), "prof_list");
	}
	
	public void optionGoEducativo() throws Exception
	{
		ProfesionEducativo go = (ProfesionEducativo)getSpringBean("profesionEducativo");
		go.init(((Profesion)getBeanSelected()).getId().longValue(), ((Profesion)getBeanSelected()).getNombre(), "prof_list");
	}
	
	public void optionGoItinerario() throws Exception
	{
		ProfesionItinerario go = (ProfesionItinerario)getSpringBean("profesionItinerario");
		go.init(((Profesion)getBeanSelected()).getId().longValue(), ((Profesion)getBeanSelected()).getNombre(), "prof_list");
	}
	
	@Override
	public void defaultList() throws Exception
	{
		List<Profesion> profesion=new ArrayList<Profesion>();
		List<Profesion> profesiones=myService.listarProfesiones();
		for(int i=0; i<profesiones.size(); i++)
		{
			if(profesiones.get(i).getFormacion().longValue()==1L)
			{profesion.add(profesiones.get(i));}
		}
		setBeanList(profesion);
		profesion=null;
		profesiones=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Profesion object = (Profesion)getBean();
		if(!validateSelect(object.getFormacion()))
		{
			setMessageError("Debe seleccionar una formación.");			
			success = false;
		}
		else if(!validateSelect(object.getFamilia()))
		{
			setMessageError("Debe seleccionar la familia profesional a la que pertenece.");			
			success = false;
		}
		else if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre de la Profesion.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción.");			
			success = false;
		}
		else if(!validateEmpty(object.getAptitudes()))
		{
			setMessageError("Debe ingresar las aptitudes.");			
			success = false;
		}
		else if(!validateEmpty(object.getActitudes()))
		{
			setMessageError("Debe ingresar las actitudes.");			
			success = false;
		}
		else if(!validateEmpty(object.getAmbiente()))
		{
			setMessageError("Debe ingresar el ambiente de trabajo.");			
			success = false;
		}
		object=null;
		return success;
	}

	public List<SelectItem> getFamiliaList() 								{return familiaList;}
	public void setFamiliaList(List<SelectItem> familiaList)	 			{this.familiaList = familiaList;}

	public MarcoService getMyService() 										{return myService;}
	public void setMyService(MarcoService myService) 						{this.myService = myService;}

	
} 
