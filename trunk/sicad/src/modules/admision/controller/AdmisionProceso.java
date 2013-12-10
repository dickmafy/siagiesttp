package modules.admision.controller; 
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;

import dataware.service.AdmisionService;
import modules.admision.domain.Proceso;
import modules.admision.domain.ProcesoCronograma;
import modules.admision.domain.ProcesoOferta;
import modules.seguridad.domain.Usuario;

@ManagedBean
@SessionScoped
public class AdmisionProceso extends GenericController   
{
	private AdmisionService	myService;
	private	Long institucion, registrante;
	private String maxFec, minFec;
	
	private	List<ProcesoCronograma> 	actividades;
	private List<ProcesoOferta>			ofertas;
	
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Procesos";
		userName=usr.getUsuario();
		registrante=usr.getId();
		institucion=usr.getInstitucion();
		defaultList();
		page_new="adm_pro_lst";
		page_main="adm_pro_lst";
		page_update="adm_pro_upd";
		forward(page_main);
		usr=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{setBeanList(myService.listarProcesos(institucion));}
	
	public void afterLoad() throws Exception
	{
		Proceso bean=(Proceso)getBeanSelected();
		actividades=myService.listarProcesoCronograma(bean.getId());
		ofertas=myService.listarProcesoOferta(bean.getId());
		maxFec=diasDelMes(bean.getProceso(), bean.getAnnio().toString())+"/"+bean.getProceso()+"/"+bean.getAnnio().toString().substring(2);
		minFec="01/"+bean.getProceso()+"/"+bean.getAnnio().toString().substring(2);
		forward(page_update);
	}
	
	public void beforeUpdate() throws Exception
	{
		Proceso bean=(Proceso)getBean();
		bean.setRegistrante(registrante);
		bean.setFecha(DateHelper.getDate());
		bean.setEstado(2L);
		setBean(bean);
		myService.actualizarProcesoCronograma(actividades, bean.getId());
		bean=null;
	}	
	
	@Override
    public boolean validation() throws Exception 
    {
        boolean success = true;
        Proceso object = (Proceso)getBean();
        if(!validateCrono())
        {success = false;}
        else if(object.getCosto_carpeta()<=0.0)
        {
                setMessageError("Debe ingresar el costo de la carpeta de postulante.");                 
                success = false;
        }
        else if(object.getCosto_inscripcion()<=0.0)
        {
                setMessageError("Debe ingresar el costo del registro al proceso de admisión.");                 
                success = false;
        }
        else if(object.getCosto_matreg()<=0.0)
        {
                setMessageError("Debe ingresar el costo de la matricula regular.");                     
                success = false;
        }
        else if(object.getCosto_matext()<=0.0)
        {
                setMessageError("Debe ingresar el costo de la matricula extemporanea.");                        
                success = false;
        }
        object=null;
        return success;
    }
	public boolean validateCrono() throws Exception
    {
        boolean success = true;
        for(int i=0; i<actividades.size(); i++)
        {
            if(!actividades.get(i).validateActividad() && success)
            {
                    setMessageError("Debe ingresar correctamente las fechas de la Actividad Nro. "+(i+1)+" del Cronograma");                    
                    success = false;
            }
        }
        return success;
    }
	
	public void optionPublicar() throws Exception
	{
		Proceso bean=(Proceso)getBeanSelected();
		myService.updateStatus(bean, 3L);
		setMessageSuccess("El proceso fue publicado exitósamente, el proceso de registro de postulantes esta habilitado.");
		defaultList();		
		bean=null;
	}
	
	public void optionAdmision() throws Exception
	{
		Proceso bean=(Proceso)getBeanSelected();
		myService.updateStatus(bean, 4L);
		setMessageSuccess("El proceso ha registrado el Inicio de la Etapa de Admisión exitósamente.");
		defaultList();
		bean=null;
	}
	public void optionMatricula() throws Exception
	{
		Proceso bean=(Proceso)getBeanSelected();
		myService.updateStatus(bean, 5L);
		myService.procesarIngresantes(bean.getId());
		setMessageSuccess("El proceso ha registrado el Inicio de la Etapa de Matricula exitósamente.");
		defaultList();
		bean=null;
	}
	public void optionAcademica() throws Exception
	{
		Proceso bean=(Proceso)getBeanSelected();
		myService.updateStatus(bean, 6L);
		setMessageSuccess("El proceso ha registrado el Inicio de la Etapa de Academica exitósamente.");
		defaultList();
		bean=null;
	}
	public void optionFinalizar() throws Exception
	{
		Proceso bean=(Proceso)getBeanSelected();
		myService.updateStatus(bean, 7L);
		setMessageSuccess("El proceso se ha finalizado exitósamente.");
		defaultList();
		bean=null;
	}
	
	public void optionAnular() throws Exception
	{
		Proceso bean=(Proceso)getBeanSelected();
		myService.updateStatus(bean, 0L);
		setMessageSuccess("El proceso fue anulado exitósamente.");
		defaultList();
		bean=null;
	}
	
	public void optionGoPostulante() throws Exception
	{
		AdmisionPostulante go = (AdmisionPostulante)getSpringBean("admisionPostulante");
		go.init(((Proceso)getBeanSelected()).getId().longValue());
	}
	public void optionGoAdmision() throws Exception
	{
		AdmisionIngresante go = (AdmisionIngresante)getSpringBean("admisionIngresante");
		go.init(((Proceso)getBeanSelected()).getId().longValue());
	}
	public void optionGoMatricula() throws Exception
	{
		AdmisionMatricula go = (AdmisionMatricula)getSpringBean("admisionMatricula");
		go.init(((Proceso)getBeanSelected()).getId().longValue());
	}
	public void optionGoRetiro() throws Exception
	{
		AdmisionRetiro go = (AdmisionRetiro)getSpringBean("admisionRetiro");
		//go.init(((Proceso)getBeanSelected()).getId().longValue());
	}
	
	public static int diasDelMes(Long mes, String anio)
	{
		mes=mes-1L;
		int mess=Integer.parseInt(mes.toString());
		int año=Integer.parseInt(anio);
		switch(mess)
		{
			case 0:  // Enero
			case 2:  // Marzo
			case 4:  // Mayo
			case 6:  // Julio
			case 7:  // Agosto
			case 9:  // Octubre
			case 11: // Diciembre
			return 31;
			case 3:  // Abril
			case 5:  // Junio
			case 8:  // Septiembre
			case 10: // Noviembre
			return 30;
			case 1:  // Febrero
			if ( ((año%100 == 0) && (año%400 == 0)) ||
			((año%100 != 0) && (año%  4 == 0))   )
			return 29;  // Año Bisiesto
			else
			return 28;
			default: throw new java.lang.IllegalArgumentException ("El mes debe estar entre 0 y 11");
		}
	}
	
	public AdmisionService getMyService() 										{return myService;}	
	public void setMyService(AdmisionService myService)							{this.myService = myService;}
	
	public List<ProcesoCronograma> getActividades() 							{return actividades;}
	public void setActividades(List<ProcesoCronograma> actividades) 			{this.actividades = actividades;}

	public List<ProcesoOferta> getOfertas() 									{return ofertas;}
	public void setOfertas(List<ProcesoOferta> ofertas) 						{this.ofertas = ofertas;}
	
	public String getMaxFec() 													{return maxFec;}
	public void setMaxFec(String maxFec) 										{this.maxFec = maxFec;}
	public String getMinFec() 													{return minFec;}
	public void setMinFec(String minFec) 										{this.minFec = minFec;}
	
} 
