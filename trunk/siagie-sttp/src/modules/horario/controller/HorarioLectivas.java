package modules.horario.controller; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.HorarioService;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Personal;
import modules.horario.domain.Seccion;
import modules.seguridad.domain.Usuario;

public class HorarioLectivas extends GenericController   
{
	private List<SelectItem>    profesionList;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    docenteList;
	
	List<MetaInstitucional> metas;
	private Long annio,proceso,profesion,turno;
	private Long institucion;
	private HorarioService	myService;
	
	public void init(Long ann, Long prc, Long prf, Long trn) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		
		annio=ann;
		proceso=prc;
		profesion=prf;
		turno=trn;
		
		if(annio>0L)
		{metas=myService.listarMetaInstitucional(institucion,annio,-1L);}
		
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		obj.setPuesto(6L);
		docenteList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "apepat,apemat,nombres"," ",true);
		obj=null;
		
		fillProcesos();
		fillProfesion();
		
		defaultList();
		page_new="hor_meta_lec";
		page_main="hor_meta_lec";
		page_update="hor_meta_lec";
		forward(page_main);
	}
	
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		institucion=usr.getInstitucion();
		annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");
		selectAnnio();
		if(procesoList.size()!=0)	{proceso=Long.parseLong((Calendar.getInstance().get(Calendar.MONTH)+1)+"");}
		else						{proceso=-1L;}
		init(annio,proceso,-1L,-1L);
		usr=null;
	}
	
	public void selectAnnio() throws Exception
	{
		metas=myService.listarMetaInstitucional(institucion,annio,-1L);
		fillProcesos();
		proceso=-1L;
		profesionList=new ArrayList<SelectItem>();
		profesion=-1L;
		turno=-1L;
		defaultList();
	}
	
	public void selectProceso() throws Exception
	{
		fillProfesion();
		profesion=-1L;
		turno=-1L;
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
	
	public void fillProfesion() throws Exception
	{
		profesionList=new ArrayList<SelectItem>();
		
		if(metas!=null)
		{
			for(int i=0; i<metas.size(); i++)
			{
				if(metas.get(i).getAnnio().longValue()==annio && metas.get(i).getProceso().longValue()==proceso)
				{profesionList.add(new SelectItem(metas.get(i).getProfesion(),metas.get(i).getNombreProfesion()));}
			}
		}
		HashSet<SelectItem> hashSet = new HashSet<SelectItem>(profesionList);
		profesionList.clear();
		profesionList.addAll(hashSet);
		hashSet=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		if(institucion.longValue()>0L && annio.longValue()>0L && proceso.longValue()>0L && profesion.longValue()>0L && turno.longValue()>0L)
		{setBeanList(myService.listarSecciones(institucion, annio, proceso, profesion, turno));}
		else
		{setBeanList(new ArrayList<Seccion>());}
	}
	
	public void setDocente() throws Exception
	{
		Seccion sec=(Seccion)getBeanSelected();
		boolean validar=true;
		if(!myService.validarHoras(sec.getDocente(), sec.getValorHoras()))
		{
			setMessageError("Asignación Fallida: La sección a asignar implica que el Docente supere su Carga Lectiva de 20 horas.");
			sec.setDocente(-1L);
			validar=false;
		}
		else if(!myService.validarDisponibilidad(sec.getId(), sec.getDocente()))
		{
			setMessageError("Asignación Fallida: El docente no cuenta con disponibilidad horaria para esta sección.");
			sec.setDocente(-1L);			
			validar=false;
		}
		if(validar)
		{
			myService.actualizarDocente(sec.getId(), sec.getDocente());
			setMessageSuccess("El docente fue asignado exitosamente.");
		}
		sec=null;
	}
	
	
	public HorarioService getMyService() 							{return myService;}
	public void setMyService(HorarioService myService) 				{this.myService = myService;}

	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getProceso() 										{return proceso;}
	public void setProceso(Long proceso) 							{this.proceso = proceso;}

	public Long getProfesion() 										{return profesion;}
	public void setProfesion(Long profesion) 						{this.profesion = profesion;}

	public Long getTurno() 											{return turno;}
	public void setTurno(Long turno) 								{this.turno = turno;}
	
	public List<SelectItem> getProfesionList() 						{return profesionList;}
	public void setProfesionList(List<SelectItem> profesionList) 	{this.profesionList = profesionList;}
	
	public List<SelectItem> getProcesoList() 						{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 		{this.procesoList = procesoList;}

	public List<SelectItem> getDocenteList() 						{return docenteList;}
	public void setDocenteList(List<SelectItem> docenteList) 		{this.docenteList = docenteList;}

} 
