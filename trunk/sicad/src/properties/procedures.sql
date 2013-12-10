







CREATE OR REPLACE FUNCTION admision.get_proceso_retiro(IN proceso integer, IN dni character varying)
  RETURNS TABLE(pk_mat integer, pk_pro integer, pk_espe integer, pk_tur integer, pk_secc integer, pk_per integer, estado integer, documento character varying, nombre character varying, apellidopat character varying, apellidomat character varying, estadocivil integer, correo character varying, ubigeo integer, nombreespe character varying, annio integer, tipo integer,periodo integer,ejec integer) AS
$BODY$
    BEGIN
	RETURN QUERY SELECT 
	      m.pk_matricula,m.pk_proceso,m.pk_especialidad,m.pk_turno,m.pk_seccion,m.pk_persona,
              m.estado,pe.documento_nro, pe.nombres, pe.apellido_paterno, 
	      pe.apellido_materno, pe.estado_civil, pe.correo, pe.ubigeo, pro.nombre,p.annio,c.tipo,c.periodo,c.ejecucion
	FROM 
	     admision.m_proceso p,
	     admision.m_matricula m, 
	     admision.m_persona pe, 
	     administracion.m_cronograma c,
	     marco.m_profesion pro	
	WHERE p.pk_institucion =$1
	AND pe.documento_nro=$2
	AND p.pk_proceso=m.pk_proceso
	AND m.pk_persona=pe.pk_persona 
	AND m.pk_especialidad=pro.pk_profesion
	AND c.pk_cronograma=p.cronograma
	AND m.estado=4;
	
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION admision.get_proceso_retiro(integer, character varying)
  OWNER TO postgres; 