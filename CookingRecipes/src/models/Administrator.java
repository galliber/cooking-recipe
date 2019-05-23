package models;

import java.io.Serializable;
import java.util.ArrayList;

import static utils.Utils.*;
import java.util.Date;
import java.util.List;

public class Administrator extends User implements Serializable{
	private List<Category> categoriesModerated;

	public Administrator() {
		super();
		this.categoriesModerated=new ArrayList<Category>();
	}

	public Administrator(String firstName, String lastName, String email, String username, String password,
			Gender gender, Role role, String picture, String description, String metadata, Status status,
			List<Comment> comments, List<Recipe> recipes, Date created, Date modified, List<Category> categoriesModerated) {
		super(firstName, lastName, email, username, password, gender, role, picture, description, metadata, status,
				comments, recipes, created, modified);
		this.categoriesModerated = categoriesModerated;
	}

	
	
	public Administrator(String firstName, String lastName, String email, String username, String password,
			Gender gender, Role role, String picture, String description, String metadata, Status status) {
		super(firstName, lastName, email, username, password, gender, role, picture, description, metadata, status, calendar.getTime(),
				calendar.getTime());
		this.setRecipes(new ArrayList<Recipe>());
		this.setComments(new ArrayList<Comment>());
		this.setCategoriesModerated(new ArrayList<Category>());
	}
	
	public Administrator(String firstName, String lastName, String email, String username, String password,
			Gender gender, String picture, String description, String metadata) {
		super(firstName, lastName, email, username, password, gender, Role.ADMIN, picture, description, metadata, Status.ACTIVE, calendar.getTime(),
				calendar.getTime());
		this.setRecipes(new ArrayList<Recipe>());
		this.setComments(new ArrayList<Comment>());
		this.setCategoriesModerated(new ArrayList<Category>());
	}

	public List<Category> getCategoriesModerated() {
		return categoriesModerated;
	}

	public void setCategoriesModerated(List<Category> categoriesModerated) {
		this.categoriesModerated = categoriesModerated;
	}

}
