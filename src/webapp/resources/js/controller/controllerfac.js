var app = angular.module("mfactura", []);
app.controller("facturaCtrl", [ '$scope', '$http', function($scope, $http) {
	
	// Variable inicial para el indice del array
	var idx = 0;
	$scope.cantidad=0;
	$scope.number=0;
	
	var f = new Date();
	$scope.fechaActual = addZero(f.getDate()) + "-" + addZero(f.getMonth() +1) + "-" + f.getFullYear();
	
	$scope.objmax = {};
	$scope.facturas = [];
	$scope.documentos = [];
	$scope.productos = [];
	$scope.clientes = [];
	$scope.productoActual = {};
	$scope.clienteActual = {};
	$scope.facturaActual = {};
	$scope.documentoActual = {};
	
	$scope.vista = "factura";
	$scope.navegacion = true;
	$scope.edit = false;
	$scope.sololectura = true;
	$scope.botones = "noeditable";
	$scope.combocategoria = "noeditable";
	
	$scope.boton="btnNuevo";

	$scope.detalle= [];
	$scope.listaDetalle= [];
	
	$scope.btnNuevo = function (){
		
		idx = $scope.facturas.length - 1;
		$scope.facturaActual = $scope.facturas[idx];
		$scope.number_factura = $scope.facturaActual.nrofactura+1;
		$scope.id_factura = $scope.facturaActual.id+1;
		
		$scope.facturaActual = {};
		$scope.facturaActual.id=$scope.id_factura;
		$scope.facturaActual.nrofactura=$scope.number_factura;
		$scope.facturaActual.fecha='20190214';
		$scope.facturaActual.subtotal=0.00;
		$scope.facturaActual.igv=0.00;
		$scope.facturaActual.total=0.00;
		$scope.facturaActual.estado=1;
		$scope.facturaActual.cliente=$scope.clienteActual;
		$scope.facturaActual.documento=$scope.documentoActual;
//		$scope.facturaActual.detalles=$scope.detalle;
		
		console.log($scope.facturaActual);
		
		$scope.boton="btnCancelar";
	};
	
	$scope.btnCancelar = function (){
		$scope.clienteActual={};
		$scope.subtotal="";
		$scope.igv="";
		$scope.total="";
		$scope.detalle=[];
		$scope.boton="btnNuevo";
	};
	
	
	
	$http({
		method : 'GET',
		url : '/apifactura/all'
	}).success(function(data) {
		$scope.facturas = data;
	});
	
	$http({
		method : 'GET',
		url : '/apidocumento/all'
	}).success(function(data) {
		$scope.documentos = data;
	});
	
	$http({
		method : 'GET',
		url : '/apiproducto/all'
	}).success(function(data) {
		$scope.productos = data;
	});
	
	
	$http({
		method : 'GET',
		url : '/apicliente/all'
	}).success(function(data) {
		$scope.clientes = data;
	});

	$scope.listar = function() {
		$http({
			method : 'GET',
			url : '/apiproducto/all'
		}).success(function(data) {
			$scope.productos = data;
		});
	};

	$scope.buscarcliente = function() {
		$scope.vista = "clientes";
	}
	
	$scope.buscarproducto = function() {
		$scope.vista = "productos";
	}
	
		
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

	$scope.seleccionar_cliente = function(id) {
		var idx = -1;
		var clienteArray = eval($scope.clientes);
		for (var i = 0; i < clienteArray.length; i++) {
			if (clienteArray[i].id === id) {
				idx = i;
				$scope.clienteActual = $scope.clientes[idx];
				break;
			}
		}
		$scope.facturaActual.cliente=$scope.clienteActual;
		console.log($scope.facturaActual);
		$scope.vista = "factura";
	};
	
	$scope.seleccionar_documento = function(id) {
		var idx = -1;
		var documentoArray = eval($scope.documentos);
		for (var i = 0; i < documentoArray.length; i++) {
			if (documentoArray[i].id === id) {
				idx = i;
				$scope.documentoActual = $scope.documentos[idx];
				break;
			}
		}
		$scope.facturaActual.documento=$scope.documentoActual;
		console.log($scope.facturaActual);
		$scope.vista = "factura";
	};
	
	$scope.seleccionar_producto = function(id, cantidad) {
		var idx = -1;
		var productoArray = eval($scope.productos);
		for (var i = 0; i < productoArray.length; i++) {
			if (productoArray[i].id === id) {
				idx = i;
				$scope.productoActual = $scope.productos[idx];
				break;
			}
		}
		$scope.detalle.push({
			'nombre':$scope.productoActual.nombre,
			'precio': $scope.productoActual.precio,
			'cantidad':cantidad,
			'subtotal':$scope.productoActual.precio*cantidad
		});
		
		calcularIGV();
		$scope.facturaActual.total=$scope.total;
		$scope.facturaActual.subtotal=$scope.subtotal;
		$scope.facturaActual.igv=$scope.igv;
			
		$scope.listaDetalle.push({
			'producto':$scope.productoActual,
			'precio': $scope.productoActual.precio,
			'cantidad':cantidad,
			'subtotal':$scope.productoActual.precio*cantidad
		});
		
		$scope.vista = "factura";
		
	};
	
	$scope.guardar = function() {

		if ($scope.edit === false) {

			console.log($scope.facturaActual);
			console.log($scope.listaDetalle);
			
			$http({
				method : 'POST',
				url : '/apifactura/savecabecera',
				headers: {'Content-Type': 'application/json'},
				data : $scope.facturaActual
			}).success(function(data, status, headers, config) {
				

				
				$http({
					method : 'PUT',
					url : '/apifactura/savedetalle/'+$scope.facturaActual.id,
					headers: {'Content-Type': 'application/json'},
					data : $scope.listaDetalle
				}).success(function(data, status, headers, config) {
					
					toastr.success('Exito.', 'Venta Registrada', {timeOut: 2000});

					$scope.vista = "factura";
					$scope.btnCancelar();
					
				}).error(function(data, status, headers, config) {
					console.log(data);
					$scope.status = status;
				});
				
				
			}).error(function(data, status, headers, config) {
				console.log(data);
				$scope.status = status;
			});
			
			
		}


	};


	function calcularIGV(){
		var subtotal=0;
		var igv=0;
		var total=0;
		var detalle = $scope.detalle;
		for (var i = 0; i < detalle.length; i++) {
			subtotal+=detalle[i].subtotal;
		}
		$scope.total=subtotal;
		$scope.subtotal=subtotal/1.18;
		$scope.igv=$scope.total-$scope.subtotal;
		
	}
	
	function addZero(i) {
	    if (i < 10) {
	        i = '0' + i;
	    }
	    return i;
	}

	$scope.quitarproducto = function(index) {
		$scope.detalle.splice(index, 1);
		calcularIGV();
	}
	
	$scope.primero = function() {
		idx = 0;
		$scope.productoActual = $scope.productos[idx];
	};

	$scope.ultimo = function() {
		// El ultimo elemento se determina por la longitud del array (lenght)
		idx = $scope.facturas.length - 1;
		$scope.facturaActual = $scope.facturas[idx];
		$scope.number = $scope.facturaActual.nrofactura+1;
	};
	

	
	
	
} ]);
