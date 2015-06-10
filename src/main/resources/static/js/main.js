var phonecatApp = angular.module('swordssorcery', []);

phonecatApp.controller('NewsController', function ($scope, $http, $sce) {
    $http.get('/news/last').success(function(data, status, headers, config) {
        for(i = 0; i < data.length; i++) {
            data[i].message = $sce.trustAsHtml(data[i].message);
            data[i].title = $sce.trustAsHtml(data[i].title);
        }

        $scope.newslist = data;
    });
});

phonecatApp.controller('LoginController', function ($scope, $http) {
    $scope.user = {};

    $scope.submit = function() {
        $http.post('/user/login', $scope.user).success(function(data, status, headers, config) {
        });
    };
});