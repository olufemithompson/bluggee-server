package com.bluggee.controllers;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluggee.models.Ads;
import com.bluggee.models.BlogCategory;
import com.bluggee.models.Content;
import com.bluggee.models.SearchTerm;
import com.bluggee.repository.AdsRepository;
import com.bluggee.repository.BlogCategoryRepository;
import com.bluggee.repository.BlogSourceRepository;
import com.bluggee.repository.ContentRepository;
import com.bluggee.repository.SearchTermRepository;
import com.bluggee.search.SearchTermSearch;
import com.bluggee.utils.Util;
import com.bluggee.utils.reponse.CategoryResponse;

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
	 AdsRepository adsRepository;
	 
	 
	 @Autowired
	 private SearchTermSearch searchTermSearch;
	 
	 
	 @Value("${sitemapDirectory}")
	 String sitemapDirectory;
	 
	 
	 @Value("${rssDirectory}")
	 String rssDirectory;
	 
	  /**
	   * Index main page.
	   */
	  @RequestMapping("/")
	  public String index(
			  
			  @RequestParam(value="page", required=false)Long page, 
			  @CookieValue(value="cat_item", required=false) String searchItem, 
			  Model model,
			  HttpServletRequest request) {
		  
		  PageRequest pageable = new PageRequest(0,20);
		  List<Long> ids = Util.returnIdsFromString(searchItem);
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
		  
		  Iterable<BlogCategory> categories = bgrepository.findAll(); 
		  List<CategoryResponse> responses = new ArrayList<CategoryResponse>();
		  for(BlogCategory cat : categories){
			  CategoryResponse resp = new CategoryResponse();
			  resp.setCategory(cat);
			  resp.setSources(srepository.listByCat(cat.getId()));
			  responses.add(resp);
			  
		  }
		  model.addAttribute("posts", posts);
		  model.addAttribute("hasCat", (ids.size() > 0));
		  model.addAttribute("last", posts.get(posts.size()-1).getId());
		  model.addAttribute("categories", responses);
		  
//		  long count = adsRepository.countAds();
//		  if(count > 0){
//			  int index = new Random().nextInt((int)count);
//			  
//			  PageRequest apageable = new PageRequest(index,1);
//			  Ads ad = adsRepository.random(apageable).get(0);
//			  model.addAttribute("ad", ad);
//		  }
		  return "index";
	  }
	  
	  
	  @RequestMapping("/privacy")
	  public String privacy(Model model) {
		  return "privacy";
	  }
	  
	  
	  
	  @RequestMapping("/sitemap{sitemapFile}")
	   public void downloadSitemap( HttpServletRequest request, HttpServletResponse response,  @PathVariable("sitemapFile") String fileName) throws IOException {
		 response.setContentType("application/xml"); 
		 InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(sitemapDirectory, "sitemap"+fileName+".xml")));
		 FileCopyUtils.copy(inputStream, response.getOutputStream());
		  
	   }
	  
	  
	  
	  @RequestMapping("/rss")
	   public void downloadRss( HttpServletRequest request, HttpServletResponse response) throws IOException {
	     response.setContentType("application/rss+xml"); 
		 InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(rssDirectory, "rss.rss")));
		 FileCopyUtils.copy(inputStream, response.getOutputStream());
	    }
	  
	  
	  
	  /**
	   * Index main page.
	   */
	  @RequestMapping("/post/{uniqueId}/{title}")
	  public String showpost(@PathVariable("uniqueId")String uniqueId, @PathVariable("title")String title,Model model) {
		  PageRequest apageable = new PageRequest(0,1);
		  model.addAttribute("post", repository.findByUniqueId(apageable,uniqueId).get(0));
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

