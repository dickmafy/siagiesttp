package modules.planificacion.controller; 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.PlanificacionService;
import modules.planificacion.domain.Indicador;
import modules.seguridad.domain.Usuario;

public class PlanificacionIndicador extends GenericController   
{	 
	
	private PlanificacionService myService;
	private Long objetivo;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Indicador";
		moduleName="Planificacion";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="planificacion_indicador_new";
		page_main="planificacion_indicador_list";
		page_update="planificacion_indicador_update";
		forward(page_main);
	}
	
	public void init(Long pcodigo_Indicador) throws Exception 
	{
		objetivo=null;
		objetivo = pcodigo_Indicador;
		init();
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Indicador bean = new Indicador();
		bean.setObjetivo(objetivo);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		bean=null;
	}
	
	@Override
	public void afterNew() throws Exception 
	{
		setEnabled(new Indicador());
		Indicador bean = (Indicador) getBean();
		bean.setObjetivo(objetivo);
	}
	
  
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Indicador object = (Indicador)getBean();
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe ingresar el tipo de Indicador.");			
			success = false;
		}
		object=null;
		return success;
	} 
	
	public PlanificacionService getMyService() {		return myService;	}
	public void setMyService(PlanificacionService myService) {		this.myService = myService;	}

	
	
	
} 
