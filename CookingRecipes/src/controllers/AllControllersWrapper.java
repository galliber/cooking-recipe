package controllers;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.Administrator;
import models.Category;
import models.Comment;
import models.Gender;
import models.Recipe;
import models.User;

public class AllControllersWrapper implements Serializable {
	public static User[] PRE_DEF_USERS = {
				new Administrator("Denis", "Pavlov", "galiber12321@gmail.com", "galiber", "somepass123.",
					Gender.MALE, "", "The master of all things!", "Master!"),
				new Administrator("Sashko", "Simeonov", "sande@abv.bg", "troqta", "sandeDaboss1.",
					Gender.OTHER, "", "The hand of the king!", "Nice hand mate! :)")
				};
	
	public static Recipe[] PRE_DEF_RECIPES = {};
	
	public static Comment[] PRE_DEF_COMMENTS = {};
	
	public static Category[] PRE_DEF_CATEGORIES = {
			new Category("Soups", "Here we post soup recipes!", ""),
			new Category("Salads", "Thee shall only post salad recipes here!", "")
	};	
	
	private static CategoryController catCnt = new CategoryController();
	private static CommentController comCnt = new CommentController();
	private static RecipeController rcCnt = new RecipeController();
	private static UserController usCnt = new UserController();
	
	
	private Map<Long, User> allUsers = new HashMap<Long, User>();
	private Map<Long, Recipe> allRecipes = new HashMap<Long, Recipe>();
	private Map<Long, Comment> allComments = new HashMap<Long, Comment>();
	private Map<Long, Category> allCategories = new HashMap<Long, Category>();
	private long userCount;
	private long recipeCount;
	private long commentCount;
	private long categoryCount;
	
	public AllControllersWrapper() {
		super();
	}
	
	public AllControllersWrapper(long userCount, long recipeCount, long commentCount, long categoryCount) {
		super();
		this.userCount = userCount;
		this.recipeCount = recipeCount;
		this.commentCount = commentCount;
		this.categoryCount = categoryCount;
	}
	
	public AllControllersWrapper(Map<Long, User> allUsers, Map<Long, Recipe> allRecipes, Map<Long, Comment> allComments,
			Map<Long, Category> allCategories, long userCount, long recipeCount, long commentCount,
			long categoryCount) {
		super();
		this.allUsers = allUsers;
		this.allRecipes = allRecipes;
		this.allComments = allComments;
		this.allCategories = allCategories;
		this.userCount = userCount;
		this.recipeCount = recipeCount;
		this.commentCount = commentCount;
		this.categoryCount = categoryCount;
	}

	public void initialize(Collection<? extends User> users, 
			Collection<? extends Recipe> recipes, 
			Collection<? extends Comment> comments,
			Collection<? extends Category> categories) {
		this.allUsers.clear();
		for(User u:users) {
			usCnt.addUser(u);
			allUsers.put(u.getId(), u);
		}
		
		this.allRecipes.clear();
		for(Recipe r:recipes) {
			rcCnt.addRecipe(r);
			allRecipes.put(r.getId(), r);
		}
		
		this.allComments.clear();
		for(Comment c: comments) {
			comCnt.addComment(c);
			allComments.put(c.getId(), c);
		}
		
		this.allCategories.clear();
		for(Category c: categories) {
			catCnt.addCategory(c);
			allCategories.put(c.getId(), c);
		}
	}
	
	public void reloadValuesForAllControllers() {
		this.allUsers.clear();
		for(User u:usCnt.getAllUsers()) {
			allUsers.put(u.getId(), u);
		}
		
		this.allRecipes.clear();
		for(Recipe r:rcCnt.getAllRecipes()) {
			allRecipes.put(r.getId(), r);
		}
		
		this.allComments.clear();
		for(Comment c: comCnt.getAllComment()) {
			allComments.put(c.getId(), c);
		}
		
		this.allCategories.clear();
		for(Category c: catCnt.getAllCategories()) {
			allCategories.put(c.getId(), c);
		}
	}
	
	public UserController getUserController() {
		return usCnt;
	}
	public RecipeController getRecipeController() {
		return rcCnt;
	}
	public CommentController getCommentController() {
		return comCnt;
	}
	public CategoryController getCategoryController() {
		return catCnt;
	}

	public long getUserCount() {
		return userCount;
	}

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	public long getRecipeCount() {
		return recipeCount;
	}

