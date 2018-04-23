package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STATUS database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STATUS_SEQ", sequenceName="STATUS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="STATUS_SEQ")
	private long id;

	private String name;

	public Status() {
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

}