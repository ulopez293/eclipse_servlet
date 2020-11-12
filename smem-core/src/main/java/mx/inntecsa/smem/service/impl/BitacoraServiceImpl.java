package mx.inntecsa.smem.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import mx.inntecsa.smem.dao.BitacoraDao;
import mx.inntecsa.smem.dto.BitacoraDto;
import mx.inntecsa.smem.pojo.Bitacora;
import mx.inntecsa.smem.service.BitacoraService;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bitacoraService")
public class BitacoraServiceImpl implements BitacoraService {

	private Logger log = Logger.getLogger(BitacoraServiceImpl.class);
	
	@Autowired
	private BitacoraDao bitacoraDao;

	@SuppressWarnings("unchecked")
	@Override
	public boolean registraBitacora(BitacoraDto bitacoraDto) {
		Bitacora bitacora = new ModelMapper().map(bitacoraDto, Bitacora.class);
		try{
			bitacoraDao.save(bitacora);
			return true;
		}catch(Exception e){
			log.error("",e);
		}
		
		return false;	
	}

	@Override
	public List<BitacoraDto> getBitacoraPorParametros(String usuario,Date fechaInicio, Date fechaFin, String movimiento) {
		
		try{
			List<Bitacora> bitacora = bitacoraDao.getBitacoraPorParametros(usuario, fechaInicio, fechaFin, movimiento);
			List<BitacoraDto> bitacoraDto = new ArrayList<BitacoraDto>();
			
			for(Bitacora bit : bitacora){
				BitacoraDto bitDto = new ModelMapper().map(bit, BitacoraDto.class);
				bitacoraDto.add(bitDto);
			}
			
			return bitacoraDto;
		}catch(Exception e){
			log.error("",e);
		}	
	
		return null;
	}

	@Override
	public List<BitacoraDto> getBitacora(String usuario) {
		try{
			List<Bitacora> bitacora = bitacoraDao.getBitacora(usuario);
			List<BitacoraDto> bitacoraDto = new ArrayList<BitacoraDto>();
			
			for(Bitacora bit : bitacora){
				BitacoraDto bitDto = new ModelMapper().map(bit, BitacoraDto.class);
				bitacoraDto.add(bitDto);
			}
			
			return bitacoraDto;
		}catch(Exception e){
			log.error("",e);
		}	
	
		return null;	
	}
}
