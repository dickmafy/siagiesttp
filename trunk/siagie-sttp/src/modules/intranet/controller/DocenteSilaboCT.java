package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.support.ServiceException;

import dataware.service.AdmisionService;
import dataware.service.IntranetService;
import modules.administracion.domain.MetaInstitucional;
import modules.admision.domain.Matricula;
import modules.admision.domain.Proceso;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCronograma;
import modules.horario.domain.SilaboUnidadCt;
import modules.intranet.domain.silabo_docente;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class DocenteSilaboCT extends GenericController   
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
	
	private Long tipo=0L,seccion,modulo,profesion,meta,docente,unidad,annio,process;
	private Proceso proceso;
	private String nombreUnidad;
	
	private Seccion seccionObject;
	
	private int notas[][];
	
	private int numbCapTerminales;
	private SilaboCronograma obtenerSilaboCronograma;

	
	public void init(Seccion pseccion,Proceso proceso, SilaboCronograma pobtenerSilaboCronograma) throws Exception 
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
		
		obtenerSilaboCronograma =  pobtenerSilaboCronograma;
		
		MetaInstitucional met = (MetaInstitucional) myService.findById(MetaInstitucional.class, meta);		
		annio=met.getAnnio();
		process=met.getProceso();
		
		page_main="DocenteSilaboCT";
		numbCapTerminales = 3;
		defaultList();		
		forward(page_main);
		optionCriterios();
	}
	
	public void optionCriterios() throws Exception
	{
		List<ReferenteEducativo> educativoList=myService.listarReferenteEducativo(profesion, 0, 1L);
		criteriosList=new ArrayList<ReferenteEducativo>();
		
		for(int i=0; i<educativoList.size(); i++)
		{
			if(educativoList.get(i).getTipo().longValue()==1L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{criteriosList.add(educativoList.get(i));	}
		}
		educativoList=null;
		
		filtrarModulo(criteriosList,modulo);
		
		forward("DocenteSilaboCT");
	}
	
	public void filtrarModulo(List<ReferenteEducativo> educativoList, Long modulo) throws Exception
	{
		ArrayList<ReferenteEducativo> filtro=new ArrayList<ReferenteEducativo>();	
		for (ReferenteEducativo item : educativoList) 
		{
			if(item.getNivelA() == modulo)
			{filtro.add(item);}						
		}
		
		criteriosList=filtro;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		/*Criteria criteria = JPAPersistenceUtil.getSession().createCriteria(Matricula.class);
		criteria.add(Expression.eq("estado",4))
		.add(Expression.eq("estado",seccionObject.));*/
		
		List<Matricula> matriculas = myService.listarAlumnosSeccion(meta, unidad, seccion, docente);
		notas = new int[matriculas.size()][numbCapTerminales];
		setBeanList(matriculas);
		
		
	}

	public void listarCT() {
		criteriosListCt=new ArrayList<ReferenteEducativo>();
		for (ReferenteEducativo check: criteriosList) {
			if(check.getCheck()){
				
				ReferenteEducativo re=new ReferenteEducativo();
				re=check;
				criteriosListCt.add(re);
			}
		}
	}
	
	
	
	public void guardarCT() throws Exception {
		
		for (ReferenteEducativo item : criteriosList) {
			if(item.getCheck()){
				SilaboUnidadCt ct = new SilaboUnidadCt();
				ct.setPk_silabo_cronograma(obtenerSilaboCronograma.getId());
				ct.setPk_ct(item.getId());
				ct.setPrioridad(1L);
				ct.setEstado(1L);
				myService.save(ct);
			}
		}
		
		SilaboCronograma bean = (SilaboCronograma)myService.findByObject(obtenerSilaboCronograma);
		bean.setEstado(2L);
		myService.save(bean); 
		
		DocenteSilaboList go = (DocenteSilaboList)getSpringBean("docenteSilaboList");
		go.init(annio,process);
		
		//forward("DocenteSilaboList");
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

	
	
	
} 
