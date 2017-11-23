(function(){
	'use-strict';
	angular.module('app', []).controller('IndexController', IndexController);
	
	IndexController.$inject = ['$scope', '$http'];
	
	function IndexController($scope, $http){
		$scope.resultado = {};
		$scope.local;
		if(navigator.geolocation){
			navigator.geolocation.getCurrentPosition(function(position){
				$scope.$apply(function(){
					$scope.local = position.coords.latitude + ',' + position.coords.longitude;
					listarLugares(position.coords.latitude + ',' + position.coords.longitude);
				})
			})
			
		}
		
		
		function listarLugares(local){
			$http.get('http://localhost:8080/wstarsus/ws/locais/' + local).then(function(data){
				$scope.resultado = data.data.results;
				console.log($scope.resultado);
			});
			
		}
	}
})();