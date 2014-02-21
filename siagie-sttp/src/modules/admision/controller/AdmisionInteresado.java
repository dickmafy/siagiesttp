package modules.admision.controller;

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
import resouces.ConnPg;
import resouces.Fecha;
import modules.administracion.domain.Institucion;
import modules.administracion.domain.Oferta;
import modules.admision.domain.Interes;
import modules.admision.domain.Interesado;
import modules.admision.domain.Persona;
import modules.seguridad.domain.Usuario;

import com.belogick.factory.util.constant.Constante;
import com.belogick.factory.util.controller.GenericController;
import com.belogick.factory.util.helper.DateHelper;
import com.belogick.factory.util.support.ServiceException;

import dataware.service.AdmisionService;

@ManagedBean
@SessionScoped
public class AdmisionInteresado extends GenericController {
	private AdmisionService myService;
	private Persona beanPersona;
	private Long institucion;
	private Long docTip;
	private String docNro;
	private boolean readonly;

	private List<SelectItem> depList;
	private List<Oferta> profesiones;
	private List<Interes> intereses;

	private String urlRpt; // Codigo Ericson Huamani
	private String nombreUsuario; // Codigo Ericson Huamani
	private String nombreInstitucion;

	@Override
	public void init() throws Exception {
		Usuario usr = (Usuario) getSpringBean("usuarioSesion");
		appName = "Admisión";
		moduleName = "Interesados";
		userName = usr.getUsuario();
		institucion = usr.getInstitucion();
		defaultList();
		page_new = "adm_int_new";
		page_main = "adm_int_list";
		page_update = "adm_int_update";
		profesiones = myService.listarOferta(institucion, DateHelper.getDate(),
				1L);
		depList = getListSelectItem(myService.listarUbigeos(0L, 0L), "id",
				"descripcion", true);
		forward(page_main);
		cleanPersona();

		setUrlRpt("/modulos/reportes/pdf/NOMINA.pdf"); // Codigo Ericson Huamaní
		setNombreUsuario(usr.getNombres()); // Codigo Ericson Huamaní
	}

	@Override
	public void defaultList() throws Exception {
		Interesado bean = new Interesado();
		bean.setInstitucion(institucion);
		setBeanList(myService.listByObjectEnabled(bean));
		bean = null;
	}

	public void optionFindPersona() throws Exception {
		boolean success = true;
		if (!validateSelect(docTip)) {
			setMessageError("Debe seleccionar el Tipo de Documento de Identificación para iniciar con la busqueda.");
			success = false;
		} else if (!validateEmpty(docNro)) {
			setMessageError("Debe ingresar el Número del Documento de Identificación para iniciar con la busqueda.");
			success = false;
		}

		if (success) {
			Persona bean = myService.encontrarPersona(docTip, docNro);
			if (bean != null) {
				beanPersona = bean;
				readonly = true;
			} else {
				setMessageError("La persona no se encuentra registrada en el sistema.");
				cleanPersona();
			}
			bean = null;
		}
	}

	public void beforeSave() throws Exception {
		beanPersona.setDocumento_tipo(docTip);
		beanPersona.setDocumento_nro(docNro);
		Interesado bean = (Interesado) getBean();
		bean.setInstitucion(institucion);
		bean.setFecha(DateHelper.getDate());
		bean.setPersona(((Persona) myService.save(beanPersona)).getId());
		bean.setEstado(Constante.ROW_STATUS_ENABLED);
		setBean(bean);
		bean = null;
	}

	public void afterSave() throws Exception {
		myService.insertarIntereses(profesiones,
				((Interesado) getBeanSave()).getId());
		cleanPersona();
	}

	public void cleanPersona() throws Exception {
		readonly = false;
		beanPersona = new Persona();
		beanPersona.setEstado(Constante.ROW_STATUS_ENABLED);
		docTip = -1L;
		docNro = "";
	}

	public void afterLoad() throws Exception {
		Interesado bean = (Interesado) getBeanSelected();
		beanPersona = (Persona) myService.findById(Persona.class,
				bean.getPersona());
		intereses = myService.obtenerIntereses(bean.getId());
		docTip = beanPersona.getDocumento_tipo();
		docNro = beanPersona.getDocumento_nro();
	}

