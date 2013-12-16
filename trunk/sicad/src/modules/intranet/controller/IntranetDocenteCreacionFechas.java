package modules.intranet.controller; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.model.SelectItem;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.belogick.factory.util.controller.GenericController;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import dataware.service.HorarioService;
import modules.administracion.domain.MetaInstitucional;
import modules.horario.domain.Seccion;
import modules.intranet.domain.Fecha;
import modules.intranet.domain.UtilFecha;
import modules.seguridad.domain.Usuario;

public class IntranetDocenteCreacionFechas extends GenericController   
{
	private List<SelectItem>    profesionList;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    docenteList;
	
	List<MetaInstitucional> metas;
	private Long annio,proceso,profesion,turno;
	private Long institucion;
	private HorarioService	myService;
	private Date fecha_inicio;
	
	private List<String> listaDiasSeleccionados;  
    private Map<String,String> listaDias;  
    private Integer cantidad_clases;
    private List<Fecha> listFechas;
    public void init(Seccion pseccion) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();

		
		//defaultList();
		page_new="";
		page_main="IntranetDocenteCreacionFechas_list";
		page_update="";
		forward(page_main);
		
		
		 llenarDias();  
		 
	}

	private void llenarDias() {
		listaDias = new HashMap<String, String>();  
	        listaDias.put("Lunes", "1");  
	        listaDias.put("Martes", "2");  
	        listaDias.put("Miercoles", "3");  
	        listaDias.put("Jueves", "4");
	        listaDias.put("Viernes", "5");
	        listaDias.put("Sabado", "6");
	        listaDias.put("Domingo", "7");
	}
	
	public void init() throws Exception
	{init(null);}
	
	
	public void generarFechas() throws ParseException{
		
		listFechas = new ArrayList<>();

			for (int j = 0; j <= 7; j++) 
			{
				try 
				{

					listFechas = getFechaConNombreDia(this.fecha_inicio, cantidad_clases,Integer.valueOf(listaDiasSeleccionados.get(j)));
				} catch (NumberFormatException e) {
				} catch (IndexOutOfBoundsException e) {
				}
			}
			
			
		
		
		//Collections.sort(listFechas,Collections.reverseOrder());
		Collections.sort(listFechas, new Comparator<Fecha>()
		{
			public int compare (Fecha m1, Fecha m2)
	    	{return m1.getFechaListada().compareTo(m2.getFechaListada());     }
		});
		
				
	} 

	
	
	private  List<Fecha> getFechaConNombreDia(Date fechaInicial,Integer cantidad_clases,Integer numero_dia) throws ParseException  {

	
		Fecha fecha = new Fecha();

		GregorianCalendar gcalendarInicial = new GregorianCalendar();
		gcalendarInicial.setTime(fechaInicial);
		GregorianCalendar gcalendarFinal = new GregorianCalendar();
		gcalendarFinal.setTime(fechaInicial);
		//# dias hasta el que va calcular.
		
		gcalendarFinal.add(Calendar.DAY_OF_WEEK, cantidad_clases);  
				
		
		while (gcalendarInicial.before(gcalendarFinal)) 
		{
			
			
				
			fecha = new Fecha();
			//se va incrementando 1 dia
			


			switch (numero_dia) 
			{

			case 1:
				if (getDiaDeLaSemana(gcalendarInicial).equals("Lunes")) {
					fecha.setDia(getDiaDeLaSemana(gcalendarInicial));
				}
				break;
			case 2:
				if (getDiaDeLaSemana(gcalendarInicial).equals("Martes")) {
					fecha.setDia(getDiaDeLaSemana(gcalendarInicial));
				}
				break;
			case 3:
				if (getDiaDeLaSemana(gcalendarInicial).equals("Miercoles")) {
					fecha.setDia(getDiaDeLaSemana(gcalendarInicial));
				}
				break;
			case 4:
				if (getDiaDeLaSemana(gcalendarInicial).equals("Jueves")) {
					fecha.setDia(getDiaDeLaSemana(gcalendarInicial));
				}
				break;
			case 5:
				if (getDiaDeLaSemana(gcalendarInicial).equals("Viernes")) {
					fecha.setDia(getDiaDeLaSemana(gcalendarInicial));
				}
				break;
			case 6:
				if (getDiaDeLaSemana(gcalendarInicial).equals("Sabado")) {
					fecha.setDia(getDiaDeLaSemana(gcalendarInicial));
				}
				break;
			case 7:
				if (getDiaDeLaSemana(gcalendarInicial).equals("Domingo")) {
					fecha.setDia(getDiaDeLaSemana(gcalendarInicial));
				}
				break;
			default:
				break;
			}


			// sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

			if (fecha.getDia() != null) 
			{
				fecha.setFechaListada(dateFormat.parse(dateFormat.format(gcalendarInicial.getTime())));
				listFechas.add(fecha);
			}else{
				//por cada dia que no escogio debe incrementarse +1
				gcalendarFinal.add(Calendar.DAY_OF_WEEK,1);
			}
			
			gcalendarInicial.add(Calendar.DAY_OF_WEEK, 1);
			 
			
			
		}
			
			
		
			return listFechas;
}
				
	
	public void guardarCreacionFechas() throws ParseException
	{
	

		
		
		
		
		
		
		
		listFechas=null;
		
		
		
		
		
		
		
		
		
		
	}
	
	
	 public static String getDiaDeLaSemana(GregorianCalendar cal){
	    	
	    	//DOMINGO es 1 ,Lunes = 2...Sabado = 7
	    	switch (cal.get(Calendar.DAY_OF_WEEK)-1) 
	    	{
			
			case 1:
				return "Lunes";
			case 2:
				return "Martes";
			case 3:
				return "Miercoles";
			case 4:
				return "Jueves";
			case 5:
				return "Viernes";
			case 6:
				return "Sabado";
			case 0:
				return "Domingo";
			default:
				break;
			}
			return "";
	    		
	    			
	    }
	
