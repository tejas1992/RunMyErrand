$(document).ready(function () {
$('#dob').datepicker({
	format:"yyyy-mm-dd"
});
$('#startDate').datepicker({
	format:"yyyy-mm-dd"
});
$('#endDate').datepicker({
	format:"yyyy-mm-dd"
});
});
function functionCopyAtoB() {
    var a = $('#inputsa').val();
    var n = a.lastIndexOf("|");
    var res = a.substring(0, n);
    var res2 = a.substring(n+1);    
    $('#taskDescription').val(res);
    $('#points').val(res2);    
}