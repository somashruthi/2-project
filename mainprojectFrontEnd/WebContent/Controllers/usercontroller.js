/**
 *   userController -- to receive data from view and vice-versa
 *   
 */


app.controller('UserController',function($scope,$rootScope,$location,UserService,$cookieStore){
	$scope.registerUser=function(user){     //user is from view i.e register.html
		alert('entering usercontroller registerUser funcrion in frontend'+ user)
		console.log('entering usercontroller registerUser function in frontend'+ user)
		UserService.registerUser(user).then(
			function(response){
				
			alert("registered successfully")
			$location.path('/home')
			},function(response){
				$scope.error=response.data
		
			}) 
	}
	
	
	$scope.login=function(user){
		/*console.log('User controller---> login')
		console.log(user)*/
		UserService.login(user).then(function(response){
			
			$rootScope.loggedInUser=response.data
			$cookieStore.put
			/*console.log(success)
			console.log (response.data)*/	
				$location.path('/home')
					},function(response){
						/*console.log('error')
							console.log(response.data)*/
						 $scope.error=response.data
					$location.path('/login')
		
			})
		
	} 
	
})