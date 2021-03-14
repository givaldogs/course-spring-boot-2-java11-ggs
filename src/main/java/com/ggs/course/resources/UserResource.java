package com.ggs.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggs.course.entities.User;

@RestController
/**
 * Para falar que a Classe e um recurso Web, que e implementado pelo um controlador REST
 * @author GivaldoGS
 *
 */
@RequestMapping(value = "/users")
/**
 * Para dar um nome para o meu recurso
 * @author GivaldoGS
 *
 */
public class UserResource {

	@GetMapping
	/**
	 * Para indicar que o metodo que reponde requisicao do tipo Get do HTTP
	 * @return
	 */
	public ResponseEntity<User> findall() {
		/**
		 * so para testar se esta funcionando esse recurso users,
		 * criar o metodo que vai ser o enti point para acessar os usuarios
		 * o tipo de retorno vai ser um ResponseEntity , que um tipo especifico do
		 * spring para retornar resposta de requisicao WEB.
		 */
		 User u = new User(1L, "Maria", "maria@gmail.com", "99999999", "12345");
		 return ResponseEntity.ok().body(u);
	}
}
