package modules.cetpro.controller; 
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
import modules.cetpro.domain.CetproCt;
import modules.cetpro.domain.CetproMatricula;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCronograma;
import modules.horario.domain.SilaboUnidadCt;
import modules.intranet.domain.silabo_docente;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class CetproDocenteListCt extends GenericController   
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
	private CetproMatricula cetproMatricula;

	
	public void init(CetproMatricula pCetproMatricula) throws Exception 
	{
		
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Notas";
		userName=usr.getUsuario();
		docente = usr.getPertenencia();
		
	
		nombreUnidad= "Prueba";
		
		
		cetproMatricula =  pCetproMatricula;
		
				
		annio=pCetproMatricula.getAnno();
		
		page_main="cetproDocenteListCt";
		numbCapTerminales = 3;
		//defaultList();		
		forward(page_main);
		optionCriterios();
		listarCT();
	}
	
	public void optionCriterios() throws Exception
	{
		List<ReferenteEducativo> educativoList=myService.listarReferenteEducativo(cetproMatricula.getProfesion(), 0, 1L);
		criteriosList=new ArrayList<ReferenteEducativo>();
		
		for(int i=0; i<educativoList.size(); i++)
		{
			if(educativoList.get(i).getTipo().longValue()==1L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
			{criteriosList.add(educativoList.get(i));	}
		}
		educativoList=null;
		
		filtrarModulo(criteriosList,cetproMatricula.getPk_modulo());
		
		forward("cetproDocenteListCt");
	}
	
	public void filtrarModulo(List<ReferenteEducativo> educativoList, Long modulo) throws Exception
	{
		ArrayList<ReferenteEducativo> filtro=new ArrayList<ReferenteEducativo>();	
		for (ReferenteEducativo item : educativoList) 
		{
			//if(item.getNivelA() == modulo)  //diego : verificar esta validacion despues
			{filtro.add(item);}						
		}
		
		criteriosList=filtro;
	}
	
	@Override
	public void defaultList() throws Exception
	{
				
		
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
		
		try {
			for (ReferenteEducativo item : criteriosList) {
				if(item.getCheck()){
					CetproCt ct = new CetproCt();
					ct.setPk_cetpro_matricula(cetproMatricula.getId());
					ct.setPk_ct(item.getId());
					ct.setPrioridad(1L);
					ct.setEstado(1L);
					myService.save(ct);
				}
			}
			
			
			status(cetproMatricula, 2L);
			setMessageSuccess("Capacidades Guardadas.");
		} catch (Exception e) {
			
		}
		forward("cetproDocenteList");
	}

	public IntranetService getMyService() {
		return myService;
	}

	public void setMyService(IntranetService myService) {
		this.myService = myService;
	}

	public AdmisionService getMyServiceAdmision() {
		return myServiceAdmision;
	}

	public void setMyServiceAdmision(AdmisionService myServiceAdmision) {
		this.myServiceAdmision = myServiceAdmision;
	}

	public List<ReferenteEducativo> getCriteriosList() {
		return criteriosList;
	}

	public void setCriteriosList(List<ReferenteEducativo> criteriosList) {
		this.criteriosList = criteriosList;
	}

	public List<ReferenteEducativo> getCriteriosListCt() {
		return criteriosListCt;
	}

	public void setCriteriosListCt(List<ReferenteEducativo> criteriosListCt) {
		this.criteriosListCt = criteriosListCt;
	}

	public List<String> getSelectCapacidades() {
		return selectCapacidades;
	}

	public void setSelectCapacidades(List<String> selectCapacidades) {
		this.selectCapacidades = selectCapacidades;
	}

	public List<SelectItem> getModuloProfesionalList() {
		return moduloProfesionalList;
	}

	public void setModuloProfesionalList(List<SelectItem> moduloProfesionalList) {
		this.moduloProfesionalList = moduloProfesionalList;
	}

	public List<SelectItem> getCapacidadProfesionalList() {
		return capacidadProfesionalList;
	}

	public void setCapacidadProfesionalList(
			List<SelectItem> capacidadProfesionalList) {
		this.capacidadProfesionalList = capacidadProfesionalList;
	}

	public List<SelectItem> getModuloTransversalList() {
		return moduloTransversalList;
	}

	public void setModuloTransversalList(List<SelectItem> moduloTransversalList) {
		this.moduloTransversalList = moduloTransversalList;
	}

	public List<SelectItem> getCapacidadTransversalList() {
		return capacidadTransversalList;
	}

	public void setCapacidadTransversalList(
			List<SelectItem> capacidadTransversalList) {
		this.capacidadTransversalList = capacidadTransversalList;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
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

	public Long getAnnio() {
		return annio;
	}

	public void setAnnio(Long annio) {
		this.annio = annio;
	}

	public Long getProcess() {
		return process;
	}

	public void setProcess(Long process) {
		this.process = process;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public Seccion getSeccionObject() {
		return seccionObject;
	}

	public void setSeccionObject(Seccion seccionObject) {
		this.seccionObject = seccionObject;
	}

	public int[][] getNotas() {
		return notas;
	}

	public void setNotas(int[][] notas) {
		this.notas = notas;
	}

	public int getNumbCapTerminales() {
		return numbCapTerminales;
	}

	public void setNumbCapTerminales(int numbCapTerminales) {
		this.numbCapTerminales = numbCapTerminales;
	}

	public CetproMatricula getCetproMatricula() {
		return cetproMatricula;
	}

	public void setCetproMatricula(CetproMatricula cetproMatricula) {
		this.cetproMatricula = cetproMatricula;
	}

		
	
} 
