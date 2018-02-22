/**
 * 
 */


var app=angular.module("app",['ngRroute'])
app.config(function($routeProvider){
	
	$routeProvider
	.when('/register',{
		templateUrl:'views/register.html',
		controller:'UserController'
		
	})
	
	
	.when('/login',{
		templateUrl:'views/login.html',
		controller:'UserController'
			
	})
	.otherwise({
		templateUrl:'views/Home.html',
		
		
	})
	
	
})
		






