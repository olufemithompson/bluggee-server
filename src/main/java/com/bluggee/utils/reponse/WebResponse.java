package com.bluggee.utils.reponse;

import java.util.List;

import com.bluggee.models.Ads;
import com.bluggee.models.Content;

public class WebResponse {
	
	List<Content> contents;
	Ads ad;
	public List<Content> getContents() {
		return contents;
	}
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	public Ads getAd() {
		return ad;
	}
	public void setAd(Ads ad) {
		this.ad = ad;
	}
	
	
}
