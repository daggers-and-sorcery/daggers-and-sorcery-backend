module.exports = {
        templateUrl: '/partial/right/menu.html',
        controller: function ($scope, $http, $state, $rootScope) {
            $scope.user = {};
            $scope.error = '';

            $scope.submit = function () {
                var requestConfig = {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    }
                };

                $http.post('/user/login', $.param($scope.user), requestConfig).success(function (data, status, headers, config) {
                    if (data.success === 'true') {
                        $rootScope.loggedIn = true;
                        $state.go('home');
                    } else {
                        $scope.error = data.error;
                    }
                });
            };
        }
}