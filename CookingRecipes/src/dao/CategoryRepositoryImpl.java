package dao;

import java.util.List;
import java.util.stream.Collectors;

import dao.infrastructure.RepositoryImpl;
import models.Category;

public class CategoryRepositoryImpl extends RepositoryImpl<Long, Category> implements CategoryRepository{

	public CategoryRepositoryImpl() {
		super();
	}

	public CategoryRepositoryImpl(IdGenerator<Long> idGenerator) {
		super(idGenerator);
	}

	@Override
	public List<Category> getCategoriesByName(String name) {
		return entries.values().stream()
				.filter(c->utils.Utils.containsCaseInsensitive(c.getName(), name))
				.collect(Collectors.toList());
	} 

}
