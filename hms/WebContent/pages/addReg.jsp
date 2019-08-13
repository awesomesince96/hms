<!DOCTYPE html>
<%@page import="com.HMS.commonUtills.Status"%>
<html lang="en">


<head>
	<%@include file="./common/commonLib.jsp"%>	
</head>
<body>
	
    <div id="wrapper">

       

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-6">
                    <div id="pageLabelAdd"> <h1 class="">Registration form</h1></div>
                    <div id="pageLabelEdit"><p class="">Please fill out the entries</p></div> 
                </div>
                
               	
            </div>
            <div class="row">
                <div class="col-lg-6">
                    
                                    <form role="form" id="regForm">
                                        <div class="form-group">
                                            <label>Client</label>
                                            <input class="form-control validate[required]" placeholder="Hospital Name" id="hname">
                                        </div>
                                        
                                        <div class="form-group">
<!--                                             <label>Address</label> -->
                                            <input class="form-control validate[required]" placeholder="Address" id="address">
                                        </div>
                                        
                                        <div class="form-group">
<!--                                             <label>Contact no</label> -->
                                            <input class="form-control validate[required,custom[integer]]" placeholder="Contact." id="contact">
                                        </div>
                                        
                                        
                                        <div class="form-group">
                                            <label>User</label>
                                            <input class="form-control validate[required]" placeholder="User Name" id="username">
                                        </div>
                                        
                                        <div class="form-group">
<!-- 										<label>Password : </label> -->
										<label>
										<input class="form-control validate[required] text-input" type="password" name="password" placeholder="Password" id="password" />
									   </label>
									   <label>
<!-- 										<label>Confirm password : </label> -->
										<label>
										<input class="form-control validate[required,equals[password]] text-input" type="password" name="password2" placeholder="Confirm Password" id="password2" />
										</label>
										<br/>
										</div>
                                        
                                        <div class="form-group">
<!--                                             <label>FirstName</label> -->
                                            <input class="form-control validate[required]" placeholder="Enter FirstName" id="firstname">
                                        </div>
                                        <div class="form-group">
<!--                                             <label>LastName</label> -->
                                            <input class="form-control validate[required]" placeholder="Enter Middlename" id="middlename">
                                        </div>
                                        <div class="form-group">
<!--                                             <label>LastName</label> -->
                                            <input class="form-control validate[required]" placeholder="Enter Lastname" id="lastname">
                                        </div>
                                        <div class="form-group">
<!--                                             <label>Email</label> -->
                                            <input class="form-control validate[required,custom[email]]" placeholder="Enter Email" id="email">
                                        </div>
                                        <div class="form-group">
                                             <label>Gender</label>
                                           	<label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="Male" checked>Male
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="Female">Female
                                            </label>
                                        </div>
                                        
<!--                                        <div class="form-group"> -->
<!--                                             <label>Phone no</label> -->
<!--                                             <input class="form-control validate[required,custom[integer]]" id="email"> -->
<!--                                         </div> -->
<!--                                         <div class="form-group"> -->
<!--                                             <label>Country</label> -->
<!--                                             <select class="form-control"> -->
<!-- 											<option>India</option> -->
<!-- 											<option>USA</option> -->
<!-- 											<option>USA</option> -->
<!-- 											<option>UK</option> -->
<!-- 											<option>RUSSIA</option> -->
<!-- 											</select> -->
<!--                                         </div> -->
                                        
<!--                                         <div class="form-group"> -->
<!--                                             <label>State</label> -->
<!--                                             <select class="form-control"> -->
<!-- 											<option>Gujarat</option> -->
<!-- 											<option>Kashmir</option> -->
<!-- 											<option>Mexico</option> -->
<!-- 											<option>Texas</option> -->
<!-- 											<option>RUSSIA</option> -->
<!-- 											</select> -->
<!--                                         </div> -->
                                        
                                        <div class="form-group">
