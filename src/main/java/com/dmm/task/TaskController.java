package com.dmm.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.task;
import com.dmm.task.data.repository.taskRepository;
import com.dmm.task.form.taskForm;
import com.dmm.task.service.AccountUserDetails;

@Controller
public class TaskController {

	@Autowired
	private taskRepository repo;

	/**
	 * 投稿の一覧表示.
	 * 
	 * @param model モデル
	 * @return 遷移先
	 */
	@GetMapping("/task")
	public String task(Model model) {
		// 逆順で投稿をすべて取得する
		List<task> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
//    Collections.reverse(list); //普通に取得してこちらの処理でもOK
		model.addAttribute("task", list);
		taskForm taskForm = new taskForm();
		model.addAttribute("taskForm", taskForm);
		return "/task";
	}

	/**
	 * 投稿を作成.
	 * 
	 * @param taskForm 送信データ
	 * @param user     ユーザー情報
	 * @return 遷移先
	 */
	@PostMapping("/task/create")
	public String create(@Validated taskForm taskForm, BindingResult bindingResult,
			@AuthenticationPrincipal AccountUserDetails user, Model model) {
		// バリデーションの結果、エラーがあるかどうかチェック
		if (bindingResult.hasErrors()) {
			// エラーがある場合は投稿登録画面を返す
			List<task> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
			model.addAttribute("task", list);
			
			model.addAttribute("taskForm", taskForm);
			return "/task";
		}

		task task = new task();
		task.setName(user.getName());
		task.setTitle(taskForm.getTitle());
		task.setText(taskForm.getText());
		task.setDate(LocalDateTime.now());

		repo.save(task);

		return "redirect:/task";
	}

	/**
	 * 投稿を削除する
	 * 
	 * @param id 投稿ID
	 * @return 遷移先
	 */
	@PostMapping("/task/delete/{id}")
	public String delete(@PathVariable Integer id) {
		repo.deleteById(id);
		return "redirect:/task";
	}
	
	// gecalendarメソッドを追加
	@GetMapping("/calendar")
	public String getcalendar(Model model) {
		// Modelに空のUserFormを追加
		taskForm userForm = new taskForm();
		model.addAttribute("userForm", userForm);
		// テンプレートは src/main/resources/templates/newuser.html とします。
		return "calendar";
	}
}