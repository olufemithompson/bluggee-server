package com.bluggee.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class BlogSource {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    
    
    @Field
	private String name;
    
    @Field
	private String image;
    
    @Field
   	private String link;
    
    
    @Field
   	private String description;
    

	
	
	public BlogSource() {
		super();
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}

	
	
	
}
