module.exports = {
    name: 'index',
    url: '/',
    data: {
        visibleWhenNotLoggedIn: true
    },
    views: {
        'top': require('../view/top/empty-view'),
        'main': require('../view/main/index-view'),
        'right': require('../view/right/menu-view')
    }
}