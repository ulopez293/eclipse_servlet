package mx.inntecsa.smem.bean;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import mx.inntecsa.smem.dto.ContratoDto;
import mx.inntecsa.smem.dto.DetalleFacturaDto;
import mx.inntecsa.smem.dto.FacturaDto;
import mx.inntecsa.smem.dto.ProveedorDto;
import mx.inntecsa.smem.enums.Estatus;
import mx.inntecsa.smem.enums.EstatusContrato;
import mx.inntecsa.smem.enums.Mes;
import mx.inntecsa.smem.enums.TipoServicio;
import mx.inntecsa.smem.service.ContratoService;
import mx.inntecsa.smem.service.FacturaService;
import mx.inntecsa.smem.service.ProveedorService;
import mx.inntecsa.smem.util.DownloadFile;
import mx.inntecsa.smem.util.VistasEnum;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("session")
@Component
public class FacturaBean implements Serializable{

	private static final long serialVersionUID = -5448955262142835369L;
	private Logger log = Logger.getLogger(FacturaBean.class);
	private int pagina; // Paginador para la tabla
	private FacturaDto facturaDto;
	private TipoServicio[] tiposServicio = TipoServicio.values();
	private Mes[] meses = Mes.values();
	private List<ProveedorDto> proveedores;
	private List<ContratoDto> contratos;
	private ProveedorDto proveedor;
	private ContratoDto contrato;
	private TipoServicio tipoServicio;
	private Mes mes;
	
	@Autowired
	private AlertaBean alertaBean; // Bean para mensajes al usuario
	
	@Autowired
	private ProveedorService proveedorService;

	@Autowired
	private ContratoService contratoService;

	@Autowired
	private FacturaService facturaService;
	
	public String inicializarObjectosPago(){
		
		this.init();
		return VistasEnum.OBJECTOSPAGO.getIdVista();
	}

	public String inicializarPenasConvencionales(){
		this.init();
		return VistasEnum.PENASCONVENCIONALES.getIdVista();
	}
	
	public void init(){
		log.info("Metodo inicializar. Factura Bean.");
		this.pagina = 1;
		
		facturaDto = new FacturaDto();
		proveedor = new ProveedorDto();
		contrato = new ContratoDto();
		proveedores = proveedorService.getProveedoresByEstatus(Estatus.ACTIVO);		
	}

	public void buscarContratos(){
		log.info("Buscando contratos para el proveedor: " + proveedor.getIdProveedor());
		contratos = contratoService.getContratosPorParametros(EstatusContrato.VIGENTE,proveedor.getIdProveedor(),null, null);
		
		if(contratos != null)
			log.info("Contratos encontratos: " + contratos.size());
	}
	
	public void buscarRegistrosObjectosPago(){
		log.info("Buscando registros objectos pago.. contrato " + contrato.getIdContrato());
		facturaDto = facturaService.getServiciosObjectoPago(contrato.getIdContrato(), mes, tipoServicio);
	}
	
	public void buscarRegistrosPenasConvencionales(){
		log.info("Buscando registros penas convencionales.. contrato " + contrato.getIdContrato());
		facturaDto = facturaService.getServiciosConPenalizacion(contrato.getIdContrato(), mes, tipoServicio);		
	}
	
	public void descargarExcel(){
		log.info(">>>Descargando archivo");		
		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			String []header = {"ID EQUIPO","EQUIPO", "MARCA","MODELO","SERIE","INVENTARIO","CENTRO DE TRABAJO",
					"TIPO DE SERVICIO","NO. DE CASO","CONSECUTIVO","FECHA INICIAL","FECHA FINAL","FECHA REAL","COSTO UNITARIO",
					"DIAS DE ATRASO","PORCENTAJE PENAS","PENAS CONVENCIONALES","DESCUENTOS","MES"};
		    
			List<String> headerList = Arrays.asList(header);  

			DownloadFile descargarArchivo = new DownloadFile("detalleFactura.xls",headerList);
			
			if(facturaDto.getListaDetalles() != null){
				descargarArchivo.write(this.getDetalleParaExcel(facturaDto.getListaDetalles()));
			}
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=\"" + "detalleFactura.xls" + "\"");

			OutputStream os = response.getOutputStream();
			os.write(descargarArchivo.getByteFile());
			os.flush();
			os.close();
			FacesContext.getCurrentInstance().responseComplete();
			
		} catch (Exception e) {
			log.error(">>>Error : " , e);
		}		
	}

	private List<List<String>> getDetalleParaExcel(List<DetalleFacturaDto> facturaDetallesDto) {
		List<List<String>> datos = new ArrayList<List<String>>();
		
		for (DetalleFacturaDto detalleDto : facturaDetallesDto) {
			List<String> listaDetalle = new ArrayList<String>();

			listaDetalle.add(detalleDto.getIdequipo().toString());
			listaDetalle.add(detalleDto.getEquipo());
			listaDetalle.add(detalleDto.getMarca());			
			listaDetalle.add(detalleDto.getModelo());			
			listaDetalle.add(detalleDto.getSerie());
			listaDetalle.add(detalleDto.getInventario());
			listaDetalle.add(detalleDto.getCentrotrabajo());
			listaDetalle.add(detalleDto.getTiposervicio());
			listaDetalle.add(detalleDto.getFolio());
			listaDetalle.add(detalleDto.getConsecutivocontrato().toString());
			listaDetalle.add(detalleDto.getFechainicial().toString());
			listaDetalle.add(detalleDto.getFechalimite().toString());
			
			if(detalleDto.getFechareal()!=null)
				listaDetalle.add(detalleDto.getFechareal().toString());
			else
				listaDetalle.add("");
			
			listaDetalle.add(detalleDto.getCosto().toString());
			listaDetalle.add(detalleDto.getDiasatraso().toString());
			listaDetalle.add(detalleDto.getPorcentajepenalizacion());
			listaDetalle.add(String.valueOf(detalleDto.getPenalizacion()));
			listaDetalle.add(String.valueOf(detalleDto.getDescuento()));
			listaDetalle.add(mes.getDescripcion());
			
			//agregando lista
			datos.add(listaDetalle);
		}		
		
		return datos;
	}
	
	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public List<ProveedorDto> getProveedores() {
		return proveedores;
	}
	
	public List<ContratoDto> getContratos() {
		return contratos;
	}

	public ProveedorDto getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDto proveedor) {
		this.proveedor = proveedor;
	}

	public ContratoDto getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDto contrato) {
		this.contrato = contrato;
	}

	public TipoServicio[] getTiposServicio() {
		return tiposServicio;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Mes[] getMeses() {
		return meses;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public FacturaDto getFacturaDto() {
		return facturaDto;
	}
}
