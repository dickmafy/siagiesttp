/*    */ package com.belogick.factory.util.helper;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class PasswordHelper
/*    */ {
/* 25 */   public static String NUMEROS = "0123456789";
/* 26 */   public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
/* 27 */   public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
/*    */ 
/*    */   static String random(String key, int length)
/*    */   {
/* 31 */     String pswd = "";
/* 32 */     for (int i = 0; i < length; i++)
/* 33 */       pswd = pswd + key.charAt((int)(Math.random() * key.length()));
/* 34 */     return pswd;
/*    */   }
/*    */ 
/*    */   public static boolean validateRange(String contrasena, Long max, Long min)
/*    */   {
/* 46 */     if ((max == null) && (min != null) && (contrasena.length() >= min.longValue())) return true;
/* 47 */     if ((max != null) && (min == null) && (contrasena.length() <= max.longValue())) return true;
/* 48 */     if ((max != null) && (min != null) && (contrasena.length() <= max.longValue()) && (contrasena.length() >= min.longValue())) return true;
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */   public static String getAleatorio(int tamano)
/*    */   {
/* 58 */     return random(NUMEROS + MAYUSCULAS + MINUSCULAS, tamano);
/*    */   }
/*    */ 
/*    */   public static boolean evaluatePassword(String contrasena, int nivel, int max, int min)
/*    */   {
/* 72 */     Pattern p = Pattern.compile("[A-Za-z 0-9]");
/* 73 */     if ((max == 0) && (min != 0))
/*    */     {
/* 75 */       if (nivel == 1) p = Pattern.compile("^([0-9])(?=\\S+$).{" + min + ",}$");
/* 76 */       if (nivel == 2) p = Pattern.compile("^(?=.*[0-9])([a-zA-Z])(?=\\S+$).{" + min + ",}$");
/* 77 */       if (nivel == 3) p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{" + min + ",}$");
/* 78 */       if (nivel == 4) p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{" + min + ",}$");
/*    */     }
/* 80 */     if ((max != 0) && (min == 0))
/*    */     {
/* 82 */       if (nivel == 1) p = Pattern.compile("^([0-9])(?=\\S+$).{," + max + "}$");
/* 83 */       if (nivel == 2) p = Pattern.compile("^(?=.*[0-9])([a-zA-Z])(?=\\S+$).{," + max + "}$");
/* 84 */       if (nivel == 3) p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{," + max + "}$");
/* 85 */       if (nivel == 4) p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{," + max + "}$");
/*    */     }
/* 87 */     if ((max != 0) && (min != 0) && (max > min))
/*    */     {
/* 89 */       if (nivel == 1) p = Pattern.compile("^([0-9])(?=\\S+$).{" + min + "," + max + "}$");
/* 90 */       if (nivel == 2) p = Pattern.compile("^(?=.*[0-9])([a-zA-Z])(?=\\S+$).{" + min + "," + max + "}$");
/* 91 */       if (nivel == 3) p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{" + min + "," + max + "}$");
/* 92 */       if (nivel == 4) p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{" + min + "," + max + "}$");
/*    */     }
/* 94 */     Matcher m = p.matcher(contrasena);
/* 95 */     if (!m.matches()) return false;
/* 96 */     return true;
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.helper.PasswordHelper
 * JD-Core Version:    0.6.2
 */