'use strict';

module.exports = require('js/app.js').factory('combatUpdate', function($rootScope) {
     var myInterceptor = {
          response: function(response) {
              if(response.data.charinfo !== undefined) {
                console.log(response.data.charinfo);
                console.log($rootScope.user);

                $rootScope.user.mana = response.data.charinfo.mana;
                $rootScope.user.life = response.data.charinfo.health;
                $rootScope.user.movement = response.data.charinfo.movement;
              }

              return response;
          }
     };

     return myInterceptor;
 });