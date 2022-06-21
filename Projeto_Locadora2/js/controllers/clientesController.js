angular.module("alugaMovie").controller('clientesController', function ($scope, clientesService) {
    $scope.app = "clientes"

    $scope.cliente = {};

    $scope.clientes = [];

    //Lista Clientes
    listaClientes = function () {
        clientesService.getClientes().then(function (response) {
            $scope.clientes = response.data.content;
        }).catch(function () {
            console.log('Ocorreu um Problema')
        });
    }

    listaClientes();

    //Adicionar um Novo Cliente
    $scope.adicionarCliente = function (cliente) {
        console.log(cliente)
        clientesService.adicionarCliente(cliente).then(function () {
            listaClientes();
            console.log("Tudo Funcionando!")
        }).catch(response => {
            console.log("Houve um Problema")

        });

        delete $scope.cliente;
    };


    //Apaga um Cliente
    $scope.apagarClientes = function (clientes) {
        var ClientesSelecionados = clientes.filter(function (cliente) {
            if (cliente.selecionado) {
                return cliente;
            }

        });
        for (var i = 0; i < ClientesSelecionados.length; i++) {

            clientesService.apagarCliente(ClientesSelecionados[i]).then(function (response) {
                console.log("Tudo Funcionando!", response)
            }).catch(response => {
                console.log("Houve um Problema", response.data)
            });
        }
    };

    //Edita clientes ja existentes
    $scope.editarCliente = function (cliente) {
        $scope.cliente = angular.copy(cliente);
        console.log(cliente)
    }

    //Verifica se o cliente esta selecionado
    $scope.isClienteSelecionado = function (clientes) {
        return clientes.some(function (cliente) {
            return cliente.selecionado;
        })
    };


})