/*
	@SuppressWarnings("unused")
	private  List<Fecha> getFechaConNombreDia(Date fechaInicial,Integer cantidad_clases,Boolean lunes,
						Boolean martes,Boolean miercoles,Boolean jueves,Boolean viernes,Boolean sabado,Boolean domingo) throws ParseException  {
		
		System.out.println("-------------------PARTE INTRANET DOCENTE CREACION FECHAS------------");
		
		List<Fecha> fechas = new ArrayList<>();
		Fecha fecha = new Fecha();
		
		GregorianCalendar gcalendarInicial = new GregorianCalendar();
		gcalendarInicial.setTime(fechaInicial); 
		GregorianCalendar gcalendarFinal = new GregorianCalendar();
		gcalendarFinal.add(Calendar.DAY_OF_WEEK, cantidad_clases); // # dias hasta el que va calcular.
		while (gcalendarInicial.before(gcalendarFinal)) {
			fecha = new Fecha();
			gcalendarInicial.add(Calendar.DAY_OF_WEEK, 1);

			
			if(lunes){if(getDiaDeLaSemana(gcalendarInicial).equals("Lunes")){{fecha.setDia(getDiaDeLaSemana(gcalendarInicial));}}}
			if(martes){if(getDiaDeLaSemana(gcalendarInicial).equals("Martes")){{fecha.setDia(getDiaDeLaSemana(gcalendarInicial));}}}
			if(miercoles){if(getDiaDeLaSemana(gcalendarInicial).equals("Miercoles")){{fecha.setDia(getDiaDeLaSemana(gcalendarInicial));}}}
			if(jueves){if(getDiaDeLaSemana(gcalendarInicial).equals("Jueves")){{fecha.setDia(getDiaDeLaSemana(gcalendarInicial));}}}
			if(viernes){if(getDiaDeLaSemana(gcalendarInicial).equals("Viernes")){{fecha.setDia(getDiaDeLaSemana(gcalendarInicial));}}}
			if(sabado){if(getDiaDeLaSemana(gcalendarInicial).equals("Sabado")){{fecha.setDia(getDiaDeLaSemana(gcalendarInicial));}}}
			if(domingo){if(getDiaDeLaSemana(gcalendarInicial).equals("Domingo")){{fecha.setDia(getDiaDeLaSemana(gcalendarInicial));}}}

			SimpleDateFormat sdf;
			//sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			
			if (fecha.getDia()!=null)
			{ 
				fecha.setFechaListada(dateFormat.parse(dateFormat.format(gcalendarInicial.getTime())));
				fechas.add(fecha);
			}

		
			
		}
		return fechas;
	}
	*/

	    
	
	public HorarioService getMyService() 							{return myService;}
	public void setMyService(HorarioService myService) 				{this.myService = myService;}

	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getProceso() 										{return proceso;}
	public void setProceso(Long proceso) 							{this.proceso = proceso;}

	public Long getProfesion() 										{return profesion;}
	public void setProfesion(Long profesion) 						{this.profesion = profesion;}

	public Long getTurno() 											{return turno;}
	public void setTurno(Long turno) 								{this.turno = turno;}
	
	public List<SelectItem> getProfesionList() 						{return profesionList;}
	public void setProfesionList(List<SelectItem> profesionList) 	{this.profesionList = profesionList;}
	
	public List<SelectItem> getProcesoList() 						{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 		{this.procesoList = procesoList;}

	public List<SelectItem> getDocenteList() 						{return docenteList;}
	public void setDocenteList(List<SelectItem> docenteList) 		{this.docenteList = docenteList;}

	public List<String> getListaDiasSeleccionados() {
		return listaDiasSeleccionados;
	}

	public void setListaDiasSeleccionados(List<String> listaDiasSeleccionados) {
		this.listaDiasSeleccionados = listaDiasSeleccionados;
	}

	public Map<String, String> getListaDias() {
		return listaDias;
	}

	public void setListaDias(Map<String, String> listaDias) {
		this.listaDias = listaDias;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	

	public List<Fecha> getListFechas() {
		return listFechas;
	}

	public void setListFechas(List<Fecha> listFechas) {
		this.listFechas = listFechas;
	}

	public Integer getCantidad_clases() {
		return cantidad_clases;
	}

	public void setCantidad_clases(Integer cantidad_clases) {
		this.cantidad_clases = cantidad_clases;
	}

} 
