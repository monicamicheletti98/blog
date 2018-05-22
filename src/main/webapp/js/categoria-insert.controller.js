"use strict";

angular.module("blog")
    .controller("CategoriaInsertController", ["$scope", "$stateParams", "Categorie","$state",
        function ($scope, $stateParams, Categorie,$state) {

            $scope.categoria = {};
            $scope.insertNew = function () {

                if(!$scope.categoria.nome) return;

                Categorie.insert($scope.categoria).then(function (resp) {
                    $state.go('common.users');
                }).catch(function (err) {

                });
            }


        }]);