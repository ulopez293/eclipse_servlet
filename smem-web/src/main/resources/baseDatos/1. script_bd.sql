CREATE TABLE usuario ( 
	id_usuario			int NOT NULL AUTO_INCREMENT,
    usuario      		varchar(20) NOT NULL,	
    nombre_usuario		varchar(100) NOT NULL,
    contrasenia   		varchar(100) NOT NULL,
    id_proveedor 			int NULL,
	id_centro_trabajo     int NULL,
    rol          			varchar(20) NULL , 
	tipo_usuario     		int NOT NULL, #(1 - ISSSTE, 2- Proveedor)
	fecha_registro   		datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   	datetime NULL,
	fecha_baja   			datetime NULL,
	baja   				bit NULL,
    CONSTRAINT PK_usuario PRIMARY KEY(id_usuario)
);

CREATE TABLE cat_funcionalidad ( 
    id_funcionalidad		int NOT NULL AUTO_INCREMENT,
    funcionalidad   		varchar(50) NOT NULL,	
	fecha_registro   		datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   	datetime NULL,
	fecha_baja   			datetime NULL,
	baja   				bit NULL,
    CONSTRAINT PK_id_funcionalidad PRIMARY KEY(id_funcionalidad)
);

CREATE TABLE cat_unidad_regional (
    id_unidad_regional    int AUTO_INCREMENT NOT NULL,
    id_supervisor		  int NULL,
    id_entidad  		  int NULL, 
    unidad_regional       varchar(100) NULL,
	fecha_registro   		datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   	datetime NULL,
	fecha_baja   			datetime NULL,
	baja   				bit NULL,
     CONSTRAINT PK_region PRIMARY KEY(id_unidad_regional)
);

CREATE TABLE cat_tipo_contratacion ( 
    id_tipo_contratacion	int AUTO_INCREMENT NOT NULL,
    tipo_contratacion   	varchar(250) NOT NULL,
	fecha_registro   		datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   	datetime NULL,
	fecha_baja   			datetime NULL,
	baja   				bit NULL,
     CONSTRAINT PK_id_tipo_contratacion PRIMARY KEY(id_tipo_contratacion)
);

CREATE TABLE cat_centro_trabajo ( 
    id_centro_trabajo         int AUTO_INCREMENT NOT NULL,
    id_unidad_regional        int NOT NULL,	
    urct                  	varchar(50) NOT NULL,
    descripcion           	varchar(100) NULL,
    responsable           	varchar(200) NULL,
    telefono              	varchar(20) NULL,
    estatus               	int NULL,
    direccion             	varchar(250) NULL,
    residente_cargo       	varchar(50) NULL,
    residente_correo      	varchar(100) NULL,
    residente_jefe        	varchar(100) NULL,
    residente_telefono_jefe	varchar(50) NULL,
    residente_correo_jefe  	varchar(100) NULL,
    residente_cargo_jefe   	varchar(100) NULL,
	fecha_registro   			datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   		datetime NULL,
	fecha_baja   				datetime NULL,
	baja   					bit NULL,
    CONSTRAINT PK_id_centro_trabajo PRIMARY KEY(id_centro_trabajo)
);

CREATE TABLE cat_entidad ( 
    id_entidad		int NOT NULL,
    entidad   		varchar(50) NOT NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_id_entidad PRIMARY KEY(id_entidad)
);

CREATE TABLE cat_subtipo_contrato ( 
    id_subtipo_contrato	int AUTO_INCREMENT NOT NULL,
    subtipo_contrato  	varchar(50) NOT NULL,
	fecha_registro   		datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   	datetime NULL,
	fecha_baja   			datetime NULL,
	baja   				bit NULL,
    CONSTRAINT PK_id_subtipo_contrato PRIMARY KEY(id_subtipo_contrato)
);

CREATE TABLE cat_grupo_clave ( 
    id_grupo_clave	int AUTO_INCREMENT NOT NULL,
    id_grupo     		int NOT NULL,
    id_clave     		int NOT NULL,
    id_equipo			int NOT NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_id_grupo_clave PRIMARY KEY(id_grupo_clave)
);

CREATE TABLE cat_equipo ( 
    id_equipo			int AUTO_INCREMENT NOT NULL,
    equipo   			varchar(255) NOT NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_id_equipo PRIMARY KEY(id_equipo)
);

CREATE TABLE cat_clave ( 
    id_clave       	int AUTO_INCREMENT NOT NULL,
    clave          	varchar(255) NOT NULL,
    nombre_generico	varchar(255) NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_id_clave PRIMARY KEY(id_clave)
);

CREATE TABLE cat_grupo ( 
    id_grupo			int AUTO_INCREMENT NOT NULL,
    grupo   			varchar(50) NOT NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_grupo PRIMARY KEY(id_grupo)
);

