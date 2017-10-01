
<!DOCTYPE HTML>
<html>
<head>
<title>Registration </title>

  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.1/css/materialize.min.css">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Classy Login form Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script src="js/jquery-2.2.3.min.js"></script> 
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script src="http://code.angularjs.org/1.3.3/angular-animate.min.js" ></script>
    <script src="toaster.js"></script>
	
    <link href="toaster.css" rel="stylesheet" />
<link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700' rel='stylesheet' type='text/css'>

</head>
<body data-ng-app="myapp" data-ng-controller="usercontroller">
<!--header start here-->
<toaster-container toaster-options="{'time-out': 3000, 'close-button':true, 'animation-class': 'toast-top-center','position-class':'toast-top-half-width' } "></toaster-container>
<div class="header">
		<div class="header-main">
		       
			   <label><font size="6" color="orange" >Registration Form</font></label>
			<div class="header-bottom">
				<div class="header-right w3agile">
					
					<div class="header-left-bottom agileinfo">
						
					 <form name="myform">
					 <br>
					 <br>
<input type="text"  placeholder="name" name="name"  id="name"  onblur="if (this.value == '') " data-ng-model="name" required/>
<input type="email"  placeholder="Email" name="email" onblur="if (this.value == '')" data-ng-model="email" required/>
<input type="text"  placeholder="contact" name="contact"  id="contact"  onblur="if (this.value == '') " data-ng-model="contact" required/>
<input type="password"  placeholder="Password" name="password"   onblur="if (this.value == '') cZ" data-ng-model="password" required/>
						
					   
	<center><button class="btn waves-effect waves-light col s12" data-ng-click="insertdata()" data-ng-disabled="myform.$invalid">Register Now</button></center>
						
						
						        
        </div>
		<div class="input-field col s12">
            <p class="margin center medium-small sign-up">Already have an account? <a href="login.html">Login</a></p>
          </div>
					</form>	
					
					
						
				</div>
				</div>
			  
			</div>
		</div>
</div>

 
  
</body>
</html>
<script>
        
	angular.module('myapp', ['ngAnimate', 'toaster'])

	.controller('usercontroller', function($scope, toaster, $window,$http,$timeout) {
	$scope.insertdata= function(){
	 
	   $http({
           url : '/myproject/myservlet',
           method : "POST",
           data : {
        	   'name': $scope.name,
               'email' : $scope.email,
              'contact': $scope.contact,
              'password': $scope.password
           }
       }).success(function(response) {
    	   
    	   toaster.success({title: "Registration Successfull ", body:""});
    	   $timeout(callAtTimeout, 2000);
    	   
       }, function(response) {
          
       });
	   
   }
   
});
	function callAtTimeout() {
		window.location.assign("/myproject/login.html")
	}
    </script>