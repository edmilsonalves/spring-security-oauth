/**
 * 
 */
package br.com.makersweb.oauth.service.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.makersweb.oauth.entity.User;
import br.com.makersweb.oauth.service.IUserService;

/**
 *
 * @author anderson.aristides
 *
 */
@Service("userService")
public class UserService implements IUserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

	@Override
	public User findById(long id) {
		User user = users.stream().filter(u -> u.getId() == id).findAny().orElse(null);
		return user;
	}

	@Override
	public User findByName(String name) {
		User user = users.stream().filter(u -> u.getName().equalsIgnoreCase(name)).findAny().orElse(null);
		return user;
	}

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	@Override
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	@Override
	public void deleteUserById(long id) {
		User user = users.stream().filter(u -> u.getId() == id).findAny().orElse(null);
		int index = users.indexOf(user);
		users.remove(index);	
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void deleteAllUsers() {
		users.clear();
	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000));
		users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000));
		users.add(new User(counter.incrementAndGet(), "Jerome", 45, 30000));
		users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
		return users;
	}

}
