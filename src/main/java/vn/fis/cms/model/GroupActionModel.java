package vn.fis.cms.model;

import java.util.*;

import vn.fis.cms.domain.Action;

public class GroupActionModel {
	private long id;
	private String name;
	private List<Action> actions = new ArrayList<Action>();
	
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
	public List<Action> getActions() {
		return actions;
	}
	public void setAction(List<Action> action) {
		this.actions = action;
	}	
}
