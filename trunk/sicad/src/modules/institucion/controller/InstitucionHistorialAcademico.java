package modules.institucion.controller; 
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.SeguridadService;
import modules.administracion.domain.HistorialAcademico;
import modules.mantenimiento.domain.Especialidad;
import modules.mantenimiento.domain.Profession;
import modules.seguridad.domain.Usuario;

public class InstitucionHistorialAcademico extends GenericController   
{
	private List<SelectItem> 	especialidadList;
	private List<SelectItem>    profesionList;
	
	private	String	nombrePersonal;
	private Long personal;
	
	private SeguridadService	myService;
	
	public void init(Long id, String nom) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Personal - Historial Académico ";
		userName=usr.getUsuario();
		nombrePersonal=nom;
		personal=id;
		defaultList();
		
		especialidadList=getListSelectItem(myService.listByObjectEnabled(new Especialidad()), "id", "nombre",true);
		profesionList=getListSelectItem(myService.listByObjectEnabled(new Profession()), "id", "nombre",true);
		
		page_new="itc_acd_new";
		page_main="itc_acd_list";
		page_update="itc_acd_update";
		forward(page_main);
	}
	
	@Override
	public void afterNew() throws Exception
	{
		HistorialAcademico bean=new HistorialAcademico();
		bean.setPersonal(personal);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		bean=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		HistorialAcademico bean=new HistorialAcademico();
		bean.setPersonal(personal);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		bean=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		HistorialAcademico object = (HistorialAcademico)getBean();
		if(!validateEmpty(object.getCentro()))
		{
			setMessageError("Debe ingresar el Centro de Estudios.");			
			success = false;
		}
		else if(!validateSelect(object.getProfesion()))
		{
			setMessageError("Debe seleccionar la Profesión.");			
			success = false;
		}
		else if(!validateSelect(object.getEspecialidad()))
		{
			setMessageError("Debe seleccionar la Especialidad.");			
			success = false;
		}
		else if(!validateEmpty(object.getTitulo()))
		{
			setMessageError("Debe ingresar el Título Académico.");			
			success = false;
		}
		else if(!validateZero(object.getDuracion()))
		{
			setMessageError("Debe ingresar la Duración en semestres.");			
			success = false;
		}
		else if(!validateSelect(object.getSituacion()))
		{
			setMessageError("Debe seleccionar la Situación del Estudio Académico.");			
			success = false;
		}
		object=null;
		return success;
	}

	public List<SelectItem> getEspecialidadList() 							{return especialidadList;}
	public void setEspecialidadList(List<SelectItem> especialidadList) 		{this.especialidadList = especialidadList;}
	
	public List<SelectItem> getProfesionList() 								{return profesionList;}
	public void setProfesionList(List<SelectItem> profesionList) 			{this.profesionList = profesionList;}
	
	public String getNombrePersonal() 										{return nombrePersonal;}
	public void setNombrePersonal(String nombrePersonal) 					{this.nombrePersonal = nombrePersonal;}

	public SeguridadService getMyService() 									{return myService;}
	public void setMyService(SeguridadService myService) 					{this.myService = myService;}	
} 
