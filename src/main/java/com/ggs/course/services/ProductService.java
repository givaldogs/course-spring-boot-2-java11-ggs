package com.ggs.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggs.course.entities.Product;
import com.ggs.course.repositories.ProductRepository;

/**
 * 
 * @author GivaldoGS
 * Vamos implementar duas operacoes basicas, uma operacao para buscar todos os usuarios, e a
 * operacao para buscar por id.
 * Para fazer isso o ProductService vai te que ter uma dependencia com ProductRepository
 * vamos declarar a dependencia aqui no comeco: private ProductRepository repository;
 * vamos colocar o annotation @Autowired para que o SPRING faca a injecao de dependencia de uma
 * forma transparente ao programador
 * vamos criar um metodo para retornar todos os usuarios do banco de dados
 * vamos criar uma List do tipo Product : public List<Product>
 * o nome do metodo vai ser findall()
 * 
 * O annotation @Component registra a classe como componente do SPRING (component registration)
 * mas como a classe ProductService nos vamos usar o annotation @Service soh para ficar mais
 * semanticamente especifico.
 * Tambem temos o annotation @Repository, que poderia colocar no ProductRepository tambem, mas 
 * soh que nao precisa porque na interface ProductRepository ja esta erdando (extends JpaRepository) 
 * do JpaRepository.
 * O JpaRepositoryja ja esta registrado como componente do SPRING, portanto e opcional colocar
 * o annotation @Repository
 * 
 */

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	/**
	 * 
	 * criamos entao uma operacao chamada findall() que ela repassa a chamada
	 * para o repository.findall(), e ai no ProductResource que eh o controlador REST
	 * vamos atualizar a implementacao do findall()
	 */
	public List<Product> findall() {
		return repository.findAll();
	}

	public Product findById(Long id) {
		/**
		 * essa operacao repository.findById(id), vai retornar um objeto Optional
		 * do tipo <Product>
		 * o nome da variavel e obj (obj = repository.findById(id))
		 * a operacao get o Optional vai retornar o objeto que estiver dentro do 
		 * Optional<Product> obj
		 * 
		 * Optional existe desse o java 8
		 */
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}

}
