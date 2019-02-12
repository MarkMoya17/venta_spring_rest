var app = angular.module("mcategoria", []);
app.controller("categoriaCtrl", [ '$scope', '$http', function($scope, $http) {

	// Variable inicial para el indice del array
	var idx = 0;
	$scope.objmax = {};
	$scope.categorias = [];
	$scope.categoriaActual = {};

	$scope.vista = "formulario";
	$scope.navegacion = true;
	$scope.edit = false;
	$scope.sololectura = true;
	$scope.botones = "noeditable";

	$http({
		method : 'GET',
		url : '/apicategoria/all'
	}).success(function(data) {
		$scope.categorias = data;
	});

	$scope.listar = function() {
		$http({
			method : 'GET',
			url : '/apicategoria/all'
		}).success(function(data) {
			$scope.categorias = data;
		});
	};

	$scope.nuevo = function() {

		var idx = -1;
		var categoriaArray = eval($scope.categorias);
		var may = categoriaArray[0].id

		for (var i = 0; i < categoriaArray.length; i++) {
			if (categoriaArray[i].id > may) {
				may = categoriaArray[i].id;
			}
		}

		$scope.categoriaActual.id = may + 1;
		$scope.categoriaActual.denominacion = " ";

		$scope.botones = "editable";
		$scope.sololectura = false;
		$scope.navegacion = false;
	};

	$scope.buscar = function() {
		$scope.vista = "listado";
	};
	
	$scope.formulario = function() {
		$scope.vista = "formulario";
	};

	$scope.editar = function() {
		$scope.edit = true;
		$scope.botones = "editable";
		$scope.sololectura = false;
		$scope.navegacion = false;
	};

	$scope.cancelar = function() {
		$scope.edit = false;
		$scope.botones = "noeditable";
		$scope.sololectura = true;
		$scope.navegacion = true;
		$scope.listar();
		$scope.primero();
	};

	$scope.seleccionar = function(id) {
		var idx = -1;
		var categoriaArray = eval($scope.categorias);
		for (var i = 0; i < categoriaArray.length; i++) {
			/*busca la categoria en el json donde el id es igual al id que se le pasa*/
			if (categoriaArray[i].id === id) {
				idx = i;
				$scope.categoriaActual = $scope.categorias[idx];
				break;
			}
		}
		
		$scope.vista = "formulario";
		toastr.success('Exito.', 'Registro Seleccionado', {timeOut:1000});
	};

	$scope.guardar = function() {

		if ($scope.edit === false) {
			$http({
				method : 'POST',
				url : '/apicategoria/save',
				data : {
					'id' : $scope.categoriaActual.id,
					'denominacion' : $scope.categoriaActual.denominacion
				}
			}).success(function(data, status, headers, config) {
				 toastr.success('Exito.', 'Registro Guardado', {timeOut: 1000});
				$scope.listar();
			}).error(function(data, status, headers, config) {
				$scope.status = status;
			});
		}
		if ($scope.edit === true) {
			$http({

				method : 'PUT',
				url : '/apicategoria/update/' + $scope.categoriaActual.id,
				data : {
					'id' : $scope.categoriaActual.id,
					'denominacion' : $scope.categoriaActual.denominacion,
				}
			}).success(function(data, status, headers, config) {
				 toastr.success('Exito.', 'Registro Actualizado', {timeOut:
				 1000});
				$scope.listar();
			}).error(function(data, status, headers, config) {
				$scope.status = status;
			});

		}

		$scope.primero();
		$scope.botones = "noeditable";
		$scope.sololectura = true;
		$scope.navegacion = true;
	};

	$scope.borrar = function() {
		var x = confirm("Esta seguro de eliminar este registro");
		if (x) {
			$http({
				method : 'DELETE',
				url : '/apicategoria/delete/' + $scope.categoriaActual.id,
				data : {
					'id' : $scope.categoriaActual.id,
				}
			}).success(function(data, status, headers, config) {
				 toastr.success('Exito.', 'Registro Eliminado', {timeOut:
				 50});
				$scope.listar();
			}).error(function(data, status, headers, config) {
				$scope.status = status;
			});
		}
		else {
		}

		$scope.primero();

	};

	$scope.primero = function() {
		idx = 0;
		$scope.categoriaActual = $scope.categorias[idx];
	};

	$scope.ultimo = function() {
		// El ultimo elemento se determina por la longitud del array (lenght)
		idx = $scope.categorias.length - 1;
		$scope.categoriaActual = $scope.categorias[idx];
	};
} ]);
