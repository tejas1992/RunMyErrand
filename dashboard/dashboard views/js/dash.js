$(document).ready(function(){
	//This code gets executed as soon as the page loads. 
	$('#tableDemo').dataTable( {
  "lengthMenu": [ [5,10, 25, 50, -1], [5,10, 25, 50, "All"] ]
                               });
	$('#membertable').dataTable();
	$('#tasktable').dataTable();
	$('#taskassign').dataTable();
});