package com.bluggee.controllers;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluggee.models.SearchTerm;
import com.bluggee.repository.BlogCategoryRepository;
import com.bluggee.repository.BlogSourceRepository;
import com.bluggee.repository.ContentRepository;
import com.bluggee.repository.SearchTermRepository;
import com.bluggee.search.SearchTermSearch;

@Controller
public class MainController {

	 
	 @Autowired
	 SearchTermRepository searchRepository;
	 
	 
	 @Autowired
	 ContentRepository repository;
	 
	 
	 @Autowired
	 BlogCategoryRepository bgrepository;
	 
	 
	 @Autowired
	 BlogSourceRepository srepository;
	 
	 
	 @Autowired
	 private SearchTermSearch searchTermSearch;
	 
	  /**
	   * Index main page.
	   */
	  @RequestMapping("/")
	  public String index(Model model) {
		  model.addAttribute("posts", repository.findAll());
		  model.addAttribute("categories", bgrepository.findAll());
		  model.addAttribute("sources", srepository.findAll());
		  return "index";
	  }
	  
	  
	  
	  /**
	   * Index main page.
	   */
	  @RequestMapping("/post/{uniqueId}/{title}")
	  public String showpost(@PathVariable("uniqueId")String uniqueId, @PathVariable("title")String title,Model model) {
		  System.out.println(uniqueId);
		  model.addAttribute("post", repository.findByUniqueId(uniqueId));
		  return "content";
	  }
	  
	  
//	  /**
//	   * Show search results for the given query.
//	   *
//	   * @param q The search query.
//	   */
//	  @RequestMapping("/search")
//	  public String search(String q, Model model) {
//		 
//		if(q != null && !q.trim().isEmpty()){
//			  SearchTerm term = new SearchTerm();
//			  term.setTerm(q.trim());
//			  searchRepository.save(term);
//		}
//		
//		  
//	    List<Post> searchResults = null;
//	    try {
//	      searchResults = search.search(q);
//	    }
//	    catch (Exception ex) {
//	    	ex.printStackTrace();
//	    }
//	    model.addAttribute("posts", searchResults);
//	    return "search";
//	  }
	  
	  
	  /**
	   * Show search results for the given query.
	   *
	   * @param q The search query.
	   */
	  @RequestMapping("/loadterm")
	  @ResponseBody
	  public List<SearchTerm> loadterms(String term, Model model) {
		List<SearchTerm> searchResults = null;
	    try {
	      searchResults = searchTermSearch.search(term);
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    return searchResults;
	  }
	
}

