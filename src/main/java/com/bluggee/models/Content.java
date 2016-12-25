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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Indexed
public class Content {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    
    
    @Field
	private String title;
    
    @Field
 	private String description;
    
    @Field
	private String image;
    
    @Field
   	private String url;
    
    
    @Field
   	private String originalUrl;
    
    @Field
    private String uniqueId;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="source_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private BlogSource source;
	
	public Content() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BlogSource getSource() {
		return source;
	}

	public void setSource(BlogSource source) {
		this.source = source;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	
	
	
	
}
