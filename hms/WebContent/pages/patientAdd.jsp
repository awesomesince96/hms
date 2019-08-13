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
                    <div id="pageLabelAdd"> <h1 class="">Patient Add</h1></div>
                    <div id="pageLabelEdit"><h1 class="">Patient Edit</h1></div> 
                </div>
                <div class="col-lg-2 panel-body">
                	<a href="./patientList.jsp">
								<span class="pull-left btn btn-primary" style="margin-top: 10px;"><i class="fa fa-list" style="margin-right: 10px;"></i>List Patient</span>
								<span class="clearfix"></span></a>
                </div>
               
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                           <form role="form" id="patientForm">
                            <div class="row">
                                
                                <div class="col-lg-4">
                                  <div class="form-group">
                                    <input  placeholder="First-Name" id="fname" name="firstName" id="firstName" type="text" class="form-control validate[required,custom[onlyLetterNumber],maxSize[20]]" >
                                </div>
                                </div> 
                                 <!-- /.col-lg-4 (nested) -->

                                <div class="col-lg-4">
                                  <div class="form-group">
                                    <input  placeholder="Middle-Name" id="mname" name="middleName" id="middleName" type="text" class="form-control validate[required,custom[onlyLetterNumber],maxSize[20]]" >
                                </div>
                                </div> 
                                 <!-- /.col-lg-4 (nested) -->

                                <div class="col-lg-4">
                                   <div class="form-group">
                                    <input  placeholder="Last-Name" id="lname" name="lastName" id="lastName" type="text" class="form-control validate[required,custom[onlyLetterNumber],maxSize[20]]" >
                                </div>
                                </div> 
                                       
                            </div>
                               
                              <div class="row">
                                <div class="col-lg-4">
								 <div class="form-group">
                                         <label>Radio Buttons: </label>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="gender" id="male" value="Male" checked>Male
                                                </label>
                                            </div>
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="gender" id="female" value="Female">Female
                                                </label>
                                            </div>
                                          </div>
                                          
                                        
                                  

                                </div>
                                <div class="col-lg-4">
                                        <div class="form-group">
                                            <label>Blood group</label>
                                            <select class="form-control" id="bloodgroup">
                                                <option value="A+">A+</option>
                                                <option value="A-">A-</option>
                                                <option value="B+">B+</option>
                                                <option value="B-">B-</option>
                                                <option value="AB+">AB+</option>
                                                <option value="O+">O+</option>
                                                <option value="O-">O-</option>
                                            </select>
                                        </div>
                                </div>
                                 <div class="col-lg-4">        
                                      <div class="form-group">
                                      <label>Birthday</label>  
                                       <input class="form-control" type ="text" id = "birthday"> 
                                      </div> 
                                 </div>
                                       
                            </div>   
                            
                            <div class="row">
                                <div class="col-lg-4">
                                  <div class="form-group" id="contryDiv">
                                            <label>Country</label>
                                               <select class="form-control " id="country">
                                               </select>
                                  </div>
                                </div> 
                                 <!-- /.col-lg-4 (nested) -->

                                <div class="col-lg-4">
                                   <div class="form-group" id="stateDiv">
                                            <label>State</label>
                                               <select class="form-control " id="state">
                                               </select>
                                        </div>
                                </div> 
                                 <!-- /.col-lg-4 (nested) -->

                                <div class="col-lg-4">
                                    <div class="form-group" id="cityDiv">
                                            <label>City</label>
                                               <select class="form-control " id="city">
                                               </select>
                                        </div>
                                </div> 
                            </div>
                            
                            <div class="row">
                                
                                
                                <div class="col-lg-4">
                                  <div class="form-group">
                                   <label>Email</label>
                                    <input  placeholder="E-mail" name="email" id="email" type="text" class="form-control validate[required,custom[email]] text-input"  >
                                </div>
                                </div> 
                                

                                <div class="col-lg-4">
                                  <div class="form-group">
                                   <label>Phone no.</label>
                                    <input  placeholder="Phone-no" name="phoneno" id="phoneno" type="tel" class="form-control validate[required,custom[integer],maxSize[10]] text-input" >
                                </div>
                                </div> 
                                

                                <div class="col-lg-4">
                                   <div class="form-group">
                                            <label>Address</label>
                                            <textarea placeholder="Address" name="address" id="address" class="form-control " rows="3"></textarea>
                                   </div>
                                </div> 
                                
                               </div> 
                            
                               <button type="submit" class="btn btn-primary">Submit Button</button>
                          
                                      <button type="reset" class="btn btn btn-warning">Reset Button</button>
                                      
                             </form>         
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
                                        
                                       
     </div>
                                
                                <div class="col-lg-6">
                                </div>
                               
 </div>
                          
                        
    
<script>
    $(document).ready(function() {
    	console.log("ready");
    	loadData();
    	  $( function() {
  		    $( "#birthday" ).datepicker({
  		      changeMonth: true,
  		      changeYear: true
  		    });
  		  } );;
  		    	 
    	initialiseVariables();
    	validateForm();
    });
    
function initialiseVariables() {

    	
    	editMode = false;
   	 	userId = '<%= request.getParameter("id")%>';
   		if(userId === 'null'){
   			$('#pageLabelAdd').show();
   			$('#pageLabelEdit').hide();
   			$('#reset').show();
   		}else{		
   			$('#pageLabelEdit').show();
   			$('#pageLabelAdd').hide();
   			editMode = true;
   			setData(userId);    		
   		}
	}

