package dao;

import java.util.List;
import java.util.stream.Collectors;

import dao.infrastructure.RepositoryImpl;
import models.Category;
import models.Recipe;
import models.User;

public class RecipeRepositoryImpl extends RepositoryImpl<Long, Recipe> implements RecipeRepository {

	public RecipeRepositoryImpl() {
		super();
	}

	public RecipeRepositoryImpl(IdGenerator<Long> idGenerator) {
		super(idGenerator);
	}

	@Override
	public List<Recipe> getRecipesByAuthor(User user) {
		return entries.values().stream()
				.filter(r->r.getAuthor().getId()==user.getId())
				.collect(Collectors.toList());
	}

	@Override
	public List<Recipe> getRecipesByCategory(Category category) {
		return entries.values().stream()
				.filter(r->r.getCategory().getId()==category.getId())
				.collect(Collectors.toList());
	}

	@Override
	public List<Recipe> getRecipesByTitle(String title) {
		return entries.values().stream()
				.filter(r->r.getTitle().equals(title))
				.collect(Collectors.toList());
	}

	
	
}
