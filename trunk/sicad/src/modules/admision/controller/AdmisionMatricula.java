package modules.admision.controller; 
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import modules.admision.domain.Matricula;
import modules.admision.domain.Proceso;
import modules.admision.domain.Requisitos;
import modules.horario.domain.Seccion;
import modules.mantenimiento.domain.Banco;
import modules.marco.domain.Itinerario;
import modules.marco.domain.Profesion;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;

import dataware.service.AdmisionService;

@ManagedBean
@SessionScoped
public class AdmisionMatricula extends GenericController   
{
	private AdmisionService	myService;
	
	private	List<Requisitos> 	requisitos;
	private	List<Itinerario> 	itinerario;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    bancoList;
	private List<SelectItem>    seccionList;
	private List<SelectItem>    moduloList;
	private	List<Seccion>		unidadesList;
	
	private boolean enabled;
	private Long 	proceso;
	private Long 	institucion;
	private	Long	tipo;
	private	Long	modulo;
	private Long 	annio,mesProceso;
	
	public void init(Long id) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Matricula";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		proceso=id;
		
		page_new="adm_mat_lst";
		page_main="adm_mat_lst";
		page_update="adm_mat_upd";	
		
		
		requisitos = new ArrayList();
		unidadesList = new ArrayList();
		procesoList=getListSelectItem(myService.listarProcesos(institucion),"id","annio,nombrePeriodo","-",true);
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
			if(bean.getEstado().longValue()==5L)	
			{
				annio=bean.getAnnio();
				mesProceso=bean.getProceso();
				enabled=true;				
			}
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
		requisitos=myService.listarRequisitosMatricula(bean.getId(), institucion, 0L);
		itinerario=myService.listarModulos(bean.getEspecialidad());
		//obtener secciones
		bean=null;
		
		
		
		//Cleanning
		tipo=-1L;
		modulo=-1L;
		selectTipo();
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
		List<Seccion> unidades=new ArrayList<Seccion>();
		unidadesList = myService.listarUnidades(institucion, annio, mesProceso, bean.getEspecialidad(), bean.getTurno(), modulo, tipo);
		for(int i=0; i<unidadesList.size(); i++)
		{
			if(i>0){
				if(!unidadesList.get(i).getNombreUnidad().equals(unidadesList.get(i-1).getNombreUnidad()))
				{unidades.add(unidadesList.get(i));}
			}
			else{unidades.add(unidadesList.get(i));}
		}
		
		unidadesList = unidades;
		bean=null;
	}
	
	public void beforeUpdate() throws Exception
	{
		Matricula bean=(Matricula)getBean();
		bean.setEstado(getEstado());
		setBean(bean);
		myService.eliminarRequisito(false, bean.getId());
		myService.insertarRequisitos(false, requisitos, bean.getId());
		myService.actualizarMatriculaSeccion(unidadesList, bean.getId());
		
	}
	
	public void optionPublicar() throws Exception
	{
		//actualizar cupos
		status((Matricula)getBeanSelected(),4L);
		defaultList();
		setMessageSuccess("La matricula se p�blico satisfactoriamente.");
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
	
	public void optionGoDetalle() throws Exception 
	{
		setBean(getBeanSelected());
		
		
		forward("adm_mat_det");
	}
	
	
	/*
	
		public void optionGoMatricula() throws Exception 
	{
		
		setBean(getBeanSelected());
		
		Matricula bean=(Matricula)getBeanSelected();
	
		listaDeSeccionPorMatricula = new ArrayList<Seccion>();
		listaDeSeccionPorMatricula = myService.listaDeSeccionPorMatricula(proceso,bean.getEspecialidad(),bean.getTurno());//

		//verificar que la seccion se ha llenado
		Matricula temporalMatricula = new Matricula();
		temporalMatricula.setProceso(bean.getProceso());
		temporalMatricula.setEspecialidad(bean.getEspecialidad());
		temporalMatricula.setTurno(bean.getTurno());
		temporalMatricula.setEstado(4L);//matriculado
		int cantidad_matriculados =  getService().listByObject(temporalMatricula).size();
		int contador= 0;
		Long ambienteAnterior=0L;
		

		for (Seccion item : listaDeSeccionPorMatricula) 
		{
			if(item.getAmbiente()!=null)
			{
			Ambiente temporalAmbiente =  (Ambiente)getService().findById(Ambiente.class,item.getAmbiente()) ;
				if(cantidad_matriculados-ambienteAnterior >=temporalAmbiente.getCapacidad()) 
				{
					contador++;
					item.setEstado(2L);
					listaDeSeccionPorMatricula.set(contador-1, item);
					ambienteAnterior = temporalAmbiente.getCapacidad();
				}
			}
		}
		
		
		forward("adm_mat_upd");
	}	
	
	*/
	
	@Override
	public boolean validation() throws Exception {
		boolean success = true;
        Matricula object = (Matricula)getBean();
        if(!validateSelect(modulo))
        {
            setMessageError("Debe seleccionar el M�dulo.");                 
            success = false;
        }
        object=null;
        return success;
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
	
	public AdmisionService getMyService() 										{return myService;}	
	public void setMyService(AdmisionService myService)							{this.myService = myService;}
	
	public List<SelectItem> getModuloList() 									{return moduloList;}
	public void setModuloList(List<SelectItem> moduloList) 						{this.moduloList = moduloList;}
	
	public List<SelectItem> getProcesoList() 									{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 					{this.procesoList = procesoList;}
	
	public List<SelectItem> getSeccionList() 									{return seccionList;}
	public void setSeccionList(List<SelectItem> seccionList) 					{this.seccionList = seccionList;}
	
	public List<SelectItem> getBancoList() 										{return bancoList;}
	public void setBancoList(List<SelectItem> bancoList) 						{this.bancoList = bancoList;}
	
	public List<Requisitos> getRequisitos() 									{return requisitos;}
	public void setRequisitos(List<Requisitos> requisitos) 						{this.requisitos = requisitos;}
	
	public Long getProceso() 													{return proceso;}
	public void setProceso(Long proceso) 										{this.proceso = proceso;}
	
	public boolean isEnabled() 													{return enabled;}
	public void setEnabled(boolean enabled) 									{this.enabled = enabled;}
	
	public Long getTipo() 														{return tipo;}
	public void setTipo(Long tipo) 												{this.tipo = tipo;}
	
	public Long getModulo() 													{return modulo;}
	public void setModulo(Long modulo) 											{this.modulo = modulo;}
	
	public List<Seccion> getUnidadesList() 										{return unidadesList;}
	public void setUnidadesList(List<Seccion> unidadesList) 					{this.unidadesList = unidadesList;}
	
	
} 