function validateForm() {
	console.log("validate form");
	$("#patientForm").validationEngine({
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
	 alert("this is filter");
	 console.log("filterded data");
	 var user = JSON.parse('<%= request.getSession().getAttribute("user")%>');
	 var data={};
	 data.fname=$('#fname').val();
	 data.mname=$('#mname').val();
	 data.lname=$('#lname').val();
	 data.gender = $("input[name='gender']:checked").val();
	 data.dob = $('#birthday').val();
	 data.bloodgroup=$('#bloodgroup').val();
	 data.phone=$('#phoneno').val();
	 data.address = $('#address').val();
	 data.cityId = $('#city').val();
	 data.stateId = $('#state').val();
	 data.contryId = $('#country').val();
	 data.userId = user.id;
	 console.log(JSON.stringify(data));
	 data.email=$('#email').val();
	 
	 $.ajax({
		 method: "post",
		 url: "PatientAdd",
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
				window.location.href = "./patientList.jsp"
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
	

function test() {
	console.log("filterded data");
	var clientId = user.client.id ;
	var userid = user.id;
	console.log("userid = "+userid+"clientid= "+clientid);
	var data={};
	
	 data.fname=$('#fname').val();
	 data.mname=$('#mname').val();
	 data.lname=$('#lname').val();
	 data.gender = $("input[name='gender']:checked").val();
	 data.dob = $('#birthday').val();
	 data.bloodgroup=$('#bloodgroup').val();
	 data.phone=$('#phoneno').val();
	 data.address = $('#address').val();
	 data.cityId = $('#city').val();
	 data.stateId = $('#state').val();
	 data.contryId = $('#country').val();
	 data.userid = userid;
	 data.email=$('#email').val();
	 
	 console.log(data);
	 
	 if(editMode){
		 data.userId = userId; 
		
   	}
	 $.ajax({
		 method: "post",
		 url: "PatientAdd",
		 dataType: "json",
		 data: data,
		 success:function(response){
			 alert(JSON.stringify(response));
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
    						 
				window.location.href = "./patientList.jsp"
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
    
    function  setData(userId){
     	alert(userId);
		var data = {};
		data.userId = userId;
		$.ajax({
   			method: "post", 
   			url: "PatientEdit", 
   			dataType: "json", 
   			data : data,
   			success: function(response) {
//    			alert(JSON.stringify(response));
   				$("#fname").val(response.firstName);
   				$("#mname").val(response.middleName);
   				$("#lname").val(response.lastName);
   				$("#email").val(response.email);
				var value = response.gender;
   				$("input[name=gender][value=" + value + "]").prop('checked', true);
   				$("#password").val(response.password);
   				$("#contactNo1").val(response.contactNo1);
   				$("#address").val(response.address);
   				var date=new Date(response.date);
   				var day = date.getDate();
   		  		var monthIndex = date.getMonth();
   		 	    var year = date.getFullYear();
   		        var finalDate= day + '-' + monthIndex + '-' + year;
   				$("#datepicker").val(finalDate);
//     			$('#country').val(response.contry.id);
//     			$('#state').val(response.state.id);
//     			$('#city').val(response.city.id);
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
    
    function loadData() {
    	
    	$.ajax({
 	   		method: "post", 
 	   		url: "ContryMasterActiveData", 
 	   		dataType: "json", 
 	   		success: function(response) {
// 	    			alert(JSON.stringify(response));
 	   			var options = $("#country");
 	   			options.empty();
 	   			
 	   			options.append($("<option />").val("").text("Select Country"));
 	   			$.each(response, function() {
 	   			    options.append($("<option />").val(this.id).text(this.name));
 	   			});
 	   			
 	   	$("#country").change(function(){
 	   		contryId = $('#country').val();
 	   		console.log("countryId :"+contryId);
		   	   	stateLoadData(contryId);
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
    
    function stateLoadData(contryId) {
    	var data = {};
			data.contryId = contryId;
			$.ajax({
		   		method: "post", 
		   		url: "StateMasterActiveData", 
		   		dataType: "json", 
		   		data : data,
		   		success: function(response) {
		   			var options = $("#state");
		   			options.empty();
		   			options.append($("<option />").val("").text("Select State"));
		   			$.each(response, function() {
		   			    options.append($("<option />").val(this.id).text(this.name));
		   			});
		   			
		   			$("#state").change(function(){
		   			stateId = $('#state').val();
		   			console.log("state id :"+stateId);
		   			cityLoadData(stateId);
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
    
    function  cityLoadData(stateId){
    	var data = {};
		data.stateId = stateId;
    	console.log(stateId);
		$.ajax({
	   		method: "post", 
	   		url: "CityMasterActiveData", 
	   		dataType: "json", 
	   		data : data,
	   		success: function(response) {
	  			console.log(JSON.stringify(response));
	   			var options = $("#city");
	   			options.empty();
	   			options.append($("<option />").val("").text("Select City"));
	   			$.each(response, function() {
	   			    options.append($("<option />").val(this.id).text(this.name));
	   			});
	   		
	   			$("#city").change(function(){
		   			cityId = $('#city').val();
		   			console.log(cityId);
		   				console.log("data loaded");
		   				
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
    
     

</body>

</html>
