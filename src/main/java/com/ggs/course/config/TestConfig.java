package com.ggs.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ggs.course.entities.Order;
import com.ggs.course.entities.User;
import com.ggs.course.repositories.OrderRepository;
import com.ggs.course.repositories.UserRepository;

/**
 * 
 * @author GivaldoGS
 * Para falar para o Spring que essa classe especifica de configuracao colocamos um annotation
 * @Configuration
 * Alem disso para falar que essa classe ela vai ser  uma configuracao especifica para o 
 * perfil de test, o annotation @Profile(test) , esse test tem se igual ao que esta no
 * application.properties = pring.profiles.active=test
 * ai o Spring consegue identificar que vai rodar essa configuracao somente quando
 * voce estiver no perfil de test
 * Essa classe vai servir por enquanto para fazer o database seeding ou seja vai popular
 * o banco de dados com alguns objetos User u1 e u2.
 * Mas para popular o banco de dados vamos ter que acessar o banco de dados, salvar os dados.
 * E a classe que vai fazer isso e' o UserRepository, entao nesse momento vou ter o caso de injecao
 * de dependencia. Porque a classe de configuracao TestConfig, ela vai ter que ter uma 
 * dependencia com o UserRepository.
 * E nas boas pratica, de que quando um servico depende de outro ela tem que ser fraca ela tem 
 * que se desacoplada.
 * E entao essa injecao de dependencia ela pode ser feita manualmente, por meio de um construtor
 * por meio de um padrao fabrica, por meio de um metodo Set, tem varias formas de fazer.
 * Mas quando estamos usando um FrameWorks, geralmente ela tem um mecanismo de injecao de 
 * dependencia implicito e automatico, e no caso do SPRING nao e diferente.
 * Para fazer um objeto depender de outro voce tem que declarar essa dependencia
 * private UserRepository userRepository;
 * e agora para que o Spring resolver essa dependencia e associar uma estancia do UseRepository 
 * no TestConfig basta colocar o ANNOTATION @Autowired
 * ====> Com que vamos fazer para que o programa fazer a inserte na tabela, quando o programa
 * for iniciado. Tem algumas forma de fazer isso, vamos fazer implementado a classe TesConfig
 * implements  TestConfig implements CommandLineRunner.
 * como ele e' uma interface temos que implementar passe o mouse em cima do vermelho que esta
 * na classe TestConfig e clicar para criar o public void run(String...
 * Tudo que voce colocar neste metodo public void run(String... args), vai ser executado
 * quando a aplicacao for iniciada.
 * 
 */

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * estamos colocando null no id, porque o id vai ser gerado pelo banco de dados agora.
		 * 
		 */
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456"); 
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

		/**
		 * Agora vamos salvar os u1 e u2 no banco de dados
		 * e para salvar vamos chamar o objeto userRepository que acessa os dados
		 * e vamos passar uma lista de objetos Arrays.asList(u1, u2) e ele salva essa lista
		 * no banco de dados
		 */
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
				
	}

}
