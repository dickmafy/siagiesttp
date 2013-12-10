package modules.evaluaciones.controller; 
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;


import modules.administracion.domain.Personal;
import modules.evaluaciones.domain.Aspecto;
import modules.evaluaciones.domain.Pregunta;
import modules.seguridad.domain.Usuario;

public class EvaluacionPregunta extends GenericController   
{	 
	private List<SelectItem> aspectoList;
	private Long aspecto;
	private String  nombreAspecto;
	
	public void init(Long id,String nom) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Pregunta ";
		userName=usr.getUsuario();
		aspecto=id;
		nombreAspecto=nom;
		defaultList();
		page_new="pregunta_new";
		page_main="pregunta_list";
		page_update="pregunta_update";
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Pregunta bean=new Pregunta();
		bean.setAspecto(aspecto);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		bean=null;
	}

	@Override
	public void afterNew() throws Exception
	{
		Pregunta bean=new Pregunta();
		bean.setAspecto(aspecto);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		bean=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Pregunta object = (Pregunta)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar la Pregunta.");			
			success = false;
		}
		object=null;
		return success;
	}

	public Long getAspecto() 								{return aspecto;}
	public void setAspecto(Long aspecto) 					{this.aspecto = aspecto;}

	public String getNombreAspecto()                   		{return nombreAspecto;}
	public void setNombreAspecto(String nombreAspecto) 		{this.nombreAspecto = nombreAspecto;}

	public List<SelectItem> getAspectoList() 				{return aspectoList;}
	public void setAspectoList(List<SelectItem> aspectoList){this.aspectoList = aspectoList;}
	
} 
