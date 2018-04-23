package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GROUP_ACTION database table.
 * 
 */
@Entity
@Table(name="GROUP_ACTION")
@NamedQuery(name="GroupAction.findAll", query="SELECT g FROM GroupAction g")
public class GroupAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GROUP_ACTION_SEQ", sequenceName="GROUP_ACTION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="GROUP_ACTION_SEQ")
	private long id;

	private String name;
	
	private long parentid;

	@OneToMany(mappedBy="groupAction", fetch = FetchType.EAGER)
	private List<Action> actions;

	public GroupAction() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Action> getActions() {
		return this.actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public Action addAction(Action action) {
		getActions().add(action);
		action.setGroupAction(this);

		return action;
	}

	public Action removeAction(Action action) {
		getActions().remove(action);
		action.setGroupAction(null);

		return action;
	}
	
	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

}