CREATE TABLE cat_marca ( 
    id_marca			int AUTO_INCREMENT NOT NULL,
    marca   			varchar(100) NOT NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_id_marca PRIMARY KEY(id_marca)
);

CREATE TABLE cat_modelo ( 
    id_modelo			int AUTO_INCREMENT NOT NULL,
    modelo   			varchar(100) NOT NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_id_modelo PRIMARY KEY(id_modelo)
);

CREATE TABLE cat_sector_adq ( 
    id_sector_adq		int AUTO_INCREMENT NOT NULL,
    sector_adq   		varchar(50) NOT NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_id_sector_adq PRIMARY KEY(id_sector_adq)
);

CREATE TABLE cat_especialidad ( 
    id_especialidad	int AUTO_INCREMENT NOT NULL,
    especialidad   	varchar(100) NOT NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_id_especialidad PRIMARY KEY(id_especialidad)
);

CREATE TABLE cat_supervisor ( 
	    id_supervisor	int AUTO_INCREMENT NOT NULL,
	    nombre   	varchar(100) NOT NULL,
	    telefono   	varchar(10) NOT NULL,
	    correo   	varchar(100) NOT NULL,
		fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
		fecha_modificacion datetime NULL,
		fecha_baja   		datetime NULL,
		baja   			bit NULL,
	    CONSTRAINT PK_id_supervisor PRIMARY KEY(id_supervisor)
);

-- FUNCIONALIDAD --
CREATE TABLE programacion_servicio (
    id_programacion_servicio	int AUTO_INCREMENT NOT NULL,
	id_solicitud_servicio int NULL,
    id_tipo_servicio   	int NULL,	
	folio             	varchar(50) NOT NULL, #PRE-001-204-00-203_01
	estatus            	int NULL,
    nprogramacion    		int NULL,
    tecnico_prov        	varchar(250) NULL,
    tel_tecnico         	varchar(50) NULL,
    observaciones      	varchar(500) NULL,
    no_control          	varchar(50) NULL,	
    fecha_visita        	datetime NULL,
	fecha_registro   		datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   	datetime NULL,
	fecha_baja   			datetime NULL,
	baja   				bit NULL,
    CONSTRAINT PK_ProgramacionServicios_depu PRIMARY KEY(id_programacion_servicio)
);

CREATE TABLE solicitud_servicio ( 
    id_solicitud_servicio	int AUTO_INCREMENT NOT NULL,
    id_contrato_detalle  	int NOT NULL,
    fecha_inicio  		datetime NULL,
    fecha_fin     		datetime NULL,
    motivo_solicitud 		varchar(500) NULL,
	fecha_registro   		datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   	datetime NULL,
	fecha_baja   			datetime NULL,
	baja   				bit NULL,
    CONSTRAINT PK_id_solicitud_servicio PRIMARY KEY(id_solicitud_servicio)
);

CREATE TABLE entrega_proveedor ( 
    id_entrega_provedor   	int AUTO_INCREMENT NOT NULL,
    id_programacion_servicio	int NULL,	
    fecha_fin           		datetime NULL,
    comentarios        		varchar(500) NULL,
	fecha_registro   			datetime NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_id_entrega_provedor PRIMARY KEY(id_entrega_provedor)
);

CREATE TABLE acta_entrega_recepcion ( 
    id_acta_entrega_recepcion int AUTO_INCREMENT NOT NULL,
    id_programacion_servicio  int NULL,
    tecnico_enviado      		varchar(200) NULL,
    fecha_inicio_servicio 	datetime NULL,
    fecha_final_servicio  	datetime NULL,
    mantenimiento_exitoso		int NULL,
    atribuible         		varchar(50) NULL,
    horas_real_servicio   	real NULL,
    kit_ref_utilizadas    	varchar(250) NULL,
    recomendaciones     		varchar(500) NULL,
    descripcion_completa_servicio    varchar(500) NULL,
    responsable_equipo        varchar(200) NULL,
	fecha_registro   			datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   		datetime NULL,
	fecha_baja   				datetime NULL,
	baja   					bit NULL,
    CONSTRAINT PK_id_acta_entrega_recepcion PRIMARY KEY(id_acta_entrega_recepcion)
);

