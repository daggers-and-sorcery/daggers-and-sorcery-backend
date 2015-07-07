'use strict';

module.exports = {
    name: 'character',
    url: '/character/',
    views: {
        'top': require('view/top/empty-view'),
        'main': require('view/main/character-view'),
        'right': require('view/right/menu-view')
    }
};