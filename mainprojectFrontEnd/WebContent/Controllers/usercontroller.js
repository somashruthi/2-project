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
	
	// statemnt whch ll get executed when controller gets loaded
	//controller to view
	if($rootScope.loggedInUser!=undefined){
	UserService.getUser().then(
			function(response){
				$scope.user=response.data        // select * from user where email=?
			},
			function(response){
				
				if(response.status==401)         //error is either 401 or 500
					$location.path('/login')
				else
					$scope.error=response.data;
			})
	
	
	}
	
	// view to controller 
	$scope.updateUser=function(user){
	UserService.updateUser(user).then(
			function(response){
				alert("updated details successfully")
				$rootScope.loggedInUser=response.data      
				$cookieStore.put('loggedInUser',resonse.data)
			},
			function(response){
				
				if(response.status==401)         //error is either 401 or 500
					$location.path('/login')
				else
					$scope.error=response.data;
			})
	
	}
	

	
})