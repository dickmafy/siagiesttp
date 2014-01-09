/*    */ package com.belogick.factory.util.helper;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.List;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ 
/*    */ public class ListHelper
/*    */ {
/*    */   public List removeDuplicates(List lista)
/*    */   {
/* 22 */     LinkedHashSet set = new LinkedHashSet(lista);
/* 23 */     lista.clear();
/* 24 */     lista.addAll(set);
/* 25 */     set = null;
/* 26 */     return lista;
/*    */   }
/*    */ 
/*    */   public List<Object> removeItem(List<Object> lista, String attribute, String value)
/*    */     throws Exception
/*    */   {
/* 41 */     for (int i = 0; i < lista.size(); i++)
/*    */     {
/* 43 */       Object object = lista.get(i);
/* 44 */       Field field = object.getClass().getField(attribute);
/* 45 */       if (field.get(object) == value)
/* 46 */         lista.remove(i);
/*    */     }
/* 48 */     Object object = null;
/* 49 */     Field field = null;
/* 50 */     return lista;
/*    */   }
/*    */ 
/*    */   public static <T> boolean isInListById(List<T> list, String field, String value)
/*    */     throws Exception
/*    */   {
/* 63 */     for (int i = 0; i < list.size(); i++)
/*    */     {
/* 65 */       Object object = PropertyUtils.getProperty(list.get(i), field);
/* 66 */       if (object.toString().toUpperCase().equals(value.toUpperCase()))
/*    */       {
/* 68 */         object = null;
/* 69 */         return true;
/*    */       }
/*    */     }
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */   public static <T> List<T> startsWith(List<T> list, String field, String value) throws Exception
/*    */   {
/* 77 */     List suggestions = new ArrayList();
/* 78 */     for (Object item : list)
/*    */     {
/* 80 */       Object object = PropertyUtils.getProperty(item, field);
/* 81 */       if (object.toString().startsWith(value))
/* 82 */         suggestions.add(item);
/*    */     }
/* 84 */     return suggestions;
/*    */   }
/*    */ 
/*    */   public static <T> List<T> joinLists(List<T> list1, List<T> list2) throws Exception
/*    */   {
/* 89 */     List lista = new ArrayList();
/*    */     Object item;
/* 90 */     for (Iterator localIterator = list1.iterator(); localIterator.hasNext(); lista.add(item)) item = (Object)localIterator.next();
/* 91 */     
/* 91 */     for (Iterator localIterator = list2.iterator(); localIterator.hasNext(); lista.add(item)) item = (Object)localIterator.next();
/* 92 */     return lista;
/*    */   }
/*    */ }

