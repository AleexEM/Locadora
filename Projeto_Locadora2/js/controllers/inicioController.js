angular.module("alugaMovie").controller('inicioController', function ($scope, filmesService) {
    $scope.app = "inicio"


$scope.filmes = [];

//LIsta todos os Filmes
filmesService.getFilmes().then(function (response) {
    $scope.filmes =  response.data.content;
 }).catch(function () {
     console.log('deu erro')
 });






})