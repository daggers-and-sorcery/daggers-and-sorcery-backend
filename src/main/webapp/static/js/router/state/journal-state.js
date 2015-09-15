'use strict';

module.exports = {
    name: 'journal',
    url: '/journal/:type',
    templateUrl: "/partial/main/journal.html",
    params: {
        type: {
            value: 'item'
        }
    },
    resolve: {
        journalInfo: function ($http, $stateParams) {
            return $http({method: 'GET', url: '/journal/list/'+$stateParams.type});
        }
    },
    controller: require('js/controller/journal-controller.js')
};