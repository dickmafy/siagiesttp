/*    */ package com.belogick.factory.util.helper;
/*    */ 
/*    */ import com.belogick.factory.util.support.ServiceException;
/*    */ import java.io.PrintStream;
/*    */ import java.lang.annotation.Annotation;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ public class BeanHelper
/*    */ {
/*    */   public static void printAll(Object object)
/*    */     throws Exception
/*    */   {
/* 25 */     Field[] fields = object.getClass().getDeclaredFields();
/* 26 */     for (Field f : fields)
/*    */     {
/* 28 */       f.setAccessible(true);
/* 29 */       System.out.print(f.getName());
/* 30 */       if (f.get(object) != null) System.out.print(": " + f.get(object)); else
/* 31 */         System.out.print(": null");
/* 32 */       System.out.println("");
/*    */     }
/* 34 */     fields = null;
/*    */   }
/*    */ 
/*    */   public static Object newInstance(Object object)
/*    */     throws Exception
/*    */   {
/* 47 */     Class clazz = object.getClass();
			Object tmpObject;
/*    */     try {
/* 49 */       tmpObject = Class.forName(clazz.getName()).newInstance();
/*    */     }
/*    */     catch (InstantiationException e)
/*    */     {
/*    */       
/* 50 */       throw new ServiceException(e); } catch (IllegalAccessException e) {
/* 51 */       throw new ServiceException(e); } catch (ClassNotFoundException e) {
/* 52 */       throw new ServiceException(e);
/*    */     }
/*    */     
/* 53 */     return tmpObject;
/*    */   }
/*    */ 
/*    */   public static Object toUpperCase(Object object)
/*    */     throws Exception
/*    */   {
/* 64 */     Field[] fields = object.getClass().getDeclaredFields();
/* 65 */     for (Field f : fields)
/*    */     {
/* 67 */       f.setAccessible(true);
/* 68 */       if (f.get(object) != null)
/*    */       {
/* 70 */         if (f.getType() == String.class)
/*    */         {
/* 72 */           String value = (String)f.get(object);
/* 73 */           f.set(object, value.toUpperCase());
/* 74 */           value = null;
/*    */         }
/*    */       }
/*    */     }
/* 78 */     fields = null;
/* 79 */     return object;
/*    */   }
/*    */ 
/*    */   public static String getTableName(Object object)
/*    */   {
/* 84 */     Annotation annotation = object.getClass().getAnnotation(Table.class);
/* 85 */     if (annotation != null)
/*    */       try {
/* 87 */         return (String)annotation.annotationType().getMethod("name", new Class[0]).invoke(annotation, new Object[0]); } catch (Exception ex) {
/* 88 */         System.out.println(ex);
/*    */       }
/* 90 */     return null;
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.helper.BeanHelper
 * JD-Core Version:    0.6.2
 */