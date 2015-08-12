'use strict';

module.exports = require('js/app.js').directive('equipmentElement', function () {
    return {
        restrict: 'A',
        scope: {
            equipmentValue: '=equipmentValue',
            equipmentName: '=equipmentName'
        },
        controller: function ($scope, $http,$rootScope) {
             $scope.inventoryPopover = {
                 templateUrl: '/partial/popover/inventory.html',
             };

             //To use in inventory popup
             $scope.item = {
                definition: $scope.equipmentValue.description
             }

            $scope.unequip = function(itemId) {
                $http.get('/unequip/'+itemId).then(function(response) {
                    if(response.data.data.success) {
                        $rootScope.$broadcast('profile-update-needed');
                    } else {
                        //TODO: error happened
                    }
                });
            };
        },
        template: require('partial/directive/equipment-element.html')
    }
});