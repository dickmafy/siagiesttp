/*     */ package com.belogick.factory.util.helper;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import org.jfree.util.Log;
/*     */ import org.postgresql.util.PSQLException;
/*     */ 
/*     */ public class DatabaseHelper
/*     */ {
/*     */   public static String getOracleError(Exception error)
/*     */   {
/*     */     try
/*     */     {
/*  21 */       StringWriter sw = new StringWriter();
/*  22 */       PrintWriter pw = new PrintWriter(sw);
/*  23 */       System.out.println("tata.....:    " + error);
/*  24 */       error.printStackTrace(pw);
/*  25 */       System.out.println("......:    " + sw);
/*  26 */       String value = sw.toString().substring(sw.toString().indexOf("ORA-") + 4, sw.toString().indexOf("ORA-") + 9);
/*  27 */       String mensaje = null;
/*  28 */       switch (Integer.valueOf(value).intValue()) {
/*     */       case 1:
/*  30 */         mensaje = "Se desea grabar un registro que ya existe"; break;
/*     */       case 18:
/*  31 */         mensaje = "Base de datos, no responde"; break;
/*     */       case 22:
/*  32 */         mensaje = "Error, por invalidacion de logueo a la base de datos"; break;
/*     */       case 100:
/*  33 */         mensaje = "Error, no encuentra data que se busca"; break;
/*     */       case 911:
/*  34 */         mensaje = "Error, por motivos de caracter invalido"; break;
/*     */       case 1033:
/*  35 */         mensaje = "Error, la base de datos se esta apagando"; break;
/*     */       case 1073:
/*  36 */         mensaje = "Error, fallas de conexion con la base de datos"; break;
/*     */       case 1426:
/*  37 */         mensaje = "Error, por numero muy grande"; break;
/*     */       case 1456:
/*  38 */         mensaje = "Error, No se puede ejecutar operacion"; break;
/*     */       case 1489:
/*  39 */         mensaje = "Error, Cadena a procesar es muy larga"; break;
/*     */       case 2043:
/*  40 */         mensaje = "Error, No se pudo ejecutar accion"; break;
/*     */       case 2292:
/*  41 */         mensaje = "Error, por integridad de data"; break;
/*     */       case 12336:
/*  42 */         mensaje = "Error, No pudo sincronizar con base de datos"; break;
/*     */       case 13009:
/*  43 */         mensaje = "Error, Cadena ingresada es invalida"; break;
/*     */       case 13011:
/*  44 */         mensaje = "Error, Valor muy largo"; break;
/*     */       case 22973:
/*  45 */         mensaje = "Error, Cadena que se quiere modificar es muy larga de la permitida"; break;
/*     */       case 29355:
/*  46 */         mensaje = "Error, Faltan llenar campos"; break;
/*     */       case 32802:
/*  47 */         mensaje = "Error, Al intentar ingresar un valor que no es cadena"; break;
/*     */       case 32806:
/*  48 */         mensaje = "Error, Uno de los valores a ingresar es muy largo"; break;
/*     */       case 38101:
/*  49 */         mensaje = "Error, No se pudo insertar la data requerida"; break;
/*     */       case 38103:
/*  50 */         mensaje = "Error, No se pudo actualizar registro"; break;
/*  51 */       }return "Error al ejecutar la operacion";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*  57 */       Log.info("Error al ejecutar la clase DBHelper" + ex.getMessage());
/*  58 */       System.out.println("Eror :" + ex);
/*  59 */       System.out.println("Descripcion Error: " + ex.getMessage());
/*  60 */       return "Error al ejecutar la operacion, error controlado1: " + ex;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getMysqlError(Exception error)
/*     */   {
/*     */     try
/*     */     {
/*  73 */       StringWriter sw = new StringWriter();
/*  74 */       PrintWriter pw = new PrintWriter(sw);
/*  75 */       error.printStackTrace(pw);
/*  76 */       System.out.println("......:    " + sw);
/*  77 */       String value = sw.toString().substring(sw.toString().indexOf("ORA-") + 4, sw.toString().indexOf("ORA-") + 9);
/*  78 */       String mensaje = null;
/*  79 */       switch (Integer.valueOf(value).intValue()) {
/*     */       case 1:
/*  81 */         mensaje = "Se desea grabar un registro que ya existe"; break;
/*     */       case 18:
/*  82 */         mensaje = "Base de datos, no responde"; break;
/*     */       case 22:
/*  83 */         mensaje = "Error, por invalidacion de logueo a la base de datos"; break;
/*     */       case 100:
/*  84 */         mensaje = "Error, no encuentra data que se busca"; break;
/*     */       case 911:
/*  85 */         mensaje = "Error, por motivos de caracter invalido"; break;
/*     */       case 1033:
/*  86 */         mensaje = "Error, la base de datos se esta apagando"; break;
/*     */       case 1073:
/*  87 */         mensaje = "Error, fallas de conexion con la base de datos"; break;
/*     */       case 1426:
/*  88 */         mensaje = "Error, por numero muy grande"; break;
/*     */       case 1456:
/*  89 */         mensaje = "Error, No se puede ejecutar operacion"; break;
/*     */       case 1489:
/*  90 */         mensaje = "Error, Cadena a procesar es muy larga"; break;
/*     */       case 2043:
/*  91 */         mensaje = "Error, No se pudo ejecutar accion"; break;
/*     */       case 2292:
/*  92 */         mensaje = "Error, por integridad de data"; break;
/*     */       case 12336:
/*  93 */         mensaje = "Error, No pudo sincronizar con base de datos"; break;
/*     */       case 13009:
/*  94 */         mensaje = "Error, Cadena ingresada es invalida"; break;
/*     */       case 13011:
/*  95 */         mensaje = "Error, Valor muy largo"; break;
/*     */       case 22973:
/*  96 */         mensaje = "Error, Cadena que se quiere modificar es muy larga de la permitida"; break;
/*     */       case 29355:
/*  97 */         mensaje = "Error, Faltan llenar campos"; break;
/*     */       case 32802:
/*  98 */         mensaje = "Error, Al intentar ingresar un valor que no es cadena"; break;
/*     */       case 32806:
/*  99 */         mensaje = "Error, Uno de los valores a ingresar es muy largo"; break;
/*     */       case 38101:
/* 100 */         mensaje = "Error, No se pudo insertar la data requerida"; break;
/*     */       case 38103:
/* 101 */         mensaje = "Error, No se pudo actualizar registro"; break;
/* 102 */       }return "Error al ejecutar la operacion";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 108 */       Log.info("Error al ejecutar la clase DBHelper" + ex.getMessage());
/* 109 */       System.out.println("Eror :" + ex);
/* 110 */       System.out.println("Descripcion Error: " + ex.getMessage());
/* 111 */       return "Error al ejecutar la operacion, error controlado2: " + ex;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getPostgresqlError(Exception error)
/*     */   {
/*     */     try
/*     */     {
/* 124 */       StringWriter sw = new StringWriter();
/* 125 */       PrintWriter pw = new PrintWriter(sw);
/* 126 */       error.printStackTrace(pw);
/* 127 */       System.out.println("..." + sw);
/* 128 */       if (error.getCause().getCause().getCause().getCause().getClass() == PSQLException.class)
/*     */       {
/* 130 */         PSQLException object = (PSQLException)error.getCause().getCause().getCause().getCause();
/* 131 */         switch (Integer.valueOf(object.getSQLState()).intValue()) {
/*     */         case 23505:
/* 133 */           return "Error: El registro ya se encuentra en la base datos.";
/*     */         case 23502:
/* 134 */           return "Error: Se envía un parametro NULL a un campo que tiene una restricción NULL.";
/*     */         case 42703:
/* 135 */           return "Error: Existe una referencia a una columna que no esta definida en la base de datos.";
/* 136 */         }return "Error Desconocido: " + Integer.valueOf(object.getSQLState());
/*     */       }
/*     */ 
/* 139 */       return "";
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 143 */       Log.info("Error al ejecutar la clase DBHelper" + ex.getMessage());
/* 144 */       System.out.println("Error - Mensaje\t\t: " + ex.getMessage());
/* 145 */       System.out.println("Error - Causa\t\t: " + ex.getCause());
/* 146 */       System.out.println("Error - Descripcion\t: " + ex.getLocalizedMessage());
/* 147 */       return "Error Desconocido: " + ex;
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.helper.DatabaseHelper
 * JD-Core Version:    0.6.2
 */