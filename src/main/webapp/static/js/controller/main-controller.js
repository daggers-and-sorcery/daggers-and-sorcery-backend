'use strict';

module.exports = require('js/app.js').controller('MainController', function ($scope, $rootScope, $state, $http) {
    //Get user info at start
    $http.get('/user/info').success(function (data, status, headers, config) {
        $rootScope.loggedIn = data.loggedIn === 'true';
        $state.go('home');
    });

    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        //Logout the user
        if (toState.name === 'logout') {
            $http.get('/user/logout').success(function (data, status, headers, config) {
                $rootScope.loggedIn = false;
                $state.go('index');
            });
            event.preventDefault();
        }

        //Always redirect to index if not logged in
        if (!(toState.hasOwnProperty('data') && toState.data.hasOwnProperty('visibleWhenNotLoggedIn') && toState.data.visibleWhenNotLoggedIn) && !$rootScope.loggedIn && toState.name !== 'index') {
            event.preventDefault();
            $state.go('index');
        }

        //If logged in redirect index to home
        if (toState.name === 'index' && $rootScope.loggedIn) {
            event.preventDefault();
            $state.go('home');
        }
    })
});