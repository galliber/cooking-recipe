package views;

import controllers.CategoryController;
import controllers.CommentController;
import controllers.RecipeController;
import controllers.UserController;
import exceptions.EntityDoesNotExistException;
import exceptions.EntityExistsException;

import static utils.Utils.*;
import static utils.Constants.*;

import java.util.List;

import models.User;
import utils.Constants;
import models.Administrator;
import models.Category;
import models.Gender;
import models.HomeCook;
import models.Role;
import models.Status;

public class MainDemo {
	private static UserController uc;
	private static CategoryController cc;
	private static RecipeController rc;
	private static CommentController comc;
	static {
		uc = new UserController();
		cc = new CategoryController();
		rc = new RecipeController();
		comc = new CommentController();
			uc.addAdministrator(new Administrator("Denis", "Pavlov", "galiber12321@gmail.com", "galiber", "somepass123.", Gender.MALE, "",
					"The master of all things!", "Master!"));
		cc.createCategory("Soups", "Here we post soup recipes!", "");
		cc.createCategory("Salads", "Thee shall only post salad recipes here!", "");
	}

	public static void main(String[] args) throws EntityDoesNotExistException {
		do {
			printLoginMenu();
			switch (sc.nextLine()) {
			case "1":
				logIn();
				break;
			case "2":
				uc.addHomeCook(null);;
				System.out.println("____________________________________\n" + "Registration completed!\n");
				break;
			default:
				System.out.println("Invalid input!");
			}
		} while (true);
	}

	public static void printLoginMenu() {
		System.out.println(String.format("%s \n%-40.40s :%d \n" + "%-40.40s :%d", "Hello there! :)",
				"If you already have an account type in ", 1, "If you don't, type in ", 2));
	}

	public static void logIn() throws EntityDoesNotExistException {
		User user;
		int counter = 5;
		do {
			user = uc.getUserByCredentials("", "");
			if (user == null) {
				counter--;
				System.out.println("There is no such user...\n" + counter + " atempts left.");
			}
		} while (user == null && counter != 0);
		switch (user.getRole()) {
		case ADMIN:
			runAdminMenu();
			break;
		case HOME_COOK:
			runHomeCookMenu((HomeCook)user);

		}
	}

	private static void runHomeCookMenu(HomeCook user) {
		outerLoop: 
		do {
			System.out.println(String.format(
				"%s %s%s\n%-41.41s :%d\n" 
				+ "%-41.41s :%d\n"
				+ "%-41.41s :%d\n"
				+ "%-41.41s :%d\n"
				+ "%-41.41s :%d\n"
				+ "%-41.41s :%d\n",
				"Welcome ", user.getUsername(),"!",
				"To see all HomeCooks, type in", 1, 
				"To see all Recipes, type in", 2,
				"To search for a user by name, type in", 3, 
				"To search for a user by username, type in", 4,
				"To modify a user by ID, type in", 5, 
				"To exit, type in", 0));
		switch (sc.nextLine()) {
		case "1":
			printAllHomeCooks();
			break;
		case "2":
			printAllRecipes();
			break;
		case "3":
			findUserByName();
			break;
		case "4":
			findUserByUsername();
			break;
		case "5":
			editUser(user);
			break;
		case "6":
			deleteUser();
			break;
		case "0":
			break outerLoop;
		default:
			System.out.println("Invalid input!");
		}
	} while (true);

	}

	private static void editUser(User user) {
		uc.updateUser(user);
	}

	private static void printAllRecipes() {
		// TODO Auto-generated method stub
		
	}

	private static void runAdminMenu() throws EntityDoesNotExistException {
		outerLoop: 
		do {
			System.out.println(String.format("%s\n%-41.41s :%d\n" 
					+ "%-41.41s :%d\n"
					+ "%-41.41s :%d\n"
					+ "%-41.41s :%d\n"
					+ "%-41.41s :%d\n"
					+ "%-41.41s :%d\n"
					+ "%-41.41s :%d\n"
					+ "%-41.41s :%d\n"
					+ "%-41.41s :%d\n",
					"Welcome to admin menu!", 
					"To see all HomeCooks, type in", 1, 
					"To see all admins, type in", 2,
					"To search for a user by name, type in",3,
					"To search for a user by username, type in", 4,
					"To modify a user by ID, type in", 5, 
					"To delete user by ID, type in", 6,
					"To see all categories, type in", 7,
					"To add category, type in", 8,
					"To exit Admin Menu, type in", 0));
			switch (sc.nextLine()) {
			case "1":
				printAllHomeCooks();
				break;
			case "2":
				printAllAdmins();
				break;
			case "3":
				findUserByName();
				break;
			case "4":
				findUserByUsername();
				break;
			case "5":
				editUserAsAdmin();
				break;
			case "6":
				deleteUser();
				break;
			case "7":
				seeCategories();
				break;
			case "8":
				cc.addCategory(null);
				break;
			case "0":
				break outerLoop;
			default:
				System.out.println("Invalid input!");
			}
		} while (true);

	}

	private static void seeCategories() {
		System.out.println(CATEGORY_HEADER);
		cc.getAllCategories().stream().forEachOrdered(System.out::println);
		
	}

	private static void deleteUser() {
		System.out.println("Enter the ID of the user that'll be deleted: ");
		try {
			if (uc.deleteUserById(Long.valueOf(sc.nextLine()))) {
				System.out.println("User was successfully deleted!");
			} else
				System.out.println("No such user found");
		} catch (NumberFormatException e) {
			System.out.println("Invalid input!");
		}
	}

	private static void editUserAsAdmin() throws EntityDoesNotExistException {
		System.out.print("Enter user ID: ");
		try {
			User user = uc.getUserById(Long.valueOf(sc.nextLine()));
			if (user == null) {
				System.out.println("No user with such ID...");
			} else if (user.getRole().equals(Role.ADMIN))
				System.out.println("You can't edit an Admin!");
			else {
				System.out.println(USER_HEADER);
				System.out.println(user);
				uc.updateUser(user);
				System.out.println("\nUser with id: " + user.getId() + " was changed successfully");
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid ID!");
		}

	}

	private static void findUserByUsername() {
		System.out.print("Enter username");
		List<User> result = uc.getUsersByUsername(sc.nextLine());
		if (result.size() != 0)
			result.stream().forEach(System.out::println);
		else
			System.out.println("\nThe are no results...\n");
	}

	private static void findUserByName() {
		
		System.out.print("Enter name: ");
		List<User> result = uc.getUsersByName(sc.nextLine());
		if (result.size() != 0) {
			System.out.println(USER_HEADER);
			result.stream().forEach(System.out::println);
		}
		else
			System.out.println("\nThe are no results...\n");
	}

	private static void printAllAdmins() {
		System.out.println(USER_HEADER);
		uc.getAdmins().parallelStream().forEach(System.out::println);
	}

	private static void printAllHomeCooks() {
		System.out.println(USER_HEADER);
		uc.getHomeCooks().stream().forEach(System.out::println);
	}
}
