angular.module("alugaMovie").service('filmesService', function ($http) {

    var url = "filmes";

    filmes = []


    var carregarFilmes = function () {
        $http.get("http://localhost:8080/" + url).
            then(function (response) {
                $scope.filme = response.data;
                console.log(data);
            })
    };



    return {
        getFilmes: function () {
            return $http.get("http://localhost:8080/" + url);
        },

        getDisponiveis: function () {
            return $http.get("http://localhost:8080/" + url + '/disponiveis');
        },

        adicionarFilme: function (filme) {
            return $http.post("http://localhost:8080/" + url, filme);
        },

        apagarFilme: function (filme) {
            return $http.delete("http://localhost:8080/" + url + '/' + filme.id)
        },
        
        editarFilme: function (filme) {
            return $http.put("http://localhost:8080/" + url, filme)
        }
    }
})

