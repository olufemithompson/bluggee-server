$('.fb').click(function(){
	elem = $(this);
	postToFeed(elem.data('title'), elem.data('desc'), elem.data('href'), elem.data('image'));
    return false;
});


$('.tw').click(function(event) {
	elem = $(this);
    var width  = 575,
        height = 400,
        left   = ($(window).width()  - width)  / 2,
        top    = ($(window).height() - height) / 2,
        url    = "http://twitter.com/share?url="+elem.data('href')+"&text="+elem.data('title'),
        opts   = 'status=1' +
                 ',width='  + width  +
                 ',height=' + height +
                 ',top='    + top    +
                 ',left='   + left;
    
    window.open(url, 'twitter', opts);
 
    return false;
  });


$('.g').click(function(event) {
	elem = $(this);
    var width  = 575,
        height = 400,
        left   = ($(window).width()  - width)  / 2,
        top    = ($(window).height() - height) / 2,
        url    = "https://plus.google.com/share?url="+elem.data('href'),
        opts   = 'status=1' +
                 ',width='  + width  +
                 ',height=' + height +
                 ',top='    + top    +
                 ',left='   + left;
    
    window.open(url, 'google', opts);
 
    return false;
  });


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

				var div = $('<div />')
				div.attr('class', 'columns');
				$.tmpl( "contentTemplate", data.contents).appendTo(div);
				div.appendTo( "#wrapper" );
				
				
//				$.tmpl( "contentTemplate", data.contents).appendTo($('.columns'));

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