package modules.cetpro.controller; 
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import modules.admision.domain.Matricula;
import modules.admision.domain.MatriculaSeccion;
import modules.admision.domain.Proceso;
import modules.admision.domain.Requisitos;
import modules.mantenimiento.domain.Banco;
import modules.marco.domain.Itinerario;
import modules.seguridad.domain.Usuario;
import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import dataware.service.AdmisionService;

@ManagedBean
@SessionScoped
public class CetproMatricula extends GenericController   
{
	private AdmisionService	myService;
	
	private	List<Requisitos> 	requisitos;
	private	List<Itinerario> 	itinerario;
	private	List<MatriculaSeccion>	matriculaList;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    bancoList;
	private List<SelectItem>    moduloList;
	private List<SelectItem>    unidadList;
	private List<SelectItem>    seccionList;
	
	
	private boolean enabled;
	private Long 	proceso;
	private Long 	institucion;
	private	Long	tipo;
	private	Long	modulo;
	private	Long	unidad;
	private	Long	seccion;
	private Long 	annio;
	private	MatriculaSeccion	selectSeccion;
	
	public void init(Long id) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Matricula";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		proceso=id;
		annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");
		
		page_new="cetpro_matricula_new";
		page_main="cetpro_matricula_list";
		page_update="cetpro_matricula_detail";	
		
		
		requisitos = new ArrayList();
		procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
		bancoList=getListSelectItem(new Banco(),"id","nombre",true);
	
		forward(page_main);
	}
	public void init() throws Exception
	{init(-1L);}
	
	public void defaultList() throws Exception
	{setBeanList(myService.listarMatricula(proceso));}
	

	
	public AdmisionService getMyService() 										{return myService;}	
	public void setMyService(AdmisionService myService)							{this.myService = myService;}
	
	public List<Requisitos> getRequisitos() 									{return requisitos;}
	public void setRequisitos(List<Requisitos> requisitos) 						{this.requisitos = requisitos;}
	
	public List<SelectItem> getBancoList() 										{return bancoList;}
	public void setBancoList(List<SelectItem> bancoList) 						{this.bancoList = bancoList;}
	
	public List<SelectItem> getProcesoList() 									{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 					{this.procesoList = procesoList;}
	
	public List<SelectItem> getModuloList() 									{return moduloList;}
	public void setModuloList(List<SelectItem> moduloList) 						{this.moduloList = moduloList;}
	
	public List<SelectItem> getSeccionList() 									{return seccionList;}
	public void setSeccionList(List<SelectItem> seccionList) 					{this.seccionList = seccionList;}
		
	public List<SelectItem> getUnidadList() 									{return unidadList;}
	public void setUnidadList(List<SelectItem> unidadList) 						{this.unidadList = unidadList;}
	
	public boolean isEnabled() 													{return enabled;}
	public void setEnabled(boolean enabled) 									{this.enabled = enabled;}
	
	public Long getProceso() 													{return proceso;}
	public void setProceso(Long proceso) 										{this.proceso = proceso;}
	
	public Long getTipo() 														{return tipo;}
	public void setTipo(Long tipo) 												{this.tipo = tipo;}
	
	public Long getModulo() 													{return modulo;}
	public void setModulo(Long modulo) 											{this.modulo = modulo;}
	
	public Long getUnidad()	 													{return unidad;}
	public void setUnidad(Long unidad) 											{this.unidad = unidad;}
	
	public Long getSeccion() 													{return seccion;}
	public void setSeccion(Long seccion) 										{this.seccion = seccion;}
	
	public Long getAnnio() 														{return annio;}
	public void setAnnio(Long annio) 											{this.annio = annio;}
	
	public MatriculaSeccion getSelectSeccion() 									{return selectSeccion;}
	public void setSelectSeccion(MatriculaSeccion selectSeccion) 				{this.selectSeccion = selectSeccion;}
	
	public List<MatriculaSeccion> getMatriculaList() 							{return matriculaList;}
	public void setMatriculaList(List<MatriculaSeccion> matriculaList) 			{this.matriculaList = matriculaList;}
	
} 
