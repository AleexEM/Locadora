var app = angular.module('alugaMovie', ['ngRoute']);

app.config(function ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);

    $routeProvider.when('/inicio', {
        controller: 'inicioController',
        templateUrl: '/templates/inicio.html'
    })

        .when('/filmes', {
            controller: 'filmesController',
            templateUrl: '/templates/filmes.html'
        })

        .when('/reservas', {
            controller: 'reservasController',
            templateUrl: 'templates/reservas.html'
        })

        .when('/clientes', {
            controller: 'clientesController',
            templateUrl: 'templates/clientes.html'
        })



    $routeProvider.otherwise({ redirectTo: '/inicio' });

});

app.controller('rotasController', function ($scope) {
    $scope.message = 'Bem Vindo a Pagina de Inicio';

});

