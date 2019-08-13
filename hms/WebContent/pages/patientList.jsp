<!DOCTYPE html>
<%@page import="com.HMS.commonUtills.Status"%>
<html lang="en">

<head>
	<%@include file="./common/commonLib.jsp"%>	
</head>

<body>

    <div id="wrapper">

        <%@include file="./common/header.jsp"%>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-10">
                    <h1 class="">Patient List</h1>
                </div>
                <div class="col-lg-2 panel-body">
                	<a href="./patientAdd.jsp">
								<span class="pull-left btn btn-primary" style="margin-top: 10px;"><i class="fa fa-plus" style="margin-right: 10px;"></i>Add Patient</span>
								<span class="clearfix"></span>
						</a>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row" >
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive" style="overflow-x: hidden;">
                                <table class="table center table-hover table-bordered " id="patientListTable">
                                    <thead class="bordered-blueberry">
                                        <tr>
                                            <th class="col-xs-1 text-center">#</th>
                                            <th class="col-xs-3 text-center">Name</th>
                                            <th class="col-xs-1 text-center">Gender</th>
                                            <th class="col-xs-2 text-center">Email</th>
                                            <th class="col-xs-1 text-center">Phone</th>
                                            <th class="col-xs-2 text-center">BloodGroup</th>
                                            <th class="col-xs-2 text-center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="patientListTableBody" class="scrollContent">
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
		</div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
