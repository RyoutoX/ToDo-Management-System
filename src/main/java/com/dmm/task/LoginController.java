
package com.dmm.task;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.entity.User;
import com.dmm.task.data.repository.UsersRepository;




@Controller
public class LoginController {
	
	//リポジットをインポート
	@Autowired
	private UsersRepository UsersRepository;

	//つながるようにするコード
	//ユーザー情報を取得する
	@GetMapping("/loginForm")
	public String getUsers(Model model) {
	List<User> users = UsersRepository.findAll();
	model.addAttribute("users", users);

		return "login";
	
	}
}
