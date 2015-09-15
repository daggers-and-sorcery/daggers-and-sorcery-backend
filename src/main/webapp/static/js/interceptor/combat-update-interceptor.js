'use strict';

module.exports = require('js/app.js').factory('combatUpdate', function($rootScope) {
    return {
         response: function (response) {
             if (response.data.charinfo !== undefined && response.data.charinfo !== null) {
                 $rootScope.user.mana = response.data.charinfo.mana;
                 $rootScope.user.life = response.data.charinfo.health;
                 $rootScope.user.movement = response.data.charinfo.movement;
             }

             return response;
         }
     };
 });