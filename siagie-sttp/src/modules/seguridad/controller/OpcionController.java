package modules.seguridad.controller;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.component.menuitem.MenuItem;
import com.belogick.factory.util.controller.GenericController;
import modules.seguridad.domain.Menu;
import modules.seguridad.domain.MenuAcceso;
public class OpcionController extends GenericController  implements ActionListener
{
	private List<Menu> 		sistemas;
	private List<Menu>		modulos;
	private	List<Menu>		menus;
	
	private Long	menuSelected;
	private	Long	moduloSelected;
	private	Long	sistemaSelected;
	
	public void init() throws Exception
	{
		sistemas=((MenuAcceso)getSpringBean("menuSesion")).getSistemas();
		modulos=((MenuAcceso)getSpringBean("menuSesion")).getModulos(sistemas.get(0).getSistema());
		menus=new ArrayList<Menu>();
		moduloSelected=0L;
		menuSelected=0L;
		sistemaSelected=sistemas.get(0).getId();
	}
			
	@Override
	public void processAction(ActionEvent event) 
    {
		if(event.getSource().getClass() == MenuItem.class) 
        {
			MenuItem sourceItem = (MenuItem) event.getSource();
			Menu item=((MenuAcceso)getSpringBean("menuSesion")).getMenu(Long.parseLong(sourceItem.getId().substring(4)));
			
			if(item.getModulo().longValue()==0L && item.getMenu().longValue()==0L)
			{
				modulos=((MenuAcceso)getSpringBean("menuSesion")).getModulos(item.getSistema());
				System.out.println("modulos: "+modulos.size());
				sistemaSelected=item.getId();
				moduloSelected=0L;
				menuSelected=0L;
				
			}
			else if(item.getModulo().longValue()!=0L && item.getMenu().longValue()==0L)
			{
				menus=((MenuAcceso)getSpringBean("menuSesion")).getMenus(item.getSistema(), item.getModulo());
				moduloSelected=item.getId();
				menuSelected=0L;				
			}
			else if(item.getModulo().longValue()!=0L && item.getMenu().longValue()!=0L)
			{
				menuSelected=item.getId();
				Object bean = getSpringBean(item.getAccion());
				Method method;
				try 
				{
					method = bean.getClass().getMethod(item.getMetodo(),null);
					method.invoke(bean, null);
				}
				catch (SecurityException e)			{e.printStackTrace();} 
				catch (NoSuchMethodException e) 	{e.printStackTrace();} 
				catch (IllegalArgumentException e) 	{e.printStackTrace();}
				catch (IllegalAccessException e) 	{e.printStackTrace();} 
				catch (InvocationTargetException e) {e.printStackTrace();}
				
				method=null;
				bean=null;
			}
			sourceItem=null;
			item=null;
        }
    }

	public List<Menu> getSistemas() 						{return sistemas;}
	public void setSistemas(List<Menu> sistemas) 			{this.sistemas = sistemas;}
	public List<Menu> getModulos() 							{return modulos;}
	public void setModulos(List<Menu> modulos) 				{this.modulos = modulos;}
	public List<Menu> getMenus() 							{return menus;}
	public void setMenus(List<Menu> menus) 					{this.menus = menus;}
	
	public Long getMenuSelected() 							{return menuSelected;}
	public void setMenuSelected(Long menuSelected) 			{this.menuSelected = menuSelected;}
	public Long getModuloSelected() 						{return moduloSelected;}
	public void setModuloSelected(Long moduloSelected) 		{this.moduloSelected = moduloSelected;}
	public Long getSistemaSelected() 						{return sistemaSelected;}
	public void setSistemaSelected(Long sistemaSelected) 	{this.sistemaSelected = sistemaSelected;}
}
