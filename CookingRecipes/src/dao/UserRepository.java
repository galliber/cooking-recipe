package dao;

import java.util.List;

import dao.infrastructure.Repository;
import models.Administrator;
import models.HomeCook;
import models.User;

public interface UserRepository extends Repository<Long, User> {
	List<HomeCook> getHomeCooks();
	List<Administrator> getAdmins();
	List<User> getUserByName(String name);
	List<User> getUserByUsername(String username);
	User getAdminById(Long id);
	User getHomeCookById(Long id);
	User getUserByCredentials(String username, String password);
}
