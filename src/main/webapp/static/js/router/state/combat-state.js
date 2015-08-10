'use strict';

module.exports = {
    name: 'combat',
    url: '/combat/',
    params: {
        'combatMessages': null
    },
    views: {
        'top': require('js/view/top/empty-view.js'),
        'main': require('js/view/main/combat-view.js'),
        'right': require('js/view/right/menu-view.js')
    }
};