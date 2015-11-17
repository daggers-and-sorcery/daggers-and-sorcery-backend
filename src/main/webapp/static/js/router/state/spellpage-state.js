'use strict';

module.exports = {
    name: 'spellpage',
    url: '/spell/:spell/page',
    params: {
        'spell': null
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