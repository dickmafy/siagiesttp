/*     */ package com.belogick.factory.util.helper;
/*     */ 
/*     */ public class StringHelper
/*     */ {
/*     */   public static int countWords(String cadena)
/*     */   {
/*  17 */     cadena = cadena + " ";
/*  18 */     int c = 0;
/*  19 */     for (int i = 0; i < cadena.length(); i++)
/*     */     {
/*  21 */       if (cadena.charAt(i) == ' ')
/*  22 */         c++;
/*     */     }
/*  24 */     cadena = null;
/*  25 */     return c;
/*     */   }
/*     */ 
/*     */   public static String removeWord(String cadena, int n)
/*     */   {
/*  36 */     cadena = cadena + " ";
/*  37 */     String nuevaCadena = "";
/*  38 */     int c = 0;
/*  39 */     String palabra = "";
/*  40 */     for (int i = 0; i < cadena.length(); i++)
/*     */     {
/*  42 */       palabra = palabra + cadena.charAt(i);
/*  43 */       if (cadena.charAt(i) == ' ')
/*     */       {
/*  45 */         c++;
/*  46 */         if (c != n) nuevaCadena = nuevaCadena + palabra;
/*  47 */         palabra = "";
/*     */       }
/*     */     }
/*  50 */     cadena = null;
/*  51 */     return nuevaCadena.trim();
/*     */   }
/*     */ 
/*     */   public static String reverseWords(String cadena)
/*     */   {
/*  61 */     cadena = cadena + " ";
/*  62 */     String palabra = "";
/*  63 */     String nuevaCadena = "";
/*  64 */     for (int i = 0; i < cadena.length(); i++)
/*     */     {
/*  66 */       palabra = cadena.charAt(i) + palabra;
/*  67 */       if (cadena.charAt(i) == ' ')
/*     */       {
/*  69 */         nuevaCadena = nuevaCadena + palabra;
/*  70 */         palabra = "";
/*     */       }
/*     */     }
/*  73 */     return nuevaCadena.trim();
/*     */   }
/*     */ 
/*     */   public static String reverse(String cadena)
/*     */   {
/*  83 */     String invertida = "";
/*  84 */     for (int x = cadena.length() - 1; x >= 0; x--)
/*  85 */       invertida = invertida + cadena.charAt(x);
/*  86 */     return invertida;
/*     */   }
/*     */ 
/*     */   public static int countCharacter(String cadena, String caracter)
/*     */   {
/*  97 */     int frecuencia = 0;
/*  98 */     for (int i = 0; i <= cadena.length() - 1; i++)
/*     */     {
/* 100 */       if (String.valueOf(cadena.charAt(i)).equals(caracter))
/* 101 */         frecuencia++;
/*     */     }
/* 103 */     return frecuencia;
/*     */   }
/*     */ 
/*     */   public static String toTitle(String cadena)
/*     */   {
/* 113 */     String[] palabras = cadena.split(" ");
/* 114 */     String titulo = "";
/* 115 */     for (int i = 0; i < palabras.length; i++)
/*     */     {
/* 117 */       if ((!palabras[i].equals("de")) && (!palabras[i].equals("y")) && (!palabras[i].equals("por")) && (!palabras[i].equals("en")) && (!palabras[i].equals("con")) && (!palabras[i].equals("la")))
/* 118 */         titulo = titulo + palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(1).toLowerCase();
/*     */       else
/* 120 */         titulo = titulo + palabras[i];
/*     */     }
/* 122 */     palabras = null;
/* 123 */     return titulo;
/*     */   }
/*     */ 
/*     */   public String removeSpaces(String linea)
/*     */   {
/* 134 */     String resultado = "";
/* 135 */     for (int i = 0; i < linea.length(); i++)
/*     */     {
/* 137 */       if (linea.charAt(i) != ' ')
/* 138 */         resultado = resultado + linea.charAt(i);
/*     */     }
/* 140 */     return resultado;
/*     */   }
/*     */ 
/*     */   public static String fillZero(Long numero, Long longitud)
/*     */   {
/* 151 */     String resultado = numero.toString();
/* 152 */     for (int i = 0; i < longitud.longValue() - resultado.length(); i++)
/* 153 */       resultado = "0" + resultado;
/* 154 */     return resultado;
/*     */   }
/*     */ 
/*     */   public static boolean containsItemOnString(String[] first, String[] second)
/*     */   {
/* 165 */     for (int i = 0; i < first.length; i++)
/*     */     {
/* 167 */       for (int j = 0; j < second.length; j++)
/*     */       {
/* 169 */         if (first[i].equals(second[j]))
/* 170 */           return true;
/*     */       }
/*     */     }
/* 173 */     return false;
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.helper.StringHelper
 * JD-Core Version:    0.6.2
 */