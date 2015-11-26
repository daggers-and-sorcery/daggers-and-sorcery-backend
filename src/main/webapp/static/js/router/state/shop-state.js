'use strict';

module.exports = {
    name: 'shop',
    url: '/shop/:shopId',
    template: require('partial/main/shop.html'),
    resolve: {
        shopData: function ($http, $stateParams) {
            return $http.get('/shop/' + $stateParams.shopId).then(function (response) {
                return response.data;
            });
        }
    },
    controller: require('js/controller/shop-controller.js')
};