package modules.institucion.controller; 
import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import modules.administracion.domain.Feriado;
import modules.seguridad.domain.Usuario;

public class InstitucionFeriado extends GenericController   
{	 
	private Long institucion;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucional";
		moduleName="Feriado";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		defaultList();
		page_new="itc_fer_new";
		page_main="itc_fer_lst";
		page_update="itc_fer_upd";
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Feriado bean=new Feriado();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBeanList(getService().listByObject(bean));
		bean=null;
	}
		
	@Override
	public void afterNew() throws Exception
	{
		Feriado bean=new Feriado();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
	}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Feriado object = (Feriado)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del Día Feriado.");			
			success = false;
		}
		if(!validateEmpty(object.getFecha_inicio()))
		{
			setMessageError("Debe ingresar la fecha de inicio del feriado.");			
			success = false;
		}
		if(!validateEmpty(object.getFecha_fin()))
		{
			setMessageError("Debe ingresar la fecha final del feriado.");			
			success = false;
		}
		else if(!validateSelect(object.getEstado()))
		{
			setMessageError("Debe seleccionar un estado.");			
			success = false;
		}
		else if(object.getFecha_fin().before(object.getFecha_inicio()))
		{
			setMessageError("La fecha final no puede ser menor a la fecha de inicio del feriado.");			
			success = false;
		}
		object=null;
		return success;
	} 
	
} 