	public void setRecipeCount(long recipeCount) {
		this.recipeCount = recipeCount;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public long getCategoryCount() {
		return categoryCount;
	}

	public void setCategoryCount(long categoryCount) {
		this.categoryCount = categoryCount;
	}
	
	public void loadCounters() {
		CategoryController.idCount[0]=this.categoryCount;
		CommentController.idCount[0]=this.commentCount;
		RecipeController.idCount[0]=this.recipeCount;
		UserController.idCount[0]=this.userCount;
	}

	public void reloadAllRepositories() {
		for(User u:usCnt.getAllUsers()) {
			usCnt.deleteUserById(u.getId());
		}
		for(User u:allUsers.values()) {
			usCnt.addUser(u);
		}
		for(Recipe r:rcCnt.getAllRecipes()) {
			rcCnt.deleteRecipeById(r.getId());
		}
		for(Recipe r:allRecipes.values()) {
			rcCnt.addRecipe(r);
		}
		for(Comment c:comCnt.getAllComment()) {
			comCnt.deleteCommentById(c.getId());
		}
		for(Comment c:allComments.values()) {
			comCnt.addComment(c);
		}
		for(Category c:catCnt.getAllCategories()) {
			usCnt.deleteUserById(c.getId());
		}
		for(Category c:allCategories.values()) {
			catCnt.addCategory(c);
		}
		
	}
		
}

//public User adminUpdateUser(User user) throws EntityDoesNotExistException {
//	Role r=user.getRole();
//	do {
//		System.out.print("Enter new first name or leave blank: ");
//		String fName = sc.nextLine();
//		if (fName.length() == 0)
//			break;
//		else {
//			if (isStringCorrect(fName, "^[a-zA-Z]{2,15}")) {
//				user.setFirstName(fName);
//				break;
//			} else
//				System.out.println("Invalid first name!");
//		}
//	} while (true);
//
//	do {
//		System.out.print("Enter new last name or leave blank: ");
//		String lName = sc.nextLine();
//		if (lName.length() == 0)
//			break;
//		else {
//			if (isStringCorrect(lName, "^[a-zA-Z]{2,15}")) {
//				user.setLastName(lName);
//				break;
//			} else
//				System.out.println("Invalid last name!");	
//		}
//	} while (true);
//
//	do {
//		System.out.print("Enter new gender or leave blank: " + Arrays.deepToString(Gender.values()));
//		String gender = sc.nextLine();
//		if (gender.length() == 0) {
//			break;
//		} else {
//			try {
//				user.setGender(Gender.valueOf(Gender.class, gender.toUpperCase()));
//				break;
//			} catch (IllegalArgumentException e) {
//				System.out.println("Invalid gender!");
//			}
//		}
//	} while (true);
//
//	do {
//		System.out.print("Enter new role or leave blank\n"
//				+ "(Note: If you change role, you'll loose user specific data!!!): "
//				+ Arrays.deepToString(Role.values()));
//		String role = sc.nextLine();
//		if (role.length() == 0) {
//			break;
//		} else {
//			try {
//				user.setRole(Role.valueOf(Role.class, role.toUpperCase()));
//				break;
//			} catch (IllegalArgumentException e) {
//				System.out.println("Invalid gender!");
//			}
//		}
//	} while (true);
//
//	do {
//		System.out.print("Enter new picture URL or leave blank: ");
//		String picture = sc.nextLine();
//		if (picture.length() == 0) {
//			break;
//		} else {
//			if (isStringCorrect(picture, Constants.PICTURE_URL_REGEX)) {
//				user.setPicture(picture);
//				break;
//			} else
//				System.out.println("Invalid picture URL!");
//		}
//	} while (true);
//	
//	do {
//		System.out.print("Enter new description(between 20 and 2500 smbols) or leave blank: ");
//		String description = sc.nextLine();
//		if (description.length() == 0) {
//			break;
//		} else {
//			if (isStringCorrect(description, "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\"]{20,2500}")) {
//				user.setDescription(description);
//				break;
//			} else
//				System.out.println("Invalid description!");
//		}
//	} while (true);
//	
//	do {
//		System.out.print("Enter new metadata or leave blank: ");
//		String metadata = sc.nextLine();
//		if (metadata.length() == 0) {
//			break;
//		} else {
//			if (isStringCorrect(metadata, "[a-zA-Z\\ \\(\\)\\!\\?\\d\\.\\,\\'\"]{1,512}")) {
//				user.setMetadata(metadata);
//				break;
//			} else
//				System.out.println("Invalid metadata!");
//		}
//	} while (true);
//	
//	do {
//		System.out.print("Enter new Status or leave blank: " + Arrays.deepToString(Status.values()));
//		String status = sc.nextLine();
//		if (status.length() == 0) {
//			break;
//		} else {
//			try {
//				user.setStatus(Status.valueOf(Status.class, status.toUpperCase()));
//				break;
//			} catch (IllegalArgumentException e) {
//				System.out.println("Invalid status!");
//			}
//		}
//	} while (true);
//	if(!user.getRole().equals(r)) {
//		if(user.getRole().equals(Role.HOME_COOK)) {
//			user=new HomeCook(user.getId(), user.getFirstName(),
//					user.getLastName(),user.getEmail(), user.getUsername(), user.getPassword(),
//					user.getGender(), Role.HOME_COOK, user.getPicture(), user.getDescription(),
//					user.getMetadata(),	user.getStatus(), user.getComments(), user.getRecipes(),
//					user.getCreated(), calendar.getTime());
//			userRepo.update(user);
//		}
//			
//		else {
//			user=new Administrator(user.getId(), user.getFirstName(), user.getLastName(),
//					user.getEmail(), user.getUsername(), user.getPassword(), user.getGender(), 
//					Role.ADMIN, user.getPicture(), user.getDescription(), user.getMetadata(), 
//					user.getStatus(), user.getCreated(), calendar.getTime());
//			userRepo.update(user);
//		}
//	}
//	user.setModified(calendar.getTime());
//	return user;
//
//}
//
//public User homeCookUpdateUser(User user) {
//	do {
//		System.out.print("Enter new first name or leave blank: ");
//		String fName = sc.nextLine();
//		if (fName.length() == 0)
//			break;
//		else {
//			if (isStringCorrect(fName, "^[a-zA-Z]{2,15}")) {
//				user.setFirstName(fName);
//				break;
//			} else
//				System.out.println("Invalid first name!");
//		}
//	} while (true);
//
//	do {
//		System.out.print("Enter new last name or leave blank: ");
//		String lName = sc.nextLine();
//		if (lName.length() == 0)
//			break;
//		else {
//			if (isStringCorrect(lName, "^[a-zA-Z]{2,15}")) {
//				user.setLastName(lName);
//				break;
//			} else
//				System.out.println("Invalid last name!");
//		}
//	} while (true);
//
//	do {
//		System.out.print("Enter new Password or leave blank: ");
//		String password = sc.nextLine();
//		if (password.length() == 0)
//			break;
//		else {
//			if (isStringCorrect(password, Constants.PASSWOR_REGEX)) {
//				user.setPassword(password);
//				break;
//			} else
//				System.out.println("Invalid Password!");
//		}
//	} while (true);
//
//	do {
//		System.out.print("Enter new gender or leave blank: " + Arrays.deepToString(Gender.values()));
//		String gender = sc.nextLine();
//		if (gender.length() == 0) {
//			break;
//		} else {
//			try {
//				user.setGender(Gender.valueOf(Gender.class, gender.toUpperCase()));
//				break;
//			} catch (IllegalArgumentException e) {
//				System.out.println("Invalid gender!");
//			}
//		}
//	} while (true);
//
//	do {
//		System.out.print("Enter new picture URL or leave blank: ");
//		String picture = sc.nextLine();
//		if (picture.length() == 0) {
//			break;
//		} else {
//			if (isStringCorrect(picture, Constants.PICTURE_URL_REGEX)) {
//				user.setPicture(picture);
//				break;
//			} else
//				System.out.println("Invalid picture URL!");
//		}
//	} while (true);
//	
//	do {
//		System.out.print("Enter new description(between 20 and 2500 smbols) or leave blank: ");
//		String description = sc.nextLine();
//		if (description.length() == 0) {
//			break;
//		} else {
//			if (isStringCorrect(description, "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\"]{20,2500}")) {
//				user.setDescription(description);
//				break;
//			} else
//				System.out.println("Invalid description!");
//		}
//	} while (true);
//	
//	user.setModified(calendar.getTime());
//	return user;
//
//}
//
//public Administrator createAdministrator(String firstName, String lastName, String email, String username,
//		String password, Gender gender, String picture, String description, String metadata) throws EntityExistsException {
//	Administrator admin = new Administrator(idCount++, firstName, lastName, email, username, password, gender,
//			Role.ADMIN, picture, description, metadata, Status.ACTIVE, calendar.getTime(), calendar.getTime());
//	if(isUserValid(admin)) {
//		userRepo.add(admin);
//		return admin;
//	}
//	else
//		return null;
//}
//
//public HomeCook createHomeCookWithInput() {
//	User homeCook = new HomeCook();
//	homeCook.setId(idCount++);
//	do {
//		System.out.print("Enter first name: ");
//		String fName = sc.nextLine();
//		if (isStringCorrect(fName, "^[a-zA-Z]{2,15}")) {
//			homeCook.setFirstName(fName);
//			break;
//		} else
//			System.out.println("Invalid first name!");
//	} while (homeCook.getFirstName() == null);
//
//	do {
//		System.out.print("Enter last name: ");
//		String lName = sc.nextLine();
//		if (isStringCorrect(lName, "^[a-zA-Z]{2,15}")) {
//			homeCook.setLastName(lName);
//		} else
//			System.out.println("Invalid last name!");
//	} while (homeCook.getLastName() == null);
//
//	do {
//		System.out.print("Enter e-mail address: ");
//		String email = sc.nextLine();
//		if (isStringCorrect(email, EMAIL_REGEX)) {
//			if (userRepo.findAll().stream().anyMatch(u -> u.getEmail().equals(email)))
//				System.out.println("Email exists! Try again");
//			else
//				homeCook.setEmail(email);
//		} else
//			System.out.println("Invalid e-mail address!");
//	} while (homeCook.getEmail() == null);
//
//	do {
//		System.out.print("Enter UserName: ");
//		String username = sc.nextLine();
//		if (isStringCorrect(username, "^[a-zA-Z]{2,15}")) {
//			if (userRepo.findAll().stream().anyMatch(u -> u.getUsername().equals(username)))
//				System.out.println("Email exists! Try again");
//			else
//				homeCook.setUsername(username);
//		} else
//			System.out.println("Invalid UserName!");
//	} while (homeCook.getUsername() == null);
//
//	do {
//		System.out.print("Enter Password: ");
//		String password = sc.nextLine();
//		if (isStringCorrect(password, Constants.PASSWOR_REGEX)) {
//			homeCook.setPassword(password);
//		} else
//			System.out.println("Invalid Password!");
//	} while (homeCook.getPassword() == null);
//
//	do {
//		System.out.print("Enter gender: " + Arrays.deepToString(Gender.values()));
//		String gender = sc.nextLine();
//		if (gender.length() == 0) {
//			System.out.println("Field is required!");
//		} else {
//			try {
//				homeCook.setGender(Gender.valueOf(Gender.class, gender.toUpperCase()));
//			} catch (IllegalArgumentException e) {
//				System.out.println("Invalid gender!");
//			}
//		}
//	} while (homeCook.getGender() == null);
//
//	do {
//		System.out.print("Enter picture URL or leave blank: ");
//		String picture = sc.nextLine();
//		if (picture.length() == 0) {
//			homeCook.setPicture("");
//			break;
//		} else {
//			if (isStringCorrect(picture, Constants.PICTURE_URL_REGEX)) {
//				homeCook.setPicture(picture);
//				break;
//			} else
//				System.out.println("Invalid picture URL!");
//		}
//	} while (homeCook.getPicture() == null);
//
//	do {
//		System.out.print("Enter description(between 20 and 2500 smbols) or leave blank: ");
//		String description = sc.nextLine();
//		if (description.length() == 0) {
//			homeCook.setDescription("");
//			break;
//		} else {
//			if (isStringCorrect(description, "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\"]{20,2500}")) {
//				homeCook.setDescription(description);
//				break;
//			} else
//				System.out.println("Invalid description!");
//		}
//	} while (homeCook.getDescription() == null);
//	homeCook.setRole(Role.HOME_COOK);
//	homeCook.setStatus(Status.ACTIVE);
//	homeCook.setMetadata("None");
//	homeCook.setCreated(calendar.getTime());
//	homeCook.setModified(calendar.getTime());
//	addHomeCook((HomeCook)homeCook);
//	return (HomeCook) homeCook;
//}