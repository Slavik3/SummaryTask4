package ua.nure.kozlov.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Basic common parent for all entities. Provides id field and get/set methods to him.
 * 
 * @author Vyacheslav Kozlov
 *
 */
public abstract class Entity implements Serializable {
	
	private static final long serialVersionUID = 4054026766183935891L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + "]";
	}
		
	
}
