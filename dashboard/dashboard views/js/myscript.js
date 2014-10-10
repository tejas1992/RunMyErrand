$(document).ready(function () {
$('#dob').datepicker({
    format: "dd/mm/yyyy"
});
$('#signupLink').click(function(){
	$("#myModal").modal({backdrop:false});
	$('#myModal').show();
});  
});
