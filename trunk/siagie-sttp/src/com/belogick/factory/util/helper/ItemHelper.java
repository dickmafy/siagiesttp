/*     */ package com.belogick.factory.util.helper;
/*     */ 
/*     */ import com.belogick.factory.util.constant.Constante;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.faces.model.SelectItem;
/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class ItemHelper
/*     */ {
/*  21 */   private static final SelectItem opcionSeleccionar = new SelectItem(Constante.NO_SELECTED, "--SELECCIONAR--");
/*  22 */   private static Logger log = Logger.getLogger(ItemHelper.class);
/*     */ 
/*     */   public static List<SelectItem> listToListItems(Collection list, String value, String label, boolean optionSelect)
/*     */   {
/*  33 */     List items = new ArrayList();
/*  34 */     if (optionSelect) items.add(opcionSeleccionar);
/*     */     try
/*     */     {
/*  37 */       for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Object object = localIterator.next();
/*  38 */         items.add(new SelectItem(BeanUtils.getSimpleProperty(object, value), BeanUtils.getSimpleProperty(object, label))); }
/*     */     }
/*     */     catch (IllegalAccessException e) {
/*  41 */       e.printStackTrace(); log.debug(e); } catch (InvocationTargetException e) {
/*  42 */       e.printStackTrace(); log.debug(e); } catch (NoSuchMethodException e) {
/*  43 */       e.printStackTrace(); log.debug(e);
/*  44 */     }return items;
/*     */   }
/*     */ 
/*     */   public static List<SelectItem> listToListItems(Collection list, String value, String label, String separador, boolean optionSelect)
/*     */   {
/*  57 */     String[] arreglo = label.split(",");
/*  58 */     String valor = "";
/*  59 */     List items = new ArrayList();
/*  60 */     if (optionSelect) items.add(opcionSeleccionar);
/*     */     try
/*     */     {
/*  63 */       for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Object object = localIterator.next();
/*     */ 
/*  65 */         for (int i = 0; i < arreglo.length; i++)
/*     */         {
/*  67 */           if (i == 0) valor = BeanUtils.getSimpleProperty(object, arreglo[i]); else
/*  68 */             valor = valor + separador + BeanUtils.getSimpleProperty(object, arreglo[i]);
/*     */         }
/*  70 */         items.add(new SelectItem(BeanUtils.getSimpleProperty(object, value), valor)); }
/*     */     }
/*     */     catch (IllegalAccessException e) {
/*  73 */       e.printStackTrace(); log.debug(e); } catch (InvocationTargetException e) {
/*  74 */       e.printStackTrace(); log.debug(e); } catch (NoSuchMethodException e) {
/*  75 */       e.printStackTrace(); log.debug(e);
/*  76 */     }return items;
/*     */   }
/*     */ 
/*     */   public static List<SelectItem> listToListItems(List list)
/*     */   {
/*  81 */     List items = new ArrayList();
/*  82 */     items.add(opcionSeleccionar);
/*  83 */     for (int i = 0; i < list.size(); i++)
/*  84 */       items.add(new SelectItem(list.get(i).toString(), list.get(i).toString()));
/*  85 */     return items;
/*     */   }
/*     */ 
/*     */   public static List<SelectItem> sortByName(List<SelectItem> lista)
/*     */   {
/*  95 */     Iterator iterador = lista.iterator();
/*  96 */     List ids = new ArrayList();
/*  97 */     Hashtable hashContent = new Hashtable();
/*  98 */     while (iterador.hasNext())
/*     */     {
/* 100 */       SelectItem item = (SelectItem)iterador.next();
/* 101 */       ids.add(item.getLabel().toUpperCase());
/* 102 */       hashContent.put(item.getLabel().toUpperCase(), item.getValue());
/*     */     }
/* 104 */     Collections.sort(ids);
/* 105 */     lista = new ArrayList();
/* 106 */     SelectItem newItem = null;
/* 107 */     for (int i = 0; i < ids.size(); i++)
/*     */     {
/* 109 */       newItem = new SelectItem(hashContent.get(ids.get(i)), (String)ids.get(i));
/* 110 */       lista.add(newItem);
/*     */     }
/* 112 */     return lista;
/*     */   }
/*     */ 
/*     */   public static List<SelectItem> addLastItemsToListItem(List<SelectItem> lista, String itemValue, String itemLabel)
/*     */   {
/* 124 */     SelectItem nuevoItem = new SelectItem(itemValue, itemLabel);
/* 125 */     lista.add(nuevoItem);
/* 126 */     nuevoItem = null;
/* 127 */     return lista;
/*     */   }
/*     */ 
/*     */   public static List<SelectItem> addFirstItemsToListItem(List<SelectItem> lista, String itemValue, String itemLabel, boolean optionSelect)
/*     */   {
/* 132 */     List items = new ArrayList();
/* 133 */     if (optionSelect) items.add(opcionSeleccionar);
/* 134 */     SelectItem nuevoItem = new SelectItem(itemValue, itemLabel);
/* 135 */     items.add(nuevoItem);
/* 136 */     for (int i = 1; i < lista.size(); i++)
/* 137 */       items.add((SelectItem)lista.get(i));
/* 138 */     return items;
/*     */   }
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.helper.ItemHelper
 * JD-Core Version:    0.6.2
 */