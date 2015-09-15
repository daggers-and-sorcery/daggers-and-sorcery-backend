'use strict';

module.exports = function ($scope, $stateParams, journalEntryInfo) {
    $scope.type = $stateParams.type;
    $scope.entry = journalEntryInfo.data.data.journal_entry;
    console.log(journalEntryInfo);
};