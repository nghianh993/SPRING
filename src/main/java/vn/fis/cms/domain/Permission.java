package vn.fis.cms.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the PERMISSION database table.
 * 
 */
@Entity
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERMISSION_SEQ", sequenceName="PERMISSION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="PERMISSION_SEQ")
	private long id;

	private String code;

	private String description;

	private boolean islock;
	
	private String link;

	@ManyToOne
	@JoinColumn(name="GROUPID")
	@OrderBy("code ASC")
	private GroupPermission groupPermission;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "PERMISION_METHOD", 
 		joinColumns = @JoinColumn(name = "PERMISSIONID", referencedColumnName = "ID"), 
 		inverseJoinColumns = @JoinColumn(name = "METHODID", referencedColumnName = "ID"))	
 	private Set<Method> method = new HashSet<Method>();

	public Permission() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIslock() {
		return this.islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	public GroupPermission getGroupPermission() {
		return this.groupPermission;
	}

	public void setGroupPermission(GroupPermission groupPermission) {
		this.groupPermission = groupPermission;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Set<Method> getMethod() {
		return method;
	}

	public void setMethod(Set<Method> method) {
		this.method = method;
	}

}