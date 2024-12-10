package in.main.service;

import in.main.entity.User;

public interface UserService {

	public User saveUser(User user);

	public void removeSessionMessage();

}
