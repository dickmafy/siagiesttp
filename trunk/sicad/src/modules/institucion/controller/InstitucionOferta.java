package modules.institucion.controller; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.SeguridadService;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Oferta;
import modules.horario.controller.HorarioMetaDetalle;
import modules.seguridad.domain.Usuario;

public class InstitucionOferta extends GenericController   
{
	private StreamedContent file;
	private	List<Oferta> profesiones;
	private SeguridadService	myService;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucion";
		moduleName="Oferta ";
		userName=usr.getUsuario();		
		page_new="itc_oferta";
		page_main="itc_oferta";
		page_update="itc_oferta";
		profesiones=myService.listarOferta(usr.getInstitucion(), DateHelper.getDate(),1L);
		forward(page_main);	
	}

	public StreamedContent getFile() 							
	{
		Oferta object = (Oferta)getBeanSelected();
		try 
		{
	        InputStream is = new BufferedInputStream(new FileInputStream(getServletContext().getRealPath("/recursos/documentos/resolucion/"+object.getResolucion()+".pdf")));
	        return new DefaultStreamedContent(is, "application/pdf", object.getId()+".pdf");
	    } 
		catch (FileNotFoundException e) 
	    {return null;}
	}
	
	public void optionItinerario() throws Exception
	{
		Oferta object = (Oferta)getBeanSelected();
		InstitucionUnidad go = (InstitucionUnidad)getSpringBean("institucionUnidad");
		go.init(object.getProfesion(), object.getNombreProfesion());
	}
	
	public List<Oferta> getProfesiones() 					{return profesiones;}
	public void setProfesiones(List<Oferta> profesiones) 	{this.profesiones = profesiones;}
	
	public SeguridadService getMyService() 						{return myService;}	
	public void setMyService(SeguridadService myService)		{this.myService = myService;}
} 
