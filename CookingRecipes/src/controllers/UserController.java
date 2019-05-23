package controllers;

import static utils.Utils.*;

import static utils.Constants.*;


import java.util.List;
import java.util.stream.Collectors;

import dao.UserRepository;
import dao.UserRepositoryImpl;
import exceptions.EntityDoesNotExistException;
import exceptions.EntityExistsException;
import models.Administrator;
import models.Gender;
import models.HomeCook;
import models.Role;
import models.Status;
import models.User;
import utils.Constants;

public class UserController {
	static long[] idCount = new long[1];
	static {
		idCount[0]=1;
	}

	private UserRepository userRepo = new UserRepositoryImpl(()->idCount[0]++);

	public List<HomeCook> getHomeCooks() {
		return userRepo.getHomeCooks();
	}

	public List<Administrator> getAdmins() {
		return userRepo.getAdmins();
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll().stream().collect(Collectors.toList());
	}

	public List<User> getUsersByName(String name) {
		return userRepo.getUserByName(name);
	}

	public List<User> getUsersByUsername(String username) {
		return userRepo.getUserByUsername(username);
	}

	public void addHomeCook(HomeCook homeCook) {
		if(isUserValid(homeCook))
			try {
				userRepo.add(homeCook);
			} catch (EntityExistsException e) {
				System.out.println(e.getMessage());
			}
	}

	public void addAdministrator(Administrator admin) {
		if(isUserValid(admin))
			try {
				userRepo.add(admin);
			} catch (EntityExistsException e) {
				System.out.println(e.getMessage());
			}
	}

	public void addUser(User user) {
		if(isUserValid(user))
			try {
				userRepo.add(user);
			}catch (EntityExistsException e) {
				System.out.println(e.getMessage());
			}
	}
	
	public User getAdministratorById(long id) {
		return userRepo.getAdminById(id);
	}

	public User getHomeCookById(long id) {
		return userRepo.getHomeCookById(id);
	}

	public User getUserByCredentials(String username, String password) {
		return userRepo.getUserByCredentials(username, password);
	}

	public User getUserById(long id) {
		return userRepo.findById(id).orElse(null);
	}

	public boolean deleteUserById(long id) {
		User user = getUserById(id);
		if (user == null)
			return false;
		else {
			if(getAdmins().size()<1)
				return false;
			userRepo.delete(id);
			return true;
		}
	}
	
	public User updateUser(User user) {
		try {
			return userRepo.update(user);
		}catch(EntityDoesNotExistException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public boolean isUserValid(User user) {
		return !userRepo.findAll().stream().anyMatch(u->u.getId()==user.getId()) &&
				isStringCorrect(user.getFirstName(), "^[a-zA-Z]{2,15}") &&
				isStringCorrect(user.getLastName(), "^[a-zA-Z]{2,15}") &&
				isStringCorrect(user.getEmail(), EMAIL_REGEX) &&
				!userRepo.findAll().stream().anyMatch(u -> u.getEmail().equals(user.getEmail())) &&
				isGenderValid(user.getGender()) &&
				isRoleValid(user.getRole()) &&
				isPictureValid(user.getPicture()) &&
				isStatusValid(user.getStatus()) &&
				isDescriptionValid(user.getDescription()) &&
				isStringCorrect(user.getUsername(), "^[a-zA-Z]{2,15}") &&
				isStringCorrect(user.getPassword(), PASSWOR_REGEX) &&
				isMetadataValid(user.getMetadata());
	}
	
	public boolean isGenderValid(Gender gender) {
		for(Gender g: Gender.values()) 
			if(g.equals(gender))
				return true;
		return false;
	}
	
	public boolean isRoleValid(Role role) {
		for(Role g: Role.values()) 
			if(g.equals(role))
				return true;
		return false;
	}
	
	public boolean isPictureValid(String picture) {
		return picture.length()==0||isStringCorrect(picture, Constants.PICTURE_URL_REGEX);
	}
	
	public boolean isStatusValid(Status status) {
		for(Status s: Status.values())
			if(s.equals(status))
				return true;
		return false;
	}
	
	public boolean isDescriptionValid(String description) {
		return description.length()==0||isStringCorrect(description, "[a-zA-Z\\\\ \\\\(\\\\)\\\\!\\\\?\\\\;\\\\:\\\\d\\\\.\\\\,\\\\'\\\"]{20,2500}");
	}
	
	public boolean isMetadataValid(String metadata) {
		return metadata.length()==0||isStringCorrect(metadata, "[a-zA-Z\\ \\(\\)\\!\\?\\;\\:\\d\\.\\,\\'\"]{1,512}");
	}

}
