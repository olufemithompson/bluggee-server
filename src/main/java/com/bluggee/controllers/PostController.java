package com.bluggee.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import com.ledisi.how2.models.Post;
//import com.ledisi.how2.models.Tag;
//import com.ledisi.how2.repository.PostRepository;
//import com.ledisi.how2.repository.StepRepository;
//import com.ledisi.how2.repository.TagRepository;

@Controller
public class PostController {

//	@Autowired
//	PostRepository postRepository;
//
//	@Autowired
//	StepRepository stepRepository;
//	
//    @Autowired
//	TagRepository repository;
//	
//	@RequestMapping("/post/add")
//	public String index(Model model) {
//		model.addAttribute("post", new Post());
//		model.addAttribute("tags", repository.findAll());
//		
//		return "addpost";
//	}
//	
//
//	@RequestMapping(value = "/post/save", method = RequestMethod.POST)
//	public String developersAdd(@RequestParam String title,
//			@RequestParam String summary, @RequestParam List<Long> tags, Model model) {
//		Post newPost = new Post();
//		newPost.setTitle(title);
//		newPost.setSummary(summary);
//		newPost.setDateCreated(new Date());
//		newPost.setTags(new ArrayList<Tag>());
//		for(Long l : tags){
//			newPost.getTags().add(repository.findOne(l));
//		}
//		
//		postRepository.save(newPost);
//		return "redirect:/post/" + newPost.getId();
//	}
//
//	
//	@RequestMapping("/post/{id}")
//	public String viewPost(@PathVariable Long id, Model model) {
//		Post post = postRepository.findOne(id);
//		post.setTotalViews(post.getTotalViews()+1);
//		postRepository.save(post);
//		model.addAttribute("post", post);
//		model.addAttribute("steps", stepRepository.findByPost(post));
//		return "post";
//	}
}
