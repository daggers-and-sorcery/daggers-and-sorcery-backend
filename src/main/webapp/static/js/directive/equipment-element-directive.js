'use strict';

module.exports = require('js/app.js').directive('equipmentElement', function () {
    return {
        restrict: 'A',
        scope: {
            equipmentValue: '=equipmentValue',
            equipmentName: '=equipmentName'
        },
        controller: function ($scope) {
             $scope.inventoryPopover = {
                 templateUrl: '/partial/popover/inventory.html',
             };

             //To use in inventory popup
             $scope.item = {
                definition: $scope.equipmentValue.description
             }
        },
        template: require('partial/directive/equipment-element.html')
    }
});