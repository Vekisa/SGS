$(document).on("click","#btn",function(){
	var text = $('#inp').val();
	
	$.ajax({
		type: "POST",
		url: "rest/osnovniServis/proba?text="+text,
		success:function(data){
			alert("POSLAO!");
			
		}
	});
});