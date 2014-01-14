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
import modules.admision.domain.Persona;
import modules.admision.domain.Proceso;
import modules.cetpro.domain.CetproAsistencia;
import modules.cetpro.domain.CetproMatricula;
import modules.cetpro.domain.CetproMatriculaAlumno;
import modules.cetpro.domain.CetproMatriculaFecha;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCronograma;
import modules.horario.domain.SilaboUnidadCt;
import modules.intranet.domain.silabo_docente;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class CetproDocenteListNota extends GenericController   
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
	
	private Long tipo=0L,seccion,modulo,profesion,meta,docente,unidad,annio,process,pk_ct;
	private Proceso proceso;
	private String nombreUnidad;
	
	private Seccion seccionObject;
	
	private int notas[][];
	
	private int numbCapTerminales;
	private CetproMatricula cetproMatricula;
	private List <CetproMatriculaAlumno> listSilaboAlumno;
	
	
	public void init(CetproMatricula pCeproMatricula) throws Exception 
	{
		
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Notas";
		userName=usr.getUsuario();
		
		//usr.getPertenencia();
		//cetproMatricula.getProfesion();
		//cetproMatricula.getModulo();
		
		
		modulo=1L;
		profesion=101L; 
		nombreUnidad= "Prueba";
		
		
		cetproMatricula =  pCeproMatricula;
		
		
		
		
		numbCapTerminales = 3;
		defaultList();		
		forward(page_main);
		optionCriterios();
		
		forward("cetproDocenteListNota");
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
		/*Criteria criteria = JPAPersistenceUtil.getSession().createCriteria(Matricula.class);
		criteria.add(Expression.eq("estado",4))
		.add(Expression.eq("estado",seccionObject.));*/
		listarAlumnos();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void listarAlumnos() throws Exception{
		
		CetproMatriculaAlumno silaboAlumno = new CetproMatriculaAlumno();
		listSilaboAlumno = myService.listByObject(silaboAlumno);
		
		for (CetproMatriculaAlumno item : listSilaboAlumno) {
			CetproAsistencia asis=new CetproAsistencia();
			asis.setPk_cetpro_matricula_alumno(item.getId());
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
				ct.setPk_silabo_cronograma(cetproMatricula.getId());
				ct.setPk_ct(item.getId());
				ct.setPrioridad(1L);
				ct.setEstado(1L);
				myService.save(ct);
			}
		}
		
		SilaboCronograma bean = (SilaboCronograma)myService.findByObject(cetproMatricula);
		bean.setEstado(2L);
		myService.save(bean); 
		
		
		
		//forward("DocenteSilaboList");
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

	public List<CetproMatriculaAlumno> getListSilaboAlumno() {
		return listSilaboAlumno;
	}

	public void setListSilaboAlumno(List<CetproMatriculaAlumno> listSilaboAlumno) {
		this.listSilaboAlumno = listSilaboAlumno;
	}



	public Long getPk_ct() {
		return pk_ct;
	}



	public void setPk_ct(Long pk_ct) {
		this.pk_ct = pk_ct;
	}

	
	
	
} 
