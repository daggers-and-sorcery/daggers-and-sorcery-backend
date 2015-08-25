'use strict';

module.exports = {
    name: 'combat',
    url: '/combat/',
    params: {
        'combatMessages': null
    },
    templateUrl: "/partial/main/combat.html",
    controller: function ($scope, $stateParams, $sce) {
        var finalMessages = {};

        angular.forEach($stateParams.combatMessages, function(value, key) {
            var message = value.messageData.message;

            angular.forEach(value.messageData, function(value, key) {
                message = message.replace("${"+key+"}", '<b>'+value+'</b>');
            });

            finalMessages[key] = {
                'message': $sce.trustAsHtml(message)
            };

            if(value.messageData.icon != undefined) {
                finalMessages[key].icon = '/icon/'+value.messageData['icon']+'.svg';
            }

            if(value.messageData.icon_color != undefined) {
                finalMessages[key].icon_color = value.messageData['icon_color']+'-icon-color';
            }
        });
            console.log(finalMessages);

        $scope.combatMessages = finalMessages;
    }
};