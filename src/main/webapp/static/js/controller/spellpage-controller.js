'use strict';

module.exports = function ($scope, $state, $http, $stateParams, pagedata) {
    $scope.pagedata = pagedata;

    $scope.goBack = function () {
        $state.go('character');
    };

    $scope.unidentify = function (itemId) {
        var requestConfig = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        };

        $http.post('/spell/cast/3', $.param({'itemId': itemId}), requestConfig).then(function (response) {
            $scope.result = response.data.data.success;

            $scope.reload();
        });
    };

    $scope.reload = function () {
        return $http({method: 'GET', url: '/spell/page/' + $stateParams.spell}).then(function (response) {
            $scope.pagedata = response.data.data;
        });
    }
};