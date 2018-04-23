package vn.fis.cms.model;

import java.util.Date;

public class AccountModel {
	private long id;

	private String address;

	private Date datecreate;

	private String email;

	private String fullname;

	private String iplogin;

	private boolean islock;

	private String lockresion;

	private String password;

	private String phone;

	public AccountModel(long id, String address, 
			Date datecreate, String email, 
			String fullname, String iplogin, 
			boolean islock,String lockresion, String password, String phone) {
		this.id = id;
		this.address = address;
		this.datecreate = datecreate;
		this.email = email;
		this.fullname = fullname;
		this.iplogin = iplogin;
		this.islock = islock;
		this.lockresion = lockresion;
		this.password = password;
		this.phone = phone;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDatecreate() {
		return datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getIplogin() {
		return iplogin;
	}

	public void setIplogin(String iplogin) {
		this.iplogin = iplogin;
	}

	public boolean isIslock() {
		return islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	public String getLockresion() {
		return lockresion;
	}

	public void setLockresion(String lockresion) {
		this.lockresion = lockresion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
