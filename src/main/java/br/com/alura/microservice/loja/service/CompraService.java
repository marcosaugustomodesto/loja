package br.com.alura.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;

@Service
public class CompraService {
	
//	@Autowired
//	private RestTemplate client;
	
//	@Autowired
//	private DiscoveryClient eurekaClient;

	@Autowired
	private FornecedorClient fornecedorClient;
	
	public void realizaCompra(CompraDTO compra) {
		
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		System.out.println(info.getEndereco());
		
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
