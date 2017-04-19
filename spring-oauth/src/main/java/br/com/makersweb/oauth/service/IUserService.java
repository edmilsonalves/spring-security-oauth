/**
 * 
 */
package br.com.makersweb.oauth.service;

import java.util.List;

import br.com.makersweb.oauth.entity.User;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IUserService {

	User findById(long id);

	User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	boolean isUserExist(User user);

}
