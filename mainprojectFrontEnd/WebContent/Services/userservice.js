/** 
 *    userservice      ====      to make server side calls
 */

green.factory('UserService',function($http) {
	var userSerrvice={}
			
		userService.registerUser=fuction(user)
		{
			console.log( user)
			return $http.post("http://loclhost:8085/mainprojectmiddleware/registeruser",user) 	
			
		}
		return userService;
	

	
})