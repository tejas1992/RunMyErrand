$(document).ready(function () {
$('#dob').datepicker({
	format:"yyyy-mm-dd"
});
$('#startDate').datepicker({
	format:"yyyy-mm-dd"
});
});
function reusableTasks() {
    var a = $('#inputsa').val();
    if (a == " ") {
    $('#taskDescription').val(res).prop("readOnly", false);
    $('#points').val(res2).prop("readOnly", false);  
    $('#number_of_days').val(res3).prop("readOnly", false);  
    }
    else{
    var n = a.indexOf("|");
    var m = a.lastIndexOf("|");    
    var res = a.substring(0, n);
    var res2 = a.substring(n+1,m);
    var res3 = a.substring(m+1);
    $('#taskDescription').val(res).prop("readOnly", true);
    $('#points').val(res2).prop("readOnly", true);  
    $('#number_of_days').val(res3).prop("readOnly", true);
    $('#flag').val("1");
    }

}