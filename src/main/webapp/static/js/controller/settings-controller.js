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

        //TODO: set the first item ad default

        $scope.usableItems = [];
        $http.get('/combat/settings/usable/item').then(function(response) {
            $scope.usableItems = response.data.data.itemList;

            if($scope.usableItems.length > 0) {
                $scope.newSetting.use = $scope.usableItems[0].id;
            }
        });

        $scope.newSetting.trigger = "turn";

        $scope.targetType = "number";
        $scope.newSetting.target = 10;

        console.log("cleared: " + $scope.newSetting)
    };

    //Initialise
    $scope.clearNew();
};