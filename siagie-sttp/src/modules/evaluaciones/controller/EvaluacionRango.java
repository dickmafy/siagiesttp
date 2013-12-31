package modules.evaluaciones.controller;

import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import modules.evaluaciones.domain.Indicadores;
import modules.evaluaciones.domain.Rango;
import modules.seguridad.domain.Usuario;

public class EvaluacionRango extends GenericController   
{	 
	private List<SelectItem> indicadorList;
	private List<Rango> rangoList;
	private Long indicador;
	String  nombreIndicador;
	
	public void init(Long id, String nom) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Evaluaciones";
		moduleName="Rango ";
		indicador = id;
		nombreIndicador = nom;
		userName=usr.getUsuario();
		defaultList();
		page_new="ran_new";
		page_main="ran_list";
		page_update="ran_update";
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Rango bean=new Rango();
		bean.setIndicador(indicador);
		setBeanList(getService().listByObjectEnabledDisabled(bean));
		setRangoList(getService().listByObjectEnabledDisabled(bean));
		bean=null;
	}
		
	@Override
	public void afterNew() throws Exception
	{
		Rango bean=new Rango();
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		bean.setIndicador(indicador);
		setBean(bean);
		indicadorList=getListSelectItem(new Indicadores(), "id", "nombre", true);
		bean=null;
	}
	
	public boolean validateRangos(int n) throws Exception
	{
		for(int i=0; i<rangoList.size(); i++)
		{
			int max =  rangoList.get(i).getValorMax().intValue();//0
			int min =  rangoList.get(i).getValorMin().intValue();//5
			
			for(int j=min;j<=max;j++){
				if(n==j){return false;}				
			}
		}
		return true;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Rango object = (Rango)getBean();
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre de rango.");			
			success = false;
		}
		else if(!validateSelect(object.getIndicador()))
		{
			setMessageError("Debe seleccionar un indicador.");			
			success = false;
		}
		else if(!validateRangos(object.getValorMin().intValue())){
			setMessageError("El valor mínimo del rango ya está siendo contenido en otro rango");
			success = false;
		}
		else if(object.getValorMax() <= object.getValorMin()){
			setMessageError("El valor mínimo del rango no puede ser mayor o igual al valor máximo del rango");
			success = false;
		}
		else if(!validateRangos(object.getValorMax().intValue())){
			setMessageError("El valor máximo del rango ya está siendo contenido en otro rango");
			success = false;
		}
		
		object=null;
		return success;
	}
	
	public Long getIndicador()               							{return indicador;}
	public void setIndicador(Long indicador) 							{this.indicador = indicador;}
	
	public String getNombreIndicador()       							{return nombreIndicador;}
	public void setNombreIndicador(String nombreIndicador) 				{this.nombreIndicador = nombreIndicador;}
	
	public List<SelectItem> getIndicadorList() 							{return indicadorList;}
	public void setIndicadorList(List<SelectItem> indicadorList) 	    {this.indicadorList = indicadorList;}

	public List<Rango> getRangoList() 									{return rangoList;}
	public void setRangoList(List<Rango> rangoList) 					{this.rangoList = rangoList;}

	
} 
