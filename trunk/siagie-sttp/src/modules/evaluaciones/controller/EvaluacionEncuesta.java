package modules.evaluaciones.controller; 
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import modules.evaluaciones.domain.Balotario;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.controller.GenericController;

@ManagedBean
@SessionScoped
public class EvaluacionEncuesta extends GenericController   
{	 
	private List<SelectItem> aspectoList;
	private Long aspecto;
	private String  nombreAspecto;
    private Long instrumento_id;
    private List<Balotario> listaPregunta;
    private Balotario beanSelected;
    
    
	public void init(Long id_instrumento) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Encuesta";
		userName=usr.getUsuario();
		setInstrumento_id(id_instrumento);
		defaultList();
		page_new="encuesta_new";
		page_main="encuesta_list";
		forward(page_main);
		
	}		

	
	@Override
	public void defaultList() throws Exception
	{
		Balotario Balotario = new Balotario();
	//	Balotario.setPk_instrumento(getInstrumento_id());
		setBeanList(getService().listByObjectEnabledDisabled(Balotario)); 
		Balotario = null;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void nativeSave(ActionEvent event) throws Exception 
	{
		listaPregunta = getBeanList();
		for (Balotario item : listaPregunta) {
			setBeanSave(item);
			super.save(event);
		}
		
	}
	

	
	public Long getAspecto()             						{return aspecto;}
	public void setAspecto(Long aspecto) 						{this.aspecto = aspecto;}

	public String getNombreAspecto()                   			{return nombreAspecto;}
	public void setNombreAspecto(String nombreAspecto) 			{this.nombreAspecto = nombreAspecto;}

	public List<SelectItem> getAspectoList() 					{return aspectoList;}
	public void setAspectoList(List<SelectItem> aspectoList) 	{this.aspectoList = aspectoList;}
	
	public Long getInstrumento_id() 							{return instrumento_id;}
	public void setInstrumento_id(Long instrumento_id) 			{this.instrumento_id = instrumento_id;}

	public Balotario getBeanSelected() 							{return beanSelected;}
	public void setBeanSelected(Balotario beanSelected) 		{this.beanSelected = beanSelected;}

	public List<Balotario> getListaPregunta() {return listaPregunta;}
	public void setListaPregunta(List<Balotario> listaPregunta) {this.listaPregunta = listaPregunta;}
	


	

} 


