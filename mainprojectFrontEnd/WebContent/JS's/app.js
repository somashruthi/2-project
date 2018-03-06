/**
 * 
 */


var app=angular.module("app",['ngRroute','ngCookies'])
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
	
	
	.when('/edituserprofile',{
		templateUrl:'views/edituserprofile.html',
		controller:'UserController'
			
	})
	
	
	.when('/addjob',{
		templateUrl:'views/jobform.html',
		controller:'JobController'
			
	})
	
	
	.when('/alljobs',{
		templateUrl:'views/jobslist.html',
		controller:'JobController'
			
	})
	
	
	.when('/getjob/:id',{
		templateUrl:'views/jobdetails.html',
		controller:'JobController'
			
	})
	
	.otherwise({
		templateUrl:'views/Home.html',
	})
})
 
app.run(function($location,$rootScope,$cookieStore,UserService){                    //this is becz when we refresh we should get HI USERNAME
	if($rootScope.loggedInUser==undefined)
		$rootScope.loggedInUser=$cookieStore.get('currentuser')
	
		$rootScope.logout=function(){
		UserService.logout().then(
				function(response){
					delete $rootScope.loggedInUser;
					$cookieStore.remove('currentuser')
					$rootScope.message="Successgully loggedout..."
						$location.path=('/login');
				},function (response){
					$rootScope.error=response.data
					if(response.status==401)
						$location.path=('/login');
					
				})
	}
	
	
})
		






