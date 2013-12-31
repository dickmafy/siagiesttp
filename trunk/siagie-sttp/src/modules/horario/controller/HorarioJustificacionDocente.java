package modules.horario.controller; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.BeanHelper;

import dataware.service.HorarioService;
import dataware.service.IntranetService;
import modules.administracion.domain.Ambiente;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Personal;
import modules.administracion.domain.Solicitud;
import modules.horario.domain.AsistenciaDocente;
import modules.horario.domain.HorarioRecuperacion;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboContenido;
import modules.intranet.controller.DocenteSilaboContenido;
import modules.intranet.controller.DocenteSilaboCriterio;
import modules.mantenimiento.domain.Empresa;
import modules.mantenimiento.domain.Puesto;
import modules.seguridad.domain.Usuario;

public class HorarioJustificacionDocente extends GenericController   
{
	private List<SelectItem>    procesoList;
	private List<SelectItem>    docenteList;
	private List<SelectItem> 	ambienteList;
	List<MetaInstitucional> metas;
	private Long annio,proceso;
	private Long institucion;
	private Long docente;
	private HorarioService	myService;
	private Date fechaActual;
	private Long usuario;
	
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		usuario=usr.getId();
		institucion=usr.getInstitucion();
		
		annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");
		selectAnnio();
		if(procesoList.size()!=0)	{proceso=Long.parseLong((Calendar.getInstance().get(Calendar.MONTH)+1)+"");}
		else						{proceso=-1L;}
		docente=-1L;
		
		fillProcesos();
		defaultList();
		
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		obj.setPuesto(6L);
		docenteList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "apepat,apemat,nombres"," ",true);
		obj=null;
		
		fechaActual = new Date();
		
		page_new="hor_just_doc";
		page_main="hor_just_doc";
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
		if(institucion.longValue()>0L && annio.longValue()>0L && proceso.longValue()>0L && docente.longValue()>0L)
		{setBeanList(myService.listarAsistenciaDocente(institucion, annio, proceso, docente, fechaActual));}
		else
		{setBeanList(new ArrayList<Seccion>());}
	}
	
	
	@Override
	public void nativeUpdate(ActionEvent event) throws Exception {
		AsistenciaDocente bean = (AsistenciaDocente)getBean();
		bean.setRegistroFecha(fechaActual);
		bean.setRegistroUsuario(usuario);
		bean.setEstado(2L);
		
		myService.actualizarSesion(bean);
		setMessageSuccess("La justificación se ha registrado exitósamente.");
		forward("hor_just_doc");
		
	}
	
	public void optionGoJustificacion() throws Exception
	{
		Seccion seccion= (Seccion)getBeanSelected();		
		AsistenciaDocente bean = myService.obtenerHorarioDocente(seccion.getId(), seccion.getFecha(), seccion.getAmbiente());
		setBean(bean);
		
		bean=null;
		seccion=null;
		forward("docente_justificacion");
	}
	
	public HorarioService getMyService() 							{return myService;}
	public void setMyService(HorarioService myService) 				{this.myService = myService;}

	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getProceso() 										{return proceso;}
	public void setProceso(Long proceso) 							{this.proceso = proceso;}
	
	public Long getDocente() 										{return docente;}
	public void setDocente(Long docente) 							{this.docente = docente;}

	public List<SelectItem> getProcesoList() 						{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 		{this.procesoList = procesoList;}

	public List<SelectItem> getDocenteList() 						{return docenteList;}
	public void setDocenteList(List<SelectItem> docenteList) 		{this.docenteList = docenteList;}
	
	public List<SelectItem> getAmbienteList() 						{return ambienteList;}
	public void setAmbienteList(List<SelectItem> ambienteList) 		{this.ambienteList = ambienteList;}

	public Date getFechaActual() 									{return fechaActual;}
	public void setFechaActual(Date fechaActual) 					{this.fechaActual = fechaActual;}
	
	
} 
