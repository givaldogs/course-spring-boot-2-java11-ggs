package com.ggs.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ggs.course.entities.User;
import com.ggs.course.repositories.UserRepository;
import com.ggs.course.services.exceptions.DatabaseException;
import com.ggs.course.services.exceptions.ResourceNotFoundException;

/**
 * 
 * @author GivaldoGS Vamos implementar duas operacoes basicas, uma operacao para
 *         buscar todos os usuarios, e a operacao para buscar por id. Para fazer
 *         isso o UserService vai te que ter uma dependencia com UserRepository
 *         vamos declarar a dependencia aqui no comeco: private UserRepository
 *         repository; vamos colocar o annotation @Autowired para que o SPRING
 *         faca a injecao de dependencia de uma forma transparente ao
 *         programador vamos criar um metodo para retornar todos os usuarios do
 *         banco de dados vamos criar uma List do tipo User : public List<User>
 *         o nome do metodo vai ser findall()
 * 
 *         O annotation @Component registra a classe como componente do SPRING
 *         (component registration) mas como a classe UserService nos vamos usar
 *         o annotation @Service soh para ficar mais semanticamente especifico.
 *         Tambem temos o annotation @Repository, que poderia colocar no
 *         UserRepository tambem, mas soh que nao precisa porque na interface
 *         UserRepository ja esta erdando (extends JpaRepository) do
 *         JpaRepository. O JpaRepositoryja ja esta registrado como componente
 *         do SPRING, portanto e opcional colocar o annotation @Repository
 * 
 */

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * 
	 * criamos entao uma operacao chamada findall() que ela repassa a chamada para o
	 * repository.findall(), e ai no UserResource que eh o controlador REST vamos
	 * atualizar a implementacao do findall()
	 */
	public List<User> findall() {
		return repository.findAll();
	}

	public User findById(Long id) {
		/**
		 * essa operacao repository.findById(id), vai retornar um objeto Optional do
		 * tipo <User> o nome da variavel e obj (obj = repository.findById(id)) a
		 * operacao get o Optional vai retornar o objeto que estiver dentro do
		 * Optional<User> obj
		 * 
		 * Optional existe desse o java 8
		 */

		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 
	 * getOne(id), vai instaciar o Usuario so que ele nao vai no banco de dados
	 * ainda ele vai so' deixar o objeto monitorado pelo JPA para trabalhar com ele
	 * e em seguida eu possa trabalhar com ele no banco de dados. e' mais eficiente
	 * dessa forma
	 * 
	 */
	public User update(Long id, User obj) {
		try {
			User entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
