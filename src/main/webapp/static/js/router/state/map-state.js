'use strict';

module.exports = {
    name: 'map',
    url: '/map/',
    views: {
        'top': require('js/view/top/empty-view'),
        'main': require('js/view/main/map-view'),
        'right': require('js/view/right/menu-view')
    }
};