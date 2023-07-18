package com.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timeline")
public class PostController {
	
	@GetMapping("/timeline_view")
	public String postListView(Model model) {
		model.addAttribute("view","post/timeline");
		return "template/layout";
	}
}
