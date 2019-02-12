var app = angular.module("mproducto", []);
app.controller("productoCtrl", [ '$scope', '$http', function($scope, $http) {

	// Variable inicial para el indice del array
	var idx = 0;
	$scope.objmax = {};
	$scope.productos = [];
	$scope.categorias = [];
	$scope.productoActual = {};

	$scope.vista = "formulario";
	$scope.navegacion = true;
	$scope.edit = false;
	$scope.sololectura = true;
	$scope.botones = "noeditable";
	$scope.combocategoria = "noeditable";

	$http({
		method : 'GET',
		url : '/apiproducto/all'
	}).success(function(data) {
		$scope.productos = data;
	});
	
	$http({
		method : 'GET',
		url : '/apicategoria/all'
	}).success(function(datax) {
		$scope.categorias = datax;
	});

	$scope.listar = function() {
		$http({
			method : 'GET',
			url : '/apiproducto/all'
		}).success(function(data) {
			$scope.productos = data;
		});
	};

	$scope.nuevo = function() {

		var idx = -1;
		var productoArray = eval($scope.productos);
		var may = productoArray[0].id

		for (var i = 0; i < productoArray.length; i++) {
			if (productoArray[i].id > may) {
				may = productoArray[i].id;
			}
		}

		$scope.productoActual.id = may + 1;
		$scope.productoActual.nombre = "";
		$scope.productoActual.stock = "";
		$scope.productoActual.precio = "";
		$scope.productoActual.categoria = "";

		$scope.botones = "editable";
		$scope.combocategoria = "editable";
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
		$scope.combocategoria = "editable";
		$scope.sololectura = false;
		$scope.navegacion = false;
	};

	$scope.cancelar = function() {
		$scope.edit = false;
		$scope.botones = "noeditable";
		$scope.combocategoria = "noeditable";
		$scope.sololectura = true;
		$scope.navegacion = true;
		$scope.listar();
		$scope.primero();
	};

	$scope.seleccionar = function(id) {
		var idx = -1;
		var productoArray = eval($scope.productos);
		for (var i = 0; i < productoArray.length; i++) {
			/*busca el producto en el json donde el id es igual al id que se le pasa*/
			if (productoArray[i].id === id) {
				idx = i;
				$scope.productoActual = $scope.productos[idx];
				break;
			}
		}
		$scope.vista = "formulario";
	};

	$scope.guardar = function() {

		if ($scope.edit === false) {
			$http({
				method : 'POST',
				url : '/apiproducto/save',
				data : {
					'id' : $scope.productoActual.id,
					'nombre' : $scope.productoActual.nombre,
					'stock' : $scope.productoActual.stock,
					'precio' : $scope.productoActual.precio,
					'categoria' : $scope.productoActual.categoria
				}
			}).success(function(data, status, headers, config) {
				toastr.success('Exito.', 'Registro Guardado', {timeOut: 1000});
				console.log(data);
				$scope.listar();
				$scope.vista = "listado";
				
			}).error(function(data, status, headers, config) {
				$scope.status = status;
			});
		}
		if ($scope.edit === true) {
			$http({

				method : 'PUT',
				url : '/apiproducto/update/' + $scope.productoActual.id,
				data : {
					'nombre' : $scope.productoActual.nombre,
					'stock' : $scope.productoActual.stock,
					'precio' : $scope.productoActual.precio,
					'categoria' : $scope.productoActual.categoria
				}
			}).success(function(data, status, headers, config) {
				toastr.success('Exito.', 'Registro Actualizado', {timeOut: 1000});
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
				method : 'PUT',
				url : '/apiproducto/delete/' + $scope.productoActual.id,
				data : {
					'id' : $scope.productoActual.id,
				}
			}).success(function(data, status, headers, config) {
				 toastr.success('Exito.', 'Registro Eliminado', {timeOut:1000});
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
		$scope.productoActual = $scope.productos[idx];
	};

	$scope.ultimo = function() {
		// El ultimo elemento se determina por la longitud del array (lenght)
		idx = $scope.productoss.length - 1;
		$scope.productoActual = $scope.productos[idx];
	};
} ]);
