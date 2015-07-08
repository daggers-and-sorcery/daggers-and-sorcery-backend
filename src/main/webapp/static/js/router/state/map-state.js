'use strict';

module.exports = {
    name: 'map',
    url: '/map/',
    views: {
        'top': require('view/top/empty-view'),
        'main': require('view/main/map-view'),
        'right': require('view/right/menu-view')
    }
};