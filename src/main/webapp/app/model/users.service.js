"use strict";

angular.module("demo")
    .factory("Users", ["$http", function ($http) {

        var users = [{ id: 1, nome: 'Federico', cognome: 'Scamuzzi', mail: 'fede@fede.it' },
        { id: 2, nome: 'Francesco', cognome: 'Rossi', mail: 'fra@gmail.it' },
        { id: 3, nome: 'Marco', cognome: 'Rossi', mail: 'marco@gmail.it' },
        { id: 4, nome: 'Federica', cognome: 'Verdi', mail: 'federicas@gmail.it' }];


        return {
            get: function () {
                return $http.get('http://localhost:8080/blog/rest/user/');
            },
            getByID: function (id) {
                return $http.get('http://localhost:8080/blog/rest/user/' + id);
            }
        };


    }]);