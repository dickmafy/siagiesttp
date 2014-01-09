/*     */ package com.belogick.factory.util.controller;
/*     */ 
/*     */ import com.belogick.factory.util.constant.Constante;
/*     */ import com.belogick.factory.util.helper.BeanHelper;
/*     */ import com.belogick.factory.util.helper.ItemHelper;
/*     */ import com.belogick.factory.util.service.GenericService;
/*     */ import java.util.List;
/*     */ import javax.faces.event.ActionEvent;
/*     */ import javax.faces.model.SelectItem;
/*     */ 
/*     */ public class GenericController extends GenericTransaction
/*     */ {
/*     */   int defaultListSize;
/*     */ 
/*     */   public void defaultBean()
/*     */     throws Exception
/*     */   {
/*  14 */     setBean(newInstance(getBean()));
/*     */   }
/*     */   public void defaultList() throws Exception {
/*  17 */     setBeanList(getService().listByObjectEnabledDisabled(newInstance(getBean())));
/*     */   }
/*  19 */   public int getDefaultListSize() { return getBeanList().size(); } 
/*  20 */   public void setDefaultListSize(int defaultListSize) { this.defaultListSize = defaultListSize; }
/*     */ 
/*     */ 
/*     */   public void nativeCancel(ActionEvent event)
/*     */     throws Exception
/*     */   {
/*  27 */     defaultBean();
/*  28 */     defaultList();
/*     */   }
/*     */ 
/*     */   public void nativeNew(ActionEvent event)
/*     */     throws Exception
/*     */   {
/*  36 */     beforeNew();
/*  37 */     defaultBean();
/*  38 */     afterNew();
/*  39 */     forward(this.page_new);
/*     */   }
/*     */ 
/*     */   public void nativeSave(ActionEvent event)
/*     */     throws Exception
/*     */   {
/*  47 */     if (validation())
/*     */     {
/*  49 */       beforeSave();
/*  50 */       if (this.uppercase) setBeanSave(BeanHelper.toUpperCase(getBean())); else
/*  51 */         setBeanSave(getBean());
/*  52 */       super.save(event);
/*  53 */       afterSave();
/*  54 */       defaultBean();
/*  55 */       defaultList();
/*  56 */       finishSave();
/*  57 */       forward(this.page_main);
/*     */     }
/*     */     else {
/*  60 */       forward(this.page_new);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void nativeSaveBack(ActionEvent event)
/*     */     throws Exception
/*     */   {
/*  68 */     if (validation())
/*     */     {
/*  70 */       beforeSave();
/*  71 */       if (this.uppercase) setBeanSave(BeanHelper.toUpperCase(getBean())); else
/*  72 */         setBeanSave(getBean());
/*  73 */       super.save(event);
/*  74 */       afterSave();
/*  75 */       defaultBean();
/*  76 */       defaultList();
/*  77 */       finishSave();
/*     */     }
/*  79 */     forward(this.page_new);
/*     */   }
/*     */ 
/*     */   public void nativeUpdate(ActionEvent event)
/*     */     throws Exception
/*     */   {
/*  87 */     if (validation())
/*     */     {
/*  89 */       beforeUpdate();
/*  90 */       if (this.uppercase) setBeanSave(BeanHelper.toUpperCase(getBean())); else
/*  91 */         setBeanSave(getBean());
/*  92 */       super.update(event);
/*  93 */       afterUpdate();
/*  94 */       defaultBean();
/*  95 */       defaultList();
/*  96 */       finishUpdate();
/*  97 */       forward(this.page_main);
/*     */     }
/*     */     else {
/* 100 */       forward(this.page_update);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void nativeDelete(ActionEvent event)
/*     */     throws Exception
/*     */   {
/* 108 */     beforeDelete();
/* 109 */     setBeanDelete(getBean());
/* 110 */     super.delete(event);
/* 111 */     afterDelete();
/* 112 */     defaultBean();
/* 113 */     defaultList();
/* 114 */     finishDelete();
/*     */   }
/*     */ 
/*     */   public void nativeRemove()
/*     */     throws Exception
/*     */   {
/* 122 */     beforeRemove();
/* 123 */     status(getBeanSelected(), Constante.ROW_STATUS_DELETE);
/* 124 */     afterRemove();
/* 125 */     setMessageSuccess("El registro ha sido eliminado.");
/* 126 */     saveLog("Registro Eliminado");
/* 127 */     defaultList();
/* 128 */     finishRemove();
/*     */   }
/*     */ 
/*     */   public void nativeLoad()
/*     */     throws Exception
/*     */   {
/* 136 */     beforeLoad();
/* 137 */     setBean(getBeanSelected());
/* 138 */     afterLoad();
/* 139 */     forward(this.page_update);
/*     */   }
/*     */ 
/*     */   public void nativeEnabled()
/*     */     throws Exception
/*     */   {
/* 147 */     status(getBeanSelected(), Constante.ROW_STATUS_ENABLED);
/* 148 */     setMessageSuccess("El registro ha sido habilitado exitosamente.");
/* 149 */     saveLog("Registro Habilitado");
/* 150 */     defaultList();
/*     */   }
/*     */ 
/*     */   public void nativeDisabled()
/*     */     throws Exception
/*     */   {
/* 158 */     status(getBeanSelected(), Constante.ROW_STATUS_DISABLED);
/* 159 */     setMessageSuccess("El registro ha sido deshabilitado exitosamente.");
/* 160 */     saveLog("Registro Deshabilitado");
/* 161 */     defaultList();
/*     */   }
/*     */ 
/*     */   public List<SelectItem> getListSelectItem(Object object, String value, String label, boolean optionSelect)
/*     */   {
/* 177 */     List lista = null;
/*     */     try { lista = getService().listByObjectEnabled(object); } catch (Exception e) {
/* 179 */       e.printStackTrace();
/* 180 */     }lista = ItemHelper.listToListItems(lista, value, label, optionSelect);
/* 181 */     lista = ItemHelper.sortByName(lista);
/* 182 */     return lista;
/*     */   }
/*     */ 
/*     */   public List<SelectItem> getListSelectItem(List list, String value, String label, boolean optionSelect) {
/* 186 */     List lista = null;
/* 187 */     lista = ItemHelper.listToListItems(list, value, label, optionSelect);
/* 188 */     lista = ItemHelper.sortByName(lista);
/* 189 */     return lista;
/*     */   }
/*     */ 
/*     */   public List<SelectItem> getListSelectItem(Object object, String value, String labels, String separator, boolean optionSelect) {
/* 193 */     List lista = null;
/*     */     try { lista = getService().listByObjectEnabled(object); } catch (Exception e) {
/* 195 */       e.printStackTrace();
/* 196 */     }lista = ItemHelper.listToListItems(lista, value, labels, separator, optionSelect);
/* 197 */     lista = ItemHelper.sortByName(lista);
/* 198 */     return lista;
/*     */   }
/*     */ 
/*     */   public List<SelectItem> getListSelectItem(List list, String value, String labels, String separator, boolean optionSelect) {
/* 202 */     List lista = null;
/* 203 */     lista = ItemHelper.listToListItems(list, value, labels, separator, optionSelect);
/* 204 */     lista = ItemHelper.sortByName(lista);
/* 205 */     return lista;
/*     */   }
/*     */ 
/*     */   public List<SelectItem> getListSelectItem(List list) {
/* 209 */     List lista = null;
/* 210 */     lista = ItemHelper.listToListItems(list);
/* 211 */     lista = ItemHelper.sortByName(lista);
/* 212 */     return lista;
/*     */   }
/*     */   public List<SelectItem> addLastItemsToListItem(List<SelectItem> lista, String itemValue, String itemLabel) {
/* 215 */     return ItemHelper.addLastItemsToListItem(lista, itemValue, itemLabel);
/*     */   }
/* 217 */   public List<SelectItem> addFirstItemsToListItem(List<SelectItem> lista, String itemValue, String itemLabel, boolean optionSelect) { return ItemHelper.addFirstItemsToListItem(lista, itemValue, itemLabel, optionSelect); }
/*     */ 
/*     */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.controller.GenericController
 * JD-Core Version:    0.6.2
 */