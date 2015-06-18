var swordssorceryApp = angular.module('swordssorcery', ['ui.router', 'ngMessages']);

swordssorceryApp.config(function($stateProvider, $urlRouterProvider){
    var indexMainView = {
        templateUrl: "/partial/index.html",
        resolve: {
            newslist: function ($http) {
                return $http({method: 'GET', url: '/news/last'});
            }
        },
        controller: function ($scope, $http, $sce, newslist) {
            for (var i = 0; i < newslist.data.length; i++) {
                newslist.data[i].message = $sce.trustAsHtml(newslist.data[i].message);
                newslist.data[i].title = $sce.trustAsHtml(newslist.data[i].title);
            }

            $scope.newslist = newslist.data;
        }
    };

    var indexMenuView = {
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
    };

    var indexTopView = {
        templateUrl: '/partial/top/navbar.html'
    };

    $urlRouterProvider.otherwise('/');
    $stateProvider.state('index', {
        url: '/',
        data: {
            visibleWhenNotLoggedIn: true
        },
        views: {
            'top': indexTopView,
            'main': indexMainView,
            'right': indexMenuView
        }
    }).state('home', {
        url: '/home/',
        views: {
            'top': indexTopView,
            'main': indexMainView,
            'right': indexMenuView
        }
    }).state('logout', {
    }).state('register', {
        url: '/register/',
        data: {
            visibleWhenNotLoggedIn: true
        },
        views: {
            'top': indexTopView,
            'main': {
                templateUrl: '/partial/register.html',
                controller: function($scope, $http){
                    $scope.user = {};
                    $scope.visibleRace = 0;
                    $scope.errorList = [];
                    $scope.successfulRegistration = false;
                    $scope.race = [
                        {
                            name: 'Human',
                            description: 'Human Description'
                        },
                        {
                            name: 'Orc',
                            description: 'Orc Description'
                        },
                        {
                            name: 'Dwarf',
                            description: 'Dwarf Description'
                        },
                        {
                            name: 'Elf',
                            description: 'Elf Description'
                        },
                        {
                            name: 'Dark Elf',
                            description: 'Dark elf Description'
                        },
                        {
                            name: 'Lizardmen',
                            description: 'Lizardmen Description'
                        },
                        {
                            name: 'Gnome',
                            description: 'Human Description'
                        },
                        {
                            name: 'Draconic',
                            description: 'Draconic Description'
                        },
                    ];

                    $scope.decreaseRace = function() {
                        if ($scope.visibleRace == 0) {
                            $scope.visibleRace = $scope.race.length - 1;
                        } else {
                            $scope.visibleRace--;
                        }
                    };
                    $scope.increaseRace = function() {
                        if ($scope.visibleRace == $scope.race.length - 1) {
                            $scope.visibleRace = 0;
                        } else {
                            $scope.visibleRace++;
                        }
                    };
                    $scope.submit = function(valid) {
                        if(valid) {
                            dataToSend = $scope.user;
                            dataToSend.race = $scope.visibleRace;

                            $http.post('/user/register', dataToSend).success(function(data, status, headers, config) {
                                $scope.errorList = [];
                                $scope.successfulRegistration = true;
                            }).error(function(data, status, headers, config) {
                                $scope.errorList = data;
                            });
                        }
                    };
                }
            },
            'right': indexMenuView
        }
    }).state('knowledge', {
        url: '/knowledge/',
        data: {
            visibleWhenNotLoggedIn: true,
        },
        views: {
            'top': indexTopView,
            'main': {
                templateUrl: '/partial/knowledge.html',
            },
            'right': indexMenuView
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

swordssorceryApp.directive('equals', function() {
  return {
    restrict: 'A', // only activate on element attribute
    require: '?ngModel', // get a hold of NgModelController
    link: function(scope, elem, attrs, ngModel) {
      if(!ngModel) return; // do nothing if no ng-model

      // watch own value and re-validate on change
      scope.$watch(attrs.ngModel, function() {
        validate();
      });

      // observe the other value and re-validate on change
      attrs.$observe('equals', function (val) {
        validate();
      });

      var validate = function() {
        // values
        var val1 = ngModel.$viewValue;
        var val2 = attrs.equals;

        // set validity
        ngModel.$setValidity('equals', ! val1 || ! val2 || val1 === val2);
      };
    }
  }
});