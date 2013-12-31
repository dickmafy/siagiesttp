package modules.intranet.controller; 
import java.util.ArrayList;
import java.util.List;
import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.IntranetService;
import modules.horario.domain.SilaboNota;
import modules.marco.domain.ReferenteEducativo;
import modules.marco.domain.Transversal;
import modules.seguridad.domain.Usuario;

public class DocenteSilaboCriterio extends GenericController   
{	
	private IntranetService	myService;
	private List<ReferenteEducativo> criteriosList;
	private List<Transversal> critTransversalesList;
	
	private Long seccion,modulo,profesion,tipoModulo;
	private String nombreUnidad;
	
	public void init(Long seccion, Long profesion, Long modulo, Long tipoModulo, String nombreUnidad) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Intranet Docente";
		moduleName="Sílabo";
		userName=usr.getUsuario();
		this.seccion=seccion;
		this.modulo=modulo;
		this.tipoModulo=tipoModulo;
		this.profesion=profesion;
		this.nombreUnidad=nombreUnidad;
		
		forward("docente_silabo_crit");
		optionCriterios();
	}
	
	public void optionCriterios() throws Exception
	{
		if(tipoModulo == 1L)
		{
			List<ReferenteEducativo> educativoList=myService.listarReferenteEducativo(profesion, 0, 1L);
			criteriosList=new ArrayList<ReferenteEducativo>();
			
			for(int i=0; i<educativoList.size(); i++)
			{
				if(educativoList.get(i).getTipo().longValue()==1L && educativoList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())
				{criteriosList.add(educativoList.get(i));	}
			}			
			
			educativoList=null;
			filtrarModuloProfesional(criteriosList,modulo);
			criteriosSeleccionados();
		}
		else
		{
			List<Transversal> transversalList=myService.listarReferenteTransversal(0, 0L);
			critTransversalesList=new ArrayList<Transversal>();
			
			for(int i=0; i<transversalList.size(); i++)
			{
				if(transversalList.get(i).getTipo().longValue()!=3L && transversalList.get(i).getEstado().longValue()!=Constante.ROW_STATUS_DELETE.longValue())	
				{critTransversalesList.add(transversalList.get(i));}
			}	
			
			transversalList=null;
			filtrarModuloTransversal(critTransversalesList,modulo);
			criteriosSeleccionados();
		}
		
		forward("docente_silabo_crit");
	}
	
	public void filtrarModuloProfesional(List<ReferenteEducativo> educativoList, Long modulo) throws Exception
	{
		ArrayList<ReferenteEducativo> filtro=new ArrayList<ReferenteEducativo>();	
		for (ReferenteEducativo item : educativoList) 
		{
			if(item.getNivelA() == modulo)
			{filtro.add(item);}						
		}
		
		criteriosList=filtro;
	}
	
	public void filtrarModuloTransversal(List<Transversal> transversalList, Long modulo) throws Exception
	{
		ArrayList<Transversal> filtro=new ArrayList<Transversal>();	
		for (Transversal item : transversalList) 
		{
			if(item.getModulo() == modulo)
			{filtro.add(item);}						
		}
		
		critTransversalesList=filtro;
	}
	
	public void criteriosSeleccionados() throws Exception
	{
		List<SilaboNota> listSilabo = new ArrayList<SilaboNota>() ;
		
		listSilabo = myService.criteriosSeleccionados(seccion);		
		
		if(tipoModulo == 1L)
		{
			for (ReferenteEducativo item : criteriosList) 
			{
				for (SilaboNota marcados : listSilabo)
				{
					if(item.getId().equals(marcados.getCriterio()))
					{item.setCheckItem(true);}
				}
			}
		}
		else
		{
			for (Transversal item : critTransversalesList) 
			{
				for (SilaboNota marcados : listSilabo)
				{
					if(item.getId().equals(marcados.getCriterio()))
					{item.setCheckItem(true);}
				}
			}
		}
		
		
	}
	
	public void guardarCriterios() throws Exception
	{	
		myService.insertarCriterios(criteriosList, critTransversalesList, seccion, tipoModulo);
		setMessageSuccess("El sílabo ha sido actualizado exitósamente");		
		forward("docente_silabo");
	}
	
	
	public IntranetService getMyService() 												{return myService;}
	public void setMyService(IntranetService myService) 								{this.myService = myService;}

	public List<ReferenteEducativo> getCriteriosList() 									{return criteriosList;}
	public void setCriteriosList(List<ReferenteEducativo> criteriosList) 				{this.criteriosList = criteriosList;}
	
	public String getNombreUnidad() 													{return nombreUnidad;}
	public void setNombreUnidad(String nombreUnidad) 									{this.nombreUnidad = nombreUnidad;}

	public List<Transversal> getCritTransversalesList() 								{return critTransversalesList;}
	public void setCritTransversalesList(List<Transversal> critTransversalesList) 		{this.critTransversalesList = critTransversalesList;}

	public Long getTipoModulo() 														{return tipoModulo;}
	public void setTipoModulo(Long tipoModulo) 											{this.tipoModulo = tipoModulo;}
} 
