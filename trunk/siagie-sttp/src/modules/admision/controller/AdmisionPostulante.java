package modules.admision.controller; 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.primefaces.model.UploadedFile;

import resouces.ConnPg;
import resouces.Fecha;
import modules.administracion.domain.Institucion;
import modules.admision.domain.Persona;
import modules.admision.domain.Postulante;
import modules.admision.domain.Proceso;
import modules.admision.domain.ProcesoOferta;
import modules.admision.domain.Requisitos;
import modules.mantenimiento.domain.Banco;
import modules.mantenimiento.domain.Ubigeo;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.BeanHelper;
import com.belogick.factory.util.helper.DateHelper;
import com.belogick.factory.util.support.ServiceException;

import dataware.service.AdmisionService;

@ManagedBean
@SessionScoped
public class AdmisionPostulante extends GenericController   
{
	private AdmisionService	myService;
	private List<SelectItem>    procesoList;
	private List<SelectItem>    bancoList;	
	private	List<Requisitos> 	requisitos;
	private List<ProcesoOferta>	ofertas;
	private UploadedFile 		foto;	
	
	private	Long 	registrante;
	private Long 	institucion;
	private Long 	annio;
	private Long 	proceso;
	private	Long	tabIndex;
	
	private	Long 	docTip;
	private String 	docNro;
	private Persona beanPersona;
	private boolean readonly;	
	private boolean enabled;
	
	private List<SelectItem> depList;
	private List<SelectItem> proList;
	private List<SelectItem> disList;
	private Long departamento, provincia;
	
	private String urlRpt; // Codigo Ericson Huamani
	private String nombreUsuario; // Codigo Ericson Huamani
	private String nombreInstitucion;
	
	public void init(Long id) throws Exception 
	{
		Usuario usr = (Usuario)getSpringBean("usuarioSesion");
		appName="Admision";
		moduleName="Postulante";
		userName=usr.getUsuario();
		registrante=usr.getId();
		institucion=usr.getInstitucion();
		proceso=id;
		annio=Long.parseLong(Calendar.getInstance().get(Calendar.YEAR)+"");
		
		page_new="adm_pos_new";
		page_main="adm_pos_lst";
		page_update="adm_pos_upd";	
		tabIndex=0L;
		
		requisitos = new ArrayList();
		procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
		bancoList=getListSelectItem(new Banco(),"id","nombre",true);
		optionSelectProceso();
		cleanPersona();
		forward(page_main);
		
		setUrlRpt("/modulos/reportes/pdf/NOMINA.pdf"); // Codigo Ericson Huamaní
		setNombreUsuario(usr.getNombres()); // Codigo Ericson Huamaní
	}
	public void init() throws Exception
	{init(-1L);}
	
	public void selectAnnio() throws Exception
	{
		proceso=-1L;
		procesoList=getListSelectItem(myService.listarProcesos(institucion,annio),"id","nombrePeriodo",true);
	}
	
	public void defaultList() throws Exception
	{setBeanList(myService.listarPostulante(proceso, 1L));}
	
	
	public void afterNew() throws Exception
	{
		cleanPersona();
		ofertas=myService.listarProcesoOferta(proceso);
		requisitos = new ArrayList();
		tabIndex=0L;
	}	
	public void cleanPersona() throws Exception
	{
		readonly=false;
		beanPersona=new Persona();
		beanPersona.setEstado(Constante.ROW_STATUS_ENABLED);
		docTip=-1L;
		docNro="";
		fillDep();
	}
	
	public void optionSelectProceso() throws Exception
	{
		if(proceso.longValue()>0L)	
		{	
			defaultList();
			ofertas=myService.listarProcesoOferta(proceso);
		}
		else						
		{setBeanList(new ArrayList());}
		enabledOptions();
	}
	public void enabledOptions() throws Exception
	{
		if(proceso.longValue()>0L)
		{
			Proceso bean=(Proceso)myService.findById(Proceso.class, proceso);
			if(bean.getEstado().longValue()==3L)	{enabled=true;}
			bean=null;
		}
		else
		{enabled=false;}
	}
	
	public void beforeSave() throws Exception
	{
		beanPersona.setDocumento_tipo(docTip);
		beanPersona.setDocumento_nro(docNro);
		beanPersona=(Persona)BeanHelper.toUpperCase(getBeanPersona());
		beanPersona=((Persona)myService.save(beanPersona));
		
		ProcesoOferta prof=new ProcesoOferta(); 
		for(int i=0; i<ofertas.size(); i++)
		{
			if(ofertas.get(i).isCheck())
			{prof=ofertas.get(i);}
		}
		
		Postulante bean=(Postulante)getBean();
		bean.setProceso(proceso);
		bean.setRegfec(DateHelper.getDate());
		bean.setRegusr(registrante);
		bean.setProfesion(prof.getProfesion());
		bean.setTurno(prof.getTurno());
		bean.setPersona(beanPersona.getId());
		bean.setEstado(getEstado());
		setBean(bean);
		bean=null;
	}
	
