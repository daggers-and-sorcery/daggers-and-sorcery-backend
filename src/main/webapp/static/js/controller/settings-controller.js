'use strict';

module.exports = function ($scope, $http) {
    $scope.settings = [
        {
            id: 111,
            type: "Item",
            use: "Health Potion",
            trigger: "Turn",
            target: "3"
        },
        {
            id: 12,
            type: "Spell",
            use: "Fireball",
            trigger: "Turn",
            target: "2"
        }
    ];

    $scope.remove = function (id) {
        console.log("remove: " + id);
    };

    $scope.save = function (id) {
        console.log("save: " + id);
    };

    $scope.saveNew = function () {
        console.log("saved: ");
        console.log($scope.newSetting)
    };

    $scope.clearNew = function () {
        $scope.newSetting = {};

        $scope.newSetting.type = "item";

        $scope.refreshUse();

        $scope.newSetting.trigger = "turn";

        $scope.targetType = "number";
        $scope.newSetting.target = 10;

        console.log("cleared: " + $scope.newSetting)
    };

    $scope.refreshUse = function() {
        console.log($scope.newSetting.type);
        if($scope.newSetting.type == "spell") {
            $scope.usables = [];
            $http.get('/combat/settings/usable/spell').then(function(response) {
                $scope.usables = response.data.data.spellList;

                if($scope.usables.length > 0) {
                    $scope.newSetting.use = $scope.usables[0];
                }
            });
        } else {
            $scope.usables = [];
            $http.get('/combat/settings/usable/item').then(function(response) {
                $scope.usables = response.data.data.itemList;

                if($scope.usables.length > 0) {
                    $scope.newSetting.use = $scope.usables[0];
                }
            });
        }
    };

    //Initialise
    $scope.clearNew();
};