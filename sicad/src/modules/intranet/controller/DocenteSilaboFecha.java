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
import com.belogick.factory.util.support.ServiceException;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import dataware.service.HorarioService;
import modules.administracion.domain.MetaInstitucional;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCalendario;
import modules.horario.domain.SilaboCronograma;
import modules.intranet.domain.Fecha;
import modules.intranet.domain.UtilFecha;
import modules.seguridad.domain.Usuario;

public class DocenteSilaboFecha extends GenericController   
{
	private List<SelectItem>    profesionList;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    docenteList;
	
	List<MetaInstitucional> metas;
	private Long annio,proceso,profesion,turno,meta,seccion,docente,unidad;
	private Long institucion;
	private HorarioService	myService;
	private Date fecha_inicio;
	
	private List<String> listaDiasSeleccionados;  
    private Map<String,String> listaDias;  
    private Integer cantidad_clases;
    private List<Fecha> listFechas;
    private SilaboCronograma silaboCronograma;
    public void init(Seccion pseccion) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		//defaultList();
		page_new="";
		page_main="DocenteSilaboFecha";
		page_update="";
		forward(page_main);
		//silaboCronograma = psilaboCronograma;
		seccion=pseccion.getId();
		docente=pseccion.getDocente();
		meta=pseccion.getMeta();
		unidad=pseccion.getValorUnidad();		
		
		llenarDias();  		 
	}

    public void guardarCreacionFechas() throws Exception
	{
    	
    	Fecha fecha = new Fecha();
    	SilaboCronograma silaboCronograma =new SilaboCronograma();
    	silaboCronograma.setPk_meta(meta);
    	silaboCronograma.setContenido("-");
    	silaboCronograma.setPk_unidad(unidad);
    	silaboCronograma.setPk_seccion(seccion);
    	silaboCronograma.setPk_docente(docente);
    	silaboCronograma.setEstado(1L);
    	
    	silaboCronograma = (SilaboCronograma) myService.save(silaboCronograma); 	
    	SilaboCalendario silaboCalendario;
    	for (Fecha item: listFechas) 
    	{
    		silaboCalendario = new SilaboCalendario();
        	silaboCalendario.setPk_silabo_cronograma(silaboCronograma.getId());
        	silaboCalendario.setFecha(item.getFechaListada());
        	silaboCalendario.setEstado(1L);
			myService.save(silaboCalendario);
		}
    	
    	myService.insertSilaboAlumno(meta, unidad, seccion, docente);
    	    	
		listFechas=new ArrayList<Fecha>();
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
	
//	public void init() throws Exception
//	{init(null);}
//	
	
	public void generarFechas() throws ParseException{
		listFechas = new ArrayList<>();
		obtenerListaFechasxDia();
		ordernarYRemoverFechasNoValidas();
	}

	private void ordernarYRemoverFechasNoValidas() {
		Collections.sort(listFechas, new Comparator<Fecha>()
		{
			public int compare (Fecha m1, Fecha m2)
	    	{return m1.getFechaListada().compareTo(m2.getFechaListada());     }
		});
		
		
		int size = listFechas.size();
		for (int j = 0; j < size - cantidad_clases; j++) 
		{
			listFechas.remove(size-1-j);
		}
	}

	private void obtenerListaFechasxDia() throws ParseException {
		for (int j = 0; j <= 7; j++) 
		{
			try 
			{
				listFechas = getFechaConNombreDia(this.fecha_inicio, cantidad_clases,Integer.valueOf(listaDiasSeleccionados.get(j)));
			} catch (NumberFormatException e) {
			} catch (IndexOutOfBoundsException e) {
			}
		}
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
				
	
	public static String getDiaDeLaSemana(GregorianCalendar cal)
	{
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

	public SilaboCronograma getSilaboCronograma() {
		return silaboCronograma;
	}

	public void setSilaboCronograma(SilaboCronograma silaboCronograma) {
		this.silaboCronograma = silaboCronograma;
	}

} 

