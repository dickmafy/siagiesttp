package modules.evaluaciones.controller; 
import java.util.List;

import javax.faces.model.SelectItem;

import modules.evaluaciones.domain.Escala;
import modules.evaluaciones.domain.EscalaDetalle;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

public class EvaluacionEscalaDetalle extends GenericController   
{	 
	private List<SelectItem> escalaList;
	private Long escala_id;
	private String escala_nombre;
	

	public void init(Long id, String nom) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Escala Detalle ";
		userName=usr.getUsuario();
		escala_id=id;
		escala_nombre=nom;
		
		defaultList();
		page_new="esc_det_new";
		page_main="esc_det_list";
		page_update="esc_det_update";
		forward(page_main);
		
	}	


	
	@Override
	public void defaultList() throws Exception {
		EscalaDetalle bean = new EscalaDetalle();
		bean.setEscala(escala_id);
		setBeanList(getService().listByObjectEnabledDisabled(bean));

	}

	@Override
	public void afterNew() throws Exception {
		setEnabled(new EscalaDetalle());
		escalaList = getListSelectItem(new Escala(), "id", "nombre", true);

		EscalaDetalle bean = new EscalaDetalle();
		bean.setEscala(escala_id);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		bean = null;

	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		EscalaDetalle object = (EscalaDetalle)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre de la Escala.");			
			success = false;
		}
		else if(!validateZero(object.getValor()))
		{
			setMessageError("Debe ingresar la descripción de la Escala.");			
			success = false;
		}
		
		object=null;
		return success;
	}   
	
	public Long getEscala_id() 								{return escala_id;}
	public void setEscala_id(Long escala_id) 				{this.escala_id = escala_id;}
	
	public String getEscala_nombre() 						{return escala_nombre;}
	public void setEscala_nombre(String escala_nombre) 		{this.escala_nombre = escala_nombre;}
	
	public List<SelectItem> getEscalaList()					 {return escalaList;}
	public void setEscalaList(List<SelectItem> escalaList)	 {this.escalaList = escalaList;}
} 
