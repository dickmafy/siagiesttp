/*     */ package com.belogick.factory.util.controller;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.jasperreports.engine.JRException;
/*     */ import net.sf.jasperreports.engine.JRExporter;
/*     */ import net.sf.jasperreports.engine.JRExporterParameter;
/*     */ import net.sf.jasperreports.engine.JRRuntimeException;
/*     */ import net.sf.jasperreports.engine.JasperCompileManager;
/*     */ import net.sf.jasperreports.engine.JasperExportManager;
/*     */ import net.sf.jasperreports.engine.JasperFillManager;
/*     */ import net.sf.jasperreports.engine.JasperPrint;
/*     */ import net.sf.jasperreports.engine.JasperReport;
/*     */ import net.sf.jasperreports.engine.design.JasperDesign;
/*     */ import net.sf.jasperreports.engine.export.JRHtmlExporter;
/*     */ import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
/*     */ import net.sf.jasperreports.engine.export.JRXlsExporter;
/*     */ import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
/*     */ import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
/*     */ import net.sf.jasperreports.engine.xml.JRXmlLoader;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class GenericReport extends GenericController
/*     */ {
/*     */   private Map parameters;
/*     */   private String jasperFile;
/*     */   private String typeExportFile;
/*     */   private String reportName;
/*  46 */   private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-hhmmss");
/*     */   private String destination;
/*     */   private boolean inHardDisk;
/*     */ 
/*     */   public void createReport()
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/*  55 */       ServletOutputStream outputStream = null;
/*  56 */       Calendar hoy = null;
/*  57 */       log.info("SE COMIENZA CON EL PROCESO DE CREACIÓN DE ARCHIVOS");
/*  58 */       byte[] bytes = null;
/*     */ 
/*  60 */       if ((getJasperFile() == null) || (getJasperFile().equals(""))) throw new Exception("No se ha definido la ubicación del archivo jasper.");
/*  61 */       log.info("JASPERFILE : " + getJasperFile());
/*  62 */       if (getTypeExportFile() == null) setTypeExportFile("__pdf__");
/*  63 */       log.info("TIPO DE ARCHIVO : " + getTypeExportFile());
/*  64 */       File reportFile = new File(getJasperFile());
/*     */ 
/*  66 */       if (!reportFile.exists()) throw new JRRuntimeException("El archivo jasper no existe en la siguiente ubicación definida.");
/*     */ 
/*  68 */       InputStream input = new FileInputStream(new File(getJasperFile()));
/*  69 */       JasperDesign jasperDesign = JRXmlLoader.load(input);
/*  70 */       JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*  71 */       jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
/*     */ 
/*  75 */       Connection conn = getConnection();
/*  76 */       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, getParameters(), conn);
/*  77 */       conn.close();
/*  78 */       conn = null;
/*  79 */       log.info("EL REPORTE HA SIDO LLENADO CON DATOS");
/*  80 */       JRExporter exporter = null;
/*  81 */       PrintWriter out = null;
/*     */ 
/*  83 */       if ((getReportName() == null) || (getReportName().equals("")))
/*     */       {
/*  85 */         hoy = new GregorianCalendar();
/*  86 */         setReportName("reporte-" + this.df.format(hoy.getTime()));
/*     */       }
/*  88 */       log.info("NOMBRE DEL REPORTE : " + getReportName());
/*     */ 
/*  90 */       if (getTypeExportFile().equals("__pdf__"))
/*     */       {
/*  92 */         log.info("SE REALIZARA LA EXPORTACION A PDF");
/*  93 */         if (!isInHardDisk())
/*     */         {
/*  95 */           getResponse().setContentType("application/pdf");
/*  96 */           getResponse().addHeader("Content-Disposition", "attachment; filename=\"" + getReportName() + ".pdf\"");
/*  97 */           bytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*  98 */           outputStream = getResponse().getOutputStream();
/*  99 */           getResponse().setContentLength(bytes.length);
/* 100 */           outputStream.write(bytes);
/* 101 */           outputStream.flush();
/* 102 */           outputStream.close();
/* 103 */           FacesContext.getCurrentInstance().responseComplete();
/* 104 */           log.info("ARCHIVO EXPORTADO SATISFACTORIAMENTE");
/* 105 */           return;
/*     */         }
/*     */ 
/* 109 */         if (getDestination() == null)
/* 110 */           throw new Exception("La ruta destino del reporte no ha sido especificada.");
/* 111 */         JasperExportManager.exportReportToPdfFile(jasperPrint, getDestination() + "\\" + getReportName() + ".pdf");
/*     */       }
/* 115 */       else if (getTypeExportFile().equals("__excel__"))
/*     */       {
/* 117 */         log.info("SE REALIZARA LA EXPORTACION A EXCEL");
/*     */ 
/* 119 */         getResponse().setContentType("application/vnd.ms-excel");
/* 120 */         getResponse().addHeader("Content-Disposition", "attachment; filename=" + getReportName() + ".xls");
/*     */ 
/* 122 */         exporter = new JRXlsExporter();
/* 123 */         exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
/* 124 */         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, getResponse().getOutputStream());
/* 125 */         exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.valueOf(true));
/* 126 */         exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.valueOf(true));
/* 127 */         exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.valueOf(true));
/*     */       } else {
/* 129 */         if (getTypeExportFile().equals("__html__"))
/*     */         {
/* 131 */           getResponse().setContentType("text/html");
/* 132 */           getResponse().addHeader("Content-Disposition", "attachment; filename=" + getReportName() + ".html");
/*     */ 
/* 134 */           out = getResponse().getWriter();
/* 135 */           exporter = new JRHtmlExporter();
/* 136 */           exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
/* 137 */           exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
/* 138 */           exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.valueOf(false));
/* 139 */           exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "charset=ISO-8859-1");
/* 140 */           exporter.exportReport();
/* 141 */           out.flush();
/* 142 */           out.close();
/* 143 */           FacesContext.getCurrentInstance().responseComplete();
/* 144 */           return;
/*     */         }
/*     */ 
/* 147 */         throw new Exception("Tipo de archivo para exportar el reporte es desconocido.");
/* 148 */       }if (!isInHardDisk())
/*     */       {
/* 150 */         exporter.exportReport();
/* 151 */         outputStream = getResponse().getOutputStream();
/* 152 */         outputStream.flush();
/* 153 */         outputStream.close();
/* 154 */         FacesContext.getCurrentInstance().responseComplete();
/*     */       }
/* 156 */       log.info("ARCHIVO EXPORTADO SATISFACTORIAMENTE");
/*     */ 
/* 160 */       input = null;
/* 161 */       jasperDesign = null;
/* 162 */       jasperReport = null;
/* 163 */       jasperPrint = null;
/* 164 */       exporter = null;
/* 165 */       out = null;
/* 166 */       hoy = null;
/* 167 */       bytes = null;
/* 168 */       outputStream = null;
/*     */     }
/*     */     catch (JRException e)
/*     */     {
/* 174 */       throw new Exception("Error al exportar y generar la vista del reporte : ");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void beforeGenerate(ActionEvent event) throws Exception {
/*     */   }
/*     */ 
/*     */   public void afterGenerate(ActionEvent event) {
/*     */   }
/*     */ 
/*     */   public void doGenerate(ActionEvent event) throws Exception {
/*     */     try { beforeCreateReport();
/* 186 */       createReport();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 192 */       throw new Exception("Error en la generación del reporte : ");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void beforeCreateReport()
/*     */   {
/* 198 */     if ((this.parameters != null) && (this.typeExportFile != null))
/*     */     {
/* 200 */       if ((this.typeExportFile.equals("__excel__")) || (this.typeExportFile.equals("__html__")))
/* 201 */         this.parameters.put("IS_IGNORE_PAGINATION", new Boolean(true));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void generate(ActionEvent event)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 218 */       beforeGenerate(event);
/* 219 */       if (validate())
/*     */       {
/* 221 */         doGenerate(event);
/* 222 */         afterGenerate(event);
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 227 */       setMessageError("Error en la generaciön del reporte");
/*     */ 
/* 229 */       throw e;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Connection getConnection() throws Exception
/*     */   {
/* 235 */     ResourceBundle rb = ResourceBundle.getBundle("/resources/jdbc");
/* 236 */     Properties props = new Properties();
/* 237 */     Class.forName(rb.getString("jdbc.driverClassName"));
/* 238 */     props.setProperty("user", rb.getString("jdbc.username"));
/* 239 */     props.setProperty("password", rb.getString("jdbc.password"));
/* 240 */     return DriverManager.getConnection(rb.getString("jdbc.url"), props);
/*     */   }
/*     */ 
/*     */   public void setExportFileType(String fileType)
/*     */   {
/* 250 */     setTypeExportFile(fileType);
/*     */   }
/*     */ 
/*     */   public boolean validate()
/*     */     throws Exception
/*     */   {
/* 268 */     return true;
/*     */   }
/* 270 */   public Map getParameters() { return this.parameters; } 
/* 271 */   public void setParameters(Map parameters) { this.parameters = parameters; } 
/*     */   public String getTypeExportFile() {
/* 273 */     return this.typeExportFile; } 
/* 274 */   public void setTypeExportFile(String typeExportFile) { this.typeExportFile = typeExportFile; } 
/*     */   public String getReportName() {
/* 276 */     return this.reportName; } 
/* 277 */   public void setReportName(String reportName) { this.reportName = reportName; } 
/*     */   public String getJasperFile() {
/* 279 */     return this.jasperFile; } 
/* 280 */   public void setJasperFile(String jasperFile) { this.jasperFile = jasperFile; } 
/*     */   public String getDestination() {
/* 282 */     return this.destination; } 
/* 283 */   public void setDestination(String destination) { this.destination = destination; } 
/*     */   public boolean isInHardDisk() {
/* 285 */     return this.inHardDisk; } 
/* 286 */   public void setInHardDisk(boolean inHardDisk) { this.inHardDisk = inHardDisk; }
/*     */ 
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.controller.GenericReport
 * JD-Core Version:    0.6.2
 */