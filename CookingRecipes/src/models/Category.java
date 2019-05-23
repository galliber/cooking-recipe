package models;

import java.io.Serializable;
import java.util.Date;

import dao.Identifiable;

public class Category implements Identifiable<Long>, Serializable{
	private long id;
	private String name;
	private String description;
	private String tags;
	private Date created;
	private Date modified;
	
	public Category() {
		super();
	}

	public Category(String name, String description, String tags) {
		super();
		this.name = name;
		this.description = description;
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	public Long getId() {
		if(id==0)
			return null;
		return this.id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%8.8s|%15.15s|%50.50s|%20.20s|%20.20s|%20.20s"
				, id+"",name, description, tags, created.toString(), modified.toString());
	}

	
	
	
}
