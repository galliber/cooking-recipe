package controllers;

import java.util.List;
import java.util.stream.Collectors;

import dao.RecipeRepository;
import dao.RecipeRepositoryImpl;
import exceptions.EntityDoesNotExistException;
import exceptions.EntityExistsException;
import models.Category;
import models.Recipe;
import models.User;

public class RecipeController {
	static long[] idCount = new long[1];
	static {
		idCount[0]=1;
	}
	private RecipeRepository recipeRepo = new RecipeRepositoryImpl(()->idCount[0]++);
	
	public List<Recipe> getAllRecipes(){
		return recipeRepo.findAll().stream().collect(Collectors.toList());
	}
	
	public Recipe getRecipeById(long id) {
		return recipeRepo.findById(id).orElse(null);
	}
	
	public List<Recipe> getRecipesByAuthor(User user){
		return recipeRepo.getRecipesByAuthor(user);
	}
	
	public List<Recipe> getRecipesByCategory(Category category){
		return recipeRepo.getRecipesByCategory(category);
	}
	
	public List<Recipe> getRecipesByTitle(String title){
		return recipeRepo.getRecipesByTitle(title);
	}
	
	public Recipe addRecipe(Recipe recipe) {
		try {
			return recipeRepo.add(recipe);
		} catch (EntityExistsException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Recipe updateRecipe(Recipe recipe)  {
		try {
			return recipeRepo.update(recipe);
		}catch (EntityDoesNotExistException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public boolean deleteRecipeById(long id) {
		Recipe recipeToBeDeleted = getRecipeById(id);
		if(recipeToBeDeleted==null)
			return false;
		else {
			recipeRepo.delete(id);
			return true;
		}
		
	}
}
