'use strict';

module.exports = function ($scope, $http, shopData) {
    $scope.shopPage = 'shop';
    $scope.shopData = shopData;

    $scope.setShopPage = function (value) {
        $scope.shopPage = value;
    };

    $scope.buyItem = function (itemId) {
        $http.get('/shop/' + $scope.shopData.shopDefinition.id + '/buy/' + itemId).then(function () {
            $scope.refreshShop();
        });
    };

    $scope.refreshShop = function () {
        $http.get('/shop/' + $scope.shopData.shopDefinition.id).then(function (response) {
            $scope.shopData = response.data.data;
        })
    };
};