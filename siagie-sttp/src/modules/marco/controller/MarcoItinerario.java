package modules.marco.controller; 
import java.util.List;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.MarcoService;
import modules.marco.domain.Itinerario;
import modules.marco.domain.Profesion;
import modules.seguridad.domain.Usuario;

public class MarcoItinerario extends GenericController   
{	
	private MarcoService	myService;
	private List<Profesion> profesionList;
	private Long profesion;
	private Double totalCreditos;
	private Long totalHoras;
	private Long totalHorasSemestre;
	
	private Double totalCreditosP;
	private Long totalHorasP;
	private Long totalHorasSemestreP;
	
	private Double totalCreditosT;
	private Long totalHorasT;
	private Long totalHorasSemestreT;
	
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Marco Educativo";
		moduleName="Profesion - Itinerario General";
		userName=usr.getUsuario();
		profesionList=myService.listarProfesiones();
		page_main="itnr_gen";
		profesion=-1L;
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{
		List<Itinerario> itins=myService.listarItinerarioTotal(profesion);
		setBeanList(itins);
		calcularTotales(itins);
		itins=null;
	}
	
	public void calcularTotales(List<Itinerario> listado) throws Exception
	{
		totalCreditos=0.0;
		totalHoras=0L;
		totalHorasSemestre=0L;
		
		totalCreditosP=0.0;
		totalHorasP=0L;
		totalHorasSemestreP=0L;
		
		totalCreditosT=0.0;
		totalHorasT=0L;
		totalHorasSemestreT=0L;
		
		for(int i=0; i<listado.size(); i++)
		{
			if(listado.get(i).getTipoItinerario().longValue()==1L)
			{
				totalCreditosP=totalCreditosP+listado.get(i).getCreditos();
				totalHorasP=totalHorasP+listado.get(i).getHorasTotal();
				totalHorasSemestreP=totalHorasSemestreP+listado.get(i).getHorasSemestre();
			}
			else
			{
				totalCreditosT=totalCreditosT+listado.get(i).getCreditos();
				totalHorasT=totalHorasT+listado.get(i).getHorasTotal();
				totalHorasSemestreT=totalHorasSemestreT+listado.get(i).getHorasSemestre();
			}
			totalCreditos=totalCreditos+listado.get(i).getCreditos();
			totalHoras=totalHoras+listado.get(i).getHorasTotal();
			totalHorasSemestre=totalHorasSemestre+listado.get(i).getHorasSemestre();
		}
		
	}
	
	public MarcoService getMyService() 										{return myService;}
	public void setMyService(MarcoService myService) 						{this.myService = myService;}

	public List<Profesion> getProfesionList() 								{return profesionList;}
	public void setProfesionList(List<Profesion> profesionList) 			{this.profesionList = profesionList;}

	public Long getProfesion() 												{return profesion;}
	public void setProfesion(Long profesion) 								{this.profesion = profesion;}

	public Double getTotalCreditos() 										{return totalCreditos;}
	public void setTotalCreditos(Double totalCreditos) 						{this.totalCreditos = totalCreditos;}

	public Long getTotalHoras() 											{return totalHoras;}
	public void setTotalHoras(Long totalHoras) 								{this.totalHoras = totalHoras;}

	public Long getTotalHorasSemestre() 									{return totalHorasSemestre;}
	public void setTotalHorasSemestre(Long totalHorasSemestre) 				{this.totalHorasSemestre = totalHorasSemestre;}

	public Double getTotalCreditosP() 										{return totalCreditosP;}
	public void setTotalCreditosP(Double totalCreditosP) 					{this.totalCreditosP = totalCreditosP;}

	public Long getTotalHorasP() 											{return totalHorasP;}
	public void setTotalHorasP(Long totalHorasP) 							{this.totalHorasP = totalHorasP;}

	public Long getTotalHorasSemestreP() 									{return totalHorasSemestreP;}
	public void setTotalHorasSemestreP(Long totalHorasSemestreP) 			{this.totalHorasSemestreP = totalHorasSemestreP;}

	public Double getTotalCreditosT() 										{return totalCreditosT;}
	public void setTotalCreditosT(Double totalCreditosT) 					{this.totalCreditosT = totalCreditosT;}

	public Long getTotalHorasT() 											{return totalHorasT;}
	public void setTotalHorasT(Long totalHorasT) 							{this.totalHorasT = totalHorasT;}

	public Long getTotalHorasSemestreT() 									{return totalHorasSemestreT;}
	public void setTotalHorasSemestreT(Long totalHorasSemestreT) 			{this.totalHorasSemestreT = totalHorasSemestreT;}
} 
