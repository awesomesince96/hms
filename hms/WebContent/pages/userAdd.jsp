<!DOCTYPE html>
<%@page import="com.HMS.commonUtills.Status"%>
<html lang="en">

<head>
<%@include file="./common/commonLib.jsp"%>
<script>
    
    $(document).ready(function(){
    	console.log("loaded");
    	initialiseVariables();
    	
    
    	
    	$('#datepicker').datepicker({
    		dateFormat: 'mm-dd-yy'
    	});
    	
    	$("#country").change(function(){
 	   		countryId = $('#country').val();
 	   		console.log("countryId :::"+countryId);
 	   		if(countryId){
		   	   	getAllStateData(countryId);
 	   		}
	      });
    	
    	$("#state").change(function(){
   			stateId = $('#state').val();
   			console.log(stateId);
   				console.log("stateid: "+stateId);
   				getAllCityData(stateId);
	     	});
    	
    });
    

    
    
    function initialiseVariables() {
    
    	getAllCountryData();
    	editMode = false;
   	 	userId = '<%= request.getParameter("id")%>';
   		if(userId === 'null'){
   			$('#pageLabelAdd').show();
   			$('#pageLabelEdit').hide();
   			$('#confirm_password').show();
   			$('#contryDiv').show();
   			$('#stateDiv').show();
   			$('#cityDiv').show();
   			$('#reset').show();
   		}else{
   			
   			$('#pageLabelAdd').hide();
   			$('#pageLabelEdit').show();
   			$('#confirm_password').hide();
   			$('#contryDiv').hide();
   			$('#stateDiv').hide();
   			$('#cityDiv').hide();
   			$('#reset').hide();
   			editMode = true;
   			setData(userId);    		
   		}
	}
    
    function getAllCountryData(){
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
    
    function getAllStateData(countryId){
    	var data = {};
		data.countryId = countryId;
		$.ajax({
	   		method: "post", 
	   		url: "StateMasterActiveData", 
	   		dataType: "json", 
	   		data : data,
	   		success: function(response) {
	   			console.log("state response: "+response);
	   			var options = $("#state");
	   			options.empty();
	   			options.append($("<option />").val("").text("Select State"));
	   			$.each(response, function() {
	   			    options.append($("<option />").val(this.id).text(this.name));
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
    
    function getAllCityData(){
    var data = {};
	data.stateId = stateId;
	console.log(stateId);
	$.ajax({
   		method: "post", 
   		url: "CityMasterActiveData", 
   		dataType: "json", 
   		data : data,
   		success: function(response) {
   			var options = $("#city");
   			options.empty();
   			options.append($("<option />").val("").text("Select City"));
   			$.each(response, function() {
   			    options.append($("<option />").val(this.id).text(this.name));
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
    
    function validateForm() {
    	$("#formId").validationEngine({
            onValidationComplete : function(form, status) {
                if (status) {
                    getFilteredData();
                }
                return false;
            },
            scroll : false,
 		});
	}
    function getFilteredData() {
    	var user =JSON.parse('<%=request.getSession(false).getAttribute("user")%>');
		var clientId = user.clientMaster.id ;
    	var data={};
		
		 data.firstName=$('#firstName').val();
		 data.middleName=$('#middleName').val();
		 data.lastName=$('#lastName').val();
		 data.email=$('#email').val();
		 data.password=$('#password').val();
		 data.contactNo1=$('#contactNo1').val();
		 data.contactNo2=$('#contactNo2').val();
		 data.address = $('#address').val();
		 data.gender = $('#gender:checked').val();
		 data.contryId = $('#country').val();
		 data.stateId = $('#state').val();
		 data.cityId = $('#city').val();
		 data.date = $('#datepicker').val();
		 data.clientId = clientId;
		 
		 if(editMode){
			 data.userId = userId; 
	   	}
		 
		 $.ajax({
			 method: "post",
			 url: "UserAdd",
			 dataType: "json",
			 data: data,
			 success:function(response){
//				 alert(JSON.stringify(response));
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
	    						 
					window.location.href = "./UserList.jsp"
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
//     	alert(userId);
		var data = {};
		data.userId = userId;
		$.ajax({
   			method: "post", 
   			url: "UserEdit", 
   			dataType: "json", 
   			data : data,
   			success: function(response) {
//    			alert(JSON.stringify(response));
   				$("#firstName").val(response.firstName);
   				$("#middleName").val(response.middleName);
   				$("#lastName").val(response.lastName);
   				$("#email").val(response.email);
				var value = response.gender;
   				$("input[name=gender][value=" + value + "]").prop('checked', true);
   				$("#password").val(response.password);
   				$("#contactNo1").val(response.contactNo1);
   				$("#contactNo2").val(response.contactNo2);
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
    
    </script>

</head>

<body>

	<div id="wrapper">

		<%@include file="./common/header.jsp"%>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-10">
					<div id="pageLabelAdd">
						<h1 class="">User Add</h1>
					</div>
					<div id="pageLabelEdit">
						<h1 class="">User Edit</h1>
					</div>
				</div>
				<div class="col-lg-2 panel-body">
					<a href="./userList.jsp"> <span
						class="pull-left btn btn-primary" style="margin-top: 10px;"><i
							class="fa fa-list" style="margin-right: 10px;"></i>User List</span> <span
						class="clearfix"></span>
					</a>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="row">
								<form role="form" id="formId">
									<fieldset>
										<div class="col-lg-4">
											<div class="form-group">
												<input placeholder="First-Name" name="firstName"
													id="firstName" type="text"
													class="form-control validate[required,custom[onlyLetterNumber],maxSize[20]]">
											</div>
										</div>
										<!-- /.col-lg-4 (nested) -->

										<div class="col-lg-4">
											<div class="form-group">
												<input placeholder="Middle-Name" name="middleName"
													id="middleName" type="text"
													class="form-control validate[required,custom[onlyLetterNumber],maxSize[20]]">
											</div>
										</div>
										<!-- /.col-lg-4 (nested) -->

										<div class="col-lg-4">
											<div class="form-group">
												<input placeholder="Last-Name" name="lastName" id="lastName"
													type="text"
													class="form-control validate[required,custom[onlyLetterNumber],maxSize[20]]">
											</div>
										</div>
										<!-- /.col-lg-4 (nested) -->

										<div class="col-lg-6">
											<div class="form-group">
												<input placeholder="E-mail" name="email" id="email"
													type="text"
													class="form-control validate[required,custom[email]] text-input">
											</div>
											<div class="form-group">
												Gender: <label class="radio-inline"> <input
													type="radio" name="gender" id="gender"
													class="validate[required] radio" value="Male">Male
													<label class="radio-inline"> <input type="radio"
														name="gender" id="gender" class="validate[required] radio"
														value="Female">Female 
											</div>

											<div class="form-group">
												<input placeholder="Contact-No." name="contactNo1"
													id="contactNo1" type="tel"
													class="form-control validate[required,custom[integer],maxSize[10]] text-input">
											</div>
											<div class="form-group">
												<input placeholder="Contact-No." name="contactNo2"
													id="contactNo2" type="tel"
													class="form-control validate[custom[integer],maxSize[10]] text-input">
											</div>
										</div>
										<!-- /.col-lg-6 (nested) -->

										<div class="col-lg-6">
											<div class="form-group">
												<!--                                             <label>Text area</label> -->
												<textarea placeholder="Address" name="address" id="address"
													class="form-control " rows="3"></textarea>
											</div>
											<div class="form-group">
												<input placeholder="Password" name="password"
													type="password" id="password"
													class="form-control validate[required]" value="">
											</div>
											<div class="form-group" id="confirm_password">
												<input placeholder="Confirm-Password"
													name="confirm_password" type="password"
													id="confirmPassword"
													class="form-control validate[required,equals[password]] text-input"
													value="">
											</div>

										</div>
										<!-- /.col-lg-6 (nested) -->

										<div class="col-lg-4">
											<div class="form-group" id="contryDiv">
												<label>Country</label> <select class="form-control "
													id="country">
												</select>
											</div>
										</div>
										<!-- /.col-lg-4 (nested) -->

										<div class="col-lg-4">
											<div class="form-group" id="stateDiv">
												<label>State</label> <select class="form-control "
													id="state">
												</select>
											</div>
										</div>
										<!-- /.col-lg-4 (nested) -->

										<div class="col-lg-4">
											<div class="form-group" id="cityDiv">
												<label>City</label> <select class="form-control " id="city">
												</select>
											</div>
										</div>
										<!-- /.col-lg-4 (nested) -->

										<div class="col-lg-4">
											<input type="text" placeholder="Date" id="datepicker"
												class="form-control validate[required]" />
										</div>

										<!-- /.col-lg-4 (nested) -->
										<div class="col-lg-4">
											<button type="submit"
												class="btn btn-success btn-lg btn-block">Register</button>
										</div>
										<!-- /.col-lg-4 (nested) -->
										<div class="col-lg-4" id="reset">
											<button type="reset" class="btn btn-warning btn-lg btn-block">Reset</button>
										</div>
										<!-- /.col-lg-4 (nested) -->
									</fieldset>
								</form>
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

	
</body>

</html>