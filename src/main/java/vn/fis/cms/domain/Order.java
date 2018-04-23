package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ORDERS database table.
 * 
 */
@Entity
@Table(name="ORDERS")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDERS_SEQ", sequenceName="ORDERS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="ORDERS_SEQ")
	private long id;

	private double height;

	private String islocation;

	private double lenght;

	private String note;

	private String ordercode;

	private BigDecimal price;

	private String receiveraddress;

	private String receivername;

	private String receiverphone;

	private String senderaddress;

	private String sendername;

	private String senderphone;

	private double weight;

	@Column(name="\"WITH\"")
	private double with;

	//bi-directional many-to-one association to Cod
	@OneToMany(mappedBy="order")
	private List<Cod> cods;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="SENDERDISTRICT")
	private Location location1;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="SENDERPROVINCE")
	private Location location2;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="SENDERCOMMUNE")
	private Location location3;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="RECEIVERPROVINCE")
	private Location location4;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="RECEIVERDISTRICT")
	private Location location5;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="RECEIVERCOMMUNE")
	private Location location6;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USERID")
	private User user;

	public Order() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getIslocation() {
		return this.islocation;
	}

	public void setIslocation(String islocation) {
		this.islocation = islocation;
	}

	public double getLenght() {
		return this.lenght;
	}

	public void setLenght(double lenght) {
		this.lenght = lenght;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrdercode() {
		return this.ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getReceiveraddress() {
		return this.receiveraddress;
	}

	public void setReceiveraddress(String receiveraddress) {
		this.receiveraddress = receiveraddress;
	}

	public String getReceivername() {
		return this.receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	public String getReceiverphone() {
		return this.receiverphone;
	}

	public void setReceiverphone(String receiverphone) {
		this.receiverphone = receiverphone;
	}

	public String getSenderaddress() {
		return this.senderaddress;
	}

	public void setSenderaddress(String senderaddress) {
		this.senderaddress = senderaddress;
	}

	public String getSendername() {
		return this.sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getSenderphone() {
		return this.senderphone;
	}

	public void setSenderphone(String senderphone) {
		this.senderphone = senderphone;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWith() {
		return this.with;
	}

	public void setWith(double with) {
		this.with = with;
	}

	public List<Cod> getCods() {
		return this.cods;
	}

	public void setCods(List<Cod> cods) {
		this.cods = cods;
	}

	public Cod addCod(Cod cod) {
		getCods().add(cod);
		cod.setOrder(this);

		return cod;
	}

	public Cod removeCod(Cod cod) {
		getCods().remove(cod);
		cod.setOrder(null);

		return cod;
	}

	public Location getLocation1() {
		return this.location1;
	}

	public void setLocation1(Location location1) {
		this.location1 = location1;
	}

	public Location getLocation2() {
		return this.location2;
	}

	public void setLocation2(Location location2) {
		this.location2 = location2;
	}

	public Location getLocation3() {
		return this.location3;
	}

	public void setLocation3(Location location3) {
		this.location3 = location3;
	}

	public Location getLocation4() {
		return this.location4;
	}

	public void setLocation4(Location location4) {
		this.location4 = location4;
	}

	public Location getLocation5() {
		return this.location5;
	}

	public void setLocation5(Location location5) {
		this.location5 = location5;
	}

	public Location getLocation6() {
		return this.location6;
	}

	public void setLocation6(Location location6) {
		this.location6 = location6;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}