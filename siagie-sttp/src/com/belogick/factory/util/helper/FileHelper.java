/*     */ package com.belogick.factory.util.helper;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class FileHelper
/*     */ {
/*  17 */   protected static Logger log = Logger.getLogger(FileHelper.class);
/*     */ 
/*     */   public static void deleteFile(String nameFile)
/*     */   {
/*  25 */     File file = new File(nameFile);
/*  26 */     if (file.exists())
/*     */     {
/*  28 */       if (file.canWrite())
/*     */       {
/*  30 */         if (file.delete()) log.debug("Se elimino el archivo: " + nameFile + " con exito"); else
/*  31 */           log.debug("No se puede eliminar el archivo: " + nameFile);
/*     */       }
/*     */       else
/*  34 */         log.debug("No se tienen permisos de escritura para el archivo: " + nameFile);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isExists(String nameFile)
/*     */   {
/*  46 */     File file = new File(nameFile);
/*  47 */     if (file == null) {
/*  48 */       return false;
/*     */     }
/*     */ 
/*  51 */     if (!file.exists()) {
/*  52 */       return false;
/*     */     }
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean isReadOnly(String nameFile)
/*     */   {
/*  64 */     File file = new File(nameFile);
/*  65 */     return file.canWrite();
/*     */   }
/*     */ 
/*     */   public static String getModifiedDate(String nameFile)
/*     */   {
/*  75 */     File file = new File(nameFile);
/*  76 */     if (file.exists())
/*     */     {
/*  78 */       long t = file.lastModified();
/*  79 */       Date d = new Date(t);
/*  80 */       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
/*  81 */       return sdf.format(d);
/*     */     }
/*     */ 
/*  85 */     System.out.println("File not found!");
/*  86 */     return "Not Found";
/*     */   }
/*     */ 
/*     */   public static String getExtension(String nameFile)
/*     */   {
/*  97 */     File file = new File(nameFile);
/*  98 */     if (file.exists())
/*     */     {
/* 100 */       String fname = file.getName();
/* 101 */       return fname.substring(fname.lastIndexOf('.') + 1, fname.length());
/*     */     }
/*     */ 
/* 105 */     System.out.println("File not found!");
/* 106 */     return "Not Found";
/*     */   }
/*     */ 
/*     */   public static long getSize(String nameFile)
/*     */   {
/* 117 */     File file = new File(nameFile);
/* 118 */     if (file.exists()) {
/* 119 */       return file.length() / 1024L;
/*     */     }
/* 121 */     return 0L;
/*     */   }
/*     */ 
/*     */   public static int countFiles(String nameFile)
/*     */   {
/* 131 */     File file = new File(nameFile);
/* 132 */     int count = 0;
/* 133 */     for (File archivo : file.listFiles())
/*     */     {
/* 135 */       if (archivo.isFile())
/* 136 */         count++;
/*     */     }
/* 138 */     return count;
/*     */   }
/*     */ 
/*     */   public static void copyFile(File sourceFile, File targetFile)
/*     */     throws IOException
/*     */   {
/* 149 */     if (!targetFile.exists()) targetFile.createNewFile();
/* 150 */     FileChannel source = null;
/* 151 */     FileChannel destination = null;
/*     */     try {
/* 154 */       source = new FileInputStream(sourceFile).getChannel();
/* 155 */       destination = new FileOutputStream(targetFile).getChannel();
/*     */ 
/* 158 */       long count = 0L;
/* 159 */       long size = source.size();
/* 160 */       while ((count += destination.transferFrom(source, count, size - count)) < size);
/*     */     } finally {
/* 164 */       if (source != null) source.close();
/* 165 */       if (destination != null) destination.close();
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.helper.FileHelper
 * JD-Core Version:    0.6.2
 */