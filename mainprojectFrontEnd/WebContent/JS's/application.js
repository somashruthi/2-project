/**
 * 
 */


var green=angular.module("application",['ngRroute'])
application.config(function($routeProvider){
	
	$routeProvider
	.when('/register',{
		templateUrl:'views/register.html',
		controller:'UserController'
		
	})
	.otherwise({
		templateUrl:'views/home.html',
		
		
	})
	
	
})
		






