module.exports = {
    templateUrl: "/partial/main/map.html",
    resolve: {
        position: function ($http) {
            return $http.get('/character/position').then(function(response) {
                return response.data.data;
            });
        }
    },
    controller: function ($scope, $http, position) {
        $scope.position = position;
        $scope.objects = position.spawnList;

        $scope.move = function(direction) {
            var directionData = {
                'direction': direction
            };

            $http.post('/character/move', directionData).success(function (data, status, headers, config) {
                if(data.data.success) {
                    $scope.$broadcast('position', data.data);
                }

                $scope.objects = data.data.spawnList;
                //TODO: Broadcast the result to the map, show the monsters etc on the new tile
            });
        }

        $scope.attack = function(target) {
            console.log(target);
        }
    }
};