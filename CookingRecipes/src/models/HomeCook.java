package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeCook extends User implements Serializable {
	private List<Recipe> favouriteRecipes;
	private List<User> favouriteCooks;
	
	public HomeCook() {
		super();
		favouriteRecipes=new ArrayList<Recipe>();
		favouriteCooks = new ArrayList<User>();
	}
	
	public HomeCook(String firstName, String lastName, String email, String username, String password, Gender gender,
			Role role, String picture, String description, String metadata, Status status, List<Comment> comments,
			List<Recipe> recipes, Date created, Date modified, List<Recipe> favouriteRecipes, List<User> favouriteCooks) {
		super(firstName, lastName, email, username, password, gender, role, picture, description, metadata, status, comments,recipes, created, modified);
		this.favouriteRecipes=favouriteRecipes;
		this.favouriteCooks=favouriteCooks;
		}
	
	public HomeCook(String firstName, String lastName, String email, String username, String password, Gender gender,
			Role role, String picture, String description, String metadata, Status status, List<Comment> comments,
			List<Recipe> recipes, Date created, Date modified) {
		super(firstName, lastName, email, username, password, gender, role, picture, description, metadata, status, comments,recipes, created, modified);
		this.favouriteCooks=new ArrayList<User>();
		this.favouriteRecipes=new ArrayList<Recipe>();
	}

	public List<Recipe> getFavouriteRecipes() {
		return favouriteRecipes;
	}

	public void setFavouriteRecipes(List<Recipe> favouriteRecipes) {
		this.favouriteRecipes = favouriteRecipes;
	}

	public List<User> getFavouriteCooks() {
		return favouriteCooks;
	}

	public void setFavouriteCooks(List<User> favouriteCooks) {
		this.favouriteCooks = favouriteCooks;
	}
	
	
}