<script>
    $(document).ready(function() {
    	initialiseVariables();
    	$('#dialog').hide();
    });
    
    function initialiseVariables(){
//     	$( "#dialog" ).dialog('close');
    	counter = 0;
    	patientMap={};
        loadData();	
       
    }
   	
    function loadData(){
    	
    	$.ajax({
    		method: "post", 
    		url: "PatientGetData", 
    		dataType: "json", 
    		
    		success: function(response) {
// 	           	alert(JSON.stringify(response));
    			setDataOnTable(response);
    		},
    		error: function(response) { 
    			alert(response); 
    		} 
    	});   
    }
    
    function setDataOnTable(response){
    	//console.log(JSON.stringify(response));
    	 $("#patientListTable").dataTable().fnDestroy();
         var tableBodyhtml=""; 
         $.each(response, function() {
             tableBodyhtml += getHtml(this);
         });
         $("#patientListTableBody").html(tableBodyhtml);
         $('#patientListTable').dataTable({
             //It is used for Not Displaying search option on Header
//              "sDom" : "t<'row'<'col-xs-6'i><'col-xs-6'p>>",
//              "scrollCollapse": true,
//              "iDisplayLength": 10,
//              "bSort": false,
             "aoColumns": [
                           { "bSortable": true},
                           { "bSortable": true},
                           { "bSortable": true},
                           { "bSortable": true},
                           { "bSortable": true},
                           { "bSortable": true},
                           { "bSortable": false}
                       ]
//              "order": [[ 0, "desc"]]
             //for scroll x
//              "bScrollCollapse": false
             
         });
    	
    } 
    function getHtml(patient) {
        
    	//console.log("LL "+patient)
     	patientMap[patient.pid] = patient;
        counter = counter + 1;
        
        var actionButtons = "";
        
        var detailButton = ' <button type="button" title="View Details" onClick="DetailAction(\''+ patient.pid+ '\');"'
        + 'class="btn btn-warning btn-circle"><i class="fa fa-eye"></i></button>';
        
        var visitButton = ' <button type="button" title="Add visit" onClick="VisitAction(\''+ patient.pid+ '\');"'
        + 'class="btn btn-success btn-rectangle"><i class="fa fa-plus-circle"></i></button>';
        
        var editButton = ' <button type="button" title="Edit Details" onClick="EditAction(\''+ patient.pid+ '\');"'
        + 'class="btn btn-info btn-sm shiny icon-only azure"><i class="fa fa-pencil-square-o"></i></button>';
        
        var deleteButton = ' <button type="button" title="Delete Patient" onClick="DeleteAction(\''+ patient.id+ '\');"'
        + 'class="btn btn-danger btn-circle"><i class="fa fa-times-circle"></i></button>';
        
        
        actionButtons += detailButton + visitButton + editButton + deleteButton;
        
        
        var html = '<tr>' 
                + '<td  class="text-center">' + counter + '</td>'
                + '<td class="text-center">' + patient.fname+'&nbsp'+patient.lname  + '</td>'
                + '<td class="text-center">' + patient.gender + '</td>'
                + '<td class="text-center">' + patient.email + '</td>'
                + '<td class="text-center">' + patient.phone + '</td>'
                + '<td class="text-center">' + patient.bloodgroup + '</td>'
                + '<td class="text-center">' + actionButtons + '</td>'
                + '</tr>';
        return html;
    }
    
    function patientEditAction(id){
    	window.location.href = "./patientAdd.jsp?id="+id;
    }
    
    function VisitAction(id){
    	
    	data = {};
    	console.log(id);
    	data.pid = id;
    	data.status = "OPEN";
        data.user = <%= request.getSession().getAttribute("user")%>  
        data.userid = data.user.id;
        
    	$.ajax({
   		 method: "post",
   		 url: "VisitAdd",
   		 dataType: "json",
   		 data: data,
   		 success:function(response){
   			 //alert(JSON.stringify(response));
   			 
   			 $.toast({
       			    text: response,
       			    position: 'bottom-right',
       			    icon: 'success',
       			    hideAfter: 3000,
       			    loader: false 
       			});
       			setTimeout(
       					  function() 
       					  {
   				window.location.href = "./visitList.jsp"
       					  }, 1000);
   		 },
   		 error:function(response){
   			 console.log(JSON.stringify(response));
   			 $.toast({
       			    text: response.responseJSON,
       			    position: 'bottom-right',
       			    icon: 'error',
       			    hideAfter: 3000,
       			    loader: false 
       			});
   		 }
   	 });	
    	
    }
    
	function DetailAction(id){
	    	
 	    	var data = patientMap[id];
			
 	    	$('#name').text(data.fname+" "+data.mname+" "+data.lname);
 	    	$('#email').text(data.email);
 	    	$('#gender').text(data.gender);
 	    	$('#bloodgroup').text(data.bloodgroup);
 	    	$('#phone').text(data.phone);
 	    	$('#dob').text(data.dob);
 	    	$('#dor').text(data.dor);
 	    	$('#address').text(data.address);
 	    	
		    
 	  $( "#dialog" ).dialog(
 		{
 			width:700,
 			height:300,
 		});
 	  	
	}
    
    </script>    
	
	
	
	<div id="dialog">
	
	<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                           <form role="form" id="patientForm">
                           
                            <div class="row">
                                <div class="col-lg-6">
                                  <div class="form-group">
                                  		<label>Name: &nbsp</label><span id="name"></span>
                                  </div>
                                </div> 
                                <div class="col-lg-6">
                                  <div class="form-group">
                                  		<label>Email: &nbsp</label><span id="email"></span>
                                  </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-lg-6">
                                  <div class="form-group">
                                  		<label>Date of Birth: &nbsp</label><span id="dob"></span>
                                  </div>
                                </div> 
                                <div class="col-lg-6">
                                  <div class="form-group">
                                  		<label>Date of Registration: &nbsp</label><span id="dor"></span>
                                  </div>
                                </div>
                            </div>
                            
                             <div class="row">
                                <div class="col-lg-4">
                                  <div class="form-group">
                                  		<label>Gender: &nbsp</label><span id="gender"></span>
                                  </div>
                                </div> 
                                <div class="col-lg-4">
                                  <div class="form-group">
                                  		<label>Bloodgroup: &nbsp</label><span id="bloodgroup"></span>
                                  </div>
                                </div>
                                <div class="col-lg-4">
                                  <div class="form-group">
                                  		<label>Phone: &nbsp</label><span id="phone"></span>
                                  </div>
                                </div>
                            </div>
                             
                            <div class="row">
                                <div class="col-lg-12">
                                  <div class="form-group">
                                  		<label>Address: &nbsp</label><span id="address"></span>
                                  </div>
                                </div> 
                            </div>
                             
                          </form> 
                        </div>   
                	  </div>
                    </div>
                  </div>
      </div>
      
           
   

	</body>

</html>
