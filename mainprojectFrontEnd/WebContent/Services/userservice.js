 /** 
 *    userservice      ====      to make server side calls
 */

app.factory('UserService',function($http) {
	var userSerrvice={}
			
		userService.registerUser=fuction(user)
		{
		//alert('entering user service registeruser function')
			console.log( 'in userservice' +user)
			return $http.post("http://loclhost:8085/mainprojectmiddleware/registeruser",user) 	
			
		}
		
	
		userService.login=fuction(user)
		{
			console.log('userservice----> login')
			console.log(user)
			return $http.post("http://loclhost:8085/mainprojectmiddleware/login",user) 	
			
		}
		
		userService.logout=function(){
			
			return $http.put("http://loclhost:8085/mainprojectmiddleware/logout")
			
			
		}
		
		return userService;
})