package com.bluggee.controllers;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluggee.models.BlogSource;
import com.bluggee.models.SearchTerm;
import com.bluggee.repository.BlogCategoryRepository;
import com.bluggee.repository.BlogSourceAndCategoryRepository;
import com.bluggee.repository.BlogSourceRepository;
import com.bluggee.repository.ContentRepository;
import com.bluggee.repository.SearchTermRepository;
import com.bluggee.search.SearchTermSearch;

@Controller
public class AdminController {

	 
	 @Autowired
	 BlogSourceAndCategoryRepository  blogSourceAndCategory;
	 
	 
	 @Autowired
	 BlogSourceRepository sourcerepository;
	 
	 
	 @Autowired
	 BlogCategoryRepository categoryrepository;
	 
	 
	@RequestMapping("/admin/sources")
	public String sources(Model model) {
		model.addAttribute("sources", sourcerepository.findAll());
		return "blogsources";
	}
	 
	
	@RequestMapping("/admin/showsource/{id}")
	public String showblogsource(@PathParam("id") Long id, Model model) {
		model.addAttribute("source", sourcerepository.findOne(id));
		//model.addAttribute("sourceCategories", blogSourceAndCategory);
		model.addAttribute("categories", categoryrepository.findAll());
		return "blogsources";
	}

	
	@RequestMapping("/admin/createsource")
	public String categories(Model model) {
		model.addAttribute("source", new BlogSource());
		model.addAttribute("categories", categoryrepository.findAll());
		return "createblogsource";
	}
	
	
	
	@RequestMapping("/admin/savesource")
	public String saveSource(@RequestParam String name, 
			@RequestParam String image, 
			@RequestParam String link, 
			@RequestParam String description, 
			@RequestParam List<Long> categories,
			Model model) {
		
		
		model.addAttribute("source", new BlogSource());
		model.addAttribute("categories", categoryrepository.findAll());
		return "showblogsource";
	}
	
}

