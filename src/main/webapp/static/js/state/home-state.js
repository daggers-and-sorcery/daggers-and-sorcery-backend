'use strict';

module.exports = {
    name: 'home',
    url: '/home/',
    views: {
        'top': require('view/top/empty-view.js'),
        'main': require('view/main/index-view.js'),
        'right': require('view/right/menu-view.js')
    }
};