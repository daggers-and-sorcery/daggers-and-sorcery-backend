'use strict';

module.exports = {
    templateUrl: '/partial/main/character.html',
    resolve: {
        characterData: function ($http, characterDataFormatter) {
            return $http.get('/character/info').then(function(response) {
                return characterDataFormatter.format(response.data);
            });
        }
    },
    controller: require('js/controller/character-controller.js')
};