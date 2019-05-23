package dao;

import java.util.List;

import dao.infrastructure.Repository;
import models.Category;

public interface CategoryRepository extends Repository<Long, Category>{
	List<Category> getCategoriesByName(String name);
}
