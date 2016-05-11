/**
 * Show spinner
*/
function loading(message){
$(".loader").html("<div class='box box--foo'><p>"+message+"</p><p><span class='spinner'>Loading...</span></p></div>");
}
/**
 * Show event message
 */
function showmessage(title,text,type){
	switch (type) {
    case "success":
    	$.pnotify({
  		  title: title,
  		  text: text,
  		  type: type,
  		  width:"390px",
        icon:true,
        before_open: function(pnotify){
  		    pnotify.css({
  		      "top":"10%", 
  		      "left": ($(window).width() / 2) - (pnotify.width() / 2),
  		    });
  		  }
  		});
        break;
    case "error":
    	$.pnotify({
  		  title: title,
  		  text: text,
  		  type: type,
  		  addclass: "alert-danger",
  		  width:"390px",
  		  before_open: function(pnotify){
  		    pnotify.css({
  		      "top":"10%", 
  		      "left": ($(window).width() / 2) - (pnotify.width() / 2),
  		    });
  		  }
  		});
        break;
    }	
}
/**
  *read cookie value(JSESSIONID)
*/
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}
/**
 * Logout user
 * */
function logout(){
 window.location.reload();
}

