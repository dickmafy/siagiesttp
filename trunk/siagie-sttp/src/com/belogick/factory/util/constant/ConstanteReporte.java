/*     */ package com.belogick.factory.util.constant;
/*     */ 
/*     */ import java.io.File;
/*     */ 
/*     */ public class ConstanteReporte
/*     */ {
/*     */   public static final String SESSION_USER = "usuarioEnSesion";
/*     */   public static final String USUARIO_CREACION = "usuarioCreacion";
/*     */   public static final String USUARIO_MODIFICACION = "usuarioModificacion";
/*     */   public static final String CONTROLLER_FOR_ALTA = "__alta__";
/*     */   public static final String CONTROLLER_FOR_ALTUALIZACION_VRF = "__act_vrf__";
/*     */   public static final String CONTROLLER_FOR_ALTUALIZACION_ETIQUETA = "__act_etiqueta__";
/*  21 */   public static final Long TIPO_ACCESO_SSH = Long.valueOf(1L);
/*  22 */   public static final Long TIPO_ACCESO_TELNET = Long.valueOf(2L);
/*     */ 
/*  25 */   public static final Long STATUS_ORDEN_PROCESANDO = Long.valueOf(11L);
/*  26 */   public static final Long STATUS_ORDEN_POR_ACTIVAR = Long.valueOf(14L);
/*  27 */   public static final Long STATUS_ORDEN_PENDIENTE_DE_EJECUCION = Long.valueOf(15L);
/*  28 */   public static final Long STATUS_ORDEN_EJECUTADO = Long.valueOf(12L);
/*  29 */   public static final Long STATUS_ORDEN_ERROR = Long.valueOf(13L);
/*  30 */   public static final Long STATUS_ORDEN_CANCELADO = Long.valueOf(16L);
/*     */ 
/*  32 */   public static final Long STATUS_PUERTO_DISPONIBLE = Long.valueOf(1L);
/*  33 */   public static final Long STATUS_PUERTO_AISLADO = Long.valueOf(2L);
/*  34 */   public static final Long STATUS_PUERTO_DESHABILITADO = Long.valueOf(3L);
/*     */ 
/*  36 */   public static final Long TIPO_TECNOLOGIA_ATM = Long.valueOf(100L);
/*  37 */   public static final Long TIPO_TECNOLOGIA_ETHERNET = Long.valueOf(101L);
/*  38 */   public static final Long TIPO_TECNOLOGIA_GPUNTO = Long.valueOf(102L);
/*  39 */   public static final Long TIPO_TECNOLOGIA_CONEXION_DIRECTA = Long.valueOf(104L);
/*  40 */   public static final Long TIPO_TECNOLOGIA_ADTRAM = Long.valueOf(105L);
/*  41 */   public static final Long TIPO_TECNOLOGIA_TRANSMISION = Long.valueOf(106L);
/*     */ 
/*  43 */   public static final Long TIPO_ROUTER_CISCO_7200 = Long.valueOf(3003L);
/*  44 */   public static final Long TIPO_ROUTER_CISCO_7500 = Long.valueOf(3004L);
/*  45 */   public static final Long TIPO_ROUTER_CISCO_12410 = Long.valueOf(3007L);
/*     */ 
/*  47 */   public static final Long TIPO_SERVICIO_VPN_L = Long.valueOf(2201L);
/*  48 */   public static final Long TIPO_SERVICIO_INTERNET_L = Long.valueOf(2202L);
/*     */   public static final String LOG_SEPARA_COLUMNA_RESPUESTA = "@@@";
/*     */   public static final String LOG_SEPARA_COLUMNA_RESPUESTA_ER = "@@@";
/*     */   public static final String LOG_SEPARA_FILA_RESPUESTA = "&&&";
/*     */   public static final String LOG_SEPARA_FILA_RESPUESTA_ER = "&&&";
/*  56 */   public static final Integer[] accessListExceptions = { Integer.valueOf(2093), Integer.valueOf(2094), Integer.valueOf(2095), Integer.valueOf(2096), Integer.valueOf(2097), Integer.valueOf(2097), Integer.valueOf(2098), Integer.valueOf(2099) };
/*     */   public static final String PARAMETRO_INVENTARIO_USUARIO = "INVENTARIO_USUARIO";
/*     */   public static final String PARAMETRO_INVENTARIO_USUARIO_CONTRASENHA = "INVENTARIO_USUARIO_CONTRASENHA";
/*     */   public static final String PARAMETRO_INVENTARIO_FTP_DIRECCION = "INVENTARIO_FTP_DIRECCION";
/*     */   public static final String PARAMETRO_INVENTARIO_FTP_PUERTO = "INVENTARIO_FTP_PUERTO";
/*     */   public static final String PARAMETRO_INVENTARIO_CADENA_CONEXION_PHP = "INVENTARIO_CADENA_CONEXION_PHP";
/*     */   public static final String PARAMETRO_INVENTARIO_FTP_USUARIO = "INVENTARIO_FTP_USUARIO";
/*     */   public static final String PARAMETRO_INVENTARIO_FTP_CONTRASENHA = "INVENTARIO_FTP_CONTRASENHA";
/*     */   public static final String PARAMETRO_INVENTARIO_RUTA_TEMP = "INVENTARIO_RUTA_TEMP";
/*     */   public static final String PARAMETRO_ARCHIVO_TEMPORAL = "ARCHIVO_TEMPORAL";
/*     */   public static final String PARAMETRO_RUTA_TEMPORALES = "RUTA_TEMPORALES";
/*     */   public static final String PARAMETRO_RUTA_EJECUTABLES = "RUTA_EJECUTABLES";
/*     */   public static final String BEAN_HELPER = "parametroHelper";
/*     */   public static final String ELEMENTO_RED_ROUTER = "ROUTER";
/*     */   public static final String NO_OPTION_SELECTED = "__none__";
/*     */   public static final String KEY = "blue";
/*     */   public static final String ESTADO_ACTIVO = "A";
/*     */   public static final String ESTADO_INACTIVO = "I";
/*     */   public static final String IP_CLASE_A = "A";
/*     */   public static final String IP_CLASE_B = "B";
/*     */   public static final String IP_CLASE_C = "C";
/*     */   public static final String IP_CLASE_NO_IDENTIFICADA = "__none__";
/*     */   public static final String MASCARA_SUBRED_CLASE_B = "255.255.0.0";
/*     */   public static final String MASCARA_SUBRED_CLASE_C = "255.255.255.0";
/*     */   public static final String MASCARA_SUBRED_NO_IDENTIFICADA = "__none__";
/*     */   public static final String MASCARA_SUBRED = "255.255.255.252";
/*     */   public static final String DESCRIPCION_ESTADO_ACTIVO = "Activo";
/*     */   public static final String DESCRIPCION_ESTADO_INACTIVO = "Inactivo";
/*     */   public static final String ARCHIVO_PDF = "__pdf__";
/*     */   public static final String ARCHIVO_EXCEL = "__excel__";
/*     */   public static final String ARCHIVO_HTML = "__html__";
/*     */   public static final int SSH_SESSION_TIMEOUT = 15000;
/*     */   public static final int SSH_DEFAULT_PORT = 22;
/*     */   public static final int TERMINAL_TIME_TO_WAIT_FOR_ANSWER = 4000;
/*     */   public static final String SEPARADOR_RUTA = "/";
/*     */   public static final String SEPARADOR_TEXTO_PLANO = ",";
/*     */   public static final String UBICACION_DEPARTAMENTO = "DEP";
/*     */   public static final String UBICACION_PROVINCIA = "PRV";
/*     */   public static final String UBICACION_DISTRITO = "DIS";
/*     */   public static final String PUERTO_FISICO = "F";
/*     */   public static final String PUERTO_LOGICO = "L";
/*     */   public static final String PUERTO_CHANNEL = "C";
/*     */   public static final String PUERTO_ESTADO_INCLUIDO = "I";
/*     */   public static final String PUERTO_ESTADO_EXCLUIDO = "E";
/*     */   public static final String ELEMENTO_RED_PUERTO = "PUERTO";
/*     */   public static final String ELEMENTO_RED_SLOT = "SLOT";
/*     */   public static final String ELEMENTO_RED_SUBSLOT = "SUBSLOT";
/*     */   public static final String ELEMENTO_VRF = "VRF";
/* 130 */   public static final String SEPARADOR = File.separator;
/*     */ 
/* 132 */   public static final String RUTA_REPORTES_COMUNES = SEPARADOR + "WEB-INF" + SEPARADOR + "classes" + SEPARADOR + "pe" + SEPARADOR + "com" + 
/* 132 */     SEPARADOR + "americatel" + SEPARADOR + "reportes" + SEPARADOR + "reportes" + SEPARADOR + "base" + SEPARADOR;
/* 133 */   public static final String REPORTE_GENERICO_HORIZONTAL = RUTA_REPORTES_COMUNES + "R_Generico_H.jasper";
/* 134 */   public static final String REPORTE_GENERICO_VERTICAL = RUTA_REPORTES_COMUNES + "R_Generico_V.jasper";
/*     */ 
/* 145 */   public static final String RUTA_REPORTES_OOSS = SEPARADOR + "WEB-INF" + SEPARADOR + "classes" + SEPARADOR + "pe" + 
/* 145 */     SEPARADOR + "com" + SEPARADOR + "americatel" + SEPARADOR + "reportes" + SEPARADOR + "reportes" + SEPARADOR;
/*     */ 
/* 149 */   public static final String RUTA_REPORTES = SEPARADOR + "WEB-INF" + SEPARADOR + "classes" + SEPARADOR + "modules" + 
/* 149 */     SEPARADOR + "logistic" + SEPARADOR + "reports" + SEPARADOR;
/*     */   public static final String PARAMETRO_PAGINA_DEFAULT_ADMINISTRADOR = "PAG_DEFAULT_ADMIN";
/*     */   public static final String PARAMETRO_PAGINA_DEFAULT_OTHER_USERS = "PAG_DEFAULT_OTHER_USERS";
/*     */   public static final String PARAMETRO_RUTA_INVENTARIO = "RUTA_INVENTARIO";
/*     */   public static final String PARAMETRO_NIVEL_DEFINICION_UBICACION_PUERTO = "NIV_DEFINICION_UBIGEO_PUERTO";
/*     */   public static final String TIPO_MOVIMIENTO_ALTA = "9";
/*     */   public static final String TIPO_MOVIMIENTO_BAJA = "10";
/*     */   public static final String TIPO_MOVIMIENTO_ACTUALIZACION_VELOCIDAD = "11";
/*     */   public static final String TIPO_MOVIMIENTO_ACTUALIZACION_POOL = "12";
/*     */   public static final String TIPO_MOVIMIENTO_ACTUALIZACION_PVC = "13";
/*     */   public static final String TIPO_MOVIMIENTO_ACTUALIZACION_IP = "14";
/*     */   public static final String TIPO_MOVIMIENTO_ACTUALIZACION_ETIQUETA = "15";
/*     */   public static final String TIPO_MOVIMIENTO_ACTUALIZACION_VRF = "16";
/*     */   public static final String TIPO_MOVIMIENTO_ALTA_CDS = "17";
/*     */   public static final String TIPO_MOVIMIENTO_INVENTARIO_P_LOGICO = "18";
/*     */   public static final String TIPO_MOVIMIENTO_SEPARA_INTERFACE = "19";
/*     */   public static final String TIPO_MOVIMIENTO_CORTE_RECONEXION = "20";
/*     */   public static final String TIPO_MOVIMIENTO_CORTE = "31";
/*     */   public static final String TIPO_MOVIMIENTO_RECONEXION = "32";
/* 176 */   public static final Long TIPO_MOVIMIENTO_ALTA_L = Long.valueOf(9L);
/* 177 */   public static final Long TIPO_MOVIMIENTO_BAJA_L = Long.valueOf(10L);
/* 178 */   public static final Long TIPO_MOVIMIENTO_ACTUALIZACION_VELOCIDAD_L = Long.valueOf(11L);
/* 179 */   public static final Long TIPO_MOVIMIENTO_ACTUALIZACION_POOL_L = Long.valueOf(12L);
/* 180 */   public static final Long TIPO_MOVIMIENTO_ACTUALIZACION_PVC_L = Long.valueOf(13L);
/* 181 */   public static final Long TIPO_MOVIMIENTO_ACTUALIZACION_IP_L = Long.valueOf(14L);
/* 182 */   public static final Long TIPO_MOVIMIENTO_ACTUALIZACION_ETIQUETA_L = Long.valueOf(15L);
/* 183 */   public static final Long TIPO_MOVIMIENTO_ACTUALIZACION_VRF_L = Long.valueOf(16L);
/* 184 */   public static final Long TIPO_MOVIMIENTO_ALTA_CDS_L = Long.valueOf(17L);
/* 185 */   public static final Long TIPO_MOVIMIENTO_INVENTARIO_P_LOGICO_L = Long.valueOf(18L);
/* 186 */   public static final Long TIPO_MOVIMIENTO_SEPARA_INTERFACE_L = Long.valueOf(19L);
/* 187 */   public static final Long TIPO_MOVIMIENTO_CORTE_RECONEXION_L = Long.valueOf(20L);
/* 188 */   public static final Long TIPO_MOVIMIENTO_CORTE_L = Long.valueOf(31L);
/* 189 */   public static final Long TIPO_MOVIMIENTO_RECONEXION_L = Long.valueOf(32L);
/*     */   public static final String TIPO_SERVICIO_IPVPN = "2201";
/*     */   public static final String TIPO_SERVICIO_INTERNET = "2202";
/*     */   public static final String TIPO_SUB_SERVICIO_IPVPN = "3221";
/*     */   public static final String TIPO_SUBSERVICIO_ATM = "2205";
/* 198 */   public static final Long TIPO_SUBSERVICIO_ATM_L = Long.valueOf(2205L);
/*     */   public static final String PUERTO_UBIGEO_NIVEL_DEPARTAMENTO = "PUDEP";
/*     */   public static final String PUERTO_UBIGEO_NIVEL_PROVINCIA = "PUPRV";
/*     */   public static final String PUERTO_UBIGEO_NIVEL_DISTRITO = "PUDIS";
/*     */   public static final String REPORTE_PARAMETRO_SHOW_SLOT = "SLT";
/*     */   public static final String REPORTE_PARAMETRO_SHOW_INTERFACE = "INT";
/*     */   public static final String REPORTE_PARAMETRO_SHOW_DETAIL = "DET";
/*     */   public static final String REPORTE_PARAMETRO_SHOW_GRAPHIC = "GPH";
/*     */   public static final String PROTOCOLO_CONFIGURACION_BGP = "BGP";
/*     */   public static final String PROTOCOLO_CONFIGURACION_RIP = "RIP";
/*     */   public static final String PROTOCOLO_CONFIGURACION_ESTATICO = "EST";
/* 217 */   public static final Integer FORMATO_ALTA_NUMERO_TOTAL_COLUMNAS = Integer.valueOf(29);
/* 218 */   public static final Integer FORMATO_BAJA_NUMERO_TOTAL_COLUMNAS = Integer.valueOf(3);
/* 219 */   public static final Integer FORMATO_ACTUALIZACION_POOL_NUMERO_TOTAL_COLUMNAS = Integer.valueOf(4);
/* 220 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_NUMERO_TOTAL_COLUMNAS = Integer.valueOf(4);
/* 221 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_NUMERO_TOTAL_COLUMNAS = Integer.valueOf(27);
/* 222 */   public static final Integer FORMATO_ACTUALIZACION_PVC_NUMERO_TOTAL_COLUMNAS = Integer.valueOf(6);
/* 223 */   public static final Integer FORMATO_ACTUALIZACION_IP_NUMERO_TOTAL_COLUMNAS = Integer.valueOf(5);
/* 224 */   public static final Integer FORMATO_ACTUALIZACION_VRF_NUMERO_TOTAL_COLUMNAS = Integer.valueOf(7);
/*     */ 
/* 227 */   public static final Integer FORMATO_ALTA_TIPO_SERVICIO = Integer.valueOf(0);
/* 228 */   public static final Integer FORMATO_ALTA_TIPO_SUBSERVICIO = Integer.valueOf(1);
/* 229 */   public static final Integer FORMATO_ALTA_TIPO_TECNOLOGIA = Integer.valueOf(2);
/* 230 */   public static final Integer FORMATO_ALTA_CODIGO_DIGITAL = Integer.valueOf(3);
/* 231 */   public static final Integer FORMATO_ALTA_ROUTER = Integer.valueOf(4);
/* 232 */   public static final Integer FORMATO_ALTA_PUERTO_FISICO = Integer.valueOf(5);
/* 233 */   public static final Integer FORMATO_ALTA_IP_CLIENTE = Integer.valueOf(6);
/* 234 */   public static final Integer FORMATO_ALTA_MASCARA = Integer.valueOf(7);
/* 235 */   public static final Integer FORMATO_ALTA_VELOCIDAD_DESCARGA = Integer.valueOf(8);
/* 236 */   public static final Integer FORMATO_ALTA_VELOCIDAD_SUBIDA = Integer.valueOf(9);
/* 237 */   public static final Integer FORMATO_ALTA_VLAN = Integer.valueOf(10);
/* 238 */   public static final Integer FORMATO_ALTA_SVLAN = Integer.valueOf(11);
/* 239 */   public static final Integer FORMATO_ALTA_VPI = Integer.valueOf(12);
/* 240 */   public static final Integer FORMATO_ALTA_VCI = Integer.valueOf(13);
/* 241 */   public static final Integer FORMATO_ALTA_VRF_CODIGO = Integer.valueOf(14);
/* 242 */   public static final Integer FORMATO_ALTA_VRF_RD = Integer.valueOf(15);
/* 243 */   public static final Integer FORMATO_ALTA_VRF_AS = Integer.valueOf(16);
/* 244 */   public static final Integer FORMATO_ALTA_RAZONSOCIAL = Integer.valueOf(17);
/*     */ 
/* 247 */   public static final Integer FORMATO_ALTA_PROTOCOLO_CONFIGURACION = Integer.valueOf(18);
/* 248 */   public static final Integer FORMATO_ALTA_CAUDAL_GESTION = Integer.valueOf(19);
/* 249 */   public static final Integer FORMATO_ALTA_CAUDAL_VOZ = Integer.valueOf(20);
/* 250 */   public static final Integer FORMATO_ALTA_CAUDAL_VIDEO = Integer.valueOf(21);
/* 251 */   public static final Integer FORMATO_ALTA_CAUDAL_PLATINO = Integer.valueOf(22);
/* 252 */   public static final Integer FORMATO_ALTA_CAUDAL_ORO = Integer.valueOf(23);
/* 253 */   public static final Integer FORMATO_ALTA_CAUDAL_PLATA = Integer.valueOf(24);
/* 254 */   public static final Integer FORMATO_ALTA_CAUDAL_BRONCE = Integer.valueOf(25);
/* 255 */   public static final Integer FORMATO_ALTA_CAUDAL_LDN = Integer.valueOf(26);
/* 256 */   public static final Integer FORMATO_ALTA_ACCESS_LIST = Integer.valueOf(27);
/* 257 */   public static final Integer FORMATO_ALTA_CIUDAD = Integer.valueOf(28);
/* 258 */   public static final Integer FORMATO_ALTA_OBSERVACIONES = Integer.valueOf(29);
/* 259 */   public static final Integer FORMATO_ALTA_FONO = Integer.valueOf(30);
/* 260 */   public static final Integer FORMATO_ALTA_SHUTDOWN = Integer.valueOf(31);
/*     */   public static final String MATCH_PROMPT = "^.*\r\n[a-zA-Z0-9]*[>|#]$";
/* 266 */   public static final Integer FORMATO_BAJA_CODIGO_DIGITAL = Integer.valueOf(0);
/* 267 */   public static final Integer FORMATO_BAJA_ROUTER = Integer.valueOf(1);
/* 268 */   public static final Integer FORMATO_BAJA_PUERTO_LOGICO = Integer.valueOf(2);
/* 269 */   public static final Integer FORMATO_BAJA_FORZOSA = Integer.valueOf(3);
/* 270 */   public static final Integer FORMATO_BAJA_OBSERVACIONES = Integer.valueOf(4);
/*     */ 
/* 273 */   public static final Integer FORMATO_ACTUALIZACION_POOL_CODIGO_DIGITAL = Integer.valueOf(0);
/* 274 */   public static final Integer FORMATO_ACTUALIZACION_POOL_ROUTER = Integer.valueOf(1);
/* 275 */   public static final Integer FORMATO_ACTUALIZACION_POOL_PUERTO_LOGICO = Integer.valueOf(2);
/* 276 */   public static final Integer FORMATO_ACTUALIZACION_POOL_ACCESS_LIST = Integer.valueOf(3);
/*     */ 
/* 279 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CODIGO_DIGITAL = Integer.valueOf(0);
/* 280 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_ROUTER = Integer.valueOf(1);
/* 281 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_PUERTO_LOGICO = Integer.valueOf(2);
/* 282 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_VELOCIDAD_DESCARGA = Integer.valueOf(3);
/* 283 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_VELOCIDAD_SUBIDA = Integer.valueOf(4);
/* 284 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CAUDAL_GESTION = Integer.valueOf(5);
/* 285 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CAUDAL_VOZ = Integer.valueOf(6);
/* 286 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CAUDAL_VIDEO = Integer.valueOf(7);
/* 287 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CAUDAL_PLATINO = Integer.valueOf(8);
/* 288 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CAUDAL_ORO = Integer.valueOf(9);
/* 289 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CAUDAL_PLATA = Integer.valueOf(10);
/* 290 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CAUDAL_BRONCE = Integer.valueOf(11);
/* 291 */   public static final Integer FORMATO_ACTUALIZACION_VELOCIDAD_CAUDAL_LDN = Integer.valueOf(12);
/*     */ 
/* 294 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_TIPO_SERVICIO = Integer.valueOf(0);
/* 295 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_TIPO_SUBSERVICIO = Integer.valueOf(1);
/* 296 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_TIPO_TECNOLOGIA = Integer.valueOf(2);
/* 297 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CODIGO_DIGITAL = Integer.valueOf(3);
/* 298 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_ROUTER = Integer.valueOf(4);
/* 299 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_PUERTO_LOGICO = Integer.valueOf(5);
/* 300 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_NODO = Integer.valueOf(6);
/* 301 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_VRF_CODIGO = Integer.valueOf(7);
/* 302 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_VRF_RD = Integer.valueOf(8);
/* 303 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_VRF_AS = Integer.valueOf(9);
/* 304 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_RAZONSOCIAL = Integer.valueOf(10);
/* 305 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_VELOCIDAD_DESCARGA = Integer.valueOf(11);
/* 306 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_VELOCIDAD_SUBIDA = Integer.valueOf(12);
/* 307 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_VLAN = Integer.valueOf(13);
/* 308 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_SVLAN = Integer.valueOf(14);
/* 309 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_VPI = Integer.valueOf(15);
/* 310 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_VCI = Integer.valueOf(16);
/* 311 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_PROTOCOLO_CONFIGURACION = Integer.valueOf(17);
/* 312 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CAUDAL_GESTION = Integer.valueOf(18);
/* 313 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CAUDAL_VOZ = Integer.valueOf(19);
/* 314 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CAUDAL_VIDEO = Integer.valueOf(20);
/* 315 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CAUDAL_PLATINO = Integer.valueOf(21);
/* 316 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CAUDAL_ORO = Integer.valueOf(22);
/* 317 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CAUDAL_PLATA = Integer.valueOf(23);
/* 318 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CAUDAL_BRONCE = Integer.valueOf(24);
/* 319 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CAUDAL_LDN = Integer.valueOf(25);
/* 320 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_CIUDAD = Integer.valueOf(26);
/* 321 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_OBSERVACIONES = Integer.valueOf(27);
/* 322 */   public static final Integer FORMATO_ACTUALIZACION_ETIQUETA_FONO = Integer.valueOf(28);
/*     */ 
/* 325 */   public static final Integer FORMATO_ACTUALIZACION_PVC_CODIGO_DIGITAL = Integer.valueOf(0);
/* 326 */   public static final Integer FORMATO_ACTUALIZACION_PVC_ROUTER = Integer.valueOf(1);
/* 327 */   public static final Integer FORMATO_ACTUALIZACION_PVC_PUERTO_LOGICO = Integer.valueOf(2);
/* 328 */   public static final Integer FORMATO_ACTUALIZACION_PVC_PVC = Integer.valueOf(3);
/* 329 */   public static final Integer FORMATO_ACTUALIZACION_PVC_VPI = Integer.valueOf(4);
/* 330 */   public static final Integer FORMATO_ACTUALIZACION_PVC_VCI = Integer.valueOf(5);
/*     */ 
/* 333 */   public static final Integer FORMATO_ACTUALIZACION_IP_CODIGO_DIGITAL = Integer.valueOf(0);
/* 334 */   public static final Integer FORMATO_ACTUALIZACION_IP_ROUTER = Integer.valueOf(1);
/* 335 */   public static final Integer FORMATO_ACTUALIZACION_IP_PUERTO_LOGICO = Integer.valueOf(2);
/* 336 */   public static final Integer FORMATO_ACTUALIZACION_IP_NUEVA_IP = Integer.valueOf(3);
/* 337 */   public static final Integer FORMATO_ACTUALIZACION_IP_NUEVA_MASCARA = Integer.valueOf(4);
/*     */ 
/* 340 */   public static final Integer FORMATO_ACTUALIZACION_VRF_CODIGO_DIGITAL = Integer.valueOf(0);
/* 341 */   public static final Integer FORMATO_ACTUALIZACION_VRF_ROUTER = Integer.valueOf(1);
/* 342 */   public static final Integer FORMATO_ACTUALIZACION_VRF_PUERTO_LOGICO = Integer.valueOf(2);
/* 343 */   public static final Integer FORMATO_ACTUALIZACION_VRF_CODIGO = Integer.valueOf(3);
/* 344 */   public static final Integer FORMATO_ACTUALIZACION_VRF_AS = Integer.valueOf(4);
/* 345 */   public static final Integer FORMATO_ACTUALIZACION_VRF_RD = Integer.valueOf(5);
/* 346 */   public static final Integer FORMATO_ACTUALIZACION_VRF_RAZONSOCIAL = Integer.valueOf(6);
/*     */   public static final String TIMESLOT_CHARACTER_SLOT_LIBRE = "X";
/*     */   public static final String TIMESLOT_CHARACTER_SEPARADOR = ".";
/*     */   public static final int TIMESLOT_POSICION_MAX = 31;
/*     */   public static final int TIMESLOT_POSICION_MIN = 1;
/*     */   public static final int TIMESLOT_ANCHO_BANDA_KBITS = 64;
/*     */   public static final String TIMESLOT_ARREGLO_VACIO = "X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X";
/*     */   public static final String TIPO_MENU_NODO = "N";
/*     */   public static final String TIPO_MENU_PADRE = "F";
/*     */   public static final String SISTEMA_OPERATIVO_WIN = "WIN";
/*     */   public static final String SISTEMA_OPERATIVO_OTRO = "OTRO";
/*     */   public static final String BAJA_FORZOSA_ACTIVO = "A";
/*     */   public static final String BAJA_FORZOSA_INACTIVO = "I";
/*     */   public static final String BALANCEO_NO = "NO";
/*     */   public static final String BALANCEO_POR_PAQUETE = "PAQ";
/*     */   public static final String TIPO_CONTROLADOR_SONET = "SO";
/*     */   public static final String TIPO_CONTROLADOR_E3 = "E3";
/*     */   public static final String TIPO_CONTROLADOR_E1 = "E1";
/*     */   public static final String ESTADO_INTERFACE_SHUTDOWN = "SHUT";
/*     */   public static final String ESTADO_INTERFACE_NO_SHUTDOWN = "NOSHUT";
/* 377 */   public static final String[] subNetMaskC = { "255.255.255.0", "255.255.255.128", 
/* 378 */     "255.255.255.192", "255.255.255.224", 
/* 379 */     "255.255.255.240", "255.255.255.248", 
/* 380 */     "255.255.255.252" };
/*     */ 
/* 381 */   public static final String[] subNetMaskB = { "255.255.0.0", "255.255.128.0", 
/* 382 */     "255.255.192.0", "255.255.224.0", 
/* 383 */     "255.255.240.0", "255.255.248.0", 
/* 384 */     "255.255.252.0", "255.255.254.0" };
/*     */ 
/* 385 */   public static final String[] subNetMaskA = { "255.0.0.0", "255.128.0.0", 
/* 386 */     "255.192.0.0", "255.224.0.0", 
/* 387 */     "255.240.0.0", "255.248.0.0", 
/* 388 */     "255.252.0.0", "255.254.0.0" };
/*     */   public static final String FLG_PUERTO_PREDETERMINADO = "1";
/*     */   public static final String TIPO_ENCAPSULAMIENTO_PPP = "PPP";
/*     */   public static final String TIPO_ENCAPSULAMIENTO_FRAME_RELAY = "FRAME-RELAY";
/*     */   public static final String TIPO_ENCAPSULAMIENTO_FRAME_RELAY_POINT_TO_POINT = "FRAME-RELAY-POINT-TO-POINT";
/*     */   public static final String OPCION_BAJA_RUTEOS = "BR";
/*     */   public static final String OPCION_BAJA_ACCESS_LIST = "BA";
/*     */   public static final String OPCION_BAJA_CHANNEL_GROUP = "BC";
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.constant.ConstanteReporte
 * JD-Core Version:    0.6.2
 */