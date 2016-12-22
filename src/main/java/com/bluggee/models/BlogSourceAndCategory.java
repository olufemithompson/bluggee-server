package com.bluggee.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class BlogSourceAndCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="source_id")
   	private BlogSource source;
    
    
    
    @ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="category_id")
   	private BlogCategory category;
    
	public BlogSourceAndCategory() {
		super();
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public BlogSource getSource() {
		return source;
	}



	public void setSource(BlogSource source) {
		this.source = source;
	}



	public BlogCategory getCategory() {
		return category;
	}



	public void setCategory(BlogCategory category) {
		this.category = category;
	}



	



	

	
	
	
	
	
	
}
