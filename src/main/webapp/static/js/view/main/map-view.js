module.exports = {
    templateUrl: "/partial/main/map.html",
    resolve: {
    },
    controller: function ($scope) {
        $scope.move = function(dir) {
            //$broadcast;
            console.log(dir);
        }
    }
};