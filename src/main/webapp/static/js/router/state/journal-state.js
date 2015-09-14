'use strict';

module.exports = {
    name: 'journal',
    url: '/journal/',
    templateUrl: "/partial/main/journal.html",
    resolve: {
        journalInfo: function ($http) {
            return $http({method: 'GET', url: '/journal/list/item'});
        }
    },
    controller: require('js/controller/journal-controller.js')
};