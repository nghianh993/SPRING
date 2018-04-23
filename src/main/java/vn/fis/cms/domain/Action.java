package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"ACTION\"")
@NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a")
public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ACTION_SEQ", sequenceName = "ACTION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ACTION_SEQ")
	private long id;

	private boolean islock;

	@Column(name = "LINK")
	private String link;

	@ManyToOne
	@JoinColumn(name = "GROUPID")
	private GroupAction groupAction;

	@ManyToMany(fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "PERMISTION_ACTION_MAPPING", 
	joinColumns = @JoinColumn(name = "ACTIONID", referencedColumnName = "ID"), 
	inverseJoinColumns = @JoinColumn(name = "PERMISSIONID", referencedColumnName = "ID"))
	private Set<Permission> permissions = new HashSet<Permission>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "ACTION_METHOD", 
	joinColumns = @JoinColumn(name = "ACTIONID", referencedColumnName = "ID"), 
	inverseJoinColumns = @JoinColumn(name = "METHODID", referencedColumnName = "ID"))
	private Set<Method> methods = new HashSet<Method>();

	public Action() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getIslock() {
		return this.islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public GroupAction getGroupAction() {
		return this.groupAction;
	}

	public void setGroupAction(GroupAction groupAction) {
		this.groupAction = groupAction;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Method> getMethods() {
		return methods;
	}

	public void setMethods(Set<Method> methods) {
		this.methods = methods;
	}
}