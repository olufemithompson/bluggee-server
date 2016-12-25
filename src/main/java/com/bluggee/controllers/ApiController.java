package com.bluggee.controllers;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluggee.models.Content;
import com.bluggee.models.SearchTerm;
import com.bluggee.repository.BlogCategoryRepository;
import com.bluggee.repository.BlogSourceRepository;
import com.bluggee.repository.ContentRepository;
import com.bluggee.repository.SearchTermRepository;
import com.bluggee.search.SearchTermSearch;
import com.bluggee.utils.Util;

@Controller
public class ApiController {

	 
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
	  @RequestMapping("/api/list")
	 
	  public  @ResponseBody List<Content> index(
			  @RequestParam(value="page", required=false)Integer page, 
			  @CookieValue(value="search_item", required=false) String searchItem,  
			  Model model) {
		  if(page == null){
			  page = 0;
		  }
		  PageRequest pageable = new PageRequest(page,20);
		  List<Long> ids = Util.returnIdsFromString(searchItem);
		  if(ids.size() > 0){
			  return repository.list(pageable, ids);
		  }else{
			  return repository.list(pageable);
		  }
	  }
	  
	  
//	  
//	  /**
//	   * Index main page.
//	   */
//	  @RequestMapping("/post/{uniqueId}/{title}")
//	  public Content showpost(@PathVariable("uniqueId")String uniqueId, @PathVariable("title")String title,Model model) {
//		  System.out.println(uniqueId);
//		  model.addAttribute("post", repository.findByUniqueId(uniqueId));
//		  return "content";
//	  }
//	  
	  
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
	  
//	  
//	  /**
//	   * Show search results for the given query.
//	   *
//	   * @param q The search query.
//	   */
//	  @RequestMapping("/loadterm")
//	  @ResponseBody
//	  public List<SearchTerm> loadterms(String term, Model model) {
//		List<SearchTerm> searchResults = null;
//	    try {
//	      searchResults = searchTermSearch.search(term);
//	    }
//	    catch (Exception ex) {
//	    	ex.printStackTrace();
//	    }
//	    return searchResults;
//	  }
	
}

