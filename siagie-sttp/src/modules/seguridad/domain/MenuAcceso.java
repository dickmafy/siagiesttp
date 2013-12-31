package modules.seguridad.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuAcceso implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<Menu> items;
		
	public List<Menu> getItems() 			{return items;}
	public void setItems(List<Menu> items) 	{this.items = items;}
	
	public List<Menu> getSistemas()
	{
		List<Menu> sistemas=new ArrayList<Menu>();
		for(int i=0; i<items.size(); i++)
		{
			if(items.get(i).getModulo().longValue()==0L && items.get(i).getMenu().longValue()==0L)
			{sistemas.add(items.get(i));}
		}
		return sistemas;
	}
	public List<Menu> getModulos(Long sistema)
	{
		List<Menu> modulos=new ArrayList<Menu>();
		for(int i=0; i<items.size(); i++)
		{
			if(items.get(i).getSistema().longValue()==sistema && items.get(i).getMenu().longValue()==0L && items.get(i).getModulo().longValue()!=0L)
			{modulos.add(items.get(i));}
		}
		return modulos;
	}
	
	public List<Menu> getMenus(Long sistema, Long modulo)
	{
		List<Menu> modulos=new ArrayList<Menu>();
		for(int i=0; i<items.size(); i++)
		{
			if(items.get(i).getSistema().longValue()==sistema && items.get(i).getModulo().longValue()==modulo && items.get(i).getMenu().longValue()!=0L)
			{modulos.add(items.get(i));}
		}
		return modulos;
	}
	
	public Menu getMenu(Long id)
	{
		for(int i=0; i<items.size(); i++)
		{
			if(items.get(i).getId().longValue()==id.longValue())
			{return items.get(i);}
		}
		return null;
	}
	
}