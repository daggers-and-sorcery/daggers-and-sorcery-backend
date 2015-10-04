/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;
/******/
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	// Router
	__webpack_require__(4);
	
	// Service
	__webpack_require__(22);
	
	// Controllers
	__webpack_require__(23);
	
	// Directives
	__webpack_require__(24);
	__webpack_require__(25);
	__webpack_require__(26);
	__webpack_require__(28);
	__webpack_require__(30);
	__webpack_require__(1);
	__webpack_require__(32);
	__webpack_require__(34);
	
	// Filters
	__webpack_require__(35);
	__webpack_require__(36);
	
	// Constants
	__webpack_require__(37);
	
	// Interceptors
	__webpack_require__(38);
	__webpack_require__(39);

/***/ },
/* 1 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).directive('spellListColumn', function () {
	    return {
	        restrict: 'E',
	        scope: {
	            columnAlignId: '=columnAlignId',
	            columnAlign: '@columnAlign',
	            spellist: '=source'
	        },
	        controller: function ($scope, $http, $rootScope) {
	            $scope.spellPopover = {
	                templateUrl: '/partial/popover/spell.html'
	            };
	
	            $scope.cast = function(spell) {
	                $http.get('/spell/cast/'+spell).then(function(response) {
	                    if(response.data.data.success) {
	                        $rootScope.$broadcast('profile-update-needed');
	                    }
	                });
	            };
	        },
	        template: __webpack_require__(3)
	    };
	});

/***/ },
/* 2 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = angular.module('swordssorcery', ['ui.router', 'ui.bootstrap', 'ngMessages']);

/***/ },
/* 3 */
/***/ function(module, exports) {

	module.exports = "<div class=\"col-xs-4 attribute-list-column-{{columnAlign}}\">\r\n    <ul class=\"list-group attribute-list-group\">\r\n        <li class=\"list-group-item attribute-list-item\" ng-repeat=\"spell in spellist\" ng-if=\"$index % 3 === columnAlignId\">\r\n            <span ng-bind=\"spell.name\"></span>\r\n            <span class=\"pull-right inventory-item-watch\" ng-click=\"cast(spell.id)\" style=\"width: 26px;height: 26px;margin-top: -3.5px;cursor: pointer;\" ng-if=\"!spell.combatSpell\"><img style=\"margin-top: -8px;width: 14px;height: 14px;\" src=\"/icon/use.svg\"></span>\r\n            <span class=\"glyphicon glyphicon-eye-open pull-right inventory-item-watch\" popover-template=\"spellPopover.templateUrl\" popover-trigger=\"mouseenter\" popover-placement=\"right\"></span>\r\n        </li>\r\n    </ul>\r\n</div>";

/***/ },
/* 4 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).config(function ($stateProvider, $urlRouterProvider) {
	    $urlRouterProvider.otherwise('/');
	
	    $stateProvider
	        .state(__webpack_require__(7))
	        .state(__webpack_require__(9))
	        .state(__webpack_require__(10))
	        .state(__webpack_require__(11))
	        .state(__webpack_require__(13))
	        .state(__webpack_require__(5))
	        .state(__webpack_require__(14))
	        .state(__webpack_require__(15))
	        .state(__webpack_require__(17))
	        .state(__webpack_require__(19))
	        .state(__webpack_require__(21));
	});

/***/ },
/* 5 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = {
	    name: 'character',
	    url: '/character/',
	    templateUrl: '/partial/main/character.html',
	    resolve: {
	        characterData: function ($http, characterDataFormatter) {
	            return $http.get('/character/info').then(function(response) {
	                return characterDataFormatter.format(response.data);
	            });
	        }
	    },
	    controller: __webpack_require__(6)
	};

/***/ },
/* 6 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = function ($scope, $http, ATTRIBUTE_BONUS_MAP, characterData, $rootScope, characterDataFormatter) {
	     $scope.attributePopoverUrl = '/partial/popover/attribute.html';
	     $scope.user = characterData;
	     $scope.attributeBonusNameMap = ATTRIBUTE_BONUS_MAP;
	
	     $scope.$on('profile-update-needed', function(event, args) {
	         $http.get('/character/info').then(function(response) {
	               $scope.user = characterDataFormatter.format(response.data);
	         });
	     });
	
	     $scope.unequip = function(slot) {
	         $http.get('/unequip/'+slot).then(function(response) {
	             if(response.data.data.success) {
	                 $rootScope.$broadcast('profile-update-needed');
	             } else {
	                 //TODO: error happened
	             }
	         });
	     };
	 };

/***/ },
/* 7 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = {
	    name: 'index',
	    url: '/',
	    data: {
	        visibleWhenNotLoggedIn: true
	    },
	    templateUrl: "/partial/main/index.html",
	    resolve: {
	        newslist: function ($http) {
	            return $http({method: 'GET', url: '/news/last'});
	        }
	    },
	    controller: __webpack_require__(8)
	};

/***/ },
/* 8 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = function ($scope, $http, $sce, newslist) {
	     for (var i = 0; i < newslist.data.length; i++) {
	         newslist.data[i].message = $sce.trustAsHtml(newslist.data[i].message);
	         newslist.data[i].title = $sce.trustAsHtml(newslist.data[i].title);
	     }
	
	     $scope.newslist = newslist.data;
	};

/***/ },
/* 9 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = {
	    name: 'home',
	    url: '/home/',
	    templateUrl: "/partial/main/index.html",
	    resolve: {
	        newslist: function ($http) {
	            return $http({method: 'GET', url: '/news/last'});
	        }
	    },
	    controller: __webpack_require__(8)
	};

/***/ },
/* 10 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = {
	    name: 'logout'
	};

/***/ },
/* 11 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = {
	    name: 'registration',
	    url: '/registration/',
	    data: {
	        visibleWhenNotLoggedIn: true
	    },
	    resolve: {
	        racelist: function ($http) {
	            return $http({method: 'GET', url: '/user/race/list'});
	        }
	    },
	    template: __webpack_require__(12),
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
	                var dataToSend = $scope.user;
	
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
	};

/***/ },
/* 12 */
/***/ function(module, exports) {

	module.exports = "<div class=\"panel panel-default panel-shadow\">\r\n    <div class=\"panel-heading\">Registration</div>\r\n    <div class=\"panel-body\">\r\n        <div class=\"alert alert-danger\" role=\"alert\" ng-show=\"errorList.length > 0\">\r\n            <div ng-repeat=\"error in errorList\" ng-bind=\"error\"></div>\r\n        </div>\r\n        <div class=\"alert alert-success\" role=\"alert\" ng-show=\"successfulRegistration\">\r\n            Congratulations, your registration is successful!\r\n        </div>\r\n        <form class=\"form-horizontal\" autocomplete=\"off\" name=\"registration_form\"  ng-submit=\"submit(registration_form.$valid)\" novalidate>\r\n            <div class=\"form-group has-feedback\" ng-class=\"{ 'has-error' : registration_form.email.$invalid && (registration_form.email.$touched || registration_form.$submitted), 'has-success': registration_form.email.$valid && registration_form.email.$touched}\">\r\n                <label for=\"email\" class=\"col-sm-2 control-label\">Email</label>\r\n                <div class=\"col-sm-10\">\r\n                    <input type=\"email\" class=\"form-control\" name=\"email\" id=\"email\" placeholder=\"Email\" ng-model=\"user.email\" ng-required=\"true\">\r\n                    <div ng-show=\"registration_form.email.$touched || registration_form.$submitted\" ng-messages=\"registration_form.email.$error\" ng-messages-multiple=\"true\" role=\"alert\">\r\n                        <div class=\"help-block\" ng-message=\"required\">The email is required.</div>\r\n                        <div class=\"help-block\" ng-message=\"email\">The email must be a valid email address.</div>\r\n                    </div>\r\n                    <span ng-show=\"registration_form.email.$touched || registration_form.$submitted\" ng-class=\"registration_form.email.$invalid && (registration_form.email.$touched || registration_form.$submitted) ? 'glyphicon-warning-sign' : 'glyphicon-ok'\" class=\"glyphicon form-control-feedback\" aria-hidden=\"true\"></span>\r\n                </div>\r\n            </div>\r\n            <div class=\"form-group has-feedback\" ng-class=\"{ 'has-error' : registration_form.username.$invalid && (registration_form.username.$touched || registration_form.$submitted), 'has-success': registration_form.username.$valid && registration_form.username.$touched}\">\r\n                <label for=\"username\" class=\"col-sm-2 control-label\">Username</label>\r\n                <div class=\"col-sm-10\">\r\n                    <input type=\"text\" class=\"form-control\" name=\"username\" id=\"username\" ng-minlength=\"6\" ng-maxlength=\"16\" ng-pattern=\"/^[0-9a-zA-Z]+$/\" placeholder=\"Username\" ng-model=\"user.username\" ng-required=\"true\">\r\n                    <div ng-show=\"registration_form.username.$touched || registration_form.$submitted\" ng-messages=\"registration_form.username.$error\" ng-messages-multiple=\"true\" role=\"alert\">\r\n                        <div class=\"help-block\" ng-message=\"required\">The username is required.</div>\r\n                        <div class=\"help-block\" ng-message=\"minlength\">The minimum username length is 6 characters.</div>\r\n                        <div class=\"help-block\" ng-message=\"maxlength\">The maximum username length is 16 characters.</div>\r\n                        <div class=\"help-block\" ng-message=\"pattern\">The username can only contain letters (a-z, A-Z) or numbers.</div>\r\n                    </div>\r\n                    <span ng-show=\"registration_form.username.$touched || registration_form.$submitted\" ng-class=\"registration_form.username.$invalid && (registration_form.username.$touched || registration_form.$submitted) ? 'glyphicon-warning-sign' : 'glyphicon-ok'\" class=\"glyphicon form-control-feedback\" aria-hidden=\"true\"></span>\r\n                </div>\r\n            </div>\r\n            <div class=\"form-group has-feedback\" ng-class=\"{ 'has-error' : registration_form.passwordFirst.$invalid && (registration_form.passwordFirst.$touched || registration_form.$submitted), 'has-success': registration_form.passwordFirst.$valid && registration_form.passwordFirst.$touched}\">\r\n                <label for=\"passwordFirst\" class=\"col-sm-2 control-label\">Password</label>\r\n                <div class=\"col-sm-10\">\r\n                    <input type=\"password\" class=\"form-control\" name=\"passwordFirst\" id=\"passwordFirst\" placeholder=\"Password\" ng-minlength=\"6\" ng-maxlength=\"16\" ng-model=\"user.passwordFirst\" ng-required=\"true\">\r\n                    <div ng-show=\"registration_form.passwordFirst.$touched || registration_form.$submitted\" ng-messages=\"registration_form.passwordFirst.$error\" ng-messages-multiple=\"true\" role=\"alert\">\r\n                        <div class=\"help-block\" ng-message=\"required\">The password is required.</div>\r\n                        <div class=\"help-block\" ng-message=\"minlength\">The minimum password length is 6 characters.</div>\r\n                        <div class=\"help-block\" ng-message=\"maxlength\">The maximum password length is 16 characters.</div>\r\n                        <div class=\"help-block\" ng-message=\"equals\">The two password must be equals!</div>\r\n                    </div>\r\n                    <span ng-show=\"registration_form.passwordFirst.$touched || registration_form.$submitted\" ng-class=\"registration_form.passwordFirst.$invalid && (registration_form.passwordFirst.$touched || registration_form.$submitted) ? 'glyphicon-warning-sign' : 'glyphicon-ok'\" class=\"glyphicon form-control-feedback\" aria-hidden=\"true\"></span>\r\n                </div>\r\n            </div>\r\n            <div class=\"form-group has-feedback\" ng-class=\"{ 'has-error' : registration_form.passwordSecond.$invalid && (registration_form.passwordSecond.$touched || registration_form.$submitted), 'has-success': registration_form.passwordSecond.$valid && registration_form.passwordSecond.$touched}\">\r\n                <label for=\"passwordSecond\" class=\"col-sm-2 control-label\">Password again</label>\r\n                <div class=\"col-sm-10\">\r\n                    <input type=\"password\" class=\"form-control\" name=\"passwordSecond\" id=\"passwordSecond\" placeholder=\"Password again\" ng-minlength=\"6\" ng-maxlength=\"16\" ng-model=\"user.passwordSecond\" ng-required=\"true\" equals=\"{{user.passwordFirst}}\">\r\n                    <div ng-show=\"registration_form.passwordSecond.$touched || registration_form.$submitted\" ng-messages=\"registration_form.passwordSecond.$error\" ng-messages-multiple=\"true\" role=\"alert\">\r\n                        <div class=\"help-block\" ng-message=\"required\">The password is required.</div>\r\n                        <div class=\"help-block\" ng-message=\"minlength\">The minimum password length is 6 characters.</div>\r\n                        <div class=\"help-block\" ng-message=\"maxlength\">The maximum password length is 16 characters.</div>\r\n                        <div class=\"help-block\" ng-message=\"equals\">The two password must be equals!</div>\r\n                    </div>\r\n                    <span ng-show=\"registration_form.passwordSecond.$touched || registration_form.$submitted\" ng-class=\"registration_form.passwordSecond.$invalid && (registration_form.passwordSecond.$touched || registration_form.$submitted) ? 'glyphicon-warning-sign' : 'glyphicon-ok'\" class=\"glyphicon form-control-feedback\" aria-hidden=\"true\"></span>\r\n                </div>\r\n            </div>\r\n            <div class=\"form-group\">\r\n                <label class=\"col-sm-2 control-label\">Race</label>\r\n                <div class=\"col-sm-10\">\r\n                    <div class=\"text-center race-paging-holder\">\r\n                        <div class=\"row\">\r\n                            <div class=\"col-sm-4 text-right\" style=\"padding: 0;\">\r\n                                <a class=\"btn btn-default btn-no-outline race-paging-btn\" ng-click=\"decreaseRace()\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span></a>\r\n                            </div>\r\n                            <div class=\"col-sm-4\" style=\"line-height: 2.2;\">\r\n                                <span class=\"race-name-holder\">~ <span ng-bind=\"race[visibleRace].name | replace:'_':' ' | capitalize\"></span> ~</span>\r\n                            </div>\r\n                            <div class=\"col-sm-4 text-left\" style=\"padding: 0;\">\r\n                                <a class=\"btn btn-default btn-no-outline race-paging-btn\" ng-click=\"increaseRace()\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span></a>\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                    <div>\r\n                        <div class=\"bold italic race-info-header\">Racial modifiers:</div>\r\n                        <div>\r\n                            <ul ng-if=\"raceAttributeModifierCount(visibleRace) > 0\">\r\n                                <li ng-repeat=\"(attribute, value) in race[visibleRace].racialAttributeModifiers\">{{value}}% - {{attribute | capitalize}}</li>\r\n                            </ul>\r\n                            <span class=\"italic race-info-text\" ng-if=\"raceAttributeModifierCount(visibleRace) == 0\">Humans doesn't have any advantages nor disadvantages.</span>\r\n                        </div>\r\n                        <div class=\"bold italic race-info-header\">Description:</div>\r\n                        <div class=\"race-info-text\">\r\n                            [[ - PLACEHOLDER - ]]\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class=\"form-group\">\r\n                <div class=\"col-sm-offset-2 col-sm-10 text-center\">\r\n                    <button type=\"submit\" class=\"btn btn-default\" ng-disabled=\"userForm.$invalid\">Register</button>\r\n                </div>\r\n            </div>\r\n        </form>\r\n    </div>\r\n</div>";

/***/ },
/* 13 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = {
	    name: 'knowledge',
	    url: '/knowledge/',
	    data: {
	        visibleWhenNotLoggedIn: true
	    },
	    templateUrl: '/partial/main/knowledge.html'
	};

/***/ },
/* 14 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = {
	    name: 'map',
	    url: '/map/',
	    templateUrl: "/partial/main/map.html",
	    resolve: {
	        position: function ($http) {
	            return $http.get('/character/position').then(function(response) {
	                return response.data.data;
	            });
	        }
	    },
	    controller: function ($scope, $http, $state, position) {
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
	            });
	        };
	
	        $scope.attack = function(target) {
	            $http.get('/map/combat/'+target).success(function (data, status, headers, config) {
	                if(!data.error) {
	                    $state.go('combat', {'combatMessages': data.combatMessages});
	                }
	            });
	        }
	    }
	};

/***/ },
/* 15 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = {
	    name: 'journal',
	    url: '/journal/:type',
	    templateUrl: "/partial/main/journal.html",
	    params: {
	        type: {
	            value: 'item'
	        }
	    },
	    resolve: {
	        journalInfo: function ($http, $stateParams) {
	            return $http({method: 'GET', url: '/journal/list/'+$stateParams.type});
	        }
	    },
	    controller: __webpack_require__(16)
	};

/***/ },
/* 16 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = function ($scope, $http, $stateParams, journalInfo) {
	    $scope.type = $stateParams.type;
	    $scope.list = journalInfo.data.data.journal_info;
	
	    $scope.changeType = function (newType) {
	        if($scope.type !== newType) {
	            $http({method: 'GET', url: '/journal/list/'+newType}).then(function(response) {
	                $scope.type = newType;
	                $scope.list = response.data.data.journal_info;
	            });
	        }
	    };
	};

/***/ },
/* 17 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = {
	    name: 'journal_entry',
	    url: '/journal/:type/:entryId',
	    templateUrl: "/partial/main/journal_entry.html",
	    resolve: {
	        journalEntryInfo: function($http, $stateParams) {
	            return $http({method: 'GET', url: '/journal/entry/'+$stateParams.type+'/'+$stateParams.entryId});
	        }
	    },
	    controller: __webpack_require__(18)
	};

/***/ },
/* 18 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = function ($scope, $stateParams, journalEntryInfo) {
	    $scope.type = $stateParams.type;
	    $scope.entry = journalEntryInfo.data.data.journal_entry;
	};

/***/ },
/* 19 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = {
	    name: 'settings',
	    url: '/settings/',
	    templateUrl: "/partial/main/settings.html",
	    controller: __webpack_require__(20)
	};

/***/ },
/* 20 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = function ($scope, $http) {
	    $scope.settings = [];
	
	    $scope.remove = function (id) {
	        $http.post('/combat/settings/remove', JSON.stringify({id: id})).success(function (data, status, headers, config) {
	            $scope.refreshSettingList();
	        });
	    };
	
	    $scope.saveNew = function () {
	        var dataToSend = $scope.newSetting;
	
	        if(dataToSend.trigger === 'monster') {
	            dataToSend.target = dataToSend.targetMonster.id;
	        }
	
	        delete dataToSend.targetMonster;
	
	        dataToSend.use = dataToSend.use.id;
	        dataToSend.type = dataToSend.type.toUpperCase();
	        dataToSend.trigger = dataToSend.trigger.toUpperCase();
	
	        $http.post('/combat/settings/insert', JSON.stringify(dataToSend)).success(function (data, status, headers, config) {
	            $scope.refreshSettingList();
	        });
	
	        $scope.clearNew();
	    };
	
	    $scope.refreshSettingList = function() {
	        $http.get('/combat/settings/list').success(function (data, status, headers, config) {
	            $scope.settings = data.data.settings;
	        });
	    };
	
	    $scope.clearNew = function () {
	        $scope.newSetting = {};
	
	        $scope.newSetting.type = "item";
	
	        $scope.refreshUse();
	
	        $scope.newSetting.trigger = "turn";
	
	        //$scope.targetType = "number";
	        $scope.refreshTarget();
	    };
	
	    $scope.refreshUse = function() {
	        if($scope.newSetting.type == "spell") {
	            $scope.usables = [];
	            $http.get('/combat/settings/usable/spell').then(function(response) {
	                $scope.usables = response.data.data.spellList;
	
	                if($scope.usables.length > 0) {
	                    $scope.newSetting.use = $scope.usables[0];
	                }
	            });
	        } else {
	            $scope.usables = [];
	            $http.get('/combat/settings/usable/item').then(function(response) {
	                $scope.usables = response.data.data.itemList;
	
	                if($scope.usables.length > 0) {
	                    $scope.newSetting.use = $scope.usables[0];
	                }
	            });
	        }
	    };
	
	    $scope.refreshTarget = function() {
	        if ($scope.newSetting.trigger === 'monster') {
	            $scope.showMonsterSelector = true;
	
	            $http.get('/combat/settings/specific_monsters').then(function(response) {
	                $scope.monsterList = response.data.data.monsterList;
	
	                if($scope.monsterList.length > 0) {
	                    $scope.newSetting.targetMonster = $scope.monsterList[0];
	                }
	            });
	        } else {
	            $scope.showMonsterSelector = false;
	            $scope.newSetting.target = 1;
	        }
	    };
	
	    //Initialise
	    $scope.clearNew();
	    $scope.refreshSettingList();
	};

/***/ },
/* 21 */
/***/ function(module, exports) {

	'use strict';
	
	module.exports = {
	    name: 'combat',
	    url: '/combat/',
	    params: {
	        'combatMessages': null
	    },
	    templateUrl: "/partial/main/combat.html",
	    controller: function ($scope, $stateParams, $sce) {
	        var finalMessages = {};
	
	        angular.forEach($stateParams.combatMessages, function(value, key) {
	            var message = value.messageData.message;
	
	            angular.forEach(value.messageData, function(value, key) {
	                message = message.replace("${"+key+"}", '<b>'+value+'</b>');
	            });
	
	            finalMessages[key] = {
	                'message': $sce.trustAsHtml(message)
	            };
	
	            if(value.messageData.icon != undefined) {
	                finalMessages[key].icon = '/icon/'+value.messageData['icon']+'.svg';
	            }
	
	            if(value.messageData.icon_color != undefined) {
	                finalMessages[key].icon_color = value.messageData['icon_color']+'-icon-color';
	            }
	        });
	
	        $scope.combatMessages = finalMessages;
	    }
	};

/***/ },
/* 22 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).factory('characterDataFormatter', function() {
	    return {
	        format: function(response) {
	            var structuredAttributes = {
	                'GENERAL_PHYSICAL': {},
	                'GENERAL_MENTAL': {}
	            };
	
	            angular.forEach(response.data.attribute, function(value, key) {
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
	
	            response.data.attribute = structuredAttributes;
	
	            return response.data;
	        }
	    }
	});

/***/ },
/* 23 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).controller('MainController', function ($scope, $rootScope, $state, $http, $timeout) {
	    $rootScope.user = {
	        loggedIn: false
	    }
	
	    //Get user info at start
	    $http.get('/user/info').success(function (data, status, headers, config) {
	        $rootScope.user = data.data;
	        $state.go('home');
	    });
	
	    $rootScope.$on('error', function (event, args) {
	        $scope.errorText = args.message;
	        $scope.errorVisible = true;
	
	        $scope.timeout = $timeout(function() {
	            $scope.errorVisible = false;
	        }, 5000);
	    });
	
	    $scope.closeError = function() {
	        $scope.errorVisible = false;
	        $timeout.cancel($scope.timeout);
	    }
	
	    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
	        //Logout the user
	        if (toState.name === 'logout') {
	            $http.get('/user/logout').success(function (data, status, headers, config) {
	                $rootScope.user.loggedIn = false;
	                $state.go('index');
	            });
	            event.preventDefault();
	        }
	
	        //Always redirect to index if not logged in
	        if (!(toState.hasOwnProperty('data') && toState.data.hasOwnProperty('visibleWhenNotLoggedIn') && toState.data.visibleWhenNotLoggedIn) && !$rootScope.user.loggedIn && toState.name !== 'index') {
	            event.preventDefault();
	            $state.go('index');
	        }
	
	        //If logged in redirect index to home
	        if (toState.name === 'index' && $rootScope.user.loggedIn) {
	            event.preventDefault();
	            $state.go('home');
	        }
	    })
	
	    //MERGED FROM RIGHT MENU
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
	                $http.get('/user/info').success(function (data, status, headers, config) {
	                    $rootScope.user = data.data;
	                    $state.go('home');
	                });
	            } else {
	                $scope.error = data.error;
	            }
	        });
	    };
	});

/***/ },
/* 24 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).directive('equals', function () {
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

/***/ },
/* 25 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).directive('gameMap', function ($injector) {
	    return {
	        template: '<div id="game-map"></div>',
	        scope: {
	            position: '='
	        },
	        link: function (scope, ele, attrs) {
	            var game = new Phaser.Game(550, 400, Phaser.AUTO, 'game-map', {
	                preload: preload,
	                create: create,
	                update: update,
	                render: render
	            });
	
	            function preload() {
	                game.load.tilemap('map_'+scope.position.map, 'map/' + scope.position.map + '.json', null, Phaser.Tilemap.TILED_JSON);
	                game.load.image('tiles', 'tileset/base.png');
	                game.load.image('player', 'image/player.png');
	            }
	
	            var map;
	            var layer;
	
	            var cursors;
	            var sprite;
	
	            function create() {
	                map = game.add.tilemap('map_'+scope.position.map);
	                map.addTilesetImage('tileset', 'tiles');
	
	                layer = map.createLayer('Ground');
	
	                layer.resizeWorld();
	
	                sprite = game.add.sprite(48, 48, 'player');
	                sprite.anchor.setTo(0, 0);
	                sprite.x = scope.position.x * 48;
	                sprite.y = scope.position.y * 48;
	
	                game.camera.follow(sprite);
	
	                cursors = game.input.keyboard.createCursorKeys();
	            }
	
	            function update() {
	            }
	
	            function render() {
	                game.debug.text('Tile X: ' + layer.getTileX(sprite.x), 32, 48, 'rgb(0,0,0)');
	                game.debug.text('Tile Y: ' + layer.getTileY(sprite.y), 32, 64, 'rgb(0,0,0)');
	            }
	
	            scope.$on('$destroy', function () {
	                game.destroy();
	            });
	
	            scope.$on('position', function (event, position) {
	                sprite.x = position.x * 48;
	                sprite.y = position.y * 48;
	
	                //TODO: add map changing
	            });
	        }
	    }
	});

/***/ },
/* 26 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).directive('attributeListColumn', function () {
	    return {
	        restrict: 'E',
	        scope: {
	            columnAlignId: '=columnAlignId',
	            columnAlign: '=columnAlign',
	            attributes: '=source'
	        },
	        controller: function ($scope, ATTRIBUTE_BONUS_MAP) {
	            $scope.attributePopover = {
	                templateUrl: '/partial/popover/attribute.html',
	            };
	
	            $scope.attributeBonusNameMap = ATTRIBUTE_BONUS_MAP;
	        },
	        template: __webpack_require__(27)
	    };
	});

/***/ },
/* 27 */
/***/ function(module, exports) {

	module.exports = "<div class=\"col-xs-4 attribute-list-column-{{columnAlign}}\">\r\n    <ul class=\"list-group attribute-list-group\">\r\n        <li class=\"list-group-item attribute-list-item\" ng-repeat=\"(attributeName, attributeValue) in attributes\" ng-if=\"$index % 3 === columnAlignId\" attribute-element attribute-value=\"attributeValue\" attribute-name=\"attributeName\"/>\r\n    </ul>\r\n</div>";

/***/ },
/* 28 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).directive('attributeElement', function () {
	    return {
	        restrict: 'A',
	        scope: {
	            attributeValue: '=attributeValue',
	            attributeName: '=attributeName'
	        },
	        controller: function ($scope, ATTRIBUTE_BONUS_MAP) {
	            $scope.attributePopover = {
	                templateUrl: '/partial/popover/attribute.html',
	            };
	
	            $scope.attributeBonusNameMap = ATTRIBUTE_BONUS_MAP;
	        },
	        template: __webpack_require__(29)
	    }
	});

/***/ },
/* 29 */
/***/ function(module, exports) {

	module.exports = "<span ng-bind=\"attributeValue.maximum > 0 ? attributeValue.actual + ' / ' + attributeValue.maximum : attributeValue.actual\" class=\"badge attribute-badge pull-right\" popover-template=\"attributePopover.templateUrl\" popover-trigger=\"mouseenter\" popover-placement=\"right\">{{attributeValue.actual}} / {{attributeValue.maximum}}</span>\r\n<span ng-bind=\"attributeName | replace:'_':' ' | capitalize\" />\r\n<div class=\"row\" ng-if=\"attributeValue.attribute.attributeType === 'GENERAL'\">\r\n    <div class=\"col-xs-3 attribute-progress-holder\">\r\n        <div class=\"attribute-progress\">{{attributeValue.pointsToNextLevel}} / 10</div>\r\n    </div>\r\n    <div class=\"col-xs-9 attribute-progress-bar-holder\">\r\n        <div class=\"progress\">\r\n            <div class=\"progress-bar progress-bar-success\" style=\"width: {{100 - (10 * (10 - attributeValue.pointsToNextLevel))}}%;\"></div>\r\n        </div>\r\n    </div>\r\n</div>";

/***/ },
/* 30 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).directive('inventoryListColumn', function () {
	    return {
	        restrict: 'E',
	        scope: {
	            columnAlignId: '=columnAlignId',
	            columnAlign: '@columnAlign',
	            inventory: '=source'
	        },
	        controller: function ($scope, $http, $rootScope) {
	            $scope.inventoryPopover = {
	                templateUrl: '/partial/popover/inventory.html'
	            };
	
	            $scope.equip = function(itemId) {
	                $http.get('/equip/'+itemId).then(function(response) {
	                    if(response.data.data.success) {
	                        $rootScope.$broadcast('profile-update-needed');
	                    } else {
	                        $rootScope.$broadcast('error', {message: 'You can\'t equip that item!'});
	                    }
	                });
	            };
	
	            $scope.use = function(item) {
	                $http.get('/item/use/'+item).then(function(response) {
	                    if(response.data.data.success) {
	                        $rootScope.$broadcast('profile-update-needed');
	                    }
	                });
	            };
	        },
	        template: __webpack_require__(31)
	    };
	});

/***/ },
/* 31 */
/***/ function(module, exports) {

	module.exports = "<div class=\"col-xs-4 attribute-list-column-{{columnAlign}}\">\r\n    <ul class=\"list-group attribute-list-group\">\r\n        <li class=\"list-group-item attribute-list-item\" ng-repeat=\"item in inventory\" ng-if=\"$index % 3 === columnAlignId\">\r\n            <span class=\"badge attribute-badge pull-left\" ng-bind=\"item.item.amount\"></span>\r\n            <span style=\"padding-left: 5px;\" ng-bind=\"item.definition.name\"></span>\r\n            <span class=\"pull-right inventory-item-watch\" ng-click=\"equip(item.definition.id)\" style=\"width: 26px;height: 26px;margin-top: -3.5px;cursor: pointer;\" ng-if=\"item.definition.equipment\"><img style=\"margin-top: -8px;\" src=\"/icon/equip.svg\"></span>\r\n            <span class=\"pull-right inventory-item-watch\" ng-click=\"use(item.definition.id)\" style=\"width: 26px;height: 26px;margin-top: -3.5px;cursor: pointer;\" ng-if=\"item.definition.usable\"><img style=\"margin-top: -8px;width: 14px;height: 14px;\" src=\"/icon/use.svg\"></span>\r\n            <span class=\"glyphicon glyphicon-eye-open pull-right inventory-item-watch\" popover-template=\"inventoryPopover.templateUrl\" popover-trigger=\"mouseenter\" popover-placement=\"right\"></span>\r\n        </li>\r\n    </ul>\r\n</div>";

/***/ },
/* 32 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).directive('equipmentElement', function () {
	    return {
	        restrict: 'A',
	        scope: {
	            equipmentValue: '=equipmentValue',
	            equipmentName: '=equipmentName'
	        },
	        controller: function ($scope, $http,$rootScope) {
	             $scope.inventoryPopover = {
	                 templateUrl: '/partial/popover/inventory.html',
	             };
	
	             //To use in inventory popup
	             $scope.item = {
	                definition: $scope.equipmentValue.description
	             }
	
	            $scope.unequip = function(itemId) {
	                $http.get('/unequip/'+itemId).then(function(response) {
	                    if(response.data.data.success) {
	                        $rootScope.$broadcast('profile-update-needed');
	                    } else {
	                        //TODO: error happened
	                    }
	                });
	            };
	        },
	        template: __webpack_require__(33)
	    }
	});

/***/ },
/* 33 */
/***/ function(module, exports) {

	module.exports = "<span ng-if=\"!equipmentValue.empty\">{{equipmentName | capitalize}}:</span>\r\n<span ng-if=\"equipmentValue.empty\">{{equipmentName | capitalize}}:</span>\r\n<span ng-if=\"!equipmentValue.empty\" class=\"pull-right inventory-item-watch\" ng-click=\"unequip(equipmentName)\" style=\"width: 26px;height: 26px;margin-top: -3.5px;cursor: pointer;\"><img style=\"margin-top: -8px;\" src=\"/icon/equip.svg\"></span>\r\n<span ng-if=\"!equipmentValue.empty\" class=\"glyphicon glyphicon-eye-open pull-right inventory-item-watch\" popover-template=\"inventoryPopover.templateUrl\" popover-trigger=\"mouseenter\" popover-placement=\"right\"></span>\r\n<span ng-if=\"!equipmentValue.empty\" class=\"pull-right\" ng-bind=\"equipmentValue.description.name\"></span>\r\n<span ng-if=\"equipmentValue.empty\" class=\"pull-right\">Empty</span>";

/***/ },
/* 34 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).directive('svgImage', ['$http', function($http) {
	    return {
	      restrict: 'E',
	      scope: {
	                src: '=src'
	              },
	      link: function(scope, element) {
	        var request = $http.get(
	          scope.src,
	          {'Content-Type': 'application/xml'}
	        );
	
	        scope.manipulateImgNode = function(data, elem){
	          var $svg = angular.element(data)[4];
	          var imgClass = elem.attr('class');
	          if(typeof(imgClass) !== 'undefined') {
	            var classes = imgClass.split(' ');
	            for(var i = 0; i < classes.length; ++i){
	              $svg.classList.add(classes[i]);
	            }
	          }
	          $svg.removeAttribute('xmlns:a');
	          return $svg;
	        };
	
	        request.success(function(data){
	          element.replaceWith(scope.manipulateImgNode(data, element));
	        });
	      }
	    };
	  }]);

/***/ },
/* 35 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).filter('replace', function () {
	    return function (input, target, replacement) {
	        return input.split(target).join(replacement);
	    }
	});

/***/ },
/* 36 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).filter('capitalize', function () {
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
	            words.forEach(function (word) {
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

/***/ },
/* 37 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__(2).constant('ATTRIBUTE_BONUS_MAP', {
	    'INITIAL': 'Initial value',
	    'RACIAL': 'Racial bonus',
	    'SKILL': 'Skill bonus',
	    'LEVEL': 'Skill level',
	    'GENERAL_ATTRIBUTE': 'General attribute bonus',
	    'EQUIPMENT': 'Equipment bonus'
	});

/***/ },
/* 38 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).factory('combatUpdate', function($rootScope) {
	    return {
	         response: function (response) {
	             if (response.data.charinfo !== undefined && response.data.charinfo !== null) {
	                 $rootScope.user.mana = response.data.charinfo.mana;
	                 $rootScope.user.life = response.data.charinfo.health;
	                 $rootScope.user.movement = response.data.charinfo.movement;
	             }
	
	             return response;
	         }
	     };
	 });

/***/ },
/* 39 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	module.exports = __webpack_require__(2).config(['$httpProvider', function($httpProvider) {
	    $httpProvider.interceptors.push('combatUpdate');
	}]);

/***/ }
/******/ ]);
//# sourceMappingURL=bundle.js.map