package vn.fis.cms.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the METHOD database table.
 * 
 */
@Entity
@NamedQuery(name="Method.findAll", query="SELECT m FROM Method m")
public class Method implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "METHOD_SEQ", sequenceName = "METHOD_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "METHOD_SEQ")
	private long id;
	
	private String name;

	public Method() {
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