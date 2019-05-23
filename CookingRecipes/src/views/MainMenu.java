package views;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controllers.AllControllersWrapper;
import exceptions.EntityDoesNotExistException;
import models.Category;
import models.Comment;
import models.Recipe;
import models.Role;
import models.User;
import utils.Constants;
import utils.Input;

import static utils.Constants.USER_HEADER;
import static utils.Utils.sc;
import static views.MenuCommand.*;

public class MainMenu {
	private static AllControllersWrapper wrapper = new AllControllersWrapper();

	private List<MenuItem> loginMenuItems = new ArrayList();
	private List<MenuItem> adminMenuItems = new ArrayList();
	private List<MenuItem> homeCookMenuItems = new ArrayList();
	private Map<MenuCommand, Command> allCommands = new HashMap<MenuCommand, Command>();

	private static User loggedUser;

	private static final String DB_PATH = "CookingRecipes.db";
	private static final String LOG_IN_MENU_PATH = "LogInMenu.txt";
	private static final String ADMIN_MENU_PATH = "AdminMenu.txt";
	private static final String HOME_COOK_MENU_PATH = "HomeCookMenu.txt";

	private static final String[] LOGIN_COMMANDS = new String[] { "If you have already registered type in: = LOG_IN",
			"To create a new user, type in: = SIGN_UP" };
	private static final String[] ADMIN_COMMANDS = new String[] { "To get back to login screen, type in: = RETURN",
			"To show all users, type in: = SHOW_ALL_USERS", "To show my comments, type in: = SHOW_MY_COMMENTS",
			"To show my recipes, type in: = SHOW_MY_RECIPES", "To show all categories, type in: = SHOW_ALL_CATEGORIES",
			"To show my categories, type in: = SHOW_MY_CATEGORIES", "To add a category, type in: = ADD_CATEGORY",
			"To delete a category, type in: = DELETE_CATEGORY",
			"To show recipes by category, type in: = SHOW_RECIPES_BY_CATEGORY",
			"To add a recipe, type in: = ADD_RECIPE", "To delete a recipe, type in: = DELETE_RECIPE",
			"To find users by id, type in: = FIND_USERS_BY_ID",
			"To find users by username, type in: = FIND_USERS_BY_USERNAME", "To delete an user, type in: = DELETE_USER",
			"To modify an user, type in: = MODIFY_USER", "To save all changes, type in: = SAVE",
			"To load from a file, type in: = LOAD" };

	private static final String[] HOME_COOK_COMMANDS = new String[] { "To get back to login screen, type in: = RETURN",
			"To show all users, type in: = SHOW_ALL_USERS",
			"To show my favourite cooks, type in: = SHOW_FAVOURITE_COOKS",
			"To show my comments, type in: = SHOW_MY_COMMENTS", "To show my recipes, type in: = SHOW_MY_RECIPES",
			"To show my favourite recipes, type in: = SHOW_FAVOURITE_RECIPES",
			"To show all categories, type in: = SHOW_ALL_CATEGORIES",
			"To show recipes by category, type in: = SHOW_RECIPES_BY_CATEGORY",
			"To add a recipe, type in: = ADD_RECIPE", "To delete a recipe, type in: = DELETE_RECIPE",
			"To update MyInfo, type in: = UPDATE_USER" };

