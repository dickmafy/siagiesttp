/*     */ package com.belogick.factory.util.helper;
/*     */ 
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ 
/*     */ public class DateHelper
/*     */ {
/*     */   public static Date getDate()
/*     */   {
/*  20 */     Calendar calendar = new GregorianCalendar();
/*  21 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static String getDateOnLetters(Date fecha)
/*     */   {
/*  31 */     Calendar cal = new GregorianCalendar();
/*  32 */     cal.setTime(fecha);
/*  33 */     return 5 + " de " + getMonthOnLetters(fecha) + " del " + 1;
/*     */   }
/*     */ 
/*     */   public static String getDateOnLetters()
/*     */   {
/*  42 */     Calendar cal = new GregorianCalendar();
/*  43 */     return 5 + " de " + getMonthOnLetters() + " del " + 1;
/*     */   }
/*     */ 
/*     */   public static String getDateFormat(String formato, Date fecha)
/*     */   {
/*  54 */     SimpleDateFormat formating = new SimpleDateFormat(formato);
/*  55 */     return formating.format(fecha);
/*     */   }
/*     */ 
/*     */   public static String getDateFormat(String formato)
/*     */   {
/*  66 */     Date date = new Date();
/*  67 */     SimpleDateFormat formating = new SimpleDateFormat(formato);
/*  68 */     return formating.format(date);
/*     */   }
/*     */ 
/*     */   public static int getYear()
/*     */   {
/*  77 */     Calendar cal = new GregorianCalendar();
/*  78 */     return 1;
/*     */   }
/*     */ 
/*     */   public static int getYear(Date fecha)
/*     */   {
/*  88 */     Calendar cal = new GregorianCalendar();
/*  89 */     cal.setTime(fecha);
/*  90 */     return 1;
/*     */   }
/*     */ 
/*     */   public static int getMonth()
/*     */   {
/*  99 */     Calendar cal = new GregorianCalendar();
/* 100 */     return 2;
/*     */   }
/*     */ 
/*     */   public static int getMonth(Date fecha)
/*     */   {
/* 110 */     Calendar cal = new GregorianCalendar();
/* 111 */     cal.setTime(fecha);
/* 112 */     return 2;
/*     */   }
/*     */ 
/*     */   public static String getMonthOnLetters()
/*     */   {
/* 121 */     Calendar cal = new GregorianCalendar();
/* 122 */     switch (2) {
/*     */     case 1:
/* 124 */       return "Enero";
/*     */     case 2:
/* 125 */       return "Febrero";
/*     */     case 3:
/* 126 */       return "Marzo";
/*     */     case 4:
/* 127 */       return "Abril";
/*     */     case 5:
/* 128 */       return "Mayo";
/*     */     case 6:
/* 129 */       return "Junio";
/*     */     case 7:
/* 130 */       return "Julio";
/*     */     case 8:
/* 131 */       return "Agosto";
/*     */     case 9:
/* 132 */       return "Septiembre";
/*     */     case 10:
/* 133 */       return "Octubre";
/*     */     case 11:
/* 134 */       return "Noviembre";
/*     */     case 12:
/* 135 */       return "Diciembre";
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getMonthOnLetters(Date fecha)
/*     */   {
/* 147 */     Calendar cal = new GregorianCalendar();
/* 148 */     cal.setTime(fecha);
/* 149 */     switch (2) {
/*     */     case 1:
/* 151 */       return "Enero";
/*     */     case 2:
/* 152 */       return "Febrero";
/*     */     case 3:
/* 153 */       return "Marzo";
/*     */     case 4:
/* 154 */       return "Abril";
/*     */     case 5:
/* 155 */       return "Mayo";
/*     */     case 6:
/* 156 */       return "Junio";
/*     */     case 7:
/* 157 */       return "Julio";
/*     */     case 8:
/* 158 */       return "Agosto";
/*     */     case 9:
/* 159 */       return "Septiembre";
/*     */     case 10:
/* 160 */       return "Octubre";
/*     */     case 11:
/* 161 */       return "Noviembre";
/*     */     case 12:
/* 162 */       return "Diciembre";
/*     */     }
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getDay(Date fecha)
/*     */   {
/* 174 */     Calendar cal = new GregorianCalendar();
/* 175 */     cal.setTime(fecha);
/* 176 */     return 5;
/*     */   }
/*     */ 
/*     */   public static int getDay()
/*     */   {
/* 185 */     Calendar cal = new GregorianCalendar();
/* 186 */     return 5;
/*     */   }
/*     */ 
/*     */   public static int getDayWeek(Date fecha)
/*     */   {
/* 196 */     Calendar cal = new GregorianCalendar();
/* 197 */     cal.setTime(fecha);
/* 198 */     return cal.get(7) + 1;
/*     */   }
/*     */ 
/*     */   public static int getDayWeek()
/*     */   {
/* 207 */     Calendar cal = new GregorianCalendar();
/* 208 */     return cal.get(7) + 1;
/*     */   }
/*     */ 
/*     */   public static String getDayWeekOnLetters(Date fecha)
/*     */   {
/* 218 */     Calendar cal = new GregorianCalendar();
/* 219 */     cal.setTime(fecha);
/* 220 */     switch (cal.get(7)) {
/*     */     case 1:
/* 222 */       return "Domingo";
/*     */     case 2:
/* 223 */       return "Lunes";
/*     */     case 3:
/* 224 */       return "Martes";
/*     */     case 4:
/* 225 */       return "Miercoles";
/*     */     case 5:
/* 226 */       return "Jueves";
/*     */     case 6:
/* 227 */       return "Viernes";
/*     */     case 7:
/* 228 */       return "Sabado";
/*     */     }
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getDayWeekOnLetters()
/*     */   {
/* 239 */     Calendar cal = new GregorianCalendar();
/* 240 */     switch (cal.get(7)) {
/*     */     case 1:
/* 242 */       return "Domingo";
/*     */     case 2:
/* 243 */       return "Lunes";
/*     */     case 3:
/* 244 */       return "Martes";
/*     */     case 4:
/* 245 */       return "Miercoles";
/*     */     case 5:
/* 246 */       return "Jueves";
/*     */     case 6:
/* 247 */       return "Viernes";
/*     */     case 7:
/* 248 */       return "Sabado";
/*     */     }
/* 250 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getHour()
/*     */   {
/* 259 */     Calendar cal = new GregorianCalendar();
/* 260 */     return 10;
/*     */   }
/*     */ 
/*     */   public static int getHour(Date fecha)
/*     */   {
/* 270 */     Calendar cal = new GregorianCalendar();
/* 271 */     cal.setTime(fecha);
/* 272 */     return 10;
/*     */   }
/*     */ 
/*     */   public static int getHour24()
/*     */   {
/* 282 */     Calendar cal = new GregorianCalendar();
/* 283 */     return 11;
/*     */   }
/*     */ 
/*     */   public static int getHour24(Date fecha)
/*     */   {
/* 293 */     Calendar cal = new GregorianCalendar();
/* 294 */     cal.setTime(fecha);
/* 295 */     return 11;
/*     */   }
/*     */ 
/*     */   public static int getMinute()
/*     */   {
/* 304 */     Calendar cal = new GregorianCalendar();
/* 305 */     return 12;
/*     */   }
/*     */ 
/*     */   public static int getMinute(Date fecha)
/*     */   {
/* 315 */     Calendar cal = new GregorianCalendar();
/* 316 */     cal.setTime(fecha);
/* 317 */     return 12;
/*     */   }
/*     */ 
/*     */   public static int getSecond()
/*     */   {
/* 326 */     Calendar cal = new GregorianCalendar();
/* 327 */     return 13;
/*     */   }
/*     */ 
/*     */   public static int getSecond(Date fecha)
/*     */   {
/* 337 */     Calendar cal = new GregorianCalendar();
/* 338 */     cal.setTime(fecha);
/* 339 */     return 13;
/*     */   }
/*     */ 
/*     */   public static double difDateSecond(Date fecFin, Date fecIni)
/*     */   {
/* 350 */     GregorianCalendar cf = new GregorianCalendar();
/* 351 */     GregorianCalendar ci = new GregorianCalendar();
/* 352 */     if (fecIni != null) ci.setTime(fecIni); else
/* 353 */       ci.setTime(new Date(-1898, 0, 0, 0, 0, 0));
/* 354 */     cf.setTime(fecFin);
/* 355 */     double from = ci.getTime().getTime();
/* 356 */     double to = cf.getTime().getTime();
/* 357 */     double difference = to - from;
/* 358 */     double days = difference / 1000.0D;
/* 359 */     return days;
/*     */   }
/*     */ 
/*     */   public static double difDateMinute(Date fecFin, Date fecIni)
/*     */   {
/* 370 */     GregorianCalendar cf = new GregorianCalendar();
/* 371 */     GregorianCalendar ci = new GregorianCalendar();
/* 372 */     if (fecIni != null) ci.setTime(fecIni); else
/* 373 */       ci.setTime(new Date(-1898, 0, 0, 0, 0, 0));
/* 374 */     cf.setTime(fecFin);
/* 375 */     double from = ci.getTime().getTime();
/* 376 */     double to = cf.getTime().getTime();
/* 377 */     double difference = to - from;
/* 378 */     double days = difference / 60000.0D;
/* 379 */     return days;
/*     */   }
/*     */ 
/*     */   public static double difDateHour(Date fecFin, Date fecIni)
/*     */   {
/* 390 */     GregorianCalendar cf = new GregorianCalendar();
/* 391 */     GregorianCalendar ci = new GregorianCalendar();
/* 392 */     if (fecIni != null) ci.setTime(fecIni); else
/* 393 */       ci.setTime(new Date(-1898, 0, 0, 0, 0, 0));
/* 394 */     cf.setTime(fecFin);
/* 395 */     double from = ci.getTime().getTime();
/* 396 */     double to = cf.getTime().getTime();
/* 397 */     double difference = to - from;
/* 398 */     double days = difference / 3600000.0D;
/* 399 */     return days;
/*     */   }
/*     */ 
/*     */   public static double difDateDay(Date fecFin, Date fecIni)
/*     */   {
/* 410 */     GregorianCalendar cf = new GregorianCalendar();
/* 411 */     GregorianCalendar ci = new GregorianCalendar();
/* 412 */     if (fecIni != null) ci.setTime(fecIni); else
/* 413 */       ci.setTime(new Date(-1898, 0, 0, 0, 0, 0));
/* 414 */     cf.setTime(fecFin);
/* 415 */     double from = ci.getTime().getTime();
/* 416 */     double to = cf.getTime().getTime();
/* 417 */     double difference = to - from;
/* 418 */     double days = difference / 86400000.0D;
/* 419 */     return days;
/*     */   }
/*     */ 
/*     */   public static Date addTime(Date fecha, int annios, int meses, int semanas, int dias, int horas, int minutos)
/*     */   {
/* 434 */     Calendar cal = new GregorianCalendar();
/* 435 */     cal.setTime(fecha);
/* 436 */     if (annios != 0) cal.add(1, annios);
/* 437 */     if (meses != 0) cal.add(2, meses);
/* 438 */     if (semanas != 0) cal.add(4, semanas);
/* 439 */     if (dias != 0) cal.add(5, dias);
/* 440 */     if (horas != 0) cal.add(11, horas);
/* 441 */     if (minutos != 0) cal.add(12, minutos);
/* 442 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static Date addTime(int annios, int meses, int semanas, int dias, int horas, int minutos)
/*     */   {
/* 457 */     Calendar cal = new GregorianCalendar();
/* 458 */     if (annios != 0) cal.add(1, annios);
/* 459 */     if (meses != 0) cal.add(2, meses);
/* 460 */     if (semanas != 0) cal.add(4, semanas);
/* 461 */     if (dias != 0) cal.add(5, dias);
/* 462 */     if (horas != 0) cal.add(11, horas);
/* 463 */     if (minutos != 0) cal.add(12, minutos);
/* 464 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static Date addYears(int annios)
/*     */   {
/* 473 */     return addTime(annios, 0, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   public static Date addMonths(int meses)
/*     */   {
/* 481 */     return addTime(0, meses, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   public static Date addWeeks(int semanas)
/*     */   {
/* 489 */     return addTime(0, 0, semanas, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   public static Date addDays(int dias)
/*     */   {
/* 497 */     return addTime(0, 0, 0, dias, 0, 0);
/*     */   }
/*     */ 
/*     */   public static Date addHours(int horas)
/*     */   {
/* 506 */     return addTime(0, 0, 0, 0, horas, 0);
/*     */   }
/*     */ 
/*     */   public static Date addMinutes(int minutos)
/*     */   {
/* 514 */     return addTime(0, 0, 0, 0, 0, minutos);
/*     */   }
/*     */ 
/*     */   public static String subDays(int dias, String format)
/*     */   {
/* 523 */     Calendar cal = new GregorianCalendar();
/* 524 */     cal.add(5, -dias);
/* 525 */     SimpleDateFormat formating = new SimpleDateFormat(format);
/* 526 */     return formating.format(new Date(cal.getTimeInMillis()));
/*     */   }
/*     */ 
/*     */   public static Boolean compare(Date fecPri, Date fecSeg)
/*     */   {
/* 537 */     if (fecPri == fecSeg) return null;
/* 538 */     if (fecPri.compareTo(fecSeg) > 0) return Boolean.valueOf(true);
/* 539 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   public static Boolean isBetween(Date fecIni, Date fecFin, Date fecha)
/*     */   {
/* 551 */     Calendar cal = new GregorianCalendar();
/* 552 */     cal.setTime(fecha);
/* 553 */     if ((fecha.after(fecIni)) && (fecha.before(fecFin)))
/* 554 */       return Boolean.valueOf(true);
/* 555 */     return Boolean.valueOf(false);
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.helper.DateHelper
 * JD-Core Version:    0.6.2
 */