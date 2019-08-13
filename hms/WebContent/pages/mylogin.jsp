<!DOCTYPE html>
<html>
<head>
<%@include file="./common/commonLib.jsp"%>	
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" id="loginform">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control validate[required,custom[email]]" placeholder="E-mail" name="email" id = email type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" id = "password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                 <div class="form-group">
                                 <button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
<!--                                <br/> -->
								</div>
                                 <div class="form-group">
                                 <a href="./registerForm.jsp" class="btn btn-primary">Register</a>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript">
 		$(document).ready(function(){
 			validateForm();
 		});
 		function validateForm() {
         $("#loginform").validationEngine({
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
 			data.email= $("#email").val();
 			data.password= $("#password").val();
			
 			$.ajax({
 				type:"post",
 				url:"LoginServlet",
 				dataType:"json",
 				data:data,
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
 	    			window.location.href = "./index.jsp"
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
</html>