<!--                                             <label>Contact no</label> -->
                                            <input class="form-control validate[required,custom[integer]]" placeholder="Phone no." id="phoneno">
                                        </div>
                                        
									
                                        
                                        <div class="form-group">
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" value="" id="active" checked>Active
                                                </label>
                                            </div>
                                        </div>
                                        
                                        <button type="submit" class="btn btn-primary">Submit Button</button>
                                        <button type="reset" class="btn btn btn-warning">Reset Button</button>
                                    </form>
                                </div>
                                
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
		</div>
        <!-- /#page-wrapper -->

    </div>

<script>
   
$(document).ready(function() {
// 	initialiseVariables();
	validateForm();
});

function validateForm() {
	$("#regForm").validationEngine({
        onValidationComplete : function(form, status) {
            if (status) {
            	console.log("it is validatiing");
                getFilteredData();
            }
            return false;
        },
        scroll : false,
    });
}

function getFilteredData(){
	console.log("--------");
	var data = {};
	var active = $('#active').prop('checked');
	data.hname = $('#hname').val();
	data.address = $('#address').val();
	data.contact = $('#contact').val();
	data.username = $('#username').val();
	data.password = $('#password').val();
	data.firstname = $('#firstname').val();
	data.middlename = $('#middlename').val();
	data.lastname = $('#lastname').val();
	data.email = $('#email').val();
	data.gender = $('#optionsRadiosInline1').val();
	data.phoneno = $('#phoneno').val();
	if(active){
		data.status = "ACTIVE";
	}else{
		data.status ="INACTIVE" 
	}
	
 	console.log(JSON.stringify(response));
	
	$.ajax({
		method: "post", 
		url: "AddUser", 
		dataType: "json", 
		data : data,
		success: function(response) {
// 			alert("success");
			$.toast({
			    text: "Success",
			    position: 'bottom-right',
			    icon: 'success',
			    hideAfter: 3000,
			    loader: false 
			});
			setTimeout(
					  function() 
					  {
			window.location.href = "./mylogin.html"
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

// function initialiseVariables(){
// // 	loadData();
// 	editMode = false;
<%-- 	 regID = '<%= request.getParameter("id")%>'; --%>
// 	if(cityId === 'null'){
// 		$('#pageLabelAdd').show();
// 		$('#pageLabelEdit').hide();
		
// 	}else{
// 		$('#pageLabelEdit').show();
// 		$('#pageLabelAdd').hide();
// 		editMode = true;
// 		setData(cityId);
// }
// }    
// function loadData(){
	
// 	$.ajax({
// 		method: "post", 
// 		url: "StateMasterActiveData", 
// 		dataType: "json", 
// 		success: function(response) {
// //   			alert(JSON.stringify(response));
// 			var options = $("#state");
// //			alert(options);
// 			$.each(response, function(){
// 				options.append($("<option />").val(this.id).text(this.name));
// 			})
// 		},
// 		error: function(response) {
// 			$.toast({
// 			    text: "Something went wrong.",
// 			    position: 'bottom-right',
// 			    icon: 'error',
// 			    hideAfter: 3000,
// 			    loader: false 
// 			});
// 		} 
// 	}); 
	
// }

// function setData(id){
// 	var data = {};
// 	data.id = id;
// 	$.ajax({
// 		method: "post", 
// 		url: "CityMasterGetData", 
// 		dataType: "json", 
// 		data : data,
// 		success: function(response) {
// 	//		alert(JSON.stringify(response)); 
// 			alert(response.stateMaster.id);
// 			$("#name").val(response.name);
// 			$("#state").val(response.stateMaster.id);
// 			if(response.status === "ACTIVE"){
// 			console.log("ACTIVE");	
// 			$('#active').prop('checked', true);
// 			}
					
// 		},
// 		error: function(response) {
// 			$.toast({
// 			    text: "Something went wrong.",
// 			    position: 'bottom-right',
// 			    icon: 'error',
// 			    hideAfter: 3000,
// 			    loader: false 
// 			});
// 		} 
// 	});    
	
// }


   
</script>      
    
</body>
</form>

</html>