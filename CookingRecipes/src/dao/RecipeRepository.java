package dao;

import java.util.List;

import dao.infrastructure.Repository;
import models.Category;
import models.Recipe;
import models.User;

public interface RecipeRepository extends Repository<Long, Recipe> {
	List<Recipe> getRecipesByAuthor(User user);
	List<Recipe> getRecipesByCategory(Category category);
	List<Recipe> getRecipesByTitle(String title);
}
