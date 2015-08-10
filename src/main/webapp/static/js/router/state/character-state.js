'use strict';

module.exports = {
    name: 'character',
    url: '/character/',
    views: {
        'top': require('js/view/top/empty-view'),
        'main': require('js/view/main/character-view'),
        'right': require('js/view/right/menu-view')
    }
};