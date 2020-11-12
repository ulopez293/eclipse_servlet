package mx.inntecsa.smem.util;

public enum VistasEnum {
	
	USUARIOS("pantallaUsuarios"),
	USUARIOS_PROVEEDOR("pantallaUsuariosProveedor"),
	CONTRATOS("pantallaContratos"),
	BITACORA("bitacora"),
	PROGRAMACIONSERVICIOS("programacionServicios"),
	DESBLOQUEO("desbloqueoUrct"),
	UNIVERSO("pantallaUniverso"),
	SOLICITUDSERVICIOS("solicitudServicio"),
	EQUIPOS("pantallaEquipos"),
	CENTROS_TRABAJO("pantallaCentrosTrabajo"),
	CLAVES("pantallaClaves"),
	ESPECIALIDAD("pantallaEspecialidad"),
	GRUPO("pantallaGrupo"),
	GRUPO_CLAVE("pantallaGrupoClave"),
	MARCA("pantallaMarca"),
	MODELO("pantallaModelo"),
	NIVEL_ATENCION("pantallaNivelAtencion"),
	TIPO_CONTRATACION("pantallaTipoContratacion"),
	SECTOR_ADQUISICION("pantallaSectorAdquisicion"),
	SUBTIPOCONTRATO("pantallaSubtipoContrato"),
	SUBTIPO_CONTRATACION("pantallaSubtipoContratacion"),
	PROVEEDORES("pantallaProveedores"),
	OBJECTOSPAGO("pantallaObjectosPago"),
	PENASCONVENCIONALES("pantallaPenasConvencionales"),
	IMPRESIONACTAS("pantallaImpresionActas"),
	CONFIGURACION("pantallaConfiguracion");
	
	private String idVista; //identificador de la vista
	
	private VistasEnum(String idVista) {
		this.idVista = idVista;
	}


	public String getIdVista() {
		return idVista;
	}

}
