package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.inntecsa.smem.dao.ContratoDao;
import mx.inntecsa.smem.dao.ContratoDetalleDao;
import mx.inntecsa.smem.dao.ProveedorDao;
import mx.inntecsa.smem.dao.UsuarioDao;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.pojo.Contrato;
import mx.inntecsa.smem.pojo.ContratoDetalle;
import mx.inntecsa.smem.pojo.Proveedor;
import mx.inntecsa.smem.pojo.Usuario;
import mx.inntecsa.smem.service.ProveedorService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("proveedorService")
public class ProveedorServiceImpl implements ProveedorService {
	
	private Logger log = Logger.getLogger(ProveedorServiceImpl.class);
	
	@Autowired
	private ProveedorDao proveedorDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private ContratoDao contratoDao;
	
	@Autowired
	private ContratoDetalleDao contratoDetalleDao;

	@Override
	public List<ProveedorDto> getProveedores() {
		List<ProveedorDto> proveedoresDto = new ArrayList<ProveedorDto>(); //Lista de Proveedors (DTO)

		for (Proveedor proveedor : proveedorDao.getProveedores()) {
			ProveedorDto proveedorDto = this.manualModelMapper(proveedor);
			proveedoresDto.add(proveedorDto);
		}
		
		return proveedoresDto;

	}

	@Override
	public List<ProveedorDto> getProveedoresByEstatus(Estatus estatus) {		
		List<ProveedorDto> proveedoresDto = new ArrayList<ProveedorDto>(); //Lista de Proveedors (DTO)

		for (Proveedor proveedor : proveedorDao.getProveedoresPorEstatus(estatus)) {
			ProveedorDto proveedorDto = this.manualModelMapper(proveedor);
			proveedoresDto.add(proveedorDto);
		}
		
		return proveedoresDto;

	}

	@Override
	public ProveedorDto getProveedorPorIdProveedor(Integer idProveedor) {
		log.info(">>>En el servicio del proveedor, buscando proveedor con id " + idProveedor);
		Proveedor proveedor = proveedorDao.getProveedorPorIdProveedor(idProveedor);			
				
		if(proveedor != null) {
			ProveedorDto proveedorDto = this.manualModelMapper(proveedor);
			return proveedorDto;
		}	
		
		return null;
	}
	
	@Override
	public ProveedorDto guardarProveedor(ProveedorDto proveedorDto) {
		Proveedor proveedor = new ModelMapper().map(proveedorDto, Proveedor.class);
		
		try{
			@SuppressWarnings("unchecked")
			Integer idProveedor = (Integer) proveedorDao.save(proveedor);
			proveedorDto.setIdProveedor(idProveedor);
		}catch(Exception e){
			log.error("",e);
		}
		
		return proveedorDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean actualizarProveedor(ProveedorDto proveedorDto) {
		
		Proveedor proveedor = new ModelMapper().map(proveedorDto, Proveedor.class);
		
		try{
			proveedorDao.update(proveedor);
			
			//En caso de que exista un usuario asociado proveedor, tambien se debe de actualizar el usuario y nombre 
			//en base a los datos del proveedor
			Usuario usuario = usuarioDao.getUsuarioPorIdProveedor(proveedor.getIdProveedor());
			
			if(usuario != null){
				usuario.setUsuario(proveedor.getRfc());
				usuario.setNombreUsuario(proveedor.getProveedor());
				usuarioDao.update(usuario);
			}
			
		}catch(Exception e){
			log.error("",e);
			return false;
		}
		
		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean eliminarProveedor(ProveedorDto proveedorDto) {
		Proveedor proveedor = new ModelMapper().map(proveedorDto, Proveedor.class);

		try{
			proveedorDao.update(proveedor);
			
			//En caso de que exista un usuario asociado proveedor, tambien se debe de dar de baja
			Usuario usuario = usuarioDao.getUsuarioPorIdProveedor(proveedor.getIdProveedor());
			
			if(usuario != null){
				usuario.setFechaBaja(new Date());
				usuario.setBaja(Estatus.INACTIVO);
				usuarioDao.update(usuario);
			}
			
			//En caso de que el proveedor tenga contratos se deben de dar de baja
			List<Contrato> contratos = contratoDao.getContratosPorParametros(null,proveedor.getIdProveedor(),null, null);
			if(contratos != null && contratos.size() > 0){
				for(Contrato contrato : contratos){
					contrato.setFechaBaja(new Date());
					contrato.setBaja(Estatus.INACTIVO);
				
					contratoDao.update(contrato);
					
					List<ContratoDetalle> contratosDetalles = contratoDetalleDao.getContratosDetallesPorIdContrato(contrato.getIdContrato());
					if(contratosDetalles != null && contratosDetalles.size() > 0){
						
						for(ContratoDetalle contratoD : contratosDetalles){
							contratoD.setFechaBaja( new Date());
							contratoD.setBaja(Estatus.INACTIVO);
						}
						
						contratoDetalleDao.updateAll(contratosDetalles);
					}
				}
			}
			
		}catch(Exception e){
			log.error("",e);
			return false;
		}
		
		return true;
	}
	
	private ProveedorDto manualModelMapper(Proveedor proveedor) {
		ProveedorDto proveedorDto = new ProveedorDto();
		proveedorDto.setIdProveedor(proveedor.getIdProveedor());			
		proveedorDto.setProveedor(proveedor.getProveedor());
		proveedorDto.setRfc(proveedor.getRfc());
		proveedorDto.setTelefono(proveedor.getTelefono());
		proveedorDto.setEmail(proveedor.getEmail());
		proveedorDto.setDireccion(proveedor.getDireccion());
		proveedorDto.setRepresentanteLegal(proveedor.getRepresentanteLegal());			
		proveedorDto.setTelefonoRepresentanteLegal(proveedor.getTelefonoRepresentanteLegal());
		proveedorDto.setNombreGerenteServicio(proveedor.getNombreGerenteServicio());
		proveedorDto.setTelefonoGerenteServicio(proveedor.getTelefonoGerenteServicio());
		proveedorDto.setEmailGerenteServicio(proveedor.getEmailGerenteServicio());
		proveedorDto.setFechaRegistro(proveedor.getFechaRegistro());
		proveedorDto.setFechaModificacion(proveedor.getFechaModificacion());
		proveedorDto.setFechaBaja(proveedor.getFechaBaja());
		proveedorDto.setBaja(proveedor.getBaja());
		return proveedorDto;
	}
	
	@Override
	public List<ProveedorDto> getProveedoresPorNombre(String nombre) {
		List<ProveedorDto> proveedoresDto = new ArrayList<ProveedorDto>();
		for (Proveedor proveedor : proveedorDao.getProveedoresPorNombre(nombre)) {
			ProveedorDto proveedorDto = this.manualModelMapper(proveedor);
			proveedoresDto.add(proveedorDto);
		}
		return proveedoresDto;
	}
	
	@Override
	public List<ProveedorDto> getProveedoresPorNombre(String nombre, Integer idProveedor) {
		List<ProveedorDto> proveedoresDto = new ArrayList<ProveedorDto>();
		for (Proveedor proveedor : proveedorDao.getProveedoresPorNombre(nombre,idProveedor)) {
			ProveedorDto proveedorDto = this.manualModelMapper(proveedor);
			proveedoresDto.add(proveedorDto);
		}
		return proveedoresDto;
	}

}
