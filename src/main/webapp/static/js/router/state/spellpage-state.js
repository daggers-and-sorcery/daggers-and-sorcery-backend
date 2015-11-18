'use strict';

module.exports = {
    name: 'spellpage',
    url: '/spell/:spell/page',
    params: {
        'spell': null
    },
    resolve: {
        pagedata: function ($http, $stateParams) {
            return $http({method: 'GET', url: '/spell/page/' + $stateParams.spell}).then(function (response) {
                return response.data.data;
            });
        }
    },
    template: function ($stateParams) {
        switch (parseInt($stateParams.spell)) {
            case 3:
                return require('partial/main/spell/3.html');
                break;
        }
    },
    controller: require('js/controller/spellpage-controller.js')
};