angular.module("alugaMovie").service('reservasService', function ($http) {

    var url = "reservas"
    reservas = []



    var carregarReservas = function () {
        $http.get("http://localhost:8080/" + url).
            then(function (response) {
                $scope.filme = response.data;
                console.log(data);
            })
    }

    return {
        getReservas: function () {
            return $http.get("http://localhost:8080/" + url);
        },

        getReservados: function () {
            return $http.get("http://localhost:8080/" + url + '/reservados');
        },

        adicionarReserva: function (reserva) {
            return $http.post("http://localhost:8080/" + url, reserva);
        },

        devolverReserva: function (reserva) {
            return $http.put("http://localhost:8080/" + url + '/' + reserva.id + '/devolver', {})
        },

    }

})
