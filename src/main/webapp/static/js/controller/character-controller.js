'use strict';

module.exports = function ($scope, $http, ATTRIBUTE_BONUS_MAP, characterData, $rootScope, characterDataFormatter) {
     $scope.attributePopoverUrl = '/partial/popover/attribute.html';
     $scope.user = characterData;
     $scope.attributeBonusNameMap = ATTRIBUTE_BONUS_MAP;

     $scope.$on('profile-update-needed', function(event, args) {
         $http.get('/character/info').then(function(response) {
               $scope.user = characterDataFormatter.format(response.data);
         });
     });

     $scope.inventoryPopover = {
         templateUrl: '/partial/popover/inventory.html',
     };

     $scope.unequip = function(slot) {
         $http.get('/unequip/'+slot).then(function(response) {
             if(response.data.data.success) {
                 $rootScope.$broadcast('profile-update-needed');
             } else {
                 //TODO: error happened
             }
         });
     }
 };