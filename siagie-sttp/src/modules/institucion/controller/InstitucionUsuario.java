package modules.institucion.controller; 
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import com.belogick.factory.util.helper.PasswordHelper;
import com.belogick.factory.util.support.Encriptador;

import modules.administracion.domain.Personal;
import modules.admision.domain.Matricula;
import modules.admision.domain.Persona;
import modules.seguridad.domain.Perfil;
import modules.seguridad.domain.Usuario;
import modules.seguridad.domain.VariableAcceso;
import dataware.service.SeguridadService;

public class InstitucionUsuario extends GenericController   
{	 
	private Long institucion;
	private List<SelectItem> 	perfilList;
	private List<SelectItem>    personalList;
	private SeguridadService	myService;
	private List<Personal> personal;
	private List<Matricula> matriculados;
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Institucional";
		moduleName="Usuario";
		userName=usr.getUsuario();
		institucion=usr.getInstitucion();
		defaultList();
		page_new="itc_usr_new";
		page_main="itc_usr_lst";
		page_update="itc_usr_upd";
		forward(page_main);
		uppercase=false;
		fillAll();
	}
	
	public void fillAll() throws Exception
	{
		Perfil objPrf=new Perfil();
		objPrf.setTipo(3L);
		objPrf.setEstado(Constante.ROW_STATUS_ENABLED);
		perfilList=getListSelectItem(objPrf, "id", "nombre", true);
		objPrf=null;
		
		//Matricula mat=new Matricula();
		
		
	}
	
	public void selectPerfil() throws Exception
	{
		Usuario bean=(Usuario)getBean();
		
		if(bean.getPerfil()==4L)
		{		
			matriculados=myService.listMatriculaInstitucion(institucion);
			personalList=getListSelectItem(matriculados, "id", "personaPaterno,personaMaterno,personaNombre"," ", true);
		}
		else
		{
			Personal objPrs=new Personal();
			objPrs.setInstitucion(institucion);
			personal=myService.listByObjectEnabled(objPrs);
			personalList=getListSelectItem(personal, "id", "apepat,apemat,nombres"," ", true);
			objPrs=null;
			
			
		}
	}
	

	public void selectPersonal() throws Exception
	{
		Usuario bean=(Usuario)getBean();
		if(bean.getPerfil()==4L){	
			for(int i=0; i<matriculados.size(); i++){
				if(bean.getPertenencia().longValue()==matriculados.get(i).getId()){
					bean.setUsuario(matriculados.get(i).getPersonaDni());
					bean.setContrasena(Encriptador.encryptBlowfish(matriculados.get(i).getPersonaDni(), Constante.KEY));
					bean.setNombres(matriculados.get(i).getPersonaNombre() + " " + matriculados.get(i).getPersonaPaterno() + " " + matriculados.get(i).getPersonaMaterno());
					bean.setCorreo(matriculados.get(i).getPersonaCorreo());
					bean.setCreacion(DateHelper.getDate());
					bean.setEstado(Constante.ROW_STATUS_ENABLED);
				}
			}
			
		} else{
			
			for(int i=0; i<personal.size(); i++){
				if(bean.getPertenencia().longValue()==personal.get(i).getId()){
					bean.setUsuario(personal.get(i).getDni());
					bean.setContrasena(Encriptador.encryptBlowfish(personal.get(i).getDni(), Constante.KEY));
					bean.setNombres(personal.get(i).getNombreCompleto());
					bean.setCorreo(personal.get(i).getCorreo());
					bean.setCreacion(DateHelper.getDate());
					bean.setEstado(Constante.ROW_STATUS_ENABLED);
				}
			}	
			
		}
		
		setBean(bean);
	}
	
	@Override 
	public void beforeSave() throws Exception
	{
		Usuario bean=(Usuario)getBean();
		Usuario aux=myService.obtenerUsuario(bean.getUsuario());
		if(aux!=null)
		{
			bean.setId(aux.getId());
			bean.setModificacion(DateHelper.getDate());
			bean.setEstado(Constante.ROW_STATUS_ENABLED);
		}
	}
	
	@Override 
	public void beforeUpdate() throws Exception
	{
		Usuario aux=(Usuario)getBean();
		aux.setModificacion(DateHelper.getDate());
		setBean(aux);
		aux=null;
	}
	
	@Override
	public void defaultList() throws Exception
	{
		Usuario bean=new Usuario();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBeanList(getService().listByObject(bean));
		bean=null;
	}
	
	@Override
	public void afterNew() throws Exception
	{
		Usuario bean=new Usuario();
		bean.setInstitucion(institucion);
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
	}
	
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Usuario object = (Usuario)getBean();
		if(!validateSelect(object.getPerfil()))
		{
			setMessageError("Debe seleccionar un perfil.");			
			success = false;
		}
		else if(!validateSelect(object.getPertenencia()))
		{
			setMessageError("Debe seleccionar al Personal de la institución");
			success = false;
		}
		else if(!validateEmpty(object.getUsuario()))
		{
			setMessageError("Debe ingresar el nombre de usuario.");			
			success = false;
		}
		/*
		else if(!validateEmpty(object.getCorreo()))
		{
			setMessageError("Debe ingresar el correo electrónico del usuario.");			
			success = false;
		}
		*/
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
	
	public SeguridadService getMyService() 								{return myService;}	
	public void setMyService(SeguridadService myService)				{this.myService = myService;}
	
	public List<SelectItem> getPerfilList() 							{return perfilList;}
	public void setPerfilList(List<SelectItem> perfilList) 				{this.perfilList = perfilList;}
	
	public List<SelectItem> getPersonalList()                       {return personalList;}
	public void setPersonalList(List<SelectItem> personalList)      {this.personalList = personalList;}

	public List<Matricula> getMatriculados() {
		return matriculados;
	}

	public void setMatriculados(List<Matricula> matriculados) {
		this.matriculados = matriculados;
	}
	
	
} 

