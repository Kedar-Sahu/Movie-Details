package in.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.entity.User;



public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
	
	Boolean findByUsernameAndPassword(String username,String password);
	

}