CREATE TABLE universo ( 
    id_universo       	int(11) AUTO_INCREMENT NOT NULL,
    id_centro_trabajo     int NOT NULL,
    id_sector_adq      	int NOT NULL,
    id_funcionalidad  	int NOT NULL,
    id_especialidad   	int NOT NULL,
    id_grupo_clave     	int NULL,
    id_marca          	int NOT NULL,
    id_modelo         	int NOT NULL,	
    inventario        	varchar(255) NULL,
    serie             	varchar(50) NULL,
    obsubica          	varchar(250) NULL,
    fecha_instalacion     datetime NULL,
    fecha_apertura        datetime NULL,
    requiere_servicio  	int NULL,
    fecha_garantia      	datetime NULL,
    observaciones     	text NULL,
    numero_licitacion     varchar(100) NULL,
    partida           	varchar(50) NULL,
    nivel_atencion   		varchar(50) NULL,
    no_contrato_adq     	nvarchar(50) NULL,
    actualizacion_tecnologica varchar(255) NULL,
    proveedor_venta_equipo varchar(100) NULL,
    anio_adq           	varchar (30) NULL,
    precio_equipo_sin_iva	varchar (30) NULL,
	fecha_registro   		datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   	datetime NULL,
	fecha_baja   			datetime NULL,
	baja   				bit NULL,
    CONSTRAINT PK_id_universo PRIMARY KEY(id_universo)
);

CREATE TABLE contrato ( 
    id_contrato          		int AUTO_INCREMENT NOT NULL,
	id_tipo_contratacion 		int NOT NULL,
    id_proveedor         		int NOT NULL,
    id_tipo_servicio     		int NOT NULL,
    id_subtipo_contrato      	int NULL,	
    numero_contrato           varchar(50) NULL,
    ejercicio            		int NULL,
    fecha_suscripcion     	datetime NULL,
    oficio_suf_presupuestal	varchar(100) NULL,
    partida              		varchar(50) NULL,
    monto_minimo          	real NULL,
    monto_maximo          	real NULL,
    vigencia_inicio_contrato  datetime NULL,
    vigencia_fin_contrato     datetime NULL,
	fecha_registro   			datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   		datetime NULL,
	fecha_baja   				datetime NULL,
	baja   					bit NULL,	
    CONSTRAINT PK_id_contrato PRIMARY KEY(id_contrato)
);

CREATE TABLE contrato_detalle( 
    id_contrato_detalle int AUTO_INCREMENT NOT NULL,
    id_contrato   	int NOT NULL,
    id_universo   	int(11) NOT NULL,
    periodo       	int NULL,
    consecutivo_contrato  int NULL,
    inicio_periodo	datetime NULL,
    fin_periodo   	datetime NULL,
    enero         	real NULL,
    febrero       	real NULL,
    marzo         	real NULL,
    abril         	real NULL,
    mayo          	real NULL,
    junio         	real NULL,
    julio         	real NULL,
    agosto        	real NULL,
    septiembre    	real NULL,
    octubre       	real NULL,
    noviembre     	real NULL,
    diciembre     	real NULL,
    inicio_vigencia	datetime NULL,
	fecha_registro   	datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion datetime NULL,
	fecha_baja   		datetime NULL,
	baja   			bit NULL,
    CONSTRAINT PK_contrato_detalle PRIMARY KEY(id_contrato_detalle)
);

CREATE TABLE proveedor ( 
    id_proveedor         	int AUTO_INCREMENT NOT NULL,
    proveedor            	varchar(500) NOT NULL,
    rfc                  	varchar(50) NULL,
    telefono             	varchar(50) NULL,
    email                	varchar(100) NULL,
    direccion            	varchar(500) NULL,
    representante_legal             varchar(250) NULL,
    telefono_representante_legal    varchar(50) NULL,
    nombre_gerente_servicio  	varchar(150) NULL,
    telefono_gerente_servicio varchar(50) NULL,
    email_gerente_servicio	varchar(100) NULL,
	fecha_registro   			datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   		datetime NULL,
	fecha_baja   				datetime NULL,
	baja   					bit NULL,
    CONSTRAINT PK_id_proveedor PRIMARY KEY(id_proveedor)
);

CREATE TABLE bitacora ( 
    id_bitacora        	int AUTO_INCREMENT NOT NULL,
    fecha              	datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    operacion            	varchar(20) NOT NULL,
    descripcion        	varchar(300) NOT NULL,
    usuario        		varchar(50) NULL,
	ip_usuario        	varchar(50) NULL,
    CONSTRAINT PK_id_bitacora PRIMARY KEY(id_bitacora)
);

CREATE TABLE historico_movimiento ( 
    id_movimiento		int(11) AUTO_INCREMENT NOT NULL,
    id_universo     	int(11) NOT NULL,
    id_funcionalidad_actual	int NOT NULL,	
	id_funcionalidad_anterior	int NOT NULL,	
	id_centro_trabajo_actual  int NOT NULL,    
	id_centro_trabajo_anterior int NOT NULL,    
    fecha_validacion 			datetime NULL,
    estatus         			int NOT NULL,
    comentarios_unidad    	varchar(500) NULL,
    comentarios_administrador varchar(500) NULL,
	fecha_registro   			datetime NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_modificacion   		datetime NULL,
	fecha_baja   				datetime NULL,
	baja   					bit NULL,
    CONSTRAINT PK_id_movimiento PRIMARY KEY(id_movimiento)
);

