package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.support.ServiceException;

import dataware.service.AdmisionService;
import dataware.service.IntranetService;
import modules.admision.domain.Proceso;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCronograma;
import modules.horario.domain.SilaboNotaAlumno;
import modules.horario.domain.SilaboUnidadCt;
import modules.horario.servicio.PersonaAlumno;
import modules.horario.servicio.SilaboNotaAlumnoServicioLocal;
import modules.intranet.servicio.VistaReferenteEducativo;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class AlumnoNota extends GenericController   
{
	private IntranetService	myService;
	private AdmisionService myServiceAdmision;
	
	private List<ReferenteEducativo> criteriosList;
	
	private List<ReferenteEducativo> criteriosListCt;
	private List<String> selectCapacidades;
	
	private List<SelectItem> moduloProfesionalList;
	private List<SelectItem> capacidadProfesionalList;
	
	private List<SelectItem> moduloTransversalList;
	private List<SelectItem> capacidadTransversalList;
	
	private Long tipo=0L,seccion,modulo,profesion,meta,docente,unidad;
	private Proceso proceso;
	private String nombreUnidad;
	
	private Seccion seccionObject;

	private SilaboCronograma obtenerSilaboCronograma;
	
	private Long pk_unidad_ctSeleccionado;
	
	private List<VistaReferenteEducativo> listarCT;
	
	private SilaboCronograma silaboCronograma;
	private Long pk_pertenancia;
	public void init(Seccion pseccion,Proceso proceso,SilaboCronograma pobtenerSilaboCronograma) throws Exception 
	{
		
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Notas";
		userName=usr.getUsuario();
		pk_pertenancia =usr.getPertenencia();
		
		meta=pseccion.getMeta();
		seccion=pseccion.getId();
		docente=pseccion.getDocente();
		unidad=pseccion.getValorUnidad();
		modulo=1L;
		profesion=101L;
		nombreUnidad= "Prueba";
		this.proceso = proceso;
		silaboCronograma = pobtenerSilaboCronograma;
		
		
		page_main="AlumnoNota";
		this.obtenerSilaboCronograma = pobtenerSilaboCronograma;
		//optionCriterios();
		
		SilaboUnidadCt silaboUnidadCt = new SilaboUnidadCt();
    	silaboUnidadCt.setPk_silabo_cronograma(silaboCronograma.getId());		
		
    	List<SilaboUnidadCt> listaActual = myService.listByObject(silaboUnidadCt);
		
		pk_unidad_ctSeleccionado = -1l;
		if (listaActual.size()>0) {
			listarCT = new ArrayList<VistaReferenteEducativo>();
			pk_unidad_ctSeleccionado = listaActual.get(0).getId();
			ReferenteEducativo referenteEducativo; 
			for (SilaboUnidadCt siCt : listaActual) {
				referenteEducativo = (ReferenteEducativo) 
						myService.findById(ReferenteEducativo.class, siCt.getPk_ct());
				listarCT.add(new VistaReferenteEducativo(siCt, referenteEducativo));
			}
		}
		
		
		forward(page_main);
		defaultList();
		
    	
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void defaultList() throws Exception
	{	
		setBeanList(SilaboNotaAlumnoServicioLocal.findBySilaboCronograma(obtenerSilaboCronograma.getId(),pk_unidad_ctSeleccionado));

		List<PersonaAlumno> listaPersonaAlumnos = new ArrayList<PersonaAlumno>();
		List<PersonaAlumno> temporalListaPersonaAlumnos = new ArrayList<PersonaAlumno>();
		listaPersonaAlumnos = getBeanList();
			 
			Iterator<PersonaAlumno> it = listaPersonaAlumnos.iterator();
			while (it.hasNext()) {
				PersonaAlumno value = it.next();
				if (value.getPersona().getId().equals(pk_pertenancia)) {
					temporalListaPersonaAlumnos.add(value);
				}
			}
	
		setBeanList(temporalListaPersonaAlumnos);
		
	}
	
	public void reloadNotas() throws ServiceException{
		
		List<PersonaAlumno> matriculados = (List<PersonaAlumno>)getBeanList();
		
		for (PersonaAlumno personaAlumno : matriculados) {
			
			SilaboNotaAlumno result = SilaboNotaAlumnoServicioLocal
					.getSilaboNotaAlumno(personaAlumno.getSilaboAlumno().getId(), pk_unidad_ctSeleccionado);
			personaAlumno.setNota(result.getNota());
			result=null;
		}
	}
	
	public void guardarNotas()  throws Exception {
		
		List<PersonaAlumno> matriculados = (List<PersonaAlumno>)getBeanList();
		
		for (PersonaAlumno personaAlumno : matriculados) {
			
			SilaboNotaAlumno result = SilaboNotaAlumnoServicioLocal
					.getSilaboNotaAlumno(personaAlumno.getSilaboAlumno().getId(), pk_unidad_ctSeleccionado);
			
			result.setNota(personaAlumno.getNota());
			myService.save(result);
			result=null;
		}
		
		forward("DocenteSilaboList");
		
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

	public Seccion getSeccionObject() {
		return seccionObject;
	}

	public void setSeccionObject(Seccion seccionObject) {
		this.seccionObject = seccionObject;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	
	public List<String> getSelectCapacidades() {
		return selectCapacidades;
	}

	public void setSelectCapacidades(List<String> selectCapacidades) {
		this.selectCapacidades = selectCapacidades;
	}

	public List<ReferenteEducativo> getCriteriosListCt() {
		return criteriosListCt;
	}

	public void setCriteriosListCt(List<ReferenteEducativo> criteriosListCt) {
		this.criteriosListCt = criteriosListCt;
	}

	public SilaboCronograma getObtenerSilaboCronograma() {
		return obtenerSilaboCronograma;
	}

	public void setObtenerSilaboCronograma(SilaboCronograma obtenerSilaboCronograma) {
		this.obtenerSilaboCronograma = obtenerSilaboCronograma;
	}

	public SilaboCronograma getSilaboCronograma() {
		return silaboCronograma;
	}

	public void setSilaboCronograma(SilaboCronograma silaboCronograma) {
		this.silaboCronograma = silaboCronograma;
	}


	public Long getPk_unidad_ctSeleccionado() {
		return pk_unidad_ctSeleccionado;
	}


	public void setPk_unidad_ctSeleccionado(Long pk_unidad_ctSeleccionado) {
		this.pk_unidad_ctSeleccionado = pk_unidad_ctSeleccionado;
	}


	public List<VistaReferenteEducativo> getListarCT() {
		return listarCT;
	}


	public void setListarCT(List<VistaReferenteEducativo> listarCT) {
		this.listarCT = listarCT;
	}


	public Long getPk_pertenancia() {
		return pk_pertenancia;
	}


	public void setPk_pertenancia(Long pk_pertenancia) {
		this.pk_pertenancia = pk_pertenancia;
	}

} 
