package modules.cetpro.controller;



import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.model.StreamedContent;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.SeguridadService;
import modules.administracion.controller.AdminMetaInstitucionalDetalle;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Personal;
import modules.admision.domain.Requisitos;
import modules.cetpro.domain.OfertaEducativa;
import modules.horario.domain.AsistenciaDocente;
import modules.horario.domain.Seccion;
import modules.mantenimiento.domain.Puesto;
import modules.mantenimiento.domain.Resolucion;
import modules.marco.domain.Profesion;
import modules.seguridad.domain.Usuario;

public class CetproOferta extends GenericController   
{
	private List<SelectItem>    resolucionList;
	private List<SelectItem> moduloList;
	private List<SelectItem> personalList;
	private StreamedContent file;
	private	List<OfertaEducativa> profesiones;
	private SeguridadService	myService;
	private Long cetpro;
	private Long annio;
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Oferta Educativa";
		moduleName="Oferta Educativa";
		userName=usr.getUsuario();
		cetpro=usr.getInstitucion();
		defaultList();
		
		Profesion obj=new Profesion();
		obj.setFormacion(2L);
		moduloList=getListSelectItem(obj, "id", "nombre",true);
		
		Personal objper=new Personal();
		objper.setInstitucion(cetpro);
		setEnabled(objper);
		personalList=getListSelectItem(objper,"id","nombres",true);
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		obj=null;
		objper=null;
		page_new="ceo_new";
		page_main="ceo_list";
		page_update="ceo_oferta_update";
		//profesiones=myService.listarOferta(usr.getInstitucion(), DateHelper.getDate(),1L);
		
		forward(page_main);	
	}

	@Override
	public void defaultList() throws Exception
	{
		OfertaEducativa oferta = new OfertaEducativa();
		oferta.setPk_institucion(cetpro);
		oferta.setEstado(Constante.ROW_STATUS_ENABLED);
		setBeanList(getService().listByObject(oferta));		
		oferta  = null;
	}
	
	
	@Override
	public void beforeSave() throws Exception {
		OfertaEducativa aux=(OfertaEducativa)getBean();
		aux.setPk_institucion(cetpro);
		aux=null;
	}
	
	@Override
	public void beforeUpdate() throws Exception {
		OfertaEducativa aux=(OfertaEducativa)getBean();
		aux.setPk_oferta(aux.getPk_oferta());
		aux.setPk_institucion(cetpro);
		setBean(aux);
		aux=null;
	}
	
	@Override
	public void afterNew() throws Exception {
		setEnabled(new OfertaEducativa());
	}
	
	@Override
	public void afterLoad() throws Exception {
		OfertaEducativa usr=(OfertaEducativa)getBeanSelected();	
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		OfertaEducativa object = (OfertaEducativa)getBean();
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el tipo de Oferta.");			
			success = false;
		}
		else if(!validateSelect(object.getModulo()))
		{
			setMessageError("Debe seleccionar el Módulo, al que pertenece");
			success = false;
		}
		else if(!validateEmpty(object.getTitulo()))
		{
			setMessageError("Debe ingresar el Titulo de la Oferta.");			
			success = false;
		}
		else if(!validateSelect(object.getResponsable()))
		{
			setMessageError("Debe seleccionar un Responsable.");			
			success = false;
		}
		else if(!validateSelect(object.getDuracion()))
		{
			setMessageError("Debe ingresar la duración.");			
			success = false;
		}
		else if(!validateEmpty(object.getFecha_inicio()))
		{
			setMessageError("Debe seleccionar la fecha de inicio.");			
			success = false;
		}
		else if(!validateEmpty(object.getFecha_termino()))
		{
			setMessageError("Debe seleccionar la fecha de finalización.");			
			success = false;
		}
		else if(!validateSelect(object.getParticipantes()))
		{
			setMessageError("Debe ingresar la Cantdad de Participantes.");			
			success = false;
		}  
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar una Descripción.");			
			success = false;
		}
		else if(!validateEmpty(object.getFundamentacion()))
		{
			setMessageError("Debe ingresar una Fundamentación.");			
			success = false;
		}
		else if(!validateEmpty(object.getMarco_legal()))
		{
			setMessageError("Debe ingresar el Marco Legal.");			
			success = false;
		}
		
		
		object=null;
		return success;
	}   
	
	public void optionGoItinerario() throws Exception
	{
		CetproItinerario go = (CetproItinerario)getSpringBean("cetproItinerario");
		Long oferta = ((OfertaEducativa) getBeanSelected()).getPk_oferta();
		go.initBase(oferta);
	}
	
//	public StreamedContent getFile() 							
//	{
//		Oferta object = (Oferta)getBeanSelected();
//		try 
//		{
//	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getResolucion()+".pdf")));
//	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
//	    } 
//		catch (FileNotFoundException e) 
//	    {return null;}
//	}
	
//	public void optionItinerario() throws Exception
//	{
//		Oferta object = (Oferta)getBeanSelected();
//		InstitucionUnidad go = (InstitucionUnidad)getSpringBean("institucionUnidad");
//		go.init(object.getProfesion(), object.getNombreProfesion());
//	}
	

	
	public SeguridadService getMyService() 							{return myService;}	
	public void setMyService(SeguridadService myService)			{this.myService = myService;}

	public List<OfertaEducativa> getProfesiones() 					{return profesiones;}
	public void setProfesiones(List<OfertaEducativa> profesiones)	{this.profesiones = profesiones;}

	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getCetpro() 										{return cetpro;}
	public void setCetpro(Long cetpro) 								{this.cetpro = cetpro;}

	public List<SelectItem> getModuloList() 						{return moduloList;}
	public void setModuloList(List<SelectItem> moduloList) 			{this.moduloList = moduloList;}

	public List<SelectItem> getPersonalList() 						{return personalList;}
	public void setPersonalList(List<SelectItem> personalList) 		{this.personalList = personalList;}
	
	public List<SelectItem> getResolucionList() 					{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 	{this.resolucionList = resolucionList;}

	
	
} 
