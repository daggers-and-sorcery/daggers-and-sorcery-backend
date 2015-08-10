'use strict';

module.exports = {
    name: 'knowledge',
    url: '/knowledge/',
    data: {
        visibleWhenNotLoggedIn: true
    },
    views: {
        'top': require('js/view/top/empty-view'),
        'main': require('js/view/main/knowledge-view'),
        'right': require('js/view/right/menu-view')
    }
};