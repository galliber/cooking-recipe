package models;

import java.io.Serializable;
import java.util.Date;

import dao.Identifiable;
import utils.Constants;

public class Comment implements Identifiable<Long>, Serializable{
	private long id;
	private User author;
	private Recipe recipe;
	private String text;
	private String url;
	private Date created;
	private Date modified;
	
	public Comment() {
		super();
	}

	public Comment(User author, Recipe recipe, String text, String url) {
		super();
		this.author = author;
		this.recipe = recipe;
		this.text = text;
		this.url = url;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(Constants.COMMENT_FORMAT, author.getUsername(), text, url, created.toString());
	}
	
	
	
}
