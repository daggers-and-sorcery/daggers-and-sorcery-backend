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

        //$scope.targetType = "number";
        $scope.refreshTarget();

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

    $scope.refreshTarget = function() {
        if ($scope.newSetting.trigger === 'monster') {
            $scope.showMonsterSelector = true;

            $http.get('/combat/settings/specific_monsters').then(function(response) {
                $scope.monsterList = response.data.data.monsterList;

                if($scope.monsterList.length > 0) {
                    $scope.newSetting.targetMonster = $scope.monsterList[0];
                }
            });
        } else {
            $scope.showMonsterSelector = false;
            $scope.newSetting.target = 1;
        }
    };

    //Initialise
    $scope.clearNew();
};