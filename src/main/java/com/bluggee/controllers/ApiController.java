package com.bluggee.controllers;
import java.util.List;
import java.util.Random;

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

import com.bluggee.models.Ads;
import com.bluggee.models.BlogSource;
import com.bluggee.models.Content;
import com.bluggee.models.SearchTerm;
import com.bluggee.repository.AdsRepository;
import com.bluggee.repository.BlogCategoryRepository;
import com.bluggee.repository.BlogSourceRepository;
import com.bluggee.repository.ContentRepository;
import com.bluggee.repository.SearchTermRepository;
import com.bluggee.search.SearchTermSearch;
import com.bluggee.utils.Util;
import com.bluggee.utils.reponse.WebResponse;

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
	 
	 @Autowired
	 AdsRepository adsRepository;
	 
	  /**
	   * Index main page.
	   */
	  @RequestMapping("/api/list")
	  public  @ResponseBody WebResponse index(
			  @RequestParam(value="page", required=false)Long page, 
			  @CookieValue(value="search_item", required=false) String searchItem,  
			  Model model) {
		  
		  PageRequest pageable = new PageRequest(0,20);
		  List<Long> ids = Util.returnIdsFromString(searchItem);
		  
		  WebResponse response  = new WebResponse();
		  
		  List<Content> posts = null;
		  if(ids.size() > 0){
			  if(page == null){
				  posts  = repository.list(pageable, ids);
				  
			  }else{
				  posts  = repository.list(pageable, ids, page);
			  }
			 
		  }else{
			  if(page == null){
				  posts  = repository.list(pageable);
			  }else{
				  posts  = repository.list(pageable, page);
				  
			  }
		  }
		  response.setContents(posts);
		  long count = adsRepository.countAds();
		  if(count > 0){
			  int index = new Random().nextInt((int)count);
			  
			  PageRequest apageable = new PageRequest(index,1);
			  Ads ad = adsRepository.random(apageable).get(0);
			  response.setAd(ad);
		  }
		  
		  return response;
	  }
	  
	  
	  /**
	   * Index main page.
	   */
	  @RequestMapping("/api/source")
	  public  @ResponseBody Iterable<BlogSource> source(
			   Model model) {
		 return srepository.findAll();
	  }
	
}

