package com.ggs.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggs.course.entities.Product;
import com.ggs.course.services.ProductService;

@RestController
/**
 * Para falar que a Classe e um recurso Web, que e implementado pelo um controlador REST
 * @author GivaldoGS
 *
 */
@RequestMapping(value = "/products")
/**
 * Para dar um nome para o meu recurso (value = "/products")
 * @author GivaldoGS
 *
 */
public class ProductResource {
	
	/**
	 * vamos colocar o annotation @Autowired, para o SPRING ja faca a injecao de dependecia 
	 * para nos.
	 */
	@Autowired
	private ProductService service;

	@GetMapping
	/**
	 * Para indicar que o metodo que reponde requisicao do tipo Get do HTTP
	 * @return
	 * agora o tipo de reposta nao vai mais ser Product ResponseEntity<Product>
	 * vai ser uma list de Product
	 */
	public ResponseEntity<List<Product>> findall() {
		/**
		 * so para testar se esta funcionando esse recurso users,
		 * criar o metodo que vai ser o enti point para acessar os usuarios
		 * o tipo de retorno vai ser um ResponseEntity , que um tipo especifico do
		 * spring para retornar resposta de requisicao WEB.
		 * Depois que criamos o ProductService , vamos retirar intanciacao manual que fizemos
		 * (mocada) que foi Product u = new Product(1L, "Maria", "maria@gmail.com", "99999999", "12345");
		 * e vamos fazer uma declaracao de uma lista de usuario List<Product> 
		 * vamos dar o nome de list, recebendo quem ? ai vamos chamar o servico na operacao
		 * findall()
		 * entao no comeco da classe ProductResource vamos colocar uma dependencia para Service
		 * private ProductService service
		 * 
		 * dentro da operacao vamos chamar o service.findall() list = service.findall()
		 */
		// Product u = new Product(1L, "Maria", "maria@gmail.com", "99999999", "12345");
				
		List<Product> list = service.findall();
		/**
		 * no corpo da resposta vamos colocar a lista body(list)
		 */
		 return ResponseEntity.ok().body(list);
		 /**
		  * temos que fazer o component registration , quando temos um objeto que ele vai poder
		  * ser injetado pelo mecanismo de injecao de independencia do SPRING, a classe desse
		  * seu objeto, tem que estar registrada no mecanismo de injecao de independencia. seja
		  * para back end, front end, vai ter alguma forma de registra o seu servico no mecanismo 
		  * de injecao de dependencia
		  * temos que voltar na classe ProductService e registra-la como componente.
		  */
		 		 
	}
	/**
	  * Vamos colocar o annotation @GetMapping, porque tambem vai ser uma requisicao do
	  * tipo Get
	  * Vamos passar na URL o valor do id do Usuario, para falar que a URL vai ter um
	  * parametro tambem, (value = "/{id}") vai indicar que a requisicao vai aceitar um id
	  * dentro da URL
	  * o Metodo findById vai receber o parametro "/{id}"
	  * para receber o "/{id}" dentro do enti point do controlador REST tem declarar o argumento
	  * Long id, so que para o SPRING aceita o id e considera lo que vai chegar como 
	  * parametro que vai chegar na URL temos colocar o annotation @PathVariable
	  */
	 
	 @GetMapping(value = "/{id}")
	 public ResponseEntity<Product> findById(@PathVariable Long id) {
		 Product obj = service.findById(id);
		 return ResponseEntity.ok().body(obj);
	 }
}
