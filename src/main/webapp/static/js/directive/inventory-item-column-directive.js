'use strict';

module.exports = require('app.js').directive('inventoryListColumn', function () {
    return {
        restrict: 'E',
        scope: {
            columnAlignId: '=columnAlignId',
            columnAlign: '@columnAlign',
            inventory: '=source'
        },
        controller: function ($scope, $http) {
            $scope.inventoryPopover = {
                templateUrl: '/partial/popover/inventory.html',
            };

            $scope.isEquipment = function(type) {
                switch(type) {
                    case 'ONE_HANDED_SWORD':
                    case 'SHIELD':
                        return true
                }

                return false;
            };

            $scope.equip = function(itemId) {
                console.log(itemId);
                $http.get('/equip/'+itemId).then(function(response) {
                    if(response.data.data.success) {
                        //TODO: equiping succeed, set the equipment in the character page (do it with broadcast)
                    } else {
                        //TODO: error happened
                    }
                });
            };
        },
        templateUrl: '/directive/inventory-item-column.html'
    };
});