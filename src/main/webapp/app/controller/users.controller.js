"use strict";

angular.module("demo")
    .controller("UsersController", ["$scope", "Users", function ($scope, Users) {

        $scope.users = [];
        var userCloned = [];

        Users.get().then(function (resp) {

            $scope.users = resp.data;
            userCloned = angular.copy($scope.users);


        }).catch(function (err) {
            console.log(err);
        });


        function caricaUser() {
            Users.get().then(function (resp) {
             
                $scope.users = resp.data;
                userCloned = angular.copy($scope.users);


            }).catch(function (err) {
                console.log(err);
             
            });
        }


        $scope.clikkami = function () {
            console.log($scope.txtSearch);
           

            if (!$scope.txtSearch) {
                $scope.users = userCloned;
                return;
            }

            var matches = [];

            userCloned.forEach(element => {
                if (element.name.toLowerCase().indexOf($scope.txtSearch.toLowerCase()) > -1) {
                    matches.push(element);
                }
            });

            $scope.users = matches;

        }

    }]);