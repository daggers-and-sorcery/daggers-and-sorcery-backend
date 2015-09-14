'use strict';

module.exports = require('js/app.js').config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('combatUpdate');
}]);