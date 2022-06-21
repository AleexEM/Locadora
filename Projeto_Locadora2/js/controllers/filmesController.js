angular.module("alugaMovie").controller('filmesController', function ($scope, filmesService) {
    $scope.app = "filmes"

    $scope.filme = {};
    $scope.filmes = [];
    $scope.disponiveis = [];

    //Lista todos os Filmes
    listaFilmes = function () {
        filmesService.getFilmes().then(function (response) {
            $scope.filmes = response.data.content;
        }).catch(function () {
            console.log('Houve um Problema')
        });
    }

    //Lista filmes com status "Disponivel"
    listaDisponiveis = function () {
        filmesService.getDisponiveis().then(function (response) {
            $scope.disponiveis = response.data;
        }).catch(function () {
            console.log('Houve um Problema')
        });
    }

    listaFilmes();
    listaDisponiveis();

    //Adiciona um novo filme
    $scope.adicionarFilme = function (filme) {
        console.log(filme)
        filmesService.adicionarFilme(filme).then(function () {
            listaFilmes();
            console.log("Tudo Funcionando!")
        }).catch(response => {
            console.log("Houve um Problema")

        });

        delete $scope.filme;
    };

    //Apaga um Filme existente
    $scope.apagarFilmes = function (filmes) {
        var filmesSelecionados = filmes.filter(function (filme) {
            if (filme.selecionado) {
                return filme;
            }
        });
        for (var i = 0; i < filmesSelecionados.length; i++) {
            filmesService.apagarFilme(filmesSelecionados[i]).then(function (response) {
                console.log("Tudo Funcionando!", response)
            }).catch(response => {
                console.log("Houve um Problema", response.data)
            });
        }
    };

    //Edita um Filme existente
    $scope.editarFilme = function (filme) {
        filmesService.editarFilme(filme).then(function () {
            listaFilmes();
            console.log("Tudo Funcionando!")
        }).catch(response => {
            console.log("Houve um Problema")

        });
        delete $scope.filme;
        $scope.filme = angular.copy(filme);

    };

    //Verifica se o Filme esta selecionado
    $scope.isFilmeSelecionado = function (filmes) {
        return filmes.some(function (filme) {
            return filme.selecionado;
        })
    };
})
