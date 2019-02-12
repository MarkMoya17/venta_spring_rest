var app = angular.module("mdocumento", []);
app.controller("documentoCtrl", [ '$scope', '$http', function($scope, $http) {

	// Variable inicial para el indice del array
	var idx = 0;
	$scope.objmax = {};
	$scope.documentos = [];
	$scope.documentoActual = {};

	$scope.vista = "formulario";
	$scope.navegacion = true;
	$scope.edit = false;
	$scope.sololectura = true;
	$scope.botones = "noeditable";

	$http({
		method : 'GET',
		url : '/apidocumento/all'
	}).success(function(data) {
		$scope.documentos = data;
	});

	$scope.listar = function() {
		$http({
			method : 'GET',
			url : '/apidocumento/all'
		}).success(function(data) {
			$scope.documentos = data;
		});
	};

	$scope.nuevo = function() {

		var idx = -1;
		var documentoArray = eval($scope.documentos);
		var may = documentoArray[0].id

		for (var i = 0; i < documentoArray.length; i++) {
			if (documentoArray[i].id > may) {
				may = documentoArray[i].id;
			}
		}

		$scope.documentoActual.id = may + 1;
		$scope.documentoActual.nombre = " ";

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
		var documentoArray = eval($scope.documentos);
		for (var i = 0; i < documentoArray.length; i++) {
			if (documentoArray[i].id === id) {
				idx = i;
				$scope.documentoActual = $scope.documentos[idx];
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
				url : '/apidocumento/save',
				data : {
					'id' : $scope.documentoActual.id,
					'nombre' : $scope.documentoActual.nombre
				}
			}).success(function(data, status, headers, config) {
				toastr.success('Exito.', 'Registro Guardado', {timeOut: 1000});
				$scope.listar();
				$scope.vista = "listado";
			}).error(function(data, status, headers, config) {
				$scope.status = status;
			});
		}
		if ($scope.edit === true) {
			$http({

				method : 'PUT',
				url : '/apidocumento/update/' + $scope.documentoActual.id,
				data : {
					'id' : $scope.documentoActual.id,
					'nombre' : $scope.documentoActual.nombre
				}
			}).success(function(data, status, headers, config) {
				toastr.success('Exito.', 'Registro Actualizado', {timeOut:1000});
				$scope.listar();
				$scope.vista = "listado";
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
				url : '/apidocumento/delete/' + $scope.documentoActual.id,
				data : {
					'id' : $scope.documentoActual.id,
				}
			}).success(function(data, status, headers, config) {
				toastr.success('Exito.', 'Registro Eliminado', {timeOut:50});
				$scope.listar();
				$scope.vista = "listado";
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
		$scope.documentoActual = $scope.documento[idx];
	};

	$scope.ultimo = function() {
		// El ultimo elemento se determina por la longitud del array (lenght)
		idx = $scope.documento.length - 1;
		$scope.documentoActual = $scope.documento[idx];
	};
} ]);
