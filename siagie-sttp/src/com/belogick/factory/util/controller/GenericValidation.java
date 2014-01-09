/*     */ package com.belogick.factory.util.controller;
/*     */ 
/*     */ import com.belogick.factory.util.constant.Constante;
/*     */ import java.util.Date;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class GenericValidation extends GenericMessage
/*     */ {
/*     */   Pattern p;
/*     */   Matcher m;
/*     */ 
/*     */   public boolean validation()
/*     */     throws Exception
/*     */   {
/*  22 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateEmpty(Date data)
/*     */   {
/*  31 */     if (data == null) return false;
/*  32 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateEmpty(String data)
/*     */   {
/*  42 */     if (data == null) return false;
/*  43 */     if (data.trim().equals("")) return false;
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateZero(Double data)
/*     */   {
/*  54 */     if (data == null) return false;
/*  55 */     if (data.doubleValue() == 0.0D) return false;
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateZero(Integer data) {
/*  60 */     if (data == null) return false;
/*  61 */     if (data.intValue() == 0) return false;
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateZero(Long data)
/*     */   {
/*  71 */     if (data == null) return false;
/*  72 */     if (data.longValue() == 0L) return false;
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateLength(Long data, int length)
/*     */   {
/*  84 */     if (data.toString().length() == length) return true;
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean validateLength(String data, int length)
/*     */   {
/*  96 */     if (data.length() == length) return true;
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean validateLengthRange(String data, int min, int max)
/*     */   {
/* 109 */     if ((data.length() <= max) && (data.length() >= min)) return true;
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean validateLengthGreater(String data, int max)
/*     */   {
/* 121 */     if (data.length() <= max) return true;
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean validateLengthLess(String data, int min)
/*     */   {
/* 133 */     if (data.length() >= min) return true;
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean validateNumeric(String data)
/*     */   {
/* 144 */     this.p = Pattern.compile("[0-9]*");
/* 145 */     this.m = this.p.matcher(data);
/* 146 */     if (!this.m.matches()) return false;
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateLetters(String data)
/*     */   {
/* 157 */     this.p = Pattern.compile("[A-ZñÑa-záéíóúÁÉÍÓÚü ]");
/* 158 */     this.m = this.p.matcher(data);
/* 159 */     if (!this.m.matches()) return false;
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateAlphanumeric(String data)
/*     */   {
/* 170 */     this.p = Pattern.compile("[A-ZñÑa-záéíóúÁÉÍÓÚü 0-9]");
/* 171 */     this.m = this.p.matcher(data);
/* 172 */     if (!this.m.matches()) return false;
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateEmail(String data)
/*     */   {
/* 183 */     this.p = Pattern.compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-z0-9][\\w\\.-]*[a-z0-9]\\.[a-z][a-z\\.]*[a-z]$");
/* 184 */     this.m = this.p.matcher(data);
/* 185 */     if (!this.m.matches()) return false;
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateNumericDecimal(String data)
/*     */   {
/* 196 */     this.p = Pattern.compile("\\d{0,4}\\.\\d{0,2}");
/* 197 */     this.m = this.p.matcher(data);
/* 198 */     if (!this.m.matches()) return false;
/* 199 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateSelect(Long data)
/*     */   {
/* 209 */     if (data == null) return false;
/* 210 */     if (data.longValue() == Constante.NO_SELECTED.longValue()) return false;
/* 211 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean validateSelect(Integer data)
/*     */   {
/* 221 */     if (data == null) return false;
/* 222 */     if (data.longValue() == Constante.NO_SELECTED.longValue()) return false;
/* 223 */     return true;
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.controller.GenericValidation
 * JD-Core Version:    0.6.2
 */