package modules.intranet.controller; 
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

public class DocenteSilaboAsistenciaListFecha extends GenericController   
{	
	private IntranetService	myService;
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
	
	private List <SilaboAlumno> listSilaboAlumno; 
	
	private SilaboCronograma obtenerSilaboCronograma;
	public void init(Seccion beanSelected, Proceso proceso, SilaboCronograma pobtenerSilaboCronograma) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Sílabo";
		userName=usr.getUsuario();
		seccion=1L;
		modulo=1L;
		profesion=101L;
		nombreUnidad= "Prueba";
		page_main="DocenteSilaboAsistenciaListFecha";
				
		
		
		this.proceso = proceso;
		meta=beanSelected.getMeta();
		seccion=beanSelected.getId();
		docente=beanSelected.getDocente();
		unidad=beanSelected.getValorUnidad();
		
		defaultList();
		forward(page_main);
	}
		
	@Override
	public void defaultList() throws Exception
	{

		listScro =new ArrayList<>();
		SilaboCronograma scro = new SilaboCronograma();
		scro.setPk_docente(docente);
		scro.setPk_meta(meta);
		scro.setPk_seccion(seccion);
		scro.setPk_unidad(unidad);
		
		scro = (SilaboCronograma) myService.findByObject(scro);
		
		SilaboCalendario scal = new SilaboCalendario();
		scal.setPk_silabo_cronograma(scro.getId());

		listCal =  myService.listByObject(scal);
				
		
	}
	
	public void goAlumno()throws Exception
	{
		AsistenciaAlumnoCalendario aac = new AsistenciaAlumnoCalendario();
		SilaboCalendario temporalCalendario = (SilaboCalendario)getBeanSelected();
		
		aac.setPk_silabo_calendario(temporalCalendario.getId());
		
		forward("DocenteSilaboAsistenciaListaAlumno");
		listarAlumnos();
	}
	
	
	@SuppressWarnings("unchecked")
	public void listarAlumnos() throws Exception{
		SilaboCalendario temporalCalendario = (SilaboCalendario)getBeanSelected();
		SilaboAlumno silaboAlumno = new SilaboAlumno();
		silaboAlumno.setPk_silabo_cronograma(temporalCalendario.getPk_silabo_cronograma());
		listSilaboAlumno = myService.listByObject(silaboAlumno);
		
		for (SilaboAlumno item : listSilaboAlumno) {
			Persona p = new Persona();
			p.setId(item.getPk_alumno());
			p = (Persona)myService.findById(p);
			item.setNombre(p.getNombreCompleto());
			
		}
		
	}
	
	public void guardarAsistencia() throws ServiceException, DaoException{
		
		AsistenciaAlumnoCalendario asistenciaAlumnoCalendario = new AsistenciaAlumnoCalendario();
		
		
		SilaboCalendario temporalCalendario = (SilaboCalendario)getBeanSelected();		
		
		
		for (SilaboAlumno item : listSilaboAlumno) {
		
			asistenciaAlumnoCalendario = new AsistenciaAlumnoCalendario();
					
			temporalCalendario = (SilaboCalendario)getBeanSelected();
			
			asistenciaAlumnoCalendario.setPk_silabo_calendario(temporalCalendario.getId());
			asistenciaAlumnoCalendario.setAsistencia(item.getAsistio());
			asistenciaAlumnoCalendario.setEstado(1L);
			asistenciaAlumnoCalendario.setPk_silabo_alumno(item.getId());
			
			
			
			myService.save(asistenciaAlumnoCalendario);
			
		}
	
		//cerrar la fecha
		temporalCalendario.setEstado(2L);
		myService.save(temporalCalendario);
		
		forward("DocenteSilaboAsistenciaListaFecha");
		
		
		
		
	}

	
	
	
	public IntranetService getMyService() 													{return myService;}
	public void setMyService(IntranetService myService) 									{this.myService = myService;}

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



	public List<SilaboAlumno> getListSilaboAlumno() {
		return listSilaboAlumno;
	}



	public void setListSilaboAlumno(List<SilaboAlumno> listSilaboAlumno) {
		this.listSilaboAlumno = listSilaboAlumno;
	}



	public SilaboCronograma getObtenerSilaboCronograma() {
		return obtenerSilaboCronograma;
	}



	public void setObtenerSilaboCronograma(SilaboCronograma obtenerSilaboCronograma) {
		this.obtenerSilaboCronograma = obtenerSilaboCronograma;
	}





} 
