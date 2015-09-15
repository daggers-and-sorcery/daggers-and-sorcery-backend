'use strict';

module.exports = function ($scope, $http, $stateParams, journalInfo) {
    $scope.type = $stateParams.type;
    $scope.list = journalInfo.data.data.journal_info;

    $scope.changeType = function (newType) {
        if($scope.type !== newType) {
            $http({method: 'GET', url: '/journal/list/'+newType}).then(function(response) {
                $scope.type = newType;
                $scope.list = response.data.data.journal_info;
            });
        }
    };
};