	public void afterSave() throws Exception
	{
		myService.insertarRequisitos(true, requisitos, ((Postulante)getBeanSave()).getId());
		uploadPic();
	}
	public void nativeDetail() throws Exception
	{
		setBean(getBeanSelected());
		afterLoad();
		forward("adm_pos_det");
	}
	
	public void afterLoad() throws Exception
	{
		
		Postulante bean=(Postulante)getBean();
		beanPersona=(Persona)myService.findById(Persona.class, bean.getPersona());
		docTip=beanPersona.getDocumento_tipo();
		docNro=beanPersona.getDocumento_nro();
		requisitos=myService.listarRequisitosPostulante(bean.getId(), institucion, bean.getModalidad());
		
		depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
		if(beanPersona.getUbigeo()!=null)
		{
			Ubigeo ubi=(Ubigeo) myService.findById(Ubigeo.class, beanPersona.getUbigeo());
			departamento = ubi.getDepartamento();
			provincia = ubi.getProvincia();
			depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
			proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
			disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		}
		else
		{
			departamento=-1L;
			provincia=-1L;
		}
	}

	public void beforeUpdate() throws Exception
	{
		beanPersona=(Persona)BeanHelper.toUpperCase(getBeanPersona());
		beanPersona=((Persona)myService.save(beanPersona));
		
		Postulante bean=(Postulante)getBean();
		bean.setEstado(getEstado());
		setBean(bean);
		myService.eliminarRequisito(true, bean.getId());
		myService.insertarRequisitos(true, requisitos, bean.getId());
		uploadPic();
		bean=null;
	}
	
	public void optionPublicar() throws Exception
	{
		Postulante bean=(Postulante)getBeanSelected();
		myService.updateStatus(bean, 4L);
		setMessageSuccess("La ficha de postulante se completo satisfactoriamente.");
		defaultList();
	}
	public void optionAnular() throws Exception
	{
		Postulante bean=(Postulante)getBeanSelected();
		myService.updateStatus(bean, 0L);
		setMessageSuccess("El postulante fue anulado exitosamente.");
		defaultList();
	}
	
	public void optionFindPersona() throws Exception
	{
		tabIndex=1L;
    	boolean success=true;
    	if(!validateSelect(docTip))
    	{
    		setMessageError("Debe seleccionar el Tipo de Documento de Identificación para iniciar con la busqueda.");
    		success=false;
    	}
    	else if(!validateEmpty(docNro))
    	{
    		setMessageError("Debe ingresar el Número del Documento de Identificación para iniciar con la busqueda.");
    		success=false;
    	}
    	
    	if(success)
    	{
    		Persona bean=myService.encontrarPersona(docTip, docNro);
			if(bean!=null)	
			{
				beanPersona=bean;
				readonly=true;
			}
			else			
			{
				setMessageError("La persona no se encuentra registrada en el sistema.");
				cleanPersona();
			}
			bean=null;
		}
	}
	
	
	public void optionSelectModalidad() throws Exception
	{
		Postulante bean=(Postulante)getBean();
		requisitos=myService.listarRequisitos(institucion, 1L, bean.getModalidad());
		bean=null;
	}
	
	public Long getEstado()
	{
		for(int i=0; i<requisitos.size(); i++)
		{
			if(!requisitos.get(i).getCheck())
			{return 1L;}
		}
		if(!validateEmpty(((Postulante)getBean()).getPago_fecha()) || !validateEmpty(((Postulante)getBean()).getPago_recibo()) || !validateSelect(((Postulante)getBean()).getPago_banco()))
		{return 2L;}
		return 3L;
	}
	
