package modules.cetpro.controller; 
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import modules.administracion.domain.Oferta;
import modules.administracion.domain.Personal;
import modules.admision.domain.Matricula;
import modules.admision.domain.MatriculaSeccion;
import modules.admision.domain.Proceso;
import modules.admision.domain.Requisitos;
import modules.horario.domain.Seccion;
import modules.intranet.domain.Fecha;
import modules.mantenimiento.domain.Banco;
import modules.marco.domain.Itinerario;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.AdmisionService;

@ManagedBean
@SessionScoped
public class CetproMatricula extends GenericController   
{
	private AdmisionService	myService;
	
	private	List<Requisitos> 	requisitos;
	private	List<Itinerario> 	itinerario;
	private	List<MatriculaSeccion>	matriculaList;
	private List<SelectItem>    moduloList;
	private List<SelectItem>    unidadList;
	private List<SelectItem>    seccionList;
	private List<SelectItem>    docenteList;
	private	List<Oferta> profesiones;
	
	private boolean enabled;
	private Long 	proceso,institucion,tipo,modulo,unidad,seccion,annio,turno,docente;
	private Date 				fecha_inicio;
	private Integer 			cantidad_clases;
	private List<String> 		listaDiasSeleccionados;  
    private Map<String,String> 	listaDias;      
    private List<Fecha> 		listFechas;
	private	MatriculaSeccion	selectSeccion;
	private SimpleDateFormat dateFormat;
	
	public void init(Long id) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Matricula";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		proceso=id;
		annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");	
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//requisitos = new ArrayList();
		//procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
		
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		obj.setPuesto(6L);
		docenteList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "apepat,apemat,nombres"," ",true);
		
		page_new="cetpro_matricula_new";
		page_main="cetpro_matricula_list";
		page_update="cetpro_matricula_detail";	
		
		forward(page_main);
		
		//llena la lista de dias multicheck
		llenarDias();  
		
	}
	public void init() throws Exception
	{init(-1L);}
	
	public void defaultList() throws Exception
	{	
		if(annio.longValue()>0L && modulo.longValue()>0L)
		{setBeanList(myService.listarUnidadesCetpro(annio,modulo));}
		else
		{setBeanList(new ArrayList<CetproMatricula>());}
	}
	
	
	public void guardarMatriculaCetpro() throws Exception{
		
		
	} 
	
	public void cargarModulo() throws Exception
	{
		profesiones=myService.listarOferta(institucion, dateFormat.parse(annio+"-01-01") ,1L);
		
		moduloList=getListSelectItem(profesiones, "id","nombreProfesion",false);
	}
	
	public void selectModulo() throws Exception
	{
		Itinerario obj=new Itinerario();
		obj.setProfesion(modulo);
		unidadList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "titulo"," ",false);
		unidad=-1L;

	}
	
	@SuppressWarnings("unchecked")
	private void llenarDias() {
		listaDias = new HashMap<String, String>();  
	        listaDias.put("Lunes", "1");  
	        listaDias.put("Martes", "2");  
	        listaDias.put("Miercoles", "3");  
	        listaDias.put("Jueves", "4");
	        listaDias.put("Viernes", "5");
	        listaDias.put("Sabado", "6");
	        listaDias.put("Domingo", "7");


	        //ordenamiento del Hashmap
	        HashMap mapResultado = new LinkedHashMap();
	        List misMapKeys = new ArrayList(listaDias.keySet());
	        List misMapValues = new ArrayList(listaDias.values());
	        TreeSet conjuntoOrdenado = new TreeSet(misMapValues);
	        Object[] arrayOrdenado = conjuntoOrdenado.toArray();
	        int size = arrayOrdenado.length;
	        for (int i=0; i<size; i++) 
	        {
	        mapResultado.put(misMapKeys.get(misMapValues.indexOf(arrayOrdenado[i])),arrayOrdenado[i]);
	        }
	        listaDias = mapResultado;
	        
	}
	
	public void generarFechas() throws ParseException{
		listFechas = new ArrayList<Fecha>();
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
	
	
	public AdmisionService getMyService() 										{return myService;}	
	public void setMyService(AdmisionService myService)							{this.myService = myService;}
	
	public List<Requisitos> getRequisitos() 									{return requisitos;}
	public void setRequisitos(List<Requisitos> requisitos) 						{this.requisitos = requisitos;}
	
	public List<SelectItem> getDocenteList() 										{return docenteList;}
	public void setDocenteList(List<SelectItem> docenteList) 						{this.docenteList = docenteList;}
		
	public List<SelectItem> getModuloList() 									{return moduloList;}
	public void setModuloList(List<SelectItem> moduloList) 						{this.moduloList = moduloList;}
	
	public List<SelectItem> getSeccionList() 									{return seccionList;}
	public void setSeccionList(List<SelectItem> seccionList) 					{this.seccionList = seccionList;}
		
	public List<SelectItem> getUnidadList() 									{return unidadList;}
	public void setUnidadList(List<SelectItem> unidadList) 						{this.unidadList = unidadList;}
	
	public boolean isEnabled() 													{return enabled;}
	public void setEnabled(boolean enabled) 									{this.enabled = enabled;}
	
	public Long getProceso() 													{return proceso;}
	public void setProceso(Long proceso) 										{this.proceso = proceso;}
	
	public Long getTipo() 														{return tipo;}
	public void setTipo(Long tipo) 												{this.tipo = tipo;}
	
	public Long getModulo() 													{return modulo;}
	public void setModulo(Long modulo) 											{this.modulo = modulo;}
	
	public Long getUnidad()	 													{return unidad;}
	public void setUnidad(Long unidad) 											{this.unidad = unidad;}
	
	public Long getSeccion() 													{return seccion;}
	public void setSeccion(Long seccion) 										{this.seccion = seccion;}
	
	public Long getAnnio() 														{return annio;}
	public void setAnnio(Long annio) 											{this.annio = annio;}
	
	public MatriculaSeccion getSelectSeccion() 									{return selectSeccion;}
	public void setSelectSeccion(MatriculaSeccion selectSeccion) 				{this.selectSeccion = selectSeccion;}
	
	public List<MatriculaSeccion> getMatriculaList() 							{return matriculaList;}
	public void setMatriculaList(List<MatriculaSeccion> matriculaList) 			{this.matriculaList = matriculaList;}
	
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Integer getCantidad_clases() {
		return cantidad_clases;
	}
	public void setCantidad_clases(Integer cantidad_clases) {
		this.cantidad_clases = cantidad_clases;
	}
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
	public List<Fecha> getListFechas() {
		return listFechas;
	}
	public void setListFechas(List<Fecha> listFechas) {
		this.listFechas = listFechas;
	}
	public Long getTurno() {
		return turno;
	}
	public void setTurno(Long turno) {
		this.turno = turno;
	}
	public Long getDocente() {
		return docente;
	}
	public void setDocente(Long docente) {
		this.docente = docente;
	}
	
	
} 
