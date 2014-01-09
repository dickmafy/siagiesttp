/*    */ package com.belogick.factory.util.converter;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.faces.component.UIComponent;
/*    */ import javax.faces.context.FacesContext;
/*    */ import javax.faces.convert.Converter;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ 
/*    */ public class GenericConverter
/*    */   implements Converter
/*    */ {
/*    */   private HashMap<String, Object> map;
/*    */ 
/*    */   public <T> GenericConverter(List<T> Objects)
/*    */   {
/* 16 */     this.map = new HashMap();
/* 17 */     for (Iterator localIterator = Objects.iterator(); localIterator.hasNext(); ) { 
				Object obj = localIterator.next();
				Object field;
/*    */       try
/*    */       {
/* 22 */         field = PropertyUtils.getProperty(obj, "id");
/* 23 */         this.map.put(field.toString(), obj);
/*    */       } catch (IllegalAccessException e) {
/* 25 */         e.printStackTrace(); } catch (InvocationTargetException e) {
/* 26 */         e.printStackTrace(); } catch (NoSuchMethodException e) {
/* 27 */         e.printStackTrace();
/* 28 */       }
			   field = null; }
/*    */   }
/*    */ 
/*    */   public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue)
/*    */   {
/* 33 */     return this.map.get(submittedValue);
/*    */   }
/*    */ 
/*    */   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
/* 37 */     if (value != null)
/*    */       try {
/* 39 */         return PropertyUtils.getProperty(value, "id").toString(); } catch (IllegalAccessException e) {
/* 40 */         e.printStackTrace(); } catch (InvocationTargetException e) {
/* 41 */         e.printStackTrace(); } catch (NoSuchMethodException e) {
/* 42 */         e.printStackTrace();
/*    */       }
/* 44 */     return null;
/*    */   }
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.converter.GenericConverter
 * JD-Core Version:    0.6.2
 */