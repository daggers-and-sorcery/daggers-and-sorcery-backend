'use strict';

module.exports = {
    name: 'registration',
    url: '/registration/',
    data: {
        visibleWhenNotLoggedIn: true
    },
    views: {
        'top': require('js/view/top/empty-view'),
        'main': require('js/view/main/registration-view'),
        'right': require('js/view/right/menu-view')
    }
};