CREATE TABLE configuracion ( 
    dias_correctivos	int NOT NULL,
    alerta_incidencia int NOT NULL
);


ALTER TABLE solicitud_servicio ADD FOREIGN KEY (id_contrato_detalle) REFERENCES contrato_detalle(id_contrato_detalle);

ALTER TABLE entrega_proveedor ADD FOREIGN KEY (id_programacion_servicio) REFERENCES programacion_servicio(id_programacion_servicio);

ALTER TABLE acta_entrega_recepcion ADD FOREIGN KEY (id_programacion_servicio) REFERENCES programacion_servicio(id_programacion_servicio);

ALTER TABLE programacion_servicio ADD FOREIGN KEY (id_solicitud_servicio) REFERENCES solicitud_servicio(id_solicitud_servicio);

ALTER TABLE historico_movimiento ADD FOREIGN KEY (id_universo) REFERENCES universo(id_universo);

ALTER TABLE contrato_detalle ADD FOREIGN KEY (id_universo) REFERENCES universo(id_universo);

ALTER TABLE contrato_detalle ADD FOREIGN KEY (id_contrato) REFERENCES contrato(id_contrato);

ALTER TABLE universo ADD FOREIGN KEY (id_centro_trabajo) REFERENCES cat_centro_trabajo(id_centro_trabajo);

ALTER TABLE universo ADD FOREIGN KEY (id_marca) REFERENCES cat_marca(id_marca);

ALTER TABLE universo ADD FOREIGN KEY (id_modelo) REFERENCES cat_modelo(id_modelo);

ALTER TABLE universo ADD FOREIGN KEY (id_sector_adq) REFERENCES cat_sector_adq(id_sector_adq);

ALTER TABLE universo ADD FOREIGN KEY (id_funcionalidad) REFERENCES cat_funcionalidad(id_funcionalidad);

ALTER TABLE universo ADD FOREIGN KEY (id_especialidad) REFERENCES cat_especialidad(id_especialidad);

ALTER TABLE universo ADD FOREIGN KEY (id_grupo_clave) REFERENCES cat_grupo_clave(id_grupo_clave);

ALTER TABLE contrato ADD FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor);

ALTER TABLE contrato ADD FOREIGN KEY (id_subtipo_contrato) REFERENCES cat_subtipo_contrato(id_subtipo_contrato);

ALTER TABLE contrato ADD FOREIGN KEY (id_tipo_contratacion) REFERENCES cat_tipo_contratacion(id_tipo_contratacion);

ALTER TABLE cat_grupo_clave ADD FOREIGN KEY (id_grupo) REFERENCES cat_grupo(id_grupo);

ALTER TABLE cat_grupo_clave ADD FOREIGN KEY (id_clave) REFERENCES cat_clave(id_clave);

ALTER TABLE cat_grupo_clave ADD FOREIGN KEY (id_equipo) REFERENCES cat_equipo(id_equipo);

ALTER TABLE cat_centro_trabajo ADD FOREIGN KEY (id_unidad_regional) REFERENCES cat_unidad_regional(id_unidad_regional);

ALTER TABLE cat_unidad_regional ADD FOREIGN KEY (id_entidad) REFERENCES cat_entidad(id_entidad);

ALTER TABLE historico_movimiento ADD FOREIGN KEY (id_funcionalidad_actual) REFERENCES cat_funcionalidad(id_funcionalidad);

ALTER TABLE historico_movimiento ADD FOREIGN KEY (id_funcionalidad_anterior) REFERENCES cat_funcionalidad(id_funcionalidad);

ALTER TABLE historico_movimiento ADD FOREIGN KEY (id_centro_trabajo_actual) REFERENCES cat_centro_trabajo(id_centro_trabajo);

ALTER TABLE historico_movimiento ADD FOREIGN KEY (id_centro_trabajo_anterior) REFERENCES cat_centro_trabajo(id_centro_trabajo);



-- CREACION DE USUARIO DE BASE DE DATOS PARA AMBIENTE DE DESARROLLO
CREATE USER 'smembduser'@'localhost' IDENTIFIED BY 'j4NvOA@l1sm3m';
GRANT UPDATE, INSERT, SELECT  ON smem.* TO 'smembduser'@'%';


-- CREACION DE USUARIO DE BASE DE DATOS PARA AMBIENTE DE QA
CREATE USER 'smembduserqa'@'localhost' IDENTIFIED BY 'j4NvOA@l1sm3m';
GRANT UPDATE, INSERT, SELECT  ON smemqa.* TO 'smembduserqa'@'%';



