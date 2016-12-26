var isLoading=false
var page = 0;
var stillMore=true;

var markup = $('#pin_template').html();
$.template( "contentTemplate", markup );


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
			console.log("scroll top " + win.scrollTop())
			console.log("doc height " +  $(document).height())
			console.log("win height " + win.height())
			if ( win.scrollTop() >= $(document).height() - win.height()) {
				console.log("has reached last page");
				doLoad(true);
			}
		}
	});
});




function setLoadingViews(fromScrolling, isLoading){
	if(isLoading){
		if(fromScrolling){
			$('#loading').show();
		}
	}else{
		if(fromScrolling){
			$('#loading').hide();
		}
		
	}
}



function doLoad(fromScrolling){
	
	if(!isLoading){
		isLoading=true;
		setLoadingViews(fromScrolling, isLoading);
		$.ajax({
			url: 'api/list?page='+page,
			dataType: 'json',
			success: function(data) {
				isLoading=false;
				setLoadingViews(fromScrolling, isLoading);
				console.log(data.length)
				$.tmpl( "contentTemplate", data ).appendTo( "#columns" );
				if(data.length >= 20){
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
	$('#columns').html("");
	
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