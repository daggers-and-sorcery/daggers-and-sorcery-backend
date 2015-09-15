'use strict';

module.exports = {
    name: 'journal_entry',
    url: '/journal/:type/:entryId',
    templateUrl: "/partial/main/journal_entry.html",
    resolve: {
        journalEntryInfo: function($http, $stateParams) {
            console.log($stateParams);
            return $http({method: 'GET', url: '/journal/entry/'+$stateParams.type+'/'+$stateParams.entryId});
        }
    },
    controller: require('js/controller/journal-entry-controller.js')
};