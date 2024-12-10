package in.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.main.entity.Movie;
import in.main.entity.User;
import in.main.repository.UserRepo;
import in.main.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "login";
	}
	
	
	@PostMapping("/userLogin")
	public String login(@ModelAttribute User user,HttpSession session) {
		 Boolean isTrue=userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		 if(isTrue) {
			 return "/";
		 }
		 else {
			 session.setAttribute("succMsg","User or Password Wrong !");
			 return "redirect:/signin";
		 }
	}
	
	
	@GetMapping("/logOut")
	public String logout() {
		return "redirect:/signin";
	}
	
	
	@GetMapping("/register")
	public String home2() {
		return "register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user,HttpSession session) {
		User save=userServiceImpl.saveUser(user);
		if(save != null) {
			session.setAttribute("succMsg","User Registration Successfully");
		}
		else {
			session.setAttribute("errorMsg","User Registration Failed");
		}
		return "redirect:/signin";
	}
	
}
