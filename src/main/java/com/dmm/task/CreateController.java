
package com.dmm.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.repository.taskRepository;


@Controller
public class CreateController {
	
	@Autowired
	private taskRepository taskRepository;
	
	@GetMapping("/create")
	public String create() {
		return "create";
	}
	

}
