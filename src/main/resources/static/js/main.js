var swordssorceryApp = angular.module('swordssorcery', ['ui.router']);

swordssorceryApp.config(function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise("/")
    $stateProvider.state('index', {
        url: "/",
        views: {
            "main": {
                templateUrl: "/sub/index.html",
                controller: function($scope, $http, $sce) {
                    $http.get('/news/last').success(function(data, status, headers, config) {
                        for(i = 0; i < data.length; i++) {
                             data[i].message = $sce.trustAsHtml(data[i].message);
                             data[i].title = $sce.trustAsHtml(data[i].title);
                        }

                        $scope.newslist = data;
                    });
                }
            },
            "right": {
                templateUrl: "/sub/login.html",
                controller: function($scope){
                    $scope.user = {};

                    $scope.submit = function() {
                        $http.post('/user/login', $scope.user).success(function(data, status, headers, config) {
                        });
                    };
                },
            }
        }
    });
});