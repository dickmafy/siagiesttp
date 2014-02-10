package modules.marco.controller; 
import java.util.List;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.MarcoService;
import modules.marco.domain.Itinerario;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class ProfesionItinerario extends GenericController   
{	
	private MarcoService	myService;
	private List<ReferenteEducativo> moduloList;
	
	private Long profesion, modulo;
	private String nombreProfesion, pagina;
	private Double totalCreditos;
	private Long totalHoras;
	private Long totalHorasSemestre;
	
	public void init(Long id, String nombre, String returnPage) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Marco Educativo";
		moduleName="Profesion - Itinerario";
		userName=usr.getUsuario();
		profesion=id;
		nombreProfesion=nombre;
		pagina=returnPage;
		modulo=-1L;
		moduloList=myService.listarReferenteEducativo(profesion, 1, 1L);
		
		page_new="itnr_new";
		page_update="itnr_update";
		page_main="itnr_list";
		defaultList();		
		forward(page_main);
	}
	
	public void returnPage() throws Exception
	{forward(pagina);}
	
	@Override
	public void defaultList() throws Exception
	{
		List<Itinerario> itins=myService.listarItinerario(profesion, modulo);
		setBeanList(itins);
		calcularTotales(itins);
		itins=null;
	}
	
	public void calcularTotales(List<Itinerario> listado) throws Exception
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
		Itinerario bean=(Itinerario)getBean();
		bean.setHorasTotal(bean.getHorasSemestre()*18);
		setBean(bean);
		bean=null;
	}
	
	public void afterNew() throws Exception
	{
		Itinerario bean=new Itinerario();
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		bean.setProfesion(profesion);
		bean.setModulo(modulo);
		setBean(bean);
		bean=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Itinerario object = (Itinerario)getBean();
		if(!validateSelect(object.getModulo()))
		{
			setMessageError("Debe seleccionar un Módulo.");			
			success = false;
		}
		else if(!validateEmpty(object.getTitulo()))
		{
			setMessageError("Debe ingresar el Título de la Unidad Didactica.");			
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
			setMessageError("Debe ingresar la cantidad de créditos.");			
			success = false;
		}
		object=null;
		return success;
	}
	
	public MarcoService getMyService() 										{return myService;}
	public void setMyService(MarcoService myService) 						{this.myService = myService;}

	public String getNombreProfesion() 										{return nombreProfesion;}
	public void setNombreProfesion(String nombreProfesion) 					{this.nombreProfesion = nombreProfesion;}

	public List<ReferenteEducativo> getModuloList() 						{return moduloList;}
	public void setModuloList(List<ReferenteEducativo> moduloList) 			{this.moduloList = moduloList;}

	public Long getModulo() 												{return modulo;}
	public void setModulo(Long modulo) 										{this.modulo = modulo;}

	public Double getTotalCreditos() 										{return totalCreditos;}
	public void setTotalCreditos(Double totalCreditos) 						{this.totalCreditos = totalCreditos;}

	public Long getTotalHoras() 											{return totalHoras;}
	public void setTotalHoras(Long totalHoras) 								{this.totalHoras = totalHoras;}

	public Long getTotalHorasSemestre() 									{return totalHorasSemestre;}
	public void setTotalHorasSemestre(Long totalHorasSemestre) 				{this.totalHorasSemestre = totalHorasSemestre;}
} 