	@Override
	public boolean validation() throws Exception {
		boolean success = true;
		Interesado object = (Interesado) getBean();
		if (!validatePersona()) {
			success = false;
		}
		if (!validateSelect(object.getMedio())) {
			setMessageError("Debe seleccionar el medio por el cual se genero el interes en la Institución.");
			success = false;
		} else if (!validateSelect(object.getTurno())) {
			setMessageError("Debe seleccionar el turno de interes.");
			success = false;
		} else if (!validateInteres()) {
			success = false;
		}
		object = null;
		return success;
	}

	public boolean validatePersona() throws Exception {
		boolean success = true;
		if (!validateSelect(docTip)) {
			setMessageError("Debe seleccionar el Documento de Identificación.");
			success = false;
		} else if (!validateEmpty(docNro)) {
			setMessageError("Debe ingresar el Número del Documento de Identificación.");
			success = false;
		} else if (!validateEmpty(beanPersona.getNombres())) {
			setMessageError("Debe ingresar los Nombres.");
			success = false;
		} else if (!validateEmpty(beanPersona.getApellido_paterno())) {
			setMessageError("Debe ingresar el Apellido Paterno.");
			success = false;
		} else if (!validateEmpty(beanPersona.getApellido_materno())) {
			setMessageError("Debe ingresar el Apellido Materno.");
			success = false;
		} else if (!validateSelect(beanPersona.getGenero())) {
			setMessageError("Debe seleccionar el Genero de la Persona.");
			success = false;
		} else if (!validateSelect(beanPersona.getEstado_civil())) {
			setMessageError("Debe seleccionar el Estado Civil.");
			success = false;
		} else if (!validateSelect(beanPersona.getNacimiento_lugar())) {
			setMessageError("Debe seleccionar el Lugar de Nacimiento.");
			success = false;
		} else if (!validateEmpty(beanPersona.getNacimiento_fecha())) {
			setMessageError("Debe ingresar la Fecha de Nacimiento.");
			success = false;
		} else if (!validateEmpty(beanPersona.getCorreo())) {
			setMessageError("Debe ingresar el Correo Electrónico correctamente.");
			success = false;
		} else if (!validateEmail(beanPersona.getCorreo().toLowerCase())) {
			setMessageError("Debe ingresar el Correo Electrónico correctamente.");
			success = false;
		} else if (!validateEmpty(beanPersona.getTelefonos())) {
			setMessageError("Debe ingresar al menos un Número Telefonico.");
			success = false;
		} else if (!validateInteres()) {
			setMessageError("Debe seleccionar al menos una Profesión de Interes.");
			success = false;
		}
		return success;
	}

	public boolean validateInteres() {
		for (int i = 0; i < profesiones.size(); i++) {
			if (profesiones.get(i).getCheck() == true) {
				return true;
			}
		}
		return false;
	}

	public AdmisionService getMyService() {
		return myService;
	}

	public void setMyService(AdmisionService myService) {
		this.myService = myService;
	}

	public Long getDocTip() {
		return docTip;
	}

	public void setDocTip(Long docTip) {
		this.docTip = docTip;
	}

	public String getDocNro() {
		return docNro;
	}

	public void setDocNro(String docNro) {
		this.docNro = docNro;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public Persona getBeanPersona() {
		return beanPersona;
	}

	public void setBeanPersona(Persona beanPersona) {
		this.beanPersona = beanPersona;
	}

	public List<SelectItem> getDepList() {
		return depList;
	}

	public void setDepList(List<SelectItem> depList) {
		this.depList = depList;
	}

	public List<Oferta> getProfesiones() {
		return profesiones;
	}

	public void setProfesiones(List<Oferta> profesiones) {
		this.profesiones = profesiones;
	}

	public List<Interes> getIntereses() {
		return intereses;
	}

	public void setIntereses(List<Interes> intereses) {
		this.intereses = intereses;
	}

	public String getUrlRpt() {
		return urlRpt;
	}

	public void setUrlRpt(String urlRpt) {
		this.urlRpt = urlRpt;
	}

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

			File fp = new File(rutaAplicacion
					+ "/modulos/reportes/jasper/rpt_interesados.jasper");
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
