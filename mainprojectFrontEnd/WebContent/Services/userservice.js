 /** 
 *    userservice      ====      to make server side calls
 */

app.factory('UserService',function($http) {
	var userService={}
			
		userService.registerUser=function(user)
		{
		//alert('entering user service registeruser function')
			console.log( 'in userservice' +user)
			return $http.post("http://loclhost:8085/mainprojectmiddleware/registeruser",user) 	
			
		}
		
	
		userService.login=function(user)
		{
			console.log('userservice----> login')
			console.log(user)
			return $http.post("http://loclhost:8085/mainprojectmiddleware/login",user) 	
			
		}
		
		userService.logout=function()
		{
			
			return $http.put("http://loclhost:8085/mainprojectmiddleware/logout")	
		}
		
		
		userService.getUser=function()
		{
			
			return $http.get("http://loclhost:8085/mainprojectmiddleware/getUser")	
		}
		
		userService.updateUser=function(user)
		{
			
			return $http.put("http://loclhost:8085/mainprojectmiddleware/updateUser",user)	
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return userService;
})