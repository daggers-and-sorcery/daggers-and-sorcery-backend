module.exports = {
    templateUrl: "/partial/main/combat.html",
    controller: function ($scope, $stateParams, $sce) {
        var finalMessages = {};

        angular.forEach($stateParams.combatMessages, function(value, key) {
            var message = value.messageData.message;

            angular.forEach(value.messageData, function(value, key) {
                message = message.replace("${"+key+"}", '<b>'+value+'</b>');
            });

            finalMessages[key] = $sce.trustAsHtml(message);
        });

        $scope.combatMessages = finalMessages;
    }
};