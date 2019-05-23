package utils;

import models.Administrator;
import models.Category;
import models.Gender;
import models.HomeCook;
import models.Role;
import models.Status;
import models.User;
import static utils.Utils.*;

import java.util.Arrays;

import exceptions.EntityDoesNotExistException;

import static utils.Constants.*;

public class Input {

	public static HomeCook createHomeCookWithInput() {
		User homeCook = new HomeCook();
		do {
			System.out.print("Enter first name: ");
			String fName = sc.nextLine();
			if (isStringCorrect(fName, "^[a-zA-Z]{2,15}")) {
				homeCook.setFirstName(fName);
				break;
			} else
				System.out.println("Invalid first name!");
		} while (homeCook.getFirstName() == null);

		do {
			System.out.print("Enter last name: ");
			String lName = sc.nextLine();
			if (isStringCorrect(lName, "^[a-zA-Z]{2,15}")) {
				homeCook.setLastName(lName);
			} else
				System.out.println("Invalid last name!");
		} while (homeCook.getLastName() == null);

		do {
			System.out.print("Enter e-mail address: ");
			String email = sc.nextLine();
			if (isStringCorrect(email, EMAIL_REGEX)) {
				homeCook.setEmail(email);
			} else
				System.out.println("Invalid e-mail address!");
		} while (homeCook.getEmail() == null);

		do {
			System.out.print("Enter UserName: ");
			String username = sc.nextLine();
			if (isStringCorrect(username, "^[a-zA-Z]{2,15}")) {
				homeCook.setUsername(username);
			} else
				System.out.println("Invalid UserName!");
		} while (homeCook.getUsername() == null);

		do {
			System.out.print("Enter Password: ");
			String password = sc.nextLine();
			if (isStringCorrect(password, Constants.PASSWOR_REGEX)) {
				homeCook.setPassword(password);
			} else
				System.out.println("Invalid Password!");
		} while (homeCook.getPassword() == null);

		do {
			System.out.print("Enter gender: " + Arrays.deepToString(Gender.values()));
			String gender = sc.nextLine();
			if (gender.length() == 0) {
				System.out.println("Field is required!");
			} else {
				try {
					homeCook.setGender(Gender.valueOf(Gender.class, gender.toUpperCase()));
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid gender!");
				}
			}
		} while (homeCook.getGender() == null);

		do {
			System.out.print("Enter picture URL or leave blank: ");
			String picture = sc.nextLine();
			if (picture.length() == 0) {
				homeCook.setPicture("");
				break;
			} else {
				if (isStringCorrect(picture, Constants.PICTURE_URL_REGEX)) {
					homeCook.setPicture(picture);
					break;
				} else
					System.out.println("Invalid picture URL!");
			}
		} while (homeCook.getPicture() == null);

		do {
			System.out.print("Enter description(between 20 and 2500 smbols) or leave blank: ");
			String description = sc.nextLine();
			if (description.length() == 0) {
				homeCook.setDescription("");
				break;
			} else {
				if (isStringCorrect(description, "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\"]{20,2500}")) {
					homeCook.setDescription(description);
					break;
				} else
					System.out.println("Invalid description!");
			}
		} while (homeCook.getDescription() == null);
		homeCook.setRole(Role.HOME_COOK);
		homeCook.setStatus(Status.ACTIVE);
		homeCook.setMetadata("None");
		homeCook.setCreated(calendar.getTime());
		homeCook.setModified(calendar.getTime());
		return (HomeCook) homeCook;
	}

	public static User homeCookUpdateUser(User user) {
		do {
			System.out.print("Enter new first name or leave blank: ");
			String fName = sc.nextLine();
			if (fName.length() == 0)
				break;
			else {
				if (isStringCorrect(fName, "^[a-zA-Z]{2,15}")) {
					user.setFirstName(fName);
					break;
				} else
					System.out.println("Invalid first name!");
			}
		} while (true);

		do {
			System.out.print("Enter new last name or leave blank: ");
			String lName = sc.nextLine();
			if (lName.length() == 0)
				break;
			else {
				if (isStringCorrect(lName, "^[a-zA-Z]{2,15}")) {
					user.setLastName(lName);
					break;
				} else
					System.out.println("Invalid last name!");
			}
		} while (true);

		do {
			System.out.print("Enter new Password or leave blank: ");
			String password = sc.nextLine();
			if (password.length() == 0)
				break;
			else {
				if (isStringCorrect(password, Constants.PASSWOR_REGEX)) {
					user.setPassword(password);
					break;
				} else
					System.out.println("Invalid Password!");
			}
		} while (true);

		do {
			System.out.print("Enter new gender or leave blank: " + Arrays.deepToString(Gender.values()));
			String gender = sc.nextLine();
			if (gender.length() == 0) {
				break;
			} else {
				try {
					user.setGender(Gender.valueOf(Gender.class, gender.toUpperCase()));
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid gender!");
				}
			}
		} while (true);

		do {
			System.out.print("Enter new picture URL or leave blank: ");
			String picture = sc.nextLine();
			if (picture.length() == 0) {
				break;
			} else {
				if (isStringCorrect(picture, Constants.PICTURE_URL_REGEX)) {
					user.setPicture(picture);
					break;
				} else
					System.out.println("Invalid picture URL!");
			}
		} while (true);

		do {
			System.out.print("Enter new description(between 20 and 2500 smbols) or leave blank: ");
			String description = sc.nextLine();
			if (description.length() == 0) {
				break;
			} else {
				if (isStringCorrect(description, "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\"]{20,2500}")) {
					user.setDescription(description);
					break;
				} else
					System.out.println("Invalid description!");
			}
		} while (true);

		user.setModified(calendar.getTime());
		return user;

	}

