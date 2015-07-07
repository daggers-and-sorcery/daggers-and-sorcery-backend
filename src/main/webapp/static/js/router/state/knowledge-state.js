'use strict';

module.exports = {
    name: 'knowledge',
    url: '/knowledge/',
    data: {
        visibleWhenNotLoggedIn: true
    },
    views: {
        'top': require('view/top/empty-view'),
        'main': require('view/main/knowledge-view'),
        'right': require('view/right/menu-view')
    }
};