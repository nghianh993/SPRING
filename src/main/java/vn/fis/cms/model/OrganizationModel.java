package vn.fis.cms.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationModel {
	private Long id;
	private String description;
	private Long lever;
	private Long parentId;
	
	private List<OrganizationModel> children = new ArrayList<OrganizationModel>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getLever() {
		return lever;
	}

	public void setLever(Long lever) {
		this.lever = lever;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<OrganizationModel> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationModel> children) {
		this.children = children;
	}
}
