'use strict';

module.exports = require('app.js').config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state(require('router/state/index-state.js'))
        .state(require('router/state/home-state.js'))
        .state(require('router/state/logout-state.js'))
        .state(require('router/state/registration-state.js'))
        .state(require('router/state/knowledge-state.js'))
        .state(require('router/state/character-state.js'))
        .state(require('router/state/map-state.js'));
});