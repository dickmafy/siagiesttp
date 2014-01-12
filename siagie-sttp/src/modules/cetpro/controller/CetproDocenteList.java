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
import modules.cetpro.domain.CetproMatricula;
import modules.cetpro.domain.CetproMatriculaAlumno;
import modules.cetpro.domain.CetproMatriculaFecha;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCalendario;
import modules.horario.domain.SilaboCronograma;
import modules.intranet.controller.DocenteSilaboCT;
import modules.intranet.controller.DocenteSilaboNota;
import modules.intranet.domain.Fecha;
import modules.mantenimiento.domain.Banco;
import modules.marco.controller.ProfesionProductivo;
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
public class CetproDocenteList extends GenericController   
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
	private Long modulo;

	
	public void init(Long id) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Matricula";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		docente=usr.getPertenencia();
		proceso=id;
		//annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");	
		annio=-1L;
		familia=-1L;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		//requisitos = new ArrayList();
		//procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
		
		enabled=false;
		
		page_new="cetproDocenteList";
		page_main="cetproDocenteList";
		page_update="cetproDocenteList";	
	
		
		forward(page_main);
			
		defaultList();
    	
	}

	public void init() throws Exception
	{init(-1L);}
	
	public void goPublicar() throws Exception
	{
		CetproMatricula bean = (CetproMatricula)getBeanSelected();
		bean = (CetproMatricula) myService.findByObject(bean);
		bean.setEstado(3L);
		myService.save(bean);
		setMessageSuccess("Se Publico la Matricula.");
		
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
    	
    	//Familia tempFamilia = new Familia();
    	//familiaList = getListSelectItem(tempFamilia, "id", "nombre",true);
    	
    	CetproMatricula beanMatricula= (CetproMatricula)getBean();
    	beanMatricula.setPk_familia(familia);
    	
    	selectModulo();
    	
    	obj=null;
    	bean=null;
	}
	
	

	public void goAsistencia() throws Exception 
	{
		CetproMatricula bean = (CetproMatricula)getBeanSelected();	
		CetproDocenteListFecha go = (CetproDocenteListFecha)getSpringBean("cetproDocenteListFecha");
		go.init(bean);
		
		
		
	}
	
	public void goNota()throws Exception{
		
		
		
		SilaboCronograma silaboCronograma = obtenerSilaboCronograma();    	
		silaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
		
		DocenteSilaboNota go = (DocenteSilaboNota)getSpringBean("docenteSilaboNota");
		Proceso proceso = new Proceso();
		proceso.setAnnio(annio);
		proceso.setProceso(this.proceso);
		proceso = (Proceso) myService.findByObject(proceso);
		go.init((Seccion)getBeanSelected(),proceso,silaboCronograma);
	}
	
	private SilaboCronograma obtenerSilaboCronograma() {
		Seccion bean = (Seccion)getBeanSelected();
		SilaboCronograma silaboCronograma =new SilaboCronograma();
		
		silaboCronograma.setPk_meta(bean.getMeta());
    	silaboCronograma.setContenido("-");
    	silaboCronograma.setPk_unidad(bean.getValorUnidad());
    	silaboCronograma.setPk_seccion(bean.getId());
    	silaboCronograma.setPk_docente(bean.getDocente());
    	silaboCronograma.setEstado(bean.getEstadoSilabo());
		return silaboCronograma;
	}
	
	public void goCt()throws Exception{
		
//		seccion=pseccion.getId();
//		docente=pseccion.getDocente();
//		meta=pseccion.getMeta();
//		unidad=pseccion.getValorUnidad();
//		
		//en la seccion esta el silabocronograma id
		CetproMatricula silaboCronograma =new CetproMatricula();
    	silaboCronograma.setModulo(unidad);
    	silaboCronograma.setPk_docente(docente);
    	silaboCronograma.setEstado(1L);
    	
    	CetproMatricula obtenerSilaboCronograma = (CetproMatricula) myService.findByObject(silaboCronograma);
    	
    	
		CetproDocenteListCt go = (CetproDocenteListCt)getSpringBean("cetproDocenteListCt");
		
		go.init(new Seccion(),new Proceso(),obtenerSilaboCronograma);
	}
	
		
	public void defaultList() throws Exception
	{	
		if(annio.longValue()>0L)
		{setBeanList(myService.listarUnidadesDocenteAlumno(annio, 1L, docente));}
		else
		{setBeanList(new ArrayList<CetproMatricula>());}
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

	
	

	
	
} 
