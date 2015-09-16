'use strict';

module.exports = function ($scope) {
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

    $scope.save = function () {
        console.log("saved: ");
        console.log($scope.newSetting)
    };

    $scope.clear = function () {
        $scope.newSetting = {};

        $scope.newSetting.type = "item";

        //TODO: get the items usable
        //TODO: set the first item ad default

        $scope.newSetting.trigger = "turn";

        $scope.targetType = "number";
        $scope.newSetting.target = 10;

        console.log("cleared: " + $scope.newSetting)
    };

    //Initialise
    $scope.clear();
};