	@Override
	public boolean validation() throws Exception 
	{
		boolean success = true;
		Postulante object = (Postulante)getBean();
		if(!validationProfesion() && object.getProfesion()==null)
        {
			tabIndex=0L;
			setMessageError("Debe seleccionar solo una Oferta de Admisión.");                        
            success = false;
        }
		else if(!validationPersona())
		{
			tabIndex=1L;
			success = false;
		}
		else if(!validateSelect(object.getModalidad()))
		{
			tabIndex=3L;
			setMessageError("Debe seleccionar una Modalidad del Proceso de Admisión en Requisitos.");			
			success = false;
		}
		object=null;
		return success;
	}
	 public boolean validationProfesion() throws Exception 
     {
             int cont=0;
             for(int i=0; i<ofertas.size(); i++)
             {
                     if(ofertas.get(i).isCheck())
                     {cont++;}
             }
             if(cont==1)     {return true;}
             else            {return false;}
     }
	public boolean validationPersona() throws Exception 
	{
		boolean success = true;
		if(!validateDocumento())
		{success=false;}
		else if(!validateEmpty(beanPersona.getNombres()))
		{
			setMessageError("Debe ingresar sus Nombres.");			
			success = false;
		}
		else if(!validateEmpty(beanPersona.getApellido_paterno()))
		{
			setMessageError("Debe ingresar su Apellido Paterno.");			
			success = false;
		}
		else if(!validateEmpty(beanPersona.getApellido_materno()))
		{
			setMessageError("Debe ingresar su Apellido Materno.");			
			success = false;
		}
		else if(!validateSelect(beanPersona.getGenero()))
		{
			setMessageError("Debe seleccionar el Genero de la Persona.");			
			success = false;
		}
		else if(!validateSelect(beanPersona.getEstado_civil()))
		{
			setMessageError("Debe seleccionar el Estado Civil.");			
			success = false;
		}
		else if(!validateSelect(beanPersona.getNacimiento_lugar()))
		{
			setMessageError("Debe seleccionar el Lugar de Nacimiento.");			
			success = false;
		}
		else if(!validateEmpty(beanPersona.getNacimiento_fecha()))
		{
			setMessageError("Debe ingresar su Fecha de Nacimiento.");			
			success = false;
		}
		else if(!validateEmpty(beanPersona.getCorreo()))
		{
			setMessageError("Debe ingresar su Correo Electrónico.");			
			success = false;
		}
		else if(!validateEmail(beanPersona.getCorreo().toLowerCase()))
		{
			setMessageError("Debe ingresar su Correo Electrónico correctamente.");			
			success = false;
		}
		else if(!validateEmpty(beanPersona.getTelefonos()))
		{
			setMessageError("Debe ingresar al menos un Número Telefonico.");			
			success = false;
		}		
		else if(!validateSelect(provincia))
		{
			setMessageError("Debe seleccionar la provincia.");			
			success = false;
		}
		else if(!validateSelect(departamento))
		{
			setMessageError("Debe seleccionar el departamento.");			
			success = false;
		}
		else if(!validateSelect(beanPersona.getUbigeo()))
		{
			setMessageError("Debe seleccionar el distrito.");			
			success = false;
		}
		else if(!validateEmpty(beanPersona.getDireccion()))
		{
			setMessageError("Debe ingresar la dirección.");			
			success = false;
		}	
		else if(!validateSelect(beanPersona.getColegio_tipo()))
		{
			setMessageError("Debe seleccionar el Tipo de Colegio de Procedencia");			
			success = false;
		}
		else if(!validateEmpty(beanPersona.getColegio()))
		{
			setMessageError("Debe ingresar el Nombre del Colegio de Procedencia.");			
			success = false;
		}
		return success;
	}
	
	public boolean validateDocumento() throws Exception 
	{		
		boolean success = true;
		if(!validateSelect(docTip))		{setMessageError("Debe seleccionar un Tipo de Documento de Identidad"); success=false;}
		else if(!validateEmpty(docNro))	{setMessageError("Debe ingresar el número de Documento de Identidad"); success=false;}
		else if(docTip.longValue()==1L && !validateLength(docNro, 8))		{setMessageError("El número de DNI debe ser de 8 dígitos"); success=false;}
		else if(docTip.longValue()==3L && !validateLength(docNro, 12))		{setMessageError("El número de Carnet de Extranjería debe ser de 12 dígitos"); success=false;}
		else if(docTip.longValue()==4L && !validateLength(docNro, 8))		{setMessageError("El número de Pasaporte debe ser de 12 dígitos"); success=false;}
		return success;
	}
	
	
	public void fillDep() throws Exception
	{
		depList=getListSelectItem(myService.listarUbigeos(0L,0L), "departamento", "descripcion", true);
		proList=new ArrayList<SelectItem>();
		disList=new ArrayList<SelectItem>();
		beanPersona.setUbigeo(-1L);
		departamento=-1L;
		provincia=-1L;
	}
	
	public void fillPro() throws Exception
	{
		proList=getListSelectItem(myService.listarUbigeos(departamento,0L), "provincia", "descripcion", true);
		disList=new ArrayList<SelectItem>();
		beanPersona.setUbigeo(-1L);
		provincia=-1L;
	}

	public void fillDis() throws Exception
	{
		disList=getListSelectItem(myService.listarUbigeos(departamento,provincia), "id", "descripcion", true);
		beanPersona.setUbigeo(-1L);
	}
	
