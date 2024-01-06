package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entity.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@org.springframework.beans.factory.annotation.Autowired(required=true);

@Controller
public class HomeController {

	/*
	 * @Autowired// this is for injecting password encoder into user private
	 * BCryptPasswordEncoder passwordEncoder;
	 * 
	 */

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String home(Model m1) {// model for sending dynamic value that is stored in
									// fragment and view in browser with the help of html
									// file.

		m1.addAttribute("tittle", "Home- Smart Contact Manager");

		return "home";
	}

	@GetMapping("/about")
	public String about(Model m1) {

		m1.addAttribute("tittle", "About- Smart Contact Manager");

		return "about";
	}

	@GetMapping("/signup")
	public String signup(Model m1) {

		m1.addAttribute("tittle", "Register- Smart Contact Manager");

		/* ======next tutorial.... */
		m1.addAttribute("user", new User());// for sending blank fields from here and retrieving computed fields from
											// there.

		return "signup";
	}

	// handler for register user...
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model m1,
			HttpSession session) {// for retrieving data from user singup form

		try {

			if (!agreement) {// for throwing user understandable message if check box not
				// selected like // throwing error
				System.out.println("You have not checked terms and conditions..");
				throw new Exception("You have not checked terms and conditions..");// this exception
				// goes to catch block // and store message in e variable // as argument inside
				// catch block
			}
			if (result.hasErrors()) {

				System.out.println("ERROR " + result.toString());
				
				m1.addAttribute("user", user);
				return "signup";
			}

			
			
			user.setEnabled(true);
			user.setRole("ROLE_MYUSER");
			user.setImageUrl("default.png");

			// (For encode user's password encoder..)
			// user.setPassword(passwordEncoder.encode(user.getEmail()));//for setting to
			// user
			// password into user field and encoding by password encoder to that
			// (Means user will register password as encoded)

			User rs = this.userRepository.save(user);

			 System.out.println(rs);

			m1.addAttribute("user", new User());// for sending new data.(means if input data is well formed then the
												// data will not be placed automatically back because it will be new
												// as user object)

			session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
			return "signup"; // "alert-success"==type of message

		} catch (Exception e) {
			e.printStackTrace();
			m1.addAttribute("user", user);// for sending back that data which has come(means if input data is malformed
											// then remaining correct data will be placed automatically back).
			session.setAttribute("message", new Message("Something went wrong @@  " + e.getMessage(), "alert-danger"));// apply
																														// a
																														// message
																														// in
																														// session
			return "signup"; // e.getMessage()==for get message from message class that is sent by 'throw'.
		} // "alert-danger"==type of message

	}

}
