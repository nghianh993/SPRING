package vn.fis.cms.model;

import java.util.*;

import vn.fis.cms.domain.Permission;

public class GroupPermissionModel {
	private long id;
	private String name;
	private Long parentId;
	private List<Permission> permissions = new ArrayList<Permission>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
