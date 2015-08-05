'use strict';

module.exports = {
    name: 'combat',
    url: '/combat/',
    params: {
        'combatMessages': null
    },
    views: {
        'top': require('view/top/empty-view.js'),
        'main': require('view/main/combat-view.js'),
        'right': require('view/right/menu-view.js')
    }
};