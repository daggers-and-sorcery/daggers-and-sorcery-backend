'use strict';

module.exports = require('js/app.js').config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state(require('js/router/state/index-state.js'))
        .state(require('js/router/state/home-state.js'))
        .state(require('js/router/state/logout-state.js'))
        .state(require('js/router/state/registration-state.js'))
        .state(require('js/router/state/knowledge-state.js'))
        .state(require('js/router/state/character-state.js'))
        .state(require('js/router/state/map-state.js'))
        .state(require('js/router/state/journal-state.js'))
        .state(require('js/router/state/journal-entry-state.js'))
        .state(require('js/router/state/settings-state.js'))
        .state(require('js/router/state/combat-state.js'))
        .state(require('js/router/state/spellpage-state.js'));
});