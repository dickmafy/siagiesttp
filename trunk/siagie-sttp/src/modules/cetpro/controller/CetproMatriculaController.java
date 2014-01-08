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

import org.hibernate.Query;

import modules.administracion.domain.Oferta;
import modules.administracion.domain.Personal;
import modules.admision.domain.Interesado;
import modules.admision.domain.Matricula;
import modules.admision.domain.MatriculaSeccion;
import modules.admision.domain.Proceso;
import modules.admision.domain.Requisitos;
import modules.cetpro.domain.CetproMatricula;
import modules.cetpro.domain.CetproMatriculaAlumno;
import modules.horario.domain.Seccion;
import modules.intranet.domain.Fecha;
import modules.mantenimiento.domain.Banco;
import modules.marco.domain.Familia;
import modules.marco.domain.Itinerario;
import modules.marco.domain.Profesion;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.AdmisionService;

@ManagedBean
@SessionScoped
public class CetproMatriculaController extends GenericController   
{
	private AdmisionService	myService;
	
	private	List<Requisitos> 	requisitos;
	private	List<Itinerario> 	itinerario;
	private	List<MatriculaSeccion>	matriculaList;
	private List<SelectItem>    familiaList;
	private List<SelectItem>    moduloList;
	private List<SelectItem>    interesadoList;
	private List<SelectItem>    docenteList;
	private	List<Oferta> 		profesiones;
	List<CetproMatriculaAlumno> listaAlumnos;
	
	private boolean enabled;
	private Long 	proceso,institucion,tipo,familia,unidad,seccion,annio,turno,interesado,docente;
	private Date 				fecha_inicio;
	private Integer 			cantidad_clases;
	private List<String> 		listaDiasSeleccionados;  
    private Map<String,String> 	listaDias;      
    private List<Fecha> 		listFechas;
	private	MatriculaSeccion	selectSeccion;
	private SimpleDateFormat dateFormat;
	
	private List<ReferenteEducativo> esquemaList;
	private List<ReferenteEducativo> contenidoList;
	private List<ReferenteEducativo> transversalList;
	private List<ReferenteEducativo> contenidoTList;
	
	private List<SelectItem> moduloTransversalList;
	private List<SelectItem> moduloProfesionalList;

	
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
		
		enabled=false;
		
		page_new="cetpro_matricula_new";
		page_main="cetpro_matricula_list";
		page_update="cetpro_matricula_detail";	
		
		forward(page_main);
	}
	
	@Override
	public void afterNew() throws Exception {
		enabled=false;
		
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		obj.setPuesto(6L);
		docenteList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "apepat,apemat,nombres"," ",false);
		
		Interesado bean=new Interesado();
    	bean.setInstitucion(institucion);
    	interesadoList= getListSelectItem(myService.listarInteresados(institucion), "id", "apellido_paterno,apellido_materno,nombres"," ",true);
    	
    	
    	
    	Familia tempFamilia = new Familia();
    	familiaList = getListSelectItem(tempFamilia, "id", "nombre",true);
    	
    	
    	ReferenteEducativo tempReferenteEducativo = new ReferenteEducativo();
    	tempReferenteEducativo.setNivelB(0L);
    	tempReferenteEducativo.setNivelC(0L);
		moduloList =  getListSelectItem(tempReferenteEducativo, "id", "titulo",true);

    	obj=null;
    	bean=null;
	}
	
	
	

	
	public void init() throws Exception
	{init(-1L);}
	
	public void defaultList() throws Exception
	{	
		if(annio.longValue()>0L && familia.longValue()>0L)
		{setBeanList(myService.listarUnidadesCetpro(annio,familia));}
		else
		{setBeanList(new ArrayList<CetproMatricula>());}
	}
	
	public void cargarModulo() throws Exception
	{
		profesiones=myService.listarOferta(institucion, dateFormat.parse(annio+"-01-01") ,1L);
		
		familiaList=getListSelectItem(profesiones, "profesion","nombreProfesion",false);
	}
