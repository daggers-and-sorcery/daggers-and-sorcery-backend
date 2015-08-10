'use strict';

module.exports = {
    name: 'index',
    url: '/',
    data: {
        visibleWhenNotLoggedIn: true
    },
    views: {
        'top': require('js/view/top/empty-view'),
        'main': require('js/view/main/index-view'),
        'right': require('js/view/right/menu-view')
    }
};