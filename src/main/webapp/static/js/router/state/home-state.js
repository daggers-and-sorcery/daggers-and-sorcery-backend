'use strict';

module.exports = {
    name: 'home',
    url: '/home/',
    views: {
        'top': require('js/view/top/empty-view.js'),
        'main': require('js/view/main/index-view.js'),
        'right': require('js/view/right/menu-view.js')
    }
};