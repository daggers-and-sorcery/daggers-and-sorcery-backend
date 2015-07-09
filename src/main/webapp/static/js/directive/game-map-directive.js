'use strict';

module.exports = require('app.js').directive('gameMap', function($injector) {
  return {
    template: '<div id="game-map"></div>',
    link: function(scope, ele, attrs) {
         createGame(scope, $injector);
    }
  }
});