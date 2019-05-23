package controllers;

import static utils.Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.CategoryRepository;
import dao.CategoryRepositoryImpl;
import exceptions.EntityDoesNotExistException;
import exceptions.EntityExistsException;
import models.Category;

public class CategoryController{
	static long[] idCount = new long[1];
	static {
		idCount[0]=1;
	}
	private CategoryRepository categoryRepo = new CategoryRepositoryImpl(()->idCount[0]++);
	
	public List<Category> getAllCategories() {
		return categoryRepo.findAll().stream().collect(Collectors.toList());
	}
	
	public Category getCategoryById(long id) {
		return categoryRepo.findById(id).orElse(null);
	}
	
	public List<Category> getCategoriesByName(String name){
		return categoryRepo.getCategoriesByName(name);
	}

	public void addCategory(Category category) {
			try {
				categoryRepo.add(category);
			} catch (EntityExistsException e) {
				System.out.println(e.getMessage());
			}
	}
	
	public Category createCategory(String name, String description, String tags)  {
		Category category=new Category(name, description, tags);
		category.setCreated(calendar.getTime());
		category.setModified(calendar.getTime());
		try {
			return categoryRepo.add(category);
		} catch (EntityExistsException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean deleteCategoryById(long id) {
		Category categoryToBeDeleted = getCategoryById(id);
		if(categoryToBeDeleted==null)
			return false;
		else {
			categoryRepo.delete(id).orElse(null);
			return true;
		}
	}
	
	public Category updateCategory(Category category) {
		try {
			return categoryRepo.update(category);
		} catch (EntityDoesNotExistException e) {
			System.out.println(e);
			return null;
		}
	}
}
