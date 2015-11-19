'use strict';

module.exports = require('js/app.js').directive('spellListColumn', function () {
    return {
        restrict: 'E',
        scope: {
            columnAlignId: '=columnAlignId',
            columnAlign: '@columnAlign',
            spellist: '=source'
        },
        controller: function ($scope, $http, $rootScope, $state) {
            $scope.spellPopover = {
                templateUrl: '/partial/popover/spell.html'
            };

            $scope.cast = function (spell) {
                $http.get('/spell/cast/' + spell).then(function (response) {
                    if (response.data.data.success) {
                        $rootScope.$broadcast('profile-update-needed');
                    }
                });
            };

            $scope.openPage = function (spell) {
                $state.go("spellpage", {'spell': spell});
            };
        },
        template: require('partial/directive/spell-entry-column.html')
    };
});