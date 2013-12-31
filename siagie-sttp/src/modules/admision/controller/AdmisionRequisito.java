package modules.admision.controller; 
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.controller.GenericController;
import dataware.service.AdmisionService;
import modules.admision.domain.Requisitos;
import modules.mantenimiento.domain.Requisito;
import modules.seguridad.domain.Usuario;

@ManagedBean
@SessionScoped
public class AdmisionRequisito extends GenericController   
{
	private AdmisionService	myService;
	private List<SelectItem>    requisitoList;
	private Long institucion;
	private Long modalidad, tipo;
	
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Requisitos";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		
		page_new="adm_req_new";
		page_main="adm_req_lst";
		page_update="adm_req_lst";		
		
		requisitoList=getListSelectItem(new Requisito(), "id", "descripcion", true);
		
		modalidad=-1L;
		tipo=-1L;
		
		forward(page_main);
		setBeanList(null);
		usr=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{optionSelect();}
	
	public void optionSelect() throws Exception
	{
		if(tipo>0L)
		{
			if(tipo==1L)	{setBeanList(myService.listarRequisitos(institucion, tipo, modalidad));}
			else			{setBeanList(myService.listarRequisitos(institucion, tipo, 0L));}
		}
	}
	
	public void optionRemove() throws Exception
	{
		myService.eliminarRequisito((Requisitos)getBeanSelected());
		defaultList();
		forward(page_main);
		setMessageSuccess("El requisito fue eliminado exitosamente");
	}
	
	public void optionNew() throws Exception
	{
		if(tipo.longValue()>0L)
		{
			Requisitos bean=new Requisitos();
			bean.setInstitucion(institucion);
			bean.setTipo(tipo);
			
			if(tipo.longValue()==1L)
			{
				if(modalidad.longValue()>=0L)
				{
					bean.setModalidad(modalidad);
					setBean(bean);
					forward(page_new);
				}
				else
				{setMessageError("Debe seleccionar la Modalidad");}
			}
			else
			{
				bean.setModalidad(0L);
				setBean(bean);
				forward(page_new);
			}
			bean=null;
		}
		else							
		{setMessageError("Debe seleccionar el Tipo");}
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Requisitos object = (Requisitos)getBean();
		if(!validateSelect(object.getRequisito()))
		{
			setMessageError("Debe seleccionar un requisito");			
			success = false;
		}
		object=null;
		return success;
	}
		
	public AdmisionService getMyService() 										{return myService;}
	public void setMyService(AdmisionService myService) 						{this.myService = myService;}

	public List<SelectItem> getRequisitoList() 									{return requisitoList;}
	public void setRequisitoList(List<SelectItem> requisitoList) 				{this.requisitoList = requisitoList;}

	public Long getModalidad() 													{return modalidad;}
	public void setModalidad(Long modalidad) 									{this.modalidad = modalidad;}

	public Long getTipo() 														{return tipo;}
	public void setTipo(Long tipo) 												{this.tipo = tipo;}
} 
