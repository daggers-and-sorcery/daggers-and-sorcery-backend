module.exports = {
    templateUrl: "/partial/main/map.html",
    resolve: {
    },
    controller: function ($scope, $http) {
        $scope.move = function(direction) {
            //$broadcast;
            //console.log(dir);
            var directionData = {
                'direction': direction
            };

            $http.post('/character/move', directionData).success(function (data, status, headers, config) {
                console.log(data);
                //Broadcast the result to the map, show the monsters etc on the new tile
            });
        }
    }
};