var app = angular.module("mcliente", []);
app.controller("clienteCtrl", [ '$scope', '$http', function($scope, $http) {

	// Variable inicial para el indice del array
	var idx = 0;
	$scope.objmax = {};
	$scope.clientes = [];
	$scope.clienteActual = {};

	$scope.vista = "formulario";
	$scope.navegacion = true;
	$scope.edit = false;
	$scope.sololectura = true;
	$scope.botones = "noeditable";

	$http({
		method : 'GET',
		url : '/apicliente/all'
	}).success(function(data) {
		$scope.clientes = data;
	});

	$scope.listar = function() {
		$http({
			method : 'GET',
			url : '/apicliente/all'
		}).success(function(data) {
			$scope.clientes = data;
		});
	};

	$scope.nuevo = function() {

		var idx = -1;
		var clienteArray = eval($scope.clientes);
		var may = clienteArray[0].id

		for (var i = 0; i < clienteArray.length; i++) {
			if (clienteArray[i].id > may) {
				may = clienteArray[i].id;
			}
		}

		$scope.clienteActual.id = may + 1;
		$scope.clienteActual.apellidos = " ";
		$scope.clienteActual.nombres = " ";
		$scope.clienteActual.direccion = " ";
		$scope.clienteActual.dni = " ";

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
		var clienteArray = eval($scope.clientes);
		for (var i = 0; i < clienteArray.length; i++) {
			if (clienteArray[i].id === id) {
				idx = i;
				$scope.clienteActual = $scope.clientes[idx];
				break;
			}
		}
		toastr.success('Exito.', 'Registro Seleccionado', {timeOut: 50});
		$scope.vista = "formulario";
	};

	$scope.guardar = function() {

		if ($scope.edit === false) {
			$http({
				method : 'POST',
				url : '/apicliente/save',
				data : {
					'id' : $scope.clienteActual.id,
					'apellidos' : $scope.clienteActual.apellidos,
					'nombres' : $scope.clienteActual.nombres,
					'direccion' : $scope.clienteActual.direccion,
					'dni' : $scope.clienteActual.dni
				}
			}).success(function(data, status, headers, config) {
				toastr.success('Exito.', 'Registro Guardado', {timeOut: 50});
				$scope.listar();
				$scope.vista = "listado";
			}).error(function(data, status, headers, config) {
				$scope.status = status;
			});
		}
		if ($scope.edit === true) {
			$http({

				method : 'PUT',
				url : '/apicliente/update/' + $scope.clienteActual.id,
				data : {
					'id' : $scope.clienteActual.id,
					'apellidos' : $scope.clienteActual.apellidos,
					'nombres' : $scope.clienteActual.nombres,
					'direccion' : $scope.clienteActual.direccion,
					'dni' : $scope.clienteActual.dni
				}
			}).success(function(data, status, headers, config) {
				toastr.success('Exito.', 'Registro Actualizado', {timeOut:50});
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
				url : '/apicliente/delete/' + $scope.clienteActual.id,
				data : {
					'id' : $scope.clienteActual.id,
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
		$scope.clienteActual = $scope.clientes[idx];
	};

	$scope.ultimo = function() {
		// El ultimo elemento se determina por la longitud del array (lenght)
		idx = $scope.clientes.length - 1;
		$scope.clienteActual = $scope.clientes[idx];
	};
} ]);
