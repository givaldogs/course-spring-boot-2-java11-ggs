package com.ggs.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggs.course.entities.OrderItem;

/**
 * vai ser responsavel para fazer operacoes com a entidade user
 * Para fazer user repository reutilizando para estender o JPARepository passando o
 * o tipo da entidade que voce vai acessar e o tipo da chave dessa entidade, no caso
 * a entidade e' OrderItem e o tipo da chave e Long.
 * Os nossos repositories vao ser Interface, porque o JPARepository e' Interface tambem.
 * entao muda class OrderItemRepository para interface OrderItemRepository 
 * --> Agora vamos criar uma Classe de configuracao, vai ser uma classe auxiliar.
 * --> vai ser chamar TestConfig
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>  {

}
