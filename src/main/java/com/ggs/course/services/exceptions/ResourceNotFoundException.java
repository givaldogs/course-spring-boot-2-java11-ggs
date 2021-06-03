package com.ggs.course.services.exceptions;

/**
 * Vamos usar RunTimeException que e' aquela execessao e o compilador 
 * nao te obrigar a tratar
 * @author GivaldoGS
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * vamos criar um construtor para receber um Object id, ou seja
	 * o vou passar o Id do Object para localizar e nao foi encontrado
	 * Vamos chamar o construtor da superclase com o super para mandar uma
	 * mensagem
	 */
	public ResourceNotFoundException(Object id){
		super("Resource not found. id " + id);
		
	}
}