	public static User adminUpdateUser(User user) throws EntityDoesNotExistException {
		Role r = user.getRole();
		do {
			System.out.print("Enter new first name or leave blank: ");
			String fName = sc.nextLine();
			if (fName.length() == 0)
				break;
			else {
				if (isStringCorrect(fName, "^[a-zA-Z]{2,15}")) {
					user.setFirstName(fName);
					break;
				} else
					System.out.println("Invalid first name!");
			}
		} while (true);

		do {
			System.out.print("Enter new last name or leave blank: ");
			String lName = sc.nextLine();
			if (lName.length() == 0)
				break;
			else {
				if (isStringCorrect(lName, "^[a-zA-Z]{2,15}")) {
					user.setLastName(lName);
					break;
				} else
					System.out.println("Invalid last name!");
			}
		} while (true);

		do {
			System.out.print("Enter new gender or leave blank: " + Arrays.deepToString(Gender.values()));
			String gender = sc.nextLine();
			if (gender.length() == 0) {
				break;
			} else {
				try {
					user.setGender(Gender.valueOf(Gender.class, gender.toUpperCase()));
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid gender!");
				}
			}
		} while (true);

		do {
			System.out.print("Enter new role or leave blank\n"
					+ "(Note: If you change role, you'll loose user specific data!!!): "
					+ Arrays.deepToString(Role.values()));
			String role = sc.nextLine();
			if (role.length() == 0) {
				break;
			} else {
				try {
					user.setRole(Role.valueOf(Role.class, role.toUpperCase()));
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid gender!");
				}
			}
		} while (true);

		do {
			System.out.print("Enter new picture URL or leave blank: ");
			String picture = sc.nextLine();
			if (picture.length() == 0) {
				break;
			} else {
				if (isStringCorrect(picture, Constants.PICTURE_URL_REGEX)) {
					user.setPicture(picture);
					break;
				} else
					System.out.println("Invalid picture URL!");
			}
		} while (true);

		do {
			System.out.print("Enter new description(between 20 and 2500 smbols) or leave blank: ");
			String description = sc.nextLine();
			if (description.length() == 0) {
				break;
			} else {
				if (isStringCorrect(description, "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\"]{20,2500}")) {
					user.setDescription(description);
					break;
				} else
					System.out.println("Invalid description!");
			}
		} while (true);

		do {
			System.out.print("Enter new metadata or leave blank: ");
			String metadata = sc.nextLine();
			if (metadata.length() == 0) {
				break;
			} else {
				if (isStringCorrect(metadata, "[a-zA-Z\\ \\(\\)\\!\\?\\d\\.\\,\\'\"]{1,512}")) {
					user.setMetadata(metadata);
					break;
				} else
					System.out.println("Invalid metadata!");
			}
		} while (true);

		do {
			System.out.print("Enter new Status or leave blank: " + Arrays.deepToString(Status.values()));
			String status = sc.nextLine();
			if (status.length() == 0) {
				break;
			} else {
				try {
					user.setStatus(Status.valueOf(Status.class, status.toUpperCase()));
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid status!");
				}
			}
		} while (true);
		if (!user.getRole().equals(r)) {
			Long id = user.getId();
			if (user.getRole().equals(Role.HOME_COOK)) {
				user = new HomeCook(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(),
						user.getPassword(), user.getGender(), Role.HOME_COOK, user.getPicture(), user.getDescription(),
						user.getMetadata(), user.getStatus(), user.getComments(), user.getRecipes(), user.getCreated(),
						calendar.getTime());
				user.setId(id);
			}

			else {
				user = new Administrator(user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(),
						user.getPassword(), user.getGender(), user.getPicture(), user.getDescription(),
						user.getMetadata());
				user.setId(id);
			}
		}
		user.setModified(calendar.getTime());
		return user;

	}
	
	public static Category createCategory() {
		Category category = new Category();
		do {
			System.out.print("Enter category name: ");
			String name = sc.nextLine();

			if (isStringCorrect(name, "^[a-zA-Z]{2,120}")) {
				category.setName(name);
				break;
			} else
				System.out.println("Invalid category name!");

		} while (category.getName()==null);
		
		do {
			System.out.print("Enter categor description: ");
			String description = sc.nextLine();

			if (isStringCorrect(description, "^[a-zA-Z#]{10,500}")) {
				category.setDescription(description);
				break;
			} else
				System.out.println("Invalid category description!");

		} while (category.getDescription()==null);
		do {
			System.out.print("Enter category tags: ");
			String tags = sc.nextLine();

			if (isStringCorrect(tags, "^[a-zA-Z]{2,120}")) {
				category.setTags(tags);
				break;
			} else
				System.out.println("Invalid category tags!");

		} while (category.getTags()==null);
		category.setCreated(calendar.getTime());
		category.setModified(calendar.getTime());		
		return category;
	}

}
