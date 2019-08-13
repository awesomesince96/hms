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
                    <div id="pageLabelAdd"> <h1 class="">Service Master Add</h1></div>
                    <div id="pageLabelEdit"><h1 class="">Service Master Edit</h1></div> 
                </div>
                <div class="col-lg-2 panel-body">
                	<a href="./serviceMasterList.jsp">
								<span class="pull-left btn btn-primary" style="margin-top: 10px;"><i class="fa fa-list" style="margin-right: 10px;"></i>List Service</span>
								<span class="clearfix"></span>
						</a>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" id="serviceForm">
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input class="form-control validate[required]" placeholder="Enter Name" id="name">
                                        </div>
                                        <div class="form-group">
                                            <label>Amount</label>
                                            <input class="form-control validate[required]" placeholder="Enter Amount" id="amount">
                                        </div>
                                        <div class="form-group">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" value="" id="active">Active
                                                </label>
                                            </div>
                                        </div>
                                        
                                        <button type="submit" class="btn btn-primary">Submit Button</button>
                                        <button type="reset" class="btn btn btn-warning">Reset Button</button>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
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
    	validateForm();
    });
    
    function initialiseVariables(){
		editMode = false;
    	 serviceId = '<%= request.getParameter("id")%>';
    	if(serviceId === 'null'){
    		$('#pageLabelAdd').show();
    		$('#pageLabelEdit').hide();
    		
    	}else{
    		$('#pageLabelEdit').show();
    		$('#pageLabelAdd').hide();
    		
    		editMode = true;
    		setData(serviceId);    		
    	}
    }
	
    function setData(id){
    	var data = {};
    	data.id = id;
    	$.ajax({
    		method: "post", 
    		url: "ServiceMasterGetData", 
    		dataType: "json", 
    		data : data,
    		success: function(response) {
    			$("#name").val(response.name);
    			if(response.status === "ACTIVE"){
    			console.log("ACTIVE");	
    			$('#active').prop('checked', true);
    			}
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
    
    function validateForm() {
    	$("#serviceForm").validationEngine({
            onValidationComplete : function(form, status) {
                if (status) {
                    getFilteredData();
                }
                return false;
            },
            scroll : false,
        });
	}
    
    function getFilteredData(){
    	var data = {};
    	var active = $('#active').prop('checked');
    	data.name = $('#name').val();
    	data.amount = $('#amount').val();
    	if(active){
    		data.status = "ACTIVE";
    	}else{
    		data.status ="INACTIVE" 
    	}
    	if(editMode){
		 data.id = serviceId;   		
    	}
    	
		
    	$.ajax({
    		method: "post", 
    		url: "ServiceMasterAdd", 
    		dataType: "json", 
    		data : data,
    		success: function(response) {
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
    			window.location.href = "./serviceMasterList.jsp"
    					  }, 1000);
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

</body>

</html>
