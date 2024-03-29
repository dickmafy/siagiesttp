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
import javax.persistence.Temporal;

import org.hibernate.Query;

import modules.administracion.domain.Oferta;
import modules.administracion.domain.Personal;
import modules.admision.domain.Interesado;
import modules.admision.domain.Matricula;
import modules.admision.domain.MatriculaSeccion;
import modules.admision.domain.Proceso;
import modules.admision.domain.Requisitos;
import modules.cetpro.domain.CetproCt;
import modules.cetpro.domain.CetproMatricula;
import modules.cetpro.domain.CetproMatriculaAlumno;
import modules.cetpro.domain.CetproMatriculaFecha;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCalendario;
import modules.horario.domain.SilaboCronograma;
import modules.intranet.domain.Fecha;
import modules.mantenimiento.domain.Banco;
import modules.marco.domain.Familia;
import modules.marco.domain.Itinerario;
import modules.marco.domain.Profesion;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Acceso;
import modules.seguridad.domain.Perfil;
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
	
	private boolean enabled, nuevasFechas;
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
	private List<ReferenteEducativo> criteriosList;
	
	private List<SelectItem> moduloTransversalList;
	private List<SelectItem> moduloProfesionalList;
	private Long modulo;

	
	public void init(Long id) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Matricula";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		proceso=id;
		//annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");	
		annio=-1L;
		familia=-1L;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//requisitos = new ArrayList();
		//procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
		
		enabled=false;
		
		page_new="cetpro_matricula_new";
		page_main="cetpro_matricula_list";
		page_update="cetpro_matricula_upd";	
		
		forward(page_main);			
		defaultList();
    	
		
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		obj.setPuesto(6L);
		docenteList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "apepat,apemat,nombres"," ",false);
		
		Interesado bean=new Interesado();
    	bean.setInstitucion(institucion);
    	interesadoList= getListSelectItem(myService.listarInteresados(institucion), "id", "apellido_paterno,apellido_materno,nombres"," ",true);
    	
    	obj=null;
    	bean=null;
	}

	public void init() throws Exception
	{init(-1L);}
	
	public void goPublicar() throws Exception
	{	
		if(validateCapacidades())
		{
			CetproMatricula bean = (CetproMatricula)getBeanSelected();
			CetproMatriculaFecha fecha = new CetproMatriculaFecha();
			List<CetproMatriculaFecha> fechas = new ArrayList<CetproMatriculaFecha>();
			fecha.setPk_cetpro_matricula(bean.getId());
	
			fechas = myService.listByObject(fecha);		
			fecha = fechas.get(fechas.size()-1);	
			bean.setFecha_ini(fecha.getFecha());
			fecha = fechas.get(0);
			bean.setFecha_fin(fecha.getFecha());
			
			bean.setEstado(3L);
			myService.save(bean);
			
			setMessageSuccess("Se Public� la Matricula.");
			defaultList();
		}
		
	}
	
	
	
	@Override
	public void afterNew() throws Exception {
		enabled=false;

    	//Familia tempFamilia = new Familia();
    	//familiaList = getListSelectItem(tempFamilia, "id", "nombre",true);
    	
    	CetproMatricula beanMatricula= (CetproMatricula)getBean();
    	beanMatricula.setPk_familia(familia);
    	
    	selectModulo();
    	
	}
	
	
	@Override
	public void afterLoad() throws Exception {
		enabled=true;
		nuevasFechas=false;
		listFechas=new ArrayList<Fecha>();

    	CetproMatricula beanMatricula= (CetproMatricula)getBean();
    	listaAlumnos=myService.listarAlumnosMatricula(beanMatricula.getId());
    	//selectModulo();
    	llenarDias();
    	llenarFechas();
    	optionCriterios();

    	beanMatricula=null;
	}
	
	
	public void goDetail() throws Exception {
		enabled=true;
		nuevasFechas=false;
		listFechas=new ArrayList<Fecha>();

    	CetproMatricula beanMatricula= (CetproMatricula)getBeanSelected();
    	setBean(beanMatricula);
    	listaAlumnos=myService.listarAlumnosMatricula(beanMatricula.getId());
    	//selectModulo();
    	llenarDias();
    	llenarFechas();
    	
    	forward("cetpro_matricula_detail");

    	beanMatricula=null;
	}
	
	public void guardarMatriculaCetpro() throws Exception{
		CetproMatricula bean = (CetproMatricula)getBean();
						
		if(validateUnidad())
		{
			bean.setAnno(annio);
			bean.setEstado(1L);
			getService().save(bean);
			setMessageSuccess("Se registr� la unidad satisfactoriamente");
			
			defaultList();
			forward("cetpro_matricula_list");
		}
	}
	
	public void updateMatriculaCetpro() throws Exception
	{
		CetproMatricula bean= (CetproMatricula)getBean();
		CetproMatriculaFecha matriculaFecha;
		
		if(listFechas.size()==0)
		{setMessageError("No ha generado fechas de las clases.");}
		else if(bean.getEstado()==1L)
		{		
			bean.setEstado(2L);
			myService.save(bean);
			
	    	for (Fecha item: listFechas) 
	    	{
	    		matriculaFecha =new CetproMatriculaFecha();
	    		matriculaFecha.setPk_cetpro_matricula(bean.getId());
	    		matriculaFecha.setFecha(item.getFechaListada());
	        	matriculaFecha.setEstado(1L);
				myService.save(matriculaFecha);
			}
	    	    	
	    	listFechas=new ArrayList<Fecha>();
	    	
	    	setMessageSuccess("Se actualiz� el m�dulo satisfactoriamente");
	    	defaultList();
			forward("cetpro_matricula_list");
		}
		else if(bean.getEstado()==2L && nuevasFechas==true)
		{
			myService.deleteByField(new CetproMatriculaFecha(), "pk_cetpro_matricula",bean.getId().toString());
			
			for (Fecha item: listFechas) 
	    	{
	    		matriculaFecha =new CetproMatriculaFecha();
	    		matriculaFecha.setPk_cetpro_matricula(bean.getId());
	    		matriculaFecha.setFecha(item.getFechaListada());
	        	matriculaFecha.setEstado(1L);
				myService.save(matriculaFecha);
			}
	    	    	
	    	listFechas=new ArrayList<Fecha>();
	    	
	    	setMessageSuccess("Se actualiz� el m�dulo satisfactoriamente");
	    	defaultList();
			forward("cetpro_matricula_list");
		}
		else
		{
			myService.save(bean);
			setMessageSuccess("Se actualiz� el m�dulo satisfactoriamente");
	    	defaultList();
			forward("cetpro_matricula_list");
		}
    	
		bean=null;
		matriculaFecha=null;
	}
	
	
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
		
		Familia tempFamilia = new Familia();
    	familiaList = getListSelectItem(tempFamilia, "id", "nombre",true);
    	
    	defaultList();
	}
	
	public void selectModulo() throws Exception
	{
		Profesion tempprofesion = new Profesion();
		tempprofesion.setFamilia(familia);
		List<Profesion>  profesiones = myService.listByObject(tempprofesion);
		List<ReferenteEducativo> beanReferenteList = null ;
		ReferenteEducativo beanReferente;
		
		List<ReferenteEducativo> listaAnidada = new ArrayList<ReferenteEducativo>();
		 
		for (Profesion item: profesiones) 
		{
			beanReferente = new ReferenteEducativo();
			
			beanReferenteList = new ArrayList<ReferenteEducativo>();
			beanReferente.setProfesion(item.getId());
			beanReferente.setNivelB(0L);
			beanReferente.setNivelC(0L);
			
			listaAnidada.addAll(myService.listByObject(beanReferente));
			
		}
		moduloList =  getListSelectItem(listaAnidada, "id", "titulo",true);
		
    
    
	}
	
	public void addAlumno() throws Exception
	{
		CetproMatricula bean=(CetproMatricula)getBean();
		if(validarAlumno(interesado, bean.getId()))
		{
			myService.actualizarMatriculaCetpro(true, bean.getId(), interesado);
			listaAlumnos=myService.listarAlumnosMatricula(bean.getId());
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
    	
    	if(list.size()==0)	{return true;}
    	else					{return false;}
    	    	
	}
	
	public void subAlumno() throws Exception
	{
		CetproMatricula bean=(CetproMatricula)getBean();
		myService.actualizarMatriculaCetpro(false, bean.getId(), interesado);
		listaAlumnos=myService.listarAlumnosMatricula(bean.getId());
		setMessageSuccess("La matricula de alumno fue eliminada del m�dulo exit�samente.");
		bean=null;
	}
	
	public void optionCriterios() throws Exception
	{
		CetproMatricula bean=(CetproMatricula)getBean();
		//ReferenteEducativo ref = new ReferenteEducativo();
		//ref.setId(bean.getPk_modulo());
		
		List<ReferenteEducativo> educativoList=myService.listarReferenteEducativo(bean.getProfesion(), 0, 1L);
		criteriosList=new ArrayList<ReferenteEducativo>();
		
		for(int i=0; i<educativoList.size(); i++)
		{
			if(educativoList.get(i).getTipo().longValue()==1L && educativoList.get(i).getNivelA().longValue()==bean.getModulo()  && educativoList.get(i).getNivelC().longValue()==0L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{criteriosList.add(educativoList.get(i));	}
		}
		educativoList=null;
		
		//filtrarModulo(criteriosList,bean.getProfesion());
		
		forward("cetproDocenteListCt");
	}
	
	public void filtrarModulo(List<ReferenteEducativo> educativoList, Long profesion) throws Exception
	{
		
		ArrayList<ReferenteEducativo> filtro=new ArrayList<ReferenteEducativo>();	
		for (ReferenteEducativo item : educativoList) 
		{
			if(item.getNivelA() == modulo)  //diego : verificar esta validacion despues
			{filtro.add(item);}						
		}
		
		criteriosList=filtro;
	}
	
	public void guardarCT() throws Exception {
		
		CetproMatricula bean=(CetproMatricula)getBean();
		CetproCt ct = new CetproCt();
		
		myService.deleteByField(ct , "pk_cetpro_matricula", bean.getId().toString());
		
		try {
			for (ReferenteEducativo item : criteriosList) {
				if(item.getCheck()){
					ct = new CetproCt();
					ct.setPk_cetpro_matricula(bean.getId());
					ct.setPk_ct(item.getId());
					ct.setPrioridad(1L);
					ct.setEstado(1L);
					myService.save(ct);
				}
			}
			
			
			//status(cetproMatricula, 2L);
			setMessageSuccess("Las Capacidades Terminales se guardaron exit�samente.");
		} catch (Exception e) {
			
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
	
	public boolean validateCapacidades() throws Exception
	{
		CetproMatricula object = (CetproMatricula)getBeanSelected();
		CetproCt ct = new CetproCt();
		List<CetproCt> ctList = new ArrayList<CetproCt>();
		ct.setPk_cetpro_matricula(object.getId());
		
		ctList = myService.listByObject(ct);
		
		if(ctList.size()==0)
		{
			setMessageError("Debe seleccionar al menos una Capacidad Terminal a evaluar.");
			return false;
		} 
		else
		{
			return true;
		}
		
	}
	
	private void llenarFechas() throws Exception
	{
		CetproMatricula bean=(CetproMatricula)getBeanSelected();
		
		CetproMatriculaFecha matriculaFecha = new CetproMatriculaFecha();
		matriculaFecha.setPk_cetpro_matricula(bean.getId());
		
    	List<CetproMatriculaFecha> fechas = myService.listByObjectEnabled(matriculaFecha);
    	
    	Fecha fecha;
    	
    	for (CetproMatriculaFecha item: fechas) 
		{
			fecha = new Fecha();			
			fecha.setFechaListada(item.getFecha());			
			listFechas.add(fecha);			
		}
    	
    	bean=null;
    	matriculaFecha=null;
    	fechas=null;
	}
	
	@Override
	public void nativeRemove() throws Exception {
		status(getBeanSelected(), 0L);
		setMessageSuccess("El registro ha sido eliminado.");
		defaultList();
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
		CetproMatricula bean= (CetproMatricula)getBeanSelected();
		listFechas = new ArrayList<Fecha>();
		obtenerListaFechasxDia();
		ordernarYRemoverFechasNoValidas();
		
		if(bean.getEstado()==2L)
		{nuevasFechas=true;}
		
		bean=null;
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

	public AdmisionService getMyService() {
		return myService;
	}

	public void setMyService(AdmisionService myService) {
		this.myService = myService;
	}

	public List<Requisitos> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<Requisitos> requisitos) {
		this.requisitos = requisitos;
	}

	public List<Itinerario> getItinerario() {
		return itinerario;
	}

	public void setItinerario(List<Itinerario> itinerario) {
		this.itinerario = itinerario;
	}

	public List<MatriculaSeccion> getMatriculaList() {
		return matriculaList;
	}

	public void setMatriculaList(List<MatriculaSeccion> matriculaList) {
		this.matriculaList = matriculaList;
	}

	public List<SelectItem> getFamiliaList() {
		return familiaList;
	}

	public void setFamiliaList(List<SelectItem> familiaList) {
		this.familiaList = familiaList;
	}

	public List<SelectItem> getModuloList() {
		return moduloList;
	}

	public void setModuloList(List<SelectItem> moduloList) {
		this.moduloList = moduloList;
	}

	public List<SelectItem> getInteresadoList() {
		return interesadoList;
	}

	public void setInteresadoList(List<SelectItem> interesadoList) {
		this.interesadoList = interesadoList;
	}

	public List<SelectItem> getDocenteList() {
		return docenteList;
	}

	public void setDocenteList(List<SelectItem> docenteList) {
		this.docenteList = docenteList;
	}

	public List<Oferta> getProfesiones() {
		return profesiones;
	}

	public void setProfesiones(List<Oferta> profesiones) {
		this.profesiones = profesiones;
	}

	public List<CetproMatriculaAlumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<CetproMatriculaAlumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getProceso() {
		return proceso;
	}

	public void setProceso(Long proceso) {
		this.proceso = proceso;
	}

	public Long getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Long institucion) {
		this.institucion = institucion;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public Long getFamilia() {
		return familia;
	}

	public void setFamilia(Long familia) {
		this.familia = familia;
	}

	public Long getUnidad() {
		return unidad;
	}

	public void setUnidad(Long unidad) {
		this.unidad = unidad;
	}

	public Long getSeccion() {
		return seccion;
	}

	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}

	public Long getAnnio() {
		return annio;
	}

	public void setAnnio(Long annio) {
		this.annio = annio;
	}

	public Long getTurno() {
		return turno;
	}

	public void setTurno(Long turno) {
		this.turno = turno;
	}

	public Long getInteresado() {
		return interesado;
	}

	public void setInteresado(Long interesado) {
		this.interesado = interesado;
	}

	public Long getDocente() {
		return docente;
	}

	public void setDocente(Long docente) {
		this.docente = docente;
	}

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

	public MatriculaSeccion getSelectSeccion() {
		return selectSeccion;
	}

	public void setSelectSeccion(MatriculaSeccion selectSeccion) {
		this.selectSeccion = selectSeccion;
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

	public Long getModulo() {
		return modulo;
	}

	public void setModulo(Long modulo) {
		this.modulo = modulo;
	}

	public List<ReferenteEducativo> getCriteriosList() {
		return criteriosList;
	}

	public void setCriteriosList(List<ReferenteEducativo> criteriosList) {
		this.criteriosList = criteriosList;
	}

	
	

	
	
} 
