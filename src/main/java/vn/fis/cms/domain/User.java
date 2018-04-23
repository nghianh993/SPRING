package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_SEQ", sequenceName="USERS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="USERS_SEQ")
	private long id;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date datecreate;

	private String email;

	private String fullname;

	private String iplogin;

	private boolean islock;

	private String lockresion;

	private String password;

	private String phone;

	//bi-directional many-to-one association to Balance
	@OneToMany(mappedBy="user")
	private List<Balance> balances;

	//bi-directional many-to-one association to Cod
	@OneToMany(mappedBy="user")
	private List<Cod> cods;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user")
	private List<Order> orders;

	//bi-directional many-to-one association to UserRoleMapping
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "USER_ROLE_MAPPING", 
      joinColumns =  @JoinColumn(name = "USERID", referencedColumnName = "ID"),
      inverseJoinColumns =  @JoinColumn(name = "ROLEID", referencedColumnName = "ID"))
	private Collection<Role> roles = new HashSet<Role>();

	public User() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getIplogin() {
		return this.iplogin;
	}

	public void setIplogin(String iplogin) {
		this.iplogin = iplogin;
	}

	public boolean getIslock() {
		return this.islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	public String getLockresion() {
		return this.lockresion;
	}

	public void setLockresion(String lockresion) {
		this.lockresion = lockresion;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Balance> getBalances() {
		return this.balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

	public Balance addBalance(Balance balance) {
		getBalances().add(balance);
		balance.setUser(this);

		return balance;
	}

	public Balance removeBalance(Balance balance) {
		getBalances().remove(balance);
		balance.setUser(null);

		return balance;
	}

	public List<Cod> getCods() {
		return this.cods;
	}

	public void setCods(List<Cod> cods) {
		this.cods = cods;
	}

	public Cod addCod(Cod cod) {
		getCods().add(cod);
		cod.setUser(this);

		return cod;
	}

	public Cod removeCod(Cod cod) {
		getCods().remove(cod);
		cod.setUser(null);

		return cod;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}