 $("#login-button").click(function(event){
     clicked(event);
 });

// $("#login-button").click(function click (event){
//		 event.preventDefault();
//	 
//         
//	 $('form').fadeOut(500);         
//	 $('.wrapper').addClass('form-success');
//});


//function validateForm() {
//    var x = document.forms["myForm"]["fname"].value;
//    if (x === null || x === "") {
//        alert("Name must be filled out");
//        return false;
//    }
//}
////
//function click (event){
//		 event.preventDefault();
//	 
//         
//	 $('form').fadeOut(500);         
//	 $('.wrapper').addClass('form-success');
//}


function clicked (event){
		 event.preventDefault();
	 
         
	 $('form').fadeOut(500);         
	 $('.wrapper').addClass('form-success');
}