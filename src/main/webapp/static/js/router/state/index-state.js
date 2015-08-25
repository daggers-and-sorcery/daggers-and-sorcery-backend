'use strict';

module.exports = {
    name: 'index',
    url: '/',
    data: {
        visibleWhenNotLoggedIn: true
    },
    templateUrl: "/partial/main/index.html",
    resolve: {
        newslist: function ($http) {
            return $http({method: 'GET', url: '/news/last'});
        }
    },
    controller: require('js/controller/index-controller.js')
};