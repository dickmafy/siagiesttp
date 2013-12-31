package modules.institucion.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.SeguridadService;
import modules.administracion.domain.HistorialLaboral;
import modules.administracion.domain.Institucion;
import modules.mantenimiento.domain.Puesto;
import modules.mantenimiento.domain.Resolucion;
import modules.mantenimiento.domain.Supervision;
import modules.seguridad.domain.Usuario;

public class InstitucionHistorialLaboral extends GenericController   
{
	private List<SelectItem>    puestoList;
	private List<SelectItem>    institucionList;
	private List<SelectItem>    resolucionList;
	
	private String 	nombrePersonal;
	private Long personal;
	private SeguridadService	myService;
	
	public void init(Long id, String nom) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Local";
		moduleName="Personal - Historial Laboral ";
		userName=usr.getUsuario();
		nombrePersonal=nom;
		personal=id;
		defaultList();
		
		institucionList=getListSelectItem(new ArrayList<Institucion>(), "id", "nombre",true);
		puestoList=getListSelectItem(myService.listByObjectEnabled(new Puesto()), "id", "nombre",true);
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id", "nombre",true);
		
		page_new="itc_lab_new";
		page_main="itc_lab_list";
		page_update="itc_lab_update";
		forward(page_main);
	}
	
	@Override
	public void afterNew() throws Exception
	{
		HistorialLaboral bean=new HistorialLaboral();
		bean.setPersonal(personal);
		bean.setTipo(-1L);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		bean=null;
	}
	
	@Override
	public void afterLoad() throws Exception
	{selectTipo();}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(myService.listarHistorialLaboral(personal));}
	
	public void selectTipo() throws Exception
	{
		HistorialLaboral bean=(HistorialLaboral)getBean();
		if(bean.getTipo().longValue()==1L)	
		{
			Institucion obj=new Institucion();
			obj.setFormacion(1L);
			institucionList=getListSelectItem(myService.listByObjectEnabled(obj),"id","nombre",true);
			obj=null;
		}
		if(bean.getTipo().longValue()==2L)	
		{
			List<Institucion> cetpros=new ArrayList<Institucion>();
			List<Institucion> instituciones=myService.listByObjectEnabled(new Institucion());
			for(int i=0; i<instituciones.size(); i++)
			{
				if(instituciones.get(i).getFormacion().longValue()!=1L)
				{cetpros.add(instituciones.get(i));}				
			}
			institucionList=getListSelectItem(cetpros,"id","nombre",true);
			instituciones=null;
			cetpros=null;
		}
		if(bean.getTipo().longValue()==3L)	
		{
			Supervision sup=new Supervision();
			sup.setSubnivel(0L);
			institucionList=getListSelectItem(myService.listByObjectEnabled(sup),"id","nombre",true);
			sup=null;
		}
		if(bean.getTipo().longValue()==4L)	
		{
			List<Supervision> ugels=new ArrayList<Supervision>();
			List<Supervision> instituciones=myService.listByObjectEnabled(new Supervision());
			for(int i=0; i<instituciones.size(); i++)
			{
				if(instituciones.get(i).getSubnivel()!=0L)
				{ugels.add(instituciones.get(i));}				
			}
			institucionList=getListSelectItem(ugels,"id","nombre",true);
			instituciones=null;
			ugels=null;
		}
		bean=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		HistorialLaboral object = (HistorialLaboral)getBean();
		
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el Tipo de Institución.");			
			success = false;
		}
		else if(!validateSelect(object.getInstitucion()))
		{
			setMessageError("Debe seleccionar la Institución.");			
			success = false;
		}
		else if(!validateSelect(object.getPuesto()))
		{
			setMessageError("Debe seleccionar el Puesto Laboral.");			
			success = false;
		}
		else if(!validateEmpty(object.getFuncion()))
		{
			setMessageError("Debe ingresar la Función Principal.");			
			success = false;
		}
		else if(!validateEmpty(object.getInicio()))
		{
			setMessageError("Debe ingresar la Fecha de Inicio de la Experiancia Laboral.");			
			success = false;
		}
		else if(!validateEmpty(object.getFin()))
		{
			setMessageError("Debe ingresar la Fecha de Fin de la Experiancia Laboral.");			
			success = false;
		}
		else if(!object.getInicio().before(object.getFin()))
		{
			setMessageError("La fecha final no puede ser menor a la fecha de inicio.");			
			success = false;
		}
		object=null;
		return success;
	}

	public List<SelectItem> getPuestoList() 							{return puestoList;}
	public void setPuestoList(List<SelectItem> puestoList) 				{this.puestoList = puestoList;}
	
	public List<SelectItem> getInstitucionList() 						{return institucionList;}
	public void setInstitucionList(List<SelectItem> institucionList) 	{this.institucionList = institucionList;}
	
	public List<SelectItem> getResolucionList() 						{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 		{this.resolucionList = resolucionList;}
	
	public String getNombrePersonal() 									{return nombrePersonal;}
	public void setNombrePersonal(String nombrePersonal) 				{this.nombrePersonal = nombrePersonal;}

	public SeguridadService getMyService() 								{return myService;}
	public void setMyService(SeguridadService myService) 				{this.myService = myService;}
} 
