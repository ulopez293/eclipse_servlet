package mx.inntecsa.smem.service;


import java.util.List;

import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.enums.Estatus;



public interface ProveedorService {
	
	public List<ProveedorDto> getProveedores();
	public List<ProveedorDto> getProveedoresByEstatus(Estatus estatus);
	public ProveedorDto getProveedorPorIdProveedor(Integer idProveedor);
	public ProveedorDto guardarProveedor(ProveedorDto proveedorDto);
	public boolean actualizarProveedor(ProveedorDto proveedorDto);
	public boolean eliminarProveedor(ProveedorDto proveedorDto);
	public List<ProveedorDto> getProveedoresPorNombre(String nombre, Integer idProveedor);
	public List<ProveedorDto> getProveedoresPorNombre(String nombre);
}
