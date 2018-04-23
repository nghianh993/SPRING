package vn.fis.cms.model;

import java.util.List;

public class ActionModel {
	private Long id;
	private String link;
	private boolean islock;
	private long parentId;
	private List<Long> methods;
	private List<Long> permission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isIslock() {
		return islock;
	}

	public void setIslock(boolean islock) {
		this.islock = islock;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public List<Long> getMethods() {
		return methods;
	}

	public void setMethods(List<Long> methods) {
		this.methods = methods;
	}
	
	public List<Long> getPermission() {
		return permission;
	}

	public void setPermission(List<Long> permission) {
		this.permission = permission;
	}

}
