package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.HorarioService;
import modules.administracion.domain.MetaInstitucional;
import modules.horario.controller.HorarioMetaDetalle;
import modules.intranet.controller.DocenteSilaboCronograma;
import modules.seguridad.domain.Usuario;

public class IntranetDocenteSilaboList extends GenericController   
{
	private List<SelectItem>    procesoList;
	
	private Long annio,proceso;
	private Long institucion;
	private HorarioService	myService;
	
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta Institucional ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		annio=-1L;
		proceso=-1L;
		
		defaultList();
		page_new="";
		page_main="IntranetDocente_silabo_list";
		page_update="";
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(myService.listarMetaInstitucional(institucion,annio,proceso));}
	
	//Se ejecuta al seleccionar el Combo Año
	public void selectAnnio() throws Exception
	{
		procesoList=new ArrayList<SelectItem>();
		List<Integer> procesos=new ArrayList<Integer>();
		List<MetaInstitucional> metas=myService.listarMetaInstitucional(institucion,annio,-1L);
		for(int i=0; i<metas.size(); i++)
		{
			if(metas.get(i).getAnnio().longValue()==annio)
			{procesos.add(Integer.parseInt(metas.get(i).getProceso().toString()));}
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
		metas=null;
		proceso=-1L;
	}
	
//	public void optionGoDetalle() throws Exception
//	{
//		HorarioMetaDetalle go = (HorarioMetaDetalle)getSpringBean("horarioMetaDetalle");
//		go.init(annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
//	}
	public void optionGoLectiva() throws Exception
	{
		IntranetDocenteSilaboNew go = (IntranetDocenteSilaboNew)getSpringBean("intranetDocenteSilaboNew");
		go.init(annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
	}
//	public void optionGoSilabo() throws Exception
//	{
//		DocenteSilaboCronograma go = (DocenteSilaboCronograma)getSpringBean("docenteSilaboCronograma");
//		go.init(annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
//	}
//	
	
	public List<SelectItem> getProcesoList() 						{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 		{this.procesoList = procesoList;}

	public HorarioService getMyService() 							{return myService;}
	public void setMyService(HorarioService myService) 				{this.myService = myService;}

	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getProceso() 										{return proceso;}
	public void setProceso(Long proceso) 							{this.proceso = proceso;}
} 
