package dataware.dao.impl;
import java.util.ArrayList;
import java.util.List;
import modules.mantenimiento.domain.Supervision;
import org.hibernate.Query;
import dataware.dao.GeneralDao;

public class GeneralDaoJpa extends SeguridadDaoJpa implements GeneralDao 
{
	public List<Supervision> listarSupervision(Long dre, boolean tipo) throws Exception 
	{
		List<Supervision> lista=new ArrayList<Supervision>();
		Query consulta=null;
		if(tipo)							{consulta=createQuery("SELECT * FROM mantenimiento.m_supervision sup WHERE sup.subnivel=0 AND sup.estado IN (1,2);");}
		if(!tipo && dre==null)				{consulta=createQuery("SELECT * FROM mantenimiento.m_supervision sup WHERE sup.subnivel!=0 AND sup.nivel!=0 AND sup.estado IN (1,2);");}
		if(!tipo && dre!=null && dre>0L)	{consulta=createQuery("SELECT * FROM mantenimiento.m_supervision sup WHERE sup.subnivel!=0 AND sup.nivel="+dre+" AND sup.estado IN (1,2);");}
		if(!tipo && dre!=null && dre<0L)	{consulta=createQuery("SELECT * FROM mantenimiento.m_supervision sup WHERE sup.subnivel!=0 AND sup.nivel!=0 AND sup.estado IN (1,2);");}
		List rst=consulta.list();
		for(int i=0; i<rst.size(); i++)
		{
			Object[] objetos=(Object[])rst.get(i);
			Supervision field=new Supervision();
			field.setId(Long.parseLong(objetos[0].toString()));
			field.setNivel(Long.parseLong(objetos[1].toString()));
			field.setSubnivel(Long.parseLong(objetos[2].toString()));
			if(objetos[3]!=null)	{field.setRuc(objetos[3].toString());}
			if(objetos[4]!=null)	{field.setNombre(objetos[4].toString());}
			if(objetos[5]!=null)	{field.setDescripcion(objetos[5].toString());}
			if(objetos[6]!=null)	{field.setCorreo(objetos[6].toString());}
			if(objetos[7]!=null)	{field.setWeb(objetos[7].toString());}
			if(objetos[8]!=null)	{field.setUbigeo(Long.parseLong(objetos[8].toString()));}
			if(objetos[9]!=null)	{field.setDireccion(objetos[9].toString());}
			if(objetos[10]!=null)	{field.setReferencia(objetos[10].toString());}
			if(objetos[11]!=null)	{field.setTelefonos(objetos[11].toString());}
			if(objetos[12]!=null)	{field.setFax(objetos[12].toString());}
			field.setEstado(Long.parseLong(objetos[13].toString()));
			lista.add(field);
		}
		return lista;	
	}
}


