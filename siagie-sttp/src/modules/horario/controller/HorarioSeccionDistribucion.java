package modules.horario.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.controller.GenericController;

import dataware.service.HorarioService;
import modules.administracion.domain.Ambiente;
import modules.horario.domain.Horario;
import modules.horario.domain.HorarioDistribucion;
import modules.horario.domain.Seccion;
import modules.seguridad.domain.Usuario;

public class HorarioSeccionDistribucion extends GenericController   
{
	private List<SelectItem> 			ambienteList;	
	private List<HorarioDistribucion> 	horarioNuevo;	
	private	List<Horario>				horario;
	private HorarioService				myService;
	
	private Horario selectHorario;
	private Long 	selectDia;
	private boolean selectEstado;
	
	private	String	nombreTipo, nombreHoras;
	private Long	ambiente,capacidad, ambienteOld;
	private	Long 	institucion,seccion,turno, horas, estado,total;
	
	public void init(Long seccion, Long tipoAmbiente, Long turno, Long horas, Long total, String nombreTipo, Long ambienteOld, Long estado) throws Exception
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Horario";
		moduleName="Distribución";
		institucion=usr.getInstitucion();
		userName=usr.getUsuario();
		
		this.seccion=seccion;
		this.turno=turno;
		this.horas=horas;
		this.nombreTipo=nombreTipo;
		this.ambienteOld=ambienteOld;
		this.estado=estado;
		this.total=total;
		
		if(ambienteOld!=null)	
		{
			ambiente=ambienteOld;
			selectAmbiente();
		}
		else
		{
			ambiente=-1L;
			capacidad=0L;
			cleanHorario(turno);
		}
				
		setBean(myService.findById(Seccion.class, seccion));
		ambienteList=getListSelectItem(myService.listarAmbientes(institucion, tipoAmbiente, total), "id", "codigo,nombre"," - ",true);
			
