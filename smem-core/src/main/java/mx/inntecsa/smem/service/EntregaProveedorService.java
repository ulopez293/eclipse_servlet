package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.EntregaProveedorDto;
import mx.inntecsa.smem.enums.Estatus;



public interface EntregaProveedorService {
	
	public List<EntregaProveedorDto> getEntregasProveedores();
	public List<EntregaProveedorDto> getEntregasProveedoresByEstatus(Estatus estatus);

}
