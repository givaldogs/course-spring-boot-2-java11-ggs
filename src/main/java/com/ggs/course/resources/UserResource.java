package com.ggs.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ggs.course.entities.User;
import com.ggs.course.services.UserService;

@RestController
/**
 * Para falar que a Classe e um recurso Web, que e implementado pelo um controlador REST
 * @author GivaldoGS
 *
 */
@RequestMapping(value = "/users")
/**
 * Para dar um nome para o meu recurso (value = "/users")
 * @author GivaldoGS
 *
 */
public class UserResource {
	
	/**
	 * vamos colocar o annotation @Autowired, para o SPRING ja faca a injecao de dependecia 
	 * para nos.
	 */
	@Autowired
	private UserService service;

	@GetMapping
	/**
	 * Para indicar que o metodo que reponde requisicao do tipo Get do HTTP
	 * @return
	 * agora o tipo de reposta nao vai mais ser User ResponseEntity<User>
	 * vai ser uma list de User
	 */
	public ResponseEntity<List<User>> findall() {
		/**
		 * so para testar se esta funcionando esse recurso users,
		 * criar o metodo que vai ser o enti point para acessar os usuarios
		 * o tipo de retorno vai ser um ResponseEntity , que um tipo especifico do
		 * spring para retornar resposta de requisicao WEB.
		 * Depois que criamos o UserService , vamos retirar intanciacao manual que fizemos
		 * (mocada) que foi User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345");
		 * e vamos fazer uma declaracao de uma lista de usuario List<User> 
		 * vamos dar o nome de list, recebendo quem ? ai vamos chamar o servico na operacao
		 * findall()
		 * entao no comeco da classe UserResource vamos colocar uma dependencia para Service
		 * private UserService service
		 * 
		 * dentro da operacao vamos chamar o service.findall() list = service.findall()
		 */
		// User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345");
				
		List<User> list = service.findall();
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
		  * temos que voltar na classe UserService e registra-la como componente.
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
	 public ResponseEntity<User> findById(@PathVariable Long id) {
		 User obj = service.findById(id);
		 return ResponseEntity.ok().body(obj);
	 }
	 
	 /**
	  * Para dizer que esse objeto -> "insert(User obj)" vai chegar no modo
	  * json na hora de fazer a requisicao, e esse json vai descerializado para o objeto USER
	  * do meu JAVA temos que colocar o anotation @RequestBody
	  * URI uri = ServletUriComponentsBuilder, e para retorno para o codigo HTTP = 201
	  */
	 
	 @PostMapping
	 public ResponseEntity<User> insert(@RequestBody User obj) {
		 obj = service.insert(obj);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).body(obj);
	 }
	 
	 /**
	  * para que a Long id seja reconhecida como uma variavel na URL, USAMOS O 
	  * COMANDO @PathVariable 
	  * 
	  * ResponseEntity.noContent - vai retornar um resposta vazia com o codigo
	  * HTTP 204
	  */
	 
	 @DeleteMapping(value = "/{id}") 
	 public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();		 
	 }
	 
	 @PutMapping(value = "/{id}") 
	 public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		  obj = service.update(id, obj);
		  return ResponseEntity.ok().body(obj);
	 }
}
