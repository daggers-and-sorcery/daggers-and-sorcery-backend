'use strict';

module.exports = function ($scope, journalInfo) {
    $scope.type = 'item';
    $scope.list = journalInfo.data.data.journal_info;

    console.log(journalInfo.data.data.journal_info);
};