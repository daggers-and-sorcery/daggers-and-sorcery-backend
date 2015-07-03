var swordssorceryApp = angular.module('swordssorcery', ['ui.router', 'ui.bootstrap', 'ngMessages']);

swordssorceryApp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider.state('index', {
        url: '/',
        data: {
            visibleWhenNotLoggedIn: true
        },
        views: {
            'top': window.view['top']['empty'],
            'main': window.view['main']['index'],
            'right': window.view['right']['menu']
        }
    }).state('home', {
        url: '/home/',
        views: {
            'top': window.view['top']['empty'],
            'main': window.view['main']['index'],
            'right': window.view['right']['menu']
        }
    }).state('logout', {}).state('register', {
        url: '/register/',
        data: {
            visibleWhenNotLoggedIn: true
        },
        views: {
            'top': window.view['top']['empty'],
            'main': {
                resolve: {
                     racelist: function ($http) {
                         return $http({method: 'GET', url: '/user/race/list'});
                     }
                 },
                templateUrl: '/partial/main/register.html',
                controller: function ($scope, $http, racelist) {
                    $scope.user = {};
                    $scope.visibleRace = 0;
                    $scope.errorList = [];
                    $scope.successfulRegistration = false;
                    $scope.race = racelist.data;

                    $scope.decreaseRace = function () {
                        if ($scope.visibleRace == 0) {
                            $scope.visibleRace = $scope.race.length - 1;
                        } else {
                            $scope.visibleRace--;
                        }
                    };
                    $scope.increaseRace = function () {
                        if ($scope.visibleRace == $scope.race.length - 1) {
                            $scope.visibleRace = 0;
                        } else {
                            $scope.visibleRace++;
                        }
                    };
                    $scope.submit = function (valid) {
                        if (valid) {
                            dataToSend = $scope.user;
                            dataToSend.race = $scope.race[$scope.visibleRace].name;

                            $http.post('/user/register', dataToSend).success(function (data, status, headers, config) {
                                $scope.errorList = [];
                                $scope.successfulRegistration = true;
                            }).error(function (data, status, headers, config) {
                                $scope.errorList = data;
                            });
                        }
                    };
                    $scope.raceAttributeModifierCount = function(raceId) {
                        return Object.keys($scope.race[raceId].racialAttributeModifiers).length;
                    };
                }
            },
            'right': window.view['right']['menu']
        }
    }).state('knowledge', {
        url: '/knowledge/',
        data: {
            visibleWhenNotLoggedIn: true,
        },
        views: {
            'top': window.view['top']['empty'],
            'main': {
                templateUrl: '/partial/main/knowledge.html',
            },
            'right': window.view['right']['menu']
        }
    }).state('character', {
        url: '/character/',
        views: {
            'top': window.view['top']['empty'],
            'main': {
                templateUrl: '/partial/main/character.html',
                controller: function ($scope, $http) {
                    $scope.attributes = {
                        basic: {},
                        combat: {}
                    };

                    $scope.attributePopover = {
                        templateUrl: '/partial/popover/attribute.html',
                    };

                    $scope.attributeBonusNameMap = {
                        'INITIAL': 'Initial value',
                        'RACIAL': 'Racial bonus',
                        'LEVEL': 'Skill level',
                        'SKILL': 'Skill bonus'
                    };

                    $http.get('/character/info').success(function (data, status, headers, config) {
                        var structuredAttributes = {
                            'GENERAL_PHYSICAL': {},
                            'GENERAL_MENTAL': {}
                        };

                        angular.forEach(data.data.attribute, function(value, key) {
                            if (value.attribute.attributeType === 'GENERAL') {
                                if(value.attribute.generalAttributeType === 'PHYSICAL') {
                                    structuredAttributes['GENERAL_PHYSICAL'][value.attribute.name] = value;
                                } else {
                                    structuredAttributes['GENERAL_MENTAL'][value.attribute.name] = value;
                                }
                            } else {
                                if(structuredAttributes[value.attribute.attributeType] === undefined) {
                                    structuredAttributes[value.attribute.attributeType] = {};
                                }

                                structuredAttributes[value.attribute.attributeType][value.attribute.name] = value;
                            }
                        });

                        data.data.attribute = structuredAttributes;

                        $scope.user = data.data;
                    });
                }
            },
            'right': window.view['right']['menu']
        }
    });
});

