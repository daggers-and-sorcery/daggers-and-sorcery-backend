'use strict';

module.exports = {
    name: 'registration',
    url: '/registration/',
    data: {
        visibleWhenNotLoggedIn: true
    },
    views: {
        'top': require('view/top/empty-view'),
        'main': require('view/main/registration-view'),
        'right': require('view/right/menu-view')
    }
};