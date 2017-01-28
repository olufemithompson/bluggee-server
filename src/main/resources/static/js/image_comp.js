function checkImage(img){
		var width = img.naturalWidth;
		var height = img.naturalHeight;
		var parentWidth = img.parentNode.offsetWidth - 3;
		
		if(width > height){
			var new_height = (parentWidth * height)/width;
			
			
			if(new_height < 200){
				img.style.height = new_height+'px';
				img.style.width = parentWidth+'px';
				
				var dif = (197 - new_height)/2;
				img.parentNode.style.paddingTop=dif+"px";
				img.parentNode.style.paddingLeft = "1px";
			}else{
				
				var new_width = (197 * width)/height;
				img.style.width = new_width+'px';
				img.style.height = '197px';
				
				var dif = (parentWidth - new_width)/2;
				img.parentNode.style.paddingLeft = dif+"px";
				img.parentNode.style.paddingTop="1px";
				
				
			}
			
		
		}else{
			var new_width = (197 * width)/height;
			img.style.width = new_width+'px';
			img.style.height = '197px';
			
			var dif = (parentWidth - new_width)/2;
			img.parentNode.style.paddingLeft = dif+"px";
			img.parentNode.style.paddingTop="1px";
			
		}
		img.style.display="block";
		document.getElementById(img.id+"_load").style.display="none";
	    
	}
