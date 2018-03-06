/**
 * 
 */

app.factory('JobService',function($http) {
	var jobService={}
			
		jobService.addJob=function(job)
		{
		
			return $http.post("http://loclhost:8085/mainprojectmiddleware/addjob",job) 	
			
		}
	
	
	jobService.getallJobs=function(job)
	{
		return $http.post("http://loclhost:8085/mainprojectmiddleware/alljobs",job) 	
		
	}
	return jobService; 
})