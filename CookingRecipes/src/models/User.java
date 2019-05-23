package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.Identifiable;
import utils.Constants;

public class User implements Identifiable<Long>, Serializable {
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private Gender gender;
	private Role role;
	private String picture;
	private String description;
	private String metadata;
	private Status status;
	private List<Comment> comments = new ArrayList<Comment>();
	private List<Recipe> recipes = new ArrayList<Recipe>();
	private Date created;
	private Date modified;
	
	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, String username, String password,
			Gender gender, Role role, String picture, String description, String metadata, Status status, Date created,
			Date modified) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.picture = picture;
		this.description = description;
		this.metadata = metadata;
		this.status = status;
		this.created = created;
		this.modified = modified;
	}

	public User(String firstName, String lastName, String email, String username, String password,
			Gender gender, Role role, String picture, String description, String metadata, Status status,
			List<Comment> comments, List<Recipe> recipes, Date created, Date modified) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.picture = picture;
		this.description = description;
		this.metadata = metadata;
		this.status = status;
		this.comments = comments;
		this.recipes = recipes;
		this.created = created;
		this.modified = modified;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				Constants.USER_STRING_FORMAT,
				id+"", firstName, lastName, email, username, gender.toString(), role.toString(), description, created.toString(), modified.toString());
	}


	
}