//	
//	public void selectModulo() throws Exception
//	{
//		CetproMatricula bean=(CetproMatricula)getBean();
//		Itinerario obj=new Itinerario();
//		obj.setProfesion(familia);
//		moduloList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "titulo"," ",false);
//		bean.setPk_unidad(-1L);
//		obj=null;
//		bean=null;
//	}
	
	public void addAlumno() throws Exception
	{
		CetproMatricula bean=(CetproMatricula)getBean();
		if(validarAlumno(interesado, bean.getPk_cetpro_matricula()))
		{
			myService.actualizarMatriculaCetpro(true, bean.getPk_cetpro_matricula(), interesado);
			listaAlumnos=myService.listarAlumnosMatricula(bean.getPk_cetpro_matricula());
			setMessageSuccess("El alumno fue matriculado satisfactoriamente.");
		}
		else
		{setMessageError("EL alumno seleccionado ya ha sido matriculado.");}
		bean=null;
	}
	
	public Boolean validarAlumno(Long alumno, Long pk) throws Exception{
		
		CetproMatriculaAlumno bean=new CetproMatriculaAlumno();
    	bean.setPk_cetpro_matricula(pk);
    	bean.setPk_persona(alumno);    	    	
    	List<CetproMatriculaAlumno> list = myService.listByObjectEnabled(bean);
    	bean=null;
    	
    	if(list==null)	{return true;}
    	else					{return false;}
    	    	
	}
	
	public void subAlumno() throws Exception
	{
		CetproMatricula bean=(CetproMatricula)getBean();
		myService.actualizarMatriculaCetpro(true, bean.getPk_cetpro_matricula(), interesado);
		listaAlumnos=myService.listarAlumnosMatricula(bean.getPk_cetpro_matricula());
		setMessageSuccess("La matricula de alumno fue eliminada de la unidad.");
		bean=null;
	}
	
	
	public void guardarMatriculaCetpro() throws Exception{
		CetproMatricula bean = (CetproMatricula)getBean();
				
		if(!enabled)
		{
			if(validateUnidad())
			{
				bean.setAnno(annio);
				bean.setEstado(1L);
				getService().save(bean);
				setMessageSuccess("Se registr� la unidad satisfactoriamente");
			}
		}
		else
		{
			
		}
	}
	
	public boolean validateUnidad() throws Exception
	{
		boolean success = true;
		CetproMatricula object = (CetproMatricula)getBean();
		if(!validateSelect(object.getPk_docente()))
		{
			setMessageError("Debe seleccionar el Docente.");			
			success = false;
		}
		else if(!validateSelect(object.getPk_familia()))
		{
			setMessageError("Debe seleccionar la familia.");			
			success = false;
		}
		else if(!validateSelect(object.getPk_modulo()))
		{
			setMessageError("Debe seleccionar el modulo.");			
			success = false;
		}
		else if(!validateSelect(object.getTurno()))
		{
			setMessageError("Debe seleccionar el turno.");			
			success = false;
		}
		object=null;
		return success;
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
		
	public List<SelectItem> getModuloList() 									{return familiaList;}
	public void setModuloList(List<SelectItem> moduloList) 						{this.familiaList = moduloList;}
	
	public List<SelectItem> getInteresadoList() 									{return interesadoList;}
	public void setInteresadoList(List<SelectItem> interesadoList) 					{this.interesadoList = interesadoList;}
		
	public List<SelectItem> getUnidadList() 									{return moduloList;}
	public void setUnidadList(List<SelectItem> unidadList) 						{this.moduloList = unidadList;}
	
	public boolean isEnabled() 													{return enabled;}
	public void setEnabled(boolean enabled) 									{this.enabled = enabled;}
	
	public Long getProceso() 													{return proceso;}
	public void setProceso(Long proceso) 										{this.proceso = proceso;}
	
	public Long getTipo() 														{return tipo;}
	public void setTipo(Long tipo) 												{this.tipo = tipo;}
	
	public Long getModulo() 													{return familia;}
	public void setModulo(Long modulo) 											{this.familia = modulo;}
	
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

	public Long getInteresado() {
		return interesado;
	}

	public void setInteresado(Long interesado) {
		this.interesado = interesado;
	}

	public List<CetproMatriculaAlumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<CetproMatriculaAlumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	public List<Itinerario> getItinerario() {
		return itinerario;
	}

	public void setItinerario(List<Itinerario> itinerario) {
		this.itinerario = itinerario;
	}

	public List<SelectItem> getFamiliaList() {
		return familiaList;
	}

	public void setFamiliaList(List<SelectItem> familiaList) {
		this.familiaList = familiaList;
	}

	public List<Oferta> getProfesiones() {
		return profesiones;
	}

	public void setProfesiones(List<Oferta> profesiones) {
		this.profesiones = profesiones;
	}

	public Long getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Long institucion) {
		this.institucion = institucion;
	}

	public Long getFamilia() {
		return familia;
	}

	public void setFamilia(Long familia) {
		this.familia = familia;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public List<ReferenteEducativo> getEsquemaList() {
		return esquemaList;
	}

	public void setEsquemaList(List<ReferenteEducativo> esquemaList) {
		this.esquemaList = esquemaList;
	}

	public List<ReferenteEducativo> getContenidoList() {
		return contenidoList;
	}

	public void setContenidoList(List<ReferenteEducativo> contenidoList) {
		this.contenidoList = contenidoList;
	}

	public List<ReferenteEducativo> getTransversalList() {
		return transversalList;
	}

	public void setTransversalList(List<ReferenteEducativo> transversalList) {
		this.transversalList = transversalList;
	}

	public List<ReferenteEducativo> getContenidoTList() {
		return contenidoTList;
	}

	public void setContenidoTList(List<ReferenteEducativo> contenidoTList) {
		this.contenidoTList = contenidoTList;
	}

	public List<SelectItem> getModuloTransversalList() {
		return moduloTransversalList;
	}

	public void setModuloTransversalList(List<SelectItem> moduloTransversalList) {
		this.moduloTransversalList = moduloTransversalList;
	}

	public List<SelectItem> getModuloProfesionalList() {
		return moduloProfesionalList;
	}

	public void setModuloProfesionalList(List<SelectItem> moduloProfesionalList) {
		this.moduloProfesionalList = moduloProfesionalList;
	}
	
	
} 
