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
		
		page_new="adm_mat_lst";
		page_main="adm_mat_lst";
		page_update="adm_mat_upd";	
		
		
		requisitos = new ArrayList();
		procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
		bancoList=getListSelectItem(new Banco(),"id","nombre",true);
		optionSelectProceso();
		forward(page_main);
	}
	public void init() throws Exception
	{init(-1L);}
	
	public void defaultList() throws Exception
	{setBeanList(myService.listarMatricula(proceso));}
	
	public void optionSelectProceso() throws Exception
	{
		if(proceso.longValue()>0L)		{defaultList();}
		else							{setBeanList(new ArrayList());}
		enabledOptions();
	}
	public void enabledOptions() throws Exception
	{
		if(proceso.longValue()>0L)
		{
			Proceso bean=(Proceso)myService.findById(Proceso.class, proceso);
			if(bean.getEstado().longValue()==5L)	{enabled=true;}
			bean=null;
		}
		else
		{enabled=false;}
	}
	
	public void nativeDetail() throws Exception
	{
		setBean(getBeanSelected());
		afterLoad();
		forward("adm_mat_det");
	}
	
	public void afterLoad() throws Exception
	{
		Matricula bean=(Matricula)getBean();
		//requisitos=myService.listarRequisitosMatricula(bean.getId(), institucion, 0L);
		itinerario=myService.listarModulos(bean.getEspecialidad());
		matriculaList=myService.listarSeccionesMatricula(bean.getId());
		bean=null;
		
		tipo=-1L;
		modulo=-1L;
		seccion=-1L;
		unidad=-1L;
		
		moduloList=new ArrayList<SelectItem>();
		moduloList.add(new SelectItem(Constante.NO_SELECTED, Constante.OPTION_SELECT));
		unidadList=new ArrayList<SelectItem>();
		unidadList.add(new SelectItem(Constante.NO_SELECTED, Constante.OPTION_SELECT));
		seccionList=new ArrayList<SelectItem>();
		seccionList.add(new SelectItem(Constante.NO_SELECTED, Constante.OPTION_SELECT));
	}
	
	
	public void beforeUpdate() throws Exception
	{
		Matricula bean=(Matricula)getBean();
		bean.setEstado(1L);
		setBean(bean);
		//myService.eliminarRequisito(false, bean.getId());
		//myService.insertarRequisitos(false, requisitos, bean.getId());
	}
	
	
	public void optionPublicar() throws Exception
	{
		status((Matricula)getBeanSelected(),2L);
		defaultList();
		setMessageSuccess("La matricula se público satisfactoriamente.");
	}
	
	public Long getEstado()
	{
		for(int i=0; i<requisitos.size(); i++)
		{
			if(!requisitos.get(i).getCheck())
			{return 1L;}
		}
		if(!validateEmpty(((Matricula)getBean()).getPago_fecha()) || !validateEmpty(((Matricula)getBean()).getPago_recibo()) || !validateSelect(((Matricula)getBean()).getPago_banco()))
		{return 2L;}
		return 3L;
	}
	
	public void selectAnnio() throws Exception
	{
		proceso=-1L;
		procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
	}
	
	public void selectTipo() throws Exception
	{
		moduloList=new ArrayList<SelectItem>();
		moduloList.add(new SelectItem(Constante.NO_SELECTED, Constante.OPTION_SELECT));
		if(tipo.longValue()>0L)
		{
			for(int i=0; i<itinerario.size(); i++)
			{
				if(itinerario.get(i).getTipoItinerario().longValue()==tipo.longValue())
				{moduloList.add(new SelectItem(itinerario.get(i).getModulo(), itinerario.get(i).getNombreModular()));}
			}
		}
	}
	public void selectModulo() throws Exception
	{
		Matricula bean=(Matricula)getBean();
		unidadList=getListSelectItem(myService.listarUnidadesDisponibles(bean.getPersona(), institucion, bean.getEspecialidad(), modulo, tipo),"id","titulo",true);
		unidad=-1L;
	}
	
	public void selectUnidad() throws Exception
	{
		Matricula bean=(Matricula)getBean();
		Proceso beanProceso=(Proceso)myService.findById(Proceso.class, proceso);
		seccionList=getListSelectItem(myService.listarSecciones(institucion, annio, beanProceso.getProceso(), bean.getEspecialidad(), bean.getTurno(), modulo, tipo, unidad),"id","nombre",true);
	}
	
	public void addSeccion() throws Exception
	{
		Matricula bean=(Matricula)getBean();
		if(myService.validarVacantes(seccion))
		{
			if(myService.validarCruces(seccion, bean.getId()))
			{
				myService.actualizarMatricula(true, seccion, bean.getId(), bean.getPersona(), DateHelper.getDate());
				matriculaList=myService.listarSeccionesMatricula(bean.getId());
				setMessageSuccess("La sección fue agregada a la matricula del alumno satisfactoriamente.");
			}
			else
			{setMessageError("La sección seleccionada genera un cruce de horarios.");}
		}
		else
		{setMessageError("No existen vacantes para esta sección.");}
		bean=null;
	}
	
	public void subSeccion() throws Exception
	{
		Matricula bean=(Matricula)getBean();
		System.out.println("valor de la sección: "+selectSeccion.getSeccion());
		myService.actualizarMatricula(false, selectSeccion.getSeccion(), bean.getId(), bean.getPersona(), DateHelper.getDate());
		matriculaList=myService.listarSeccionesMatricula(bean.getId());
		setMessageSuccess("La sección fue eliminada de la matricula del alumno satisfactoriamente.");
		bean=null;
	}
	
	public String getPic()
	{
		try
		{			
			File fotoFile = new File(getServletContext().getRealPath("/recursos/fotos/"+((Matricula)getBean()).getPersona()+".png"));
			if(fotoFile.isFile()) return "/recursos/fotos/"+((Matricula)getBean()).getPersona()+".png";
			else return "/recursos/fotos/default.png";
		}
		catch(Exception e)
		{return "/recursos/fotos/default.png";}
		
	}
	
	
	public boolean validation()
	{
		boolean success=true;
		Matricula bean=(Matricula)getBean();
		if(matriculaList.size()<3)
		{
			success=false;
			setMessageError("Debe agregar al menos tres secciones en la lista de matricula por sección.");	
		}
		else if(!validateEmpty(bean.getPago_fecha())) 
		{
			success=false;
			setMessageError("Debe ingresar la fecha de pago.");
		}
		else if(!validateSelect(bean.getPago_banco())) 
		{
			success=false;
			setMessageError("Debe seleccionar el Banco donde realizo el pago.");
		}
		else if(!validateEmpty(bean.getPago_recibo())) 
		{
			success=false;
			setMessageError("Debe ingresar el número de recibo del voucher.");
		}
		return success;
	}
	
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
