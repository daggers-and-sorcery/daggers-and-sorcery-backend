'use strict';

module.exports = require('app.js').directive('inventoryListColumn', function () {
    return {
        restrict: 'E',
        scope: {
            columnAlignId: '=columnAlignId',
            columnAlign: '@columnAlign',
            inventory: '=source'
        },
        controller: function ($scope) {
            $scope.inventoryPopover = {
                templateUrl: '/partial/popover/inventory.html',
            };
        },
        templateUrl: '/directive/inventory-item-column.html'
    };
});