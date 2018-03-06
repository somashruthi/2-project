/** JOB CONTROLLER
 * 
 */

app.controller('JobController',function($scope,$rootScope,$location,JobService){
	$scope.addJob=function(job){
		JobService.addJob(job).then(
		function(response){
			
			alert("job details posted successfully")
			$location.path('/home')
			},function(response){
				//3 return statements for error
				// 1.not authenticated,not authrized  - 401
				
				
				$rootScope.error=response.data
				if(response.data=401)
					$location.path('/login')
				
			})
			
	}
	
	JobService.getAllJobs().then(function(response){
		$scope.jobs=response.data
	},function(response){
	$rootScope.error=response.data
	if(response.data=401)
		$location.path('/login')
	
	})
	
	
})

	
