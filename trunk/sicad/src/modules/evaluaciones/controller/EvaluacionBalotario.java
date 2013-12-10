package modules.evaluaciones.controller; 

import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import modules.evaluaciones.domain.Balotario;
import modules.evaluaciones.domain.Indicadores;
import modules.evaluaciones.domain.Instrumento;
import modules.seguridad.domain.Usuario;

public class EvaluacionBalotario extends GenericController   
{	 
	private List<SelectItem> instrumentoList;
	
	@Override
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Balotario ";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="balotario_new";
		page_main="balotario_list";
		page_update="balotario_update";
		forward(page_main);
		
	}
		
	
	@Override
	public void afterNew() throws Exception
	{
		setEnabled(new Balotario());
		instrumentoList=getListSelectItem(new Instrumento(),"id","nombre",true);
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Balotario object = (Balotario)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del aspecto.");			
			success = false;
		}
		if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción del aspecto.");			
			success = false;
		}
		else if(!validateSelect(object.getInstrumento()))
		{
			setMessageError("Debe seleccionar un Instrumento.");			
			success = false;
		}
		
		object=null;
		return success;
	}  
	
	public void optionGoPreguntas() throws Exception
	{
		EvaluacionPregunta go = (EvaluacionPregunta)getSpringBean("evaluacionPregunta");
		go.init(((Balotario)getBeanSelected()).getId().longValue(), ((Balotario)getBeanSelected()).getNombre());
	}


	public List<SelectItem> getInstrumentoList() 							{return instrumentoList;}
	public void setInstrumentoList(List<SelectItem> instrumentoList) 		{this.instrumentoList = instrumentoList;}
		
	
} 
