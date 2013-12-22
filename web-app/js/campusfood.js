$(document).ready(function(){
	$("#meal-date").change(fetchMealsByDate).change()
});

function fetchMealsByDate(){
	
	$.ajax({
		  url: baseURL + 'meal/date/' + $("#meal-date").val(),
		  dataType: 'html',
		  success: function(html){
		    $('#meals').html(html);
		  }
	});
}