		page_new="hor_meta_hor";
		page_main="hor_meta_hor";
		page_update="hor_meta_hor";
		forward(page_main);	
	}
	
	public void selectAmbiente() throws Exception 
	{
		capacidad = ((Ambiente)myService.findById(Ambiente.class, ambiente)).getCapacidad();
		cleanHorario(turno);
		setHorarios(myService.listarDistribucion(1L, seccion, ambiente));
		setRestriccion(myService.listarDistribucion(3L, seccion, ambiente));
	}
	
	public void optionSave() throws Exception
	{
		if(validation())
		{
			myService.insertarDistribucion(horarioNuevo, seccion, ambienteOld);
			setMessageSuccess("El registro fue guardado satifactoriamente.");
			
			HorarioSeccion go = (HorarioSeccion)getSpringBean("horarioSeccion");
			go.selectSeccion();
			
			HorarioMetaDetalle met = (HorarioMetaDetalle)getSpringBean("horarioMetaDetalle");
			met.optionAvance();
			
			go=null;
			met=null;
			forward("hor_meta_sec");
			
		}
	}
		
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		if(!validateSelect(ambiente))
		{
			setMessageError("Debe seleccionar el Ambiente.");			
			success = false;
		}
		else if(horarioNuevo.size()>horas)
		{
			setMessageError("La cantidad de horas seleccionada es mayor al limite permitido.");			
			success = false;
		}
		else if(horarioNuevo.size()<horas)
		{
			setMessageError("La cantidad de horas seleccionada es menor al limite permitido.");			
			success = false;
		}
		return success;
	}
		

	public List<SelectItem> getAmbienteList() 					{return ambienteList;}
	public void setAmbienteList(List<SelectItem> ambienteList) 	{this.ambienteList = ambienteList;}

	public HorarioService getMyService() 						{return myService;}
	public void setMyService(HorarioService myService) 			{this.myService = myService;}
	
	public List<Horario> getHorario() 							{return horario;}
	public void setHorario(List<Horario> horario) 				{this.horario = horario;}

	public Long getCapacidad() 									{return capacidad;}
	public void setCapacidad(Long capacidad) 					{this.capacidad = capacidad;}

	public Long getAmbiente() 									{return ambiente;}
	public void setAmbiente(Long ambiente) 						{this.ambiente = ambiente;}
	
	
	public void setHorarioNuevo() throws Exception
	{
		if(selectEstado)
		{
			HorarioDistribucion nuevo=new HorarioDistribucion();
			nuevo.setAmbiente(ambiente);
			nuevo.setDia(selectDia);
			nuevo.setInicio(selectHorario.getInicio());
			nuevo.setFin(selectHorario.getFin());
			horarioNuevo.add(nuevo);
		}
		else
		{
			for(int i=0; i<horarioNuevo.size(); i++)
			{
				if(horarioNuevo.get(i).getDia().longValue()==selectDia.longValue() && horarioNuevo.get(i).getInicio().longValue()==selectHorario.getInicio().longValue() && horarioNuevo.get(i).getFin().longValue()==selectHorario.getFin().longValue())
				{horarioNuevo.remove(i);}
			}
		}
	}
	
	public void setRestriccion(List<HorarioDistribucion> distribucion) throws Exception
	{fillHorario(distribucion,true);}
	
	public void setHorarios(List<HorarioDistribucion> distribucion) throws Exception
	{fillHorario(distribucion,false);}
	
	private void fillHorario(List<HorarioDistribucion> distribucion, boolean tipo) throws Exception
	{
		for(int i=0; i<distribucion.size(); i++)
		{
			HorarioDistribucion obj=distribucion.get(i);
			for(int j=0; j<horario.size(); j++)
			{
				if(tipo && obj.getInicio().longValue()==horario.get(j).getInicio().longValue() && obj.getFin().longValue()==horario.get(j).getFin().longValue())
				{horario.get(j).setFreeDia(obj.getDia());}
				if(!tipo && obj.getInicio().longValue()==horario.get(j).getInicio().longValue() && obj.getFin().longValue()==horario.get(j).getFin().longValue())
				{horario.get(j).setDia(obj.getDia());}
			}
			if(!tipo)
			{horarioNuevo.add(obj);}
			obj=null;
		}
	}

	public void cleanHorario(Long turno)
	{
		horarioNuevo=new ArrayList<HorarioDistribucion>();
		horario=new ArrayList<Horario>();
		if(turno.longValue()==1L)
		{
			for(int j=1; j<10; j++)
			{
				Horario bean=new Horario();
				bean.cleanDia();
				bean.setInicio(Long.parseLong(j+""));
				bean.setFin(Long.parseLong((j+1)+""));
				horario.add(bean);
				bean=null;
			}
		}
		else if(turno.longValue()==2L)
		{
			for(int j=10; j<15; j++)
			{
				Horario bean=new Horario();
				bean.cleanDia();
				bean.setInicio(Long.parseLong(j+""));
				bean.setFin(Long.parseLong((j+1)+""));
				horario.add(bean);
				bean=null;
			}
		}
		else if(turno.longValue()==3L)
		{
			for(int j=15; j<20; j++)
			{
				Horario bean=new Horario();
				bean.cleanDia();
				bean.setInicio(Long.parseLong(j+""));
				bean.setFin(Long.parseLong((j+1)+""));
				horario.add(bean);
				bean=null;
			}
		}
		
	}

	public Horario getSelectHorario() 						{return selectHorario;}
	public void setSelectHorario(Horario selectHorario) 	{this.selectHorario = selectHorario;}

	public Long getSelectDia() 								{return selectDia;}
	public void setSelectDia(Long selectDia) 				{this.selectDia = selectDia;}

	public boolean isSelectEstado() 						{return selectEstado;}
	public void setSelectEstado(boolean selectEstado) 		{this.selectEstado = selectEstado;}

	public Long getHoras() 									{return horas;}
	public void setHoras(Long horas) 						{this.horas = horas;}

	public String getNombreTipo() 							{return nombreTipo;}
	public void setNombreTipo(String nombreTipo) 			{this.nombreTipo = nombreTipo;}

	public String getNombreHoras() 
	{
		if(horas.longValue()==1L)	{return "1 hora";}
		else						{return horas+" horas";}
	}

	public void setNombreHoras(String nombreHoras) 			{this.nombreHoras = nombreHoras;}

	public Long getEstado() 								{return estado;}
	public void setEstado(Long estado) 						{this.estado = estado;}

	public Long getTotal() 									{return total;}
	public void setTotal(Long total) 						{this.total = total;}
} 
