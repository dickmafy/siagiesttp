/*    */ package com.belogick.factory.util.constant;
/*    */ 
/*    */ public class Constante
/*    */ {
/*    */   public static final String KEY = "bluesicat";
/*  8 */   private final String PATH_RESOURCES = "/recursos";
/*  9 */   private final String PATH_IMAGES = "/recursos/imagenes";
/* 10 */   private final String PATH_ICONS = "/recursos/iconos";
/* 11 */   private final String PATH_STYLES = "/recursos/estilos";
/*    */   public static final String MESSAGE_ENABLE = "El registro ha sido habilitado exitosamente.";
/*    */   public static final String MESSAGE_DISABLE = "El registro ha sido deshabilitado exitosamente.";
/*    */   public static final String MESSAGE_DELETE = "El registro ha sido eliminado.";
/*    */   public static final String MESSAGE_UPDATE = "El registro ha sido actualizado satisfactoriamente.";
/*    */   public static final String MESSAGE_CREATE = "El registro ha sido guardado satisfactoriamente.";
/*    */   public static final String MESSAGE_IMPORT = "Los registros han sido importados satisfactoriamente.";
/*    */   public static final String MESSAGE_EXPORT = "Los registros han sido exportados satisfactoriamente.";
/* 23 */   public static final Long ROW_STATUS_NEW = Long.valueOf(0L);
/* 24 */   public static final Long ROW_STATUS_ENABLED = Long.valueOf(1L);
/* 25 */   public static final Long ROW_STATUS_DISABLED = Long.valueOf(2L);
/* 26 */   public static final Long ROW_STATUS_DELETE = Long.valueOf(3L);
/* 27 */   public static final Long ROW_STATUS_BLOCKED = Long.valueOf(4L);
/*    */   public static final String OPERATION_ENABLE = "Registro Habilitado";
/*    */   public static final String OPERATION_DISABLE = "Registro Deshabilitado";
/*    */   public static final String OPERATION_STATUS = "Registro Estado Actualizado";
/*    */   public static final String OPERATION_CREATE = "Registro Creado";
/*    */   public static final String OPERATION_UPDATE = "Registro Actualizado";
/*    */   public static final String OPERATION_DELETE = "Registro Eliminado";
/*    */   public static final String OPERATION_LIST = "Listar Registros";
/*    */   public static final String OPERATION_SEARCH = "Consultar Registros";
/*    */   public static final String OPERATION_EXPORT = "Exportar Registros";
/*    */   public static final String OPERATION_IMPORT = "Importar Registros";
/*    */   public static final String USER_SESSION = "usuarioSesion";
/*    */   public static final int LIST_SIZE = 15;
/*    */   public static final String OPTION_SELECT = "--SELECCIONAR--";
/* 45 */   public static final Long NO_SELECTED = Long.valueOf("-1");
/*    */   public static final String LIST_EMPTY = "No se encontraron resultados.";
/*    */ 
/*    */   public Long getROW_STATUS_ENABLED()
/*    */   {
/* 49 */     return ROW_STATUS_ENABLED; } 
/* 50 */   public Long getROW_STATUS_DISABLED() { return ROW_STATUS_DISABLED; } 
/* 51 */   public Long getROW_STATUS_DELETE() { return ROW_STATUS_DELETE; } 
/*    */   public String getPATH_IMAGES() {
/* 53 */     return "/recursos/imagenes"; } 
/* 54 */   public String getPATH_ICONS() { return "/recursos/iconos"; } 
/* 55 */   public String getPATH_STYLES() { return "/recursos/estilos"; } 
/*    */   public String getOPTION_SELECT() {
/* 57 */     return "--SELECCIONAR--"; } 
/* 58 */   public String getLIST_EMPTY() { return "No se encontraron resultados."; } 
/* 59 */   public int getLIST_SIZE() { return 15; }
/*    */ 
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.constant.Constante
 * JD-Core Version:    0.6.2
 */