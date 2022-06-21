angular.module("alugaMovie").controller('reservasController', function ($scope, filmesService, clientesService, reservasService) {
    $scope.app = "filmes"


    $scope.reserva = {};

    $scope.reservas = [];

    $scope.reservados = [];

    $scope.cliente = [];

    $scope.filme = [];


    //Metodos para carregar Listas de Filmes, Clientes e Reservas.


    //Lista Todos os Filmes
    listaFilmes = function () {
        filmesService.getFilmes().then(function (response) {
            $scope.filmes = response.data.content;
        }).catch(function () {
            console.log('Ocorreu um Problema')
        });
    }

    //Lista Todos os Filmes com o Status "Disponivel"
    listaDisponiveis = function () {
        filmesService.getDisponiveis().then(function (response) {
            $scope.disponiveis = response.data;
        }).catch(function () {
            console.log('Ocorreu um Problema')
        });
    }

    listaDisponiveis();
    listaFilmes();

    //Lista Todos os Clientes
    listaClientes = function () {
        clientesService.getClientes().then(function (response) {
            $scope.clientes = response.data.content;
        }).catch(function () {
            console.log('Ocorreu um Problema')
        });
    }

    listaClientes();

    //Lista Todas as Reservas Ativas e Inativas
    listaReservas = function () {
        reservasService.getReservas().then(function (response) {
            $scope.reservas = response.data.content;
        }).catch(function () {
            console.log('Ocorreu um Problema')
        });
    }


    //Lista Todas as Reservas Ativas
    listaReservados = function () {
        reservasService.getReservados().then(function (response) {
            $scope.reservados = response.data;
        }).catch(function () {
            console.log('Ocorreu um Problema')
        });
    }

    listaReservas();
    listaReservados();

    //=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    //Função para adicionar uma reserva a lista.  

    $scope.adicionarReserva = function (reserva) {
        var filmesSelecionados = $scope.disponiveis.filter(function (filme) {
            if (filme.selecionado) {
                return filme;
            }

        });
        for (var i = 0; i < filmesSelecionados.length; i++) {
            delete filmesSelecionados[i].selecionado

        }
        reserva.filmes = filmesSelecionados;

        reservasService.adicionarReserva(reserva).then(function () {
            listaReservas();
            listaReservados();
            listaDisponiveis();

            console.log("Tudo Funcionando!")
        }).catch(response => {
            console.log("Houve um Problema")

        });

        console.log(reserva)
    };

    //Função para devolver reserva.

    $scope.devolverReserva = function (reserva) {
        reservasService.devolverReserva(reserva).then(function () {
            listaReservas();
            listaReservados();
            listaDisponiveis();

            console.log("Tudo Funcionando!")
        }).catch(response => {
            console.log("Houve um Problema")

        })

        delete $scope.reserva;
    };

    $scope.isReservaSelecionada = function (reservas) {
        return reservas.some(function (reserva) {
            return reserva.selecionada;
        })
    };


})