swordssorceryApp.controller('MainController', function ($scope, $rootScope, $state, $http) {
    //Get user info at start
    $http.get('/user/info').success(function (data, status, headers, config) {
        $rootScope.loggedIn = data.loggedIn === 'true';
        $state.go('home');
    });

    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        //Logout the user
        if (toState.name === 'logout') {
            $http.get('/user/logout').success(function (data, status, headers, config) {
                $rootScope.loggedIn = false;
                $state.go('index');
            });
            event.preventDefault();
        }

        //Always redirect to index if not logged in
        if (!(toState.hasOwnProperty('data') && toState.data.hasOwnProperty('visibleWhenNotLoggedIn') && toState.data.visibleWhenNotLoggedIn) && !$rootScope.loggedIn && toState.name !== 'index') {
            event.preventDefault();
            $state.go('index');
        }

        //If logged in redirect index to home
        if (toState.name === 'index' && $rootScope.loggedIn) {
            event.preventDefault();
            $state.go('home');
        }
    })
});

swordssorceryApp.directive('equals', function () {
    return {
        restrict: 'A', // only activate on element attribute
        require: '?ngModel', // get a hold of NgModelController
        link: function (scope, elem, attrs, ngModel) {
            if (!ngModel) return; // do nothing if no ng-model

            // watch own value and re-validate on change
            scope.$watch(attrs.ngModel, function () {
                validate();
            });

            // observe the other value and re-validate on change
            attrs.$observe('equals', function (val) {
                validate();
            });

            var validate = function () {
                // values
                var val1 = ngModel.$viewValue;
                var val2 = attrs.equals;

                // set validity
                ngModel.$setValidity('equals', !val1 || !val2 || val1 === val2);
            };
        }
    }
});

swordssorceryApp.filter('replace', function () {
    return function (input, target, replacement) {
        return input.split(target).join(replacement);
    }
});

swordssorceryApp.filter('capitalize', function () {
                     return function (input, format) {
                       if (!input) {
                         return input;
                       }
                       format = format || 'all';
                       if (format === 'first') {
                         // Capitalize the first letter of a sentence
                         return input.charAt(0).toUpperCase() + input.slice(1).toLowerCase();
                       } else {
                         var words = input.split(' ');
                         var result = [];
                         words.forEach(function(word) {
                           if (word.length === 2 && format === 'team') {
                             // Uppercase team abbreviations like FC, CD, SD
                             result.push(word.toUpperCase());
                           } else {
                             result.push(word.charAt(0).toUpperCase() + word.slice(1).toLowerCase());
                           }
                         });
                         return result.join(' ');
                       }
                     }
                     });

swordssorceryApp.directive('attributeListColumn', function () {
    return {
        restrict: 'E',
        scope: {
            columnAlignId: '=columnAlignId',
            columnAlign: '@columnAlign',
            attributes: '=source'
        },
        controller: function ($scope) {
            $scope.attributeNameMap = {
                'MOVEMENT': 'Movement Points',

                'LIFE': 'Life Points',
                'MANA': 'Mana Points',
                'INITIATION': 'Initiation',
                'ATTACK': 'Attack',
                'AIMING': 'Aiming',
                'DEFENSE': 'Defense',
                'SPELL_RESISTANCE': 'Spell resistance',
                'DAMAGE': 'Damage',
                'RANGED_DAMAGE': 'Ranged Damage',

                'STRENGTH': 'Strength',
                'PERCEPTION': 'Perception',
                'DEXTERITY': 'Dexterity',
                'SWIFTNESS': 'Swiftness',
                'VITALITY': 'Vitality',
                'ENDURANCE': 'Endurance',
                'BEAUTY': 'Beauty',

                'INTELLIGENCE': 'Intelligence',
                'WISDOM': 'Wisdom',
                'WILLPOWER': 'Willpower',
                'CHARISMA': 'Charisma'
            };

            $scope.attributePopover = {
                templateUrl: '/partial/popover/attribute.html',
            };

            $scope.attributeBonusNameMap = {
                'INITIAL': 'Initial value',
                'RACIAL': 'Racial bonus',
                'SKILL': 'Skill bonus',
                'LEVEL': 'Skill level'
            };
        },
        templateUrl: '/directive/attribute-list-column.html'
    };
});