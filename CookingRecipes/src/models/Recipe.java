package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import dao.Identifiable;

public class Recipe implements Identifiable<Long>, Serializable{
	private long id;
	private Category category;
	private String title;
	private User author;
	private String shortDescription;
	private int cookingTime;
	private String usedProducts;
	private String picture;
	private String description;
	private String tags;
	private List<Comment> coments;
	private Date created;
	private Date modified;
	
	public Recipe() {
		super();
	}

	public Recipe(Category category, String title, User author, String shortDescription, int cookingTime,
			String usedProducts, String picture, String description, String tags, List<Comment> coments) {
		super();
		this.category = category;
		this.title = title;
		this.author = author;
		this.shortDescription = shortDescription;
		this.cookingTime = cookingTime;
		this.usedProducts = usedProducts;
		this.picture = picture;
		this.description = description;
		this.tags = tags;
		this.coments = coments;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public String getUsedProducts() {
		return usedProducts;
	}

	public void setUsedProducts(String usedProducts) {
		this.usedProducts = usedProducts;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	public List<Comment> getComents() {
		return coments;
	}

	public void setComents(List<Comment> coments) {
		this.coments = coments;
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
		Recipe other = (Recipe) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Recipe [category=%s, title=%s, author=%s, shortDescription=%s, created=%s]", 
				author.getUsername(), category.getName(), title, shortDescription, created);
	}
	
	
	
	
}
