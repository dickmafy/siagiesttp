package modules.cetpro.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.SeguridadService;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Oferta;
import modules.horario.controller.HorarioMetaDetalle;
import modules.institucion.controller.InstitucionUnidad;
import modules.marco.controller.ProfesionEducativo;
import modules.marco.controller.ProfesionItinerario;
import modules.marco.domain.Familia;
import modules.marco.domain.Profesion;
import modules.seguridad.domain.Usuario;

public class CetproOfertaEducativa extends GenericController   
{
	private StreamedContent file;
	private	List<Oferta> profesiones;
	private List<SelectItem> familiaList;
	private SeguridadService	myService;
	private Oferta oferta;
	private Long institucion;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucion";
		moduleName="Oferta ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		page_new="oferta_profesion_new";
		page_main="oferta_profesion_list";
		page_update="oferta_profesion_upd";
		profesiones=myService.listarOfertaCetpro(usr.getInstitucion());
		forward(page_main);	
	}

	@Override
	public void afterNew() throws Exception
	{
		oferta = new Oferta();
		Profesion bean=new Profesion();
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		bean.setFormacion(4L);
		bean.setInstitucion(institucion);
		setBean(bean);
		familiaList=getListSelectItem(myService.listByObjectEnabled(new Familia()), "id", "nombre", true);
		bean=null;
	}
	
	@Override
	public void afterSave() throws Exception {
		
		Profesion prof = (Profesion) getBean();
		prof = (Profesion)myService.findByObject(prof);
		
		oferta.setInstitucion(institucion);
		oferta.setProfesion(prof.getId());		
		myService.save(oferta);
		
		profesiones=myService.listarOfertaCetpro(institucion);
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		familiaList=getListSelectItem(myService.listByObjectEnabled(new Familia()), "id", "nombre", true);		
	}
	
	public void optionItinerario() throws Exception
	{
		Oferta object = (Oferta)getBeanSelected();
		InstitucionUnidad go = (InstitucionUnidad)getSpringBean("institucionUnidad");
		go.init(object.getProfesion(), object.getNombreProfesion());
	}
	
	public void optionGoEducativo() throws Exception
	{
		ProfesionEducativo go = (ProfesionEducativo)getSpringBean("profesionEducativo");
		go.init(((Oferta)getBeanSelected()).getProfesion().longValue(), ((Oferta)getBeanSelected()).getNombreProfesion(), "oferta_profesion_list");
	}
	
	public void optionGoItinerario() throws Exception
	{
		ProfesionItinerario go = (ProfesionItinerario)getSpringBean("profesionItinerario");
		go.init(((Oferta)getBeanSelected()).getProfesion().longValue(), ((Oferta)getBeanSelected()).getNombreProfesion(), "oferta_profesion_list");
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Profesion object = (Profesion)getBean();
		if(!validateSelect(object.getFamilia()))
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
		else if(!validateEmpty(oferta.getVigenciaFin()))
		{
			setMessageError("Debe ingresar la fecha final de la vigencia.");			
			success = false;
		}
		else if(!validateEmpty(oferta.getVigenciaInicio()))
		{
			setMessageError("Debe ingresar la fecha de inicio de la vigencia.");			
			success = false;
		}
		else if(oferta.getVigenciaFin().before(oferta.getVigenciaInicio()))
		{
			setMessageError("La fecha final no puede ser menor a la fecha de inicio de la vigencia.");			
			success = false;
		}
		object=null;
		return success;
	}

	public List<SelectItem> getFamiliaList() 								{return familiaList;}
	public void setFamiliaList(List<SelectItem> familiaList)	 			{this.familiaList = familiaList;}

	public Oferta getOferta() {return oferta;}
	public void setOferta(Oferta oferta) {this.oferta = oferta;}

	public List<Oferta> getProfesiones() 					{return profesiones;}
	public void setProfesiones(List<Oferta> profesiones) 	{this.profesiones = profesiones;}
	
	public SeguridadService getMyService() 						{return myService;}	
	public void setMyService(SeguridadService myService)		{this.myService = myService;}
} 
