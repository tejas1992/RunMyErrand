$(document).ready(function () {
$('#dob').datepicker({
	format:"yyyy-mm-dd"
});
$('#signupLink').click(function(){
	$("#myModal").modal({backdrop:false});
	$('#myModal').show();
});  
});
