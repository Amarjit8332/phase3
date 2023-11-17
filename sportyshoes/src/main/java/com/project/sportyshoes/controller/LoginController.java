package com.project.sportyshoes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.sportyshoes.global.GlobalData;
import com.project.sportyshoes.model.Role;
import com.project.sportyshoes.model.User;
import com.project.sportyshoes.repository.RoleRepository;
import com.project.sportyshoes.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
	    String password = user.getPassword();

	    user.setPassword(bCryptPasswordEncoder.encode(password));

	    // Check if the role with ID 2 exists
	    Role role = roleRepository.findById(2).orElse(null);

	    if (role != null) {
	    	 List<Role> roles = new ArrayList<>();
		        roles.add(role);
		        user.setRoles(roles);
		        userRepository.save(user);
		        request.login(user.getEmail(), password);
	    } else {
	        // Handle the case when the role with ID 2 does not exist
	        // You might want to log an error or throw an exception
	        // For example:
	        throw new RuntimeException("Role with ID 2 not found");
	    }
	    
	    
	    return "redirect:/";
	}

	
}

