package com.ggs.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Temos que colocar nessa classe algumas anotacoes (Annotation) do JPA para
 * instruir para o JPA como que ele vai converter os objetos para o modelo
 * relacional.
 * 
 * @author GivaldoGS Quando coloca o Annotation @Entity , na hora de importa ,
 *         sempre importa o javax.persistence porque sempre vamos da preferencia
 *         para especificacao. Eh sempre bom fazer sua classe depender da
 *         especificacao, e nao da implementacao por isso que nao importa
 *         diretamente o HIPERNATE
 *         anotation @Table server para dar o nome da tabela no banco de dados, no caso
 *         tb_user
 */

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Precisamos falar para o JPA qual desses campos eh a chave primaria da tabela
	 * do banco de dados no caso a chave eh o campo id, entao vamos colocar a
	 * Annotation @id antes do private Long id. como a chave eh uma chave numerica
	 * ela vai ser auto incrementavel no banco de dados. para dizer isso no JPA,
	 * colocar o Annotation @GeneratedValue
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) -> ela da certo para
	 *                          muitos banco de dados.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;

	/**
	 * o nome da lista eh orders, como a lista de pedidos do usuario eh uma colecao
	 * nos vamos instanciar => orders = new ArrayList<>()
	 * @JsonIgnore e para que nao fique em Loop, o Usuario(cliente) chama o pedido e o pedido
	 * chama o Usuario, por causa do relacionamento Cliente x Pedidos (OneToMany e ManyToOne)
	 */
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();

	/**
	 * Como estamos usando FrameWorks eh obrigado criar um construtor vazio public
	 * User() Por conveniencia (prof. Nelio Alves custuma colocar o construtor com
	 * todos os atributos da classe (name, email phone, password)
	 * 
	 */
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
