var isLoading=false
var page = 1;
var stillMore=true;

var markup = $('#pin_template').html();
$.template( "contentTemplate", markup );


var mk = $('#ads_template').html();
$.template( "adTemplate", mk );


function setFormFields(){
	var cookies = getCookie("search_item");
	var ids = cookies.split("-");
	for(var i = 0 ; i < ids.length; i++){
		var chck = document.getElementById("cat_"+ids[i]);
		if(chck){
			chck.checked=true;
		}
	}
}

$(document).ready(function() {
	
	
	setFormFields();
	var win = $(window);
	// Each time the user scrolls
	
	
	
	
	
	
	win.on('scroll',function() {
		
		
		
		
		if(stillMore){
		// End of the document reached?
			//console.log(win.scrollTop());
			//console.log(win.height());
			//console.log($(document).height());
//			if(win.scrollTop() >= ($('#wrapper').height() - ($('#wrapper').position().top  + 200 )     )) {
////				console.log("win top" +win.scrollTop());
////				console.log("div top" + $('#wrapper').height());
////				console.log("div top" +$('#wrapper').position().top);
//				doLoad(true);
//		    }
			if ( win.scrollTop() >= $(document).height() - (win.height()+50)) {
				doLoad(true);
			}
		}
	});
});




function setLoadingViews(fromScrolling, isLoading){
	if(isLoading){
		if(fromScrolling){
			$('#loading').show();
		}else{
			$('#loading_big').show();
		}
	}else{
		if(fromScrolling){
			$('#loading').hide();
		}else{
			$('#loading_big').hide();
		}
		
	}
}



function doLoad(fromScrolling){
	var seconds = new Date().getTime();
	if(!isLoading){
		isLoading=true;
		setLoadingViews(fromScrolling, isLoading);
		$.ajax({
			url: 'api/list?page='+page+"&time="+seconds,
			dataType: 'json',
			success: function(data) {
				isLoading=false;
				setLoadingViews(fromScrolling, isLoading);
				
				
				
				
//				var script = document.createElement( 'script' );
//				
//				var ads_content = document.createElement("div");
//				ads_content.className = "ads_content";
//				
//				var center = document.createElement("center");
//				center.appendChild(script);
//				
//				
//				ads_content.appendChild(center);
//				
//				var container = document.getElementById("wrapper");
//				container.appendChild(ads_content);
//			
//				
//				
//				if(data.ad.type){
//					script.type = data.ad.type;
//				}
//				
//				if(data.ad.src){
//					script.src = data.ad.src;
//				}
//				
//				if(data.ad.async){
//					script.async = data.ad.async;
//				}
//				
//				if(data.ad.dataCfasync){
//					script.dataCfasync = data.ad.dataCfasync;
//				}
				
				
//				var output = $( "#ad_template" ).tmpl( data.ad ).html();
//				var container = document.getElementById("wrapper");
//				var content = document.createElement("div");
//				content.className = "ads_content";
//				content.innerHTML = output;
//				container.appendChild(content);
				
				
				
				var div = $('<div />')
				div.attr('class', 'columns');
				$.tmpl( "contentTemplate", data.contents).appendTo(div);
				div.appendTo( "#wrapper" );
				
				
//				var adiv = $('<div />')
//				adiv.attr('class', 'ads_content');
//				adiv.attr('id', seconds);
//				
//				$.tmpl( "adTemplate", data.ad).appendTo(adiv);
//				adiv.appendTo( "#wrapper" );
//				
//				
//				 
//				setTimeout(function() { 
//					 	$('#'+seconds+" script").each(function (index, element) { 
//					 		element.id = seconds+"scr";
//					 		$('#'+seconds+"scr").load(data.ad.src );
//					})
//					
//				 
//				 
//				 }, 20000);
				
				
//				$.tmpl( "adTemplate", data.ad).appendTo(adiv);
//				adiv.appendTo( "#wrapper" );
//				 $('#'+seconds+" script").each(function (index, element) { 
//					 element.setAttribute('src', data.ad.src);
//					 
//					 if(data.ad.type){
//						 element.type = data.ad.type;
//					 }
//					 if(data.ad.async){
//						 element.async = data.ad.async;
//					 }
//					 if(data.ad.dataCfasync){
//						 element.dataCfasync = data.ad.dataCfasync;
//					 }
//					 
//				 })
				 
				
				 //setTimeout(function() { 
					// $('#wrapper script').each(function (index, element) { element.src = element.src; })
				 
				// }, 20000);
				
				if(data.contents.length >= 20){
					stillMore = true;
					page+=1;
				}else{
					stillMore=false;
				}
			},
			error: function(xhr, error){
				isLoading=false;
				setLoadingViews(fromScrolling, isLoading);
		   }
		});
		
	}
}


function filter(){
	reset();
	
	items = [];
	$('.cat_check:checked').each(function () {
		   console.log(this.checked);
	       var val = $(this).val();
	       items.push(val);
	});
	
	var cookie = "";
	if(items.length > 0){
		for(var i = 0; i < items.length; i++){
			if(i < items.length - 1){
				cookie += items[i]+"-";
			}else{
				cookie += items[i];
			}
			
		}
	}
	setCookie("search_item", cookie, 1000)
	doLoad(false);
	$('#filterModal').modal('hide');
}




function reset(){
	page = 0;
	stillMore=true;
	$('#wrapper').html("");
	
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}




function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}