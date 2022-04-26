
package com.dmm.task;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/loginForm")
	@PreAuthorize("hasRole('ROLE_ADMIN')") // 追記 ROLE_ADMINのユーザのみアクセスを許可
	String loginForm() {
		return "login";
	}
}
