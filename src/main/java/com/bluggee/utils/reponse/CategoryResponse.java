package com.bluggee.utils.reponse;

import java.util.ArrayList;
import java.util.List;

import com.bluggee.models.BlogCategory;
import com.bluggee.models.BlogSource;

public class CategoryResponse {
	BlogCategory category;
	List<BlogSource> sources = new ArrayList<BlogSource>();
	public BlogCategory getCategory() {
		return category;
	}
	public void setCategory(BlogCategory category) {
		this.category = category;
	}
	public List<BlogSource> getSources() {
		return sources;
	}
	public void setSources(List<BlogSource> sources) {
		this.sources = sources;
	}
	
	
	
}
