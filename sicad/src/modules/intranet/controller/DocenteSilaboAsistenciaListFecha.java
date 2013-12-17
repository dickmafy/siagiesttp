package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.catalina.Session;
import org.hibernate.impl.CriteriaImpl;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.AdmisionService;
import dataware.service.IntranetService;
import dataware.service.MarcoService;
import modules.administracion.domain.Institucion;
import modules.admision.domain.Matricula;
import modules.admision.domain.Proceso;
import modules.horario.domain.AsistenciaAlumnoCalendario;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboAlumno;
import modules.horario.domain.SilaboCalendario;
import modules.horario.domain.SilaboCronograma;
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
	

	
	public void init() throws Exception 
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
		defaultList();		
		forward(page_main);
		
	}
	


	public void init(Seccion beanSelected, Proceso proceso) throws Exception {
		this.proceso = proceso;
		init();
		
			
	}
	
	@Override
	public void defaultList() throws Exception
	{
		
		
		
		
		
		listScro =new ArrayList<>();
		SilaboCronograma scro = new SilaboCronograma();
		scro.setPk_docente(27L);
		scro.setPk_meta(1L);
		scro.setPk_seccion(5L);
		scro.setPk_unidad(5L);
		
		scro = (SilaboCronograma) myService.findByObject(scro);
		
		SilaboCalendario scal = new SilaboCalendario();
		scal.setPk_silabo_cronograma(scro.getId());
		listCal =  myService.listByObject(scal);
				
		
		
				
		
		
	}
	
	public void goAlumno()throws Exception
	{
		DocenteSilaboAsistenciaListaAlumno go = (DocenteSilaboAsistenciaListaAlumno)getSpringBean("docenteSilaboAsistenciaListaAlumno");
		
		AsistenciaAlumnoCalendario aac = new AsistenciaAlumnoCalendario();
		SilaboCalendario temporalCalendario = (SilaboCalendario)getBeanSelected();
		
		aac.setPk_silabo_calendario(temporalCalendario.getId());
		go.init(temporalCalendario);
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





} 
