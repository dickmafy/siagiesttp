package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.IntranetService;
import modules.administracion.domain.MetaInstitucional;
import modules.horario.domain.AsistenciaAlumno;
import modules.horario.domain.AsistenciaDocente;
import modules.horario.domain.Seccion;
import modules.seguridad.domain.Usuario;

public class DocenteAsistencia extends GenericController   
{
	private List<SelectItem>    procesoList;
	private List<SelectItem>    ambienteList;
	
	private List<AsistenciaDocente> 	sesionList;
	private List<AsistenciaAlumno> 		asistenciaList;
	private List<MetaInstitucional> metas;
	private Long annio,proceso, pertenencia;
	private Long institucion, ambiente;
	private	AsistenciaDocente	sesionSelect;
	private	AsistenciaAlumno	asistenciaSelect;
	private IntranetService	myService;
	
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		pertenencia=usr.getPertenencia();
		
		annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");
		selectAnnio();
		if(procesoList.size()!=0)	{proceso=Long.parseLong((Calendar.getInstance().get(Calendar.MONTH)+1)+"");}
		else						{proceso=-1L;}
		
		fillProcesos();
		defaultList();
		
		page_new="docente_asistencia";
		page_main="docente_asistencia";
		page_update="docente_asistencia";
		forward(page_main);
	}
	
	public void selectAnnio() throws Exception
	{
		metas=myService.listarMetaInstitucional(institucion,annio,-1L);
		fillProcesos();
		proceso=-1L;
		defaultList();
	}
	
	public void fillProcesos() throws Exception
	{
		procesoList=new ArrayList<SelectItem>();
		List<Integer> procesos=new ArrayList<Integer>();
		
		if(metas!=null)
		{
			for(int i=0; i<metas.size(); i++)
			{
				if(metas.get(i).getAnnio().longValue()==annio)
				{procesos.add(Integer.parseInt(metas.get(i).getProceso().toString()));}
			}
		}
		HashSet<Integer> hashSet = new HashSet<Integer>(procesos);
		procesos.clear(); 
		procesos.addAll(hashSet);
		 
		for(int j=0; j<procesos.size(); j++)
		{
			if(procesos.get(j)==1)	{procesoList.add(new SelectItem(1,"Enero"));}
			if(procesos.get(j)==2)	{procesoList.add(new SelectItem(2,"Febrero"));}
			if(procesos.get(j)==3)	{procesoList.add(new SelectItem(3,"Marzo"));}
			if(procesos.get(j)==4)	{procesoList.add(new SelectItem(4,"Abril"));}
			if(procesos.get(j)==5)	{procesoList.add(new SelectItem(5,"Mayo"));}
			if(procesos.get(j)==6)	{procesoList.add(new SelectItem(6,"Junio"));}
			if(procesos.get(j)==7)	{procesoList.add(new SelectItem(7,"Julio"));}
			if(procesos.get(j)==8)	{procesoList.add(new SelectItem(8,"Agosto"));}
			if(procesos.get(j)==9)	{procesoList.add(new SelectItem(9,"Septiembre"));}
			if(procesos.get(j)==10)	{procesoList.add(new SelectItem(10,"Octubre"));}
			if(procesos.get(j)==11)	{procesoList.add(new SelectItem(11,"Noviembre"));}
			if(procesos.get(j)==12)	{procesoList.add(new SelectItem(12,"Diciembre"));}
		}
		procesos=null;
		hashSet=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		if(institucion.longValue()>0L && annio.longValue()>0L && proceso.longValue()>0L)
		{setBeanList(myService.listarSilabos(institucion, annio, proceso, pertenencia));}
		else
		{setBeanList(new ArrayList<Seccion>());}
	}
	
	public void optionGoSesiones() throws Exception
	{
		Seccion bean= (Seccion)getBeanSelected();
		ambienteList=getListSelectItem(myService.listarAmbientes(bean.getId()), "id", "nombre",true);
		forward("docente_asistencia_ambiente");
	}
	
	public void selectAmbiente() throws Exception
	{
		if(ambiente>0L)
		{
			Seccion bean= (Seccion)getBeanSelected();
			sesionList=myService.listarSesiones(bean.getId(), ambiente);
			bean=null;
		}
		else
		{sesionList=new ArrayList<AsistenciaDocente>();}
	}
	
	public void optionGoAsistencia() throws Exception
	{
		asistenciaList=myService.listarAsistencia(sesionSelect.getSeccion(), ambiente, sesionSelect.getFecha());
		forward("docente_asistencia_alumno");
	}
	public void setAsistencia() throws Exception
	{
		myService.save(asistenciaSelect);
		setMessageSuccess("El registro de asistencia fue éxitoso.");
	}
	
	public void optionGoNotas() throws Exception
	{
		/*
		Seccion bean= (Seccion)getBeanSelected();
		DocenteSilaboContenido go = (DocenteSilaboContenido)getSpringBean("docenteSilaboContenido");
		go.init(bean.getId(), bean.getValorProfesion(), bean.getValorTipoModulo(), bean.getValorModulo(), bean.getNombreUnidad());
		bean=null;*/
	}
	
	public IntranetService getMyService() 									{return myService;}
	public void setMyService(IntranetService myService) 					{this.myService = myService;}

	public Long getAnnio() 													{return annio;}
	public void setAnnio(Long annio) 										{this.annio = annio;}

	public Long getProceso() 												{return proceso;}
	public void setProceso(Long proceso) 									{this.proceso = proceso;}

	public List<SelectItem> getProcesoList() 								{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList)		 		{this.procesoList = procesoList;}

	public List<SelectItem> getAmbienteList() 								{return ambienteList;}
	public void setAmbienteList(List<SelectItem> ambienteList) 				{this.ambienteList = ambienteList;}

	public List<AsistenciaDocente> getSesionList() 							{return sesionList;}
	public void setSesionList(List<AsistenciaDocente> sesionList) 			{this.sesionList = sesionList;}

	public List<AsistenciaAlumno> getAsistenciaList() 						{return asistenciaList;}
	public void setAsistenciaList(List<AsistenciaAlumno> asistenciaList) 	{this.asistenciaList = asistenciaList;}

	public Long getAmbiente() 												{return ambiente;}
	public void setAmbiente(Long ambiente) 									{this.ambiente = ambiente;}

	public AsistenciaDocente getSesionSelect() 								{return sesionSelect;}
	public void setSesionSelect(AsistenciaDocente sesionSelect) 			{this.sesionSelect = sesionSelect;}

	public AsistenciaAlumno getAsistenciaSelect() 							{return asistenciaSelect;}
	public void setAsistenciaSelect(AsistenciaAlumno asistenciaSelect) 		{this.asistenciaSelect = asistenciaSelect;}
} 
