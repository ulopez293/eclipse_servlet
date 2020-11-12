package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.List;

import mx.inntecsa.smem.dao.EntregaProveedorDao;
import mx.inntecsa.smem.dto.EntregaProveedorDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.EntregaProveedor;
import mx.inntecsa.smem.service.EntregaProveedorService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("entregaProveedorService")
public class EntregaProveedorServiceImpl implements EntregaProveedorService {
	
	@Autowired
	private EntregaProveedorDao entregaProveedorDao;

	@Override
	public List<EntregaProveedorDto> getEntregasProveedores() {
		List<EntregaProveedorDto> entregaProveedoresDto = new ArrayList<EntregaProveedorDto>(); //Lista de EntregaProveedores(DTO)

		for (EntregaProveedor entregaProveedor : entregaProveedorDao.getEntregasProveedores()) {
			EntregaProveedorDto entregaProveedorDto = new ModelMapper().map(entregaProveedor, EntregaProveedorDto.class);
			entregaProveedoresDto.add(entregaProveedorDto);
		}
		
		return entregaProveedoresDto;
	}

	@Override
	public List<EntregaProveedorDto> getEntregasProveedoresByEstatus(Estatus estatus) {
		List<EntregaProveedorDto> entregaProveedoresDto = new ArrayList<EntregaProveedorDto>(); //Lista de EntregaProveedores(DTO)

		for (EntregaProveedor entregaProveedor : entregaProveedorDao.getEntregasProveedoresByEstatus(estatus)) {
			EntregaProveedorDto entregaProveedorDto = new ModelMapper().map(entregaProveedor, EntregaProveedorDto.class);
			entregaProveedoresDto.add(entregaProveedorDto);
		}
		
		return entregaProveedoresDto;
	}

}
