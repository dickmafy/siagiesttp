package modules.horario.controller; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.HorarioService;
import modules.administracion.domain.MetaInstitucional;
import modules.seguridad.domain.Usuario;

public class HorarioMetas extends GenericController   
{
	private List<SelectItem>    procesoList;
	
	private Long annio,proceso;
	private Long institucion;
	private HorarioService	myService;
	
	public void init() throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horarios";
		moduleName="Meta IESTP ";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		
		annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");
		selectAnnio();
		if(procesoList.size()!=0)	{proceso=Long.parseLong((Calendar.getInstance().get(Calendar.MONTH)+1)+"");}
		else						{proceso=-1L;}
		
		defaultList();
		page_new="hor_meta_list";
		page_main="hor_meta_list";
		page_update="hor_meta_list";
		forward(page_main);
	}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(myService.listarMetaInstitucional(institucion,annio,proceso));}
	
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
	public void optionGoDetalle() throws Exception
	{
		HorarioMetaDetalle go = (HorarioMetaDetalle)getSpringBean("horarioMetaDetalle");
		go.init(annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
	}
	public void optionGoLectiva() throws Exception
	{
		HorarioLectivas go = (HorarioLectivas)getSpringBean("horarioLectivas");
		go.init(annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
	}
	public void optionGoSilabo() throws Exception
	{
		HorarioSilabo go = (HorarioSilabo)getSpringBean("horarioSilabo");
		go.init(annio, proceso, ((MetaInstitucional)getBeanSelected()).getProfesion(), ((MetaInstitucional)getBeanSelected()).getTurno());
	}
	
	public void procesoAsignacion() throws Exception
	{
		Long cantidad=myService.validarEtapaAsignacion(((MetaInstitucional)getBeanSelected()).getId());
		cantidad=0L;
		if(cantidad!=null)
		{
			if(cantidad.longValue()==0L)
			{
				myService.updateStatus(getBeanSelected(), 2L);
				setMessageSuccess("El proceso ha registrado el Inicio de la Etapa Asignaci���n Docente exit���samente.");
				defaultList();
			}
			else
			{setMessageError("No es posible iniciar la Etapa Asignaci���n Docente debido a que existen "+cantidad+" Unidad(es) Didactica(s) pendiente asignaci���n horaria.");}
		}
		cantidad=null;
	}
	
	public void procesoSilabo() throws Exception
	{
		Long cantidad=myService.validarEtapaSilabo(((MetaInstitucional)getBeanSelected()).getId());
		cantidad=0L;
		if(cantidad!=null)
		{
			if(cantidad.longValue()==0L)
			{
				myService.updateStatus(getBeanSelected(), 3L);
				setMessageSuccess("El proceso ha registrado el Inicio de la Etapa Registro de Silabo exit���samente.");
				defaultList();
			}
			else
			{setMessageError("No es posible iniciar la Etapa Registro de Silabo debido a que existen "+cantidad+" Seccion(es) sin docentes asignados.");}
		}
		cantidad=null;
	}
	
	public void procesoAdmision() throws Exception
	{
		Long cantidad=myService.validarEtapaAdmision(annio, proceso);
		cantidad=0L;
		if(cantidad!=null)
		{
			if(cantidad.longValue()==0L)
			{
				myService.etapaAdmision(annio, proceso);
				setMessageSuccess("El proceso ha registrado el Inicio de la Etapa de Admisi���n exit���samente.");
				defaultList();
			}
			else
			{setMessageError("Para iniciar la Etapa de Admisi���n todos las secciones de los procesos en conjunto deben tener aprobados sus silabos.");}
		}
		cantidad=null;
	}
	
	
	public List<SelectItem> getProcesoList() 						{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 		{this.procesoList = procesoList;}

	public HorarioService getMyService() 							{return myService;}
	public void setMyService(HorarioService myService) 				{this.myService = myService;}

	public Long getAnnio() 											{return annio;}
	public void setAnnio(Long annio) 								{this.annio = annio;}

	public Long getProceso() 										{return proceso;}
	public void setProceso(Long proceso) 							{this.proceso = proceso;}
} 
