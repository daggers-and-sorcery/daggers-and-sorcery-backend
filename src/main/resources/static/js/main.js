var swordssorceryApp = angular.module('swordssorcery', ['ui.router']);

swordssorceryApp.config(function($stateProvider, $urlRouterProvider){
    indexMainView = {
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

    indexLoginView = {
         templateUrl: '/sub/login.html',
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

    indexTopView = {
        templateUrl: '/sub/top-empty.html',
    }

    $urlRouterProvider.otherwise('/')
    $stateProvider.state('index', {
        url: '/',
        data: {
            visibleWhenNotLoggedIn: true,
        },
        views: {
            'top': indexTopView,
            'main': indexMainView,
            'right': indexLoginView
        }
    }).state('home', {
        url: '/home/',
        views: {
            'top': {
                templateUrl: '/sub/top-login.html',
            },
            'main': indexMainView,
            'right': {
                templateUrl: "/sub/menu.html",
                controller: function($scope){
                }
            }
        }
    }).state('logout', {
    }).state('register', {
        url: '/register/',
        data: {
            visibleWhenNotLoggedIn: true,
        },
        views: {
            'top': indexTopView,
            'main': {
                templateUrl: '/sub/register.html',
                controller: function($scope){
                }
            },
            'right': indexLoginView
        }
    }).state('knowledge', {
        url: '/knowledge/',
        data: {
            visibleWhenNotLoggedIn: true,
        },
        views: {
            'top': indexTopView,
            'main': {
                templateUrl: '/sub/knowledge.html',
            },
            'right': indexLoginView
        }
    });
});

swordssorceryApp.controller('MainController',   function($scope, $rootScope, $state, $http) {
    //Get user info at start
    $http.get('/user/info').success(function(data, status, headers, config) {
        $rootScope.loggedIn = data.loggedIn === 'true';
        $state.go('home');
    });

    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
        //Logout the user
        if(toState.name === 'logout') {
            $http.get('/user/logout').success(function(data, status, headers, config) {
                $rootScope.loggedIn = false;
                $state.go('index');
            });
            event.preventDefault();
        }

        //Always redirect to index if not logged in
        if(!(toState.hasOwnProperty('data') && toState.data.hasOwnProperty('visibleWhenNotLoggedIn') && toState.data.visibleWhenNotLoggedIn) && !$rootScope.loggedIn && toState.name !== 'index') {
            event.preventDefault();
            $state.go('index');
        }

        //If logged in redirect index to home
        if(toState.name === 'index' && $rootScope.loggedIn) {
            event.preventDefault();
            $state.go('home');
        }
    })
});