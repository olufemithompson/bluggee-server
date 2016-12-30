function resizer(id)
{

var doc=document.getElementById(id).contentWindow.document;
var body_ = doc.body, html_ = doc.documentElement;

var height = Math.max( body_.scrollHeight, body_.offsetHeight, html_.clientHeight, html_.scrollHeight, html_.offsetHeight );
var width  = Math.max( body_.scrollWidth, body_.offsetWidth, html_.clientWidth, html_.scrollWidth, html_.offsetWidth );

document.getElementById(id).style.height=height;
document.getElementById(id).style.width=width;

}