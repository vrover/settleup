var app = angular.module('myapp', []);

app.controller('membercontroller', function($scope) {
	var vm=this;
	 $scope.names = ["Licky", "Deo", "Shashank","Piyush","Varshant","Jatin"];
         $scope.groupname = ["Licky", "Deo", "Shashank","Piyush","Varshant","Jatin"];
         $scope.Payable = ["Lic", "o", "Sank","Piyush","Varshant","Jatin"];
         $scope.Recievable = ["Licky", "Deo", "Shashank","Psh","Vshant","Jin"];
         $scope.dropnames = [{'id':'deo'},{'id':'sha'},{'id':'maa'}];
  
  $scope.mess='rahul'
  $scope.init = function(name, id)
  {
    
    $scope.id = $scope.mess;
    $scope.name = name; 
    
  };
  $scope.selectedProjects=[];
    
      $scope.myFunction = function() {
      
    
      $window.alert('hi ANgular');
            
};
  
  
});