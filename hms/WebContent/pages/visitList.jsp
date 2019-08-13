<!DOCTYPE html>
<%@page import="com.HMS.commonUtills.Status"%>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>		
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
                    <h1 class="">Visit List</h1>
                </div>
                <div class="col-lg-2 panel-body">
                	<a href="./visitAdd.jsp">
								<span class="pull-left btn btn-primary" style="margin-top: 10px;"><i class="fa fa-plus" style="margin-right: 10px;"></i>Add Visit</span>
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
                                <table class="table center table-hover table-bordered " id="visitListTable">
                                    <thead class="bordered-blueberry">
                                        <tr>
                                            <th class="col-xs-1 text-center">#</th>
                                            <th class="col-xs-4 text-center">Name</th>
                                            <th class="col-xs-2 text-center">Status</th>
                                            <th class="col-xs-3 text-center">Date of Reg</th>
                                            <th class="col-xs-3 text-center">Action</th>
                                        </tr>
                                        
                                    </thead>
                                    <tbody id="visitListTableBody" class="scrollContent">
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
    	visitMap = {};
    });
    
    function initialiseVariables(){
    	counter = 0;
        loadData();	
//         loadBillData();
 //       loadService();
    }
   	
    function loadData(){
    	$('#dialog').hide();
    	$('#billdialog').hide();
    	$.ajax({
    		method: "post", 
    		url: "VisitGetData", 
    		dataType: "json", 
    		
    		success: function(response) {
//  	           	alert(JSON.stringify(response));
    			setDataOnTable(response);
    		},
    		error: function(response) { 
    			alert("Error occured"); 
    		} 
    	});   
    }
    
    function setDataOnTable(response){
    	 $("#visitListTable").dataTable().fnDestroy();
//     	 console.log(response);
         var tableBodyhtml=""; 
         $.each(response, function() {
             tableBodyhtml += getHtml(this);
         });
         $("#visitListTableBody").html(tableBodyhtml);
         $('#visitListTable').dataTable({
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
                           { "bSortable": false}
                       ]
//              "order": [[ 0, "desc"]]
             //for scroll x
//              "bScrollCollapse": false
             
         });
    	
    } 
    function getHtml(visit) {
        console.log("this is visit:   "+JSON.stringify(visit));
        counter = counter + 1;
        visitMap[visit.vid] = visit;
        console.log(visit.vstatus);
        
        var actionButtons = "";
        
        
        
        var billButton = ' <button type="button" id="billButton" title="Generate Bill" onClick="BillAction(\''+ visit.vid+ '\');"'
        + 'class="btn btn-success"><i class="fa fa-money"></i></button>';
       
        if(visit.vstatus == "OPEN")
        	{
        	 var addButton = ' <button type="button" id="addButton" title="Add Item" onClick="addService(\''+ visit.vid+ '\');"'
              + 'class="btn btn-info btn-sm shiny icon-only azure"><i class="fa fa-plus"></i></button>';
        	
		        var closeButton = ' <button type="button" id="closeButton" title="Close Visit" onClick="closeAction(\''+ visit.vid+ '\');"'
		        + 'class="btn btn-danger"><i class="glyphicon glyphicon-folder-close"></i></button>';
        	}else{
        		
        		 var addButton = ' <button type="button" id="addButton" title="Add Item" onClick="addService(\''+ visit.vid+ '\');"'
                 + 'class="btn btn-info btn-sm shiny icon-only azure"><i class="fa fa-plus"></i></button>';
        		
        		var closeButton = ' <button type="button" id="closeButton" disabled="disabled" title="Close Visit" onClick="closeAction(\''+ visit.vid+ '\');"'
		        + 'class="btn btn-danger "><i class="glyphicon glyphicon-folder-close"></i></button>';
        	}
        
        
        actionButtons += addButton + billButton + closeButton  ;
        
        
        var html = '<tr>' 
                + '<td  class="text-center">' + counter + '</td>'
                + '<td class="text-center">' + visit.patient.fname+'&nbsp'+visit.patient.lname  + '</td>'
                + '<td class="text-center">' + visit.vstatus + '</td>'
                + '<td class="text-center">' + visit.dov + '</td>'
                + '<td class="text-center">' + actionButtons + '</td>'
                + '</tr>';
        return html;
    }
    
    function closeAction(id){
// 		console.log(id);    	
    	var data = {}
    	data.id = id;
    	$.ajax({
   		 method: "post",
   		 url: "VisitClose",
   		 dataType: "json",
   		 data: data,
   		 success:function(response){
//    			 alert(JSON.stringify(response));
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
   			
   			 $.toast({
       			    text: "Something went wrong.",
       			    position: 'bottom-right',
       			    icon: 'error',
       			    hideAfter: 3000,
       			    loader: false 
       			});
   		 }
   	 });		
  }
    
    function addService(id){
     $('#hidden').val(id);
//      $('#closeButton').removeAttr("disabled");
//  	$('#closeButton').removeClass("disabled");
     loadService();		
	 $( "#dialog" ).dialog(
		{
			width:300,
			height:400,
		});
    }
	  	
    function addSelected(){
    	
    	var data = {}
    	data.json = [];
    	data.id = $('#hidden').val();
//     	console.log("visitid: "+data.id)
    	$('#service :selected').each(function(i, selected){ 
    	  data.json[i] = $(selected).val(); 
    	});
    	console.log("selected values: "+data.json);
    	
    	$.ajax({
   		 method: "post",
   		 url: "VisitAddItems",
   		 dataType: "json",
   		 data: data,
//    		 data: {json:json},
   		 success:function(response){
//    			 alert(JSON.stringify(response));
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
   			
   			 $.toast({
       			    text: "Something went wrong.",
       			    position: 'bottom-right',
       			    icon: 'error',
       			    hideAfter: 3000,
       			    loader: false 
       			});
   		 }
   	 });	
    	
    } 
    
	function loadService() {
	    	
		$.ajax({
		   		method: "post", 
		   		url: "ServiceMasterActiveData", 
		   		dataType: "json", 
		   		success: function(response) {
// 	    			alert(JSON.stringify(response));
		   			var options = $("#service");
		   			options.empty();
	// 	   			options.append($("<option />").val("").text("Select Service"));
		   			$.each(response, function() {
		   			    options.append($("<option />").val(this.id).text(this.name));
		   			});
			   	$("#service").change(function(){
			   		serviceId = $('#service').val();
// 			   		console.log("serviceID :"+serviceId);
		      	    	});
			   		 },
		   		error: function(response) {
		   			$.toast({
		   			    text: "Something went wrong.",
		   			    position: 'bottom-right',
		   			    icon: 'error',
		   			    hideAfter: 3000,
		   			    loader: false 
		   			});
		   		} 
		   	});
	}
	
	function BillAction(id)
	{    var a = visitMap[id];
		 
// 		console.log("this is it: "+ JSON.stringify(a));
 	    
 	     loadBillData(id);		
		 $( "#billdialog" ).dialog(
			{
				width:700,
				height:600,
			});
	}
	
	function loadBillData(id){
//     	$('#dialog').hide();
//     	$('#billdialog').hide();
// 		console.log("this is visit id : "+id);
		var data = {}
    	data.id = id;
    	$.ajax({
    		method: "post", 
    		url: "BillActiveData", 
    		dataType: "json", 
    		data: data,
    		
    		success: function(response) {
//    	           	alert(JSON.stringify(response));
      			BillsetDataOnTable(response);
    		},
    		error: function(response) { 
    			alert("Error occured"); 
    		} 
    	});   
    }
	
	function BillsetDataOnTable(response){
		$("#hospitalname").text(response[0].bill.user.client.hname);
		$("#amount").text(response[0].bill.amount);
		$("#personname").text(response[0].bill.user.fname+" "+response[0].bill.user.lname);
		var fullDate = new Date();
		var currentDate = fullDate.getDate() + "/" + fullDate.getMonth() + "/" + fullDate.getFullYear();
		$("#billtime").text(currentDate);

		$("#billListTable").dataTable().fnDestroy();
//    	 console.log("will this work: " + response[0].bill.user.client.hname );
//    	 console.log("this is from setdata on table : "+ JSON.stringify(response));
        var BilltableBodyhtml=""; 
        $.each(response, function() {
            BilltableBodyhtml += getBillHtml(this);
        });
        $("#billListTableBody").html(BilltableBodyhtml);
        $('billListTable_paginate').hide();
        $('billListTable_info').hide();
        $('#billListTable').dataTable({
            //It is used for Not Displaying search option on Header
             "sDom" : "t<'row'<'col-xs-6'i><'col-xs-6'p>>",
             "scrollCollapse": false,
            "iDisplayLength": 10,
             "bSort": false,
              "order": [[ 5, "desc"]],
             "bScrollCollapse": false,
            "aoColumns": [
                          { "bSortable": false},
                          { "bSortable": false},
                          { "bSortable": false},
                          
                      ]
//             "order": [[ 0, "desc"]]
            //for scroll x
//             "bScrollCollapse": false
            
        });
   } 
   function getBillHtml(service) {
//          console.log(JSON.stringify(service));
// 		console.log("--------------------------------");
       counter = counter + 1;
       
       var html = '<tr>' 
               + '<td  class="text-center">' + counter + '</td>'
               + '<td class="text-center">' + service.service.name + '</td>'
               + '<td class="text-center">' + service.service.amount + '</td>'
               + '</tr>';
       return html;
   }
   
   function GenerateBill(){
	  
	   var htmlFormat = $('#billdialog').html();
// 	   console.log(htmlFormat);
	   var data = {}
	   data.html = htmlFormat;
	   $.ajax({
	   		method: "post", 
	   		url: "GenerateBill", 
	   		dataType: "json", 
	   		data: data,
	   		success: function(response) {
	      	    	$.toast({
	   			    text: "Successfull",
	   			    position: 'bottom-right',
	   			    icon: 'success',
	   			    hideAfter: 3000,
	   			    loader: false 
	      	    	});
	      	    
		   		 },
	   		error: function(response) {
	   			$.toast({
	   			    text: "Something went wrong.",
	   			    position: 'bottom-right',
	   			    icon: 'error',
	   			    hideAfter: 3000,
	   			    loader: false 
	   			});
	   		} 
	   	});
   }
    
    </script>   
   
    <div id="dialog">
 
	<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                          
                           <form role="form" id="patientForm">
	                            <div class="col-lg-12">
	                                  <div class="form-group" id="contryDiv">
	                                        <label>Select Services</label>
                                            <select multiple class="form-control" id="service"  style="height:150px">
                                        	</select>
	                                  </div>
	                            </div> 
                          </form> 
                          
                         </div>
                   	  </div>  
                </div>
				<div class="form-group" id="addbutton" style="margin-left:10px;">
           			 <button type="button" class="btn btn-success " id="addservicebutton" onClick="addSelected();">Add Services</button>
           			 <input type="hidden" id="hidden" value="">
                </div>  
       </div>
     </div>
    
      
      <div id="billdialog">

	<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                           <form role="form" id="patientForm">
                        	<h1 class="text-center">Bill Details</h1>
                        	<h3 class="text-center" id="hospitalname"></h3>
                           
	                            <div class="col-lg-12">
	                                  <div class="form-group" id="contryDiv">
                        	<div class="text-right"><span  id="billtime"></span></div>
	                                        <div class="table-responsive" style="overflow-x: hidden;">
				                                <table class="table center table-hover table-bordered " id="billListTable">
				                                    <thead class="bordered-blueberry">
                                        				<tr>
                                        					<th class="col-xs-1 text-center">No.</th>
				                                            <th class="col-xs-6 text-center">Items</th>
				                                            <th class="col-xs-5 text-center">Amount</th>
                                        				</tr>
                                   				    </thead>
                                   				    
                                  				 <tbody id="billListTableBody" class="scrollContent">
                                   				 </tbody>
                               				    </table>
                               				    <h4 id="personname"></h4>
                                  				<h4>Total Payable Amount is : </h4><h4  id="amount"><b></b><h4>
                           				    </div>
	                                  </div>
	                             </div> 

                            </div>
                         </form> 
                     </div>  
              </div>
                	   				<div class="form-group" id="printbill" style="margin-left:10px;">
                               			 <button type="button" class="btn btn-success" onClick="GenerateBill();">Print Bill</button>
                               			 <input type="hidden" id="billhidden" value="">
                                 	  </div>  
                    </div>
                  </div>
      </div>
   
   
   

</body>

</html>
