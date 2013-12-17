package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.AdmisionService;
import dataware.service.IntranetService;
import modules.admision.domain.Matricula;
import modules.admision.domain.Proceso;
import modules.horario.domain.Seccion;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class IntranetDocenteNotas extends GenericController   
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
	
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Silabo";
		userName=usr.getUsuario();
		seccion=1L;
		modulo=1L;
		profesion=101L;
		nombreUnidad= "Prueba";
		page_new="IntranetDocenteNotas_new";
		page_update="IntranetDocenteNotas_update";
		page_main="IntranetDocenteNotas_list";
		defaultList();		
		//forward(page_main);
		optionCriterios();
	}
	
	public void init(Seccion pseccion,Proceso proceso) throws Exception 
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

		page_main="IntranetDocenteNotasAlumno_list";
		numbCapTerminales = 3;
		defaultList();		
		forward(page_main);
		//optionCriterios();
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
		
		forward("IntranetDocenteNotas_list");
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
		
		List<Matricula> matriculas = myServiceAdmision.listarMatricula(proceso.getId());
		notas = new int[matriculas.size()][numbCapTerminales];
		setBeanList(matriculas);
		
		
	}

	public void listarCT() {
		 criteriosListCt=new ArrayList<ReferenteEducativo>();
		for (ReferenteEducativo x: criteriosList) {
			if(x.getCheck()){
				
				ReferenteEducativo re=new ReferenteEducativo();
				re=x;
				criteriosListCt.add(re);
			}
		}
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		ReferenteEducativo object = (ReferenteEducativo)getBean();
		if(tipo.longValue()>2L && !validateSelect(object.getNivelA()))
		{
			setMessageError("Debe seleccionar una Modulo.");			
			success = false;
		}
		else if(tipo.longValue()==4L && !validateSelect(object.getNivelB()))
		{
			setMessageError("Debe seleccionar una Capacidad Terminal.");			
			success = false;
		}
		else if(!validateEmpty(object.getTitulo()) && tipo.longValue()==1L)
		{
			setMessageError("Debe ingresar el T�tulo.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la Descripcion.");			
			success = false;
		}
		object=null;
		return success;
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

	
	
	
} 
