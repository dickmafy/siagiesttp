package modules.marco.controller; 
import java.util.List;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.MarcoService;
import modules.marco.domain.ItinerarioTransversal;
import modules.marco.domain.Transversal;
import modules.seguridad.domain.Usuario;

public class ProfesionItinerarioTransversal extends GenericController   
{	
	private MarcoService	myService;
	private List<Transversal> moduloList;
	private Long tipo;
	private Double totalCreditos;
	private Long totalHoras;
	private Long totalHorasSemestre;
	
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Marco Educativo";
		moduleName="Profesion - Itinerario";
		userName=usr.getUsuario();
		tipo=-1L;
		moduloList=myService.listarReferenteTransversal(1, 1L);
		page_new="itnrt_new";
		page_update="itnrt_update";
		page_main="itnrt_list";
		defaultList();		
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		List<ItinerarioTransversal> itins=myService.listarItinerarioTransversal(tipo);
		setBeanList(itins);
		calcularTotales(itins);
		itins=null;
	}
	
	public void calcularTotales(List<ItinerarioTransversal> listado) throws Exception
	{
		totalCreditos=0.0;
		totalHoras=0L;
		totalHorasSemestre=0L;
		for(int i=0; i<listado.size(); i++)
		{
			totalCreditos=totalCreditos+listado.get(i).getCreditos();
			totalHoras=totalHoras+listado.get(i).getHorasTotal();
			totalHorasSemestre=totalHorasSemestre+listado.get(i).getHorasSemestre();
		}
		
	}
	public void calcularHoras() throws Exception
	{
		ItinerarioTransversal bean=(ItinerarioTransversal)getBean();
		bean.setHorasTotal(bean.getHorasSemestre()*18);
		setBean(bean);
		bean=null;
	}
	public void afterNew() throws Exception
	{
		ItinerarioTransversal bean=new ItinerarioTransversal();
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		bean.setTipo(tipo);
		setBean(bean);
		bean=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		ItinerarioTransversal object = (ItinerarioTransversal)getBean();
		if(!validateEmpty(object.getTitulo()))
		{
			setMessageError("Debe ingresar el T�tulo de la Unidad Didactica.");			
			success = false;
		}
		else if(!validateSelect(object.getSemestre()))
		{
			setMessageError("Debe seleccionar un semestre.");			
			success = false;
		}
		else if(!validateZero(object.getHorasSemestre()))
		{
			setMessageError("Debe ingresar la cantidad de horas por semestre.");			
			success = false;
		}
		else if(!validateZero(object.getCreditos()))
		{
			setMessageError("Debe ingresar la cantidad de cr�ditos.");			
			success = false;
		}
		object=null;
		return success;
	}
	
	public MarcoService getMyService() 										{return myService;}
	public void setMyService(MarcoService myService) 						{this.myService = myService;}

	public Double getTotalCreditos() 										{return totalCreditos;}
	public void setTotalCreditos(Double totalCreditos) 						{this.totalCreditos = totalCreditos;}

	public Long getTotalHoras() 											{return totalHoras;}
	public void setTotalHoras(Long totalHoras) 								{this.totalHoras = totalHoras;}

	public Long getTotalHorasSemestre() 									{return totalHorasSemestre;}
	public void setTotalHorasSemestre(Long totalHorasSemestre) 				{this.totalHorasSemestre = totalHorasSemestre;}

	public Long getTipo() 													{return tipo;}
	public void setTipo(Long tipo) 											{this.tipo = tipo;}

	public List<Transversal> getModuloList()								{return moduloList;}
	public void setModuloList(List<Transversal> moduloList) 				{this.moduloList = moduloList;}
} 
