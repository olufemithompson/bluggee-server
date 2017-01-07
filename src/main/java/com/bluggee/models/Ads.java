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
public class Ads {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    
    
    @Field
	private String content;
    
    
    @Field
	private String type;
    
    
    @Field
	private String src;
    
    
    @Field
	private String async;
    
    
    
    @Field
  	private String dataCfasync;
      
    
  
    public Ads() {
		super();
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getSrc() {
		return src;
	}



	public void setSrc(String src) {
		this.src = src;
	}



	public String getAsync() {
		return async;
	}



	public void setAsync(String async) {
		this.async = async;
	}



	public String getDataCfasync() {
		return dataCfasync;
	}



	public void setDataCfasync(String dataCfasync) {
		this.dataCfasync = dataCfasync;
	}




	
	
	
}
