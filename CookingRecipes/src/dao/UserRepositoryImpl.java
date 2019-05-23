package dao;

import java.util.List;
import java.util.stream.Collectors;

import dao.infrastructure.RepositoryImpl;
import models.Administrator;
import models.HomeCook;
import models.Role;
import models.User;
import utils.Utils;

public class UserRepositoryImpl extends RepositoryImpl<Long, User> implements UserRepository{
	
	

	public UserRepositoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRepositoryImpl(IdGenerator<Long> idGenerator) {
		super(idGenerator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<HomeCook> getHomeCooks() {
		return entries.values().stream()
		.map(u->(User)u)
		.filter(u -> u.getRole().equals(Role.HOME_COOK))
		.map(u->(HomeCook)u)
		.collect(Collectors.toList());
	}

	@Override
	public List<Administrator> getAdmins() {
		return entries.values().stream()
				.map(u->(User)u)
				.filter(u -> u.getRole().equals(Role.ADMIN))
				.map(u->(Administrator)u)
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getUserByName(String name) {
		return entries.values().stream()
				.filter(u -> Utils.containsCaseInsensitive(u.getFirstName() + u.getLastName(), name))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> getUserByUsername(String username) {
		return entries.values().stream()
				.filter(u -> Utils.containsCaseInsensitive(u.getUsername(), username))
				.collect(Collectors.toList());
	}

	@Override
	public Administrator getAdminById(Long id) {
		return (Administrator)entries.get(id);
	}

	@Override
	public HomeCook getHomeCookById(Long id) {
		return (HomeCook)entries.get(id);
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		return entries.values().stream()
				.filter(u -> (u.getUsername().equals(username) || u.getEmail().contentEquals(username))
						&& u.getPassword().equals(password))
				.findAny().orElse(null);
	}
	
}
