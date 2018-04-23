package vn.fis.cms.model;

import java.util.List;

public class PermissionModel {
	private Long id;
    private String code;
    private String description;
    private boolean islock;
    private Long parentId;
    private String link;
    private List<Long> methods;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isIslock() {
		return islock;
	}
	public void setIslock(boolean islock) {
		this.islock = islock;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<Long> getMethods() {
		return methods;
	}
	public void setMethods(List<Long> methods) {
		this.methods = methods;
	}
}
