package modules.seguridad.controller; 
import java.util.ArrayList;
import java.util.List;
import com.belogick.factory.util.controller.GenericController;

import modules.seguridad.domain.Acceso;
import modules.seguridad.domain.Menu;
import modules.seguridad.domain.Perfil;
import modules.seguridad.domain.Usuario;
import modules.seguridad.domain.MenuAcceso;
import dataware.service.SeguridadService;

public class PerfilController extends GenericController   
{	 
	private SeguridadService myService;
	private List<Menu> menuList;
	private List<Menu> menuFilterList;
	private Menu menuSelected;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Configuración";
		moduleName="Seguridad - Perfiles";
		userName=usr.getUsuario();
		defaultList();
		page_new="prf_new";
		page_main="prf_list";
		page_update="prf_update";
		forward(page_main);
		usr=null;
	}
	
	@Override
	public void afterNew() throws Exception
	{
		setEnabled(new Perfil());
		menuList=new ArrayList<Menu>();		
	}
	
	public void fillAccesos() throws Exception
	{menuList=((MenuAcceso) myService.listarAccesos(0L,((Perfil)getBean()).getTipo())).getItems();}
	
	@Override
	public void afterSave() throws Exception
	{
		Perfil aux=(Perfil)getBeanSave();
		myService.insertarAcceso(menuList, aux.getId());
		aux=null;
	}
	
	@Override
	public void afterUpdate() throws Exception
	{
		Perfil aux=(Perfil)getBeanSave();
		myService.deleteByField(new Acceso(), "pk_perfil",aux.getId().toString());
		myService.insertarAcceso(menuList, aux.getId());
		aux=null;
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		Perfil bean=(Perfil)getBean();
		menuList=((MenuAcceso) myService.listarAccesos(0L,bean.getTipo())).getItems();	
		setListMenu(bean.getId());
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Perfil object = (Perfil)getBean();
		if(!validateSelect(object.getTipo()))
		{
			setMessageError("Debe seleccionar el tipo de Perfil.");			
			success = false;
		}
		else if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el nombre del Perfil.");			
			success = false;
		}
		else if(!validateEmpty(object.getDescripcion()))
		{
			setMessageError("Debe ingresar la descripción del Perfil.");			
			success = false;
		}
		else if(!validateSelect(object.getEstado()))
		{
			setMessageError("Debe seleccionar un estado.");			
			success = false;
		}
		else if(!validateMenus())
		{
			setMessageError("Debe seleeccionar al menos un menu de acceso.");			
			success = false;
		}
		object=null;
		return success;
	}   
	
	public void clickMenu() throws Exception
	{
		if(menuSelected.getModulo().longValue()==0L && menuSelected.getMenu().longValue()==0L)
		{setStatus(1,menuSelected.getSelected());}
		else if(menuSelected.getModulo().longValue()!=0L && menuSelected.getMenu().longValue()==0L)
		{
			setStatus(2,menuSelected.getSelected());
			if(menuSelected.getSelected())	{setStatus(3,menuSelected.getSelected());}
			else							{setStatusRoot(3);}
		}
		else if(menuSelected.getMenu().longValue()!=0L)
		{
			if(menuSelected.getSelected())	{setStatus(4,menuSelected.getSelected()); setStatus(3,menuSelected.getSelected());}
			else							{setStatusRoot(4);setStatusRoot(3);}
		}
	}
		
	private void setStatusRoot(int type)
	{
		int contFalse=0;
		for(int i=0; i<menuList.size(); i++)
		{
			if(type==3 && menuList.get(i).getSistema().longValue()==menuSelected.getSistema().longValue() && menuList.get(i).getModulo().longValue()!=0L && menuList.get(i).getSelected())
			{contFalse++;}
			if(type==4 && menuList.get(i).getSistema().longValue()==menuSelected.getSistema().longValue() && menuList.get(i).getModulo().longValue()==menuSelected.getModulo().longValue() && menuList.get(i).getMenu().longValue()!=0L && menuList.get(i).getSelected())
			{contFalse++;}
		}
		if(contFalse==0)
		{setStatus(type, false);}
	}
	
	private void setStatus(int type, boolean status)
	{
		for(int i=0; i<menuList.size(); i++)
		{
			//Todo lo que esta dentro de un sistema
			if(type==1 && menuList.get(i).getSistema().longValue()==menuSelected.getSistema().longValue())
			{menuList.get(i).setSelected(status);}
			
			//Todo lo que esta dentro de un modulo
			if(type==2 && menuList.get(i).getSistema().longValue()==menuSelected.getSistema().longValue() && menuList.get(i).getModulo().longValue()==menuSelected.getModulo().longValue())
			{menuList.get(i).setSelected(status);}
			
			//Solo el sistema padre
			if(type==3 && menuList.get(i).getId().longValue()==menuSelected.getSistema().longValue()*10000L)
			{menuList.get(i).setSelected(status);}

			//Solo el modulo padre
			if(type==4 && menuList.get(i).getId().longValue()==menuSelected.getSistema().longValue()*10000L+menuSelected.getModulo().longValue()*100L)
			{menuList.get(i).setSelected(status);}
		}
	}
		
	public void setListMenu(Long perfil) throws Exception 
	{
		List<Menu> auxList=((MenuAcceso) myService.listarAccesos(perfil,0L)).getItems();
		for(int i=0; i<auxList.size(); i++)
		{
			for(int j=0; j<menuList.size(); j++)
			{
				if(menuList.get(j).getId().equals(auxList.get(i).getId()))
				{menuList.get(j).setSelected(true);}
			}
		}
		auxList=null;
	}
	
	public boolean validateMenus()
	{
		int cont=0;
		for(int i=0; i<menuList.size(); i++)
		{
			if(menuList.get(i).getSelected())
			{cont++;}
		}
		if(cont==0)	{return false;}
		return true;
	}	
	
	public SeguridadService getMyService() 						{return myService;}	
	public void setMyService(SeguridadService myService)	 	{this.myService = myService;}

	public List<Menu> getMenuList() 							{return menuList;}
	public void setMenuList(List<Menu> menuList) 				{this.menuList = menuList;}

	public List<Menu> getMenuFilterList()						{return menuFilterList;}
	public void setMenuFilterList(List<Menu> menuFilterList) 	{this.menuFilterList = menuFilterList;}

	public Menu getMenuSelected() 								{return menuSelected;}
	public void setMenuSelected(Menu menuSelected) 				{this.menuSelected = menuSelected;}
	
} 
