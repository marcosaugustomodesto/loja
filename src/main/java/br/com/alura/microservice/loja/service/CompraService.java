package br.com.alura.microservice.loja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.controller.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;

@Service
public class CompraService {
	
//	@Autowired
//	private RestTemplate client;
	
//	@Autowired
//	private DiscoveryClient eurekaClient;

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(CompraDTO compra) {

		final String  estado = compra.getEndereco().getEstado();
		LOG.info("Buscando informações do fornecedor de {}", estado);
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);

		LOG.info("realizando um pedido");
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		
		System.out.println(info.getEndereco());
		
		Compra compraSalva = new Compra();
		
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;
//		ResponseEntity<InfoFornecedoDTO> exchange = 
//				client.exchange("http://fornecedor/info/"+compra.getEndereco().getEstado(),
//				HttpMethod.GET, null, InfoFornecedoDTO.class);
//		
//		eurekaClient.getInstances("fornecedor").stream()
//		.forEach(fornecedor ->{
//			System.out.println("localhost:"+fornecedor.getPort());
//		});
//		
//		System.out.println(exchange.getBody().getEndereco());
	}

}
