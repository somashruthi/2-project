/**
 *   userController -- to receive data from view and vice-versa
 *   
 */


green.controller('UserController',function($scope,$location,UserService){
	$scope.registerUser=function(user){     //user is from view i.e register.html
		UserService.registerUser(user).then(
			function(response){
				
			alert("registered successfully")
			$location.path('/home')
			},function(response){
				$scope.error=response.data
		
			
			
			
			})
		
		
		
		
		
		
	}
	
	
	
	
})