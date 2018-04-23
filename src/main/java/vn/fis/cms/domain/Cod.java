package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the COD database table.
 * 
 */
@Entity
@NamedQuery(name="Cod.findAll", query="SELECT c FROM Cod c")
public class Cod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COD_SEQ", sequenceName="COD_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="COD_SEQ")
	private long id;

	private BigDecimal amount;

	private String status;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="ORDERID")
	private Order order;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;

	public Cod() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}