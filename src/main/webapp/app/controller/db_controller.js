"use strict"

angular.module('app', [])
    .controller('dbController', function ($scope, $http) {
        $http.get('http://localhost:8080/blog/rest/post').
            then(function (response) {
                $scope.post = response.data;
            });
        $http.get('http://localhost:8080/blog/rest/categoria').
            then(function (response) {
                $scope.categoria = response.data;
            });
        $http.get('http://localhost:8080/blog/rest/tags').
            then(function (response) {
                $scope.tag = response.data;
            });
    });

