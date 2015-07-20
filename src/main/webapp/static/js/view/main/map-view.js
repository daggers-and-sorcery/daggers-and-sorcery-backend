module.exports = {
    templateUrl: "/partial/main/map.html",
    resolve: {
        position: function ($http) {
            return $http.get('/character/position');
        }
    },
    controller: function ($scope, $http, position) {
        $scope.$broadcast('position', position);

        $scope.move = function(direction) {
            //$broadcast;
            //console.log(dir);
            var directionData = {
                'direction': direction
            };

            $http.post('/character/move', directionData).success(function (data, status, headers, config) {
                if(data.data.success) {
                    $scope.$broadcast('position', data.data);
                }
                //Broadcast the result to the map, show the monsters etc on the new tile
            });
        }
    }
};