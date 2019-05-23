package dao;

import dao.infrastructure.RepositoryImpl;
import models.Comment;

public class CommentRepositoryImpl extends RepositoryImpl<Long, Comment> implements CommentRepository{

	public CommentRepositoryImpl() {
		super();
	}

	public CommentRepositoryImpl(IdGenerator<Long> idGenerator) {
		super(idGenerator);
	}
	
}
