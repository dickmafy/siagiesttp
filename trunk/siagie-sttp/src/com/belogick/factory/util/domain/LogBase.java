/*    */ package com.belogick.factory.util.domain;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="log")
/*    */ public class LogBase
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String usuario;
/*    */   private String sistema;
/*    */   private String modulo;
/*    */   private String operacion;
/*    */   private Date fecha;
/*    */   private String host;
/*    */   private String ip_local;
/*    */   private String ip_publica;
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*    */   @Column(name="pk_log", insertable=true)
/*    */   public Long getId()
/*    */   {
/* 28 */     return this.id; } 
/* 29 */   public void setId(Long id) { this.id = id; } 
/*    */   @Column(name="usuario")
/*    */   public String getUsuario() {
/* 32 */     return this.usuario; } 
/* 33 */   public void setUsuario(String usuario) { this.usuario = usuario; } 
/*    */   @Column(name="sistema")
/*    */   public String getSistema() {
/* 36 */     return this.sistema; } 
/* 37 */   public void setSistema(String sistema) { this.sistema = sistema; } 
/*    */   @Column(name="modulo")
/*    */   public String getModulo() {
/* 40 */     return this.modulo; } 
/* 41 */   public void setModulo(String modulo) { this.modulo = modulo; } 
/*    */   @Column(name="operacion")
/*    */   public String getOperacion() {
/* 44 */     return this.operacion; } 
/* 45 */   public void setOperacion(String operacion) { this.operacion = operacion; } 
/*    */   @Column(name="fecha")
/*    */   public Date getFecha() {
/* 48 */     return this.fecha; } 
/* 49 */   public void setFecha(Date fecha) { this.fecha = fecha; } 
/*    */   @Column(name="host")
/*    */   public String getHost() {
/* 52 */     return this.host; } 
/* 53 */   public void setHost(String host) { this.host = host; } 
/*    */   @Column(name="ip_privada")
/*    */   public String getIp_local() {
/* 56 */     return this.ip_local; } 
/* 57 */   public void setIp_local(String ip_local) { this.ip_local = ip_local; } 
/*    */   @Column(name="ip_publica")
/*    */   public String getIp_publica() {
/* 60 */     return this.ip_publica; } 
/* 61 */   public void setIp_publica(String ip_publica) { this.ip_publica = ip_publica; }
/*    */ 
/*    */ }

/* Location:           /Users/jhanlos/Documents/workspace/aprolab/sicad/WebContent/WEB-INF/lib/arquitectura-1.0.jar
 * Qualified Name:     com.belogick.factory.util.domain.LogBase
 * JD-Core Version:    0.6.2
 */