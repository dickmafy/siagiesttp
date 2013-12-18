package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import com.aprolab.sicad.persistence.JPAPersistenceUtil;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.AdmisionService;
import dataware.service.IntranetService;
import modules.admision.domain.Matricula;
import modules.admision.domain.Proceso;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCronograma;
import modules.horario.domain.SilaboUnidadCt;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;
import modules.horario.domain.SilaboNotaAlumno;
import modules.horario.servicio.PersonaAlumno;
import modules.horario.servicio.PersonaAlumno;
import modules.horario.servicio.SilaboNotaAlumnoServicioLocal;

public class DocenteSilaboNota extends GenericController   
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
	
	private int notas[][];
	
	private int numbCapTerminales;
	private SilaboCronograma obtenerSilaboCronograma;
	
	private Long pk_unidad_ctSeleccionado;
	private List<ReferenteEducativo> listarCT;
	private SilaboCronograma silaboCronograma;
	public void init(Seccion pseccion,Proceso proceso,SilaboCronograma pobtenerSilaboCronograma) throws Exception 
	{
		
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Notas";
		userName=usr.getUsuario();
		meta=pseccion.getMeta();
		seccion=pseccion.getId();
		docente=pseccion.getDocente();
		unidad=pseccion.getValorUnidad();
		modulo=1L;
		profesion=101L;
		nombreUnidad= "Prueba";
		this.proceso = proceso;
		silaboCronograma = pobtenerSilaboCronograma;
		
		
		page_main="DocenteSilaboNota";
		this.obtenerSilaboCronograma = pobtenerSilaboCronograma;
		
				
		pk_unidad_ctSeleccionado = 36L;
		
		forward(page_main);
		defaultList();
		//optionCriterios();
		SilaboUnidadCt silaboUnidadCt = new SilaboUnidadCt();
    	silaboUnidadCt.setPk_silabo_cronograma(silaboCronograma.getId());
		listarCT = myService.listByObject(silaboUnidadCt);
		
		
	}
	
	
	@Override
	public void defaultList() throws Exception
	{	
		setBeanList(SilaboNotaAlumnoServicioLocal.findBySilaboCronograma(obtenerSilaboCronograma.getId(),pk_unidad_ctSeleccionado));
	}
	
	public void guardarNotas()  throws Exception {
		
		List<PersonaAlumno> matriculados = (List<PersonaAlumno>)getBeanList();
		
		for (PersonaAlumno personaAlumno : matriculados) {
			SilaboNotaAlumno result = SilaboNotaAlumnoServicioLocal
					.getSilaboNotaAlumno(personaAlumno.getSilaboAlumno().getId(), pk_unidad_ctSeleccionado);
			
			JPAPersistenceUtil.getSession().save(result);
			result=null;
		}
		forward("DocenteSilaboList");
		
	}

//	public void listarCT() {
//		 criteriosListCt=new ArrayList<ReferenteEducativo>();
//		for (ReferenteEducativo x: criteriosList) {
//			if(x.getCheck()){
//				
//				ReferenteEducativo re=new ReferenteEducativo();
//				re=x;
//				criteriosListCt.add(re);
//			}
//		}
//	}
	
	
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

	public int[][] getNotas() {
		return notas;
	}

	public void setNotas(int[][] notas) {
		this.notas = notas;
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






	public List<ReferenteEducativo> getListarCT() {
		return listarCT;
	}






	public void setListarCT(List<ReferenteEducativo> listarCT) {
		this.listarCT = listarCT;
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

	

	
	
	
} 
