'use strict';

module.exports = function ($scope, $state, $http, pagedata) {
    $scope.pagedata = pagedata;

    $scope.goBack = function () {
        $state.go('character');
    };

    $scope.unidentify = function (itemId) {
        $http.get('/item/unidentify/' + itemId).then(function (response) {
            console.log(response.data.data.result);
            $scope.result = response.data.data.result;
        });
    };
};