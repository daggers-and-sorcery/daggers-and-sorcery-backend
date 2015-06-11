var swordssorceryApp = angular.module('swordssorcery', ['ui.router']);

swordssorceryApp.config(function($stateProvider, $urlRouterProvider){
    mainView = {
        templateUrl: "/sub/index.html",
        resolve: {
            newslist:  function($http){
                return $http({method: 'GET', url: '/news/last'});
            },
        },
        controller: function($scope, $http, $sce, newslist) {
            for(i = 0; i < newslist.data.length; i++) {
                 newslist.data[i].message = $sce.trustAsHtml(newslist.data[i].message);
                 newslist.data[i].title = $sce.trustAsHtml(newslist.data[i].title);
            }
        
            $scope.newslist = newslist.data;
        }
    };

    $urlRouterProvider.otherwise('/')
    $stateProvider.state('index', {
        url: '/',
        views: {
            'main': mainView,
            'right': {
                templateUrl: "/sub/login.html",
                controller: function($scope, $http, $state, $rootScope) {
                    $scope.user = {};

                    $scope.submit = function() {
                        $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
                        $http.post('/user/login', $.param($scope.user)).success(function(data, status, headers, config) {
                            if(data.success) {
                                $rootScope.loggedIn = true;
                                $state.go('home');
                            } else {
                                //TODO: print error
                            }
                        });
                    };
                }
            }
        }
    }).state('home', {
        url: '/home/',
        views: {
            'main': mainView,
            'right': {
                templateUrl: "/sub/menu.html",
                controller: function($scope){
                }
            }
        }
    });
});

swordssorceryApp.controller('MainController',   function($scope, $rootScope, $state) {
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
        if(toState.name !== 'index' && !$rootScope.loggedIn) {
            event.preventDefault();
            $state.go('index');
        }
    })
});