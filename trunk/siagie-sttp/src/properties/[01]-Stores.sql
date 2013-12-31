


CREATE OR REPLACE FUNCTION admision.exist_proceso_cronograma(proceso integer, actividad integer, fecha date) 
RETURNS INTEGER
AS $$
    BEGIN
	RETURN lst.pk_proceso FROM admision.m_proceso_cronograma lst WHERE lst.pk_proceso=$1 AND lst.pk_actividad=$2 AND lst.fecha_inicio<=$3 AND lst.fecha_fin>=$3;
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION admision.exist_proceso(institucion integer, annio integer) 
RETURNS table(pk_pro integer, pk_inst integer, an smallint,  ms smallint, tip smallint, cc real, ci real, cm real, cme real, fec date, reg integer, est integer)
AS $$
    BEGIN
	RETURN QUERY SELECT lst.* 
	FROM admision.m_proceso lst 
	WHERE lst.pk_institucion=$1 AND lst.annio=$2 AND lst.estado>0;
    END;
$$ LANGUAGE plpgsql;





CREATE OR REPLACE FUNCTION seguridad.get_ubigeo(ubigeo integer, tipo boolean) 
RETURNS table(pk integer, depa integer, prov integer, dis integer, des varchar, pos varchar)
AS $$
    BEGIN

	IF tipo=true THEN
		RETURN QUERY SELECT reb.* 
		FROM mantenimiento.m_ubigeo dep, mantenimiento.m_ubigeo reb
		WHERE dep.pk_ubigeo=$1 AND reb.departamento=dep.departamento AND reb.provincia=0 AND reb.distrito=0;
	ELSE

		RETURN QUERY SELECT reb.* 
		FROM mantenimiento.m_ubigeo dep, mantenimiento.m_ubigeo reb
		WHERE dep.pk_ubigeo=$1 AND reb.departamento=dep.departamento AND reb.provincia=dep.provincia AND reb.distrito=0;
	END IF;
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION admision.get_proceso_ingresante(proceso integer, dni varchar) 
RETURNS table(pk_pos integer, pk_pro integer, pk_espe integer, pk_persona integer, pk_turno integer, 
pagfec date, pagban integer, pagrec varchar,
puntuacion real, exonerado smallint, regfec date, refusr integer, estado integer,
docnro varchar, nom varchar, apepat varchar, apemat varchar, nomespe varchar, pks integer)
AS $$
    BEGIN
	RETURN QUERY SELECT lst.*, prs.documento_nro, prs.nombres, prs.apellido_paterno, prs.apellido_materno, esp.nombre, prs.pk_persona
	FROM admision.m_postulante lst, admision.m_persona prs, marco.m_profesion esp
	WHERE lst.pk_proceso=$1 AND lst.estado<>6 AND lst.pk_persona=prs.pk_persona AND lst.pk_especialidad=esp.pk_profesion AND prs.documento_nro=$2;
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION admision.get_proceso_matriculado(IN proceso integer, IN dni character varying)
RETURNS TABLE(pk_mat integer, pk_pro integer, pk_espe integer, pk_tur integer, pk_secc integer, pk_per integer, estado integer, documento character varying, nombre character varying, apellidopat character varying, apellidomat character varying, estadocivil integer, correo character varying, ubigeo integer, nombreespe character varying) 
AS $$
    BEGIN
	RETURN QUERY SELECT lst.pk_matricula, lst.pk_proceso,lst.pk_especialidad, lst.pk_turno, lst.pk_seccion, lst.pk_persona,lst.estado,  prs.documento_nro, prs.nombres, prs.apellido_paterno, prs.apellido_materno, prs.estado_civil, prs.correo, prs.ubigeo, esp.nombre
	FROM admision.m_matricula lst, 
	     admision.m_persona prs, 
	     marco.m_profesion esp
	WHERE lst.pk_proceso=$1 AND lst.estado<>6 
	AND lst.pk_persona=prs.pk_persona 
	AND lst.pk_especialidad=esp.pk_profesion 
	AND prs.documento_nro=$2;
	END;
$$ LANGUAGE plpgsql;






CREATE OR REPLACE FUNCTION admision.get_proceso_retiro(IN proceso integer, IN dni character varying)
  RETURNS TABLE(pk_mat integer, pk_pro integer, pk_espe integer, pk_tur integer, pk_secc integer, pk_per integer, estado integer, documento character varying, nombre character varying, apellidopat character varying, apellidomat character varying, estadocivil integer, correo character varying, ubigeo integer, nombreespe character varying,annio smallint,tipo smallint ) 
 	AS $$
    BEGIN
	RETURN QUERY SELECT 
	      m.pk_matricula,m.pk_proceso,m.pk_especialidad,m.pk_turno,m.pk_seccion,m.pk_persona,
              m.estado,pe.documento_nro, pe.nombres, pe.apellido_paterno, 
	      pe.apellido_materno, pe.estado_civil, pe.correo, pe.ubigeo, pro.nombre,p.annio,p.tipo
			   
	FROM 
	     admision.m_proceso p,
	     admision.m_matricula m, 
	     admision.m_persona pe, 
	     marco.m_profesion pro	
	WHERE p.pk_institucion =$1
	AND pe.documento_nro=$2
	AND p.pk_proceso=m.pk_proceso
	AND m.pk_persona=pe.pk_persona 
	AND m.pk_especialidad=pro.pk_profesion
	AND m.estado=4;
	
END;
$$ LANGUAGE plpgsql;


CREATE TABLE admision.m_retiro
(
 pk_retiro serial NOT NULL,
 pk_matricula integer,
 pk_persona integer,
 motivo smallint,
 observacion character varying(200),
 estado smallint,
 CONSTRAINT m_retiro_pkey PRIMARY KEY (pk_retiro),
 CONSTRAINT "Ref_m_retiro_to_m_matricula" FOREIGN KEY (pk_matricula)
     REFERENCES admision.m_matricula (pk_matricula) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION,
 CONSTRAINT uk_retiro_persona UNIQUE (pk_matricula, pk_persona)
)
WITH (
 OIDS=FALSE
);
ALTER TABLE admision.m_retiro
 OWNER TO postgres;



  

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
  
  
  