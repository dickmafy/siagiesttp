package modules.planificacion.controller; 
import com.belogick.factory.util.controller.GenericController;

import dataware.service.PlanificacionService;
import modules.planificacion.domain.Capacitacion;
import modules.seguridad.domain.Usuario;

public class PlanificacionCapacitacion extends GenericController   
{	 
	
	private PlanificacionService myService;
	private Long pintitucion;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Capacitacion";
		moduleName="Planificacion";
		userName=usr.getUsuario();
		pintitucion = usr.getInstitucion();
		
		defaultList();
		page_new="planificacion_capacitacion_new";
		page_main="planificacion_capacitacion_list";
		page_update="planificacion_capacitacion_update";
		forward(page_main);
	}
	
	public void init(Long pcodigo_Capacitacion) throws Exception 
	{
		pintitucion=null;
		pintitucion = pcodigo_Capacitacion;
		init();
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Capacitacion bean = new Capacitacion();
		bean.setPk_institucion(pintitucion);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		bean=null;
	}
	
	@Override
	public void afterNew() throws Exception 
	{
		setEnabled(new Capacitacion());
		Capacitacion bean = (Capacitacion) getBean();
		bean.setPk_institucion(pintitucion);
	}
	
  
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Capacitacion object = (Capacitacion)getBean();
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe ingresar el tipo.");			
			success = false;
		}
		else if(!validateSelect(object.getCondicion()))
		{
			setMessageError("Debe ingresar la condicion.");			
			success = false;
		}
		else if(!validateSelect(object.getConvocatoria()))
		{
			setMessageError("Debe ingresar la convocatoria.");			
			success = false;
		}
		else if(!validateSelect(object.getArea()))
		{
			setMessageError("Debe ingresar el Area.");			
			success = false;
		}
		object=null;
		return success;
	} 
	
	public PlanificacionService getMyService() {		return myService;	}
	public void setMyService(PlanificacionService myService) {		this.myService = myService;	}

	
	
	
} 
