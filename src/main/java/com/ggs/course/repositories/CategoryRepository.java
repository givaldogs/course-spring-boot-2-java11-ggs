package com.ggs.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggs.course.entities.Category;

/**
 * vai ser responsavel para fazer operacoes com a entidade category
 * Para fazer Category repository reutilizando para estender o JPARepository passando o
 * o tipo da entidade que voce vai acessar e o tipo da chave dessa entidade, no caso
 * a entidade e' Category e o tipo da chave e Long.
 * Os nossos repositories vao ser Interface, porque o JPARepository e' Interface tambem.
 * entao muda class CategoryRepository para interface CategoryRepository 
 * --> Agora vamos criar uma Classe de configuracao, vai ser uma classe auxiliar.
 * --> vai ser chamar TestConfig
 */
public interface CategoryRepository extends JpaRepository<Category, Long>  {

}
