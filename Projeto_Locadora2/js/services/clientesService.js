angular.module("alugaMovie").service('clientesService', function ($http) {

    var url = "clientes";

    clientes = []
    

    

    var carregarClientes = function () {
        $http.get("http://localhost:8080/" + url).
            then(function (response) {
                $scope.cliente = response.data;
                console.log(data);
            })
    };


    return {
        getClientes: function () {
            return $http.get("http://localhost:8080/" + url);
        },

        adicionarCliente: function (cliente) {
            return $http.post("http://localhost:8080/" + url, cliente);
        },

        apagarCliente: function (cliente) {
            return $http.delete("http://localhost:8080/" + url + '/' + cliente.id)
        },
        
        editarCliente: function (cliente) {
            return $http.put("http://localhost:8080/" + url, cliente)
        }
    }
})


