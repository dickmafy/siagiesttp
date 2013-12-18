package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.HorarioService;
import modules.administracion.domain.MetaInstitucional;
import modules.administracion.domain.Personal;
import modules.admision.domain.Proceso;
import modules.horario.domain.Seccion;
import modules.horario.domain.SilaboCronograma;
import modules.horario.domain.SilaboUnidadCt;
import modules.intranet.domain.silabo_docente;
import modules.marco.domain.ReferenteEducativo;
import modules.seguridad.domain.Usuario;

public class DocenteSilaboList extends GenericController   
{
	private List<SelectItem>    profesionList;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    docenteList;
	
	List<MetaInstitucional> metas;
	private Long annio,proceso,profesion,turno;
	private Long institucion,docente;
	private HorarioService	myService;
	
	private List<ReferenteEducativo> listarCT;
	private Long ctSeleccionado;
	private Long meta,seccion,unidad;
	
	
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Detalle ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		docente=usr.getPertenencia();
		
		annio = -1L;
		proceso = -1L;
				
		if(annio>0L)
		{metas=myService.listarMetaInstitucional(institucion,annio,-1L);}
		
		Personal obj=new Personal();
		obj.setInstitucion(institucion);
		obj.setPuesto(6L);
		docenteList=getListSelectItem(myService.listByObjectEnabled(obj), "id", "apepat,apemat,nombres"," ",true);
		obj=null;
		
		fillProcesos();
		
		defaultList();
		page_new="";
		page_main="DocenteSilaboList";
		page_update="";
		forward(page_main);
		
		
//		SilaboCronograma silaboCronograma =new SilaboCronograma();
//		silaboCronograma.setPk_meta(meta);
//    	silaboCronograma.setContenido("-");
//    	silaboCronograma.setPk_unidad(unidad);
//    	silaboCronograma.setPk_seccion(seccion);
//    	silaboCronograma.setPk_docente(docente);
//    	silaboCronograma.setEstado(1L);
//    	
//    	SilaboCronograma obtenerSilaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
//    	
//    	SilaboUnidadCt silaboUnidadCt = new SilaboUnidadCt();
//    	
//    	silaboUnidadCt.setPk_silabo_cronograma(obtenerSilaboCronograma.getId());
//		listarCT = myService.listByObject(silaboUnidadCt);
		
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
		{setBeanList(myService.listarSeccionesDocente(institucion, annio, proceso,docente));}
		else
		{setBeanList(new ArrayList<Seccion>());}
	}
	
	public void goCrearFechas() throws Exception
	{
//		SilaboCronograma silaboCronograma = obtenerSilaboCronograma();    	
//		silaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
		
		DocenteSilaboFecha go = (DocenteSilaboFecha)getSpringBean("docenteSilaboFecha");
		//go.init(annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
		go.init((Seccion)getBeanSelected());
	}
	
	public void goNotas(Seccion item)throws Exception{
		
		setBeanSelected(item);
		
		SilaboCronograma silaboCronograma = obtenerSilaboCronograma();    	
		silaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
		
		DocenteSilaboNota go = (DocenteSilaboNota)getSpringBean("docenteSilaboNota");
		Proceso proceso = new Proceso();
		proceso.setAnnio(annio);
		proceso.setProceso(this.proceso);
		proceso = (Proceso) myService.findByObject(proceso);
		go.init((Seccion)getBeanSelected(),proceso,silaboCronograma,ctSeleccionado);
	}
	
	
	public void goAsistencia()throws Exception{
		SilaboCronograma silaboCronograma = obtenerSilaboCronograma();    	
		silaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
    	
		DocenteSilaboAsistenciaListFecha  go = (DocenteSilaboAsistenciaListFecha)getSpringBean("docenteSilaboAsistenciaListFecha");
		Proceso proceso = new Proceso();
		proceso.setAnnio(annio);
		proceso.setProceso(this.proceso);
		proceso = (Proceso) myService.findByObject(proceso);
		go.init((Seccion)getBeanSelected(),proceso,silaboCronograma);
	}


	private SilaboCronograma obtenerSilaboCronograma() {
		Seccion bean = (Seccion)getBeanSelected();
		SilaboCronograma silaboCronograma =new SilaboCronograma();
		
		silaboCronograma.setPk_meta(bean.getMeta());
    	silaboCronograma.setContenido("-");
    	silaboCronograma.setPk_unidad(bean.getValorUnidad());
    	silaboCronograma.setPk_seccion(bean.getId());
    	silaboCronograma.setPk_docente(bean.getDocente());
    	silaboCronograma.setEstado(1L);
		return silaboCronograma;
	}
	
	public void goCt()throws Exception{
		
//		seccion=pseccion.getId();
//		docente=pseccion.getDocente();
//		meta=pseccion.getMeta();
//		unidad=pseccion.getValorUnidad();
//		
		//en la seccion esta el silabocronograma id
    	SilaboCronograma silaboCronograma =new SilaboCronograma();
		silaboCronograma.setPk_meta(meta);
    	silaboCronograma.setContenido("-");
    	silaboCronograma.setPk_unidad(unidad);
    	silaboCronograma.setPk_seccion(seccion);
    	silaboCronograma.setPk_docente(docente);
    	silaboCronograma.setEstado(1L);
    	
    	SilaboCronograma obtenerSilaboCronograma = (SilaboCronograma) myService.findByObject(silaboCronograma);
    	
    	
		DocenteSilaboCT go = (DocenteSilaboCT)getSpringBean("docenteSilaboCT");
		Proceso proceso = new Proceso();
		proceso.setAnnio(annio);
		proceso.setProceso(this.proceso);
		proceso = (Proceso) myService.findByObject(proceso);
		go.init((Seccion)getBeanSelected(),proceso,obtenerSilaboCronograma);
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


	public List<ReferenteEducativo> getListarCT() {
		return listarCT;
	}


	public void setListarCT(List<ReferenteEducativo> listarCT) {
		this.listarCT = listarCT;
	}


	public Long getCtSeleccionado() {
		return ctSeleccionado;
	}


	public void setCtSeleccionado(Long ctSeleccionado) {
		this.ctSeleccionado = ctSeleccionado;
	}

} 
