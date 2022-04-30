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

import com.dmm.task.data.entity.tasks;
import com.dmm.task.data.repository.taskRepository;
import com.dmm.task.form.taskForm;
import com.dmm.task.service.AccountUserDetails;

@Controller
public class MainController {

	@Autowired
	private taskRepository repo;

	/**
	 * 投稿の一覧表示.
	 * 
	 * @param model モデル
	 * @return 遷移先
	 */
	@GetMapping("/main")
	public String posts(Model model) {
		// 逆順で投稿をすべて取得する
		List<tasks> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
//    Collections.reverse(list); //普通に取得してこちらの処理でもOK
		model.addAttribute("task", list);
		taskForm taskForm = new taskForm();
		model.addAttribute("taskForm", taskForm);
		return "/main";
	}

	/**
	 * 投稿を作成.
	 * 
	 * @param taskForm 送信データ
	 * @param user     ユーザー情報
	 * @return 遷移先
	 */
	
	@GetMapping("/main/create")
	public String create(@Validated taskForm taskForm, BindingResult bindingResult,
			@AuthenticationPrincipal AccountUserDetails user, Model model) {
		// バリデーションの結果、エラーがあるかどうかチェック
		if (bindingResult.hasErrors()) {
			// エラーがある場合は投稿登録画面を返す
			List<tasks> list = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
			model.addAttribute("task", list);
			model.addAttribute("taskForm", taskForm);
			return "/create";
		}
	

		
	    tasks task = new tasks();
		task.setName(task.getName());
		task.setTitle(task.getTitle());
		task.setText(task.getText());
		task.setDate(LocalDateTime.now());
        task.setDone(false);
        task.setId(null);
		repo.save(task);

		return "redirect:/task";
		}
	


	/**
	 * 投稿を削除する
	 * 
	 * @param id 投稿ID
	 * @return 遷移先
	 */
	@PostMapping("/mian/delete/{id}")
	public String delete(@PathVariable Integer id) {
		repo.deleteById(id);
		return "redirect:/main";
	}

}