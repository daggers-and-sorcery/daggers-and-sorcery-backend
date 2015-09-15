'use strict';

module.exports = require('js/app.js').directive('inventoryListColumn', function () {
    return {
        restrict: 'E',
        scope: {
            columnAlignId: '=columnAlignId',
            columnAlign: '@columnAlign',
            inventory: '=source'
        },
        controller: function ($scope, $http, $rootScope) {
            $scope.inventoryPopover = {
                templateUrl: '/partial/popover/inventory.html'
            };

            $scope.equip = function(itemId) {
                $http.get('/equip/'+itemId).then(function(response) {
                    if(response.data.data.success) {
                        $rootScope.$broadcast('profile-update-needed');
                    } else {
                        $rootScope.$broadcast('error', {message: 'You can\'t equip that item!'});
                    }
                });
            };
        },
        template: require('partial/directive/inventory-item-column.html')
    };
});