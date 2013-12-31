package modules.seguridad.controller; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import com.belogick.factory.util.helper.PasswordHelper;
import com.belogick.factory.util.support.Encriptador;

import modules.administracion.domain.Institucion;
import modules.administracion.domain.Personal;
import modules.seguridad.domain.Perfil;
import modules.seguridad.domain.Usuario;
import modules.seguridad.domain.VariableAcceso;
import dataware.service.SeguridadService;

public class UsuarioController extends GenericController   
{	 
	private SeguridadService	myService;
	private List<SelectItem> 	perfilList;
	private List<SelectItem> 	institucionList;
	private List<SelectItem> 	colaboradorList;
	
	private List<Personal> personal;
	
	private	String newPas;
	private	String oldPas;
	private	String repPas;
	private Long tipo=-1L;
	private Boolean select;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Configuración";
		moduleName="Seguridad - Usuarios ";
		userName=usr.getUsuario();
		
		defaultList();
		page_new="usr_new";
		page_main="usr_list";
		page_update="usr_update";
		forward(page_main);
		uppercase=false;
	}
		
	
	
	@Override 
	public void beforeSave() throws Exception
	{
		Usuario aux=(Usuario)getBean();
		aux.setCreacion(DateHelper.getDate());
		setBean(aux);
		aux=null;
	}	
	
	@Override 
	public void beforeUpdate() throws Exception
	{
		Usuario aux=(Usuario)getBean();
		aux.setModificacion(DateHelper.getDate());
		setBean(aux);
		aux=null;
	}
	
	public void fillPerfil() throws Exception
	{
		Usuario aux=(Usuario)getBean();
		aux.setInstitucion(-1L);
		aux.setPertenencia(-1L);
		setBean(aux);
		aux=null;
		
		Perfil obj=new Perfil();
		obj.setTipo(tipo);
		perfilList=getListSelectItem(obj, "id", "nombre", true);
		obj=null;
		fillInstitucion();
	}
	
	public void fillInstitucion() throws Exception
	{
		Usuario aux=(Usuario)getBean();
		aux.setInstitucion(-1L);
		aux.setNombres("");
		aux.setCorreo("");
		setBean(aux);
		aux=null;
		
		if(tipo.longValue()==2L)
		{
			Institucion inst=new Institucion();
			inst.setFormacion(1L);
			inst.setEstado(Constante.ROW_STATUS_ENABLED);
			institucionList=getListSelectItem(inst,"id","nombre",true);
			inst=null;
		}
		if(tipo.longValue()==3L)
		{
			Institucion inst=new Institucion();
			inst.setEstado(Constante.ROW_STATUS_ENABLED);
			
			List<Institucion> lista=myService.listByObject(inst);
			List<Institucion> cetpro=new ArrayList<Institucion>();
			
			for(int i=0; i<lista.size(); i++)
			{
				if(lista.get(i).getFormacion().longValue()==2L)
				{cetpro.add(lista.get(i));}
			}
			
			institucionList=getListSelectItem(cetpro,"id","nombre",true);
			inst=null;
			lista=null;
			cetpro=null;
		}
		if(tipo.longValue()==4L)
		{
			Institucion inst=new Institucion();
			inst.setEstado(Constante.ROW_STATUS_ENABLED);
			institucionList=getListSelectItem(inst,"id","nombre",true);
		}
	}
	
	public void fillPersonal() throws Exception
	{
		if(tipo.longValue()==2L || tipo.longValue()==3L)
		{
			Personal prs=new Personal();
			prs.setInstitucion(((Usuario)getBean()).getInstitucion());
			prs.setEstado(Constante.ROW_STATUS_ENABLED);
			personal=myService.listByObject(prs);
			colaboradorList=getListSelectItem(personal,"id","nombres,apepat,apemat"," ",true);
		}
	}
	
	public void selectPersonal() throws Exception
	{
		Usuario bean=(Usuario)getBean();
		if(bean.getPertenencia().longValue()>0L)
		{
			for(int i=0; i<personal.size(); i++)
			{
				if(bean.getPertenencia().longValue()==personal.get(i).getId())
				{
					bean.setNombres(personal.get(i).getNombres()+" "+personal.get(i).getApepat()+" "+personal.get(i).getApemat());
					bean.setCorreo(personal.get(i).getCorreo());
				}
			}
			setBean(bean);
			select=true;
		}
	}
	
	@Override
	public void afterNew() throws Exception
	{
		setEnabled(new Usuario());
		
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		Usuario usr=(Usuario)getBeanSelected();
		Perfil prf=(Perfil)myService.findById(Perfil.class, usr.getPerfil());
		Perfil obj=new Perfil();
		obj.setTipo(prf.getTipo());
		
		perfilList=getListSelectItem(obj, "id", "nombre", true);
		tipo=obj.getTipo();
		fillInstitucion();		
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Usuario object = (Usuario)getBean();
		if(!validateSelect(tipo))
		{
			setMessageError("Debe seleccionar el tipo de usuario.");			
			success = false;
		}
		else if(!validateSelect(object.getPerfil()))
		{
			setMessageError("Debe seleccionar un perfil.");			
			success = false;
		}
		else if(tipo.longValue()>1L && !validateSelect(object.getInstitucion()))
		{
			if(tipo.longValue()==2L)	{setMessageError("Debe seleccionar la Institución a la que pertenece.");}			
			if(tipo.longValue()==3L)	{setMessageError("Debe seleccionar el CETPRO al que pertenece.");}
			if(tipo.longValue()==4L)	{setMessageError("Debe seleccionar la Institución a la que pertenece.");}
			success = false;
		}
		else if(tipo.longValue()==2L && !validateSelect(object.getPertenencia()))
		{
			setMessageError("Debe seleccionar al Personal de la institución");
			success = false;
		}
		else if(!validateEmpty(object.getUsuario()))
		{
			setMessageError("Debe ingresar el nombre de usuario.");			
			success = false;
		}
		else if(tipo.longValue()!=2L && !validateEmpty(object.getNombres()))
		{
			setMessageError("Debe ingresar el nombre completo del usuario.");			
			success = false;
		}
		else if(tipo.longValue()!=2L && !validateEmpty(object.getCorreo()))
		{
			setMessageError("Debe ingresar el correo electrónico del usuario.");			
			success = false;
		}
		object=null;
		return success;
	}   
	
	public void optionReset(ActionEvent event) throws Exception 
	{
		VariableAcceso varLogin=myService.listarVariables();
		Usuario tmp=(Usuario)getBeanSelected();
		String clave=PasswordHelper.getAleatorio(varLogin.getValorInteger(1L));
		tmp.setContrasena(Encriptador.encryptBlowfish(clave, Constante.KEY));
		setBeanSave(getService().save(tmp));
		setMessageSuccess("La contraseña del usuario "+ tmp.getUsuario()+ " fue reseteada con el siguiente valor: "+clave);
		saveLog("Reiniciar Password");
		infoSucess(); 
		tmp=null;
		clave=null;
		varLogin=null;
	}
	
	public void optionCleanPasswords(ActionEvent event) throws Exception
	{
		newPas="";
		oldPas="";
		repPas="";
	}
	
	public void optionUpdatePassword(ActionEvent event) throws Exception
	{
		VariableAcceso varLogin=myService.listarVariables();
		if(!newPas.equals("") && !oldPas.equals("") && !repPas.equals(""))
		{
			Usuario usr = (Usuario)getSpringBean("usuarioSesion");
			if(usr.getContrasena().equals(Encriptador.encryptBlowfish(oldPas,Constante.KEY)))
			{
				if(newPas.equals(repPas))
				{
					if(newPas.length()>=varLogin.getValorInteger(1L))
					{
						usr.setContrasena(Encriptador.encryptBlowfish(newPas, Constante.KEY));
						setBeanSave(getService().save(usr));
						setMessageSuccess("La contraseña fue actualizada satisfactoriamente.");
						saveLog("Cambiar Password");
						infoSucess(); 
						optionCleanPasswords(event);
					}
					else
					{
						repPas="";
						newPas="";
						setMessageError("La contraseña nueva debe tener un mínimo de "+varLogin.getValorInteger(1L)+" caracteres.");
					}
				}
				else
				{
					repPas="";
					newPas="";
					setMessageError("La contraseña nueva no coincide con la repetida.");
				}
			}
			else
			{setMessageError("La contraseña antigua no es la correcta.");}
		}
		else
		{setMessageError("Para actualizar la contraseña satisfactoriamente debe ingresar los parametros solicitados.");}
		varLogin=null;
	}

	
	
	public String getNewPas() 											{return newPas;}
	public void setNewPas(String newPas) 								{this.newPas = newPas;}
		
	public String getOldPas() 											{return oldPas;}
	public void setOldPas(String oldPas) 								{this.oldPas = oldPas;}

	public String getRepPas() 											{return repPas;}
	public void setRepPas(String repPas) 								{this.repPas = repPas;}
	
	public SeguridadService getMyService() 								{return myService;}	
	public void setMyService(SeguridadService myService)				{this.myService = myService;}
	
	public List<SelectItem> getPerfilList() 							{return perfilList;}
	public void setPerfilList(List<SelectItem> perfilList) 				{this.perfilList = perfilList;}
	
	public List<SelectItem> getInstitucionList()						{return institucionList;}
	public void setInstitucionList(List<SelectItem> institucionList) 	{this.institucionList = institucionList;}

	public List<SelectItem> getColaboradorList() 						{return colaboradorList;}
	public void setColaboradorList(List<SelectItem> colaboradorList) 	{this.colaboradorList = colaboradorList;}

	public Long getTipo() 												{return tipo;}
	public void setTipo(Long tipo) 										{this.tipo = tipo;}

	public Boolean getSelect() 											{return select;}
	public void setSelect(Boolean select) 								{this.select = select;}
} 

