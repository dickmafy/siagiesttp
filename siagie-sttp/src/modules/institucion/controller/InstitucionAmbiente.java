package modules.institucion.controller; 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import modules.administracion.domain.Ambiente;
import modules.administracion.domain.Local;
import modules.seguridad.domain.Usuario;

public class InstitucionAmbiente extends GenericController   
{
	private List<SelectItem>    localList;
	private	List<Local> 		locales;
	private	String				nombreLocal;
	
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucional";
		moduleName="Ambientes";
		userName=usr.getUsuario();
		page_new="itc_amb_new";
		page_main="itc_amb_lst";
		page_update="itc_amb_upd";
		forward(page_main);
		setBeanList(new ArrayList<Ambiente>());
		setEnabled(new Ambiente());
		
		Local bean=new Local();
		bean.setInstitucion(usr.getInstitucion());
		locales=getService().listByObjectEnabled(bean);
		if(locales!=null)
		{localList=getListSelectItem(locales, "id", "nombre",true);}
	}
	
	public void optionNew() throws Exception
	{
		Ambiente bean=(Ambiente)getBean();
		if(bean.getLocal().longValue()>0L){forward(page_new);}
		else
		{setMessageError("Debe seleccionar un Local para agregar ambientes.");}
		bean=null;
	}
	
	public void selectLocal() throws Exception
	{
		Ambiente bean=(Ambiente)getBean();
		for(int i=0; i<locales.size(); i++)
		{
			if(locales.get(i).getId().longValue()==bean.getLocal().longValue())
			{nombreLocal=locales.get(i).getNombre();}
		}
		setBeanList(getService().listByObjectEnabled(bean));
		bean=null;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Ambiente object = (Ambiente)getBean();
		
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el tipo de ambiente.");			
			success = false;
		}
		else if(!validateEmpty(object.getCodigo()))
		{
			setMessageError("Debe ingresar el codigo del ambiente.");			
			success = false;
		}
		if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del ambiente.");			
			success = false;
		}
		if(object.getArea()<=0L)
		{
			setMessageError("El area del ambiente debe ser mayor a 0.");			
			success = false;
		}
		object=null;
		return success;
	}

	public List<SelectItem> getLocalList() 					{return localList;}
	public void setLocalList(List<SelectItem> localList) 	{this.localList = localList;}

	public String getNombreLocal() 							{return nombreLocal;}
	public void setNombreLocal(String nombreLocal) 			{this.nombreLocal = nombreLocal;}
	
} 
