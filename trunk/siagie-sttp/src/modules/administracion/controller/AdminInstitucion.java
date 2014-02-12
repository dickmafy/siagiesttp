package modules.administracion.controller; 

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import resouces.ConnPg;
import resouces.Fecha;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import com.belogick.factory.util.support.Encriptador;

import dataware.service.SeguridadService;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Local;
import modules.administracion.domain.Personal;
import modules.mantenimiento.domain.Puesto;
import modules.mantenimiento.domain.Resolucion;
import modules.seguridad.domain.Usuario;

public class AdminInstitucion extends GenericController   
{	 	
	private Personal	beanPersona;
	private Local		beanLocal;
	
	private	Usuario		beanUsuario;
	private List<SelectItem>    resolucionList;
	private List<SelectItem>    puestoList;
	
	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia;
	
	private SeguridadService	myService;
	private boolean flag;
	
	private String urlRpt; // Codigo Ericson Huamani
	private String nombreUsuario; // Codigo Ericson Huamani
	
	@Override 
	public void init() throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admin";
		moduleName="Institucion ";
		userName=usr.getUsuario();		
		defaultList();
		page_new="itc_new";
		page_main="itc_lst";
		page_update="itc_upd";
		forward(page_main);
		flag=false;
		
		setUrlRpt("/modulos/reportes/pdf/NOMINA.pdf"); // Codigo Ericson Huamanï¿½
		nombreUsuario=usr.getNombres(); // Codigo Ericson Huamanï¿½
	}
		
	@Override
	public void afterNew() throws Exception
	{
		flag=true;
		setEnabled(new Institucion());
		beanPersona=new Personal();
		beanLocal=new Local();
		beanUsuario=new Usuario();
		
		puestoList=getListSelectItem(myService.listByObjectEnabled(new Puesto()), "id","nombre",true);
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
		
		fillDep();
	}
	
	@Override
	public void afterLoad() throws Exception
	{
		puestoList=getListSelectItem(myService.listByObjectEnabled(new Puesto()), "id","nombre",true);
		resolucionList=getListSelectItem(myService.listByObjectEnabled(new Resolucion()), "id","nombreSiglas,nombre"," ",false);
	}
	
	@Override
	public void afterSave() throws Exception
	{
		 if(flag)
		 {
			 Institucion inst=(Institucion)getBeanSave();
			 beanPersona.setInstitucion(inst.getId());
			 beanPersona.setEstado(Constante.ROW_STATUS_ENABLED);
			 beanLocal.setInstitucion(inst.getId());
			 beanLocal.setEstado(Constante.ROW_STATUS_ENABLED);
			 beanLocal.setPrincipal(true);
			 beanLocal.setNombre("Principal");
			 
			 beanUsuario.setUsuario(inst.getCodigo());
			 beanUsuario.setContrasena(Encriptador.encryptBlowfish(inst.getCodigo(), Constante.KEY));
			 beanUsuario.setInstitucion(inst.getId());
			 beanUsuario.setNombres(inst.getNombreFormacion()+" "+inst.getNombre());
			 beanUsuario.setCorreo(inst.getCorreo());
			 beanUsuario.setCreacion(DateHelper.getDate());
			 
			//2 = formacion cetpro
			 if(inst.getFormacion().equals(2L)) 
			 {
				//3 = perfil admin cetpro
				 beanUsuario.setPerfil(3L);
			 }
			 else
			 {
				//2 = perfil admin iest
				 beanUsuario.setPerfil(2L);
					 
			 }
			 
			 
			 
			 
			 beanUsuario.setEstado(Constante.ROW_STATUS_ENABLED);
			 
			 beanPersona=(Personal)getService().save(beanPersona);
			 beanLocal.setEncargado(beanPersona.getId());
			 
			 getService().save(beanLocal);
			 getService().save(beanUsuario);
		 }
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Institucion object = (Institucion)getBean();
		
		if(!validateSelect(object.getFormacion()))
		{
			setMessageError("Debe ingresar el Centro de formacion.");			
			success = false;
		}
		else if(!validateSelect(object.getResolcre()))
		{
			setMessageError("Debe seleccionar la Resolucion de Creacion.");			
			success = false;
		}
		else if(!validateSelect(object.getGestion()))
		{
			setMessageError("Debe ingresar el tipo de Gestion.");			
			success = false;
		}
		else if(!validateEmpty(object.getCodigo()))
		{
			setMessageError("Debe ingresar el Código Modular.");			
			success = false;
		}
		else if(!validateEmpty(object.getNombre()))
		{
			setMessageError("Debe ingresar el Nombre de la Institucion.");			
			success = false;
		}
		else if(!validateEmpty(object.getRuc()))
		{
			setMessageError("Debe ingresar el RUC.");			
			success = false;
		}
		else if(flag && !validateSelect(provincia))
		{
			setMessageError("Debe seleccionar la Provincia de la Dirección de la Institución.");			
			success = false;
		}
		else if(flag && !validateSelect(departamento))
		{
			setMessageError("Debe seleccionar el Departamento de la Dirección de la Institución.");			
			success = false;
		}
		else if(flag && !validateSelect(beanLocal.getUbigeo()))
		{
			setMessageError("Debe seleccionar el Distrito de la Dirección de la Institución.");			
			success = false;
		}
		else if(flag && !validateEmpty(beanLocal.getDireccion()))
		{
			setMessageError("Debe ingresar la dirección de la Institución.");			
			success = false;
		}
		else if(flag && !validateEmpty(beanPersona.getCodigo()))
		{
			setMessageError("Debe ingresar el Código Modular del Encargado.");			
			success = false;
		}
		else if(flag && !validateEmpty(beanPersona.getDni()))
		{
			setMessageError("Debe ingresar el número de DNI del Encargado.");			
			success = false;
		}
		else if(flag && !validateLength(beanPersona.getDni(), 8))
		{
			setMessageError("El número de DNI del Encargado no puede exceder los 8 dígitos.");			
			success = false;
		}
		else if(flag && !validateEmpty(beanPersona.getNombres()))
		{
			setMessageError("Debe ingresar los Nombres del Encargado.");			
			success = false;
		}
		else if(flag && !validateEmpty(beanPersona.getApepat()))
		{
			setMessageError("Debe ingresar el Apellido Paterno del Encargado.");			
			success = false;
		}
		else if(flag && !validateEmpty(beanPersona.getApemat()))
		{
			setMessageError("Debe ingresar el Apellido Materno del Encargado.");			
			success = false;
		}
		object=null;
		return success;
	}  

	public void optionGoPersonal() throws Exception
	{
		AdminPersonal go = (AdminPersonal)getSpringBean("adminPersonal");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto());
	}
	
	public void optionGoLocales() throws Exception
	{
		AdminLocal go = (AdminLocal)getSpringBean("adminLocal");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto());
	}

	public void optionGoAmbientes() throws Exception
	{
		AdminAmbiente go = (AdminAmbiente)getSpringBean("adminAmbiente");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto());
	}
	
	public void optionGoOferta() throws Exception
	{
		AdminOferta go = (AdminOferta)getSpringBean("adminOferta");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto(), ((Institucion)getBeanSelected()).getFormacion().longValue());
	}
	
	public void optionGoCronograma() throws Exception
	{
		AdminProceso go = (AdminProceso)getSpringBean("adminProceso");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto());
	}
	
	public void optionGoConvenio() throws Exception
	{
		AdminConvenio go = (AdminConvenio)getSpringBean("adminConvenio");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto());
	}
	
	public void optionGoMetaInstitucional() throws Exception
	{
		AdminMetaInstitucional go = (AdminMetaInstitucional)getSpringBean("adminMetaInstitucional");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto(), ((Institucion)getBeanSelected()).getFormacion().longValue());
	}
	
	public void optionGoMetaOcupacional() throws Exception
	{
		AdminMetaOcupacional go = (AdminMetaOcupacional)getSpringBean("adminMetaCetpro");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto(), ((Institucion)getBeanSelected()).getFormacion().longValue());
	}
	
	public void optionGoUsuarios() throws Exception
	{
		AdminUsuario go = (AdminUsuario)getSpringBean("adminUsuario");
		go.init(((Institucion)getBeanSelected()).getId().longValue(), ((Institucion)getBeanSelected()).getNombreCompleto());
	}
	
	public void fillDep() throws Exception
	{
		depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
		proList=new ArrayList<SelectItem>();
		disList=new ArrayList<SelectItem>();
		
		beanLocal.setUbigeo(-1L);
		departamento=-1L;
		provincia=-1L;
	}
	
	public void fillPro() throws Exception
	{
		proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
		disList=new ArrayList<SelectItem>();
		
		beanLocal.setUbigeo(-1L);
		provincia=-1L;
	}
	
	public void fillDis() throws Exception
	{
		disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		beanLocal.setUbigeo(-1L);
	}
	
	
	
	public Personal getBeanPersona() 				 			{return beanPersona;}
	public void setBeanPersona(Personal beanPersona) 			{this.beanPersona = beanPersona;}

	public Local getBeanLocal() 								{return beanLocal;}
	public void setBeanLocal(Local beanLocal) 					{this.beanLocal = beanLocal;}
	
	public List<SelectItem> getDepList() 						{return depList;}
	public void setDepList(List<SelectItem> depList) 			{this.depList = depList;}
	public List<SelectItem> getProList() 						{return proList;}
	public void setProList(List<SelectItem> proList) 			{this.proList = proList;}
	public List<SelectItem> getDisList() 						{return disList;}
	public void setDisList(List<SelectItem> disList) 			{this.disList = disList;}
	public Long getDepartamento() 								{return departamento;}
	public void setDepartamento(Long departamento) 				{this.departamento = departamento;}
	public Long getProvincia() 									{return provincia;}
	public void setProvincia(Long provincia) 					{this.provincia = provincia;}
	
	public List<SelectItem> getResolucionList() 					{return resolucionList;}
	public void setResolucionList(List<SelectItem> resolucionList) 	{this.resolucionList = resolucionList;}

	public List<SelectItem> getPuestoList() 					{return puestoList;}
	public void setPuestoList(List<SelectItem> puestoList) 		{this.puestoList = puestoList;}

	public SeguridadService getMyService() 						{return myService;}	
	public void setMyService(SeguridadService myService)		{this.myService = myService;}

	public String getUrlRpt() {
		return urlRpt;
	}

	public void setUrlRpt(String urlRpt) {
		this.urlRpt = urlRpt;
	}
	
	// Inicio Codigo Ericson Huamanï¿½ 19-12-2013 11:00
			@SuppressWarnings("unchecked")
			public void generarReporte(ActionEvent evt) {			
					try {
						String rutaAplicacion = FacesContext.getCurrentInstance()
								.getExternalContext().getRealPath("/");
						String nombreArchivoPdf = new Fecha().getFecha(new Date(),
								Fecha.PATTERN_DDMMYYYYHHMMS, Fecha.LOCALE_ES) + ".pdf";
						JasperPrint print = null;
						
						@SuppressWarnings("rawtypes")
						Map parametro = new HashMap();
						parametro.put("TITULO_REPORTE", "Listado de Institutos");
						parametro.put("TIPO_INSTITUCION",1L);
						parametro.put("USUARIO", nombreUsuario);
						parametro.put("RUTA_IMAGEN",rutaAplicacion+"/recursos/imagenes/sicad_1_rpt.jpg");
						
						File fp = new File(rutaAplicacion
								+ "/modulos/reportes/jasper/rpt_instituciones.jasper");
						InputStream reportSt = new BufferedInputStream(
								new FileInputStream(fp));
						print = JasperFillManager.fillReport(reportSt, parametro,
								ConnPg.getConexion());
						OutputStream output = new FileOutputStream(new File(
								rutaAplicacion + "/modulos/reportes/pdf/"
										+ nombreArchivoPdf));
						JasperExportManager.exportReportToPdfStream(print, output);
						urlRpt = "/modulos/reportes/pdf/" + nombreArchivoPdf;
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

			// Fin codigo Ericson Huamanï¿½
} 