	public MainMenu() {
		wrapper.initialize(Arrays.asList(AllControllersWrapper.PRE_DEF_USERS),
				Arrays.asList(AllControllersWrapper.PRE_DEF_RECIPES),
				Arrays.asList(AllControllersWrapper.PRE_DEF_COMMENTS),
				Arrays.asList(AllControllersWrapper.PRE_DEF_CATEGORIES));
		try (BufferedReader br = new BufferedReader(new FileReader(LOG_IN_MENU_PATH))) {
			List<String> lines = new ArrayList();
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			if (lines.size() <= 0) {
				loadMenu(LOGIN_COMMANDS, loginMenuItems);
				System.out.println("Login Menu loaded from default.");
			} else {
				loadMenu(LOGIN_COMMANDS, loginMenuItems);
				System.out.println("Login Menu loaded from file.");
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_MENU_PATH))) {
			List<String> lines = new ArrayList();
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			if (lines.size() <= 0) {
				loadMenu(ADMIN_COMMANDS, adminMenuItems);
				System.out.println("Admin Menu loaded from default.");
			} else {
				loadMenu(ADMIN_COMMANDS, adminMenuItems);
				System.out.println("Admin Menu loaded from file.");
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (BufferedReader br = new BufferedReader(new FileReader(HOME_COOK_MENU_PATH))) {
			List<String> lines = new ArrayList();
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			if (lines.size() <= 0) {
				loadMenu(HOME_COOK_COMMANDS, homeCookMenuItems);
				System.out.println("HomeCook Menu loaded from default.");
			} else {
				loadMenu(HOME_COOK_COMMANDS, homeCookMenuItems);
				System.out.println("HomeCook Menu loaded from file.");

			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		allCommands.put(NONE, () -> true);
		allCommands.put(EXIT, () -> {
			System.out.println("You are goiung to exit. All unsaved data will be lost. Are you sure? [y/n]:");
			if (sc.nextLine().toLowerCase().startsWith("y")) {
				System.out.println("Exiting from invoicing program ...");
				System.exit(0);
			}
			return true;
		});
		allCommands.put(SIGN_UP, () -> {
			wrapper.getUserController().addHomeCook(Input.createHomeCookWithInput());
			return true;
		});
		allCommands.put(LOG_IN, () -> {
			System.out.print("Enter UserName:");
			String un = sc.nextLine();
			System.out.print("\nEnter Password:");
			String ps = sc.nextLine();
			loggedUser = wrapper.getUserController().getUserByCredentials(un, ps);
			if (loggedUser.getRole().equals(Role.ADMIN))
				startAdminMenu();
			else
				startHomeCookMenu();
			return true;
		});
		allCommands.put(SHOW_ALL_USERS, () -> {
			System.out.println(Constants.USER_HEADER);
			for (User u : wrapper.getUserController().getAllUsers()) {
				System.out.println(u.toString());
			}
			return true;
		});
		allCommands.put(SHOW_MY_COMMENTS, () -> {
			System.out.println(Constants.COMMENT_HEADER);
			for (Comment c : loggedUser.getComments()) {
				System.out.println(c.toString());
			}
			return true;
		});
		allCommands.put(SHOW_MY_RECIPES, () -> {
			System.out.println(Constants.RECIPE_HEADER);
			for (Recipe r : loggedUser.getRecipes()) {
				System.out.println(r);
			}
			return true;
		});
		allCommands.put(MODIFY_USER, () -> {
			try {
				editUserAsAdmin();
			} catch (EntityDoesNotExistException e) {
				System.out.println(e.getMessage());
			}
			return true;
		});
		allCommands.put(SAVE, () -> {
			try (ObjectOutputStream outSession = new ObjectOutputStream(new FileOutputStream(DB_PATH))) {
				wrapper.reloadValuesForAllControllers();
				outSession.writeObject(wrapper);
				return true;
			} catch (IOException e) {
				System.err.println("Error saving data to file: " + DB_PATH);

				return false;
			}
		});
		allCommands.put(LOAD, () -> {
			try (ObjectInputStream inSession = new ObjectInputStream(new FileInputStream(DB_PATH))) {
				wrapper = null;
				wrapper = (AllControllersWrapper) inSession.readObject();
				wrapper.loadCounters();
				wrapper.reloadAllRepositories();
				return true;
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error loading data from file: " + DB_PATH);
				e.printStackTrace();
				return false;
			}
		});
		allCommands.put(DELETE_USER, ()->{
			do{
			System.out.println("Enter the ID of the user you want to delete:");
			String id=sc.nextLine();
			try {
				boolean b=wrapper.getUserController().deleteUserById(Long.valueOf(id));
				if(b) {
					System.out.println("User with id:"+id+" was deleted");
					return true;
				}
				else{
					System.out.println("User not found");
					
				}
				
			}catch(NumberFormatException e) {
				System.out.println("Invalid ID!");
			}
			}while(true);
		});
		allCommands.put(ADD_CATEGORY,()->{
			wrapper.getCategoryController().addCategory(Input.createCategory());
			return true;
		});
		allCommands.put(SHOW_ALL_CATEGORIES, ()->{
			System.out.println(Constants.CATEGORY_HEADER);
			for(Category c:wrapper.getCategoryController().getAllCategories()) {
				System.out.println(c.toString());
			}
			return true;
		});
	}

	public void start() {
		boolean finishedFlag = false;
		do {
			System.out.println("-============={Welcome to cooking recipes! :)  }=============-");
			for (int i = 0; i < LOGIN_COMMANDS.length; i++) {
				System.out.println(String.format("%-45.45s {%d}", loginMenuItems.get(i).getLabel(), i));
			}
			System.out.println("______________________________________________________________");
			String answer;
			int chosenOption = 0;
			do {
				System.out.println("Please select operation [0 to " + (loginMenuItems.size() - 1) + "]:");
				answer = sc.nextLine();
				try {
					chosenOption = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.err.println("Invalid option - should be [0 to " + (loginMenuItems.size() - 1) + "].");
				}
			} while (chosenOption < 0 || chosenOption > loginMenuItems.size());

			// execute command
			Command commandToExecute = allCommands.get(loginMenuItems.get(chosenOption).getCommand());
			if (commandToExecute != null) {
				commandToExecute.action();
			}
			System.out.println("\n");
		} while (!finishedFlag);

	}

	private static void editUserAsAdmin() throws EntityDoesNotExistException {
		System.out.print("Enter user ID: ");
		try {
			User user = wrapper.getUserController().getUserById(Long.valueOf(sc.nextLine()));
			if (user == null) {
				System.out.println("No user with such ID...");
			} else if (user.getRole().equals(Role.ADMIN))
				System.out.println("You can't edit an Admin!");
			else {
				System.out.println(USER_HEADER);
				System.out.println(user);
				wrapper.getUserController().updateUser(Input.adminUpdateUser(user));
				System.out.println("\nUser with id: " + user.getId() + " was changed successfully");
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid ID!");
		}

	}

	public void startAdminMenu() {
		boolean finishedFlag = false;
		do {
			System.out.println("-========================{ADMIN MENU}========================-");
			for (int i = 0; i < ADMIN_COMMANDS.length; i++) {
				System.out.println(String.format("%-45.45s {%d}", adminMenuItems.get(i).getLabel(), i));
			}
			System.out.println("______________________________________________________________");
			String answer;
			int chosenOption = 0;
			do {
				System.out.println("Please select operation [0 to " + (adminMenuItems.size() - 1) + "]:");
				answer = sc.nextLine();
				try {
					chosenOption = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.err.println("Invalid option - should be [0 to " + (adminMenuItems.size() - 1) + "].");
				}
			} while (chosenOption < 0 || chosenOption > adminMenuItems.size());

			// execute command
			Command commandToExecute = allCommands.get(adminMenuItems.get(chosenOption).getCommand());
			if (commandToExecute != null) {
				commandToExecute.action();
			}
			if (chosenOption == 0)
				finishedFlag = true;
			System.out.println("\n");
		} while (!finishedFlag);
	}

	public void startHomeCookMenu() {
		boolean finishedFlag = false;
		do {
			System.out.println("-========================{HOMECOOK MENU}========================-");
			for (int i = 0; i < HOME_COOK_COMMANDS.length; i++) {
				System.out.println(String.format("%-45.45s {%d}", homeCookMenuItems.get(i).getLabel(), i));
			}
			System.out.println("______________________________________________________________");
			String answer;
			int chosenOption = 0;
			do {
				System.out.println("Please select operation [0 to " + (homeCookMenuItems.size() - 1) + "]:");
				answer = sc.nextLine();
				try {
					chosenOption = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.err.println("Invalid option - should be [0 to " + (homeCookMenuItems.size() - 1) + "].");
				}
			} while (chosenOption < 0 || chosenOption > homeCookMenuItems.size());

			// execute command
			Command commandToExecute = allCommands.get(homeCookMenuItems.get(chosenOption).getCommand());
			if (commandToExecute != null) {
				commandToExecute.action();
			}
			if (chosenOption == 0)
				finishedFlag = true;
			System.out.println("\n");
		} while (!finishedFlag);
	}

	private void loadMenu(String[] array, List<MenuItem> menu) {
		for (String s : array) {
			menu.add(parseMenuItemString(s));
		}
	}

	public static MenuItem parseMenuItemString(String menuItemString) {
		Pattern p = Pattern.compile("\\s*(.+)\\s*=\\s*(.+)\\s*");
		Matcher m = p.matcher(menuItemString);
		if (m.matches()) {
			try {
				return new MenuItem(m.group(1), Enum.valueOf(MenuCommand.class, m.group(2)));
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			}
		}
		System.err.println("Incorrect menu item string: " + menuItemString);
		return new MenuItem();
	}

	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu();
		mainMenu.start();

	}
}
