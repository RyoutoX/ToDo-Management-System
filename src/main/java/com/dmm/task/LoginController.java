
package com.dmm.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.repository.UsersRepository;


@Controller
public class LoginController {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/loginForm")
	public String login() {
		return "login";
	}

}