	public String getPic()
	{
		try
		{			
			File fotoFile = new File(getServletContext().getRealPath("/recursos/fotos/"+beanPersona.getId()+".png"));
			if(fotoFile.isFile()) return "/recursos/fotos/"+beanPersona.getId()+".png";
			else return "/recursos/fotos/default.png";
		}
		catch(Exception e)
		{return "/recursos/fotos/default.png";}
		
	}
	
	public void uploadPic() throws IOException
	{
		if(foto != null) 
		{
			File docFile = new File(getServletContext().getRealPath("/recursos/logos/"+beanPersona.getId()+".png"));
			OutputStream out = new FileOutputStream(docFile, true);
			out.write(foto.getContents());
			out.close();
        }
	}
	
	
	public AdmisionService getMyService()						{return myService;}
	public void setMyService(AdmisionService myService) 		{this.myService = myService;}
	
	public Long getProceso() 									{return proceso;}
	public void setProceso(Long proceso) 						{this.proceso = proceso;}

	public List<SelectItem> getProcesoList() 					{return procesoList;}
	public void setProcesoList(List<SelectItem> procesoList) 	{this.procesoList = procesoList;}
	public List<SelectItem> getBancoList() 						{return bancoList;}
	public void setBancoList(List<SelectItem> bancoList) 		{this.bancoList = bancoList;}
	
	public Persona getBeanPersona() 							{return beanPersona;}
	public void setBeanPersona(Persona beanPersona) 			{this.beanPersona = beanPersona;}	
	public Long getDocTip() 									{return docTip;}
	public void setDocTip(Long docTip) 							{this.docTip = docTip;}
	public String getDocNro() 									{return docNro;}
	public void setDocNro(String docNro) 						{this.docNro = docNro;}
	public boolean isReadonly() 								{return readonly;}
	public void setReadonly(boolean readonly) 					{this.readonly = readonly;}
	public boolean isEnabled() 									{return enabled;}
	public void setEnabled(boolean enabled) 					{this.enabled = enabled;}
	
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
	
	public List<ProcesoOferta> getOfertas() 					{return ofertas;}
	public void setOfertas(List<ProcesoOferta> ofertas) 		{this.ofertas = ofertas;}
	public List<Requisitos> getRequisitos() 					{return requisitos;}
	public void setRequisitos(List<Requisitos> requisitos) 		{this.requisitos = requisitos;}
	
	public UploadedFile getFoto()								{return foto;}
	public void setFoto(UploadedFile foto) 						{this.foto = foto;}

	public Long getTabIndex() 									{return tabIndex;}
	public void setTabIndex(Long tabIndex) 						{this.tabIndex = tabIndex;}
	
	public Long getAnnio() 										{return annio;}
	public void setAnnio(Long annio) 							{this.annio = annio;}
	
	
	// Inicio Codigo Ericson Huamaní 19-12-2013 11:00
		@SuppressWarnings("unchecked")
		public void generarReporte(ActionEvent evt) {
			try {
				String rutaAplicacion = FacesContext.getCurrentInstance()
						.getExternalContext().getRealPath("/");
				String nombreArchivoPdf = new Fecha().getFecha(new Date(),
						Fecha.PATTERN_DDMMYYYYHHMMS, Fecha.LOCALE_ES) + ".pdf";
				JasperPrint print = null;

				nombreInstitucion = ((Institucion) myService.findById(
						Institucion.class, institucion)).getNombre();

				@SuppressWarnings("rawtypes")
				Map parametro = new HashMap();
				parametro.put("PK_INSTITUCION", institucion);
				parametro.put("NOMBRE_INSTITUTO", getNombreInstitucion());
				parametro.put("USUARIO", nombreUsuario);
				parametro.put("RUTA_IMAGEN", rutaAplicacion
						+ "/recursos/imagenes/sicad_1_rpt.jpg");
				parametro.put("ANIO", annio);
				parametro.put("PROCESO", proceso);
				File fp = new File(rutaAplicacion
						+ "/modulos/reportes/jasper/rpt_postulantes.jasper");
				InputStream reportSt = new BufferedInputStream(new FileInputStream(
						fp));
				print = JasperFillManager.fillReport(reportSt, parametro,
						ConnPg.getConexion());
				OutputStream output = new FileOutputStream(new File(rutaAplicacion
						+ "/modulos/reportes/pdf/" + nombreArchivoPdf));
				JasperExportManager.exportReportToPdfStream(print, output);
				setUrlRpt("/modulos/reportes/pdf/" + nombreArchivoPdf);
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
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	public String getUrlRpt() {
		return urlRpt;
	}
	public void setUrlRpt(String urlRpt) {
		this.urlRpt = urlRpt;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombreInstitucion() {
		return nombreInstitucion;
	}
	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}
	
} 