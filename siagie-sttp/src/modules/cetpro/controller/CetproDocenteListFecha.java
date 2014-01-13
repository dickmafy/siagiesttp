package modules.cetpro.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.catalina.Session;
import org.hibernate.impl.CriteriaImpl;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.support.DaoException;
import com.belogick.factory.util.support.ServiceException;

import dataware.service.AdmisionService;
import dataware.service.IntranetService;
import dataware.service.MarcoService;
import modules.administracion.domain.Institucion;
import modules.admision.domain.Matricula;
import modules.admision.domain.Persona;
import modules.admision.domain.Proceso;
import modules.cetpro.domain.CetproMatricula;
import modules.cetpro.domain.CetproMatriculaAlumno;
import modules.cetpro.domain.CetproMatriculaFecha;
import modules.cetpro.domain.CetproAsistencia;
import modules.horario.domain.AsistenciaAlumno;
import modules.horario.domain.AsistenciaAlumnoCalendario;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboAlumno;
import modules.horario.domain.SilaboCalendario;
import modules.horario.domain.SilaboCronograma;
import modules.intranet.domain.AsistenciaWrapper;
import modules.intranet.domain.Fecha;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class CetproDocenteListFecha extends GenericController   
{	
	private AdmisionService	myService;
	private List<ReferenteEducativo> criteriosList;
	
	private List<SelectItem> moduloProfesionalList;
	private List<SelectItem> capacidadProfesionalList;
	
	private List<SelectItem> moduloTransversalList;
	private List<SelectItem> capacidadTransversalList;
	
	private Long tipo=0L,seccion,modulo,profesion;
	private String nombreUnidad;
	
	private AdmisionService myServiceAdmision;
	private Proceso proceso;
	
	private List<SilaboCronograma> listScro;
	private List<SilaboCalendario> listCal;
	private Long meta,docente,unidad;
	private List<Matricula> matriculas;
	private AsistenciaWrapper asistenciaWrapper;
	
	private List <CetproMatriculaAlumno> listSilaboAlumno; 
	
	private SilaboCronograma obtenerSilaboCronograma;
	private CetproMatricula cetproMatricula;
    
	public void init(CetproMatricula pcetproMatricula) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Sílabo";
		userName=usr.getUsuario();
		seccion=1L;
		modulo=1L;
		profesion=101L;
		nombreUnidad= "Prueba";
		page_main="cetproDocenteListFecha";
				
		
		cetproMatricula = pcetproMatricula;
		this.proceso = proceso;
		

		
		defaultList();
		forward(page_main);
	}
		
	@Override
	public void defaultList() throws Exception
	{
		CetproMatriculaFecha scal = new CetproMatriculaFecha();
		scal.setPk_cetpro_matricula(cetproMatricula.getId());

		setBeanList(myService.listByObject(scal));
				
	}
	
	public void goAlumno()throws Exception
	{
		CetproAsistencia aac = new CetproAsistencia();
		CetproMatriculaFecha temporalCalendario = (CetproMatriculaFecha)getBeanSelected();
		
		aac.setPk_cetpro_matricula_fecha(temporalCalendario.getId());
		
		
		listarAlumnos();
		
		forward("cetproDocenteListFechaAlumno");
	}
	
	public void goDetail()throws Exception
	{
		CetproAsistencia aac = new CetproAsistencia();
		CetproMatriculaFecha temporalCalendario = (CetproMatriculaFecha)getBeanSelected();
		
		aac.setPk_cetpro_matricula_fecha(temporalCalendario.getId());
		listarAlumnos();
		forward("cetproDocenteListFechaAlumnoDetail");		
	}
	
	
	@SuppressWarnings("unchecked")
	public void listarAlumnos() throws Exception{
		CetproMatriculaFecha temporalCalendario = (CetproMatriculaFecha)getBeanSelected();
		CetproMatriculaAlumno silaboAlumno = new CetproMatriculaAlumno();
		silaboAlumno.setPk_cetpro_matricula(temporalCalendario.getPk_cetpro_matricula());
		listSilaboAlumno = myService.listByObject(silaboAlumno);
		
		
		for (CetproMatriculaAlumno item : listSilaboAlumno) {
			CetproAsistencia asis=new CetproAsistencia();
			asis.setPk_cetpro_matricula_alumno(item.getId());
			asis.setPk_cetpro_matricula_fecha(temporalCalendario.getId());
			
			Persona p = new Persona();
			p.setId(item.getPk_persona());
			p = (Persona)myService.findById(p);
			item.setAlumno_apepat(p.getApellido_paterno());
			item.setAlumno_apemat(p.getApellido_materno());
			item.setAlumno_nom(p.getNombres());
			
			try {
				CetproAsistencia temp;
				temp=(CetproAsistencia)myService.findByObject(asis);
				item.setAsistio((long)temp.getAsistencia());
			} catch (Exception e) {
				item.setAsistio(1L);
			}
		}
		
	}
	
	public void guardarAsistencia() throws ServiceException, DaoException{
		
		CetproAsistencia asis;	
		CetproMatriculaFecha fecha = (CetproMatriculaFecha)getBeanSelected();			
		
		for (CetproMatriculaAlumno item : listSilaboAlumno) {
		
			asis = new CetproAsistencia();		
			asis.setPk_cetpro_matricula_alumno(item.getId());
			asis.setPk_cetpro_matricula_fecha(fecha.getId());
			asis.setAsistencia(item.getAsistio());
			asis.setEstado(1L);
			
			myService.save(asis);			
		}
	
		//cerrar la fecha
		fecha.setEstado(2L);
		myService.save(fecha);
		
		forward("cetproDocenteListFecha");
	
	}

	
	
	
	

	public List<ReferenteEducativo> getCriteriosList() 										{return criteriosList;}
	public void setCriteriosList(List<ReferenteEducativo> criteriosList) 					{this.criteriosList = criteriosList;}

	public String getNombreUnidad() 													{return nombreUnidad;}
	public void setNombreUnidad(String nombreUnidad) 									{this.nombreUnidad = nombreUnidad;}

	public List<SelectItem> getModuloProfesionalList() 									{return moduloProfesionalList;}
	public void setModuloProfesionalList(List<SelectItem> moduloProfesionalList) 		{this.moduloProfesionalList = moduloProfesionalList;}

	public List<SelectItem> getCapacidadProfesionalList() 								{return capacidadProfesionalList;}
	public void setCapacidadProfesionalList(List<SelectItem> capacidadProfesionalList) 	{this.capacidadProfesionalList = capacidadProfesionalList;}

	public List<SelectItem> getModuloTransversalList() 									{return moduloTransversalList;}
	public void setModuloTransversalList(List<SelectItem> moduloTransversalList) 		{this.moduloTransversalList = moduloTransversalList;}
	
	public List<SelectItem> getCapacidadTransversalList() 								{return capacidadTransversalList;}
	public void setCapacidadTransversalList(List<SelectItem> capacidadTransversalList) 	{this.capacidadTransversalList = capacidadTransversalList;}
	
	public Long getTipo() 																{return tipo;}
	public void setTipo(Long tipo) 														{this.tipo = tipo;}



	public AdmisionService getMyServiceAdmision() {
		return myServiceAdmision;
	}



	public void setMyServiceAdmision(AdmisionService myServiceAdmision) {
		this.myServiceAdmision = myServiceAdmision;
	}



	public List<SilaboCronograma> getListScro() {
		return listScro;
	}



	public void setListScro(List<SilaboCronograma> listScro) {
		this.listScro = listScro;
	}



	public List<SilaboCalendario> getListCal() {
		return listCal;
	}



	public void setListCal(List<SilaboCalendario> listCal) {
		this.listCal = listCal;
	}




	public SilaboCronograma getObtenerSilaboCronograma() {
		return obtenerSilaboCronograma;
	}



	public void setObtenerSilaboCronograma(SilaboCronograma obtenerSilaboCronograma) {
		this.obtenerSilaboCronograma = obtenerSilaboCronograma;
	}

	public AdmisionService getMyService() {
		return myService;
	}

	public void setMyService(AdmisionService myService) {
		this.myService = myService;
	}

	public Long getSeccion() {
		return seccion;
	}

	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}

	public Long getModulo() {
		return modulo;
	}

	public void setModulo(Long modulo) {
		this.modulo = modulo;
	}

	public Long getProfesion() {
		return profesion;
	}

	public void setProfesion(Long profesion) {
		this.profesion = profesion;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Long getMeta() {
		return meta;
	}

	public void setMeta(Long meta) {
		this.meta = meta;
	}

	public Long getDocente() {
		return docente;
	}

	public void setDocente(Long docente) {
		this.docente = docente;
	}

	public Long getUnidad() {
		return unidad;
	}

	public void setUnidad(Long unidad) {
		this.unidad = unidad;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public AsistenciaWrapper getAsistenciaWrapper() {
		return asistenciaWrapper;
	}

	public void setAsistenciaWrapper(AsistenciaWrapper asistenciaWrapper) {
		this.asistenciaWrapper = asistenciaWrapper;
	}

	public CetproMatricula getCetproMatricula() {
		return cetproMatricula;
	}

	public void setCetproMatricula(CetproMatricula cetproMatricula) {
		this.cetproMatricula = cetproMatricula;
	}

	public List<CetproMatriculaAlumno> getListSilaboAlumno() {
		return listSilaboAlumno;
	}

	public void setListSilaboAlumno(List<CetproMatriculaAlumno> listSilaboAlumno) {
		this.listSilaboAlumno = listSilaboAlumno;
	}

	





} 
