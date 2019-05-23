package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.CommentRepository;
import dao.CommentRepositoryImpl;
import exceptions.EntityDoesNotExistException;
import exceptions.EntityExistsException;
import models.Comment;
import models.Recipe;
import models.User;

public class CommentController {
	static long[] idCount = new long[1];
	static {
		idCount[0]=1;
	}
	private CommentRepository commentRepo = new CommentRepositoryImpl(()-> idCount[0]++);
	
	public void addComment(Comment comment) {
		try {
			commentRepo.add(comment);
		} catch (EntityExistsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Comment> getAllComment(){
		return commentRepo.findAll().stream().collect(Collectors.toList());
	}
	
	public List<Comment> getCommentsByAuthor(User author){
		return commentRepo.findAll().stream()
				.filter(com->com.getAuthor().equals(author))
				.collect(Collectors.toList());
	}
	
	public Comment getCommentById(long id){
		return commentRepo.findById(id).orElse(null);
	}
	
	public List<Comment> getCommentsByRecipe(Recipe recipe){
		return commentRepo.findAll().stream()
				.filter(com->com.getRecipe().equals(recipe))
				.collect(Collectors.toList());
	}
	
	public boolean deleteCommentById(long id) {
		Comment commentToBeDeleted = getCommentById(id);
		if(commentToBeDeleted==null)
			return false;
		else {
			commentRepo.delete(id);
			return true;
		}
		
	}
	
	public Comment updateComment(Comment comment) {
		try {
			return commentRepo.update(comment);
		} catch(EntityDoesNotExistException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
