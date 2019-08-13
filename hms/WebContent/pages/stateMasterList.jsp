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
                    <h1 class="">State Master List</h1>
                </div>
                <div class="col-lg-2 panel-body">
                	<a href="./stateMasterAdd.jsp">
								<span class="pull-left btn btn-primary" style="margin-top: 10px;"><i class="fa fa-plus" style="margin-right: 10px;"></i>Add State</span>
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
                                <table class="table center table-hover table-bordered " id="stateListTable">
                                    <thead class="bordered-blueberry">
                                        <tr>
                                            <th class="col-xs-1 text-center">#</th>
                                            <th class="col-xs-5 text-center">Name</th>
                                            <th class="col-xs-3 text-center">Status</th>
                                            <th class="col-xs-3 text-center">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody id="stateListTableBody" class="scrollContent">
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
    });
    
    function initialiseVariables(){
    	counter = 0;
    loadData();	
    }
   	
    function loadData(){
    	$.ajax({
    		method: "post", 
    		url: "StateMasterGetData", 
    		dataType: "json", 
    		
    		success: function(response) {
//     			alert(JSON.stringify(response));
    			setDataOnTable(response);
    		},
    		error: function(response) { 
    			alert(response); 
    		} 
    	});   
    }
    
    function setDataOnTable(response){
    	 $("#stateListTable").dataTable().fnDestroy();
         var tableBodyhtml=""; 
         $.each(response, function() {
             tableBodyhtml += getHtml(this);
         });
         $("#stateListTableBody").html(tableBodyhtml);
         $('#stateListTable').dataTable({
             //It is used for Not Displaying search option on Header
//              "sDom" : "t<'row'<'col-xs-6'i><'col-xs-6'p>>",
//              "scrollCollapse": true,
//              "iDisplayLength": 10,
//              "bSort": false,
             "aoColumns": [
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
    function getHtml(state) {
        
        counter = counter + 1;
        var status = "";
        <%for(Status status : Status.values()){%>
            if(state.status == "<%=status.name()%>"){
                status = "<%=status.getName()%>";
            }
        <%}%>
        
        var actionButtons = "";
        if(status === "Inactive") {
        	var statusButton = '<button type="button" title="Active" onClick="activeAction(\''+ state.id+ '\');"'
            + 'class="btn btn-info btn-sm shiny icon-only azure"><i class="fa fa-check"></i></button>';
        } 
        else {
        	var statusButton = '<button type="button" title="Inactive" onClick="inactiveAction(\''+ state.id+ '\');"'
            + 'class="btn btn-info btn-sm shiny icon-only azure"><i class="fa fa-times"></i></button>';
        }
        
        var editButton = ' <button type="button" title="Manage Appointment" onClick="stateEditAction(\''+ state.id+ '\');"'
        + 'class="btn btn-info btn-sm shiny icon-only azure"><i class="fa fa-pencil-square-o"></i></button>';
        
        actionButtons += statusButton + editButton;
        
        
        var html = '<tr>' 
                + '<td  class="text-center">' + counter + '</td>'
                + '<td class="text-center">' + state.name  + '</td>'
                + '<td class="text-center">' + status + '</td>'
                + '<td class="text-center">' + actionButtons + '</td>'
                + '</tr>';
        return html;
    }
    function inactiveAction(id){
    	var data = {};
    	data.id = id;
    	data.status = "INACTIVE";
    	$.ajax({
    		method: "post", 
    		url: "StateMasterStatus", 
    		dataType: "json", 
    		data : data,
    		success: function(response) {
    			$.toast({
    			    text: 'Successfully Status Changed.',
    			    position: 'bottom-right',
    			    icon: 'success',
    			    hideAfter: 3000,
    			    loader: false 
    			});
    			counter = 0;
    			loadData();	
    		},
    		error: function(response) { 
    			$.toast({
    			    text: 'Something went wrong.',
    			    position: 'bottom-right',
    			    icon: 'error', //warning,success,error,info
    			    hideAfter: 3000,
    			    loader: false 
    			});
    		} 
    	});
    }
    
    function activeAction(id){
    	var data  = {};
    	data.id = id;
    	data.status = "ACTIVE";
    	$.ajax({
    		method: "post", 
    		url: "StateMasterStatus", 
    		dataType: "json", 
    		data : data,
    		success: function(response) {
    			
    			$.toast({
    			    text: 'Successfully Status Changed.',
    			    position: 'bottom-right',
    			    icon: 'success',
    			    hideAfter: 3000,
    			    loader: false 
    			});
    			counter = 0;
    			loadData();
    		},
    		error: function(response) { 
    			$.toast({
    			    text: 'Something went wrong.',
    			    position: 'bottom-right',
    			    icon: 'error', //warning,success,error,info
    			    hideAfter: 3000,
    			    loader: false 
    			});
    		} 
    	});
    }
    
    
    function stateEditAction(id){
    	window.location.href = "./stateMasterAdd.jsp?id="+id;
    	
    }
    
    </script>    

</body>

</html>
