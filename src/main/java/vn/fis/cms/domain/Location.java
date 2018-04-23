package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the LOCATION database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	@Column(name="\"TYPE\"")
	private String type;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="location1")
	private List<Order> orders1;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="location2")
	private List<Order> orders2;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="location3")
	private List<Order> orders3;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="location4")
	private List<Order> orders4;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="location5")
	private List<Order> orders5;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="location6")
	private List<Order> orders6;

	public Location() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Order> getOrders1() {
		return this.orders1;
	}

	public void setOrders1(List<Order> orders1) {
		this.orders1 = orders1;
	}

	public Order addOrders1(Order orders1) {
		getOrders1().add(orders1);
		orders1.setLocation1(this);

		return orders1;
	}

	public Order removeOrders1(Order orders1) {
		getOrders1().remove(orders1);
		orders1.setLocation1(null);

		return orders1;
	}

	public List<Order> getOrders2() {
		return this.orders2;
	}

	public void setOrders2(List<Order> orders2) {
		this.orders2 = orders2;
	}

	public Order addOrders2(Order orders2) {
		getOrders2().add(orders2);
		orders2.setLocation2(this);

		return orders2;
	}

	public Order removeOrders2(Order orders2) {
		getOrders2().remove(orders2);
		orders2.setLocation2(null);

		return orders2;
	}

	public List<Order> getOrders3() {
		return this.orders3;
	}

	public void setOrders3(List<Order> orders3) {
		this.orders3 = orders3;
	}

	public Order addOrders3(Order orders3) {
		getOrders3().add(orders3);
		orders3.setLocation3(this);

		return orders3;
	}

	public Order removeOrders3(Order orders3) {
		getOrders3().remove(orders3);
		orders3.setLocation3(null);

		return orders3;
	}

	public List<Order> getOrders4() {
		return this.orders4;
	}

	public void setOrders4(List<Order> orders4) {
		this.orders4 = orders4;
	}

	public Order addOrders4(Order orders4) {
		getOrders4().add(orders4);
		orders4.setLocation4(this);

		return orders4;
	}

	public Order removeOrders4(Order orders4) {
		getOrders4().remove(orders4);
		orders4.setLocation4(null);

		return orders4;
	}

	public List<Order> getOrders5() {
		return this.orders5;
	}

	public void setOrders5(List<Order> orders5) {
		this.orders5 = orders5;
	}

	public Order addOrders5(Order orders5) {
		getOrders5().add(orders5);
		orders5.setLocation5(this);

		return orders5;
	}

	public Order removeOrders5(Order orders5) {
		getOrders5().remove(orders5);
		orders5.setLocation5(null);

		return orders5;
	}

	public List<Order> getOrders6() {
		return this.orders6;
	}

	public void setOrders6(List<Order> orders6) {
		this.orders6 = orders6;
	}

	public Order addOrders6(Order orders6) {
		getOrders6().add(orders6);
		orders6.setLocation6(this);

		return orders6;
	}

	public Order removeOrders6(Order orders6) {
		getOrders6().remove(orders6);
		orders6.setLocation6(null);

		return orders6;
	}

}